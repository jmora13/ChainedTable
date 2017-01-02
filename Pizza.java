//Jose Mora
//CIS 256
//05-12-2016

import java.util.*;
public class Pizza {
	public static void main(String[] args){
	
	Scanner keyboard = new Scanner(System.in);
	int counter = 0;
	System.out.println("How many customers would you like to enter? ");
	int numCustomers = keyboard.nextInt();
	ChainedTable<Integer,Object> table = new ChainedTable<Integer,Object>(numCustomers);
	do {	
	System.out.println("Enter Name of customer " + (counter+1) + ": ");
	String name = keyboard.next();
	
	System.out.println("Enter Address of customer " + (counter+1) + ": ");
	String address = keyboard.next();
	
	System.out.println("Enter Phone Number of customer " + (counter+1) + ": ");
	int number = keyboard.nextInt();
	
	Customer customer = new Customer(name,address,number);
	table.put(number, customer);
	
	counter++;
	} while(counter < numCustomers);
	
	int choice;
	do {
		System.out.println("1. Get Customer\n2. Remove Customer\n3. Quit");
		choice = keyboard.nextInt();
		
		if (choice == 1) {
			System.out.println("Enter Phone Number: ");
			String phoneNumber = keyboard.next();
			System.out.println("------Customer Info------");
			if(table.containsKey(Integer.valueOf(phoneNumber)) == true) {
			System.out.println(table.get(Integer.valueOf(phoneNumber)));
			} else {
				System.out.println("Customer not found");
			}
		}
		if (choice == 2) {
			System.out.println("Enter Phone Number: ");
			String phoneNumber = keyboard.next();
			if(table.containsKey(Integer.valueOf(phoneNumber)) == true) {
				System.out.println("------Customer " + Integer.valueOf(phoneNumber) + " Removed------");
				table.remove(Integer.valueOf(phoneNumber));
		  } else {
			  System.out.println("Customer not found");
		  }
	   }
	} while (choice != 3);
  }
}

