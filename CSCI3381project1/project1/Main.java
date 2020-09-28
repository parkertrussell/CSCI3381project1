package project1;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		boolean continueProgram = true;
		
		peopleCollection people = new peopleCollection("Data.txt");
		
		System.out.println(people.size + " people were read from file.");
	
		while (continueProgram = true) {
			Scanner selectedOp = new Scanner(System.in);
			
			System.out.println("Please Select An Operation: \n 1) Print current list of people \n 2) Add a new person from keyboard \n 3) Remove person from list \n 4) Save current list and exit the program \n");
			int selection = selectedOp.nextInt();
			selectedOp.close();
			
			if (selection == 1) {
				people.outputPeople();
			}
			else if (selection == 2) {
				people.addPersonFromKeyboard();
			}
			else if (selection == 3) {
				Scanner personToDelete = new Scanner(System.in);
				personToDelete.close();
				System.out.println("What is the id of the person you want to remove?: ");
				String removeID = personToDelete.next();
				
				people.removePerson(removeID);
			}
			else if (selection == 4) {
				people.writeFile();
				continueProgram = false;
			}
			else {
				System.out.println("The selection entered was invalid, please review the options and try again.");
			}
		}
		
		people.writeFile();
	}
}
