package com.thoughtworks.game.battleship.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.thoughtworks.game.battleship.BattleAreaMain;
import com.thoughtworks.game.battleship.exception.BsRuntimeException;


public class InvalidBattleShipGame extends AbstractGameTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
	protected String getTestDataFile() {
		return "invalidScenario.testdata.filename";
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
	public void testInvalidShipCount()  {
		String inputSting = inputDataStream.findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		thrown.expect(BsRuntimeException.class);
		thrown.expectMessage("Invalid Ship count");
		BattleAreaMain.startNewGame(asList);
		
	}
	
	@Test
	public void testBattleAreaDimension() {
		String inputSting = inputDataStream.skip(1).findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		thrown.expect(BsRuntimeException.class);
		thrown.expectMessage("Invalid Battle Area Dimensions");
		BattleAreaMain.startNewGame(asList);
	}
	
	@Test
	public void testBattleAreaHavingShipOnSameCordinates(){
		String inputSting = inputDataStream.skip(2).findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		thrown.expect(BsRuntimeException.class);
		thrown.expectMessage("Multiple ships found for same coordinates");
		BattleAreaMain.startNewGame(asList);
	}
	
	@Test
	public void testBattleAreaHavingShipType() {
		String inputSting = inputDataStream.skip(3).findFirst().get();
		String[] split = inputSting.split(",");
		List<String> asList = Arrays.asList(split);
		thrown.expect(BsRuntimeException.class);
		thrown.expectMessage("Invalid Ship Type");
		BattleAreaMain.startNewGame(asList);
	}

}
