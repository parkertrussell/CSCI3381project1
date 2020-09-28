package project1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//declare a boolean used to exit the menu loop of main (defaulted to true for initial run)
		boolean continueProgram = true;
		
		//create new peopleCollection with data from text file
		peopleCollection people = new peopleCollection("./project1/Data.txt");

		//lets the user of the program know how many (if any) people were read in from the file (on running, scroll up to see)
		System.out.println(people.size + " people were read from file.\n");

		//Testing for individual methods
		/*people.outputPeople();

		people.addPersonFromKeyboard();

		Scanner personToDelete = new Scanner(System.in);;
		System.out.println("What is the id of the person you want to remove?: ");
		String removeID = personToDelete.nextLine();
		personToDelete.close();
		people.removePerson(removeID);

		people.outputPeople();

		people.writeFile("./project1/testWrite.txt");

		System.out.println("Contacts of 515");
		people.displayContacts("515");

		"The person with id " + id + " is: " + 
		people.lookupName("515");

		String newContacts = "511, 512, 734";
		people.addContacts("515", newContacts);

		people.outputPeople();

		 */

		//open a scanner for main
		Scanner selectedOp = new Scanner(System.in);

		//a while loop that takes the user's requested selection (what they want to do)
		while (continueProgram = true) {
			int selection = 0;
			selection = mainMenu(selectedOp);

			if (selection == 1) {
				people.outputPeople();
			}
			else if (selection == 2) {
				people.addPersonFromKeyboard();
			}
			else if (selection == 3) {
				Scanner personToDelete = new Scanner(System.in);
				System.out.println("What is the id of the person you want to remove?: ");
				String removeID = personToDelete.next();

				people.removePerson(removeID);
			}
			else if (selection == 4) {
				int editSelection = editMenu(selectedOp);
				System.out.println("What is the id of the person whose information you want to edit?: ");
				String userToEdit = selectedOp.next();

				people.editInfo(userToEdit, editSelection);
			}
			else if (selection == 5) {
				System.out.println("What is the id of the person you are trying to find?: ");
				String userToFind = selectedOp.next();

				people.findPerson(userToFind);
			}
			else if (selection == 6) {
				System.out.println("What is the id of the person you are trying to find?: ");
				String userToFind = selectedOp.next();

				people.lookupName(userToFind);
			}
			else if (selection == 7) {
				System.out.println("What is the id of the person whose contacts you want to see?: ");
				String userToFind = selectedOp.next();

				people.displayContacts(userToFind);
			}
			else if (selection == 8) {
				people.writeFile("./project1/Data.txt");
				continueProgram = false;
				System.out.println("Program is exiting, all changes saved...");
				return;
			}
			else if (selection == 9) {
				continueProgram = false;
				System.out.println("Program is exiting, no changes saved...");
				return;
			}
			else {
				System.out.println("The selection entered was invalid, please review the options and try again.");
			}
		}
		selectedOp.close();
		return;
	}

	//produces a menu for users to choose what to do
	public static int mainMenu(Scanner selectedOp) {
		System.out.println("\n\nPlease Select An Operation: \n 1) Print current list of people \n 2) Add a new person from keyboard \n 3) Remove person from list \n 4) Edit info for a person \n 5) Find a person by id \n 6) Lookup just a name using the person's id \n 7) List the contacts for a person \n 8) Save changes and exit the program \n 9) Exit the program without updating/removing any information \n\n");
		int selection = selectedOp.nextInt();

		return selection;
	}
	//produces a task menu specifically for the editing option of "mainMenu"
	public static int editMenu(Scanner selectedOp) {
		System.out.println("What information would you like to edit? \n 1) id \n 2) first name \n 3) last name \n 4) phone number \n 5) infection status \n 6) add contacts 7) go back, no changes made");
		int selection = selectedOp.nextInt();

		return selection;
	}
}

