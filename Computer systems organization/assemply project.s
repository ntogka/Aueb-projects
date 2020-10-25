#Description: This program simulates a parking ticket machine. The user gives 2 values: the euros and cents he paid. The machine then will either return money
#	      (as change in bankontes and/or coins) or not depending on the user-inputted values along with the corresponding message.

###############################################################################################################################################################

#####################################################              REGISTERS              #####################################################################

#$t0 = 1) stores the euros given by the user. In a later stage it stores the sum of the converted euros and cents
       2) stores the sum of the converted euros and cents	
#$t1 = stores the cents given by the user
#$t2 = stores either 0 or 1, depending on whether the value is less than or equal to 20
#$t3 = stores either 0 or 1, depending on whether the value is less than 100
#$t4 = stores the value of the fee (524)
#$t5 = stores the multiplier/divisor for the convertor (100)
#$t6 = 1) stores the values of the available euros/cents (here: 10,5,2,1 euro(s), 50,20,10,5,2,1 cent(s))
       2) stores the result of the multiplication: (quantity of euros/cents for the change)*(euros'/cents' value)
#$t7 = stores the result of the division : value of euros/100
#$s0 = stores either 0 or 1, depending on this math: ($t2 && 1)
#$s1 = stores either 0 or 1, depending on this math: ($t3 && 1)
#$s2 = stores either 0 or 1, depending on this math: ($s0 && $s1)
       #&& : bitwise AND
#$s3 = 1) stores the result of the subtraction : payment-fee ($t0-$t4)
       2) stores the result of the subtraction (change)-(the multiplication result stored in the register $t6) or ($s3-$t6)
#$s4 = stores either 0 or 1, depending on whether the change is greater than 0
#$s5 = stores the result of the division : changes/available euros/cents
#$a0 = argument for a sub-routine (for example move the value stored in a register to this register, so it can be used for something, e.g. print an integer)
#$v0 = 1) stores a value from an expression evaluation (for example an integer or a string)
       2) loads appropriate system call code into the register (for example, print an integer, reading an integer, exit)

#####################################################                CODE                ######################################################################
	
	.text
	.globl main
main:	
	#Pseudocode:
	#
	#writeln("--Parking Ticket Machine--");
	#writeln();
	#writeln("Euros (<=20): ")
	#readln(euros);
	#writeln("Cents (<100): ")
	#readln(cents);

	la $a0,header_and_fee  		#Show the string
	li $v0,4	       		#Show the title and the fee of the parking ticket
	syscall
	
	la $a0,euros	       		#Show the string
	li $v0,4	       		#"Euros (<=20): " on the console
	syscall
	
	li $v0,5	       		#Read an int value (how many euros did you pay)	
	syscall
	move $t0,$v0			#Store the value of euros at the temporary register $t0, for later use
	la $a0,cents	       		#Show the string	
	li $v0,4	       		#"Cents (<100): " on the console
	syscall
	
	li $v0,5	       		#Read an int value (how many cents did you pay)
	syscall
	move $t1,$v0			#Store the value of cents at the temporary register $t1, for later use
	

	#Pseudocode:
	#
	#if ((euros<=20) AND (cents<100)) goto type_of_change
	#else    
	#    writeln("Error! Please try again.");
	#    end.	
	
	slti $t2,$t0,21			#$t2=1 if $t0<=20
	slti $t3,$t1,100       		#$t3=1 if $t1<100
	andi $s0,$t2,1			#$s0=1 if $t2=1
	andi $s1,$t3,1			#$s1=1 if $t3=1
	and $s2,$s0,$s1			#$s2=1 if $s0=$s1
	bne $s2,$zero,type_of_change	#if $s2=1 goto type_of_change
	la $a0,error_msg		#Show the string
	li $v0,4			#"Error! Please try again." on the console
	syscall
	j exit				#goto Exit


	#Pseudocode:
	#
	#euros = euros*100;
	#money_given = euros+cents;
	#change = money_given-cents;
	#if (change>0) goto changes
	#else if(change=0) goto no_change
	#else
	#   writeln("Error! Not enough money!");
	#   end.

type_of_change:
	lw $t4,fee			#load the value of the fee on the temporary register $t5
	lw $t5,convert_to_cents		#load the value of the multiplier to convert euros to cents on the temporary register $t6
	mul $t0,$t0,$t5			#multiply euros with 100 to convert euros to cents
	add $t0,$t0,$t1			#add the cents to the recently converted euros
	sub $s3,$t0,$t4			#change (payment-fee)
	slt $s4,$zero,$s3		#$s4=1 if (changes>0)
	bne $s4,$zero,changes		#if ($s4=1) goto changes
	beq $s3,$zero,no_change		#if (changes=0) goto no_change
	la $a0,not_enough_money_msg	#show the string
	li $v0,4			#"Error! Not enough money!" on the console
	syscall
	j exit				#goto Exit

	#Pseudocode:
	#
	#writeln("Changes: ")
	#writeln();
	#value = 1000
	#changes = changes div 1000
	#if (changes != 0) goto print
	#...
	#...
	#end.
	
