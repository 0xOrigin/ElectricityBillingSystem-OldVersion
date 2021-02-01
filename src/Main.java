import NewCustomer.NewCustomerDriver;
import OldCustomer.OldCustomerDriver;
import Administrator.AdministratorDriver;
import java.util.Scanner;
import Util.Display;

public class Main {

    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);
        
        Display.printProgramNameBanner();
        Display.printMainDashboards();
        
        do {
            
            System.out.print("\n[+] Choose a number(0 to terminate the program): ");
            char choice = input.next().charAt(0);
            input.nextLine();
            
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
        
        Display.clearScreen();
        Display.printProgramNameBanner();
        Display.printMainDashboards();
        
    }
    
}