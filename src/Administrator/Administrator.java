package Administrator;
import Person.PersonDriver;
import Datebase.AdministratorDatabase;


public class Administrator extends PersonDriver {

    // Data fields
    private String administratorID;
    private String administratorPass;
    private String administratorRole;
    
    
    protected static boolean isAdministratorIdExists(String administratorID){
        return AdministratorDatabase.isAdministratorIdExists(administratorID);
    }
    
    
    protected static boolean loginValidator(String administratorID, String administratorPass){
        return AdministratorDatabase.loginValidator(administratorID, administratorPass);
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
    
    
    protected static String getAdministratorRole(String administratorID){
        return AdministratorDatabase.getAdministratorRole(administratorID);
    }
    
    
    // --------------------------- Public Getters
    
    
    public String getAdministratorID() {
        return administratorID;
    }

    
    public String getAdministratorPass() {
        return administratorPass;
    }

    
    public String getAdministratorRole() {
        return administratorRole;
    }

    
    // Push to Database
    
    protected void pushAllNewAdministratorInfoToDB(){
        AdministratorDatabase.addAdministrator(
                getName(), getNationalIdNum(), getAddress(), getEmail(),getPhoneNumber(), getGender(),
                getDateOfBirth(), getAdministratorID(), getAdministratorPass(), getContractDate(), getAdministratorRole()
        );
    }
    
    
}