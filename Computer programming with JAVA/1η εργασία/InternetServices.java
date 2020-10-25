public class InternetServices extends AllServices
{
	private double dataCost;
	private int freeData;
	// Constructor.
	public InternetServices (int freeData, double dataCost, String nameOfService, double fixed, double discount)
	{
		super(nameOfService, fixed, discount);
		this.freeData = freeData;
		this.dataCost = dataCost;
	}	 


	// Getters.
	public int getFreeData()
	{
		return freeData;
	}

	public double getDataCost()
	{
		return dataCost;
	}

	
	// toString() returns information about a service.
	public String toString()
	{	
		return super.toString() + "\nFree data: " + freeData + "\n";		
	}
	
}
