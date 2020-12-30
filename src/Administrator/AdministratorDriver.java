package Administrator;

import NewCustomer.NewCustomerDriver;
import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class AdministratorDriver extends Administrator {

    // Data fields
    private String administratorID;
    private String administratorPass;
    
    private char qContinue, choice;
    Scanner input = new Scanner(System.in);
    

    public void runDashboard() {

        System.out.print("\n\t[+] Enter administrator ID: ");
        administratorID = AdministratorID_Val(input.nextLine());

        System.out.print("\n\t[+] Enter administrator password: ");
        administratorPass = input.nextLine();

        String choiceDashboard = loginForm(administratorID, administratorPass);

        if (choiceDashboard.equals(adminRole)) {
            runAdminDashboard();
        } else {
            runOperatorDashboard();
        }

    }


    private void runAdminDashboard() {

        do {

            viewAdminDashboard();

            do {

                System.out.print("\n[+] Choose a number(0 to return to main menu): ");

                choice = input.next().charAt(0);
                input.nextLine();
                
                switch (choice) {
                    
                    case '1':
                        /*case [1] Add User -> (Operator || Customer || Administrator)? */
                        addUser();
                        break;
                    case '2':
                        /*case [2] Delete User-> (Operator || Customer || Administrator)? */
                        deleteUser();
                        break;
                    case '3':
                        /*case [3] Update User-> (Operator || Customer || Administrator)? */
                        updateUser();
                        break;
                    case '4':
                        viewTotalCollected();
                        break;
                    case '5':
                        //view bills of specific region
                        break;
                    case '6':
                        consumptionStatForSpecificRegion();
                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        break;
                        
                }

            } while (choice != '1' && choice != '2' && choice != '3' && choice != '4' && choice != '5' && choice != '6');

            System.out.print("\n[+] Do you want to perform any additional operation in this dashboard? (y/n): ");
            
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }

    
    private static void viewAdminDashboard() {

        clearScreen();
        printAdminBanner();
        printAdminSelections();
        
    }

    
    private static void printAdminBanner() {

        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        System.out.println("\t\t[-] Welcome in Admin Dashboard [-]");
        System.out.println("\t\t——————————————————————————————————");

    }

    
    private static void printAdminSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Add user.\n");
        System.out.println("\t     [2] - Delete user.\n");
        System.out.println("\t     [3] - Update user.\n");
        System.out.println("\t     [4] - View total collected.\n");
        System.out.println("\t     [5] - View all bills of specific regions.\n");
        System.out.println("\t     [6] - Make consumption statistics for specific regions.");

    }

   
    private void runOperatorDashboard() {

        do {

            viewOperatorDashboard();

            do {

                System.out.print("\n[+] Choose a number(0 to return to main menu): ");
                
                choice = input.next().charAt(0);
                input.nextLine();
                
                switch (choice) {
                    case '1':

                        break;
                    case '2':

                        break;
                    case '3':

                        break;
                    case '4':

                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        break;
                }

            } while (choice != '1' && choice != '2' && choice != '3' && choice != '4');

            System.out.print("\n[+] Do you want to perform any additional operation in this dashboard? (y/n): ");
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }

    
    private static void viewOperatorDashboard() {

        clearScreen();
        printOperatorBanner();
        printOperatorSelections();
        
    }

    
    private static void printOperatorBanner() {

        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        System.out.println("\t      [-] Welcome in Operator Dashboard [-]");
        System.out.println("\t      —————————————————————————————————————");

    }

    
    private static void printOperatorSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Collect payments from customer.\n");
        System.out.println("\t     [2] - Print bill with meter code.\n");
        System.out.println("\t     [3] - View bills of specific region.\n");
        System.out.println("\t     [4] - Stop meter and cancel subscription for customer.");
        
    }

   
    private static void clearScreen() {

        try {

            Robot ro = new Robot();
            ro.setAutoWaitForIdle(true);
            ro.setAutoDelay(10);
            ro.keyPress(KeyEvent.VK_CONTROL);
            ro.keyPress(KeyEvent.VK_L);
            ro.keyRelease(KeyEvent.VK_L);
            System.out.print("\u001b[1K");
            ro.keyRelease(KeyEvent.VK_CONTROL);
            System.out.print("\u001b[2K");
            ro.delay(10);

        } catch (AWTException ex) {
            System.out.println(ex.toString());
        }

    }

    
    private String loginForm(String administratorID, String administratorPass) {

        do {

            if (loginValidator(administratorID, administratorPass)) {
                return getAdministratorRole(administratorID);
            } else {

                System.out.print("\n[+] Enter a valid administrator password: ");
                administratorPass = input.nextLine();

            }

        } while (true);

    }

    
    private String AdministratorID_Val(String administratorID) {

        do {

            if (isAdministratorIdExists(administratorID)) {
                return administratorID;
            } else {

                System.out.print("\n[+] Enter a valid administrator ID: ");
                administratorID = input.nextLine();

            }

        } while (true);

    }

    
    private String AdminID_Val(String adminID) {

        do {

            if (isAdministratorIdExists(adminID) && adminRole.equals(getAdministratorRole(adminID))) {
                return adminID;
            } else {

                System.out.print("\n[+] Enter a valid admin ID: ");
                adminID = input.nextLine();

            }

        } while (true);

    }
    
    
    private String OperatorID_Val(String OperatorID) {

        do {

            if (isAdministratorIdExists(OperatorID) && operatorRole.equals(getAdministratorRole(OperatorID))) {
                return OperatorID;
            } else {

                System.out.print("\n[+] Enter a valid operator ID: ");
                OperatorID = input.nextLine();

            }

        } while (true);

    }

    
    private String Customer_Val(String meterCode) {

        do {

            if (isMeterCodeExists(meterCode)) {
                return meterCode;
            } else {

                System.out.print("\n[+] Enter a valid meter code: ");
                meterCode = input.nextLine();

            }

        } while (true);

    }

    
    private void addUser() {
        
        do {
            
            System.out.println("\n\t     [1] - Add New Customer.\n");
            System.out.println("\t     [2] - Add New Operator.\n");
            System.out.println("\t     [3] - Add New Administrator.");

            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            
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
                    addNewAdministrator();
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n[+] Do you want to Add another User?(y/n): ");
            
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
        System.out.print("\n\t     [+] Enter Your Password : ");
        setAdministratorPass(input.nextLine());
        pushAllAdministratorInfoToDB();
        
    }

    
    private void addNewAdministrator() {
        
        runPersonDriver();
        setAdministratorID(getGovernmentCode(), getNationalIdNum());
        setAdministratorRole(adminRole);
        System.out.print("\n\t     [+] Enter Your Password : ");
        setAdministratorPass(input.nextLine());
        pushAllAdministratorInfoToDB();
        
    }

    
    private void deleteUser() {
        
        do {
            
            System.out.println("\n\t     [1] - Delete  Customer.\n");
            System.out.println("\t     [2] - Delete  Operator.\n");
            System.out.println("\t     [3] - Delete  Addminstrator.");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t      [+] Enter meter code: ");
                    deleteCustomer(Customer_Val(input.nextLine()));
                    break;
                case '2':
                    System.out.print("\n\t      [+] Enter Operator ID: ");
                    deleteOperator(OperatorID_Val(input.nextLine()));
                    break;
                case '3':
                    System.out.print("\n\t      [+] Enter Administrator ID: ");
                    deleteAdministrator(AdminID_Val(input.nextLine()));
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n[+] Do you want to Delete another User? (y/n): ");
            
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private void deleteCustomer(String meterCode) {
        
        deleteCustomerFromDB(meterCode);
        System.out.println("\n\t       [-] The Customer Deleted Successfully.");
        
    }

    
    private void deleteOperator(String operatorID) {
        
        deleteAdministratorFromDB(operatorID);
        System.out.println("\n\t       [-] The Operator Deleted Successfully.");

    }

    
    private void deleteAdministrator(String administratorID) {
        
        deleteAdministratorFromDB(administratorID);
        System.out.println("\n\t       [-] The Administrator Deleted Successfully.");
        
    }


    private void updateUser() {
        
        do {
            
            System.out.println("\n\t     [1] - Update Customer.\n");
            System.out.println("\t     [2] - Update Operator.\n");
            System.out.println("\t     [3] - Update Addminstrator.");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t      [+] Enter meter code: ");
                    viewCustomerDataBase(Customer_Val(input.nextLine()));
                    break;
                case '2':
                    System.out.print("\n\t      [+] Enter Operator ID: ");
                    viewOperatorDataBase(OperatorID_Val(input.nextLine()));
                    break;
                case '3':
                    System.out.print("\n\t      [+] Enter Administrator ID: ");
                    viewAdministratorDataBase(AdminID_Val(input.nextLine()));
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n[+] Do you want to Update another User?(y/n): ");
            
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private void viewCustomerDataBase(String meterCode) {
        
        do {
            
            System.out.println("\n[+] Select The Information You Want To Update In (" + getCustomerName(meterCode).split(" ")[0] + "'s) Information ");
            System.out.println("\n\t[-] Alert : You Can't Update Name, National Id, Birth Date, Administrator ID, Gender nor Contract Date");
            System.out.println("\n\t     [1] - Address.\n");
            System.out.println("\t     [2] - Email.\n");
            System.out.println("\t     [3] - GovernmentCode.\n");
            System.out.println("\t     [4] - Phone Number.\n");
            System.out.println("\t     [5] - Type Of Use.");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t      [+] Enter new address: ");
                    updateCustomer(addressColumn, Address_Val(input.nextLine()), meterCode);
                    break;
                case '2':
                    System.out.print("\n\t      [+] Enter new email: ");
                    updateCustomer(emailColumn, Email_Val(input.nextLine()), meterCode);
                    break;
                case '3':
                    System.out.print("\n\t      [+] Enter new government code: ");
                    updateCustomer(governmentCodeColumn, GovernmentCode_Val(input.nextLine()), meterCode);
                    break;
                case '4':
                    System.out.print("\n\t      [+] Enter new phone number: ");
                    updateCustomer(phoneNumColumn, PhoneNumber_Val(input.nextLine()), meterCode);
                    break;
                case '5':
                    NewCustomerDriver newCustomerDriver = new NewCustomerDriver();
                    System.out.print("\n\t      [+] Enter new type of use(0 for Home use, 1 for Commerical use): ");
                    updateCustomer(typeOfUseColumn, newCustomerDriver.TypeOfUse_Val(input.next().charAt(0)), meterCode);
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+] Do you want to Update another Information in (" + getCustomerName(meterCode).split(" ")[0] + "'s) Informations?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    
    private void viewOperatorDataBase(String operatorID) {
        
        do {
            
            System.out.println("\n[+] Select The Information You Want To Update In (" + getAdministratorName(operatorID).split(" ")[0] + "'s) Information ");
            System.out.println("\n[-] Alert : You Can't Update Name, National Id, Birth Date, Administrator ID, Gender nor Contract Date");
            System.out.println("\n\t     [1] - Address.\n");
            System.out.println("\t     [2] - Email.\n");
            System.out.println("\t     [3] - Phone Number.\n");
            System.out.println("\t     [4] - Password.\n");
            System.out.println("\t     [5] - Administrator Role.");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            
            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t      [+] Enter new address: ");
                    updateOperator(addressColumn, Address_Val(input.nextLine()), operatorID);
                    break;
                case '2':
                    System.out.print("\n\t      [+] Enter new email: ");
                    updateOperator(emailColumn, Email_Val(input.nextLine()), operatorID);
                    break;
                case '3':
                    System.out.print("\n\t      [+] Enter new phone number: ");
                    updateOperator(phoneNumColumn, PhoneNumber_Val(input.nextLine()), operatorID);
                    break;
                case '4':
                    System.out.print("\n\t      [+] Enter new password: ");
                    updateOperator(administratorPassColumn, input.nextLine(), operatorID);
                    break;
                case '5':
                    System.out.print("\n\t      [+] Enter new Administrator Role:");
                    String value = input.next(); //// -----------------------------------------------------------------------
                    updateOperator(administratorRoleColumn, value, operatorID);
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n[+] Do you want to Update another Information in (" + getAdministratorName(operatorID).split(" ")[0] + "'s) Informations ?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    private void viewAdministratorDataBase(String administratorID) {

        do {
            
            System.out.println("\n[+] Select The Information You Want To Update In (" + getAdministratorName(administratorID).split(" ")[0] + "'s) Information ");
            System.out.println("\n\t[-] Alert : You Can't Update Name, National Id, Birth Date, Administrator ID, Gender nor Contract Date");
            System.out.println("\n\t     [1] - Address.\n");
            System.out.println("\t     [2] - Email.\n");
            System.out.println("\t     [3] - Phone Number.\n");
            System.out.println("\t     [4] - Password.\n");
            System.out.println("\t     [5] - Administrator Role.");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");

            choice = input.next().charAt(0);
            input.nextLine();
            
            switch (choice) {
                
                case '1':
                    System.out.print("\n\t      [+] Enter new address: ");
                    updateAdministrator(addressColumn, Address_Val(input.nextLine()), administratorID);
                    break;
                case '2':
                    System.out.print("\n\t      [+] Enter new email: ");
                    updateAdministrator(emailColumn, Email_Val(input.nextLine()), administratorID);
                    break;
                case '3':
                    System.out.print("\n\t      [+] Enter new phone number: ");
                    updateAdministrator(phoneNumColumn, PhoneNumber_Val(input.nextLine()), administratorID);
                    break;
                case '4':
                    System.out.print("\n\t      [+] Enter new password: ");
                    updateAdministrator(administratorPassColumn, input.nextLine(), administratorID);
                    break;
                case '5':
                    System.out.print("\n\t      [+] Enter new Administrator Role: ");
                    String value = input.next(); //// =====================================================
                    updateAdministrator(administratorRoleColumn, value, administratorID);
                    break;
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
                    
            }

            System.out.print("\n[+] Do you want to Update another Information in (" + getAdministratorName(administratorID).split(" ")[0] + "'s) Informations ?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

    private void updateCustomer(final String columnName, String value, String meterCode) {

        pushCustomerUpdateToDB(columnName, value, meterCode);
        System.out.println("\n\t       [-] The Customer Updated Successfully.");

    }

    private void updateOperator(final String columnName, String value, String operatorID) {
        
        pushAdministratorUpdateToDB(columnName, value, operatorID);
        System.out.println("\n\t       [-] The Operator Updated Successfully.");
        
    }

    private void updateAdministrator(final String columnName, String value, String administratorID) {
        
        pushAdministratorUpdateToDB(columnName, value, administratorID);
        System.out.println("\n\t       [-] The Administrator Updated Successfully.");
        
    }

    
    private void viewTotalCollected() {
        
        System.out.println("\n      [-] The Total Collected is: " + getTotalCollectedFromDB() + "  L.E");
        
    }

    
    private void consumptionStatForSpecificRegion() {

        System.out.println("\n-------------------------------------------------------------------------------------------------");
        
        for (int i = 0; i < 22; i += 3) {
            
            System.out.printf("| %20s -> %5s |", governmentCodes[i][1], governmentCodes[i][0]);
            System.out.printf(" %20s -> %5s ", governmentCodes[i + 1][1], governmentCodes[i + 1][0]);
            System.out.printf("| %20s -> %5s |\n", governmentCodes[i + 2][1], governmentCodes[i + 2][0]);

        }
        
        System.out.printf("| %20s -> %5s |", governmentCodes[24][1], governmentCodes[24][0]);
        System.out.printf(" %20s -> %5s ", governmentCodes[25][1], governmentCodes[25][0]);
        System.out.printf("| %29s |", "");

        System.out.println("\n-------------------------------------------------------------------------------------------------");
        
        String governmentCode;
        
        do {
            
            boolean Exists = false;
            
            do {
                
                System.out.print("\n\t[+] Enter the government code of the region that you want to make consumption statistics for: ");
                governmentCode = input.next();
                
                for (int i = 0; i < 26; i++)
                    if (governmentCode.equals(governmentCodes[i][0])) {
                        Exists = true;
                        break;
                    }
                
                if (!Exists)
                    System.out.println("\n[+] Enter a valid Government Code.");

            } while (!Exists);
            
            String[] statisticsData = consumptionStatForSpecificRegionFromDB(governmentCode);
            
            System.out.println("\n\t\t [-] The Summation Of Consumptions: " + statisticsData[0]);
            System.out.println("\n\t\t [-] The Actual Number Of Consumers: " + statisticsData[1]);
            System.out.println("\n\t\t [-] The Average consumption For: " + statisticsData[2]);

            System.out.print("\n[+]Do you want to View Consumption Of another region?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }

}
