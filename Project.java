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
	
	/** The architect name. */
	private String architectName; // Note that the last three attributes only contain the name of said person, but the Person class contains the different attributes of a person
	
	/** The contractor name. */
	private String contractorName;
	
	/** The customer name. */
	private String customerName;
	
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
	 * @param architectName the architect name
	 * @param contractorName the contractor name
	 * @param customerName the customer name
	 * @param finalised the finalised
	 * @param completionDateAsObject the completion date as object
	 */
	// Constructor
	public Project(int projectNumber, String projectName, String buildingType, String address, int erfNumber, double totalFee, double amountPaid, Date deadlineAsDateObject, String architectName, String contractorName, String customerName, String finalised, Date completionDateAsObject) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.address = address;
		this.erfNumber = erfNumber;
		this.totalFee = totalFee;
		this.amountPaid = amountPaid;
		this.deadline = deadlineAsDateObject;
		this.architectName = architectName;
		this.contractorName = contractorName;
		this.customerName = customerName;
		this.finalised = finalised;
		this.completionDate = completionDateAsObject; // This will be empty string upon creating instance of Project class
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
	 * Gets the architect name.
	 *
	 * @return the architect name
	 */
	public String getArchitectName() {
		return architectName;
	}
	
	/**
	 * Gets the contractor name.
	 *
	 * @return the contractor name
	 */
	public String getContractorName() {
		return contractorName;
	}
	
	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
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
	 * Sets the total paid.
	 *
	 * @param totalPaid the new total paid
	 */
	// Setters
	public void setTotalPaid(double totalPaid) {
		this.totalFee = totalPaid;
	}
	
	/**
	 * Sets the deadline.
	 *
	 * @param deadline the new deadline
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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
	 */
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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
		if (finalised.equals("Yes")) {
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
				"\nArchitect: " + architectName +
				"\nContractor: " + contractorName +
				"\nClient: " + customerName +
				"\n" + progressString;
		return string;
	}

}
