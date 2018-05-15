package com.thoughtworks.game.battleship.initializer;

import java.util.List;

import com.thoughtworks.game.battleship.context.BattleShipGame;

public interface GameInitializer {

	public BattleShipGame initializeBattleGame(List<String> input);
}