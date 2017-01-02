//Jose Mora
//CIS 256
//05-12-2016

public class Customer {
 private String name, address;
 private int number;
 
 public Customer(String name, String address, int number) { //default constructor 
	 this.name = name;
	 this.address = address;
	 this.number = number;
 }
 public void setName(String name) { //set name of the customer
	 this.name = name;
 }
 
 public String getName() { //retrieves name of customer 
	 return name;
 }
 
 public void setAddress(String address) { //set address of customer 
	 this.address = address;
 }
 
 public String getAddress(){ //get address of customer 
	 return address;
 }
 
 public void setNumber(int number){ //set phone number of customer 
	 this.number = number;
 }
 
 public int getNumber() { //get phone number of customer 
	 return number;
 }
public String toString(){ //to string 
	String s = "Name: " + name + "\nAddress: " + address + "\nNumber: " + number;
	return s;
	}
}

