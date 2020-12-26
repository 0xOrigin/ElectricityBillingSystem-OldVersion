import NewCustomer.NewCustomerDriver;
import OldCustomer.OldCustomerDriver;
import Administrator.AdministratorDriver;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.*;
import Datebase.OldCustomerDatabase;

        
public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        
        printBanner();
        printDashboards();
        
        do {

            System.out.print("\n[+] Choose a number(0 to terminate the program): ");
            System.out.flush();
            char choice = input.next().charAt(0);

            switch (choice) {
                case '1':
                    NewCustomerDriver newCustomer = new NewCustomerDriver();
                    newCustomer.runDashboardNC();
                    returnedToMain();
                    break;
                case '2':

                    returnedToMain();
                    break;
                case '3':

                    returnedToMain();
                    break;
                case '0':
                    System.out.println("\n[-] The program has been successfully terminated.");
                    System.exit(0);
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

        } while (true);

    }
    
    public static void returnedToMain(){
        
        clearScreen();
        System.out.flush();
        printBanner();
        printDashboards();
        
    }
    
    
    public static void printBanner(){
        
        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————");
        
    }

    public static void printDashboards(){
        
        System.out.println("\n[-] Are you _____________________ ?\n");
        System.out.println("\t     [1] - New Customer\n");
        System.out.println("\t     [2] - Old Customer\n");
        System.out.println("\t     [3] - Administrator");
        
    }
    
    
    public static void clearScreen(){
       
        try {
            
            Robot ro = new Robot();
            ro.keyPress(KeyEvent.VK_CONTROL);
            ro.waitForIdle();
            ro.keyPress(KeyEvent.VK_L);
            ro.waitForIdle();
            ro.keyRelease(KeyEvent.VK_L);
            ro.waitForIdle();
            ro.keyRelease(KeyEvent.VK_CONTROL);
            ro.waitForIdle();
           
        } catch (AWTException ex) {
            System.out.println(ex.toString());
        }
         
    }
    
}