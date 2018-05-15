package com.thoughtworks.game.battleship.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Player {
	
	private String name;
	private List<Point> opponentHitCordinates = new ArrayList<Point>();
	private int prevHitCounter = -1;
	private int activeShipCount = -1;
	private BattleArea battleArea;
	
	
	
	public Player(String name, BattleArea battleArea, int shipCount) {
		this.name = name;
		this.battleArea = battleArea;
		this.activeShipCount = shipCount;
	}
	
	
	public boolean hitShip(Point targetLocation){
		boolean isSuccessfulAttack = false;
		Optional<Cell> battleCell = this.battleArea.getBattleCells(targetLocation);
		if(!battleCell.isPresent()){
			return false;
		}else{
			Optional<Ship> optionalShip = battleCell.get().getShip();
			if(!optionalShip.isPresent()){
				return false;
			}
			isSuccessfulAttack = optionalShip.get().attackShip(targetLocation);
			if(optionalShip.get().isShipDestroyed() && activeShipCount > 0){
				activeShipCount--;
			}
		}
		return isSuccessfulAttack;
		
	}
	
	public Optional<Point> getTargetLocation(){
		prevHitCounter += 1;
		if(opponentHitCordinates.size() <= prevHitCounter){
			return Optional.empty();
		}
		return Optional.ofNullable(opponentHitCordinates.get(prevHitCounter));
	}
	
	public int getNumberOfMissiles() {
		if(opponentHitCordinates.size() > prevHitCounter){
			return 1;
		}
		return 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	public boolean isAllShipDestroyed(){
		return activeShipCount > 0 ? false : true;
	}
	
	
	public void addMissileTarget(Point target){
		opponentHitCordinates.add(target);
	}
	
	public BattleArea getBattleArea(){
		return this.battleArea;
	}
	
	
}
