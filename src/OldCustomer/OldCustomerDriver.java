package OldCustomer;

import Email.Sendmail;
import static OldCustomer.OldCustomer.complainAboutBill;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OldCustomerDriver extends OldCustomer {

    Scanner input = new Scanner(System.in);
    
    
    public void runDashboard(){
        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        System.out.println("\t     [-] Welcome in Old Customer Dashboard [-]");
        System.out.println("\t    ———————————————————————————————————————————");
        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Pay bill with meter code.");
        System.out.println("\t     [2] - Enter monthly reading of meter code.");
        System.out.println("\t     [3] - Complain about bill.");
        System.out.print("\n[+] Choose a number(0 to return to main menu): ");
        char choice = input.next().charAt(0);
        input.nextLine();
        String meterCode;
                switch (choice) {
                    case '1':
                        meterCode= enterMeterCode();
                        payBill(meterCode);
                        break;
                     case '2':
                        meterCode= enterMeterCode();
                        System.out.println("\n[-]Enter the current reading: ");
                        int currentReading=input.nextInt();
                        input.nextLine();
                        int tariff=1; double moneyValue=100;// SHOULD BE CHANGED LATER!!!
                        enterMonthlyReading(meterCode, currentReading, tariff, moneyValue);
                        break;
                     case '3':
                        meterCode= enterMeterCode();
                         System.out.println("\n[-]Enter your complain about "+firstUnpaidMoneyValueString(meterCode)+"$: ");
                         String complain=input.nextLine();
                         complainAboutBill( complain,meterCode);
                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        break;
                }
        
    }
    private String enterMeterCode()
    {
        System.out.println("\n[+]Enter your meter code: ");
        String meterCode=input.nextLine();
        while (isMeterCodeExists(meterCode)==false)
        {
            System.out.println("\n[+]Enter VALID meter code: ");
            meterCode=input.nextLine();
        }
        sendUnpaidMail(meterCode);
        return meterCode;
    }
    public void payBill(String meterCode){
        
            if (haveUnpaidBills(meterCode)==true)
            {
                System.out.println("\n[+]Your Bill Information");
                System.out.println("\n\t[-]Government Code: "+getFirstUnpaidGovCode(meterCode));
                System.out.println("\n\t[-]Past Reading: "+getFirstUnpaidPastReading(meterCode));
                System.out.println("\n\t[-]Current Reading: "+getFirstUnpaidCurrentReading(meterCode));
                System.out.println("\n\t[-]Consumption: "+getFirstUnpaidConsumption(meterCode));
                System.out.println("\n\t[-]Tariff: "+getFirstUnpaidTariff(meterCode));
                System.out.println("\n\t[-]Date Of Bill: "+getFirstUnpaidDateOfBill(meterCode));
                System.out.println("\n[-]Do you want to pay "+firstUnpaidMoneyValueString(meterCode)+"$ ? (1 = Yes / 0 = No)");
                int choice1=input.nextInt();
                input.nextLine();
                if (choice1==0)
                {
                    System.out.println("[-]Do you want to complain about the bill? (1 = Yes / 0 = No)");
                    int choice2=input.nextInt();
                    input.nextLine();
                    if (choice2==1)
                    {
                        System.out.println("[-]Please Enter your complain: ");
                        String complain=input.nextLine();
                        complainAboutBill(complain,meterCode);
                    }
                    else if (choice2==0)
                        return;
                }
                else if (choice1==1)
                {
                    changePaidStatusToPaid(meterCode);
                }
            }
            else
            {
                System.out.println("\n[-]You have no unpaid bills");
                return;
            }
        
        
    }
    
    @Override
    public void enterMonthlyReading(String meterCode, int currentReading, int tariff, double moneyValue)
    {
        
        
        if (true/*if the current reading is true*/)
        {
            super.enterMonthlyReading(meterCode, currentReading, tariff, moneyValue);
        }
        else
        {
            System.out.println("\n\nPlease enter valid reading.");
        }
    }
    
    private void sendUnpaidMail(String meterCode){
        if (unpaidMonthCount(meterCode)>=3)
        {
            String name[]=getName(meterCode);
            try {
                Sendmail.unpaidEmail(getEmail(meterCode),name[0],meterCode);
            } catch (Exception ex) {
                Logger.getLogger(OldCustomerDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    
}
