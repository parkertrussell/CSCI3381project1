package project1;

public class Person {
	private String personID, firstN, lastN, phoneNumber, status, contacts;

	public Person(String id, String firstname, String lastname, String phone, String status, String contacted) {
		personID = id;
		firstN = firstname;
		lastN = lastname;
		phoneNumber = phone;
		this.status = status;
		contacts = contacted;
	}
	public String getId() {
		return personID;
	}
	public String getFName() {
		return firstN;
	}
	public String getLName() {
		return lastN;
	}
	public String getPhoneNum() {
		return phoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public String getContactIDs() {
		return contacts;
	}
	public void editId(String n) {
		personID = n;
	}
	public void editFName(String n) {
		firstN = n;
	}
	public void editLName(String n) {
		lastN = n;
	}
	public void editPhoneNum(String n) {
		phoneNumber = n;
	}
	public void editStatus(String n) {
		status = n;
	}
	public void editContactIDs(String n) {
		contacts = n;
	}
}
