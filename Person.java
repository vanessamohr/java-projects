
/**
 * The Class Person.
 */
public class Person {
	
	/** The name. */
	// Attributes
	private String name;
	
	/** The type. */
	private String type; // Either Architect, Contractor or Customer
	
	/** The telephone. */
	private String telephone;
	
	/** The email. */
	private String email;
	
	/** The address. */
	private String address;
	
	// Methods
	
	/**
	 * Instantiates a new person.
	 *
	 * @param name the name
	 * @param type the type
	 * @param telephone the telephone
	 * @param email the email
	 * @param address the address
	 */
	// Constructor
	public Person(String name, String type, String telephone, String email, String address) {
		this.name = name;
		this.type = type;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
	}
	
	// Other methods
	
	/**
	 * Gets the person name.
	 *
	 * @return the person name
	 */
	// Getters
	public String getPersonName() {
		return name;
	}
	
	/**
	 * Gets the person type.
	 *
	 * @return the person type
	 */
	public String getPersonType() {
		return type;
	}
	
	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the telephone.
	 *
	 * @param telephone the new telephone
	 */
	// Setters
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String string = "\nName: " + name +
				"\nJob Title: " + type +
				"\nTelephone number: " + telephone +
				"\nEmail address: " + email +
				"\nPhysical Address: " + address;
		return string;
	}
	
}
