package Person;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Person {
    
    
    // Data fields
    private String name;
    private String nationalIdNum;
    private String address;
    private String email;
    private String governmentCode;
    private String phoneNum;
    
    
    // Derived Data fields
    private String gender;
    private String dateOfBirth;
    private String contractDate;
    

    protected final String[][] governmentCodes = { {"02", "Cairo"}, {"013", "Qalyubia"}, {"03", "Alexandria"}, {"040", "Gharbia"},
                                                   {"048", "Monufia"}, {"055", "Sharqia"}, {"062", "Suez"}, {"064", "Ismailia"},
                                                   {"046", "Matruh"}, {"066", "Port Said"}, {"068", "North Sinai"}, {"069", "South Sinai"}, 
                                                   {"050", "Dakahlia"}, {"045", "Beheira"}, {"057", "Damietta"}, {"047", "Kafr El Sheikh"},
                                                   {"092", "New Valley"}, {"065", "Red Sea"}, {"084", "Faiyum"}, {"082", "Beni Suef"}, 
                                                   {"086", "Minya"}, {"088", "Asyut"}, {"093", "Sohag"}, {"095", "Luxor"},
                                                   {"096", "Qena"}, {"097", "Aswan"} };
    
    
    // Public setters
    
    protected void setName(String name) {
        this.name = name;
    }

    protected void setNationalIdNum(String nationalIdNum) {
        this.nationalIdNum = nationalIdNum;
        setGender(nationalIdNum);
        setDateOfBirth(nationalIdNum);
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setGovernmentCode(String governmentCode) {
        this.governmentCode = governmentCode;
    }

    protected void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    protected void setContractDate() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatDate = new SimpleDateFormat("E, dd/MM/yyyy, hh:mm:ss a");
        this.contractDate = formatDate.format(currentDate);
    }
    
    // Private Setters
    
    private void setGender(String nationalIdNum) {
        int genderSelector = nationalIdNum.charAt(12) - 48;
        this.gender = (genderSelector % 2 != 0) ? "Male" : "Female";
    }

    private void setDateOfBirth(String nationalIdNum){
        String year = ((nationalIdNum.charAt(0) == '3') ? "20" : "19");
        
        this.dateOfBirth = nationalIdNum.charAt(5) + "" + nationalIdNum.charAt(6) + "/" 
            + nationalIdNum.charAt(3) + nationalIdNum.charAt(4) + "/" 
            + year + nationalIdNum.charAt(1) + nationalIdNum.charAt(2); 
    }


    //--------- Public Getters --------------//

    public String getName(){
        return name;
    }
    
    public String getNationalIdNum(){
        return nationalIdNum;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getGovernmentCode(){
        return governmentCode;
    }
    
    public String getPhoneNumber(){
        return phoneNum;
    }
    
    public String getGender(){
        return gender;
    }
    
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    
    public String getContractDate(){
        return contractDate;
    }
    
}
