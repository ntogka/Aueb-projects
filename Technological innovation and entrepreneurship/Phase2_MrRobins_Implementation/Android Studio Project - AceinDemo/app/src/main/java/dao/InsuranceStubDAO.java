package com.example.georgiosmoschovis.aceindemo.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.georgiosmoschovis.aceindemo.R;

/**
 * This class illustrates a sample data base object container.
 */
public class InsuranceStubDAO {
	InsuranceImplDAO stub = new InsuranceImplDAO();
	List<Integer> pictures = new ArrayList<Integer>();

	public InsuranceStubDAO() {}

	public void addEntities() {
		com.example.georgiosmoschovis.aceindemo.domain.EInsurance e1 = new com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance("Home Insurance", "Classic", 10, 1, new com.example.georgiosmoschovis.aceindemo.domain.Money(150, com.example.georgiosmoschovis.aceindemo.domain.Currencies.EUR));
		com.example.georgiosmoschovis.aceindemo.domain.EInsurance e2 = new com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance("Health Insurance", "Premium", 5, 4, new com.example.georgiosmoschovis.aceindemo.domain.Money(200, com.example.georgiosmoschovis.aceindemo.domain.Currencies.EUR));
		com.example.georgiosmoschovis.aceindemo.domain.EInsurance e3 = new com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance("Insurance Motor/Car", "Gold", 5, 2, new com.example.georgiosmoschovis.aceindemo.domain.Money(50, com.example.georgiosmoschovis.aceindemo.domain.Currencies.EUR));
		com.example.georgiosmoschovis.aceindemo.domain.EInsurance e4 = new com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance("Insurance Product", "Ultra Premium", 1, 1, new com.example.georgiosmoschovis.aceindemo.domain.Money(350, com.example.georgiosmoschovis.aceindemo.domain.Currencies.USD));
		com.example.georgiosmoschovis.aceindemo.domain.EInsurance e5 = new com.example.georgiosmoschovis.aceindemo.domain.EStandardInsurance("Insurance Patent", "Gold", 1, 1, new com.example.georgiosmoschovis.aceindemo.domain.Money(250, com.example.georgiosmoschovis.aceindemo.domain.Currencies.GBP));
		stub.save(e1); 
		stub.save(e2);
		stub.save(e3);
		stub.save(e4);
		stub.save(e5);
	}
	
	public void addPictures() {
		pictures.add(R.drawable.image1);
		pictures.add(R.drawable.image2);
		pictures.add(R.drawable.image3);
		pictures.add(R.drawable.image4);
		pictures.add(R.drawable.image5);
		InsuranceImplDAO.pictures = pictures;

	}
	
	public void initialize() {
		this.addEntities();
		this.addPictures();
	}
	
	public InsuranceDAO getDAO() {
		return stub;
	}
}
