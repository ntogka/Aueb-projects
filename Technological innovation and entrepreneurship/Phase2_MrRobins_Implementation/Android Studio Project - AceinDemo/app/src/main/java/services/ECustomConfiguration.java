package com.example.georgiosmoschovis.aceindemo.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements Escape Rooms Demonstration; as sample or modified configurations.
 */
public class ECustomConfiguration implements Serializable {
	private com.example.georgiosmoschovis.aceindemo.domain.EInsurance configuredInsurance;
	private ECustomConfigurationItem configuredInsuranceDetails;
	private ECustomModificationItem modifiedInsuranceDetails;
	private String name, description;
	private String[] descriptionPrefix;
	com.example.georgiosmoschovis.aceindemo.dao.InsuranceDAO dao;
	com.example.georgiosmoschovis.aceindemo.services.PConfigurationSummary summary;
	com.example.georgiosmoschovis.aceindemo.services.PModificationSummary modSummary;
	
	public ECustomConfiguration() {}
	
	public ECustomConfiguration(int insuranceID, com.example.georgiosmoschovis.aceindemo.dao.InsuranceDAO dao) {
		List<com.example.georgiosmoschovis.aceindemo.domain.EInsurance> data = dao.findAll();
		for(com.example.georgiosmoschovis.aceindemo.domain.EInsurance e: data) {
			if(e.CurrID == insuranceID)configuredInsurance = e;
		}
		this.dao = dao;
	}
	
	public ECustomConfiguration submit(int ID, com.example.georgiosmoschovis.aceindemo.dao.InsuranceDAO dao) {
		ECustomConfiguration e = new ECustomConfiguration(ID, dao);
		return e;
	}
	
	public ECustomConfiguration getCurrentConfiguration() {
		this.name = configuredInsurance.getName();
		this.description = configuredInsurance.getInsuranceDescription();
		ECustomConfigurationItem configurationElement = new ECustomConfigurationItem();
		Integer view = configurationElement.getItemInformation(dao, configuredInsurance);
		summary = new PConfigurationSummary(view);
		return this;
	}
	
	public ECustomConfiguration expand() {
		com.example.georgiosmoschovis.aceindemo.domain.Money price = configuredInsurance.getPrice();
		summary = new PConfigurationSummary(name, description, summary.getView(), price);
		return this;
	}
	
	public ECustomConfiguration modify(com.example.georgiosmoschovis.aceindemo.ui.UIDialogTemplate dialog) {
		descriptionPrefix = dialog.getFurtherDetails();
		modifiedInsuranceDetails = new ECustomModificationItem(descriptionPrefix);
		summary = new PConfigurationSummary(summary, descriptionPrefix[4]);
		return this;
	}
	
	public void setModificationEnabled(EmailProviderService mailServer, EmailAddress to) {
		modifiedInsuranceDetails.elicitateAttributes(configuredInsurance.getName());
		modifiedInsuranceDetails.generateModification();
		ArrayList<String> modification = modifiedInsuranceDetails.getGeneratedModification();
		com.example.georgiosmoschovis.aceindemo.domain.EInsurance tempInsurance = modifiedInsuranceDetails.getInsurance();
		configuredInsurance = new com.example.georgiosmoschovis.aceindemo.domain.EModifiedInsurance(tempInsurance.getName(), tempInsurance.getDifficulty(), tempInsurance.getDuration(),tempInsurance.getCapacity(), tempInsurance.getPrice(),modification);
		modSummary = new PModificationSummary(dao,configuredInsurance);
		summary = new PConfigurationSummary(tempInsurance, summary.getView(), descriptionPrefix[4]);
		modSummary.informDataWarehouse();
		if(to != null) {
			EmailMessage message = new EmailMessage(to, "Your Insurance reservation details!", summary.toString());
			modSummary.emailReservationDetails(mailServer, message);
		}
	}
	
	public com.example.georgiosmoschovis.aceindemo.domain.EInsurance getconfiguredInsurance(){
		return configuredInsurance;
	}
	
	public ECustomConfigurationItem getconfiguredInsuranceDetails() {
		return configuredInsuranceDetails;
	}
	
	public String getname() {
		return name;
	}
	
	public String getdescription() {
		return description;
	}
	
	public com.example.georgiosmoschovis.aceindemo.services.PConfigurationSummary getSummary() {
		return summary;
	}
}
