package com.example.georgiosmoschovis.aceindemo.services;

import android.util.Log;

import java.util.ArrayList;

import com.example.georgiosmoschovis.aceindemo.domain.Currencies;

/**
 * This class implements calculations on standard Insurance configurations modification.
 */
public class ECustomModificationItem {
	String[] modificationDescription;
	ArrayList<String> modificationElements = null;
	com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance insurance;
	
	public ECustomModificationItem(String[] s) {
		this.modificationDescription = s;
	}

	public void elicitateAttributes(String name) {
		String[] Price = modificationDescription[0].split(" ");
		float num = Float.parseFloat(Price[0]);
		Currencies curr = null;
		if(Price[1].equals("EUR")) curr = Currencies.EUR;
			else if(Price[1].equals("USD")) curr = Currencies.USD;
			else if(Price[1].equals("GBP")) curr = Currencies.GBP;
			else if(Price[1].equals("JPY")) curr = Currencies.JPY;
			else if(Price[1].equals("CHF")) curr = Currencies.CHF;
		com.example.georgiosmoschovis.aceindemo.domain.Money price = new com.example.georgiosmoschovis.aceindemo.domain.Money(num, curr);
		String difficulty = modificationDescription[1];
		int duration = Integer.parseInt(modificationDescription[2]);
		int capacity = Integer.parseInt(modificationDescription[3]);
		Log.e("NEW_DIFFICULTY", difficulty+"");
		Log.e("NEW_DURATION", duration+"");
		Log.e("NEW_CAPACITY", capacity+"");
		Log.e("NEW_PRICE", price.toString());
		insurance = new com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance(name, difficulty, duration, capacity, price);
	}
	
	public void generateModification() {
		String[] references = modificationDescription[modificationDescription.length-1].split(".");
		modificationElements = new ArrayList<String>(references.length);
		for(String ref: references) {
			if(ref.startsWith(" ")) ref = ref.substring(1, ref.length());
			modificationElements.add(ref);
		}
	}

	public com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance getInsurance() {
		return this.insurance;
	}

	public ArrayList<String> getGeneratedModification() {
		return modificationElements;
	}
}
