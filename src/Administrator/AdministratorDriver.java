package Administrator;

import Datebase.*;
import NewCustomer.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.*;

public class AdministratorDriver extends Administrator {

    private String administratorID;
    private String administratorPass;
    private String operatorID;
    private char qContinue, choice;
    Scanner input = new Scanner(System.in);
    private String meterCode;

    public void runDashboard() {

        System.out.print("\n\t[+] Enter administrator ID: ");
        administratorID = input.nextLine();
        administratorID = AdministratorID_Val(administratorID);

        System.out.print("\n\t[+] Enter administrator password: ");
        administratorPass = input.nextLine();

        String choiceDashboard = loginForm(administratorID, administratorPass);

        if (choiceDashboard.equals("Administrator")) {
            runAdminDashboard();
        } else {
            runOperatorDashboard();
        }

    }

    /*
        -------------------- Administrator DashBoard --------------------
        -------------------- Administrator DashBoard --------------------
        -------------------- Administrator DashBoard --------------------
        -------------------- Administrator DashBoard --------------------
        -------------------- Administrator DashBoard --------------------
    
     */
    private void runAdminDashboard() {

        do {

            viewAdminDashboard();

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
                    case '4':
                        viewTotalCollected();
                        break;
                    case '5':
                        //view bills of specific region
                        //view bills of specific region
                        //view bills of specific region
                        //view bills of specific region
                        //view bills of specific region
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
            System.out.flush();
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }

    private static void viewAdminDashboard() {

        clearScreen();
        printAdminBanner();
        printAdminSelections();
        System.out.flush();

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
        System.out.println("\n\t     [1] - Add User ");
        System.out.println("\t     [2] - Delete User ");
        System.out.println("\t     [3] - Update User ");
        System.out.println("\t     [4] - View Total Collected ");
        System.out.println("\t     [5] - View All Bills Of Specific Regions. ");
        System.out.println("\t     [6] - Make Consumption Statistics For Specific Regions. ");

    }

    /*
        -------------------- End Of Administrator DashBoard --------------------
        -------------------- End Of Administrator DashBoard --------------------
        -------------------- End Of Administrator DashBoard --------------------
        -------------------- End Of Administrator DashBoard --------------------
        -------------------- End Of Administrator DashBoard --------------------
     */
 /*-------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------*/
 /*
        -------------------- Operator DashBoard --------------------
        -------------------- Operator DashBoard --------------------
        -------------------- Operator DashBoard --------------------
        -------------------- Operator DashBoard --------------------
        -------------------- Operator DashBoard --------------------
    
     */
    private void runOperatorDashboard() {

        do {

            viewOperatorDashboard();

            do {

                System.out.print("\n[+] Choose a number(0 to return to main menu): ");
                System.out.flush();
                System.out.print("\u001b[0K");
                choice = input.next().charAt(0);

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
            System.out.flush();
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }

    private static void viewOperatorDashboard() {

        clearScreen();
        printOperatorBanner();
        printOperatorSelections();
        System.out.flush();

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

    /*
        -------------------- End Of Operator DashBoard --------------------
        -------------------- End Of Operator DashBoard --------------------
        -------------------- End Of Operator DashBoard --------------------
        -------------------- End Of Operator DashBoard --------------------
        -------------------- End Of Operator DashBoard --------------------
    
     */
 /*-------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------*/
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

    /*-------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------*/

 /*
        -------------------- Administrator Code --------------------
        -------------------- Administrator Code --------------------
        -------------------- Administrator Code --------------------
        -------------------- Administrator Code --------------------
        -------------------- Administrator Code --------------------
     */
    private String AdministratorID_Val(String administratorID) {

        do {

            if (isAdministratorIdExists(administratorID) && "Administrator".equals(AdministratorDatabase.getAdministratorRole(administratorID))) {
                return administratorID;
            } else {

                System.out.print("\n[+] Enter a valid administrator ID: ");
                administratorID = input.nextLine();

            }

        } while (true);

    }

    private String OperatorID_Val(String OperatorID) {

        do {

            if (isAdministratorIdExists(OperatorID) && "Operator".equals(AdministratorDatabase.getAdministratorRole(OperatorID))) {
                return OperatorID;
            } else {

                System.out.print("\n[+] Enter a valid operator ID: ");
                OperatorID = input.nextLine();

            }

        } while (true);

    }

    private String Customer_Val(String meterCode) {

        do {

            if (NewCustomerDatabase.isMeterCodeExists(meterCode) == true) {
                return meterCode;
            } else {

                System.out.print("\n[+] Enter a valid Meter Code: ");
                meterCode = input.nextLine();

            }

        } while (true);

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
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+] Do you want to Add another User? (y/n): ");
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
        setAdministratorID(getGovernmentCode(), getNationalIdNum());
        setAdministratorRole("Operator");
        System.out.print("\n\t     [+] Enter Your Password : ");
        String password = input.next();
        setAdministratorPass(password);
        pushAllNewAdministratorInfoToDB();
    }

    private void addNewAdministrator() {
        runPersonDriver();
        setAdministratorID(getGovernmentCode(), getNationalIdNum());
        setAdministratorRole("Administrator");
        System.out.print("\n\t     [+] Enter Your Password : ");
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
                case '0':
                    return;
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
        this.meterCode = Customer_Val(meterCode);
        AdministratorDatabase.deleteCustomer(this.meterCode);
        System.out.println("\n\t       The Customer Deleted Successfully.");
    }

    public void deleteOperator(String operatorID) {
        this.operatorID = OperatorID_Val(operatorID);
        AdministratorDatabase.deleteAdministrator(this.operatorID);
        System.out.println("\n\t       The Operator Deleted Successfully.");

    }

    public void deleteAdministrator(String administratorID) {
        this.administratorID = AdministratorID_Val(administratorID);
        AdministratorDatabase.deleteAdministrator(this.administratorID);
        System.out.println("\n\t       The Administrator Deleted Successfully.");
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
                    String meterCode;
                    System.out.print("\n\t      Enter Meter Code: ");
                    meterCode = input.next();
                    this.meterCode = Customer_Val(meterCode);
                    viewCustomerDataBase(this.meterCode);
                    break;
                }
                case '2': {
                    String operatorID;
                    System.out.print("\n\t      Enter Operator ID: ");
                    operatorID = input.next();
                    this.operatorID = OperatorID_Val(operatorID);
                    viewOperatorDataBase(this.operatorID);
                    break;
                }

                case '3': {
                    String administratorID;
                    System.out.print("\n\t      Enter Administrator ID: ");
                    administratorID = input.next();
                    this.administratorID = AdministratorID_Val(administratorID);
                    viewAdministratorDataBase(this.administratorID);
                    break;
                }
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+]Do you want to Update another User? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }

    public void viewCustomerDataBase(String meterCode) {
        char choice_2, q2Continue;
        do {
            System.out.println("\n[+]Select The Information You Want To Update In (" + NewCustomerDatabase.getName(meterCode) + "'s) Information ");
            System.out.println("\nAlert : You Can't Update Name, National Id, Birth Date, Administrator ID, Gender nor Contract Date");
            System.out.println("\n\t     [1] - Address");
            System.out.println("\t     [2] - Email");
            System.out.println("\t     [3] - GovernmentCode");
            System.out.println("\t     [4] - Phone Number");
            System.out.println("\t     [5] - Type Of Use");
            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            System.out.flush();
            System.out.print("\u001b[0K");
            choice_2 = input.next().charAt(0);
            switch (choice_2) {
                case '1': {
                    System.out.print("\n\t      Enter The New Address: ");
                    String value = input.next();
                    updateCustomer("Address", value, meterCode);
                    break;
                }
                case '2': {
                    System.out.print("\n\t       Enter The New Email: ");
                    String value = input.next();
                    updateCustomer("Email", value, meterCode);
                    break;
                }
                case '3': {
                    System.out.print("\n\t      Enter The New Government Code: ");
                    String value = input.next();
                    updateCustomer("GovernmentCode", value, meterCode);
                    break;
                }
                case '4': {
                    System.out.print("\n\t      Enter The New Phone Number: ");
                    String value = input.next();
                    updateCustomer("Phone Number", value, meterCode);
                    break;
                }
                case '5': {
                    System.out.print("\n\t      Enter The New Type Of Use: ");
                    String value = input.next();
                    updateCustomer("TypeOfUse", value, meterCode);
                    break;
                }
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+]Do you want to Update another Information in ( " + NewCustomerDatabase.getName(meterCode) + "'s ) Informations ? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }

    public void viewOperatorDataBase(String operatorID) {
        char choice_2, q2Continue;
        do {
            System.out.println("\n[+]Select The Information You Want To Update In (" + AdministratorDatabase.getAdministratorName(operatorID) + "'s) Information ");
            System.out.println("\nAlert : You Can't Update Name, National Id, Birth Date, Administrator ID, Gender nor Contract Date");
            System.out.println("\n\t     [1] - Address");
            System.out.println("\t     [2] - Email");
            System.out.println("\t     [3] - Phone Number");
            System.out.println("\t     [4] - Password");
            System.out.println("\t     [5] - Administrator Role");

            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            System.out.flush();
            System.out.print("\u001b[0K");
            choice_2 = input.next().charAt(0);
            switch (choice_2) {
                case '1': {
                    System.out.print("\n\t      Enter The New Address: ");
                    String value = input.next();
                    updateAdministrator("Address", value, operatorID);
                    break;
                }
                case '2': {
                    System.out.print("\n\t       Enter The New Email: ");
                    String value = input.next();
                    updateAdministrator("Email", value, operatorID);
                    break;
                }
                case '3': {
                    System.out.print("\n\t      Enter The New Phone Number: ");
                    String value = input.next();
                    updateAdministrator("PhoneNumber", value, operatorID);
                    break;
                }
                case '4': {
                    System.out.print("\n\t      Enter The New Password: ");
                    String value = input.next();
                    updateAdministrator("AdministratorPass", value, operatorID);
                    break;
                }
                case '5': {
                    System.out.print("\n\t      Enter The New Administrator Role: ");
                    String value = input.next();
                    updateAdministrator("AdministratorRole", value, operatorID);
                    break;
                }
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+]Do you want to Update another Information in ( " + AdministratorDatabase.getAdministratorName(operatorID) + "'s ) Informations ? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }

    public void viewAdministratorDataBase(String administratorID) {
        char choice_2, q2Continue;
        do {
            System.out.println("\n[+]Select The Information You Want To Update In (" + AdministratorDatabase.getAdministratorName(administratorID) + "'s) Information ");
            System.out.println("\nAlert : You Can't Update Name, National Id, Birth Date, Administrator ID, Gender nor Contract Date");
            System.out.println("\n\t     [1] - Address");
            System.out.println("\t     [2] - Email");
            System.out.println("\t     [3] - Phone Number");
            System.out.println("\t     [4] - Password");
            System.out.println("\t     [5] - Administrator Role");

            System.out.print("\n[+] Choose a number(0 to return to main menu): ");
            System.out.flush();
            System.out.print("\u001b[0K");
            choice_2 = input.next().charAt(0);
            switch (choice_2) {
                case '1': {
                    System.out.print("\n\t      Enter The New Address: ");
                    String value = input.next();
                    updateAdministrator("Address", value, administratorID);
                    break;
                }
                case '2': {
                    System.out.print("\n\t       Enter The New Email: ");
                    String value = input.next();
                    updateAdministrator("Email", value, administratorID);
                    break;
                }
                case '3': {
                    System.out.print("\n\t      Enter The New Phone Number: ");
                    String value = input.next();
                    updateAdministrator("PhoneNumber", value, administratorID);
                    break;
                }
                case '4': {
                    System.out.print("\n\t      Enter The New Password: ");
                    String value = input.next();
                    updateAdministrator("AdministratorPass", value, administratorID);
                    break;
                }
                case '5': {
                    System.out.print("\n\t      Enter The New Administrator Role: ");
                    String value = input.next();
                    updateAdministrator("AdministratorRole", value, administratorID);
                    break;
                }
                case '0':
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

            System.out.print("\n[+]Do you want to Update another Information in ( " + AdministratorDatabase.getAdministratorName(administratorID) + "'s ) Informations ? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }

    public void updateCustomer(final String columnName, String value, String meterCode) {
        this.meterCode = Customer_Val(meterCode);
        AdministratorDatabase.updateCustomer(columnName, value, this.meterCode);
        System.out.println("\n\t       The Customer Updated Successfully.");

    }

    public void updateOperator(final String columnName, String value, String operatorID) {
        this.operatorID = OperatorID_Val(operatorID);
        AdministratorDatabase.updateAdministrator(columnName, value, this.operatorID);
        System.out.println("\n\t       [-] The Operator Updated Successfully.");
    }

    public void updateAdministrator(final String columnName, String value, String administratorID) {
        this.administratorID = OperatorID_Val(administratorID);
        AdministratorDatabase.updateAdministrator(columnName, value, this.administratorID);
        System.out.println("\n\t       The Administrator Updated Successfully.");
    }

    /**
     ****************** END OF Update USER****************
     */
    /**
     ****************** View Total Collected ****************
     */
    public void viewTotalCollected() {

        System.out.println("\n      The Total Collected : " + AdministratorDatabase.viewTotalCollected());
    }

    /**
     ****************** END OF View Total Collected ****************
     */
    private void consumptionStatForSpecificRegion() {
        char q2Continue;
        System.out.println("\n-------------------------------------------------------------------------------------------------");
        String[][] governmentCodes = {{"02", "Cairo"}, {"013", "Qalyubia"}, {"03", "Alexandria"}, {"040", "Gharbia"}, {"048", "Monufia"}, {"055", "Sharqia"}, {"062", "Suez"}, {"064", "Ismailia"},
        {"046", "Matruh"}, {"066", "Port Said"}, {"068", "North Sinai"}, {"069", "South Sinai"}, {"050", "Dakahlia"}, {"045", "Beheira"}, {"057", "Damietta"}, {"047", "Kafr El Sheikh"},
        {"092", "New Valley"}, {"065", "Red Sea"}, {"084", "Faiyum"}, {"082", "Beni Suef"}, {"086", "Minya"}, {"088", "Asyut"}, {"093", "Sohag"}, {"095", "Luxor"},
        {"096", "Qena"}, {"097", "Aswan"}};
        for (int i = 0; i < 22; i += 3) {
            System.out.printf("| %20s -> %5s |", governmentCodes[i][1], governmentCodes[i][0]);
            System.out.printf(" %20s -> %5s ", governmentCodes[i + 1][1], governmentCodes[i + 1][0]);
            System.out.printf("| %20s -> %5s |\n", governmentCodes[i + 2][1], governmentCodes[i + 2][0]);

        }
        System.out.printf("| %20s -> %5s |", governmentCodes[24][1], governmentCodes[24][0]);
        System.out.printf(" %20s -> %5s ", governmentCodes[25][1], governmentCodes[25][0]);
        System.out.printf("| %29s |", "");

        System.out.println("\n-------------------------------------------------------------------------------------------------");
        String GovernmentCode;
        do {
            boolean Exists = false;
            do {
                System.out.print("\n\t[+]Enter The Government Code Of The Regoin That You Want To Mack Consumption Statistics For: ");
                GovernmentCode = input.next();
                for (int i = 0; i < 26; i++) {
                    if (GovernmentCode.equals(governmentCodes[i][0])) {
                        Exists = true;
                        break;
                    }
                }
                if (!Exists) {
                    System.out.println("\n[+] Enter a valid Government Code.");
                }
            } while (!Exists);
            String[] statisticsData;
            statisticsData = AdministratorDatabase.consumptionStatForSpecificRegion(GovernmentCode);
            System.out.println("\n\t\t [-] The Summation Of Consumptions : " + statisticsData[0]);
            System.out.println("\n\t\t [-] The Actual Number Of Consumers : " + statisticsData[1]);
            System.out.println("\n\t\t [-] The Average consumption For " + ": " + statisticsData[2]);

            System.out.print("\n[+]Do you want to View Consumption Of another Region ? (y/n): ");
            System.out.flush();
            q2Continue = input.next().charAt(0);
        } while (q2Continue == 'Y' || q2Continue == 'y');
    }
    /*
        -------------------- End Of Administrator Code --------------------
        -------------------- End Of Administrator Code --------------------
        -------------------- End Of Administrator Code --------------------
        -------------------- End Of Administrator Code --------------------
        -------------------- End Of Administrator Code --------------------
     */
 /*-------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------
    -------------------------------------------------------------------*/

}
