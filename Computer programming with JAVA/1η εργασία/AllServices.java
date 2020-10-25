
// AllServices includes phone services and internet services.

public class AllServices 
{

	private String nameOfService;
	private double fixed, discount;
	private int id; // id of each service used by user to choose this service.
	private static int lastId = 0; // as input to id of a new service.

	// Constructor.
	public AllServices (String nameOfService, double fixed, double discount)
	{
		id = ++lastId;
		this.nameOfService = nameOfService;
		this.fixed = fixed;
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
	
	// toString() returns information about a service.
	public String toString()
	{	
		return "\nId: " + id + "\nService: " + nameOfService + "\nFixed: " + fixed;		
	}
	
}
