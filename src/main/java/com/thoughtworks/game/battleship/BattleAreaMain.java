package com.thoughtworks.game.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.game.battleship.context.BattleShipGame;
import com.thoughtworks.game.battleship.exception.BsRuntimeException;
import com.thoughtworks.game.battleship.execution.BattleShipGameRunner;
import com.thoughtworks.game.battleship.execution.GameRunner;
import com.thoughtworks.game.battleship.initializer.BattleShipGameInitializer;
import com.thoughtworks.game.battleship.initializer.GameInitializer;
import com.thoughtworks.game.battleship.utils.Utils;

public class BattleAreaMain {

	private static final Logger logger = LoggerFactory.getLogger(BattleAreaMain.class);
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String args[]) {
		
		List<String> inputList = new ArrayList();
		System.out.println("Eneter BattleShip Dimension:");
		inputList.add(scanner.nextLine());
		System.out.println("Eneter Number Of Ships for Player :");
		String noOfShipsStr = scanner.nextLine();
		inputList.add(noOfShipsStr);
		int noOfShips = Integer.parseInt(noOfShipsStr.trim());
		for(int i = 0; i < noOfShips; i ++){
			String recieveStringInput = Utils.recieveStringInput("Enter Ship Details with location :");
			inputList.add(recieveStringInput);
		}
		String recieveStringInput = Utils.recieveStringInput("Enter Missile Target for Player:");
		inputList.add(recieveStringInput);
		recieveStringInput = Utils.recieveStringInput("Enter Missile Target for Player:");
		inputList.add(recieveStringInput);
		startNewGame(inputList);
	}

	public static Optional<BattleShipGame> startNewGame(final List<String> input) throws BsRuntimeException{
		BattleShipGame battleShipGame = null;
		try {
			GameInitializer gameInitializer = new BattleShipGameInitializer();
			battleShipGame = gameInitializer.initializeBattleGame(input);
			GameRunner gameRunner = new BattleShipGameRunner();
			gameRunner.runGame(battleShipGame);
		} catch (BsRuntimeException e) {
			logger.error("runtime exception occured ", e);
			System.out.println("Game Exit. " + e.getMessage());
			throw e;
		}
		
		return  Optional.ofNullable(battleShipGame);
	}

}