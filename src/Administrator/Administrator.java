package Administrator;
import Person.PersonDriver;
import Datebase.*;
import java.util.ArrayList;


public class Administrator extends PersonDriver {

    // Data fields
    private String administratorID;
    private String administratorPass;
    private String administratorRole;
    
    protected final String operatorRole = "Operator";
    protected final String adminRole = "Admin";
    
    protected final String addressColumn = "Address";
    protected final String emailColumn = "Email";
    protected final String governmentCodeColumn = "GovernmentCode";
    protected final String phoneNumColumn = "PhoneNumber";
    protected final String typeOfUseColumn = "TypeOfUse";
    protected final String administratorPassColumn = "AdministratorPass";
    protected final String administratorRoleColumn = "AdministratorRole";
    
    
    protected static boolean isMeterCodeExists(String meterCode){
        return NewCustomerDatabase.isMeterCodeExists(meterCode);
    }
    
    
    protected static boolean isAdministratorIdExists(String administratorID){
        return AdministratorDatabase.isAdministratorIdExists(administratorID);
    }
    
    
    protected static boolean loginValidator(String administratorID, String administratorPass){
        return AdministratorDatabase.loginValidator(administratorID, administratorPass);
    }

    
    protected static void deleteCustomerFromDB(String meterCode){
        AdministratorDatabase.deleteCustomer(meterCode);
    }
    
    
    protected static void deleteAdministratorFromDB(String administratorID){
        AdministratorDatabase.deleteAdministrator(administratorID);
    }
    
    
    protected static void pushCustomerUpdateToDB(final String columnName, String value, String meterCode){
        AdministratorDatabase.updateCustomer(columnName, value, meterCode);
    }
    
    
    protected static void pushAdministratorUpdateToDB(final String columnName, String value, String administratorID){
        AdministratorDatabase.updateAdministrator(columnName, value, administratorID);
    }

    
    protected static void collectPayments(String meterCode){
        AdministratorDatabase.collectPayments(OldCustomerDatabase.getMoneyValueofLastPaidBill(meterCode));
    }
    

    protected static String[] getLastBillInfo(String meterCode){
        return OldCustomerDatabase.getLastBillInfo(meterCode);
    }
    
    protected static int getLastReading(String meterCode){
        return OldCustomerDatabase.getLastReading(meterCode);
    }
    
    protected static double getMoneyValueofLastPaidBill(String meterCode){
        return OldCustomerDatabase.getMoneyValueofLastPaidBill(meterCode);
    }
    
    
    protected static ArrayList<String[]> getBillsInfoOfSpecificRegion(String governmentCode){
        return OldCustomerDatabase.getBillsInfoOfSpecificRegion(governmentCode);
    }
    
    // --------------------------------------- Protected Setters
    
    protected void setAdministratorID(String governmentCode, String nationalIdNum){
        
        int random = (int)(Math.random() * 10000);

        this.administratorID = String.format("%03d", Integer.parseInt(governmentCode)) + nationalIdNum.charAt(7) + nationalIdNum.charAt(8) +
                               String.format("%04d", random) + nationalIdNum.charAt(13);
    }

    
    protected void setAdministratorPass(String administratorPass) {
        this.administratorPass = administratorPass;
    }
    
    
    protected void setAdministratorRole(String administratorRole) {
        this.administratorRole = administratorRole;
    }
    
    
    // --------------------------- Public Getters
    
    
    protected String getAdministratorID() {
        return administratorID;
    }

    
    protected String getAdministratorPass() {
        return administratorPass;
    }

    
    protected static String getAdministratorRole(String administratorID){
        return AdministratorDatabase.getAdministratorRole(administratorID);
    }
    
    
    protected static String getCustomerName(String meterCode){
        return NewCustomerDatabase.getName(meterCode);
    }
    
    
    protected static String getAdministratorName(String administratorID){
        return AdministratorDatabase.getAdministratorName(administratorID);
    }
    
    
    protected static double getTotalCollectedFromDB(){
        return AdministratorDatabase.viewTotalCollected();
    }
    
    
    protected static String[] consumptionStatForSpecificRegionFromDB(String governmentCode){
        return AdministratorDatabase.consumptionStatForSpecificRegion(governmentCode);
    }
    
    // Private getter
    
    private String getAdministratorRole() {
        return administratorRole;
    }

    
    // Push to Database
    
    protected void pushAllAdministratorInfoToDB(){
        AdministratorDatabase.addAdministrator(
                getName(), getNationalIdNum(), getAddress(), getEmail(),getPhoneNumber(), getGender(),
                getDateOfBirth(), getAdministratorID(), getAdministratorPass(), getContractDate(), getAdministratorRole()
        );
    }
    
    
}