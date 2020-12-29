package OldCustomer;

import Datebase.NewCustomerDatabase;
import Datebase.OldCustomerDatabase;
import Email.Sendmail;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OldCustomer {
    
    
    public boolean isMeterCodeExists(String meterCode)
    {
        return (NewCustomerDatabase.isMeterCodeExists(meterCode));
    }
    public boolean haveUnpaidBills(String meterCode){
        return (OldCustomerDatabase.countUnpaidBills(meterCode)>0);
    }
    public int unpaidBillsCount(String meterCode)
    {
        return OldCustomerDatabase.countUnpaidBills(meterCode);
    }
    public String firstUnpaidMoneyValueString(String meterCode)
    {
        return OldCustomerDatabase.getFirstUnpaidBillInfo(meterCode)[5];
    }
    public double firstUnpaidMoneyValue(String meterCode)
    {
        double moneyValue=Double.parseDouble(OldCustomerDatabase.getFirstUnpaidBillInfo(meterCode)[5]);
        return moneyValue;
    }
    public void changePaidStatusToPaid(String meterCode)
    {
        OldCustomerDatabase.payBill(meterCode);
    }
    public void enterMonthlyReading(String meterCode, int currentReading, int tariff, double moneyValue)
    {
        OldCustomerDatabase.enterMonthlyReading(meterCode, currentReading, tariff, moneyValue);
    }
    public String getEmail(String meterCode)
    {
        return NewCustomerDatabase.getEmail(meterCode);
    }
    public String[] getName(String meterCode)
    {
        return NewCustomerDatabase.getName(meterCode).split(" ");
    }
    private int getFirstUnpaidBillMonth(String meterCode)
    {
        String firstUnpaidDateString=OldCustomerDatabase.getFirstUnpaidBillInfo(meterCode)[6];
        
        int firstBillMonth=Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(0)))*10 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(1)));
        return firstBillMonth;
    }
    private int getFirstUnpaidBillYear(String meterCode)
    {
        String firstUnpaidDateString=OldCustomerDatabase.getFirstUnpaidBillInfo(meterCode)[6];
        int firstBillYear=Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(3)))*1000 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(4)))*100 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(5)))*10 +
        Integer.parseInt(String.valueOf(firstUnpaidDateString.charAt(6)));
        return firstBillYear;
    }
    private int getCurrentMonth()
    {
         java.time.LocalDate currentdate = java.time.LocalDate.now();
        
        String current=String.valueOf(currentdate);
        
        int currentMonth=Integer.parseInt(String.valueOf(current.charAt(5)))*10 +
        Integer.parseInt(String.valueOf(current.charAt(6)));
        return currentMonth;
    }
    private int getCurrentYear()
    {
         java.time.LocalDate currentdate = java.time.LocalDate.now();
        
        String current=String.valueOf(currentdate);
        
        int currentYear =
        Integer.parseInt(String.valueOf(current.charAt(0)))*1000 + 
        Integer.parseInt(String.valueOf(current.charAt(1)))*100 +
        Integer.parseInt(String.valueOf(current.charAt(2)))*10 +
        Integer.parseInt(String.valueOf(current.charAt(3)));
        return currentYear;
    }
    public int unpaidMonthCount(String meterCode)
    {
        int unpaidCount=0;
        if (getFirstUnpaidBillYear(meterCode)==getCurrentYear())
        {
            unpaidCount=getCurrentMonth()-getFirstUnpaidBillMonth(meterCode);
            return unpaidCount;
        }
        else if (getFirstUnpaidBillYear(meterCode)<getCurrentYear())
        {
            for (int i=getFirstUnpaidBillMonth(meterCode),j=getFirstUnpaidBillYear(meterCode);;i++)
            {

                if (i%13==0)
                {
                    i=1;
                    j++;
                }
                if (i==getCurrentMonth()&&j==getCurrentYear())
                {
                    break;
                }
                unpaidCount++;
            }
        }
        return unpaidCount;
    }
    
    
    
    
    protected static void complainAboutBill(String complain, String meterCode){
        OldCustomerDatabase.complainAboutBill(complain, meterCode);
    }
    
    
}
