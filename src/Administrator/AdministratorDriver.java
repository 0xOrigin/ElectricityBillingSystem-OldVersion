package Administrator;

import java.util.Scanner;
import java.util.ArrayList;

public class AdministratorDriver extends Administrator {

    // Data fields
    private String administratorID;
    private String administratorPass;
    
    protected char qContinue, choice;
    protected boolean authorizationFlag = false;
    Scanner input = new Scanner(System.in);
    

    public void runDashboard() {

        System.out.print("\n\t[+] Enter administrator ID: ");
        administratorID = AdministratorID_Val(input.nextLine());

        System.out.print("\n\t[+] Enter administrator password: ");
        administratorPass = input.nextLine();

        String choiceDashboard = loginForm(administratorID, administratorPass);

        if (choiceDashboard.equals(adminRole)) {
            Admin admin = new Admin();
            admin.runAdminDashboard(administratorID);
        } else {
            Operator operator = new Operator();
            operator.runOperatorDashboard();
        }

    }
    
 
    private String loginForm(String administratorID, String administratorPass) {

        do {

            if (loginValidator(administratorID, administratorPass)) {
                return getAdministratorRole(administratorID);
            } else {

                System.out.print("\n[+] Enter a valid administrator password: ");
                administratorPass = input.nextLine();

            }

        } while (true);

    }
    
    protected String AdministratorID_Val(String administratorID) {

        do {

            if (isAdministratorIdExists(administratorID)) {
                return administratorID;
            } else {

                System.out.print("\n[+] Enter a valid administrator ID: ");
                administratorID = input.nextLine();

            }

        } while (true);

    }

    
    protected String AdminID_Val(String adminID) {

        do {

            if (isAdministratorIdExists(adminID) && adminRole.equals(getAdministratorRole(adminID))) {
                return adminID;
            } else {

                System.out.print("\n[+] Enter a valid admin ID: ");
                adminID = input.nextLine();

            }

        } while (true);

    }
    
    
    protected String OperatorID_Val(String OperatorID) {

        do {

            if (isAdministratorIdExists(OperatorID) && operatorRole.equals(getAdministratorRole(OperatorID))) {
                return OperatorID;
            } else {

                System.out.print("\n[+] Enter a valid operator ID: ");
                OperatorID = input.nextLine();

            }

        } while (true);

    }

    
    protected String Customer_Val(String meterCode) {

        do {

            if (isMeterCodeExists(meterCode)) {
                return meterCode;
            } else {

                System.out.print("\n[+] Enter a valid meter code: ");
                meterCode = input.nextLine();

            }

        } while (true);

    }

    
    protected void viewBillsOfSpecificRegion(){ // Enables operator to view bills of specific region.
                                                // Enables admin to view all bills of specific regions.
        printGovernmentCodes();
        
        do {            
            
            System.out.print("\n[+] Enter a government code: ");            
            ArrayList<String[]> billsInfo = getBillsInfoOfSpecificRegion(GovernmentCode_Val(input.next()));
            
            if(billsInfo.isEmpty()){
                
                System.out.println("\n\t[-] No bills have been registered in this government yet.");
                
            } else {
            
                System.out.println("\n  ===============================================================================================================================================");
                System.out.printf("\t  %-15s | %-15s | %-15s | %-15s | %-10s | %-15s | %-15s | %-20s", "MeterCode", "PastReading", "CurrentReading",
                                  "Consumption", "Tariff", "MoneyValue", "Status", "DateOfBill");
                System.out.println();
                System.out.println("  ===============================================================================================================================================");

                for(int i = 0; i < billsInfo.size(); i++){
                        System.out.format("\t  %-15s | %-15s | %-15s | %-15s | %-10s | %-15s | %-15s | %-20s",
                                          billsInfo.get(i)[0], billsInfo.get(i)[1], billsInfo.get(i)[2], billsInfo.get(i)[3],
                                          billsInfo.get(i)[4], billsInfo.get(i)[5], billsInfo.get(i)[6], billsInfo.get(i)[7]);

                    System.out.println();
                }
                System.out.println("  ===============================================================================================================================================");
            
            }

            System.out.print("\n[+] Do you want to View bills Of another region?(y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }
    
    
    protected void printGovernmentCodes(){
        
        System.out.println("\n\t-------------------------------------------------------------------------------------------------");
        
        for (int i = 0; i < 22; i += 3) {
            
            System.out.printf("\t| %20s -> %5s |", governmentCodes[i][1], governmentCodes[i][0]);
            System.out.printf(" %20s -> %5s ", governmentCodes[i + 1][1], governmentCodes[i + 1][0]);
            System.out.printf("| %20s -> %5s |\n", governmentCodes[i + 2][1], governmentCodes[i + 2][0]);

        }
        
        System.out.printf("\t| %20s -> %5s |", governmentCodes[24][1], governmentCodes[24][0]);
        System.out.printf(" %20s -> %5s ", governmentCodes[25][1], governmentCodes[25][0]);
        System.out.printf("| %29s |", "");

        System.out.println("\n\t-------------------------------------------------------------------------------------------------");
        
    }
    
    
    protected void deleteCustomer(String meterCode) {
        
        if(countUnpaidBills(meterCode) > 0){
            
            System.out.println("\n[-] You have unpaid bills, please pay them first.");
            return;
            
        }

        deleteCustomerFromDB(meterCode);
        System.out.println("\n\t[-] The meter has been stopped and the customer has been successfully deleted.");
        
    }
    
}