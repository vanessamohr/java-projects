// Compulsory Task

/**
 * @author Vanessa
 * @version 3.00, 23 Sep 2021
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat; 

// Changes from Task 21
// 4 new classes have been added
// 2 facilitate reading from the text files 'projects.txt' and 'people.txt'
// 2 facilitate writing to the same text files
// readProjects() and readPeople() facilitate the reading of existing project/people data to the arrayList that stores Project and Person objects respectively
// 2 new menu items (8 and 9) have been added to call these 2 methods upon request of the user
// writeProject() and writePerson() facilitate the writing of newly created project/people data to the text files
// The newProject() and newPerson() methods now call these 2 methods so that each new project/person is written to the text files  

// NOTE: My decimal separator is set as a comma

/**
 * The Class PoisedEngineering.
 */
public class PoisedEngineering {
	
	/** The project number. */
	static int projectNumber = 1;
	
	/** The project array. */
	// ArrayList to store projects
	static ArrayList<Project> projectArray = new ArrayList<Project>();
	
	/** The person array. */
	// ArrayList to store persons
	static ArrayList<Person> personArray = new ArrayList<Person>();
	
	/** The input. */
	static Scanner input = new Scanner(System.in);
	
	/**
	 * New project.
	 *
	 * @return the project
	 * @throws ParseException the parse exception
	 */
	public static Project newProject() throws ParseException {
		String projectName = "";
		String buildingType = "";
		String address = "";
		int erfNumber = 0;
		double totalFee = 0;
		double amountPaid = 0;
		String deadline = "";
		// Upon creating an instance of the project class the attribute "finalised" should be "No" by default
		String finalised = "No"; // Yes or No
		// Upon creating an instance of the project class the attribute completionDate should be an empty string (this will be updated later)
		Date completionDate = null;
		// Get all particulars of new project from user
		System.out.println("Please enter a name for the project followed by Enter: ");
		projectName = input.nextLine().trim();
		System.out.println("Please enter the building type (House, Apartment, Office or Store) followed by Enter: ");
		buildingType = input.nextLine().trim();
		System.out.println("Please enter the address: ");
		address = input.nextLine().trim();
		System.out.println("Please enter the ERF number: ");
		erfNumber = input.nextInt();
		System.out.println("Please enter the total fee for the project: ");
		totalFee = input.nextDouble();
		System.out.println("Please enter the total amount paid to date: ");
		amountPaid = input.nextDouble();
		input.nextLine();
		System.out.println("Please enter the deadline for the project. The date MUST be of the format dd/mm/yyyy: ");
		deadline = input.nextLine().trim();
		Date deadlineAsDateObject = new SimpleDateFormat("dd/MM/yyyy").parse(deadline);  
		Person architect = newPerson("Architect");
		Person contractor = newPerson("Contractor");
		Person customer = newPerson("Customer");
		// If name for project was not entered use building type and customer's surname
		if (projectName.isBlank()) {
			String customerSurname = customer.getPersonName().substring(customer.getPersonName().lastIndexOf(" ") + 1);
			projectName = buildingType.trim() + " " + customerSurname.trim();
		}
		// Create new Project object using attributes entered by user
		Project project = new Project(projectNumber, projectName, buildingType, address, erfNumber, totalFee, amountPaid, deadlineAsDateObject, architect.getPersonName(), contractor.getPersonName(), customer.getPersonName(), finalised, completionDate);
		// Store projects in ArrayList (each new project at the next index)
		projectArray.add(project);
		writeProject(project);
		return project;
	}
	
