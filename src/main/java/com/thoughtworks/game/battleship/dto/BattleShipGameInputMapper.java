package com.thoughtworks.game.battleship.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.game.battleship.constants.Constants;
import com.thoughtworks.game.battleship.domain.Point;
import com.thoughtworks.game.battleship.domain.Ship.ShipType;
import com.thoughtworks.game.battleship.exception.BsRuntimeException;
import com.thoughtworks.game.battleship.rules.GameRuleValidator;
import com.thoughtworks.game.battleship.utils.Utils;

public class BattleShipGameInputMapper {

	public static BattleShipGameInputDto convert(List<String> input) {
		if (input != null && input.size() > 0) {
			BattleShipGameInputDto battleShipGameInputDto = new BattleShipGameInputDto();
			String battleAreaDimension = input.get(0).trim();
			NumberSpaceChar numberSpaceChar = Utils.recieveIntSpaceCharInput(battleAreaDimension);
			int rows = Utils.getAlphabetOrder(numberSpaceChar.getCharacter());
			int columns = numberSpaceChar.getNumber();
			if (!GameRuleValidator.validateDimension(rows, columns)) {
				throw new BsRuntimeException("Invalid Battle Area Dimensions");
			}
			battleShipGameInputDto.setRow(rows);
			battleShipGameInputDto.setColumn(columns);
			int numberOfShips = 0;
			if (input.size() > 1) {
				numberOfShips = Utils.recieveIntegerInput(input.get(1));
			}
			if (numberOfShips < 1 || (numberOfShips > rows * columns)) {
				throw new BsRuntimeException("Invalid Ship count");
			}
			battleShipGameInputDto.setNumberOfShips(numberOfShips);
			if (input.size() < 2 + numberOfShips) {
				throw new BsRuntimeException("Incomplete information");
			}
			for (int shipCounter = 2; shipCounter < numberOfShips + 2; shipCounter++) {
				if (input.get(shipCounter).length() > 0) {
					String[] shipMetadata = input.get(shipCounter).trim().split(" ");
					if (shipMetadata.length == 5) {
						BattleShipGameInputDto.Ship ship = new BattleShipGameInputDto.Ship();
						Optional<ShipType> validShipType = ShipType.validShipTypes.stream().filter(shipTye -> shipTye.name()
								.equals(shipMetadata[0])).findAny();
						if(!validShipType.isPresent()){
							throw new BsRuntimeException("Invalid Ship Type");
						}
						ship.setShipType(validShipType.get());
						int shipHeight = Utils.recieveIntegerInput(shipMetadata[2]);
						int shipWidth = Utils.recieveIntegerInput(shipMetadata[1]);
						if (shipHeight < 1 || shipWidth < 1 || shipWidth > rows || shipHeight > columns) {
							throw new BsRuntimeException("Invalid ship size");
						}
						ship.setHeight(shipHeight);
						ship.setWidth(shipWidth);
						ship.addToShipLocation(Constants.playerNames.get(0), covertStringToPoint(shipMetadata[3] ,"Invalid ship location"));
						ship.addToShipLocation(Constants.playerNames.get(1), covertStringToPoint(shipMetadata[4],"Invalid ship location"));
						battleShipGameInputDto.addToShips(ship);
					} else {
						throw new BsRuntimeException("Invalid input for ship");
					}
				} else {
					throw new BsRuntimeException("Invalid input for ship");
				}
			}
			if (input.size() < 2 + numberOfShips + Constants.playerNames.size()) {
				throw new BsRuntimeException("Incomplete player information");
			}
			for (int playerCounter = 0; playerCounter < Constants.playerNames.size(); playerCounter++) {
				String[] missileLocations = input.get(2 + numberOfShips + playerCounter).trim().split(" ");
				String playerName = Constants.playerNames.get(playerCounter);
				List<Point> shootingPoints = new ArrayList<Point>();
				for (String missileLocation : missileLocations) {
					shootingPoints.add(covertStringToPoint(missileLocation, "Invalid missile location"));
				}
				if (shootingPoints.size() == 0) {
					throw new BsRuntimeException("Player Missiles should not be null");
				}
				battleShipGameInputDto.addShootingPoint(playerName, shootingPoints);
			}
			return battleShipGameInputDto;
		}
		throw new BsRuntimeException("Invalid input");
	}

	private static Point covertStringToPoint(String inputString, String invalidInputErrorMessage) {
		if(StringUtils.isEmpty(inputString)){
			throw new BsRuntimeException(invalidInputErrorMessage);
		}
		int rowNumber = Utils.getAlphabetOrder(inputString.charAt(0));
		int columnNumber = Utils.recieveIntegerInput(inputString.substring(1));
		if (!GameRuleValidator.validateDimension(rowNumber, columnNumber)) {
			throw new BsRuntimeException(invalidInputErrorMessage);
		}
		return new Point(rowNumber, columnNumber);
	}
}
