package com.thoughtworks.game.battleship.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.game.battleship.BattleAreaMain;
import com.thoughtworks.game.battleship.context.BattleShipGame;
import com.thoughtworks.game.battleship.context.BattleShipGame.Status;

public class DrawBattleshipGameTest extends AbstractGameTest {

	protected String getTestDataFile() {
		return "draw.testdata.filename";
	}

	@Before
	public void setUp() throws IOException {
		super.setUp();
	}

	@After
	public void teadDown() throws IOException {
		super.teadDown();
	}

	@Test
	public void testGameDrawWhenPlayerHasOneShip() {
		String inputSting = inputDataStream.findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		Optional<BattleShipGame> startNewGame = BattleAreaMain.startNewGame(asList);
		if (startNewGame.isPresent()) {
			Assert.assertEquals(startNewGame.get().getStatus(), Status.PEACE);
		}

	}
	
	@Test
	public void testGameDrawWhenPlayerHasMultipleShip() {
		String inputSting = inputDataStream.skip(1).findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		Optional<BattleShipGame> startNewGame = BattleAreaMain.startNewGame(asList);
		if (startNewGame.isPresent()) {
			Assert.assertEquals(startNewGame.get().getStatus(), Status.PEACE);
		}

	}
	
}