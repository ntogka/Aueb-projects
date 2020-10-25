
import java.util.*;
import java.io.*;

public class ContractCollection
{

	private ArrayList <Contract> contracts = new ArrayList <Contract>();
	private ServiceCollection servicesColl;
	
	public ContractCollection(ServiceCollection c){
		servicesColl = c;
	}	

	// addContract() adds a contract in contracts list.
	public void addContract(Contract c)
	{
		contracts.add(c);
	}

	// listDisplay() displays a list of contracts.
	public void listDisplay()
	{
		for(Contract c: contracts)
		{
			System.out.println("\n" + c.toString());		
		}
	}

	// Getters.
	public Contract getContract(int x)
	{
		return contracts.get(x);	
	}

	public void getContractOfService(int x)
	{
		switch (x)
		{
		case 1: 

			// Phone service's contracts. 
			System.out.println("Contracts of phone service are:\n");
			for (Contract c: contracts)
			{
				if ((c.getService() instanceof PhoneServices) && !(c.getService() instanceof CardContractProgram))
				{
					System.out.println(c);
				}
			}
			break;
		case 2: 

			// Phone card service's contracts. 
			System.out.println("Contracts of card phone service are:\n");
			for (Contract c: contracts)
			{
				if (c.getService() instanceof CardContractProgram)
				{
					System.out.println(c);
				}
			}
			break;
		case 3: 

			// Internet service's contracts. 
			System.out.println("Contracts of mobile internet service are:\n");
			for (Contract c: contracts)
			{
				if (c.getService() instanceof InternetServices)
				{
					System.out.println(c);
				}
			}
			break;
		}	
	}
	
	// method clear() clears the list of contracts.
	public void clear(){
		contracts.clear();
	}

	// loadfile reads a file and store the contracts.
	public void loadFile(File f){

		BufferedReader reader = null;
		String line;

		try {
			reader = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			System.err.println("\nError opening file!");
		}
		try { 
	
			line = reader.readLine().toUpperCase().trim();

			if (line.equals("CONTRACT_LIST")){
				line = reader.readLine().toUpperCase().trim();

				if (line.equals("{")){

					line = reader.readLine().toUpperCase().trim();	

					
					// This while reads all contracts in CONTRACT_LIST.
					while (!line.equals("}")) {

						if (line.equals("CONTRACT")){
							line = reader.readLine().toUpperCase().trim();

							if (line.equals("{")){

								line = reader.readLine().toUpperCase().trim();

								String conNumber = null, serName = null, type = null, cust = null, phone = null, date = null, mobORdata = "0", sms = "0", pay = "Cash";
	
								// This while reads all attributes of each CONTRACT.
								while (!line.equals("}")) {

									StringTokenizer str = new StringTokenizer(line, " \t");
									String attributeName = str.hasMoreTokens() ? str.nextToken() : "";
									String attributeValue = str.hasMoreTokens() ? str.nextToken() : "";

									if (attributeName.equals("CONTRACT_NUMBER")){	
										conNumber = attributeValue;
									}

									if (attributeName.equals("SERVICE_NAME")){
										serName = attributeValue;
									}
									if (attributeName.equals("TYPE")){
										type = attributeValue;										
									}
									if (attributeName.equals("CUSTOMER")){
										cust = attributeValue;
									}
									if (attributeName.equals("PHONE_NUMBER")){
										phone = attributeValue;
									}
									if (attributeName.equals("ACTIVATION_DATE")){
										date = attributeValue;
									}
									if (attributeName.equals("PAYMENT")){
										pay = attributeValue;
									}
									if (attributeName.equals("TALK_TIME_CONSUMPTION")||attributeName.equals("DATA_CONSUMPTION")){
										mobORdata = attributeValue;
									}
									if (attributeName.equals("SMS_CONSUMPTION")) {
										sms = attributeValue;	
									}
									
									line = reader.readLine().toUpperCase().trim();
								}// End of While, end of elements of CONTRACT (attributes).
								

								if ((conNumber != null) && (type != null) && (serName != null) && (cust != null) && (phone != null) && (date != null)){

									if ((servicesColl.getServiceByName(serName) != null)&&(type.equalsIgnoreCase(servicesColl.getServiceByName(serName).getType()))){
										Contract contract = new Contract();
										contract.setId(Integer.parseInt(conNumber));
										contract.setService(servicesColl.getServiceByName(serName));
										contract.setName(cust);
										contract.setPhone(phone);
										contract.setDate(date);
										contract.setType(type);
										contract.setPayment(pay);
										contract.setExtraDiscount(pay);// Extra extra_discount=0 when payment=cash or extra_discount=o.1 when payment=card.
										if(contract.getService() instanceof PhoneServices) mobORdata = ((Integer.parseInt(mobORdata) % 60 == 0)? ""+Integer.parseInt(mobORdata)/ 60 : ""+ Integer.parseInt(mobORdata) / 60 + 1);
										// This if convert seconds to minutes if mobORdata refers to PhoneService object.
										contract.setConsumption(Integer.parseInt(mobORdata));
										contract.setSmsConsumption(Integer.parseInt(sms));
										contracts.add(contract);	
									}else{
										System.out.println("Contract of customer: " + cust + " doesn't represent any Service.");
									}
								}
							}
						}

						line = reader.readLine().toUpperCase().trim();

					}// End of While, end of elements of CONTRACT_LIST (contracts).
				}
			}
		} catch(IOException e){
			System.out.println("Error in file reading.");
		}
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
	
	}// End of method loadFile.

	// createFile writes a file with contract that exist in contracts list.
	public void createFile (String path) {

		BufferedWriter writer = null;
		File f = new File(path + ".txt");

		try	{
			writer = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(f)));
		}
		catch (FileNotFoundException e) {
			System.err.println("Error opening file for writing!");
		}
		try	{
			writer.write("CONTRACT_LIST\n{");
		}
		catch (IOException e) {
			System.err.println("Write error!");
		}
		for (Contract con: contracts) {
			try	{
				writer.write("\n\tCONTRACT\n\t{\n\t\tCONTRACT_NUMBER " + con.getId() + "\n\t\tSERVICE_NAME " + con.getService().getNameOfService()  + "\n\t\tCUSTOMER " + con.getName() + "\n\t\tTYPE " + con.getService().getType() + "\n\t\tPHONE_NUMBER " + con.getPhone() + "\n\t\tACTIVATION_DATE " + con.getDate() + "\n\t\tDISCOUNT " + con.getTotalDiscount() + "\n\t\tPAYMENT " + con.getPayment() + con.getBalance() + "\n\t}");

			}
			catch (IOException e) {
				System.err.println("Writtinh error at contract: " + con );
			}
		}
		try {
			writer.write("\n}");
			writer.close();
		}
		catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}// End of method createFile.

}



