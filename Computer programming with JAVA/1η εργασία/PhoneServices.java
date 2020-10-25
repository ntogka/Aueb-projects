// PhoneServices includes card contract programs and simple contract programs. 

public class PhoneServices extends AllServices
{
	private int freeTalkTime, freeSms;
	private double talkCost, smsCost;
	
	// Constructor.
	public PhoneServices (int freeTalkTime, int freeSms, double talkCost, double smsCost, String nameOfService, double fixed, double discount)
	{
		super(nameOfService, fixed, discount);
		this.freeTalkTime = freeTalkTime;
		this.freeSms = freeSms;
		this.talkCost = talkCost;
		this.smsCost = smsCost;
	} 
	

	// Getters.
	public int getFreeTalkTime()
	{
		return freeTalkTime;	
	}
	
	public int getFreeSms()
	{
		return freeSms;	
	}

	public double getTalkCost()
	{
		return talkCost;	
	}

	public double getSmsCost()
	{
		return smsCost;	
	}



	// toString() returns information about a service.
	public String toString()
	{	
		return super.toString() + "\nFree talk time: " + freeTalkTime + "\nFree sms: " + freeSms + "\n";		
	}

}
