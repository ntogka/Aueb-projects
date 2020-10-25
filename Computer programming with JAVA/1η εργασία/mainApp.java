/*
	TEAM:

	GALATOULA NATALIA, 3160019, 1st semester, natalag2009@gmail.com
	NTOGKA MARINA, 3160119, 1st semester, marinadoga98@gmail.com
	LAMPRAKIS KONSTANTINOS, 3160079, 1st semester, koslamprakis@gmail.com
*/

import java.util.*;

public class mainApp
{
	public static void main(String[] args)
	{
		
		// Instantiation of services. 
		ServiceCollection serviceList = new ServiceCollection();
		instantiationOfServices(serviceList);
		
		// Instantiation of contracts. 
		ContractCollection contractList = new ContractCollection();
		instantiationOfContracts(contractList, serviceList);
		
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
					break;
 				case 4:
					// Calculation of cost.
				
					contractList.listDisplay();
					System.out.print("\nGive the id of contract that you want to print the cost please:\n> ");
					numberOfContract = inputControl(1, Contract.getLastId(), in) - 1;
				
					Contract con2 = contractList.getContract(numberOfContract);
					
					printCostOfContract(con2);
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
	
	// printBalance() calculates and prints balance of talktime, sms and data.
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

	// statisticsUpdate() reads new statistics and updates previous.
	private static void statisticsUpdate(Scanner in, AllServices service, Contract con)
	{
		
		if (service instanceof PhoneServices)
		{
			PhoneServices s = (PhoneServices)service;
			
			// Show current statistics. 
			System.out.println("\nThe current statistics are:\nTalktime consumption: " + con.getConsumption());
 			System.out.println("Sms consumption: " + con.getSmsConsumption());
			
			// Import new statistics.
			System.out.print("\nGive the new talktime consumption:\n> ");
			int talkTimeConsumption = inputConsumption(in);

			System.out.print("\nGive the new sms consumption:\n> ");
			int smsConsumption = inputConsumption(in);

			con.setConsumption(talkTimeConsumption);
			con.setSmsConsumption(smsConsumption);
			
			// Show new statistics.
			System.out.println("\nThe new statistics are:\nTalktime consumption: " + con.getConsumption());
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
		
	// instantiationOfServices() makes instances of services's collection.
	private static void instantiationOfServices(ServiceCollection serviceList)
	{
	
		AllServices s1 = new PhoneServices(300, 50, 0.25, 0.05, "NonStop Call", 13, 0.3);
		serviceList.addService(s1);
	
		AllServices s2 = new PhoneServices(500, 70, 0.20, 0.04, "NonStop Call Plus", 18, 0.4);
		serviceList.addService(s2);
		
		AllServices s3 = new CardContractProgram(200, 50, 0.33, 0.08, "CallMore", 5, 0.1, 20);
		serviceList.addService(s3);

		AllServices s4 = new CardContractProgram(400, 100, 0.33, 0.08, "CallMore Extra", 8, 0.4, 30);
		serviceList.addService(s4);
	
		AllServices s5 = new InternetServices(2000, 0.09, "Giga Data", 5, 0.1);
		serviceList.addService(s5);

		AllServices s6 = new InternetServices(5000, 0.05, "Unlimmited Data", 10, 0.25);
		serviceList.addService(s6);
	}

	// instantiationOfContracts() makes instances of contracts's collection.
	private static void instantiationOfContracts(ContractCollection contractList, ServiceCollection serviceList)
	{

		Contract c1 = new Contract("Mike Miller", "2101586350", serviceList.getService(0), "01/18/2017", "Cash");
		contractList.addContract(c1);

		Contract c2 = new Contract("John Hoster", "2101537956", serviceList.getService(0), "01/06/2017", "Cash");
		contractList.addContract(c2);;

		Contract c3 = new Contract("Emily Tomson", "2101176093", serviceList.getService(1), "02/01/2017", "Card");
		contractList.addContract(c3);

		Contract c4 = new Contract("Mary Jonson", "2136996441", serviceList.getService(1), "03/22/2017", "Cash");
		contractList.addContract(c4);

		Contract c5 = new Contract("Sara Fors", "2133586390", serviceList.getService(2), "06/12/2016", "Card");
		contractList.addContract(c5);

		Contract c6 = new Contract("Jason Pris", "2101736352", serviceList.getService(2), "09/08/2016", "Cash");
		contractList.addContract(c6);

		Contract c7 = new Contract("George Allen", "2115556726", serviceList.getService(3), "10/04/2016", "Card");
		contractList.addContract(c7);

		Contract c8 = new Contract("Jack Lipton", "2107528004", serviceList.getService(3), "12/28/2016", "Card");
		contractList.addContract(c8);

		Contract c9 = new Contract("Peter Smith", "2115122286", serviceList.getService(4), "05/01/2017", "Cash");
		contractList.addContract(c9);

		Contract c10 = new Contract("Anna Huston", "2138588385", serviceList.getService(4), "07/19/2016", "Cash");
		contractList.addContract(c10);

		Contract c11 = new Contract("Gina Roster", "2100397948", serviceList.getService(5), "02/11/2017", "Card");
		contractList.addContract(c11);

		Contract c12 = new Contract("Monika Selbst", "2112523649", serviceList.getService(5), "08/08/2016", "Cash");
		contractList.addContract(c12);
	}
}
