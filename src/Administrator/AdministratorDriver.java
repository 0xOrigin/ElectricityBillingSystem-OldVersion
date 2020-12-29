package Administrator;

import Datebase.*;
import Person.*;
import NewCustomer.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.*;

public class AdministratorDriver extends Administrator {

    Scanner input = new Scanner(System.in);

    public void runDashboard() {
        char qContinue;

        do {

            char choice;
            viewDashboard();

            do {

                System.out.print("\n[+] Choose a number(0 to return to main menu): ");
                System.out.flush();
                System.out.print("\u001b[0K");
                choice = input.next().charAt(0);
                switch (choice) {
                    /*case [1] Add User -> (Operator || Customer || Administrator)? */
                    case '1':
                        addUser();
                        break;

                    /*case [2] Delete User-> (Operator || Customer || Administrator)? */
                    case '2':
                        deleteUser();
                        break;
                    /*case [3] Update User-> (Operator || Customer || Administrator)? */
                    case '3':
                        updateUser();
                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        break;
                }

            } while (choice != '1');

            System.out.print("\n[+]Do you want to perform any additional operation in this dashboard? (y/n): ");
            System.out.flush();
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }

    private static void viewDashboard() {

        clearScreen();
        printBanner();
        printSelections();
        System.out.flush();

    }

    private static void printBanner() {

        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        System.out.println("\t     [-] Welcome in Administration Dashboard [-]");
        System.out.println("\t    ———————————————————————————————————————————");

    }

    private static void printSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\n\t     [1] - Add User ");
        System.out.println("\t     [2] - Delete User ");
        System.out.println("\t     [3] - Update User ");
        System.out.println("\t     [4] - View Total Collected ");
        System.out.println("\t     [5] - View All Bills Of Specific Regions. ");

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

    /**
     ************************ADD USER****************
     */
    private void addUser() {
        char choice_2, q2Continue;
        do {
            System.out.println("\n\t     [1] - Add New Customer ");
            System.out.println("\t     [2] - Add New Operator ");
            System.out.println("\t     [3] - Add New Administrator ");

            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            System.out.flush();
            System.out.print("\u001b[0K");
            choice_2 = input.next().charAt(0);
            switch (choice_2) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    addNewOperator();
                    break;
                case '3':
                    addNewAdministrator();
                    break;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+]Do you want to Add another User? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }

    private void addNewCustomer() {
        NewCustomerDriver newCustomer = new NewCustomerDriver();
        newCustomer.fillNewContract();
    }

    private void addNewOperator() {
        runPersonDriver();
        setAdministratorID(getGovernmentCode() , getNationalIdNum());
        setAdministratorRole("Operator");
        String password = input.next();
        setAdministratorPass(password);
        pushAllNewAdministratorInfoToDB();
    }

    private void addNewAdministrator() {
        runPersonDriver();
        setAdministratorID(getGovernmentCode() , getNationalIdNum());
        setAdministratorRole("Administrator");
        String password = input.next();
        setAdministratorPass(password);
        pushAllNewAdministratorInfoToDB();
    }

    /**
     ******************END OF ADD USER****************
     */
    /**
     **********************Delete********************
     */
    private void deleteUser() {
        char choice_2, q2Continue;
        String administratorID, meterCode, operatorID;
        do {
            System.out.println("\n\t     [1] - Delete  Customer");
            System.out.println("\t     [2] - Delete  Operator");
            System.out.println("\t     [3] - Delete  Addminstrator");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            System.out.flush();
            System.out.print("\u001b[0K");
            choice_2 = input.next().charAt(0);
            switch (choice_2) {
                case '1': {
                    System.out.print("\n\t      Enter Meter Code: ");
                    meterCode = input.next();
                    deleteCustomer(meterCode);
                    break;
                }
                case '2': {
                    System.out.print("\n\t      Enter Operator ID: ");
                    operatorID = input.next();
                    deleteOperator(operatorID);
                    break;
                }
                case '3': {
                    System.out.print("\n\t      Enter Administrator ID: ");
                    administratorID = input.next();
                    deleteAdministrator(administratorID);
                    break;
                }
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+]Do you want to Delete another User? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }

    public void deleteCustomer(String meterCode) {
        if (NewCustomerDatabase.isMeterCodeExists(meterCode) == true) {
            AdministratorDatabase.deleteCustomer(meterCode);
            System.out.println("\n\t       The Customer Deleted Successfully.");
        } else {
            System.out.println("\n\t      The Meter Code \"" + meterCode + "\" Does Not Exists");
        }
    }

    public void deleteOperator(String operatorID) {
        if (AdministratorDatabase.isAdministratorIdExists(operatorID) == true && "Operator".equals(AdministratorDatabase.getAdministratorRole(operatorID))) {
            AdministratorDatabase.deleteAdministrator(operatorID);
            System.out.println("\n\t       The Operator Deleted Successfully.");
        } else {
            System.out.println("\n\t      The Operator \"" + operatorID + "\" Does Not Exists");
        }
    }

    public void deleteAdministrator(String administratorID) {
        if (AdministratorDatabase.isAdministratorIdExists(administratorID) == true && "Administrator".equals(AdministratorDatabase.getAdministratorRole(administratorID))) {
            AdministratorDatabase.deleteAdministrator(administratorID);
            System.out.println("\n\t       The Administrator Deleted Successfully.");
        } else {
            System.out.println("\n\t      The Administrator \"" + administratorID + "\" Does Not Exists");
        }
    }

    /**
     ******************END OF Delete USER****************
     */
    /**
     * ***************Update User Info*********************
     */
    private void updateUser() {
        char choice_2, q2Continue;
        do {
            System.out.println("\n\t     [1] - Update Customer ");
            System.out.println("\t     [2] - Update Operator ");
            System.out.println("\t     [3] - Update Addminstrator ");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            System.out.flush();
            System.out.print("\u001b[0K");
            choice_2 = input.next().charAt(0);
            switch (choice_2) {
                case '1': {
                    String meterCode, value;
                    final String columnName;

                    System.out.print("\n\t      Enter Meter Code: ");
                    meterCode = input.next();
                    System.out.print("\n\t      Enter Value: ");
                    value = input.next();
                    System.out.print("\n\t      Enter Column Name: ");
                    columnName = input.next();
                    updateCustomer(columnName, value, meterCode);
                    break;
                }
                case '2': {
                    String operatorID, value;
                    final String columnName;

                    System.out.print("\n\t      Enter Operator ID: ");
                    operatorID = input.next();
                    System.out.print("\n\t      Enter Value: ");
                    value = input.next();
                    System.out.print("\n\t      Enter Column Name: ");
                    columnName = input.next();
                    updateOperator(columnName, value, operatorID);
                    break;
                }

                case '3': {
                    String administratorID, value;
                    final String columnName;

                    System.out.print("\n\t      Enter Administrator ID: ");
                    administratorID = input.next();
                    System.out.print("\n\t      Enter Value: ");
                    value = input.next();
                    System.out.print("\n\t      Enter Column Name: ");
                    columnName = input.next();
                    updateAdministrator(columnName, value, administratorID);
                    break;
                }

                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+]Do you want to Add another User? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }

    public void updateCustomer(final String columnName, String value, String meterCode) {
        if (NewCustomerDatabase.isMeterCodeExists(meterCode) == true) {
            AdministratorDatabase.updateCustomer(columnName, value, meterCode);
            System.out.println("\n\t       The Customer Updated Successfully.");
        } else {
            System.out.println("\n\t      The Meter Code \"" + meterCode + "\" Does Not Exists");
        }
    }

    public void updateOperator(final String columnName, String value, String operatorID) {
        if (AdministratorDatabase.isAdministratorIdExists(operatorID) == true && "Operator".equals(AdministratorDatabase.getAdministratorRole(operatorID))) {
            AdministratorDatabase.updateAdministrator(columnName, value, operatorID);
            System.out.println("\n\t       The Operator Updated Successfully.");
        } else {
            System.out.println("\n\t      The Operator \"" + operatorID + "\" Does Not Exists");
        }
    }

    public void updateAdministrator(final String columnName, String value, String administratorID) {
        if (AdministratorDatabase.isAdministratorIdExists(administratorID) == true && "Administrator".equals(AdministratorDatabase.getAdministratorRole(administratorID))) {
            AdministratorDatabase.updateAdministrator(columnName, value, administratorID);
            System.out.println("\n\t       The Administrator Updated Successfully.");
        } else {
            System.out.println("\n\t      The Administrator \"" + administratorID + "\" Does Not Exists");
        }
    }
    /**
     ******************END OF Update USER****************
     */

}