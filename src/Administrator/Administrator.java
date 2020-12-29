package Administrator;
import Person.PersonDriver;
import Datebase.*;
public class Administrator extends PersonDriver {

    // Data fields
    private String administratorID;
    private String administratorPass;
    private String administratorRole;
    
    protected void setAdministratorID(String governmentCode, String nationalIdNum){
        
        int random = (int)(Math.random() * 10000);

        this.administratorID = String.format("%03d", Integer.parseInt(governmentCode)) + nationalIdNum.charAt(7) + nationalIdNum.charAt(8) +
                               String.format("%04d", random) + nationalIdNum.charAt(13);
    }

    public String getAdministratorID() {
        return administratorID;
    }

    public String getAdministratorPass() {
        return administratorPass;
    }

    public void setAdministratorPass(String administratorPass) {
        this.administratorPass = administratorPass;
    }

    public String getAdministratorRole() {
        return administratorRole;
    }

    public void setAdministratorRole(String administratorRole) {
        this.administratorRole = administratorRole;
    }
    
    protected void pushAllNewAdministratorInfoToDB(){
        AdministratorDatabase.addAdministrator(
                getName(), getNationalIdNum(), getAddress(), getEmail(),getPhoneNumber(), getGender(),
                getDateOfBirth(), getAdministratorID(), getAdministratorPass(), getContractDate(), getAdministratorRole()
        );
    }
    
}