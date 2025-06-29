package utilities;

import java.io.*;
import java.util.*;

public class TestProperties {

	//this method loads the input file & return properties
	public Properties initiateProperties() {
		Properties prop = new Properties();
		try {
			File file = new File(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis); //looks in to the all key-value pairs in config.properties file
		} catch(Exception e) {
			e.printStackTrace();
		}
		return prop; //retuns the looked/populated key-value pairs
	}
	
	//utility method to get a specific property's value based on key
	public String getProperty(String property) {
		Properties prop = null;
		try {
			prop = initiateProperties(); //loads the file, looks in to the file & returns it
		} catch(Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(property); //returns the value for the given key
	}
}
