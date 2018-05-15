package com.thoughtworks.game.battleship.domain;


import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.game.battleship.exception.BsRuntimeException;
import com.thoughtworks.game.battleship.rules.GameRuleValidator;
import com.thoughtworks.ship.destruction.strategy.ShipDestroyStrategy;
import com.thoughtworks.ship.destruction.strategy.SingleHitDestroyStrategy;


public class BattleShip implements Ship {
	
	private String shipName;
	private ShipDestroyStrategy shipDestroyStrategy;
	private Map<Point, Integer> pointsOnWhichShipAttacked;
	private int shipTotalPower = 0;
	
	private BattleShip(ShipBuilder builder) {
		this.shipName = builder.name;
		this.shipTotalPower = builder.totalNumberOfCells*(builder.strategy.hitsRequiredToDestroy());
		this.shipDestroyStrategy = builder.strategy;
		this.pointsOnWhichShipAttacked = new HashMap<Point, Integer>();
	}
	
	
	public boolean attackShip(Point point) {
		if(isShipDestroyed()){
			return false;
		}
		boolean isSuccessfullHit = false;
		if(pointsOnWhichShipAttacked.containsKey(point)){
			Integer timesShipAttacked = pointsOnWhichShipAttacked.get(point);
			int hitsRequiredToDestroy = shipDestroyStrategy.hitsRequiredToDestroy();
			if(hitsRequiredToDestroy <= timesShipAttacked){
				return false;
			}else{
				pointsOnWhichShipAttacked.put(point, timesShipAttacked++);
				shipTotalPower--;
				isSuccessfullHit = true;
			}
		}else{
			pointsOnWhichShipAttacked.put(point, 1);
			shipTotalPower--;
			isSuccessfullHit =  true;
		}
		
		return isSuccessfullHit;
		
	}


	public boolean isShipDestroyed() {
		return shipTotalPower >0 ? false : true;
	}
	
	
	public static class ShipBuilder {

		private int totalNumberOfCells;
		private String name;
		private ShipDestroyStrategy strategy = new SingleHitDestroyStrategy();

		public ShipBuilder(int totalNumberOfCells, String name,ShipDestroyStrategy strategy ) {
			this.totalNumberOfCells =  totalNumberOfCells;
			GameRuleValidator.validatePositiveInteger(totalNumberOfCells,	"Total Number of Cells should be greater than zero");
			if(strategy==null){
				throw new BsRuntimeException("Type of ship must be defined");
			}
			this.name = name;
			this.strategy = strategy;
		}
		
		public Ship build() {
			return new BattleShip(this);
		}
	}

}
