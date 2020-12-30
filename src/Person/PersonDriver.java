package Person;

import java.util.*;

public class PersonDriver extends Person {
    
    Scanner input = new Scanner(System.in);
    
    // Data fields
    private String name;
    private String nationalIdNum;
    private String address;
    private String email;
    private String governmentCode;
    private String phoneNum;
    
    
    public void runPersonDriver() {
      
        getPersonalDataFromCustomer();
        PersonalDataSetter();
        
    }
    
    private void PersonalDataSetter(){
        
        setName(name);
        setNationalIdNum(nationalIdNum);
        setAddress(address);
        setEmail(email);
        setGovernmentCode(governmentCode);
        setPhoneNum(phoneNum);
        setContractDate();
    }
    
    
    private void getPersonalDataFromCustomer(){
        
        System.out.print("\n\t[+] Enter name: ");
        name = input.nextLine();
        name = Name_Val(name.trim());
        
        System.out.print("\n\t[+] Enter National ID: ");
        nationalIdNum = input.nextLine();
        nationalIdNum = NationalID_Val(nationalIdNum);
        
        System.out.print("\n\t[+] Enter address: ");
        address = input.nextLine();
        address = Address_Val(address.trim());
        
        System.out.print("\n\t[+] Enter e-mail: ");
        email = input.nextLine();
        email = Email_Val(email.trim());
        
        System.out.print("\n\t[+] Enter government code: ");
        governmentCode = input.nextLine();
        governmentCode = GovernmentCode_Val(governmentCode.trim());
        
        System.out.print("\n\t[+] Enter phone number: ");
        phoneNum = input.nextLine();
        phoneNum = PhoneNumber_Val(phoneNum.trim());
        
    }
    
    
    
    // Data Validators
    
    public String Name_Val(String name){
        
        do {
            
            if(name.isBlank() || name.matches(".*\\d.*")){
                
                System.out.print("\n[-] Invalid name, Enter a valid name: ");
                name = input.nextLine();
                
            } else
                return name.trim();
            
        } while (true);
        
    }
    
    
    public String NationalID_Val(String nationalIdNum){
        
        do {

            if(!nationalIdNum.matches("\\d+") || nationalIdNum.length() != 14){
                
                System.out.print("\n[-] Invalid National ID, Enter a valid National ID Number: ");
                nationalIdNum = input.nextLine();
                
            } else 
                return nationalIdNum;
                
        } while (true);
  
    }
    
    
    public String Address_Val(String address){
 
        do {
            
            if(address.isBlank()){
                
                System.out.print("\n[-] Invalid address, Enter a valid address: ");
                address = input.nextLine();
                
            } else 
                return address.trim();
                
        } while (true);
        
    }
    
    
    public String Email_Val(String email){
        
        do {
            
            if(!email.matches("[^@]+@[^\\.]+\\..+")){
                
                System.out.print("\n[-] Invalid email, Enter a valid email: ");
                email = input.nextLine();
                
            } else
                return email.trim();
            
        } while (true);
        
    }
    
    
    public String GovernmentCode_Val(String governmentCode){
        
        do {
            
            boolean found = false;
            
            for(int i = 0; i < governmentCodes.length; i++)
                if(governmentCode.equals(governmentCodes[i][0])){
                    found = true;
                    break;
                }
            
            
            if(!governmentCode.matches("^(\\w+\\S+)$") || !found){ // No spaces No chars and should be in the array list
                
                System.out.print("\n[-] Invalid government code, Enter a valid government code: ");
                governmentCode = input.nextLine();
                
            } else 
                return governmentCode;
                
        } while (true);
        
    }
    
    
    public String PhoneNumber_Val(String phoneNum){
        
        do {

            if(!phoneNum.matches("\\d+")){
                
                System.out.print("\n[-] Invalid phone number, Enter a valid phone number: ");
                phoneNum = input.nextLine();
                
            } else
                return phoneNum;
            
        } while (true);
        
    }
    
}
