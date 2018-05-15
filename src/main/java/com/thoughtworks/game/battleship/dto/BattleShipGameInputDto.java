package com.thoughtworks.game.battleship.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.game.battleship.domain.Point;
import com.thoughtworks.game.battleship.domain.Ship.ShipType;

public class BattleShipGameInputDto {

	private int row;
	private int column;
	private int numberOfShips;
	private List<Ship> ships = new ArrayList<BattleShipGameInputDto.Ship>();

	// mapping of player name to shooting points
	private Map<String, List<Point>> shootingPoints = new HashMap<String, List<Point>>();

	public void addShootingPoint(String playerName, List<Point> points) {
		shootingPoints.putIfAbsent(playerName, points);
	}

	public List<Point> getShootingPoints(String playerName) {
		return Collections.unmodifiableList(shootingPoints.get(playerName));
	}

	public static class Ship {
		private ShipType shipType;
		private int height;
		private int width;

		// mapping of player name to point
		private Map<String, Point> shipLocation = new HashMap<String, Point>();

		public ShipType getShipType() {
			return shipType;
		}

		public void setShipType(ShipType shipType) {
			this.shipType = shipType;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public void addToShipLocation(String playerName, Point location) {
			this.shipLocation.put(playerName, location);
		}

		public Point getLocation(String playerName) {
			return this.shipLocation.get(playerName);
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getNumberOfShips() {
		return numberOfShips;
	}

	public void setNumberOfShips(int numberOfShips) {
		this.numberOfShips = numberOfShips;
	}

	public void addToShips(Ship ship) {
		this.ships.add(ship);
	}

	public List<Ship> getShips() {
		return this.ships;
	}
}
