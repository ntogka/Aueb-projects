
/*
	TEAM:

	GALATOULA NATALIA, 3160019, 1st semester, natalag2009@gmail.com
	NTOGKA MARINA, 3160119, 1st semester, marinadoga98@gmail.com
	LAMPRAKIS KONSTANTINOS, 3160079, 1st semester, koslamprakis@gmail.com
*/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.util.Date;
import java.text.*;

public class mainApp extends JFrame
{
	// Initialize GUI components for main Window.
	private JButton loadServButton, loadConButton, saveButton; // insert services, insert contracts, save Contracts respectively.
	private JToolBar bar;
	private JTabbedPane tabs;
	private JPanel serTab, conTab;
	
	// Initialize GUI components for serTab Window.
	private JPanel serP1, serP2, serP3, totalSerP;
	private JLabel serL1, serL2, serL3;
	private JComboBox<String> typeServ;
	private JList<String> servList;
	private DefaultListModel<String> helperSerList;
	private JTextArea infoSerArea;
	private JLabel imageServ;
	private JButton addContractButton;
	
	// Initialize GUI components for conTab Window.
	private JPanel conP1, conP2, conP3, conP4, totalConP;
	private JLabel conL1, conL2, conL3;
	private JComboBox<String> typeServPlus;
	private JList<String> conList;
	private DefaultListModel<String> helperConList;
	private JTextArea infoConArea;
	private JButton moneyBalanceButton, costButton, timeBalanceButton, updateButton;
	
	// Initialize the rest variables.
	private static ServiceCollection serviceList = new ServiceCollection();
	private static ContractCollection contractList = new ContractCollection(serviceList);
	private AllServices ser = null;
	private Contract contr = null;

// Beginning of GUI methods.


