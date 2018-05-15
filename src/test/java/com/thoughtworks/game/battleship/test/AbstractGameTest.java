package com.thoughtworks.game.battleship.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

import com.thoughtworks.game.battleship.test.constants.Constants;

public abstract class AbstractGameTest {

	private InputStream propertiesFileInputStream;
	
	protected Stream<String> inputDataStream = null;

	protected abstract String getTestDataFile();

	protected void setUp() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		Properties properties = loadPropertiesFile(classLoader);
		String folder = properties.getProperty("testdata.folder.location");
		String testDataFile = properties.getProperty(getTestDataFile());
		inputDataStream = Files.lines(Paths.get(folder + testDataFile));
	}

	private Properties loadPropertiesFile(ClassLoader classLoader) throws IOException {
		Properties properties = new Properties();
		propertiesFileInputStream = new FileInputStream(new File(classLoader.getResource(Constants.propertiesFile)
				.getFile()));
		properties.load(propertiesFileInputStream);
		return properties;
	}

	protected void teadDown() throws IOException {
		if (propertiesFileInputStream != null) {
			propertiesFileInputStream.close();
		}
	}

}