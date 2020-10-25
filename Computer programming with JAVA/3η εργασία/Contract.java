
public class Contract 
{
	private int id;// id of each contract used by user to choose this contract.
	private static int lastId = 0;// as input to id of a new contract.
	private AllServices service;
	private String phone, name, date, payment, type;
	private double extraDiscount;
	private int consumption = 0, smsConsumption = 0;// consumption = talkTime consumption or data consumption, depends on type of service. 

	// Constructors.
	public Contract (String name, String phone, AllServices service, String date, String payment)
	{
		id = ++lastId;
		this.phone = phone;
		this.service = service;
		this.name = name;
		this.date = date;
		this.payment = payment;
		extraDiscount = (payment.equalsIgnoreCase("Cash")) ? 0.1 : 0;// extraDiscount depends on the way of payment.
	}

	public Contract() { 
		++lastId;
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

	public void setName(String name){
		this.name = name;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public void setDate(String date){
		this.date = date;
	}

	public void setPayment(String payment){
		this.payment = payment;
	}

	public void setService(AllServices service){
		this.service = service;
	}

	public void setId(int id){
		this.id=id;
	}

	public void setExtraDiscount(String payment){
		extraDiscount = (payment.equalsIgnoreCase("Cash")) ? 0.1 : 0;// extraDiscount depends on the way of payment.
	}

	public void setType(String type){
		this.type = type;
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

	public int getId(){
		return id;	
	}
	
	public String getName(){
		return name;	
	}

	public String getDate(){
		return date;	
	}

	public String getPhone(){
		return phone;	
	}
	
	public String getPayment(){
		return payment;	
	}

	public String getBalance(){
	
		if (this.getService() instanceof PhoneServices){
			return "\n\t\tTALK_TIME_CONSUMPTION " + this.getConsumption()*60 + "\n\t\tSMS_CONSUMPTION " + this.getSmsConsumption();
			//  this.getConsumption()*60 convert minutes to seconds if we have a phoneservice.
		}else{
			return "\n\t\tDATA_CONSUMPTION " + this.getConsumption();
		}
	}


	// toString() prints information about a contract.
	public String toString()
	{
		return "\nId: " + id + "\nName: " + name + "\nPhone: " + phone + "\nDate of activate: " + date + "\nPayment: " + payment + "\nService: " + service.getNameOfService() + "\nDiscount: "+ this.getTotalDiscount() + "\n";

	}
}
