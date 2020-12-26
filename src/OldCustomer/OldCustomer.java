package OldCustomer;

import Datebase.OldCustomerDatabase;

public class OldCustomer {
    
    
    
    protected void complainAboutBill(String complain, String meterCode){
        OldCustomerDatabase.complainAboutBill(complain, meterCode);
    }
    
    
}
