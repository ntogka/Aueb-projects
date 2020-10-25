import acm.program.*;
public class Ticket extends Program{
	private static double price= 1.4;
	public void run(){
		double input=0;
		double enter=0;
		while(	enter != 0){
		 enter=readDouble ("Give the amount of money you want  : ");
		
		if(enter ==0.1 ||enter ==0.2 ||enter ==0.5 ||enter ==1 ||enter ==2 ||enter ==5){
			input=input+enter;
		}
		else println("WRONG AMOUNT OF MONEY !! TYPE AGAIN");
		}
			if(input>=price){
				println("Here is your ticket !!");
			 
			 
			 double e2=0;
			 double e1=0;
			 double c50=0;
			 double c20=0;
			 double c10=0;
            if (input>price){
			 while((input-price)>=2.0){
			 e2++;
			 input=input-2.0;
			 }
			 while((input-price)>=1.0){
			 e1++;
			 input=input-1;
			 }
			 while((input-price)>=0.5){
			 c50++;
		     input=input-0.5;
			 }
			 while((input-price)>=0.2){
			 c20++;
			 input=input-0.2; 
			 }
			 while((input-price)>=0.1){
			 c10++;
			 input=input-0.1;
			 }
			
			}
             if(e2!=0){
			 println("You have change "+e2+" 2 euros");	
			 }
			 if(e1!=0){
				 println("You have change "+e1+" 1 euro");
			 }
			 if(c50!=0){
				 println("You have change "+c50+" 50 cents");
				 }
			if (c20!=0){
				println("You have change "+c20+" 20 cents");
			}
            if (c10!=0){
				println("You have change "+c10+" 10 cents");
			}			
			}
			else println("not enough money, your change is "+input);
	}
		}
	
