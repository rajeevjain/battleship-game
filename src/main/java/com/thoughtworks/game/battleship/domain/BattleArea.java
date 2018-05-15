package com.thoughtworks.game.battleship.domain;

import java.util.Optional;

import com.thoughtworks.game.battleship.utils.Utils;

public class BattleArea {
	private int rows;
	private int columns;
	private Cell[][] battleCells;
	
	public BattleArea(BattleArea battleArea) {
		this.rows = battleArea.getRows();
		this.columns = battleArea.getColumns();
		this.battleCells = new Cell[this.rows][this.columns];
	}

	public BattleArea(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		battleCells = new Cell[this.rows][this.columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Optional<Cell> getBattleCells(Point point) {
		return Optional.ofNullable(battleCells[point.getRowNumber()][point.getColumnNumber()]);
	}
	
	public void addCell(Cell cell) {
		battleCells[cell.getPoint().getRowNumber()][cell.getPoint().getColumnNumber()] = cell;
	} 
		 


}