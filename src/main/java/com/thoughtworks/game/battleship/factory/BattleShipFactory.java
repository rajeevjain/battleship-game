package com.thoughtworks.game.battleship.factory;

import com.thoughtworks.game.battleship.domain.BattleShip;
import com.thoughtworks.game.battleship.domain.BattleShip.ShipBuilder;
import com.thoughtworks.game.battleship.domain.Ship;
import com.thoughtworks.game.battleship.domain.Ship.ShipType;
import com.thoughtworks.game.battleship.exception.BsRuntimeException;
import com.thoughtworks.ship.destruction.strategy.DoubleHitDestroyStrategy;
import com.thoughtworks.ship.destruction.strategy.ShipDestroyStrategy;
import com.thoughtworks.ship.destruction.strategy.SingleHitDestroyStrategy;


public class BattleShipFactory {
	
	public static Ship getShip(ShipType shipType, int width, int height) throws BsRuntimeException{
		if(shipType == null){
			throw new BsRuntimeException("Unknown ship type");
		}
		if(ShipType.P == shipType){
			ShipDestroyStrategy shipDestroyStrategy = new SingleHitDestroyStrategy();
			return buildShip(width, height, shipDestroyStrategy);
		}else if(ShipType.Q== shipType){
			ShipDestroyStrategy dest = new DoubleHitDestroyStrategy();
			return buildShip(width, height, dest);
		}
		throw new BsRuntimeException("Unknown ship type");
	}

	private static Ship buildShip(int width, int height, ShipDestroyStrategy shipDestroyStrategy) {
		ShipBuilder shipBuilder = new BattleShip.ShipBuilder(width*height, "SampleShip", shipDestroyStrategy);
		return shipBuilder.build();
	}

}
