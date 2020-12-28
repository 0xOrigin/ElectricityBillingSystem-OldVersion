package NewCustomer;
import Datebase.NewCustomerDatabase;
import Person.*;

public class NewCustomer extends PersonDriver {
    
    // Data fields
    private String typeOfUse;
    private String meterCode;
    private String apartmentContractPath;
    
    // Protected setters

    protected void setTypeOfUse(String typeOfUse) {
        this.typeOfUse = typeOfUse;
    }

    protected void setMeterCode(String governmentCode, String nationalIdNum) {
        
        int latestNumberOfCustomers = NewCustomerDatabase.getNumberOfCustomers() + 1;
        int random = (int)(Math.random() * 100);
        String updatedNumOfCustomers = String.format("%07d", latestNumberOfCustomers);
        
        this.meterCode = String.format("%03d", Integer.parseInt(governmentCode)) + "C" + nationalIdNum.charAt(7) + nationalIdNum.charAt(8) +
                         String.format("%02d", random) + updatedNumOfCustomers;
    }

    protected void setApartmentContractPath(String apartmentContractPath) {
        this.apartmentContractPath = apartmentContractPath;
    }
    
    // Public getters

    public String getTypeOfUse() {
        return typeOfUse;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public String getApartmentContractPath() {
        return apartmentContractPath;
    }
    
    // Protected functions
    
    protected void pushAllNewCustomerInfoToDB(){
        NewCustomerDatabase.insertNewCustomer(
                getName(), getNationalIdNum(), getAddress(), getEmail(),
                getGovernmentCode(), getPhoneNumber(), getGender(), getDateOfBirth(),
                getTypeOfUse(), getMeterCode(), getContractDate());
    }
    
    protected void attachCopyOfContractToDB(){
        NewCustomerDatabase.attachApartmentContract(getApartmentContractPath(), getMeterCode());
    }
    
}