	/**
	 * New person.
	 *
	 * @param jobTitle the job title
	 * @return the person
	 */
	public static Person newPerson(String jobTitle) {
		String personName = "";
		String personTelephone = "";
		String personEmail = "";
		String personAddress = "";
		System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s name: ");
		personName = input.nextLine().trim();
		// Get all particulars of new person from user (first architect, then contractor, then customer)
		System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s telephone number: ");
		personTelephone = input.nextLine().trim();
		System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s email address: ");
		personEmail = input.nextLine().trim();
		System.out.println("Please enter the " + jobTitle.toLowerCase() + "'s physical address: ");
		personAddress = input.nextLine().trim();
		// Create new Person object using attributes entered by user
		Person person = new Person(personName, jobTitle, personTelephone, personEmail, personAddress);
		// Store persons in ArrayList (each new project at the next index)
		personArray.add(person);
		writePerson(person);
		return person;
	}
	
	/**
	 * Read projects.
	 *
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	// This method reads all information from the text file 'projects' and passes them as arguments to the Project constructor
	public static void readProjects() throws NumberFormatException, IOException, ParseException {
		try {
			projectNumber = 0;
			String projectName = "";
			String buildingType = "";
			String address = "";
			int erfNumber = 0;
			double totalFee = 0;
			double amountPaid = 0;
			String deadline = "";
			String architectName = "";
			String contractorName = "";
			String customerName = "";
			String finalised = "";
			String completionDate = "";
			File inputFile = new File("projects.txt");
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			while (br.readLine() != null) {
				projectNumber = Project.getNoOfProjects() + 1;
				String projectNameLine = br.readLine();
				projectName = projectNameLine.substring(projectNameLine.lastIndexOf(":") + 2).trim();
				String buildingTypeLine = br.readLine();
				buildingType = buildingTypeLine.substring(buildingTypeLine.lastIndexOf(":") + 2).trim();
				String addressLine = br.readLine();
				address = addressLine.substring(addressLine.lastIndexOf(":") + 2).trim();
				String erfNumberLine = br.readLine();
				erfNumber = Integer.parseInt(erfNumberLine.substring(erfNumberLine.lastIndexOf(":") + 2));
				String totalFeeLine = br.readLine();
				totalFee = Double.parseDouble(totalFeeLine.substring(totalFeeLine.lastIndexOf("R") + 1));
				String amountPaidLine = br.readLine();
				amountPaid = Double.parseDouble(amountPaidLine.substring(amountPaidLine.lastIndexOf("R") + 1));
				String deadlineLine = br.readLine();
				deadline = deadlineLine.substring(deadlineLine.lastIndexOf(":") + 2).trim();
				Date deadlineAsDateObject = new SimpleDateFormat("dd/MM/yyyy").parse(deadline);
				String architectLine = br.readLine();
				architectName = architectLine.substring(architectLine.lastIndexOf(":") + 2).trim();
				String contractorLine = br.readLine();
				contractorName = contractorLine.substring(contractorLine.lastIndexOf(":") + 2).trim();
				String customerLine = br.readLine();
				customerName = customerLine.substring(customerLine.lastIndexOf(":") + 2).trim();
				String finalisedLine = br.readLine();
				finalised = finalisedLine.substring(finalisedLine.lastIndexOf(":") + 2).trim();
				String completionLine = br.readLine();
				String completionDatePre = completionLine.substring(completionLine.lastIndexOf(":") + 1).trim();
				Date completionDateAsObject = null;
				if (completionDatePre.isBlank()) {
					completionDate = "";
				} else {
					completionDate = completionDatePre.trim();
					completionDateAsObject = new SimpleDateFormat("dd/MM/yyyy").parse(completionDate);
				}
				if (projectArray.isEmpty()) {
					// Create new Project object using attributes read from text file
					Project project = new Project(projectNumber, projectName, buildingType, address, erfNumber, totalFee, amountPaid, deadlineAsDateObject, architectName, contractorName, customerName, finalised, completionDateAsObject);
					// Store projects in ArrayList (each new project at the next index)
					projectArray.add(project);
				}
				else {
					for (int i = 0; i < projectArray.size(); i++) {
						Project thisProject = projectArray.get(i);
						String thisProjectName = thisProject.getProjectName();
						if (projectName.equalsIgnoreCase(thisProjectName) == false) {
							// Create new Project object using attributes read from text file
							Project project = new Project(projectNumber, projectName, buildingType, address, erfNumber, totalFee, amountPaid, deadlineAsDateObject, architectName, contractorName, customerName, finalised, completionDateAsObject);
							// Store projects in ArrayList (each new project at the next index)
							projectArray.add(project);
						}
					}
				}
				// Clear all variables for next project
				projectName = "";
				buildingType = "";
				address = "";
				erfNumber = 0;
				totalFee = 0;
				amountPaid = 0;
				deadline = "";
				architectName = "";
				contractorName = "";
				customerName = "";
				finalised = "";
				completionDate = "";
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error reading file");
		} catch (NumberFormatException e) {
			System.out.println("String entered when number expected");
		} catch (IOException e) {
			System.out.println("Input or Output problem");
		} catch (ParseException e) {
			System.out.println("Some string failed to parse correctly");
		}
	}
	
	/**
	 * Read people.
	 *
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// This method reads all information from the text file 'people' and passes them as arguments to the Person constructor
	public static void readPeople() throws NumberFormatException, IOException {
		
		try {
			String personName = "";
			String personType = "";
			String personTelephone = "";
			String personEmail = "";
			String personAddress = "";
			File inputFile = new File("people.txt");
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			while (br.readLine() != null) {
				String personNameLine = br.readLine();
				personName = personNameLine.substring(personNameLine.lastIndexOf(":") + 2).trim();
				String personTypeLine = br.readLine();
				personType = personTypeLine.substring(personTypeLine.lastIndexOf(":") + 2).trim();
				String telephoneLine = br.readLine();
				personTelephone = telephoneLine.substring(telephoneLine.lastIndexOf(":") + 2).trim();
				String emailLine = br.readLine();
				personEmail = emailLine.substring(emailLine.lastIndexOf(":") + 2).trim();
				String addressLine = br.readLine();
				personAddress = addressLine.substring(addressLine.lastIndexOf(":") + 2).trim();
				if (personArray.isEmpty()) {
					// Create new Project object using attributes read from text file
					Person person = new Person(personName, personType, personTelephone, personEmail, personAddress);
					// Store projects in ArrayList (each new project at the next index)
					personArray.add(person);
				}
				else {
					for (int i = 0; i < personArray.size(); i++) {
						Person thisPerson = personArray.get(i);
						String thisPersonName = thisPerson.getPersonName();
						if (personName.equalsIgnoreCase(thisPersonName) == false) {
							// Create new Project object using attributes read from text file
							Person person = new Person(personName, personType, personTelephone, personEmail, personAddress);
							// Store projects in ArrayList (each new project at the next index)
							personArray.add(person);
						}
					}
				}
				// Clear all variables for next project
				personName = "";
				personType = "";
				personTelephone = "";
				personEmail = "";
				personAddress = "";
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error reading file");
		} catch (NumberFormatException e) {
			System.out.println("String entered when number expected");
		} catch (IOException e) {
			System.out.println("Input or Output problem");
		}

	}
	
	/**
	 * Write project.
	 *
	 * @param project the project
	 */
	// This method writes the details of a Project object to the text file 'projects' after creation
	public static void writeProject(Project project) {
		
		try {
			FileWriter writer = new FileWriter("projects.txt", true);
			BufferedWriter out = new BufferedWriter(writer);
			out.write("\nProject number: " + project.getProjectNumber());
			out.write("\nProject name: " + project.getProjectName());
			out.write("\nBuilding type: " + project.getBuildingType());
			out.write("\nAddress: " + project.getAddress());
			out.write("\nERF number: " + project.getErfNumber());
			out.write("\nTotal fee: " + project.getTotalFee());
			out.write("\nAmount paid to date: " + project.getAmountPaid());
			out.write("\nDeadline: " + project.getDeadline());
			out.write("\nArchitect: " + project.getArchitectName());
			out.write("\nContractor: " + project.getContractorName());
			out.write("\nClient: " + project.getCustomerName());
			out.write("\nFinalised: " + project.getFinalised());
			out.write("\nCompletion date: " + project.getCompletionDate());
			out.close();
		} catch (Exception e) {
			System.out.println("Error writing to file");
		}
		
	}
	
