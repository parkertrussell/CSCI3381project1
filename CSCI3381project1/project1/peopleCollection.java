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
import java.util.Scanner;

public class peopleCollection {
	private String fileName;
	private static final int DEFAULT_SIZE = 10;
	private Person[] people;
	public int size;

	public peopleCollection() {
		// default constructor
		size = 0;
		people = new Person[DEFAULT_SIZE];
		fileName = null;
	}
	public peopleCollection(String fn) {
		// constructor with given input file
		this();
		fileName = fn;
		readFile();
	}	
	public void addPerson(Person p) {
		// method that adds the input Student to the people array
		if (size >= people.length)
			doubleArray();
		people[size] = p;
		size++;
	}
	public void addPersonFromKeyboard () {
		String id, firstname, lastname, phone, status, contacted;

		Scanner personIn = new Scanner(System.in);
		personIn.close();
		System.out.println("Enter Person's identification number: ");
		id = personIn.next();

		System.out.print("Enter Person's First Name: ");
		firstname = personIn.next();

		System.out.print("Enter Person's First Name: ");
		lastname = personIn.next();

		System.out.print("Enter Person's Phone Number: ");
		phone = personIn.next();

		System.out.print("Enter Person's Infection Status: ");
		status = personIn.next();

		System.out.print("Enter Person's Contacts (ids): ");
		contacted = personIn.next();

		addPerson(new Person(id, firstname, lastname, phone, status, contacted));
	}
	public void removePerson (String id) {

		for(int i = 0; i < size; i++){
			if(people[i].getId().equalsIgnoreCase(id)) {
				for(int j = i; j < size - 1; j++){
					people[j] = people[j + 1];
				}
				size--;
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
	public void readFile () {
		/*BufferedReader lineReader = null;

		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;

			while ((line = lineReader.readLine())!=null) {
				String id = lineReader.readLine();
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
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				}
			catch (IOException e) {
				System.err.println("could not close the file reader");
			}
		}
		 */
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				String name = lineReader.readLine();
				String id = lineReader.readLine();
				String Fname = lineReader.readLine();
				String Lname = lineReader.readLine();
				String phoneNumber = lineReader.readLine();
				String status = lineReader.readLine();
				String contacts = lineReader.readLine();
				
				addPerson(new Person(id, Fname, Lname, phoneNumber, status, contacts));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String id = lineReader.readLine();
					String Fname = lineReader.readLine();
					String Lname = lineReader.readLine();
					String phoneNumber = lineReader.readLine();
					String status = lineReader.readLine();
					String contacts = lineReader.readLine();
					
					addPerson(new Person(id, Fname, Lname, phoneNumber, status, contacts));
				}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}
	public void writeFile() {
		try
		{

			FileWriter fw = new FileWriter(fileName);
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
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fileName);
		}
	}
	public void outputPeople() {
		String toOutput = "";
		for (int i = 0; i < size; i++) {
			toOutput += people[i]+"\n";
			System.out.println(toOutput);
			System.out.println("/n");
		}
	}
}