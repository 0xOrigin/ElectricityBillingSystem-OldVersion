package OldCustomer;

import Email.Sendmail;
import java.util.Scanner;
import Administrator.Operator;
import Util.Display;

public class OldCustomerDriver extends OldCustomer {

    Scanner input = new Scanner(System.in);
    
    private double paymentCollector = 0.0;
    
    char qContinue, choice;
    
    public void runDashboard(){
        
        do {
            
            viewOldCustomerDashboard();
            
            do {
                
                System.out.print("\n[+] Choose a number(0 to return to main menu): ");
                
                choice = input.next().charAt(0);
                input.nextLine();
                
                switch (choice) {
                    
                    case '1':
                        System.out.print("\n\t[+] Enter meter code: ");
                        payBillWithMeterCode(MeterCode_Val(input.nextLine()));
                        choice = '1';
                        break;
                    case '2':
                        System.out.print("\n\t[+] Enter meter code: ");
                        enterMonthlyReading(MeterCode_Val(input.next()));
                        break;
                    case '3':
                        System.out.print("\n\t[+] Enter meter code: ");
                        complainAboutBillWithMeterCode(MeterCode_Val(input.nextLine()));
                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        break;
                        
                }
                
            } while (choice != '1' && choice != '2' && choice != '3');
            
            System.out.print("\n[+] Do you want to perform any additional operation in this dashboard? (y/n): ");
            qContinue = input.next().charAt(0);
            
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }
    
    private static void viewOldCustomerDashboard(){
        
        Display.clearScreen();
        Display.printProgramNameBanner();
        Display.printOldCustomerDashboardBanner();
        Display.printOldCustomerSelections();
        
    }
                
    public void payBillWithMeterCode(String meterCode){
        
        paymentCollector = 0.0;
        
        do{
            
            if(countUnpaidBills(meterCode) > 0){
            
                printBill(meterCode);

                System.out.print("\n[+] Pay this bill?(y/n): ");
                choice = input.next().charAt(0);
                input.nextLine();
                
                if(choice == 'y' || choice == 'Y'){
                    
                    changeUnpaidStatusToPaid(meterCode);
                    
                    paymentCollector += getMoneyValueofLastPaidBill(meterCode);
                    System.out.println("\n\t[-] The bill has been paid successfully.");
                
                    System.out.print("\n[+] Do you want to pay another bill?(y/n): ");
                    qContinue = input.next().charAt(0);
                    input.nextLine();
                    
                } else {
                    
                    System.out.print("\n[-] Do you want to complain about the bill?(y/n): ");
                    choice = input.next().charAt(0);
                    input.nextLine();
                    
                    if(choice == 'y' || choice == 'Y'){
                        
                        System.out.print("\n\t[+] Enter your complaint: ");
                        complainAboutBill(Complaint_Val(input.nextLine()), meterCode);
                        
                        System.out.println("\n\t[-] The complaint has been received, we are doing our best to raise the level of customer satisfaction.");
                        break;
                        
                    } else {
                        
                        return;
                        
                    }
                }

            } else {
                
                System.out.println("\n\t[-] You have already paid all bills, have a nice day!");
                break;
                
            }
  
        } while (qContinue == 'Y' || qContinue == 'y');
        
    }
    
    public double getPaymentCollected(){

       return paymentCollector;

   }
    
    private void printBill(String meterCode){
        
        String[] billInfo = getFirstUnpaidBillInfo(meterCode);
        
        System.out.println("\n  ===============================================================================================================================================");
        System.out.printf("\t  %-15s | %-15s | %-15s | %-15s | %-10s | %-15s | %-20s", "GovernmentCode", "PastReading", "CurrentReading",
                          "Consumption", "Tariff", "MoneyValue", "DateOfBill");
        System.out.println("\n  ===============================================================================================================================================");


        System.out.format("\t  %-15s | %-15s | %-15s | %-15s | %-10s | %-15s | %-20s",
                            billInfo[0], billInfo[1], billInfo[2], billInfo[3],
                            billInfo[4], billInfo[5], billInfo[6]);

        System.out.println("\n  ===============================================================================================================================================");
        
    }
    
    private void enterMonthlyReading(String meterCode){
     
        Operator operator = new Operator();
        
        System.out.print("\n\t[+] Enter monthly reading: ");
        int reading = Integer.parseInt(IO_Val(input.next()));
        int monthlyReading = operator.Reading_Val(reading, meterCode);
        
        double moneyValue = Double.parseDouble(String.format("%.2f", operator.getMoneyValue(getConsumption(meterCode, monthlyReading), getTypeOfUse(meterCode))));
        
        pushMonthlyReadingToDB(meterCode, monthlyReading,
                               moneyValue,
                               operator.getTarrif());
        
        System.out.println("\n\t[-] A new bill has been released.");
        
        if(countUnpaidBills(meterCode) >= 3)
            Sendmail.unpaidEmail(getEmail(meterCode), getName(meterCode), meterCode);
        
    }
    
    
    private void complainAboutBillWithMeterCode(String meterCode){
        
        System.out.print("\n\t[+] Enter your complaint: ");
        complainAboutBill(Complaint_Val(input.nextLine()), meterCode);
        System.out.println("\n\t[-] The complaint has been received, we are doing our best to raise the level of customer satisfaction.");
        
    }
        
    //------------------------------------------------- Data Validators

    private String MeterCode_Val(String meterCode) {

        do {

            if (isMeterCodeExists(meterCode)) {
                return meterCode;
            } else {

                System.out.print("\n[+] Enter a valid meter code: ");
                meterCode = input.nextLine();

            }

        } while (true);

    }
    
    private String IO_Val(String reading){
        
        do {

            if (reading.matches("\\d+\\S") && !reading.isBlank()) {
                return reading;
            } else {

                System.out.print("\n[+] Enter a valid monthly reading: ");
                reading = input.next();

            }

        } while (true);
        
    }
    
    private String Complaint_Val(String complaint){
        
        do {
            
            if(!complaint.isBlank()){
                
                return complaint;
                
            } else {
                
                System.out.print("[+] Empty complaint. Enter a complaint: ");
                complaint = input.nextLine();
                
            }
            
        } while (true);
        
    }
        
}