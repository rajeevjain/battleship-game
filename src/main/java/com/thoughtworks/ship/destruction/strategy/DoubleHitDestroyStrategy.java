package com.thoughtworks.ship.destruction.strategy;

public class DoubleHitDestroyStrategy implements ShipDestroyStrategy {

	public int hitsRequiredToDestroy() {
		return 2;
	}

	

}