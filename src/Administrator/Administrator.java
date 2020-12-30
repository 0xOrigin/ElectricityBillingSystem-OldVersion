package Administrator;
import Person.PersonDriver;
import Datebase.*;


public class Administrator extends PersonDriver {

    // Data fields
    private String administratorID;
    private String administratorPass;
    private String administratorRole;
    
    protected final String operatorRole = "Operator";
    protected final String adminRole = "Admin";
    
    
    protected final String[][] governmentCodes = { {"02", "Cairo"}, {"013", "Qalyubia"}, {"03", "Alexandria"}, {"040", "Gharbia"},
                                                   {"048", "Monufia"}, {"055", "Sharqia"}, {"062", "Suez"}, {"064", "Ismailia"},
                                                   {"046", "Matruh"}, {"066", "Port Said"}, {"068", "North Sinai"}, {"069", "South Sinai"}, 
                                                   {"050", "Dakahlia"}, {"045", "Beheira"}, {"057", "Damietta"}, {"047", "Kafr El Sheikh"},
                                                   {"092", "New Valley"}, {"065", "Red Sea"}, {"084", "Faiyum"}, {"082", "Beni Suef"}, 
                                                   {"086", "Minya"}, {"088", "Asyut"}, {"093", "Sohag"}, {"095", "Luxor"},
                                                   {"096", "Qena"}, {"097", "Aswan"} };
    
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