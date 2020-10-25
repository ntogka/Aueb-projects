public class Contract 
{
	private int id;// id of each contract used by user to choose this contract.
	private static int lastId = 0;// as input to id of a new contract.
	private AllServices service;
	private String phone, name, date, payment;
	private double extraDiscount;
	private int consumption = 0, smsConsumption = 0;// consumption = talkTime consumption or data consumption, depends on type of service. 

	// Constructor.
	public Contract (String name, String phone, AllServices service, String date, String payment)
	{
		id = ++lastId;
		this.phone = phone;
		this.service = service;
		this.name = name;
		this.date = date;
		this.payment = payment;
		extraDiscount = (payment == "Cash") ? 0.1 : 0;// extraDiscount depends on the way of payment.
	}

	// Setters.
	
	public void setSmsConsumption(int s)
	{
		smsConsumption = s;
	}
	
	public void setConsumption(int c)
	{
		consumption = c;
	}
	
	// Getters.
	
		public int getSmsConsumption()
	{
		return smsConsumption;	
	}
	
	public int getConsumption()
	{
		return consumption;
	}
	
	public double getTotalDiscount()
	{
		double totalDiscount = service.getDiscount() + extraDiscount;
		return totalDiscount;
	}
	
	public static int getLastId()
	{
		return lastId;
	}
	
	public AllServices getService()
	{
		return service;	
	}

	// toString() prints information about a contract.
	public String toString()
	{
		return "\nId: " + id + "\nName: " + name + "\nPhone: " + phone + "\nDate of activate: " + date + "\nPayment: " + payment + "\nService: " + service.getNameOfService() + "\nDiscount: "+ this.getTotalDiscount() + "\n";

	}
}
