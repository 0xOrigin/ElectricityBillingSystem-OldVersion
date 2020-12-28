package Administrator;

public class Administrator {

    // Data fields
    private String administratorID;
    private String administratorPass;
    private String administratorRole;
    
    protected void setAdministratorID(String governmentCode, String nationalIdNum){
        
        int random = (int)(Math.random() * 10000);

        this.administratorID = String.format("%03d", Integer.parseInt(governmentCode)) + nationalIdNum.charAt(7) + nationalIdNum.charAt(8) +
                               String.format("%04d", random) + nationalIdNum.charAt(13);
    }
    
}
