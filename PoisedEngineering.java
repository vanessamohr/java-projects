// Compulsory Task 2

import java.util.ArrayList;
import java.util.Scanner;

// NOTE: My decimal separator is set as a comma

public class PoisedEngineering {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); 
		int userChoice = 0;
		// Variables for menu option 1
		int projectNumber = 0;
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
		String customerSurname = "";
		// Upon creating an instance of the project class the attribute "finalised" should be "No" by default and completion date an empty string
		String finalised = "No"; // Yes or No
		String completionDate = "";
		// Variables for menu option 2 
		String personName = "";
		String personType = ""; // Either Architect, Contractor or Customer
		String personTelephone = "";
		String personEmail = "";
		String personAddress = "";
		// Variables for menu option 3 & its sub-options
		int subChoice = 0;
		String newDeadline = "";
		double newAmountPaid = 0;
		// Variables for menu option 4 & its sub-options
		String newTelephone = "";
		String newEmail = "";
		// ArrayList to store projects
		ArrayList<Project> projectArray = new ArrayList<Project>();
		// ArrayList to store persons
		ArrayList<Person> personArray = new ArrayList<Person>();
		
		// Main menu
		while (userChoice != -1) {
			System.out.println("Please enter an option (number): \n1 - Add a new project \n2 - Add a new person \n3 - Update an existing project \n4 - Update the contact details of a person \n5 - Finalise a project \n-1 - Exit \nPlease press enter upon completion of each entry");
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
			// -1 to exit
			
			userChoice = input.nextInt();
			if (userChoice == 1) {
				projectNumber = Project.getNoOfProjects() + 1; // Number each consecutive project 1 more than the current existing number of projects
				input.nextLine();
				// Get all particulars of new project from user
				System.out.println("Please enter a name for the project followed by Enter: ");
				projectName = input.nextLine();
				System.out.println("Please enter the building type (House, Apartment, Office or Store) followed by Enter: ");
				buildingType = input.nextLine();
				System.out.println("Please enter the address: ");
				address = input.nextLine();
				System.out.println("Please enter the ERF number: ");
				erfNumber = input.nextInt();
				System.out.println("Please enter the total fee for the project: ");
				totalFee = input.nextDouble();
				System.out.println("Please enter the total amount paid to date: ");
				amountPaid = input.nextDouble();
				input.nextLine();
				System.out.println("Please enter the deadline for the project: ");
				deadline = input.nextLine();
				String[] personTypes = {"Architect", "Contractor", "Customer"};
				String[] personNames = {"", "", ""}; // Array to contain the names of the relevant people on the project in the order stated in line just above
				System.out.println("Please enter the name of the architect for the project: ");
				architectName = input.nextLine();
				personNames[0] = architectName;
				System.out.println("Please enter the name of the contractor for the project: ");
				contractorName = input.nextLine();
				personNames[1] = contractorName;
				System.out.println("Please enter the full name of the customer (the name, followed by a space and then the surname): ");
				customerName = input.nextLine();
				personNames[2] = customerName;
				// Create new Person objects for Architect, Contractor and Customer
				int counter = 0;
				while (counter < 3) {
					System.out.println("Please enter the details for " + personNames[counter]);
					// Get all particulars of new person from user (first architect, then contractor, then customer)
					System.out.println("Please enter the telephone number of the person: ");
					personTelephone = input.nextLine();
					System.out.println("Please enter the email address of the person: ");
					personEmail = input.nextLine();
					System.out.println("Please enter the physical address of the person: ");
					personAddress = input.nextLine();
					// Create new Person object using attributes entered by user
					Person person = new Person(personNames[counter], personTypes[counter], personTelephone, personEmail, personAddress);
					// Store projects in ArrayList (each new project at the next index)
					personArray.add(person);
					counter++;
				}
				// We need to extract the customer's surname from their name string, so we create a list storing name and surname separately and then use the surname (last index)
				String[] nameList = customerName.split("\\s+");
				customerSurname = nameList[nameList.length - 1];
				if (projectName == "") {
					projectName = buildingType + " " + customerSurname;
				}
				// Create new Project object using attributes entered by user
				Project project = new Project(projectNumber, projectName, buildingType, address, erfNumber, totalFee, amountPaid, deadline, architectName, contractorName, customerName, finalised, completionDate);
				// Store projects in ArrayList (each new project at the next index)
				projectArray.add(project);
			}
			else if (userChoice == 2) {
				input.nextLine();
				// Get all particulars of new person from user
				System.out.println("Please enter the name of the person: ");
				personName = input.nextLine();
				System.out.println("Please enter the type of the person (Architect, Contractor or Customer): ");
				personType = input.nextLine();
				System.out.println("Please enter the telephone number of the person: ");
				personTelephone = input.nextLine();
				System.out.println("Please enter the email address of the person: ");
				personEmail = input.nextLine();
				System.out.println("Please enter the physical address of the person: ");
				personAddress = input.nextLine();
				// Create new Person object using attributes entered by user
				Person person = new Person(personName, personType, personTelephone, personEmail, personAddress);
				// Store projects in ArrayList (each new project at the next index)
				personArray.add(person);
			}
			else if (userChoice == 3) {
				boolean found = false;
				input.nextLine();
				// First check whether project entered exists as an object in the projectArray
				while (found == false) {
					System.out.println("Please enter the name of the project you wish to make changes to: ");
					projectName = input.nextLine();
					for (int i = 0; i < projectArray.size(); i++) {
						// Iterate through project array to match user input with particular project using its getProjectName() method
						Project thisProject = projectArray.get(i);
						String thisProjectName = thisProject.getProjectName();
						if (projectName.equals(thisProjectName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
							found = true;
						}
					}
					if (found == false) {
							System.out.println("The project you entered does not exist. Please check your use of spelling and letter case and try again.");
					}
				}
				String projectNameToUse = projectName;
				System.out.println("Please enter the number of one of the following options: \n1 - Update a project deadline \n2 - Update the amount paid to date");
				subChoice = input.nextInt();
				if (subChoice == 1) {
					input.nextLine();
					System.out.println("Please enter the new project deadline: ");
					newDeadline = input.nextLine();
					for (int i = 0; i < projectArray.size(); i++) {
						// Iterate through project array to match user input with particular project using its getProjectName() method
						Project thisProject = projectArray.get(i);
						String thisProjectName = thisProject.getProjectName();
						if (projectNameToUse.equals(thisProjectName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
							projectArray.get(i).setDeadline(newDeadline);
						}
					}
				}
				else if (subChoice == 2) {
					input.nextLine();
					System.out.println("Please enter the updated amount paid: ");
					newAmountPaid = input.nextDouble();
					for (int i = 0; i < projectArray.size(); i++) {
						// Iterate through project array to match user input with particular project using its getProjectName() method
						Project thisProject = projectArray.get(i);
						String thisProjectName = thisProject.getProjectName();
						if (projectNameToUse.equals(thisProjectName)) { 
							projectArray.get(i).setTotalPaid(newAmountPaid);
						}
					}
				}
			}
			else if (userChoice == 4) {
				boolean found = false;
				input.nextLine();
				// First check whether person entered exists as an object in the personArray
				while (found == false) {
					System.out.println("Please enter the name of the person whose contact details you wish to update: ");
					personName = input.nextLine();
					for (int i = 0; i < personArray.size(); i++) {
						// Iterate through person array to match user input with particular person using its getPersonName() method
						Person thisPerson = personArray.get(i);
						String thisPersonName = thisPerson.getPersonName();
						if (personName.equals(thisPersonName)) { // NB NB NB!!!!!!! YOU CANNOT USE THE == OPERATOR BECAUSE IN JAVA IT COMPARES ADDRESSES OF STRINGS AND NOT THE ACTUAL STRINGS
							found = true;
						}
					}
					if (found == false) {
							System.out.println("The person you entered does not exist. Please check your use of spelling and letter case and try again.");
					}
				}
				String personNameToUse = personName;
				System.out.println("Please enter the number of one of the following options: \n1 - Update their telephone number \n2 - Update their email address");
				subChoice = input.nextInt();
				if (subChoice == 1) {
					input.nextLine();
					System.out.println("Please enter the new telephone number: ");
					newTelephone = input.nextLine();
					for (int i = 0; i < personArray.size(); i++) {
						// Iterate through person array to match user input with particular person using its getPersonName() method
						Person thisPerson = personArray.get(i);
						String thisPersonName = thisPerson.getPersonName();
						if (personName.equals(thisPersonName)) {
							personArray.get(i).setTelephone(newTelephone);
						}
					}
				}
				else if (subChoice == 2) {
					input.nextLine();
					System.out.println("Please enter the new email address: ");
					newEmail = input.nextLine();
					for (int i = 0; i < personArray.size(); i++) {
						// Iterate through person array to match user input with particular person using its getPersonName() method
						Person thisPerson = personArray.get(i);
						String thisPersonName = thisPerson.getPersonName();
						if (personName.equals(thisPersonName)) {
							personArray.get(i).setEmail(newEmail);
						}
					}
				}
			}
			else if (userChoice == 5) {
				// Initialise  variables to be printed on invoice
				double totalFeeForInvoice = 0;
				double amountPaidForInvoice = 0;
				String nameForInvoice = "";
				String phoneForInvoice = "";
				String emailForInvoice = "";
				String addressForInvoice = "";
				input.nextLine();
				System.out.println("Please enter the name of the project you wish to finalise: ");
				projectName = input.nextLine();
				System.out.println("Please enter the completion date: ");
				completionDate = input.nextLine();
				finalised = "Yes";
				for (int i = 0; i < projectArray.size(); i++) {
					// Iterate through project array to match user input with particular project using its getProjectName() method
					Project thisProject = projectArray.get(i);
					String thisProjectName = thisProject.getProjectName();
					if (projectName.equals(thisProjectName)) {
						// Set the completion date and mark project as finalised
						projectArray.get(i).setFinalised(finalised);
						projectArray.get(i).setCompletionDate(completionDate);
						totalFeeForInvoice = projectArray.get(i).getTotalFee();
						amountPaidForInvoice = projectArray.get(i).getAmountPaid();
						nameForInvoice = projectArray.get(i).getCustomerName();
					}
				}
				for (int j = 0; j < personArray.size(); j++) {
					// Iterate through person array to match user input with particular person using its getPersonName() method
					Person thisCustomer = personArray.get(j);
					String thisCustomerName = thisCustomer.getPersonName();
					if (nameForInvoice.equals(thisCustomerName)) {
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
			}
			else if (userChoice == -1) {
				System.out.println("Exiting");
			}
			else {
				System.out.println("Please enter a valid choice or enter -1 to exit");
			}
		}
		input.close();
	}

}
