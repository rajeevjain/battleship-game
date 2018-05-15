package com.thoughtworks.game.battleship.rules;

import com.thoughtworks.game.battleship.domain.BattleArea;
import com.thoughtworks.game.battleship.domain.Point;
import com.thoughtworks.game.battleship.exception.BsRuntimeException;

public class GameRuleValidator {

	private GameRuleValidator() {}

	public static boolean validateDimension(int rows, int columns) {
		if (columns < 1 || columns > 9) {
			return false;
		}
		if (rows < 1 || rows > 26) {
			return false;
		}
		return true;
	}


	public static boolean isValidPoint(Point point, BattleArea battleArea) {
		if (point.getColumnNumber() < 0 || point.getColumnNumber() > battleArea.getColumns()
				|| point.getRowNumber() < 0 || point.getRowNumber() > battleArea.getRows()) {
			return false;
		}
		return true;
	}
	
	public static void validatePositiveInteger(int value, String errorMessage) {
		if (value <= 0) {
			throw new BsRuntimeException(errorMessage);
		}
	}
}