import java.util.*;

public class ContractCollection
{
	
	private ArrayList <Contract> contracts = new ArrayList <Contract>();
	
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
}
