package com.example.georgiosmoschovis.aceindemo.domain;
import java.util.ArrayList;

/**
 * This class illustrates a Modified Configured Insurance.
 */
public class EModifiedInsurance extends EInsurance {
	/* Domain Model: Instance variables */
	private Money ConfiguredPrice;
    private ArrayList<String> configInfo;
    
    /* Overloaded Constructor */
    public EModifiedInsurance(String name, String difficulty, int duration, int capacity, Money configuredPrice, ArrayList<String> configInfo) {
    	super(name, difficulty, duration, capacity);
    	ConfiguredPrice = configuredPrice;
        this.configInfo = configInfo;
    }

    /* Getters */
    public Money getPrice() {
        return ConfiguredPrice;
    }

    public ArrayList<String> getConfigInfo() {
        return configInfo;
    }
}
