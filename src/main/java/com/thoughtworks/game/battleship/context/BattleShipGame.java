package com.thoughtworks.game.battleship.context;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.thoughtworks.game.battleship.domain.Player;
import com.thoughtworks.game.battleship.exception.BsRuntimeException;

public class BattleShipGame {

	private Set<Player> players;
	private Status status = Status.NEW;
	private Player shooter;
	private Player opponent;

	private BattleShipGame(GameContextBuilder builder) {
		this.players = builder.players;
	}

	public enum Status {
		
		NEW, RUNNING, WIN_LOSS, PEACE;
	}
	
	public void startGame() {
		if (status == Status.NEW) {
			status = Status.RUNNING;
			shooter = players.stream().findFirst().get();
			opponent = getInitialOpponent();
		}
	}

	public void endGame() {
		if (status == Status.RUNNING) {
			status = Status.WIN_LOSS;
		}
	}

	public Player getShooter() {
		return shooter;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	

	private Player getInitialOpponent() {
		Iterator<Player> itr = players.iterator();
		Player temp = null;
		while (itr.hasNext()) {
			temp = itr.next();
			if (!temp.getName().equals(shooter.getName())) {
				break;
			}
		}
		return temp;
	}

	public void switchShooter() {
		if (shooter.getNumberOfMissiles() < 1 && opponent.getNumberOfMissiles() < 1 && status == Status.RUNNING) {
			status = Status.PEACE;
		}
		if (status == Status.RUNNING) {
			switchShooterAndOpponent();
			if (!isShooterHasMissile()) {
				System.out.println("Player-" + shooter.getName() + " has no more missiles left");
				switchShooter();
			}
		}
	}

	private void switchShooterAndOpponent() {
		Player temp = opponent;
		opponent = shooter;
		shooter = temp;
	}

	public boolean isShooterHasMissile() {
		return shooter.getNumberOfMissiles() > 0;
	}
	
	public boolean hasShooterWon() {
		return opponent.isAllShipDestroyed();
	}

	public Status getStatus() {
		return status;
	}

	
	public static class GameContextBuilder {

		private Set<Player> players = new HashSet<Player>();

		public GameContextBuilder() {}

		public GameContextBuilder addPlayer(Player player) {
			if (player != null) {
				players.add(player);
			}
			return this;
		}

		public BattleShipGame build() {
			validatePlayersCount();
			return new BattleShipGame(this);
		}

		private void validatePlayersCount() {
			if (players.size() != 2) {
				throw new BsRuntimeException("Game must have 2 players");
			}
		}
	}
	

}