	/**
	 * Write person.
	 *
	 * @param person the person
	 */
	// This method writes the details of a Person object to the text file 'people' after creation
	public static void writePerson(Person person) {
		
		try {
			FileWriter writer = new FileWriter("people.txt", true);
			BufferedWriter out = new BufferedWriter(writer);
			out.write("\nName: " + person.getPersonName());
			out.write("\nJob title: " + person.getPersonType());
			out.write("\nTelephone number: " + person.getTelephone());
			out.write("\nEmail address: " + person.getEmail());
			out.write("\nAddress: " + person.getAddress());
			out.close();
		} catch (Exception e) {
			System.out.println("Error writing to file");
		}
		
	}
	
	// Whenever an update is made to a project the text file 'projects' is overwritten with all projects information including latest update
	/**
	 * Clear projects.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// The first method clears the text file whilst the second method writes all project information to it
	public static void clearProjects() throws IOException {
		FileWriter writer = new FileWriter("projects.txt", false);
		BufferedWriter out = new BufferedWriter(writer);
		out.write("");
	}
	
	/**
	 * Overwrite projects.
	 *
	 * @param project the project
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void overwriteProjects(Project project) throws IOException {
		try {
			FileWriter writer = new FileWriter("C:\\projects.txt", true);
			BufferedWriter out = new BufferedWriter(writer);
			out.write("\nProject number: " + project.getProjectNumber());
			out.write("\nProject name: " + project.getProjectName());
			out.write("\nBuilding type: " + project.getBuildingType());
			out.write("\nAddress: " + project.getAddress());
			out.write("\nERF number: " + project.getErfNumber());
			out.write("\nTotal fee: " + project.getTotalFee());
			out.write("\nAmount paid to date: " + project.getAmountPaid());
			out.write("\nDeadline: " + project.getDeadline());
			out.write("\nArchitect: " + project.getArchitectName());
			out.write("\nContractor: " + project.getContractorName());
			out.write("\nClient: " + project.getCustomerName());
			out.write("\nFinalised: " + project.getFinalised());
			out.write("\nCompletion date: " + project.getCompletionDate());
			out.close();
		} catch (Exception e) {
			System.out.println("Error writing to file");
		}
		
	}
	
	// Whenever an update is made to a person the text file 'people' is overwritten with all people information including latest update
	/**
	 * Clear people.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// The first method clears the text file whilst the second method writes all people information to it
	public static void clearPeople() throws IOException {
		FileWriter writer = new FileWriter("people.txt", false);
		BufferedWriter out = new BufferedWriter(writer);
		out.write("");
	}
	
	/**
	 * Overwrite people.
	 *
	 * @param person the person
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void overwritePeople(Person person) throws IOException {
		try {
			FileWriter writer = new FileWriter("people.txt", true);
			BufferedWriter out = new BufferedWriter(writer);
			out.write("\nName: " + person.getPersonName());
			out.write("\nJob title: " + person.getPersonType());
			out.write("\nTelephone number: " + person.getTelephone());
			out.write("\nEmail address: " + person.getEmail());
			out.write("\nAddress: " + person.getAddress());
			out.close();
		} catch (Exception e) {
			System.out.println("Error writing to file");
		}
		
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		
		int userChoice = 0;
		// Variables for menu option 1
		projectNumber = 0;
		// Variables for menu option 2 
		String jobTitle = "";
		// Variables for menu option 3 & its sub-options
		String projectName = "";
		int subChoice = 0;
		String newDeadline = "";
		double newAmountPaid = 0;
		// Variables for menu option 4 & its sub-options
		String personName = "";
		String newTelephone = "";
		String newEmail = "";
		// Variables for menu option 5
		double totalFeeForInvoice = 0;
		double amountPaidForInvoice = 0;
		String nameForInvoice = "";
		String phoneForInvoice = "";
		String emailForInvoice = "";
		String addressForInvoice = "";
		String completionDate;
		
		// Main menu
		while (userChoice != -1) {
			System.out.println("Please enter an option (number): \n1 - Add a new project \n2 - Add a new person \n3 - Update an existing project \n4 - Update the contact details of a person \n5 - Finalise a project and generate invoice \n6 - View all projects \n7 - View details of person \n8 - Read in existing projects \n9 - Read in existing people \n10 - Display list of projects that still need to be completed \n11 - Display list of projects that are past the deadline \n-1 - Exit \nPlease press enter upon completion of each entry");
			// Overview
			// Option 1: Add new project
			// Option 2: Add new person (Architect, Contractor, Customer)
			// Option 3: Modify to existing project
				// Sub-option 1: Update deadline of project
				// Sub-option 2: Update amount paid to date
			// Option 4: Update contact details of person
				// Sub-option 1: Update telephone number
				// Sub-option 2: Update email address
			// Option 5: Finalise an existing project (including invoice generation)
			// Option 6/7: View project/person details
			// Option 8/9: Read all project/person data to text file
			// -1 to exit
			
			userChoice = input.nextInt();
			if (userChoice == 1) {
				projectNumber = Project.getNoOfProjects() + 1; // Number each consecutive project 1 more than the current existing number of projects
				input.nextLine();
				newProject();
			}
			
			else if (userChoice == 2) {
				System.out.println("Please enter the job title of the person (Architect, Contractor or Customer): ");
				jobTitle = input.next().trim();
				input.nextLine();
				newPerson(jobTitle);
			}
			
			else if (userChoice == 3) {
				boolean found = false;
				input.nextLine();
				// First check whether project entered exists as an object in the projectArray
				try {
					while (found == false) {
						System.out.println("Please enter the name of the project you wish to make changes to: ");
						projectName = input.nextLine().trim();
						for (int i = 0; i < projectArray.size(); i++) {
							// Iterate through project array to match user input with particular project using its getProjectName() method
							Project thisProject = projectArray.get(i);
							String thisProjectName = thisProject.getProjectName();
							if (projectName.equalsIgnoreCase(thisProjectName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
								found = true;
								System.out.println("Please enter the number of one of the following options: \n1 - Update a project deadline \n2 - Update the amount paid to date");
								subChoice = input.nextInt();
								if (subChoice == 1) {
									input.nextLine();
									System.out.println("Please enter the new project deadline. The date MUST be of the format dd/mm/yyyy: ");
									newDeadline = input.nextLine().trim();
									Date newDeadlineAsObject = new SimpleDateFormat("dd/MM/yyyy").parse(newDeadline);
									projectArray.get(i).setDeadline(newDeadlineAsObject);
								}
								else if (subChoice == 2) {
									input.nextLine();
									System.out.println("Please enter the updated amount paid: ");
									newAmountPaid = input.nextDouble();
									projectArray.get(i).setTotalPaid(newAmountPaid);
								}
							}
						}
						if (found == false) {
								System.out.println("The project you entered does not exist in the database. Please check your use of spelling and letter case and try again.");
						}
					}
				} catch (Exception e) {
					System.out.println("Invalid input entered");
				}
				clearProjects();
				for (int j = 0; j < projectArray.size(); j++) {
					Project thisProject = projectArray.get(j);
					overwriteProjects(thisProject);
				}
			}
			
			else if (userChoice == 4) {
				boolean found = false;
				input.nextLine();
				// First check whether person entered exists as an object in the personArray
				try {
					while (found == false) {
						System.out.println("Please enter the name of the person whose contact details you wish to update: ");
						personName = input.nextLine().trim();
						for (int i = 0; i < personArray.size(); i++) {
							// Iterate through person array to match user input with particular person using its getPersonName() method
							Person thisPerson = personArray.get(i);
							String thisPersonName = thisPerson.getPersonName();
							if (personName.equalsIgnoreCase(thisPersonName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
								found = true;
								System.out.println("Please enter the number of one of the following options: \n1 - Update their telephone number \n2 - Update their email address");
								subChoice = input.nextInt();
								if (subChoice == 1) {
									input.nextLine();
									System.out.println("Please enter the new telephone number: ");
									newTelephone = input.nextLine().trim();
									personArray.get(i).setTelephone(newTelephone);
								}
								else if (subChoice == 2) {
									input.nextLine();
									System.out.println("Please enter the new email address: ");
									newEmail = input.nextLine().trim();
									personArray.get(i).setEmail(newEmail);
								}
							}
						}
						if (found == false) {
								System.out.println("The person you entered does not exist in the database. Please check your use of spelling and letter case and try again.");
						}
					}
				} catch (Exception e) {
					System.out.println("Invalid input entered");
				}
			}
			
			else if (userChoice == 5) {
				input.nextLine();
				System.out.println("Please enter the name of the project you wish to finalise: ");
				projectName = input.nextLine();
				System.out.println("Please enter the completion date: ");
				completionDate = input.nextLine();
				for (int i = 0; i < projectArray.size(); i++) {
					// Iterate through project array to match user input with particular project using its getProjectName() method
					Project thisProject = projectArray.get(i);
					String thisProjectName = thisProject.getProjectName();
					if (projectName.equalsIgnoreCase(thisProjectName)) {
						// Set the completion date and mark project as finalised
						projectArray.get(i).setFinalised("Yes");
						Date completionDateAsObject = new SimpleDateFormat("dd/MM/yyyy").parse(completionDate);
						projectArray.get(i).setCompletionDate(completionDateAsObject);
						totalFeeForInvoice = projectArray.get(i).getTotalFee();
						amountPaidForInvoice = projectArray.get(i).getAmountPaid();
						nameForInvoice = projectArray.get(i).getCustomerName();
					}
				}
				for (int j = 0; j < personArray.size(); j++) {
					// Iterate through person array to match user input with particular person using its getPersonName() method
					Person thisCustomer = personArray.get(j);
					String thisCustomerName = thisCustomer.getPersonName();
					if (nameForInvoice.equalsIgnoreCase(thisCustomerName)) {
						phoneForInvoice = thisCustomer.getTelephone();
						emailForInvoice = thisCustomer.getEmail();
						addressForInvoice = thisCustomer.getAddress();
					}
				}
				// Generate invoice
				if (Double.compare(totalFeeForInvoice, amountPaidForInvoice) != 0) {
					double amountDue = totalFeeForInvoice - amountPaidForInvoice;
					System.out.println("INVOICE \nCustomer name: " + nameForInvoice + "\nCustomer contact details \nTelephone number: " + phoneForInvoice + "\nEmail address: " + emailForInvoice + "\nPhysical Address: " + addressForInvoice + "\nAmount due: R" + amountDue);
				}
				clearProjects();
				for (int j = 0; j < projectArray.size(); j++) {
					Project thisProject = projectArray.get(j);
					overwriteProjects(thisProject);
				}
			}
			
			// View details of a project
			else if (userChoice == 6) {
				System.out.println(projectArray);
			}
			
			// View details of a person
			else if (userChoice == 7) {
				boolean found = false;
				input.nextLine();
				// First check whether person entered exists as an object in the personArray
				while (found == false) {
					System.out.println("Please enter the name of the person whose contact details you wish to view: ");
					personName = input.nextLine().trim();
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
				readProjects();
			}
			
			else if (userChoice == 9) {
				readPeople();
			}
			
			else if (userChoice == 10) {
				for (int i = 0; i < projectArray.size(); i++) {
					Project thisProject = projectArray.get(i);
					String thisProjectName = thisProject.getProjectName();
					if (thisProject.getFinalised().equalsIgnoreCase("No")) {
						System.out.println(thisProjectName);
					}
				}
			}
			
			else if (userChoice == 11) {
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
}
