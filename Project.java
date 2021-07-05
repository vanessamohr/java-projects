
public class Project {
	
	// Attributes
	int projectNumber;
	String projectName;
	String buildingType;
	String address;
	int erfNumber;
	double totalFee;
	double amountPaid;
	String deadline;
	String architectName; // Note that the last three attributes only contain the name of said person, but the Person class contains the different attributes of a person
	String contractorName;
	String customerName;
	String finalised; // Yes or No
	String completionDate;
	
	private static int noOfProjects; // Count current number of projects in order to number next project

	// Methods
	
	// Constructor
	public Project(int projectNumber, String projectName, String buildingType, String address, int erfNumber, double totalFee, double amountPaid, String deadline, String architectName, String contractorName, String customerName, String finalised, String completionDate) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.address = address;
		this.erfNumber = erfNumber;
		this.totalFee = totalFee;
		this.amountPaid = amountPaid;
		this.deadline = deadline;
		this.architectName = architectName;
		this.contractorName = contractorName;
		this.customerName = customerName;
		this.finalised = finalised;
		this.completionDate = completionDate; // This will be empty string upon creating instance of Project class
		noOfProjects++;
	}
	
	// Other methods
	
	// Getters
	public String getProjectName() {
		return projectName;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public double getTotalFee() {
		return totalFee;
	}
	
	public double getAmountPaid() {
		return amountPaid;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	// Setters
	public void setTotalPaid(double totalPaid) {
		this.totalFee = totalPaid;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public void setFinalised(String finalised) {
		this.finalised = finalised;
	}
	
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	
	public static int getNoOfProjects() {
		return noOfProjects;
	 }

}
