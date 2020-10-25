
import java.util.*;
import java.io.*;

public class ServiceCollection
{
	private ArrayList <AllServices> services = new ArrayList <AllServices>();
	
	// addService() adds a service in services list.
	public void addService(AllServices s)
	{
		services.add(s);
	}

	// listDisplay() displays a list of services.
	public void listDisplay()
	{
		for(AllServices s: services)
		{
			System.out.println("\n" + s.toString());		
		}
	}

	// Getters.
	public AllServices getService(int x)
	{
		return services.get(x);	
	}
	
	// getServiceByName return the service witch has a specific name.
	public AllServices getServiceByName(String name) {

		for (AllServices service : services) {

			if (service.getNameOfService().equalsIgnoreCase(name))
				return service;
		}
		return null;
	}

	// loadFile reads a file and store the services.
	public void loadFile(String path) {

		File f = null;
		BufferedReader reader = null;
		String line;

		try {
			f = new File(path);
		} catch (NullPointerException e) {
			System.err.println("File not found.");
		}
		try {
			reader = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file!");
		}
		try { 
			line = reader.readLine().toUpperCase().trim();
			if (line.equals("SERVICE_LIST")){
				line = reader.readLine().toUpperCase().trim();
				if (line.equals("{")){

					line = reader.readLine().toUpperCase().trim();

					// This while reads all services from SERVICE_LIST.
					while (!line.equals("}")) {

						if (line.equals("SERVICE")){
							line = reader.readLine().toUpperCase().trim();
							if (line.equals("{")){

								AllServices service = null;
								line = reader.readLine().toUpperCase().trim();
								String type = null, serName = null, monthPrice = null, sms = "50", mobMin = "100", data = "5", discount = "0.20", balance = "0";
								String talkTimeCost = "0.25", smsCost = "0.08", dataCost = "2";	

								// This while reads all attributes each SERVICE.
								while (!line.equals("}")) {

									StringTokenizer str = new StringTokenizer(line, " \t");
									String attributeName = str.hasMoreTokens() ? str.nextToken() : "";
									String attributeValue = str.hasMoreTokens() ? str.nextToken() : "";
									
									if (attributeName.equals("TYPE")){	
										type = attributeValue;
									}
									if (attributeName.equals("SERVICE_NAME")){
										serName = attributeValue;
									}

									if (attributeName.equals("MONTHLY_PRICE")){
										monthPrice = attributeValue;
									}

									if (attributeName.equals("FREE_MOBILE_MIN")){
										mobMin = attributeValue;
									}
									if (attributeName.equals("FREE_SMS")){
										sms = attributeValue;
									}
									if(attributeName.equals("FREE_DATA")){
										data = attributeValue;
									}
									if (attributeName.equals("TALK_TIME_COST")){
										talkTimeCost = attributeValue;
									}
									if (attributeName.equals("SMS_COST")){
										smsCost = attributeValue;
									}
									if(attributeName.equals("DATA_COST")){
										dataCost = attributeValue;
									}
									if(attributeName.equals("DISCOUNT")){
										discount = attributeValue;
									}
									if(attributeName.equals("BALANCE")){
										balance = attributeValue;
									}

									line = reader.readLine().toUpperCase().trim();
								}// End of While, end of elements of  SERVICE (attributes).
								
								if ((type != null) && (serName != null) && (monthPrice != null)){

									if (type.equals("PHONECONTRACT")) {
										service = new PhoneServices();
										service.setNameOfService(serName);
										service.setFixed(Double.parseDouble(monthPrice));
										((PhoneServices) service).setFreeTalkTime(Integer.parseInt(mobMin));
										((PhoneServices) service).setFreeSMS(Integer.parseInt(sms));
										service.setDiscount(Double.parseDouble(discount));
										((PhoneServices)service).setTalkCost(Double.parseDouble(talkTimeCost));
										((PhoneServices)service).setSMSCost(Double.parseDouble(smsCost));
										services.add(service);
									}
									else if (type.equals("PHONECARDCONTRACT")) {
										service = new CardContractProgram();
										service.setNameOfService(serName);
										service.setFixed(Double.parseDouble(monthPrice));
										((PhoneServices) service).setFreeTalkTime(Integer.parseInt(mobMin));
										((PhoneServices) service).setFreeSMS(Integer.parseInt(sms));
										service.setDiscount(Double.parseDouble(discount));		
										((CardContractProgram)service).setBalance(Double.parseDouble(balance));	
										((PhoneServices)service).setTalkCost(Double.parseDouble(talkTimeCost));
										((PhoneServices)service).setSMSCost(Double.parseDouble(smsCost));								
										services.add(service);
									}
									else if (type.equalsIgnoreCase("MOBILEINTERNET")) {
										service = new InternetServices();
										service.setNameOfService(serName);
										service.setFixed(Double.parseDouble(monthPrice));
										((InternetServices) service).setFreeData(Integer.parseInt(data));
										service.setDiscount(Double.parseDouble(discount));
										((InternetServices) service).setDataCost(Double.parseDouble(dataCost));	
										services.add(service);
									}
								}
							}
						}
						
						line = reader.readLine().toUpperCase().trim();
					}// End of While, end of elements of SERVICE_LIST (services).
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

}


