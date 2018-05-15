package com.thoughtworks.ship.destruction.strategy;

public class SingleHitDestroyStrategy implements ShipDestroyStrategy {

	public int hitsRequiredToDestroy() {
		return 1;
	}

	

}
