import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Author: Mohammedaaman Shaikh
 * Class: Directory
 * Course Name: CST8130
 * Lab Section: 300
 * Date: 25 March 2019
 * Purpose: Maintain a arraylist of linkedlist of email for adding, deleting, displaying and creating directory
 * Data Members: Scanner: to take input
 * ArrayList: Dynamic array of EmailList
 * Methods: Directory(): constructor to initialize list
 * addEmailList(EmailList): void - add email to the EmailList
 * display(): display the array of directory
 * createList(String): create a new directory
 * enterEmailaddress(EmailList):  adds a new email address to the particular directory
 * deleteEntryFromList(String): delete mail from the specified directory
 * displaySingleList(String): display the specified directory
 * addEntryToList(String): add entry to the specified directory
 * sortList(EmailList): int - sorts the directory (Code taken from Assignment 2 solution @author Linda Crane)
 * findEmailList(String): EmailList - Find the email list
 */
public class Directory 
{
	private Scanner sc = new Scanner(System.in);
	private ArrayList<EmailList> list = null;

	//default constructor
	public Directory() 
	{
		this.list = new ArrayList<EmailList>();
	}

	//Adds emailList to directory
	public void addEmailList(EmailList emailList) 
	{
		list.add(emailList);
	}

	//Display all email lists
	public void display() 
	{	
		System.out.println("The email lists are: ");
		for (int i = 0; i < list.size(); i++) 
		{
			list.get(i).display();
		}
	}

	public void createList(String listName) 
	{
		String name = listName;
		EmailList emailList = findEmailList(name);
		if (emailList == null) 
		{
			emailList = new EmailList(name);
			enterEmailAddress(emailList);
			this.addEmailList(emailList);
		}
		else 
		{
			System.out.println("The EmailList with the name already exist.");
		}
	}

	private void enterEmailAddress(EmailList emailList) 
	{
		boolean done = false;
		EmailAddress emailAddress = null;

		while (!done) 
		{
			emailAddress = new EmailAddress();
			emailAddress.addAddress(sc, "y");
			emailList.addEntry(emailAddress);

			sc.nextLine();

			System.out.print("Another? y/n: ");

			while (!sc.hasNextLine()) 
			{
				sc.next();
			}
			String option = sc.nextLine().toLowerCase();
			if (option.equals("y")) 
			{
				continue;
			} 
			else if (option.equals("n")) 
			{
				done = true;
			} 
			else 
			{
				System.out.println("Invalid option, please try again.");
			}
		}
	}

	//Prompts user to enter the name of the EmailList and deletes the list.
	public void deleteEntryFromList(String listName) 
	{
		String name = listName;
		EmailList emailList = findEmailList(name);

		if (emailList != null) 
		{
			int count = emailList.size();
			if (count > 0) 
			{
				emailList.displayDirectory();
				int index = -1;
				while (true) 
				{
					System.out.print("Enter entry number to delete: ");
					try 
					{
						index = sc.nextInt();
						sc.nextLine();
						break;
					} 
					catch (InputMismatchException ex) 
					{
						System.out.println("Please enter an integer");
						sc.nextLine();
					}
				}
				if (index >= 0 && index < count) 
				{
					emailList.deleteEntry(index);
				} 
				else 
				{
					System.out.println("The entry number is out of range.");
				}
			} 
			else 
			{
				System.out.println("The EmailList is empty.");
			}
		} 
		else 
		{
			System.out.println("The EmailList does not exist.");
		}
	}

	//Prompts user to enter the name of the EmailList and displays the list.
	public void displaySingleList(String listName) 
	{
		String name = listName;
		EmailList emailList = findEmailList(name);

		if (emailList != null) 
		{
			emailList.display();
		} 
		else 
		{
			System.out.println("The EmailList does not exist.");
		}
	}

	//Prompts user to enter the name of the EmailList and adds an EmailAddress to the list.
	public void addEntryToList(String listName) {
		String name = listName;
		EmailList emailList = findEmailList(name);

		if (emailList != null) 
		{
			EmailAddress emailAddress = new EmailAddress();
			emailAddress.addAddress(sc, "y");
			emailList.addEntry(emailAddress);
			sc.nextLine();
		}
		else 
		{
			System.out.println("The EmailList does not exist.");
		}
	}	

	//Code take from Assignment 2 solution
	//@Author: Linda Crane, professor for CST8130 (Data Structures)
	public int sortList(EmailList eList)
	{
		int high = list.size() - 1;
		int low = 0;
		while(low <= high)
		{
			int middle = (low + high)/2;
			if(list.get(middle).isSame(eList))
				return middle;
			if(list.get(middle).isGreater(eList))
				high = middle - 1;
			else
				low = middle + 1;
		}
		return low;
	}

	//Finds the specified EmailList for the given name.3
	public EmailList findEmailList(String name) 
	{
		for (EmailList emailList: list) 
		{
			if (emailList.isEqual(name)) 
			{
				return emailList;
			}
		}
		return null;
	}
}