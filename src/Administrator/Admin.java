package Administrator;

import NewCustomer.NewCustomerDriver;
import Util.Display;

public class Admin extends AdministratorDriver {
        
    protected void runAdminDashboard(String loggedInID) {

        do {

            do {
                
                authorizationFlag = false;
                
                viewAdminDashboard();
                System.out.print("\n[+] Choose a number(0 to return to main menu): ");

                choice = input.next().charAt(0);
                input.nextLine();
                
                switch (choice) {
                    
                    case '1':
                        /*case [1] Add User -> (Operator || Customer || Administrator)? */
                        addUser();
                        break;
                    case '2':
                        /*case [2] Update User-> (Operator || Customer || Administrator)? */
                        updateUser(loggedInID);
                        if(authorizationFlag)
                            return;
                        break;
                    case '3':
                        /*case [3] Delete User-> (Operator || Customer || Administrator)? */
                        deleteUser(loggedInID);
                        if(authorizationFlag)
                            return;
                        break;
                    case '4':
                        viewTotalCollected();
                        break;
                    case '5':
                        viewBillsOfSpecificRegion();
                        break;
                    case '6':
                        consumptionStatForSpecificRegion();
                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.toString());
                        }
                        break;
                        
                }
                                
            } while (choice != '1' && choice != '2' && choice != '3' && choice != '4' && choice != '5' && choice != '6');

