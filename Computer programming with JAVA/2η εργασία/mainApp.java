
/*
	TEAM:

	GALATOULA NATALIA, 3160019, 1st semester, natalag2009@gmail.com
	NTOGKA MARINA, 3160119, 1st semester, marinadoga98@gmail.com
	LAMPRAKIS KONSTANTINOS, 3160079, 1st semester, koslamprakis@gmail.com

	Comments: 

	This program take as input talk_time consumption (as seconds) and converts them to minutes. These minutes must be an integer(because companies in real life count 		minutes as integer, not double). For example 140 sec = 3 minutes (not 2.333333).
*/

import java.util.*;

public class mainApp
{
	public static void main(String[] args)
	{
		
		// Read Services. 
		ServiceCollection serviceList = new ServiceCollection();
		
		try {
			serviceList.loadFile("Services.txt");
		} catch (NullPointerException e) {
			System.err.println("\nFile with services not found.");
		}

		// Read Contracts.
		ContractCollection contractList = new ContractCollection(serviceList);
		try {
			contractList.loadFile("Contracts.txt");
		} catch (NullPointerException e) {
			System.err.println("\nFile with contracts not found.");
		}
		

		// Initialization.
		Scanner in = new Scanner(System.in);
		boolean done = false;	
		String name, date, payment, phone;	
		int numberOfService, numberOfContract;
		AllServices service;
		Contract con;

		// Menu.
		while ( ! done)	
		{
			switch (menuAnswer(in))
			{
				case 1:
					// Creation of a new Contract.

					// Choose the service of new contract.
					serviceList.listDisplay();
					System.out.print("\nGive the id of service that you want to choose please:\n> ");
					numberOfService = inputControl(1, AllServices.getLastId(), in) - 1;
					service = serviceList.getService(numberOfService);
					System.out.println("\nYou choose the service:\n" + service);
				
					// Give name.
					System.out.print("\nGive name please:\n> ");
					name = in.nextLine(); 

					// Give phone.
					System.out.print("\nGive phone please:\n> ");
					phone = in.nextLine();

					// Give date of activation.
					System.out.print("\nGive the date of activate please:\n> ");
					date = in.nextLine();

					// Give the way of payment.
					System.out.print("\nChoose the way for payment please:\n1. Cash\n2. Card\n> ");
					payment = (inputControl(1, 2, in) == 1) ? "Cash" : "Card";

					// Create and print the new contract.
					Contract con1 = new Contract(name, phone, service, date, payment);
					contractList.addContract(con1);
					System.out.println("\nCreation is successfull. New contract is:");
					System.out.println(con1); 

					// Updating file with new contracts.	
					contractList.createFile("Contracts.txt");
				    System.out.println("...File updated!");

 					break;
 				case 2:
					// Printing contracts of a service.

					// Choose service.
					System.out.print("\nChoose the type of service that you want please:\n1. Phone contract\n2. Phone card contract\n3. Mobile internet\n> ");
					numberOfService = inputControl(1, 3, in);

					// Print contracts.
					contractList.getContractOfService(numberOfService);
 					break;
 				case 3:
					// Updating Statistics of a contract.

					//Choose contract.
					contractList.listDisplay();
					System.out.print("\nGive the id of contract that you want to update statistics please:\n> ");
					numberOfContract = inputControl(1, Contract.getLastId(), in) - 1;
					
					con = contractList.getContract(numberOfContract);
					service = con.getService();
					statisticsUpdate(in, service, con);

					// Updating file with new statistics.		
					contractList.createFile("Contracts.txt");
		    		System.out.println("...File updated!");

					break;
 				case 4:
					// Calculation of cost.
				
					contractList.listDisplay();
					System.out.print("\nGive the id of contract that you want to print the cost please:\n> ");
					numberOfContract = inputControl(1, Contract.getLastId(), in) - 1;
				
					Contract con2 = contractList.getContract(numberOfContract);
					
					printCostOfContract(con2);

					// Updating file with new balance if have a phone card service.		
					contractList.createFile("Contracts.txt");
		    		System.out.println("...File updated!");

					break;
 				case 5:
					// Calculation of balance.
				
					contractList.listDisplay();
					System.out.print("\nGive the id of contract that you want to print the balance please:\n> ");
					numberOfContract = inputControl(1, Contract.getLastId(), in) - 1;

					Contract con3 = contractList.getContract(numberOfContract);
					printBalance(con3);
 					break;
 				case 0: 
					// Exit. 
				
					done = true; 
					break;
 	 		}
		}		
	}

	// inputConsumption() controls and return consumption to be positive or zero.
	private static int inputConsumption(Scanner in)
	{
		while (true)
		{
			int number = in.nextInt();
			in.nextLine(); 
			if (number >= 0) return number;
			System.out.print("\nYou gave a invalid consumption. Give a positive or a zero consumption please:\n> ");
		}
	}
	
	// inputControl() controls and return the answer to be between s and e.
	private static int inputControl(int s, int e, Scanner in)
	{
		// s stands for Start. 
		// e stands for End.
		while (true)
		{
			int numberOfChoice = in.nextInt();
			in.nextLine(); // because the .nextInt method does not consume the last newline character of input.
			if ((numberOfChoice >= s)&&(numberOfChoice <= e)) return numberOfChoice;
			System.out.print("\nYou gave a invalid number. Give a number between " + s + " and " + e + " please:\n> ");
		}
	}
	
