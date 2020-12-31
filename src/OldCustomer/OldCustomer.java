package OldCustomer;

import Datebase.NewCustomerDatabase;
import Datebase.OldCustomerDatabase;


public class OldCustomer {
    
    
    protected boolean isMeterCodeExists(String meterCode){
        return NewCustomerDatabase.isMeterCodeExists(meterCode);
    }
    
    
    protected int countUnpaidBills(String meterCode){
        return OldCustomerDatabase.countUnpaidBills(meterCode);
    }
    
    
    protected String[] getFirstUnpaidBillInfo(String meterCode){
        return OldCustomerDatabase.getFirstUnpaidBillInfo(meterCode);
    }
          
    
    protected void changeUnpaidStatusToPaid(String meterCode){
        OldCustomerDatabase.payBill(meterCode);
    }
    

    protected String getEmail(String meterCode){
        return NewCustomerDatabase.getEmail(meterCode);
    }
    
    
    protected String getName(String meterCode){
        return NewCustomerDatabase.getName(meterCode).split(" ")[0];
    }
    
    
    protected String getTypeOfUse(String meterCode){
        return NewCustomerDatabase.getTypeOfUse(meterCode);
    }
    
    
    protected static void pushMonthlyReadingToDB(String meterCode, int currentReading, double moneyValue, int tariff){
        OldCustomerDatabase.enterMonthlyReading(meterCode, currentReading, tariff, moneyValue);
    }
    
    
    protected static int getConsumption(String meterCode, int currentReading){
        return OldCustomerDatabase.getConsumption(meterCode, currentReading);
    }
    
    
    protected static double getMoneyValueofLastPaidBill(String meterCode){
        return OldCustomerDatabase.getMoneyValueofLastPaidBill(meterCode);
    }
    
    protected static void complainAboutBill(String complain, String meterCode){
        OldCustomerDatabase.complainAboutBill(complain, meterCode);
    }
    
    
}
