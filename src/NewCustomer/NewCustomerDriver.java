package NewCustomer;

import java.util.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.File;
import Email.Sendmail;

public class NewCustomerDriver extends NewCustomer {
    
    Scanner input = new Scanner(System.in);
    
    // Data fields
    private char typeOfUse;
    private String apartmentContractPath;
    
    private char determinesAttachOrNot;
    
    
    public void runDashboard() throws Exception{
        
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
                    case '1':
                        fillNewContract();
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
    
    private static void viewDashboard(){
        
        clearScreen();
        printBanner();
        printSelections();
        System.out.flush();
        
    }
    
    private static void printBanner(){
        
        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        System.out.println("\t     [-] Welcome in New Customer Dashboard [-]");
        System.out.println("\t    ———————————————————————————————————————————");
        
    }

    private static void printSelections(){
        
        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Fill new contract.");
        
    }
    
    
    private static void clearScreen(){
       
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
    
    
    public void fillNewContract() throws Exception {
        
        runPersonDriver();
        setMeterCode(getGovernmentCode(), getNationalIdNum());
        getContractDataFromCustomer();
        setContractDate();
        pushAllNewCustomerInfoToDB();
        String [] name = getName().split(" ");
        Sendmail.meterReady(getEmail(), name[0], getMeterCode());
        
        if(determinesAttachOrNot == 'Y' || determinesAttachOrNot == 'y'){
            
            setApartmentContractPath(apartmentContractPath);
            attachCopyOfContractToDB();

        }
        
        System.out.println("\n[-] The contract has been successfully registered, check your email.");
    }
    
    private void getContractDataFromCustomer(){
        
        System.out.print("\n\t[+] Enter type of use(0 for Home use, 1 for Commerical use): ");
        typeOfUse = input.next().charAt(0);
        TypeOfUse_Val(typeOfUse);
        
        System.out.print("\n\t[+] Do you want to attach a copy of apartment contract? (y/n): ");
        determinesAttachOrNot = input.next().charAt(0);
        
        if(determinesAttachOrNot == 'Y' || determinesAttachOrNot == 'y'){
            input.nextLine();
            System.out.format("\n\t[+] Enter a file path of apartment contract: ");
            apartmentContractPath = input.nextLine();
            apartmentContractPath = AttachedFile_Val(apartmentContractPath);
        }
            
        
    }
    
    // Data Validators
    
    public void TypeOfUse_Val(char selector){
        
        do{
            
            switch (selector) {
                case '0':
                    setTypeOfUse("Home");
                    return;
                case '1':
                    setTypeOfUse("Commerical");
                    return;
                default:
                    System.out.print("\n[-] Invalid type of use selection, Enter a valid type of use: ");
                    selector = input.next().charAt(0);
                    break;
            }
            
        } while (true);
        
    }
 
    public String AttachedFile_Val(String apartmentContractPath){
        
        do{
            
            File f = new File(apartmentContractPath);
            
            if(f.exists())
                return apartmentContractPath;        
            else {
                System.out.print("\n[-] Invalid file path, Enter a valid path of contract file: ");
                apartmentContractPath = input.nextLine();
            }
            
        } while (true);
        
    }
    
}
