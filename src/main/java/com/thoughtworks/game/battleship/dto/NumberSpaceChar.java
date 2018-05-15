package com.thoughtworks.game.battleship.dto;

public class NumberSpaceChar {

	private int number;
	private char character;

	public NumberSpaceChar(int number, char character) {
		this.number = number;
		this.character = character;
	}

	public int getNumber() {
		return number;
	}

	public char getCharacter() {
		return character;
	}
}