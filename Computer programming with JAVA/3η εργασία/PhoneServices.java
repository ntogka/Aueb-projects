
// PhoneServices includes card contract programs and simple contract programs. 

public class PhoneServices extends AllServices
{
	protected int freeTalkTime, freeSms;
	protected double talkCost, smsCost;
	
	// Constructors.
	public PhoneServices (int freeTalkTime, int freeSms, double talkCost, double smsCost, String nameOfService, double fixed, double discount)
	{
		super(nameOfService, fixed, discount);
		this.freeTalkTime = freeTalkTime;
		this.freeSms = freeSms;
		this.talkCost = talkCost;
		this.smsCost = smsCost;
	} 

	public PhoneServices (){
		super();
	}

	// Setters.
	public void setFreeTalkTime (int freeTalkTime){
		this.freeTalkTime = freeTalkTime;
	}

	public void setFreeSMS (int freeSms){
		this.freeSms = freeSms;
	}

	public void setTalkCost (double talkCost){
		this.talkCost = talkCost;
	}

	public void setSMSCost (double smsCost){
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
