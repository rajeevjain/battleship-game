package com.thoughtworks.game.battleship.execution;

import com.thoughtworks.game.battleship.context.BattleShipGame;

public interface GameRunner {

	public BattleShipGame runGame(BattleShipGame context);
}