            System.out.print("\n[+] Do you want to perform any additional operation in this dashboard?(y/n): ");
            
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }
    
    
    private static void viewAdminDashboard() {

        Display.clearScreen();
        Display.printProgramNameBanner();
        Display.printAdminDashboardBanner();
        Display.printAdminSelections();
        
    }

    private void addUser() {
        
        do {
            
            Display.addUserSelections();

            System.out.print("\n[+] Choose a number(0 to return to admin dashboard menu): ");
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    addNewOperator();
                    break;
                case '3':
                    addNewAdmin();
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n\t[+] Do you want to Add another User?(y/n): ");
            
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private void addNewCustomer() {
        
        NewCustomerDriver newCustomer = new NewCustomerDriver();
        newCustomer.fillNewContract();
        
    }

    
    private void addNewOperator() {
        
        runPersonDriver();
        setAdministratorID(getGovernmentCode(), getNationalIdNum());
        setAdministratorRole(operatorRole);
        System.out.print("\n\t[+] Enter Your Password : ");
        setAdministratorPass(Password_Val(input.nextLine()));
        System.out.println("\n[-] Your Operator ID is: " + getAdministratorID() + " . You can use it to login.");
        pushAllAdministratorInfoToDB();
        
    }

    
    private void addNewAdmin() {
        
        runPersonDriver();
        setAdministratorID(getGovernmentCode(), getNationalIdNum());
        setAdministratorRole(adminRole);
        System.out.print("\n\t[+] Enter Your Password : ");
        setAdministratorPass(Password_Val(input.nextLine()));
        System.out.println("\n[-] Your Admin ID is: " + getAdministratorID() + " . You can use it to login.");
        pushAllAdministratorInfoToDB();
        
    }

    
    private void deleteUser(String loggedInID) {
        
        do {
            
            authorizationFlag = false;
            
            Display.deleteUserSelections();
            
            System.out.print("\n[+] Choose a number(0 to return to admin dashboard menu): ");
            
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t[+] Enter meter code: ");
                    deleteCustomer(Customer_Val(input.nextLine()));
                    break;
                case '2':
                    System.out.print("\n\t[+] Enter Operator ID: ");
                    deleteOperator(OperatorID_Val(input.nextLine()));
                    break;
                case '3':
                    System.out.print("\n\t[+] Enter Admin ID: ");
                    deleteAdmin(AdminID_Val(input.nextLine()), loggedInID);
                    if(authorizationFlag)
                        return;
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n\t[+] Do you want to Delete another User? (y/n): ");
            
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private void deleteOperator(String operatorID) {
    
        if(getNumOfAdministratorRole(operatorRole) == 1){
            
            System.out.println("\n[-] This is the last operator, add another operator before deleting the current one.");
            return;
            
        }
        
        deleteAdministratorFromDB(operatorID);
        System.out.println("\n\t[-] The operator has been successfully deleted.");

    }

    
    private void deleteAdmin(String adminID, String loggedInID) {
        
        if(getNumOfAdministratorRole(adminRole) == 1){
            
            System.out.println("\n[-] This is the last admin, add another admin before deleting the current one.");
            return;
            
        }
        
        deleteAdministratorFromDB(adminID);
        System.out.println("\n\t[-] The admin has been successfully deleted.");
        
        if(adminID.equals(loggedInID)){
            
            authorizationFlag = true;
            System.out.println("\n\t[-] You just deleted yourself, so you are not authorized to continue with this dashboard.");
            System.out.println("\t[!] You will be logged out in five seconds from now.\n");
            
            try {
                
                Thread.sleep(5000);
                
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
            
        }
        
    }

    private void updateUser(String loggedInID) {
        
        do {
            
            Display.updateUserSelections();
            
            System.out.print("\n[+] Choose a number(0 to return to admin dashboard menu): ");
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t[+] Enter meter code: ");
                    viewCustomerDataBase(Customer_Val(input.nextLine()));
                    break;
                case '2':
                    System.out.print("\n\t[+] Enter Operator ID: ");
                    viewOperatorDataBase(OperatorID_Val(input.nextLine()));
                    break;
                case '3':
                    System.out.print("\n\t[+] Enter Admin ID: ");
                    viewAdminDataBase(AdminID_Val(input.nextLine()), loggedInID);
                    if(authorizationFlag)
                        return;
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n\t[+] Do you want to Update another User?(y/n): ");
            
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private void viewCustomerDataBase(String meterCode) {
        
        do {
            
            System.out.println("\n[-] Select The Information You Want To Update In (" + getCustomerName(meterCode).split(" ")[0] + "'s) Information.");
            Display.customerUpdateList();
            System.out.print("\n[+] Choose a number(0 to return to admin dashboard menu): ");
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t[+] Enter new address: ");
                    updateCustomer(addressColumn, Address_Val(input.nextLine()), meterCode);
                    break;
                case '2':
                    System.out.print("\n\t[+] Enter new email: ");
                    updateCustomer(emailColumn, Email_Val(input.nextLine()), meterCode);
                    break;
                case '3':
                    System.out.print("\n\t[+] Enter new government code: ");
                    updateCustomer(governmentCodeColumn, GovernmentCode_Val(input.nextLine()), meterCode);
                    break;
                case '4':
                    System.out.print("\n\t[+] Enter new phone number: ");
                    updateCustomer(phoneNumColumn, PhoneNumber_Val(input.nextLine()), meterCode);
                    break;
                case '5':
                    NewCustomerDriver newCustomerDriver = new NewCustomerDriver();
                    System.out.print("\n\t[+] Enter new type of use(0 for Home use, 1 for Commerical use): ");
                    updateCustomer(typeOfUseColumn, newCustomerDriver.TypeOfUse_Val(input.nextLine()), meterCode);
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n\t[+] Do you want to Update another Information in (" + getCustomerName(meterCode).split(" ")[0] + "'s) Informations?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private void viewOperatorDataBase(String operatorID) {
        
        do {
            
            System.out.println("\n[-] Select The Information You Want To Update In (" + getAdministratorName(operatorID).split(" ")[0] + "'s) Information.");
            System.out.println("\n[!] Alert : You Can't Update Name, National Id, Birth Date, Operator ID, Gender nor Contract Date.");
            Display.administratorUpdateList();
            System.out.print("\n[+] Choose a number(0 to return to admin dashboard menu): ");
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t[+] Enter new address: ");
                    updateOperator(addressColumn, Address_Val(input.nextLine()), operatorID);
                    break;
                case '2':
                    System.out.print("\n\t[+] Enter new email: ");
                    updateOperator(emailColumn, Email_Val(input.nextLine()), operatorID);
                    break;
                case '3':
                    System.out.print("\n\t[+] Enter new phone number: ");
                    updateOperator(phoneNumColumn, PhoneNumber_Val(input.nextLine()), operatorID);
                    break;
                case '4':
                    System.out.print("\n\t[+] Enter new password: ");
                    updateOperator(administratorPassColumn, Password_Val(input.nextLine()), operatorID);
                    break;
                case '5':
                    System.out.print("\n\t[+] Enter new Administrator Role(0 for Operator, 1 for Admin): ");
                    updateOperator(administratorRoleColumn, AdministratorRole_Val(input.nextLine(), operatorID), operatorID);
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n\t[+] Do you want to Update another Information in (" + getAdministratorName(operatorID).split(" ")[0] + "'s) Informations ?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    private void viewAdminDataBase(String adminID, String loggedInID) {

        do {
            
            authorizationFlag = false;
            
            System.out.println("\n[-] Select The Information You Want To Update In (" + getAdministratorName(adminID).split(" ")[0] + "'s) Information.");
            System.out.println("\n\t[!] Alert : You Can't Update Name, National Id, Birth Date, Admin ID, Gender nor Contract Date.");
            Display.administratorUpdateList();
            System.out.print("\n[+] Choose a number(0 to return to admin dashboard menu): ");

            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t[+] Enter new address: ");
                    updateAdmin(addressColumn, Address_Val(input.nextLine()), adminID, loggedInID);
                    break;
                case '2':
                    System.out.print("\n\t[+] Enter new email: ");
                    updateAdmin(emailColumn, Email_Val(input.nextLine()), adminID, loggedInID);
                    break;
                case '3':
                    System.out.print("\n\t[+] Enter new phone number: ");
                    updateAdmin(phoneNumColumn, PhoneNumber_Val(input.nextLine()), adminID, loggedInID);
                    break;
                case '4':
                    System.out.print("\n\t[+] Enter new password: ");
                    updateAdmin(administratorPassColumn, Password_Val(input.nextLine()), adminID, loggedInID);
                    break;
                case '5':
                    System.out.print("\n\t[+] Enter new Administrator Role(0 for Operator, 1 for Admin): ");
                    updateAdmin(administratorRoleColumn, AdministratorRole_Val(input.nextLine(), adminID), adminID, loggedInID);
                    if(authorizationFlag)
                        return;
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n\t[+] Do you want to Update another Information in (" + getAdministratorName(adminID).split(" ")[0] + "'s) Informations ?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private String Password_Val(String pass){
        
        do {
            
            if(pass.isBlank()){
                
                System.out.print("\n[-] Invalid password, Enter a valid password: ");
                pass = input.nextLine();
                
            } else 
                return pass;
                
        } while (true);
        
    }
    
    
    private String AdministratorRole_Val(String selector, String administratorID){
        
        do{
            
            switch (selector.charAt(0)) {
                case '0':
                    if(getNumOfAdministratorRole(adminRole) == 1 && getAdministratorRole(administratorID).equals(adminRole)){
                        System.out.println("\n[-] This is the last admin, add another admin before updating the current one.");
                    } else if(selector.charAt(0) == '0')
                        return operatorRole;
                case '1':
                    if(getNumOfAdministratorRole(operatorRole) == 1 && getAdministratorRole(administratorID).equals(operatorRole)){
                        System.out.println("\n[-] This is the last operator, add another operator before updating the current one.");
                    } else if(selector.charAt(0) == '1')
                        return adminRole;
                default:
                    System.out.print("\n[-] Enter a valid administrator role(0 for Operator, 1 for Admin): ");
                    selector = input.nextLine();
                    break;
            }
            
        } while (true);
        
    }
    
    
    private void updateCustomer(final String columnName, String value, String meterCode) {

        pushCustomerUpdateToDB(columnName, value, meterCode);
        System.out.println("\n\t[-] Customer information has been successfully updated.");

    }

    private void updateOperator(final String columnName, String value, String operatorID) {
        
        pushAdministratorUpdateToDB(columnName, value, operatorID);
        System.out.println("\n\t[-] Operator information has been successfully updated.");
        
    }

    private void updateAdmin(final String columnName, String value, String adminID, String loggedInID) {
        
        pushAdministratorUpdateToDB(columnName, value, adminID);
        System.out.println("\n\t[-] Admin information has been successfully updated.");
        
        if(adminID.equals(loggedInID) && columnName.equals(administratorRoleColumn) && value.equals(operatorRole)){
            
            authorizationFlag = true;
            System.out.println("\n\t[-] You have just switched your role to 'Operator', so you are not authorized to continue with this dashboard.");
            System.out.println("\t[!] You will be logged out in five seconds from now.\n");
            
            try {
                
                Thread.sleep(5000);
                
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        
        }

    }    
    
    private void viewTotalCollected() {
        
        System.out.format("\n\t\t[-] The Total Collected is: %.2f L.E\n", getTotalCollectedFromDB());
        
    }

    private void consumptionStatForSpecificRegion() {

        printGovernmentCodes();
        
        do {            
            
            System.out.print("\n[+] Enter the government code of the region that you want to make consumption statistics for: ");
            String governmentCode = GovernmentCode_Val(input.nextLine());
            
            String[] statisticsData = consumptionStatForSpecificRegionFromDB(governmentCode);
            
            System.out.println("\n\t [-] The Summation Of Consumptions: " + statisticsData[0]);
            System.out.println("\n\t [-] The Actual Number Of Consumers: " + statisticsData[1]);
            System.out.println("\n\t [-] The Average consumption For: " + statisticsData[2]);

            System.out.print("\n[+] Do you want to View Consumption Of another region?(y/n): ");
            qContinue = input.next().charAt(0);
            input.nextLine();
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }
    
}