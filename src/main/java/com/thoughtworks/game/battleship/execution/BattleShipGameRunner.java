package com.thoughtworks.game.battleship.execution;

import java.util.Optional;

import com.thoughtworks.game.battleship.context.BattleShipGame;
import com.thoughtworks.game.battleship.context.BattleShipGame.Status;
import com.thoughtworks.game.battleship.domain.Player;
import com.thoughtworks.game.battleship.domain.Point;
import com.thoughtworks.game.battleship.utils.Utils;

public class BattleShipGameRunner implements GameRunner {

	public BattleShipGame runGame(BattleShipGame battleShipGame) {
		battleShipGame.startGame();
		while (battleShipGame.getStatus() == Status.RUNNING) {
			boolean isShipHit = shootMissile(battleShipGame.getShooter(),battleShipGame.getOpponent());
			if (isShipHit) {
				if (battleShipGame.hasShooterWon()) {
					battleShipGame.endGame();
				}
			} else {
				battleShipGame.switchShooter();
			}
		}
		if (battleShipGame.getStatus() == Status.WIN_LOSS) {
			System.out.println("Player-" + battleShipGame.getShooter().getName() + " won the battle");
		} else {
			System.out.println("match ended in peace");
		}
		
		return battleShipGame;
	}
	
	private boolean shootMissile(Player shooter, Player opponent){
		Optional<Point> targetLocation = shooter.getTargetLocation();
		if(targetLocation.isPresent()){
			boolean isShipHit = opponent.hitShip(targetLocation.get());
			printAttackOutcome(shooter,isShipHit, targetLocation.get());
			return isShipHit;
		}
		System.out.println("Player-"+ shooter.getName() + " no more missile left to launch");
		return false;
		
	}
	
	private void printAttackOutcome(Player shooter ,boolean isSuccessfullHit, Point targetPoint) {
		String hitStatus = "missed";
		if (isSuccessfullHit) {
			hitStatus = "hit";
		}
		System.out.println("Player-"+ shooter.getName() + " fires a missile with target "
				+ Utils.getChar(targetPoint.getRowNumber()) + targetPoint.getColumnNumber() + " which got " + hitStatus);
	}
	
	
	

}