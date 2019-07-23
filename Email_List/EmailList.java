import java.util.LinkedList;

/**
 * Class: EmailList
 * Course Name: CST8130
 * Author: Mohammedaaman Shaikh
 * Lab Section: 300
 * Date: 25 March 2019
 * Purpose: adding, deleting, displaying and compairing the list
 * Data Members: name: String - email (name)
 * 				 addresses: LinkedList - list of email addressess
 * Methods: EmailList(String) - constructor to initialize the data members
 * addEntry(EmailAddress): void - add email to the end of list
 * deleteEntry(int): void - delete email from the specified index
 * display(): void - display the content of the list
 * displayDirectory(): void - display a particular directory
 * isGreater(EmailList): boolean - checking for name if > 0
 * isEqual(String): boolean - checking for name if equal
 * isSame(EmailList): boolean - checking for name if same 
 */
public class EmailList {

	private String name = null;
	private LinkedList<EmailAddress> addresses = null;

	public EmailList(String name) {
		this.name = name;
		this.addresses = new LinkedList<EmailAddress>();
	}

	//Adds an entry to the list
	public void addEntry(EmailAddress emailAddress) 
	{
		addresses.add(emailAddress);
	}

	//Removes the entry at the specified position in the list
	public void deleteEntry(int index) {
		addresses.remove(index);
	}

	//Displays all entries in the list
	public void display() {
		System.out.print(name + ":[");

		for (int i = 0; i < addresses.size(); i++) {
			if (i > 0)
				System.out.println(",");
			System.out.print(addresses.get(i));
		}

		System.out.println("]");
	}

	//Shows all entries in the directory for selection	 
	public void displayDirectory() 
	{
		System.out.println(name);
		for (int i = 0; i < addresses.size(); i++) 
		{
			System.out.println(i + " " + addresses.get(i));
		}
	}
	
	//Compare list to check if it is greater than the specified list
	public boolean isGreater(EmailList eList)
	{
		if(this.name.compareTo(name) < 0)
			return true;
		else
			return false;
	}
	//Compare to check if lists are same
	public boolean isSame(EmailList eList)
	{
		if(this.name.equals(name))
			return true;
		else
			return false;
	}
	
	//Compares the EmailList with name.
	public boolean isEqual(String name) {
		if (this.name.equals(name)) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	//Returns the number of entries in the list
	public int size() 
	{
		return addresses.size();
	}
}