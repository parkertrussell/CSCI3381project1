package project1;

/*
 * Data in file is formatted as follows:

 * id
 * first name
 * last name
 * phone number
 * status
 * contacts
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;

public class peopleCollection {
	//declare variables needed throughout the peopleCollection class
	private String fileName;
	private static final int DEFAULT_SIZE = 10;
	private Person[] people;
	public int size;

	public peopleCollection() {
		// default constructor for an empty peopleCollection class
		size = 0;
		people = new Person[DEFAULT_SIZE];
		fileName = null;
	}
	public peopleCollection(String fn) {
		// constructor with given input file (used in this program)
		this();
		fileName = fn;
		readFile();
	}	
	public void addPerson(Person p) {
		// method that adds the input People to the people array (called as the file is read in to add people)
		if (size >= people.length)
			doubleArray();
		people[size] = p;
		size++;
	}
	public void addPersonFromKeyboard () {
		//method used to manually add new people to the set of people
		String id, firstname, lastname, phone, status, contacted;

		Scanner personIn = new Scanner(System.in);

		System.out.println("Enter Person's identification number: ");
		id = personIn.nextLine();

		System.out.print("Enter Person's First Name: ");
		firstname = personIn.nextLine();

		System.out.print("Enter Person's Last Name: ");
		lastname = personIn.nextLine();

		System.out.print("Enter Person's Phone Number: ");
		phone = personIn.nextLine();

		System.out.print("Enter Person's Infection Status: ");
		status = personIn.nextLine();

		System.out.print("Enter Person's Contacts (ids): ");
		contacted = personIn.nextLine();

		addPerson(new Person(id, firstname, lastname, phone, status, contacted));
	}
	public void removePerson (String id) {
		//finds the person to delete and removes them from the array of people
		for(int i = 0; i < size; i++){
			if(people[i].getId().equalsIgnoreCase(id)) {
				for(int j = i; j < size - 1; j++){
					people[j] = people[j + 1];
				}
				size--;
			}
		}
	}
	public void findPerson (String id) {
		//lookup and display all information for a person by their id
		boolean found = false;
		for(int i = 0; i < size; i++){
			if(people[i].getId().equalsIgnoreCase(id)) {
				outputPerson(people[i]);
				found = true;
			}
		}
		if (found = false) {

			System.out.println("Error: id not found");
		}

	}
	public void editInfo (String id, int editSelection) {
		//used to edit any information regarding a Person
		boolean found = false;
		int p = 0;

		//find the person, store the index for use 
		for(int i = 0; i < size; i++){
			if(people[i].getId().equalsIgnoreCase(id)) {
				p = i;
				found = true;
			}
		}
		if (found = false) {
			System.out.println("Error: id not found");
			return;
		}

		if (editSelection == 1) { //change person's id
			Scanner newString = new Scanner(System.in);
			System.out.println("What would you like to change the person's id to?: ");
			String n = newString.nextLine();
			people[p].editId(n);
		}
		else if (editSelection == 2) { //change person's first name
			Scanner newString = new Scanner(System.in);
			System.out.println("What would you like to change the person's first name to?: ");
			String n = newString.nextLine();
			people[p].editFName(n);
		}
		else if (editSelection == 3) { //change person's last name
			Scanner newString = new Scanner(System.in);
			System.out.println("What would you like to change the person's last name to?: ");
			String n = newString.nextLine();
			people[p].editLName(n);
		}
		else if (editSelection == 4) { //change person's phone number
			Scanner newString = new Scanner(System.in);
			System.out.println("What would you like to change the person's phone number to?: ");
			String n = newString.nextLine();
			people[p].editPhoneNum(n);
		}
		else if (editSelection == 5) { //change person's infection status
			Scanner newString = new Scanner(System.in);
			System.out.println("What would you like to change the person's infection status to?: ");
			String n = newString.nextLine();
			people[p].editStatus(n);
		}
		else if (editSelection == 6) { //change a person's list of contacts
			Scanner newString = new Scanner(System.in);
			System.out.println("What ids would you like to add to the person's contact list?: ");
			String n = newString.nextLine();
			people[p].editContactIDs(n);
		}
		else if (editSelection == 7) {
			return;
		}
		else {
			System.out.println("The selection entered was invalid, please review the options and try again.");
		}
	}
	public void lookupName(String id) { //returns just a person's name, found by id
		for (int i = 0; i < size; i++) {
			if(people[i].getId().equalsIgnoreCase(id)) {
				System.out.println(people[i].getFName() + " " + people[i].getLName());
			}
		}
	}
	public void addContacts (String id, String newContacts) { //used to add new contacts to a Person
		for(int i = 0; i < size; i++){
			if(people[i].getId().equalsIgnoreCase(id)) {
				people[i].editContactIDs((people[i].getContactIDs() + ", " + newContacts));
			}
		}
	}
	public void displayContacts (String id) { //displays the ids of people that have come into contact with a user expressed id
		String contacts = "";

		for(int i = 0; i < size; i++){
			if(people[i].getId().equalsIgnoreCase(id)) {
				System.out.println(people[i].getContactIDs());
			}
		}
	}
	private void doubleArray () {
		// private method used to double the size of the array when needed
		Person[] newPeople = new Person[people.length*2];
		for (int i = 0; i < size; i++) {
			newPeople[i] = people[i];
		}		
		people = newPeople;
	}
	public void readFile () { //reads the file
		BufferedReader lineReader = null;

		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;

			while ((line = lineReader.readLine())!=null) {
				String id = line;
				String Fname = lineReader.readLine();
				String Lname = lineReader.readLine();
				String phoneNumber = lineReader.readLine();
				String status = lineReader.readLine();
				String contacts = lineReader.readLine();

				Person read = new Person(id, Fname, Lname, phoneNumber, status, contacts);
				addPerson(read);
			}
		} catch(IOException e) {
			System.err.println("Error reading file???");
		} 
	} 
	public void writeFile(String file) { //writes the output
		try
		{

			FileWriter fw = new FileWriter(file);
			BufferedWriter myOutfile = new BufferedWriter(fw);			

			for (int i = 0; i < size; i++) {
				Person person = people[i];

				myOutfile.write (person.getId()+"\n");
				myOutfile.write (person.getFName()+"\n");
				myOutfile.write (person.getLName()+"\n");
				myOutfile.write (person.getPhoneNum()+"\n");
				myOutfile.write (person.getStatus()+"\n");
				myOutfile.write (person.getContactIDs()+"\n");
			}
			myOutfile.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + file);
		}
	}
	public void outputPeople() { //displays all people in the people array as well as their information
		for (int i = 0; i < size; i++) {
			System.out.println(people[i].getId());
			System.out.println(people[i].getFName());
			System.out.println(people[i].getLName());
			System.out.println(people[i].getPhoneNum());
			System.out.println(people[i].getStatus());
			System.out.println("Contact with: " + people[i].getContactIDs());
			System.out.println("\n");
		}
	}
	public void outputPerson(Person p) { //displays just one person's information
		System.out.println(p.getId());
		System.out.println(p.getFName());
		System.out.println(p.getLName());
		System.out.println(p.getPhoneNum());
		System.out.println(p.getStatus());
		System.out.println("Contact with: " + p.getContactIDs());
		System.out.println("\n");
	}
	public Iterator<?> interator() {
		
		return null;

	}
}