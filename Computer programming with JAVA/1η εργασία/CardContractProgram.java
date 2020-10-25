public class CardContractProgram extends PhoneServices
{
	private double balance;	
		
	// Constructor.
	public CardContractProgram (int freeTalkTime, int freeSms, double talkCost, double smsCost, String nameOfService, double fixed, double discount, double balance)
	{
		super(freeTalkTime, freeSms, talkCost, smsCost, nameOfService, fixed, discount);
		this.balance = balance;
	}
	
	// Setters.
	public void setBalance(double b)
	{
		balance = b;
	}

	// Getters.
	public double getBalance()
	{
		return balance;
	}

	// toString() returns information about a service.
	public String toString()
	{	
		return super.toString() + "Balance per month: " + balance + "\n";		
	}

}
