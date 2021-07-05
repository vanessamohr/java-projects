
public class Person {
	
	// Attributes
	String name;
	String type; // Either Architect, Contractor or Customer
	String telephone;
	String email;
	String address;
	
	// Methods
	
	// Constructor
	public Person(String name, String type, String telephone, String email, String address) {
		this.name = name;
		this.type = type;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
	}
	
	// Other methods
	
	// Getters
	public String getPersonName() {
		return name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}
	
	// Setters
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