	// Constructor.
	public mainApp(){
		
		super("Phone-Internet Service Provider");
		createWindow(); // Creates App's window.

		// Handlers for main Window.		
		ButtonHandler bHandler = new ButtonHandler();
		
		saveButton.addActionListener(bHandler);
		loadConButton.addActionListener(bHandler);
		loadServButton.addActionListener(bHandler);


		// Handlers for Service's Tab.

		// Shows services of specific type of service at JList.
		typeServ.addItemListener(new ItemListener()
		{

			// Clear and add at helperSerList all services of a specific type of service(0, 1, or 2 -> card, phone and internet respectively).
			public void itemStateChanged(ItemEvent event) {
				
				if(event.getStateChange() == ItemEvent.SELECTED){				
					helperSerList.clear();
					int z = typeServ.getSelectedIndex();

					for (int i=0; i < AllServices.getLastId() ;i++){
					
						switch (z){
							case 0: if ((serviceList.getService(i) instanceof PhoneServices) && !(serviceList.getService(i)  instanceof CardContractProgram)) helperSerList.addElement(serviceList.getService(i).getNameOfService()) ; break;
							case 1: if (serviceList.getService(i)  instanceof CardContractProgram) helperSerList.addElement(serviceList.getService(i).getNameOfService())  ; break;
							case 2: if (serviceList.getService(i)  instanceof InternetServices) helperSerList.addElement(serviceList.getService(i).getNameOfService())  ; break;
						}
					}
				}
			} 
		});

		// Shows information about a specific service(of JList) at JTextArea.
		servList.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event) {

				String output = "";
				for (int i=0; i < AllServices.getLastId() ;i++){
				
					if(serviceList.getService(i).getNameOfService().equalsIgnoreCase(servList.getSelectedValue())){
						
						ser = serviceList.getService(i);								
						output += ser;
						break;
					}
				}
				infoSerArea.setText(output);
			} 
		});

		// adds a handler to addContractButton.
		addContractButton.addActionListener(bHandler);


		// Handlers for Contract's Tab.

		// Shows contracts of specific service at JList.
		typeServPlus.addItemListener(new ItemListener()
		{

			// Clear and add at helperSerList all contracts of a specific service.
			public void itemStateChanged(ItemEvent event) {

				if(event.getStateChange()==ItemEvent.SELECTED){				
					helperConList.clear();
					int z = typeServPlus.getSelectedIndex();

					for (int i=0; i < Contract.getLastId() ;i++){
					
						switch (z){
							case 0: if ((contractList.getContract(i).getService() instanceof PhoneServices) && !(contractList.getContract(i).getService()  instanceof CardContractProgram)) helperConList.addElement(contractList.getContract(i).getName()+ " - " +contractList.getContract(i).getService().getNameOfService()); break;
							case 1: if (contractList.getContract(i).getService()  instanceof CardContractProgram) helperConList.addElement(contractList.getContract(i).getName()+ " - " +contractList.getContract(i).getService().getNameOfService()); break;
							case 2: if (contractList.getContract(i).getService()  instanceof InternetServices)
helperConList.addElement(contractList.getContract(i).getName()+ " - " +contractList.getContract(i).getService().getNameOfService()); break;
							case 3: helperConList.addElement(contractList.getContract(i).getName()+ " - " +contractList.getContract(i).getService().getNameOfService()); break;
						}
					}
				}
			} 
		});

		// Shows information about a specific contract(of JList) at JTextArea.
		conList.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event) {

				String output = "";
				for (int i=0; i < Contract.getLastId() ;i++){
				
					if((contractList.getContract(i).getName()+" - "+contractList.getContract(i).getService().getNameOfService()).equalsIgnoreCase(conList.getSelectedValue())){
						contr = contractList.getContract(i);
						output += contr;
						break;
					}
				}
				infoConArea.setText(output);
			}
		});

		// adds a handler at rest buttons.
		moneyBalanceButton.addActionListener(bHandler);
		costButton.addActionListener(bHandler);
		timeBalanceButton.addActionListener(bHandler);
		updateButton.addActionListener(bHandler);
	}
	
	// Creation of a private class to create button handlers.
	private class ButtonHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource() == loadConButton){ 
				contractList.clear();
				contractList.loadFile(chooseFile());

			}else if(e.getSource() == loadServButton){
				serviceList.clear();
				serviceList.loadFile(chooseFile());

			}else if(e.getSource() == saveButton){
				contractList.createFile(chooseDirectory());

			}else if(e.getSource() == addContractButton){
				new InputDialogWin().displayWin();// Creates a new window for input of contract'sinformation.

			}else if(e.getSource() == moneyBalanceButton){

				if (contr != null && (contr.getService() instanceof CardContractProgram)){
					JOptionPane.showMessageDialog(null, "Money Balance of this contracts is: "+((CardContractProgram)contr.getService()).getBalance(), "Money Balance", JOptionPane.INFORMATION_MESSAGE);

				}else{		
					JOptionPane.showMessageDialog(null, "You must select a phone card contract to see the balance of money.", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}else if(e.getSource() == costButton){

				if (contr != null){
					printCostOfContract(contr);
				}else{
					JOptionPane.showMessageDialog(null, "You must select a contract to see the cost.", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}else if(e.getSource() == timeBalanceButton){

				if (contr != null){
					printBalance(contr);
				}else{
					JOptionPane.showMessageDialog(null, "You must select a contract to see the time, sms or data balance.", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}else if(e.getSource() == updateButton){

				if (contr != null){
					guiStatisticsUpdate(contr.getService(), contr);
				}else{
					JOptionPane.showMessageDialog(null, "Updating Failed!\nYou must select a contract to update statistics.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	// private class InputDialogWin creates a window for contract's information input.
	private class InputDialogWin
	{
		private String[] info;
		private JTextField[] questionsField;
		private JRadioButton[] payment;

		// Constructor.
		public InputDialogWin()
		{
			// Initialization.
		    info = new String[3];
		    questionsField = new JTextField[3];
		    questionsField[0] = new JTextField(10);
		    questionsField[1] = new JTextField(10);
		    questionsField[2] = new JTextField(10);
			payment = new JRadioButton[2];
			payment[0] = new JRadioButton("Cash", true);    
			payment[1] = new JRadioButton("Card");  
			ButtonGroup bg = new ButtonGroup();    
			bg.add(payment[0]);
			bg.add(payment[1]);
		}

		// method displayWin() display the window at screen.
		private void displayWin()
		{
		    int selection = JOptionPane.showConfirmDialog(
		            null, getPanel(), "Input information for contract : "
		                            , JOptionPane.OK_CANCEL_OPTION
		                            , JOptionPane.PLAIN_MESSAGE);

		    if (selection == JOptionPane.OK_OPTION) 
		    {
		        for ( int i = 0; i < 3; i++)
		        {
		            info[i] = questionsField[i].getText();             
		        }
				if (ser!=null && !info[0].equals("") && !info[1].equals("")){
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					String d = (!info[2].equals(""))? info[2] : dateFormat.format(date);
					String paym = payment[0].isSelected() ? "Cash": "Card";					
					Contract cont = new Contract(info[0], info[1], ser, d, paym);
					contractList.addContract(cont);
		       
					JOptionPane.showMessageDialog(null
		                ,"New contract is : "+cont
		              	, "Creation Success"
		                , JOptionPane.PLAIN_MESSAGE);
	
					JOptionPane.showMessageDialog(null, "Click the button \"Save Contracts\" at the top of window to save changes.", "Warning", JOptionPane.WARNING_MESSAGE);
				}else{ 
					JOptionPane.showMessageDialog(null, "Creation Failed!\nYou must select a service, fill name and phone before create a new contract.", "Error", JOptionPane.ERROR_MESSAGE);
				}
		    }
		}

		// method getPanel() creates and returns window at displayWin().
		private JPanel getPanel()
		{
		    JPanel p = new JPanel();

		    JPanel centerPanel = new JPanel();
		    centerPanel.setLayout(new GridLayout(4, 2, 5, 5));

		    JLabel mLabel1 = new JLabel("Name : ");
		    JLabel mLabel2 = new JLabel("Phone number : ");
		    JLabel mLabel3 = new JLabel("Activation date : ");
		    JLabel mLabel4 = new JLabel("Way of payment : ");

			JPanel tempPanel = new JPanel();
			tempPanel.add(payment[0]);
			tempPanel.add(payment[1]);

		    centerPanel.add(mLabel1);
		    centerPanel.add(questionsField[0]);
		    centerPanel.add(mLabel2);
		    centerPanel.add(questionsField[1]);
		    centerPanel.add(mLabel3);
		    centerPanel.add(questionsField[2]);
		    centerPanel.add(mLabel4);
		    centerPanel.add(tempPanel);
		    p.add(centerPanel);

		    return p;
		}
	}

	// method chooseFile() creates a JFileChooser for input of contracts and services.
	private File chooseFile(){
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(mainApp.this);
			
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

	// method chooseDirectory() creates a JFileChooser and save contracts.
	private String chooseDirectory(){
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
		int returnVal = chooser.showSaveDialog(mainApp.this);
			
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	
	// createWindow() creates GUI and offers to user all of services easily.
	private void createWindow(){
		
		// Create Buttons.
		loadServButton = new JButton("Insert Services", new ImageIcon("images/insertServ.gif"));
		loadConButton = new JButton("Insert Contracts", new ImageIcon("images/insertCon.png"));
		saveButton = new JButton("Save Contracts", new ImageIcon("images/save.gif"));
		loadServButton.setToolTipText("Insert a .txt file with services.");
		loadConButton.setToolTipText("Insert a .txt file with contracts.");
		saveButton.setToolTipText("Save/Update list of contracts.");
		
		// Create bar.
		bar = new JToolBar();
		bar.add(loadServButton);
		bar.add(loadConButton);
		bar.add(saveButton); 

		// Create tabs.
		serTab = new JPanel();
		serTab.setLayout(new BorderLayout());
		conTab = new JPanel();
		conTab.setLayout(new BorderLayout());
		tabs = new JTabbedPane();
		tabs.addTab("Services", serTab);
		tabs.addTab("Contracts", conTab);
		
		String[] typeServices = {"Phone contract", "Phone card contract", "Mobile internet"};
		
		// Fill Service Tab.
			
		serP1 = new JPanel();
		serP2 = new JPanel();
		serP3 = new JPanel();
		totalSerP = new JPanel();
		totalSerP.setLayout(new GridLayout(1, 3));	
		

		// List of type services.
		serL1 = new JLabel("Choose type of Services: ");
		serP1.add(serL1);
		typeServ = new JComboBox<String>(typeServices);
		serP1.add(typeServ);
		totalSerP.add(serP1);
		
		// List of services.
		serL2 = new JLabel("Services of that type are: ");
		serP2.add(serL2);
		
		helperSerList = new DefaultListModel<String>();
		servList = new JList<String>(helperSerList);
		servList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servList.setSelectedIndex(0);
		JScrollPane listSerScroller = new JScrollPane(servList);
		listSerScroller.setPreferredSize(new Dimension(300, 200));
		serP2.add(listSerScroller);
		totalSerP.add(serP2);

		
		// Area includes service's info.
		serL3 = new JLabel("Information about the service: ");
		serP3.add(serL3);
		
		addContractButton = new JButton("add Contract", new ImageIcon("images/addConIcon.png"));
		serP3.add(addContractButton);
		
		infoSerArea = new JTextArea("", 30, 15);
		infoSerArea.setFont(new Font("Serif", Font.ITALIC, 18));
		infoSerArea.setEditable(false);
		serP3.add(infoSerArea);
		totalSerP.add(serP3);
		serTab.add(totalSerP);
		
		// Fill Contracts Tab.
		
		conP1 = new JPanel();
		conP2 = new JPanel();
		conP3 = new JPanel();
		conP4 = new JPanel();
		conP4.setLayout(new GridLayout(4, 1, 3, 3));
		totalConP = new JPanel();
		totalConP.setLayout(new GridLayout(1, 4, 3, 3));
		
		// List of type services.
		conL1 = new JLabel("Choose type of Services");
		conP1.add(conL1);
		typeServPlus = new JComboBox<String>(typeServices);
		typeServPlus.addItem("All services");
		conP1.add(typeServPlus);
		totalConP.add(conP1);
		
		// List of Contracts. 
		conL2 = new JLabel("Contracts of that Service are: ");
		conP2.add(conL2);
		
		helperConList = new DefaultListModel<String>();
		conList = new JList<String>(helperConList);
		conList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		conList.setSelectedIndex(0);
		JScrollPane listConScroller = new JScrollPane(conList);
		listConScroller.setPreferredSize(new Dimension(300, 200));
		conP2.add(listConScroller);
		totalConP.add(conP2);
		
		// Area includes contract's info.
		conL3 = new JLabel("Information about the contract: ");
		conP3.add(conL3);
		infoConArea = new JTextArea("", 30, 15);
		infoConArea.setFont(new Font("Serif", Font.ITALIC, 18));
		infoConArea.setEditable(false);
		conP3.add(infoConArea);
		totalConP.add(conP3);
		
		// Button Panel.
		moneyBalanceButton = new JButton("Balance of Money");
		conP4.add(moneyBalanceButton);
		
		costButton = new JButton("Cost");
		conP4.add(costButton);
		
		timeBalanceButton = new JButton("Balance of time, sms or MB");
		conP4.add(timeBalanceButton);
		
		updateButton = new JButton("Update Contract");
		conP4.add(updateButton);
		totalConP.add(conP4);
		conTab.add(totalConP);
		
		
		// Define some parametres.
		setLayout(new BorderLayout());
		add("North", bar);
		add("Center", tabs);
		pack();
		setSize(1200,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

// End of GUI methods.


	public static void main(String[] args)
	{
		mainApp g = new mainApp();
	
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

					
//prommt to save changes

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
		    		System.out.println("...File updated!");

					break;
 				case 4:
					// Calculation of cost.
				
					contractList.listDisplay();
					System.out.print("\nGive the id of contract that you want to print the cost please:\n> ");
					numberOfContract = inputControl(1, Contract.getLastId(), in) - 1;
				
					Contract con2 = contractList.getContract(numberOfContract);
					
					printCostOfContract(con2);
		
//promt to save changes

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
			JOptionPane.showMessageDialog(null, "Balance of talk time(as minutes): " + balanceTalkTime + "\nBalance of sms: " + balanceSms, "Talktime-Sms Balance", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			InternetServices s = (InternetServices) service;
			double data = s.getFreeData() - c.getConsumption();
			double balanceData = ((data > 0) ? data : 0);
			JOptionPane.showMessageDialog(null, "Balance of data: " + balanceData, "Data Balance", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Total cost is: " + totalCost + "\nTotal balance is: " + s.getBalance(), "Total Cost", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Total cost is: " + totalCost + "\nCurrent balance is: " + s.getBalance()+ ". This balance isn't enough for cost.", "Total Cost", JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Total cost is: " + totalCost, "Total Cost", JOptionPane.INFORMATION_MESSAGE);			
		}
		else
		{
			InternetServices s = (InternetServices) service;
			double data = c.getConsumption() - s.getFreeData();
			extraCost = ((data > 0) ? data : 0) * s.getDataCost();
			totalCost = extraCost + service.getFixed();
			totalCost -= totalCost * c.getTotalDiscount();
			JOptionPane.showMessageDialog(null, "Total cost is: " + totalCost, "Total Cost", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}


	private static void guiStatisticsUpdate(AllServices service, Contract con)
	{
		if (service instanceof PhoneServices)
		{
			PhoneServices s = (PhoneServices)service;
			
			// Show current statistics. 
			JOptionPane.showMessageDialog(null, "The current statistics are:\nTalktime consumption (as seconds): " + (con.getConsumption()*60) +"\nSms consumption: " + con.getSmsConsumption(), "Update Statistics", JOptionPane.INFORMATION_MESSAGE);
			
			// Import new statistics.
			int talkTimeConsumption = Integer.parseInt(JOptionPane.showInputDialog(null, "\nGive the new talktime consumption (as seconds):"));
			int smsConsumption = Integer.parseInt(JOptionPane.showInputDialog(null, "\nGive the new sms consumption:"));

			con.setConsumption((talkTimeConsumption % 60 == 0)? talkTimeConsumption/ 60 : talkTimeConsumption / 60 + 1);
			// Converts second to minutes.

			con.setSmsConsumption(smsConsumption);
			
			// Show new statistics.
			JOptionPane.showMessageDialog(null, "The new statistics are:\nTalktime consumption (as seconds): " + (con.getConsumption()*60) +"\nSms consumption: " + con.getSmsConsumption(), "Update Statistics", JOptionPane.INFORMATION_MESSAGE);

		}
		else
		{
			InternetServices s = (InternetServices)service;
			
			// Show current statistics.
			int dataConsumption = Integer.parseInt(JOptionPane.showInputDialog(null, "The current statistics are:\nData consumption: " + con.getConsumption()+ "\nGive new data consumption please: "));
			
			// Import and show new statistics.
			con.setConsumption(dataConsumption);
			JOptionPane.showMessageDialog(null, "The new statistics are:\nData consumption: " + con.getConsumption(), "Update Statistics", JOptionPane.INFORMATION_MESSAGE);
		}
		JOptionPane.showMessageDialog(null, "Click the button \"Save Contracts\" at the top of window to save changes.", "Warning", JOptionPane.WARNING_MESSAGE);
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

