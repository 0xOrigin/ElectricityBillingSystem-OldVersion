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
    
    protected char qContinue, choice;
    Scanner input = new Scanner(System.in);
    

    public void runDashboard() {

        System.out.print("\n\t[+] Enter administrator ID: ");
        administratorID = AdministratorID_Val(input.nextLine());

        System.out.print("\n\t[+] Enter administrator password: ");
        administratorPass = input.nextLine();

        String choiceDashboard = loginForm(administratorID, administratorPass);

        if (choiceDashboard.equals(adminRole)) {
            Admin admin = new Admin();
            admin.runAdminDashboard();
        } else {
            Operator operator = new Operator();
            operator.runOperatorDashboard();
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

    
    protected String AdministratorID_Val(String administratorID) {

        do {

            if (isAdministratorIdExists(administratorID)) {
                return administratorID;
            } else {

                System.out.print("\n[+] Enter a valid administrator ID: ");
                administratorID = input.nextLine();

            }

        } while (true);

    }

    
    protected String AdminID_Val(String adminID) {

        do {

            if (isAdministratorIdExists(adminID) && adminRole.equals(getAdministratorRole(adminID))) {
                return adminID;
            } else {

                System.out.print("\n[+] Enter a valid admin ID: ");
                adminID = input.nextLine();

            }

        } while (true);

    }
    
    
    protected String OperatorID_Val(String OperatorID) {

        do {

            if (isAdministratorIdExists(OperatorID) && operatorRole.equals(getAdministratorRole(OperatorID))) {
                return OperatorID;
            } else {

                System.out.print("\n[+] Enter a valid operator ID: ");
                OperatorID = input.nextLine();

            }

        } while (true);

    }

    
    protected String Customer_Val(String meterCode) {

        do {

            if (isMeterCodeExists(meterCode)) {
                return meterCode;
            } else {

                System.out.print("\n[+] Enter a valid meter code: ");
                meterCode = input.nextLine();

            }

        } while (true);

    }

    
    protected static void clearScreen() {

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
    
}