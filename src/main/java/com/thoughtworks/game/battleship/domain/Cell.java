package com.thoughtworks.game.battleship.domain;

import java.util.Optional;

public class Cell {
	
	private Ship ship;
	private Point cellCordinates;

	public Cell(Point point, Ship ship) {
		this.cellCordinates = point;
		this.ship = ship;
	}


	public Point getPoint() {
		return cellCordinates;
	}

	/**
	 * @return the ship
	 */
	public Optional<Ship> getShip() {
		return Optional.ofNullable(ship);
	}
}