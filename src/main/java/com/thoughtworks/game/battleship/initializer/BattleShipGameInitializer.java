package com.thoughtworks.game.battleship.initializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.thoughtworks.game.battleship.constants.Constants;
import com.thoughtworks.game.battleship.context.BattleShipGame;
import com.thoughtworks.game.battleship.context.BattleShipGame.GameContextBuilder;
import com.thoughtworks.game.battleship.domain.BattleArea;
import com.thoughtworks.game.battleship.domain.Cell;
import com.thoughtworks.game.battleship.domain.Player;
import com.thoughtworks.game.battleship.domain.Point;
import com.thoughtworks.game.battleship.domain.Ship;
import com.thoughtworks.game.battleship.domain.Ship.ShipType;
import com.thoughtworks.game.battleship.dto.BattleShipGameInputDto;
import com.thoughtworks.game.battleship.dto.BattleShipGameInputMapper;
import com.thoughtworks.game.battleship.exception.BsRuntimeException;
import com.thoughtworks.game.battleship.factory.BattleShipFactory;
import com.thoughtworks.game.battleship.rules.GameRuleValidator;

public class BattleShipGameInitializer implements GameInitializer {

	public BattleShipGame initializeBattleGame(final List<String> input) {
		BattleShipGameInputDto battleShipGameInputDto = BattleShipGameInputMapper.convert(input);
		BattleArea playerOneBattleArea = new BattleArea(battleShipGameInputDto.getRow(), battleShipGameInputDto.getColumn());
		BattleArea playerTwoBattleArea = new BattleArea(playerOneBattleArea);

		int noOfShipsForEachPlayer = battleShipGameInputDto.getNumberOfShips();
		List<Player> players = new ArrayList<Player>();
		players.add(new Player(Constants.playerNames.get(0), playerOneBattleArea, noOfShipsForEachPlayer));
		players.add(new Player(Constants.playerNames.get(1), playerTwoBattleArea, noOfShipsForEachPlayer));
		List<com.thoughtworks.game.battleship.dto.BattleShipGameInputDto.Ship> inputShipDetailList = battleShipGameInputDto.getShips();
		for (com.thoughtworks.game.battleship.dto.BattleShipGameInputDto.Ship shipDto : inputShipDetailList) {
			ShipType shipType = shipDto.getShipType();
			int width = shipDto.getWidth();
			int height = shipDto.getHeight();
			for (Player player : players) {
				Point point = shipDto.getLocation(player.getName());
				Ship ship = BattleShipFactory.getShip(shipType, width, height);
				for (int widthCount = 0; widthCount < width; widthCount++) {
					for (int heightCount = 0; heightCount < height; heightCount++) {
						Point shipLocation = new Point(point.getRowNumber() + widthCount,
								point.getColumnNumber() + heightCount);
						if (!GameRuleValidator.isValidPoint(shipLocation, player.getBattleArea())) {
							throw new BsRuntimeException("Invalid Ship Dimension");
						}
						Optional<Cell> battleCells = player.getBattleArea().getBattleCells(shipLocation);
						if (battleCells.isPresent()) {
							throw new BsRuntimeException(
									"Multiple ships found for same coordinates" + shipLocation.toString());
						} else {
							Cell cell = new Cell(point, ship);
							player.getBattleArea().addCell(cell);
						}
					}
				}
			}
		}
		
		for (final Player player : players) {
			battleShipGameInputDto.getShootingPoints(player.getName()).stream().forEach(point -> player.addMissileTarget(point));
		}
		GameContextBuilder contextBuilder = new GameContextBuilder();
		for (Player player : players) {
			contextBuilder.addPlayer(player);
		}
		return contextBuilder.build();
	}

}