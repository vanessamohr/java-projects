
/**
 * The Class Person.
 */
public class Person {
	
	/** The name. */
	// Attributes
	private String name;
	
	/** The job title. */
	private String jobTitle; // Either Engineer, Project Manager, Architect or Client
	
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
	 * @param jobTitle the job title
	 * @param telephone the telephone
	 * @param email the email
	 * @param address the address
	 */
	// Constructor
	public Person(String name, String jobTitle, String telephone, String email, String address) {
		this.name = name;
		this.jobTitle = jobTitle;
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
	 * Gets the person title.
	 *
	 * @return the person title
	 */
	public String getPersonTitle() {
		return jobTitle;
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
				"\nJob Title: " + jobTitle +
				"\nTelephone number: " + telephone +
				"\nEmail address: " + email +
				"\nPhysical Address: " + address;
		return string;
	}

}
