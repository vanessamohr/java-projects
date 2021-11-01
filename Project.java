import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class Project.
 */
public class Project {
	
	/** The project number. */
	// Attributes
	private int projectNumber;
	
	/** The project name. */
	private String projectName;
	
	/** The building type. */
	private String buildingType;
	
	/** The address. */
	private String address;
	
	/** The erf number. */
	private int erfNumber;
	
	/** The total fee. */
	private double totalFee;
	
	/** The amount paid. */
	private double amountPaid;
	
	/** The deadline. */
	private Date deadline;
	
	/** The engineer name. */
	private String engineerName;
	
	/** The manager name. */
	private String managerName;
	
	/** The architect name. */
	private String architectName; // Note that the last three attributes only contain the name of said person, but the Person class contains the different attributes of a person
	
	/** The client name. */
	private String clientName;
	
	/** The finalised. */
	private String finalised; // Yes or No
	
	/** The completion date. */
	private Date completionDate;
	
	/** The no of projects. */
	private static int noOfProjects; // Count current number of projects in order to number next project

	// Methods
	
	/**
	 * Instantiates a new project.
	 *
	 * @param projectNumber the project number
	 * @param projectName the project name
	 * @param buildingType the building type
	 * @param address the address
	 * @param erfNumber the erf number
	 * @param totalFee the total fee
	 * @param amountPaid the amount paid
	 * @param deadlineAsDateObject the deadline as date object
	 * @param engineerName the engineer name
	 * @param managerName the manager name
	 * @param architectName the architect name
	 * @param clientName the client name
	 * @param finalised the finalised
	 * @param completionDateAsObject the completion date as object
	 * @throws ParseException the parse exception
	 */
	// Constructor
	public Project(int projectNumber, String projectName, String buildingType, String address, int erfNumber, double totalFee, double amountPaid, Date deadlineAsDateObject, String engineerName, String managerName, String architectName, String clientName, String finalised, Date completionDateAsObject) throws ParseException {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.address = address;
		this.erfNumber = erfNumber;
		this.totalFee = totalFee;
		this.amountPaid = amountPaid; 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String deadlineString = formatter.format(deadlineAsDateObject);  
		Date deadlineAsFormattedDateObject =  new SimpleDateFormat("yyyy-MM-dd").parse(deadlineString);
		this.deadline = deadlineAsFormattedDateObject;
		this.engineerName = engineerName;
		this.managerName = managerName;
		this.architectName = architectName;
		this.clientName = clientName;
		this.finalised = finalised;
		if (completionDateAsObject != null) {
			String completionString = formatter.format(completionDateAsObject);  
			Date completionDateAsFormattedObject =  new SimpleDateFormat("yyyy-MM-dd").parse(completionString);
			this.completionDate = completionDateAsFormattedObject;
		}
		else {
			this.completionDate = null;
		}
		noOfProjects++;
	}
	
	// Other methods
	
	/**
	 * Gets the project number.
	 *
	 * @return the project number
	 */
	// Getters
	public int getProjectNumber() {
		return projectNumber;
	}
	
	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * Gets the building type.
	 *
	 * @return the building type
	 */
	public String getBuildingType() {
		return buildingType;
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
	 * Gets the erf number.
	 *
	 * @return the erf number
	 */
	public int getErfNumber() {
		return erfNumber;
	}
	
	/**
	 * Gets the total fee.
	 *
	 * @return the total fee
	 */
	public double getTotalFee() {
		return totalFee;
	}

	/**
	 * Gets the amount paid.
	 *
	 * @return the amount paid
	 */
	public double getAmountPaid() {
		return amountPaid;
	}
	
	/**
	 * Gets the deadline.
	 *
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}
	
	/**
	 * Gets the engineer name.
	 *
	 * @return the engineer name
	 */
	public String getEngineerName() {
		return engineerName;
	}
	
	/**
	 * Gets the manager name.
	 *
	 * @return the manager name
	 */
	public String getManagerName() {
		return managerName;
	}
	
	/**
	 * Gets the architect name.
	 *
	 * @return the architect name
	 */
	public String getArchitectName() {
		return architectName;
	}
	
	/**
	 * Gets the client name.
	 *
	 * @return the client name
	 */
	public String getClientName() {
		return clientName;
	}
	
	/**
	 * Gets the finalised.
	 *
	 * @return the finalised
	 */
	public String getFinalised() {
		return finalised;
	}
	
	/**
	 * Gets the completion date.
	 *
	 * @return the completion date
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * Sets the amount paid.
	 *
	 * @param amountPaid the new amount paid
	 */
	// Setters
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * Sets the deadline.
	 *
	 * @param deadline the new deadline
	 * @throws ParseException the parse exception
	 */
	public void setDeadline(Date deadline) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String deadlineString = formatter.format(deadline);  
		Date deadlineAsFormattedDateObject =  new SimpleDateFormat("yyyy-MM-dd").parse(deadlineString);
		this.deadline = deadlineAsFormattedDateObject;
	}
	
	/**
	 * Sets the finalised.
	 *
	 * @param finalised the new finalised
	 */
	public void setFinalised(String finalised) {
		this.finalised = finalised;
	}
	
	/**
	 * Sets the completion date.
	 *
	 * @param completionDate the new completion date
	 * @throws ParseException the parse exception
	 */
	public void setCompletionDate(Date completionDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String completionString = formatter.format(completionDate);  
		Date completionDateAsFormattedObject =  new SimpleDateFormat("yyyy-MM-dd").parse(completionString);
		this.completionDate = completionDateAsFormattedObject;
	}
	
	/**
	 * Gets the no of projects.
	 *
	 * @return the no of projects
	 */
	public static int getNoOfProjects() {
		return noOfProjects;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String progressString;
		if (finalised.equalsIgnoreCase("Yes")) {
			progressString = "This project has been completed. The completion date was " + completionDate;
		}
		else {
			progressString = "This project has not been completed";
		}
		String string = "\nProject number: " + projectNumber +
				"\nProject name: " + projectName +
				"\nBuilding type: " + buildingType +
				"\nAddress: " + address +
				"\nERF Number: " + erfNumber +
				"\nTotal Fee: R" + totalFee +
				"\nAmount Paid to date: R" + amountPaid +
				"\nDeadline: " + deadline +
				"\nEngineer: " + engineerName +
				"\nProject Manager: " + managerName +
				"\nArchitect: " + architectName +
				"\nClient: " + clientName +
				"\n" + progressString;
		return string;
	}

}