	// printBalance() calculates and prints balance of talk time, SMS and data.
	private static void printBalance(Contract c)
	{
		AllServices service = c.getService();
		if (service instanceof PhoneServices)
		{
			PhoneServices s = (PhoneServices) service;
			
			int talk = s.getFreeTalkTime() - c.getConsumption();
			int sms = s.getFreeSms() - c.getSmsConsumption();
			int balanceTalkTime = ((talk > 0) ? talk : 0);
			int balanceSms = ((sms > 0) ? sms : 0);
			
			System.out.println("Balance of talk time: " + balanceTalkTime + "\nBalance of sms: " + balanceSms);
		}
		else
		{
			InternetServices s = (InternetServices) service;
			double data = s.getFreeData() - c.getConsumption();
			double balanceData = ((data > 0) ? data : 0);
			
			System.out.println("Balance of data: " + balanceData);
		}
	}
	
	// printCostOfContract() calculates and prints cost of a contract that depend on statistics.
	private static void printCostOfContract(Contract c)
	{
		AllServices service = c.getService();
		double totalCost, extraCost;
		if (service instanceof CardContractProgram)
		{
			CardContractProgram s = (CardContractProgram) service;
			int talk = c.getConsumption() - s.getFreeTalkTime();
			int sms = c.getSmsConsumption() - s.getFreeSms();
			extraCost = ((talk > 0) ? talk : 0) * s.getTalkCost() + ((sms > 0) ? sms : 0) * s.getSmsCost();
			totalCost = extraCost + service.getFixed();
			totalCost -= totalCost * c.getTotalDiscount();
			double temp = s.getBalance() - totalCost;
			if (temp >= 0)
			{
				s.setBalance(temp);
				System.out.println("\nTotal balance is: " + s.getBalance());	
			}
			else
			{
				System.out.println("\nCurrent balance is: " + s.getBalance() + ". This balance isn't enough for cost.");
			}
		}
		else if (service instanceof PhoneServices)
		{
			PhoneServices s = (PhoneServices) service;
			int talk = c.getConsumption() - s.getFreeTalkTime();
			int sms = c.getSmsConsumption() - s.getFreeSms();
			extraCost = ((talk > 0) ? talk : 0) * s.getTalkCost() + ((sms > 0) ? sms : 0) * s.getSmsCost();
			totalCost = extraCost + service.getFixed();
			totalCost -= totalCost * c.getTotalDiscount();				
		}
		else
		{
			InternetServices s = (InternetServices) service;
			double data = c.getConsumption() - s.getFreeData();
			extraCost = ((data > 0) ? data : 0) * s.getDataCost();
			totalCost = extraCost + service.getFixed();
			totalCost -= totalCost * c.getTotalDiscount();
		}
		
		System.out.println("\nTotal cost is: " + totalCost);
	}

	// statisticsUpdate() reads new statistics and updates previous. Also updates file Contracts.
	private static void statisticsUpdate(Scanner in, AllServices service, Contract con)
	{
		
		if (service instanceof PhoneServices)
		{
			PhoneServices s = (PhoneServices)service;
			
			// Show current statistics. 
			System.out.println("\nThe current statistics are:\nTalktime consumption (as seconds): " + con.getConsumption()*60);
 			System.out.println("Sms consumption: " + con.getSmsConsumption());
			
			// Import new statistics.
			System.out.print("\nGive the new talktime consumption (as seconds):\n> ");
			int talkTimeConsumption = inputConsumption(in);

			System.out.print("\nGive the new sms consumption:\n> ");
			int smsConsumption = inputConsumption(in);

			con.setConsumption((talkTimeConsumption % 60 == 0)? talkTimeConsumption/ 60 : talkTimeConsumption / 60 + 1);
			// Converts second to minutes.

			con.setSmsConsumption(smsConsumption);
			
			// Show new statistics.
			System.out.println("\nThe new statistics are:\nTalktime consumption: " + con.getConsumption()*60);
			System.out.println("Sms consumption: " + con.getSmsConsumption());

		}
		else
		{
			InternetServices s = (InternetServices)service;
			
			// Show current statistics.
			System.out.print("\nThe current statistics are:\nData consumption: " + con.getConsumption() + "\nGive new data consumption please:\n> ");
			int dataConsumption = inputConsumption(in);
			
			// Import and show new statistics.
			con.setConsumption(dataConsumption);
			System.out.println("\nThe new statistics are:\nData consumption: " + con.getConsumption());
			}
	}

	// menuAnswer() show menu and return the answer.
	private static int menuAnswer(Scanner in)
	{
		System.out.println("\nChoose a choice from below please:");
		System.out.println("\n1. Add Contract");
		System.out.println("2. List of Contracts for a Service");
   		System.out.println("3. Update Monthly Statistics");
 		System.out.println("4. Monthly Cost of a Contract");
 		System.out.println("5. Balance");
 		System.out.print("0. exit\n> ");
 		
		return inputControl(0, 5, in);
	}

}

