package com.thoughtworks.game.battleship.domain;

import java.util.EnumSet;
import java.util.Set;

public interface Ship {
	
	public static enum ShipType { 
		P, Q;
		public static Set<ShipType> validShipTypes = EnumSet.of(P,Q);
	};
	boolean attackShip(Point point);
	boolean isShipDestroyed();
	
}
