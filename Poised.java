import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * The Class Poised.
 */
public class Poised {
	
	/** The project number static. */
	static int projectNumberStatic = 0;
	
	/** The project array. */
	// ArrayList to store projects
	static ArrayList<Project> projectArray = new ArrayList<Project>();
	
	/** The person array. */
	// ArrayList to store persons
	static ArrayList<Person> personArray = new ArrayList<Person>();
	
	/** The input. */
	static Scanner input = new Scanner(System.in);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) throws ParseException {
		
		// First read all database records into project and people ArrayLists
		getAllProjects();
		getAllPeople();
		
		// Initialise local variables
		int userChoice = 0;
		String jobTitle = ""; // Needed to call Person constructor on four separate titles in newProject() method
		
		// Main menu
		while (userChoice != -1) {
			System.out.println("Please enter an option (number): \n1 - Add a new project \n2 - Add a new person \n3 - Update an existing project \n4 - Update the contact details of a person \n5 - Finalise a project and generate invoice \n6 - View project details \n7 - View details of person \n8 - Display all projects not yet finalised \n9 - Display all projects past deadline \n-1 - Exit \nPlease press enter upon completion of each entry");
			// Overview
			// Option 1: Add new project 
			// Option 2: Add new person (Architect, Contractor, Customer)
			// Option 3: Update existing project
				// Sub-option 1: Update deadline of project
				// Sub-option 2: Update amount paid to date
			// Option 4: Update contact details of existing person
				// Sub-option 1: Update telephone number
				// Sub-option 2: Update email address
			// Option 5: Finalise an existing project (including invoice generation)
			// Option 6: View project details (Search by name or number)
			// Option 7: View person details
			// Option 8: Display all projects not yet finalised
			// Option 9: Display all projects past deadline
			// -1 to exit
			
			userChoice = input.nextInt();
			if (userChoice == 1) {
				projectNumberStatic = projectArray.size() + 1; // Number each consecutive project 1 more than the current existing number of projects
				input.nextLine();
				newProject(projectNumberStatic);
			}
			
			else if (userChoice == 2) {
				System.out.println("Please enter the job title of the person (Engineer, Manager, Architect or Client): ");
				jobTitle = input.next().trim();
				input.nextLine();
				newPerson(jobTitle);
			}
			
			
			else if (userChoice == 3) {
				updateProject();
			}
			
			else if (userChoice == 4) {
				updatePerson();
			}
			
			else if (userChoice == 5) {
				finaliseProject();
			}
			
			// View details of a project
			else if (userChoice == 6) {
				input.nextLine();
				boolean found = false;
				int searchChoice = 0;
				System.out.println("Would you like to search by project number or project name?: \n1 - Project number \n2 - Project name");
				searchChoice = input.nextInt();
				if (searchChoice == 1) {
					// First check whether project entered exists as an object in the projectArray
					while (found == false) {
						System.out.println("Please enter the number of the project you wish to view: ");
						int projectNumberToSearch = input.nextInt();
						for (int i = 0; i < projectArray.size(); i++) {
							// Iterate through project array to match user input with particular project using its getProjectNumber() method
							Project thisProject = projectArray.get(i);
							int thisProjectNum = thisProject.getProjectNumber();
							if (projectNumberToSearch == thisProjectNum) {
								found = true;
								System.out.println(thisProject);
							}
						}
						if (found == false) {
								System.out.println("The project you entered does not exist in the database. Please check your use of spelling and letter case and try again.");
						}
					}
				} else if (searchChoice == 2) {
					// First check whether project entered exists as an object in the projectArray
					while (found == false) {
						System.out.println("Please enter the name of the project you wish to view: ");
						String projectName = input.nextLine().trim();
						for (int i = 0; i < projectArray.size(); i++) {
							// Iterate through project array to match user input with particular project using its getProjectName() method
							Project thisProject = projectArray.get(i);
							String thisProjectName = thisProject.getProjectName();
							if (projectName.equalsIgnoreCase(thisProjectName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
								found = true;
								System.out.println(thisProject);
							}
						}
						if (found == false) {
								System.out.println("The project you entered does not exist in the database. Please check your use of spelling and letter case and try again.");
						}
					}
				} else {
					System.out.println("Please enter a valid option number");
				}
			}
			
			// View details of a person
			else if (userChoice == 7) {
				input.nextLine();
				boolean found = false;
				// First check whether person entered exists as an object in the personArray
				while (found == false) {
					System.out.println("Please enter the name of the person whose contact details you wish to view: ");
					String personName = input.nextLine().trim();
					for (int i = 0; i < personArray.size(); i++) {
						// Iterate through person array to match user input with particular person using its getPersonName() method
						Person thisPerson = personArray.get(i);
						String thisPersonName = thisPerson.getPersonName();
						if (personName.equalsIgnoreCase(thisPersonName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
							found = true;
							System.out.println(thisPerson);
						}
					}
					if (found == false) {
							System.out.println("The person you entered does not exist in the database. Please check your use of spelling and letter case and try again.");
					}
				}
			}
			
			else if (userChoice == 8) {
				for (int i = 0; i < projectArray.size(); i++) {
					Project thisProject = projectArray.get(i);
					String thisProjectName = thisProject.getProjectName();
					if (thisProject.getFinalised().equalsIgnoreCase("No")) {
						System.out.println(thisProjectName);
					}
				}
			}
			
			else if (userChoice == 9) {
				Date current = new Date();
				for (int i = 0; i < projectArray.size(); i++) {
					Project thisProject = projectArray.get(i);
					String thisProjectName = thisProject.getProjectName();
					if (thisProject.getDeadline().before(current) && thisProject.getFinalised().equalsIgnoreCase("No")) {
						System.out.println(thisProjectName);
					}
				}
			}
			
			else if (userChoice == -1) {
				System.out.println("Main menu exited");
			}
			else {
				System.out.println("Please enter a valid choice or enter -1 to exit");
			}
		}
		input.close();
	}
	
	/**
	 * Gets all the projects.
	 *
	 * @return the all projects
	 * @throws ParseException the parse exception
	 */
	// This methods reads all project information into project ArrayList
	public static void getAllProjects() throws ParseException {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "password");
			String query = "select * from projects";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery(query);
			// For each record in project table in database create a Project object using its getter functions
			while (result.next()) {
				Project project = new Project(result.getInt("Project_Number"), result.getString("Project_Name"), result.getString("Building_Type"), result.getString("Address"), result.getInt("ERF_Number"), result.getDouble("Total_Fee"), result.getDouble("Amount_Paid"), result.getDate("Deadline"), result.getString("Engineer"), result.getString("Project_Manager"), result.getString("Architect"), result.getString("Client"), result.getString("Finalised"), result.getDate("Completion_Date"));
				projectArray.add(project);
			}
			// Close connections
			conn.close();
			stmt.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all the people.
	 *
	 * @return the all people
	 * @throws ParseException the parse exception
	 */
	// This methods reads all person information into person ArrayList
	public static void getAllPeople() throws ParseException {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "password");
			String query = "select * from people";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery(query);
			// For each record in people table in database create a Person object using its getter functions
			while (result.next()) {
				Person person = new Person(result.getString("Name"), result.getString("Job_Title"), result.getString("Telephone_Number"), result.getString("Email_Address"), result.getString("Physical_Address"));
				personArray.add(person);
			}
			// Close connections
			conn.close();
			stmt.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * New project.
	 *
	 * @param projectNumber_s the project number s
	 * @return the project
	 * @throws ParseException the parse exception
	 */
	// This method takes user input to create a new Project object and a new database record 
	public static Project newProject(int projectNumber_s) throws ParseException {
		Project project = null;
		// Initialise variables for attributes of project
		String projectName = "";
		String buildingType = "";
		String address = "";
		int erfNumber = 0;
		double totalFee = 0;
		double amountPaid = 0;
		String deadline = "";
		Date deadlineAsObject = null;
		String finalised = "No";
		Date completionDate = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "password");
			// Get all particulars of new project from user
			System.out.println("Please enter a name for the project followed by Enter: ");
			projectName = input.nextLine().trim();
			System.out.println("Please enter the building type (House, Apartment, Office or Store) followed by Enter: ");
			buildingType = input.nextLine().trim();
			System.out.println("Please enter the address: ");
			address = input.nextLine().trim();
			System.out.println("Please enter the ERF number: ");
			erfNumber = input.nextInt();
			System.out.println("Please enter the total fee for the project (Note: if entering decimals, use comma as seperator): ");
			totalFee = input.nextDouble();
			System.out.println("Please enter the total amount paid to date (Note: if entering decimals, use comma as seperator): ");
			amountPaid = input.nextDouble();
			input.nextLine();
			System.out.println("Please enter the deadline for the project. The date MUST be of the format yyyy-MM-dd: ");
			deadline = input.nextLine().trim();
			deadlineAsObject = new SimpleDateFormat("yyyy-MM-dd").parse(deadline);
			Person engineer = newPerson("Engineer");
			Person manager = newPerson("Project Manager");
			Person architect = newPerson("Architect");
			Person client = newPerson("Client");
			// If name for project was not entered use building type and customer's surname
			if (projectName.isBlank()) {
				String customerSurname = client.getPersonName().substring(client.getPersonName().lastIndexOf(" ") + 1);
				projectName = buildingType.trim() + " " + customerSurname.trim();
			}
			// Create new Project object using attributes entered by user
			project = new Project(projectNumber_s, projectName, buildingType, address, erfNumber, totalFee, amountPaid, deadlineAsObject, engineer.getPersonName(), manager.getPersonName(), architect.getPersonName(), client.getPersonName(), finalised, completionDate);
			// Store project in ArrayList
			projectArray.add(project);
			// Prepare dates for entry into database
			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			Project latestProject = projectArray.get(projectArray.size() - 1);
			// Deadline 
			String formattedDate1 = targetFormat.format(project.getDeadline());
			// CompletionDate
			String formattedDate2 = "";
			if (project.getCompletionDate() != null) {
				formattedDate1 = targetFormat.format(project.getCompletionDate());
			} else {
				formattedDate2 = "";
			}
			// Add new project to SQL database
			String query = "insert into projects "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,  latestProject.getProjectNumber());
			stmt.setString(2,  latestProject.getProjectName());
			stmt.setString(3,  latestProject.getBuildingType());
			stmt.setString(4,  latestProject.getAddress());
			stmt.setInt(5,  latestProject.getErfNumber());
			stmt.setDouble(6, latestProject.getTotalFee());
			stmt.setDouble(7, latestProject.getAmountPaid());
			stmt.setString(8,  formattedDate1);
			stmt.setString(9,  latestProject.getEngineerName());
			stmt.setString(10,  latestProject.getManagerName());
			stmt.setString(11,  latestProject.getArchitectName());
			stmt.setString(12,  latestProject.getClientName());
			stmt.setString(13,  latestProject.getFinalised());
			if (formattedDate2.isBlank() == false) {
				stmt.setString(14,  formattedDate2);
			} else {
				stmt.setNull(14, Types.NULL);
			}
			stmt.execute();
			// Close connections
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}
	
	/**
	 * New person.
	 *
	 * @param jobTitle the job title
	 * @return the person
	 */
	// This method takes user input to create a new Person object and a new database record 
	public static Person newPerson(String jobTitle) {
		Person person = null;
		// Initialise variables for attributes of person
		String personName = "";
		String personTelephone = "";
		String personEmail = "";
		String personAddress = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "password");
			// Get all particulars of new person from user
			System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s name: ");
			personName = input.nextLine().trim();
			System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s telephone number: ");
			personTelephone = input.nextLine().trim();
			System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s email address: ");
			personEmail = input.nextLine().trim();
			System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s physical address: ");
			personAddress = input.nextLine().trim();
			// Create new Person object using attributes entered by user
			person = new Person(personName, jobTitle, personTelephone, personEmail, personAddress);
			// Store person in ArrayList
			personArray.add(person);
			Person latestPerson = personArray.get(personArray.size() - 1);
			// Add new person to SQL database
			String query = "insert into people "
					+ "values (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,  latestPerson.getPersonName());
			stmt.setString(2,  latestPerson.getPersonTitle());
			stmt.setString(3,  latestPerson.getTelephone());
			stmt.setString(4,  latestPerson.getEmail());
			stmt.setString(5,  latestPerson.getAddress());
			stmt.execute();
			// Close connections
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
	
	/**
	 * Update project.
	 *
	 * @throws ParseException the parse exception
	 */
	// This method takes user input to update an existing project's information (in projectArray and then the database);
	public static void updateProject() throws ParseException {
		input.nextLine();
		// Initialise local variables for menu option 3 & its sub-options
		String projectName = "";
		String newDeadline = "";
		double newAmountPaid = 0;
		boolean found = false;
		int subChoice = 0;
		String columnName = "";
		String query = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "password");
			System.out.println("Please enter the name of the project you wish to make changes to: ");
			projectName = input.nextLine().trim();
			System.out.println("Please enter the number of one of the following options: \n1 - Update a project deadline \n2 - Update the amount paid to date");
			subChoice = input.nextInt();
			while (found == false) {
				// First check whether project entered exists as an object in the projectArray
				for (int i = 0; i < projectArray.size(); i++) {
					// Iterate through projectArray to match user input with particular project using its getProjectName() method
					Project thisProject = projectArray.get(i);
					String thisProjectName = thisProject.getProjectName();
					if (projectName.equalsIgnoreCase(thisProjectName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
						found = true;
						if (subChoice == 1) {
							input.nextLine();
							System.out.println("Please enter the new project deadline. The date MUST be of the format yyyy-MM-dd: ");
							newDeadline = input.nextLine().trim();
							Date newDeadlineAsObject = new SimpleDateFormat("yyyy-MM-dd").parse(newDeadline);
							projectArray.get(i).setDeadline(newDeadlineAsObject);
							// Date format handling
							DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
							String formattedDate1 = targetFormat.format(thisProject.getDeadline());
							// Prepare query
							columnName = "Deadline";
							query = "update projects "
								+ "set " + columnName + " = '" + formattedDate1 
								+ "' where Project_Name = '" + thisProject.getProjectName() + "'";
						} else if (subChoice == 2) {
							input.nextLine();
							System.out.println("Please enter the updated amount paid: ");
							newAmountPaid = input.nextDouble();
							projectArray.get(i).setAmountPaid(newAmountPaid);
							// Prepare query
							columnName = "Amount_Paid";
							query = "update projects "
									+ "set " + columnName + " = " + thisProject.getAmountPaid() 
									+ " where Project_Name = '" + thisProject.getProjectName() + "'";
						} else {
							System.out.println("Please enter a valid option number");
						}
						// Update record in database
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.execute();
						// Close connections
						stmt.close();
					}
				}
				if (found == false) {
					System.out.println("The project you entered does not exist in the database. Please check your use of spelling and letter case and try again.");
				}
			}
			// Close connections
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update person.
	 */
	// This method takes user input to update an existing person's information (in personArray and then the database);
	public static void updatePerson() {
		input.nextLine();
		// Initialise local variables for menu option 4 & its sub-options
		String personName = "";
		String newTelephone = "";
		String newEmail = "";
		boolean found = false;
		int subChoice = 0;
		String columnName = "";
		String query = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "password");
			System.out.println("Please enter the name of the person whose contact details you wish to update: ");
			personName = input.nextLine().trim();
			System.out.println("Please enter the number of one of the following options: \n1 - Update their telephone number \n2 - Update their email address");
			subChoice = input.nextInt();
			while (found == false) {
				// First check whether person entered exists as an object in the personArray
				for (int i = 0; i < personArray.size(); i++) {
					// Iterate through personArray to match user input with particular person using its getPersonName() method
					Person thisPerson = personArray.get(i);
					String thisPersonName = thisPerson.getPersonName();
					if (personName.equalsIgnoreCase(thisPersonName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
						found = true;
						if (subChoice == 1) {
							input.nextLine();
							System.out.println("Please enter the new telephone number: ");
							newTelephone = input.nextLine().trim();
							personArray.get(i).setTelephone(newTelephone);
							columnName = "Telephone_Number";
							query = "update people "
								+ "set " + columnName + " = '" + thisPerson.getTelephone() 
								+ "' where Name = '" + thisPerson.getPersonName() + "'";
						} else if (subChoice == 2) {
							input.nextLine();
							System.out.println("Please enter the new email address: ");
							newEmail = input.nextLine().trim();
							personArray.get(i).setEmail(newEmail);
							columnName = "Email_Address";
							query = "update people "
									+ "set " + columnName + " = '" + thisPerson.getEmail() 
									+ "' where Name = '" + thisPerson.getPersonName() + "'";
						} else {
							System.out.println("Please enter a valid option number");
						}
						// Update record in database
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.execute();
						// Close connections
						stmt.close();
					}
				}
				if (found == false) {
					System.out.println("The person you entered does not exist in the database. Please check your use of spelling and letter case and try again.");
				}
			}
			// Close connections
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Finalise project.
	 *
	 * @throws ParseException the parse exception
	 */
	// This method finalises a project and generates an invoice
	public static void finaliseProject() throws ParseException {
		// Initialise local variables for menu option 5
		String projectName = "";
		String completionDate = "";
		double totalFeeForInvoice = 0;
		double amountPaidForInvoice = 0;
		String nameForInvoice = "";
		String phoneForInvoice = "";
		String emailForInvoice = "";
		String addressForInvoice = "";
		String formattedDate1 = "";
		String columnName = "";
		String query1 = "";
		String query2 = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "password");
			input.nextLine();
			System.out.println("Please enter the name of the project you wish to finalise: ");
			projectName = input.nextLine();
			System.out.println("Please enter the completion date in the format yyyy-MM-dd: ");
			completionDate = input.nextLine();	
			// Update projectArray
			for (int i = 0; i < projectArray.size(); i++) {
				// Iterate through project array to match user input with particular project using its getProjectName() method
				Project thisProject = projectArray.get(i);
				String thisProjectName = thisProject.getProjectName();
				if (projectName.equalsIgnoreCase(thisProjectName)) {
					// Set the completion date and mark project as finalised
					projectArray.get(i).setFinalised("Yes");
					Date completionDateAsObject = new SimpleDateFormat("yyyy-MM-dd").parse(completionDate);
					projectArray.get(i).setCompletionDate(completionDateAsObject);
					// Date format handling
					DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
					formattedDate1 = targetFormat.format(thisProject.getCompletionDate());
					// Perform database updates
					columnName = "Completion_Date";
					query1 = "update projects "
						+ "set " + columnName + " = '" + formattedDate1 
						+ "' where Project_Name = '" + thisProject.getProjectName() + "'";
					query2 = "update projects "
						+ "set Finalised = 'Yes'"
						+ " where Project_Name = '" + thisProject.getProjectName() + "'";
					PreparedStatement stmt1 = conn.prepareStatement(query1);
					PreparedStatement stmt2 = conn.prepareStatement(query2);
					stmt1.execute();
					stmt2.execute();
					// Close connections
					stmt1.close();
					stmt2.close();
					// Get project particulars for invoice
					totalFeeForInvoice = projectArray.get(i).getTotalFee();
					amountPaidForInvoice = projectArray.get(i).getAmountPaid();
					nameForInvoice = projectArray.get(i).getClientName();
				}
			}
			for (int j = 0; j < personArray.size(); j++) {
				// Iterate through person array to match user input with particular person using its getPersonName() method
				Person thisClient = personArray.get(j);
				String thisClientName = thisClient.getPersonName();
				if (nameForInvoice.equalsIgnoreCase(thisClientName)) {
					// Get client particulars for invoice
					phoneForInvoice = thisClient.getTelephone();
					emailForInvoice = thisClient.getEmail();
					addressForInvoice = thisClient.getAddress();
				}
			}
			// Generate invoice if full amount not yet received
			if (Double.compare(totalFeeForInvoice, amountPaidForInvoice) != 0) {
				double amountDue = totalFeeForInvoice - amountPaidForInvoice;
				System.out.println("INVOICE \nCustomer name: " + nameForInvoice + "\nCustomer contact details \nTelephone number: " + phoneForInvoice + "\nEmail address: " + emailForInvoice + "\nPhysical Address: " + addressForInvoice + "\nAmount outstanding: R" + amountDue);
			}
			// Close connections
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
