package com.thoughtworks.game.battleship.utils;

import java.util.Scanner;

import com.thoughtworks.game.battleship.dto.NumberSpaceChar;

public class Utils {
	
	private static Scanner scanner;
	static {
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Eg: for character=A, return value will be 1
	 * @param character
	 * @return
	 */
	public static int getAlphabetOrder(char character) {
		return character - 64;
	}

	public static char getChar(int character) {
		return (char)(character + 64);
	}

	public static String recieveStringInput(String messageForInput) {
		System.out.println(messageForInput);
		return scanner.nextLine();
	}

	public static int recieveIntegerInput(String inputString) {
		try {
			return Integer.parseInt(inputString.trim().substring(0));
		} catch (Exception e) {
			return -1;
		}
	}

	
	public static NumberSpaceChar recieveIntSpaceCharInput(String inputString) {
		NumberSpaceChar numberSpaceChar = null;
		try {
			numberSpaceChar = new NumberSpaceChar(Integer.parseInt(inputString.substring(0, inputString.indexOf(" "))),
					inputString.charAt(inputString.indexOf(" ") + 1));
		} catch (Exception e) {
			numberSpaceChar = new NumberSpaceChar(-1, '1');
		}
		return numberSpaceChar;
	}
	

	

	

	
}