
// AllServices includes phone services and internet services.

public class AllServices 
{

	protected String nameOfService;
	private double fixed, discount;
	private int id; // id of each service used by user to choose this service.
	private static int lastId = 0; // as input to id of a new service.

	// Constructors.
	public AllServices (String nameOfService, double fixed, double discount)
	{
		id = ++lastId;
		this.nameOfService = nameOfService;
		this.fixed = fixed;
		this.discount = discount;
	}

	public AllServices ()
	{
		id = ++lastId;
	}
	
	//Setters.

	public void setNameOfService (String nameOfService){
		this.nameOfService = nameOfService;
	}

	public void setFixed (double fixed){
		this.fixed = fixed;
	}

	public void setDiscount (double discount){
		this.discount = discount;
	}

	// Getters.
	public double getDiscount()
	{
		return discount; 
	}
	
	public double getFixed()
	{
		return fixed;
	}
	
	public String getNameOfService()
	{
		return nameOfService;
	}

	public static int getLastId()
	{
		return lastId;
	}	

	// getType return as String the type of a Service.
	public String getType()
	{
		if (this instanceof CardContractProgram){
			return "phoneCardContract";
		}else if (this instanceof PhoneServices){
			return "phoneContract";
		}else{
			return "mobileInternet";
		}	
	}
	
	// toString() returns information about a service.
	public String toString()
	{	
		return "\nId: " + id + "\nService: " + nameOfService + "\nFixed: " + fixed;		
	}
	
}
