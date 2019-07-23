import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;

/** 
 * Author: Mohammedaaman Shaikh
 * Class: Assign3
 * Course Name: CST8130
 * Lab Section: 300
 * Date: 25 MArch 2019
 * Purpose:  Main class for the assignment
 * Data Members: Scanner - for input
 * Directory: for using methods of Directory class
 * methods: main(String[]) - From where the program starts
 * menu(): displays options and manipulates user input
 * readFile() - read the specified file
 * enterName(String): String - checks for name if correct or not
 * openFile(String): Scanner - opens the specified file  
 */
public class Assign3 {
	private Scanner sc = new Scanner(System.in);
	private Directory directory = new Directory();

	public static void main(String[] args) 
	{
		Assign3 assign3 = new Assign3();
		assign3.menu();
	}

	// Shows the menu, interacts with the user for menu selection, loops till they exit.
	public void menu() 
	{
		boolean quit = false;
		String option = null;
		while (!quit) 
		{
			System.out.println("Enter \n\tc to create a new list");
			System.out.println("\tp to display all the email lists");
			System.out.println("\ta to add an entry to a list");
			System.out.println("\td to delete an entry from a list");
			System.out.println("\tl to display a list");
			System.out.println("\tf to load lists from file");
			System.out.println("\tq to quit ");
			System.out.print("Your Choice: ");
			while (!sc.hasNextLine()) 
			{
				sc.next();
			}

			option = sc.nextLine().toLowerCase();
			
			
			  switch(option)
			  {
			 		case "c": String name = enterName("Enter the name of list: ");
			 				  directory.createList(name);
			 				  break;
			 		case "p": directory.display();
			 				  break;
			 		case "a": String addName = enterName("Enter name of list to add: ");
			 				  directory.addEntryToList(addName);
			 				  break;
			 		case "d": String deleteName = enterName("Enter name of list to delete from: ");
			 				  directory.deleteEntryFromList(deleteName);
			 				  break;
			 		case "l": String listName = enterName("Enter name of list to display: ");
			 				  directory.displaySingleList(listName);
			 				  break;
			 		case "f": readFile();
			 				  break;
			 		case "q": System.out.println("Thanks!");
			 				  quit = true;
			 				  break;
			 		default:  System.out.println("Invalid entry, enter again!\n");
			 				  break;  
			  }
			System.out.println();
		}
	}	
	
	//Reads in EmailLists from a file.
	private void readFile() {
		String filename = null;

		System.out.print("Enter name of file to process: ");

		while (true) 
		{
			while (!sc.hasNextLine()) 
			{
				sc.next();
			}
			filename = sc.nextLine().trim();
			if (!filename.equals("")) 
			{
				break;
			}
		}

		Scanner scanner = openFile(filename);
		if (scanner != null) 
		{
			// Read the line count.
			int totalLines = 0;

			while (scanner.hasNextLine()) 
			{
				String line = scanner.nextLine().trim();

				if (!line.equals("")) 
				{
					try 
					{
						totalLines = Integer.parseInt(line);

						if (totalLines <= 0) 
						{
							return;
						}
						break;
					} 
					catch (NumberFormatException ex) 
					{
						return;
					}
				}
			}

			int lineCount = 0;

			while (scanner.hasNextLine()) 
			{
				String line = scanner.nextLine().trim();

				if (!line.equals("")) 
				{
					lineCount++;
					Scanner s = new Scanner(line).useDelimiter(" ");
					String name = s.next();
					int numberOfEmailAddresses = s.nextInt();
					EmailList emailList = new EmailList(name);

					for (int i = 0; i < numberOfEmailAddresses; i++) 
					{
						emailList.addEntry(new EmailAddress(s.next()));
					}
					directory.addEmailList(emailList);

					if (lineCount >= totalLines) 
					{
						break;
					}
				}
			}

			scanner.close();
		} 
		else 
		{
			System.out.println(filename + " not found");
		}
	}

	// Prompts user to enter a name that represents the EmailList and returns the name.
	private String enterName(String prompt) 
	{
		String name = null;
		boolean done = false;
		while (!done) 
		{
			System.out.print(prompt);
			while (!sc.hasNextLine()) 
			{
				sc.next();
			}
			name = sc.nextLine().trim();
			if (!name.equals("")) 
			{
				done = true;
			}
		}
		return name;
	}

	private Scanner openFile(String filename) 
	{
		File file = new File(filename);

		if (file.exists()) 
		{
			try 
			{
				Scanner scanner = new Scanner(file);
				return scanner;
			} 
			catch (FileNotFoundException e) 
			{
				return null;
			}
		} 
		else 
		{
			return null;
		}
	}
}