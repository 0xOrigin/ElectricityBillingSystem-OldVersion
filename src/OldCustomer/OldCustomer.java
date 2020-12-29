package OldCustomer;

import Datebase.NewCustomerDatabase;
import Datebase.OldCustomerDatabase;
import Email.Sendmail;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OldCustomer {
    
    public static void payBills(String meterCode){
        Scanner input =new Scanner(System.in);
        if(NewCustomerDatabase.isMeterCodeExists(meterCode)==true)
        {
            System.out.println("\n\nYou have "+ OldCustomerDatabase.countUnpaidBills(meterCode)+" unpaid bills\n\n");
            if (OldCustomerDatabase.countUnpaidBills(meterCode)>0)
            {
                System.out.println("\n\nDo you want to pay "+OldCustomerDatabase.getFirstUnpaidBillInfo(meterCode)[5]+"$ ? (1 = Yes / 0 = No)");
                int x=input.nextInt();
                if (x==0)
                {
                    System.out.println("Do you want to complain about the bill? (1 = Yes / 0 = No)");
                    int y=input.nextInt();
                    if (y==1)
                    {
                        System.out.println("Please Enter your complain: ");
                        String complain=input.nextLine();
                        complainAboutBill(complain,meterCode);
                    }
                    else if (y==0)
                        return;
                }
                else if (x==1)
                {
                    OldCustomerDatabase.payBill(meterCode);
                }
            }
            else
            {
                return;
            }
        }
        else{
            System.out.println("\n\nPlease enter valid metercode\n\n");
            return;
        }
    }
    
    public static void enterMonthlyReading(String meterCode, int currentReading, int tariff, double moneyValue)
    {
        if(NewCustomerDatabase.isMeterCodeExists(meterCode)==true)
        {
            sendUnpaidMail(meterCode);
            if (true/*if the current reading is true*/)
            {
                OldCustomerDatabase.enterMonthlyReading(meterCode, currentReading, tariff, moneyValue);
            }
            else
            {
                System.out.println("\n\nPlease enter valid reading.");
            }
        }
        else
        {
            System.out.println("\n\nPlease enter vaild meter code");
        }
        
    }
    private static void sendUnpaidMail(String meterCode){
        String Email=NewCustomerDatabase.getEmail(meterCode);//Define email
        
        String name[]=NewCustomerDatabase.getName(meterCode).split(" ");//define name
        
        String firstUnpaidDateString=OldCustomerDatabase.getFirstUnpaidBillInfo(meterCode)[6];//get the date of first unpaid bill.
        
        int lastBillMonth=Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(0)))*10 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(1)));//Turns Index of month from string to integer.
        
        int lastBillYear=Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(3)))*1000 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(4)))*100 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(5)))*10 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(6)));//Turns Index of year from string to integer.
        
        java.time.LocalDate currentdate = java.time.LocalDate.now();//get current time
        
        String current=String.valueOf(currentdate);//Turns current time object into string that carries current time.
        
        int currentMonth=Integer.parseInt(String.valueOf(current.charAt(5)))*10 +
        Integer.parseInt(String.valueOf(current.charAt(6)));//current month in integer.
        
        int currentYear =
        Integer.parseInt(String.valueOf(current.charAt(0)))*1000 + 
        Integer.parseInt(String.valueOf(current.charAt(1)))*100 +
        Integer.parseInt(String.valueOf(current.charAt(2)))*10 +
        Integer.parseInt(String.valueOf(current.charAt(3)));//current year in integer. 
        
        int numberOfUnpaidMonths=0;
        
        if (currentYear==lastBillYear)
        {
            numberOfUnpaidMonths=currentMonth-lastBillMonth;
            if (numberOfUnpaidMonths>=3)
            {

                try 
                {
                    Sendmail.unpaidEmail(Email, name[0], meterCode);
                } 
                catch (Exception ex)
                {
                    Logger.getLogger(OldCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        else if (currentYear>lastBillYear)
        {
            boolean x=false;
            int count=0;
            for (int i=lastBillMonth,j=lastBillYear;x=true;i++)
            {

                if (i%13==0)
                {
                    i=1;
                    j++;
                }
                if (i==currentMonth&&j==currentYear)
                {
                    x=true;
                    if (count>=3)
                    {
                        try 
                        {
                            Sendmail.unpaidEmail(Email, name[0], meterCode);
                        } 
                        catch (Exception ex)
                        {
                            Logger.getLogger(OldCustomer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                count++;
            }
        }
    }
    
    protected static void complainAboutBill(String complain, String meterCode){
        OldCustomerDatabase.complainAboutBill(complain, meterCode);
    }
    
    
}
