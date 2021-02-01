package Util;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Display {
    
    public static void printProgramNameBanner(){
        
        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        
    }
    
    public static void printMainDashboards(){
        
        System.out.println("\n[-] Are you _____________________ ?\n");
        System.out.println("\t     [1] - New Customer\n");
        System.out.println("\t     [2] - Old Customer\n");
        System.out.println("\t     [3] - Administrator");
        
    }
    
    public static void printNewCustomerDashboardBanner(){
        
        System.out.println("\t     [-] Welcome in New Customer Dashboard [-]");
        System.out.println("\t    ———————————————————————————————————————————");
        
    }
    
    public static void printNewCustomerSelections(){
        
        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Fill new contract.");
        
    }
    
    public static void printOldCustomerDashboardBanner(){
        
        System.out.println("\t     [-] Welcome in Old Customer Dashboard [-]");
        System.out.println("\t    ———————————————————————————————————————————");
        
    }
    
    public static void printOldCustomerSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Pay bill with meter code.\n");
        System.out.println("\t     [2] - Enter monthly reading of meter code.\n");
        System.out.println("\t     [3] - Complain about bill.");
        
    }
    
    public static void printOperatorDashboardBanner(){
        
        System.out.println("\t\t[-] Welcome in Operator Dashboard [-]");
        System.out.println("\t\t—————————————————————————————————————");
        
    }
    
    public static void printOperatorSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Collect payments from customer.\n");
        System.out.println("\t     [2] - Print bill with meter code.\n");
        System.out.println("\t     [3] - View bills of specific region.\n");
        System.out.println("\t     [4] - Stop meter and cancel subscription for customer.");
        
    }
    
    public static void printAdminDashboardBanner(){
        
        System.out.println("\t\t[-] Welcome in Admin Dashboard [-]");
        System.out.println("\t\t——————————————————————————————————");
        
    }
    
    public static void printAdminSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Add user.\n");
        System.out.println("\t     [2] - Update user.\n");
        System.out.println("\t     [3] - Delete user.\n");
        System.out.println("\t     [4] - View total collected.\n");
        System.out.println("\t     [5] - View all bills of specific regions.\n");
        System.out.println("\t     [6] - Make consumption statistics for specific regions.");

    }
    
    public static void addUserSelections(){
        
        System.out.println("\n\t     [1] - Add New Customer.\n");
        System.out.println("\t     [2] - Add New Operator.\n");
        System.out.println("\t     [3] - Add New Admin.");
        
    }
    
    public static void updateUserSelections(){
        
        System.out.println("\n\t     [1] - Update Customer.\n");
        System.out.println("\t     [2] - Update Operator.\n");
        System.out.println("\t     [3] - Update Admin.");
        
    }
    
    public static void deleteUserSelections(){
        
        System.out.println("\n\t     [1] - Delete Customer.\n");
        System.out.println("\t     [2] - Delete Operator.\n");
        System.out.println("\t     [3] - Delete Admin.");

    }
    
    public static void customerUpdateList(){
        
        System.out.println("\n\t[!] Alert : You Can't Update Name, National Id, Birth Date, Meter Code, Gender nor Contract Date.");
        System.out.println("\n\t     [1] - Address.\n");
        System.out.println("\t     [2] - Email.\n");
        System.out.println("\t     [3] - GovernmentCode.\n");
        System.out.println("\t     [4] - Phone Number.\n");
        System.out.println("\t     [5] - Type Of Use.");
        
    }
    
    public static void administratorUpdateList(){
        
        System.out.println("\n\t     [1] - Address.\n");
        System.out.println("\t     [2] - Email.\n");
        System.out.println("\t     [3] - Phone Number.\n");
        System.out.println("\t     [4] - Password.\n");
        System.out.println("\t     [5] - Administrator Role.");
        
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
            ro = null;
            
        } catch (AWTException ex) {
            System.out.println(ex.toString());
        }

    }
    
}