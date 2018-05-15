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

public class WinBattleshipGameTest extends AbstractGameTest {

	protected String getTestDataFile() {
		return "win.testdata.filename";
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
	public void testGameWonWhenPlayerHasOneShip() {
		String inputSting = inputDataStream.findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		Optional<BattleShipGame> startNewGame = BattleAreaMain.startNewGame(asList);
		if (startNewGame.isPresent()) {
			Assert.assertEquals(startNewGame.get().getStatus(), Status.WIN_LOSS);
		}

	}
	
	@Test
	public void testGameWonWhenPlayerHasMultipleShip() {
		String inputSting = inputDataStream.skip(1).findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		Optional<BattleShipGame> startNewGame = BattleAreaMain.startNewGame(asList);
		if (startNewGame.isPresent()) {
			Assert.assertEquals(startNewGame.get().getStatus(), Status.WIN_LOSS);
		}

	}
	
	/*@Test
	public void test() throws IOException {
		while(true){
			String inputSting = inputDataReader.readLine();
			if(inputSting == null){
				break;
			}
			String[] split = inputSting.split(",");
			List<String> asList = Arrays.asList(split);
			Optional<BattleShipGame> startNewGame = BattleAreaMain.startNewGame(asList);
			if(startNewGame.isPresent()){
				Assert.assertEquals(startNewGame.get().getStatus(), Status.WIN_LOSS);
			}
		}
		
	}*/
}