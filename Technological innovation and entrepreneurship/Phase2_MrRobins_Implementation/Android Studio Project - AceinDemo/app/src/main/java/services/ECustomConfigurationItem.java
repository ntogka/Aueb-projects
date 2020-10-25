package com.example.georgiosmoschovis.aceindemo.services;

import java.util.List;

/**
 * This class implements computations on sample or modified Insurance configurations.
 */
public class ECustomConfigurationItem {
	
	public Integer getItemInformation(com.example.georgiosmoschovis.aceindemo.dao.InsuranceDAO dao, com.example.georgiosmoschovis.aceindemo.domain.EInsurance configuredInsurance){
		List<Integer> data = dao.findDetails();
		return data.get(configuredInsurance.CurrID);
	}
}
