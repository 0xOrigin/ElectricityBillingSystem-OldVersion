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
            System.out.print("\u001b[0K");
            char choice = input.next().charAt(0);
            
            switch (choice) {
                case '1':
                    NewCustomerDriver newCustomer = new NewCustomerDriver();
                    newCustomer.runDashboard();
                    returnedToMain();
                    break;
                case '2':
                    OldCustomerDriver oldCustomer = new OldCustomerDriver();
                    oldCustomer.runDashboard();
                    returnedToMain();
                    break;
                case '3':
                    AdministratorDriver administratorDriver = new AdministratorDriver();
                    administratorDriver.runDashboard();
                    returnedToMain();
                    break;
                case '0':
                    System.out.println("\n[-] The program has been successfully terminated.");
                    return;
                default:
                    System.out.println("\n[-] Enter a valid choice.");
                    break;
            }

        } while (true);

    }
    
    public static void returnedToMain(){
        
        clearScreen();
        printBanner();
        printDashboards();
        System.out.flush();
        
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