changes:
	la $a0,print_changes		#show the string
	li $v0,4			#"Changes : " on the console
	syscall

calculation:
	addi $t6,$zero,1000		#$t6 = 1000
	div $s5,$s3,$t6			#$s5 = changes/1000
	bne $s5,$zero,print		#if ($s5=1) goto print

	addi $t6,$zero,500		#$t6 = 500
	div $s5,$s3,$t6			#$s5 = changes/500
	bne $s5,$zero,print

	addi $t6,$zero,200		#$t6 = 200
	div $s5,$s3,$t6			#$s5 = changes/200
	bne $s5,$zero,print

	addi $t6,$zero,100		#$t6 = 100
	div $s5,$s3,$t6			#$s5 = changes/100
	bne $s5,$zero,print
	
	addi $t6,$zero,50		#$t6 = 50
	div $s5,$s3,$t6			#$s5 = changes/50
	bne $s5,$zero,print

	addi $t6,$zero,20		#$t6 = 20
	div $s5,$s3,$t6			#$s5 = changes/20
	bne $s5,$zero,print
	
	addi $t6,$zero,10		#$t6 = 10
	div $s5,$s3,$t6			#$s5 = changes/10
	bne $s5,$zero,print

	addi $t6,$zero,5		#$t6 = 5
	div $s5,$s3,$t6			#$s5 = changes/5
	bne $s5,$zero,print

	addi $t6,$zero,2		#$t6 = 2
	div $s5,$s3,$t6			#$s5 = changes/2
	bne $s5,$zero,print

	addi $t6,$zero,1		#$t6 = 1
	div $s5,$s3,$t6			#$s5 = changes/1
	bne $s5,$zero,print
	j exit				#goto exit
	

	#Pseudocode:
	#
	#write(changes);
	#write(" x ");
	#value = value div 100
	#if (value != 0){
	#    write(value);
	#    write(" euros");
	#    writeln();
	#} else{  
	#    write(value); 
	#    write(" cents");
	#    writeln();
	#}
	#value = value*quantity;
	#changes = changes-value;
	#jump to calculation

print:
	move $a0,$s5			#store the value of $s5 to $a0, show the value
	li $v0,1			#of the quotient on the console
	syscall				#(the quotient represents the quantity of the euros/cents given in the changes)
	la $a0,result			#show the string
	li $v0,4			#" x " on the console
	syscall
	div $t7,$t6,$t5			#$t7 = value of euros(here)/100 (this division is done to distinguish the euros from the cents)
	bne $t7,$zero,euros_print	#if ($t7=1) goto euros_print
	move $a0,$t6			#store the value of $t3 to $a0, show the value
	li $v0,1			#of cents on the console
	syscall
	la $a0,c			#show the string
	li $v0,4			#" cents" on the console
	syscall

sub_change:
	la $a0,newLine			#print a new line (go to the next line)
	li $v0,4			
	syscall
	mul $t6,$t6,$s5			#multiply the quantity of the euros/cents given in the change with that value
	sub $s3,$s3,$t6			#subtract the previous value from the changes
	j calculation			#goto calculation

euros_print:
	move $a0,$t7			#store the value of $t7 to the $a0, show the value
	li $v0,1			#of euros on the console
	syscall
	la $a0,e			#show the string
	li $v0,4			#" euros" on the console
	syscall
	j sub_change			#goto sub_change

	#Pseudocode:
	#
	#writeln("Changes = 0);
	#end.

no_change:
	la $a0,zero_change		#show the string
	li $v0,4			#"Changes = 0" on the console
	syscall

exit:	
	li $v0,10	       		#return to the operating system
	syscall

	.data
header_and_fee: .asciiz "-- Parking Ticket Machine --\n\nFee: 5 euros and 24 cents\n\n"
euros: .asciiz "Euros (<= 20): "
cents: .asciiz "Cents (< 100): "
error_msg: .asciiz "\nError! Please try again."
not_enough_money_msg: .asciiz "\nError! Not enough money!"
zero_change: .asciiz "\nChange = 0"
print_changes: .asciiz "\nChange : \n\n"
fee: .word 524
convert_to_cents: .word 100
result: .asciiz " x "
e: .asciiz " euros"
c: .asciiz " cents"
newLine: .asciiz "\n"