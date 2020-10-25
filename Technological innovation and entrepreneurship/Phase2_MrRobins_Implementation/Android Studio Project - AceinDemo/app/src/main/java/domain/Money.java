package com.example.georgiosmoschovis.aceindemo.domain;

import java.io.Serializable;

/**
 * This class illustrates Money.
 */
public class Money implements Serializable {
	private float value;
	private Currencies currency;
	
	public Money(float value, Currencies currency) {
		this.value = value;
		this.currency = currency;
	}
	
	public float getValue() {
		return this.value;
	}
	
	public Currencies getCurrency() {
		return this.currency;
	}
	
	public String toString() {
		return this.value + " " + this.currency;
	}
}
