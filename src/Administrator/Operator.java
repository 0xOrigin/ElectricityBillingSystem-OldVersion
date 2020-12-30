package Administrator;

import Datebase.AdministratorDatabase;
import Datebase.OldCustomerDatabase;

public class Operator extends AdministratorDriver {

    private int tarrif;
            
    protected void runOperatorDashboard() {

        do {

            viewOperatorDashboard();

            do {

                System.out.print("\n[+] Choose a number(0 to return to main menu): ");
                
                choice = input.next().charAt(0);
                input.nextLine();
                
                switch (choice) {
                    case '1':

                        break;
                    case '2':

                        break;
                    case '3':

                        break;
                    case '4':

                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        break;
                }

            } while (choice != '1' && choice != '2' && choice != '3' && choice != '4');

            System.out.print("\n[+] Do you want to perform any additional operation in this dashboard? (y/n): ");
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }

    
    private static void viewOperatorDashboard() {

        clearScreen();
        printOperatorBanner();
        printOperatorSelections();
        
    }

    
    private static void printOperatorBanner() {

        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        System.out.println("\t      [-] Welcome in Operator Dashboard [-]");
        System.out.println("\t      —————————————————————————————————————");

    }

    
    private static void printOperatorSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Collect payments from customer.\n");
        System.out.println("\t     [2] - Print bill with meter code.\n");
        System.out.println("\t     [3] - View bills of specific region.\n");
        System.out.println("\t     [4] - Stop meter and cancel subscription for customer.");
        
    }

    public double getMoneyValue( int consumption, String typeOfUse){

        double moneyValue=0;

        if (typeOfUse=="Home"){
            if (consumption >= 0 && consumption <=50){
                moneyValue = 0.38 *consumption;
                tarrif =1;
            }    
            else if (consumption >50 && consumption <=100){
                moneyValue = 19 + 0.48* (consumption-50);
                tarrif=2;
            }    
            else if (consumption >100 && consumption <=200){
                moneyValue = .65 * consumption;
                tarrif=3;
            }
            else if (consumption >200 && consumption <=350){
                moneyValue = 130 + .96 * (consumption-200);
                tarrif =4;
            }
            else if (consumption >350 && consumption <=650){
                moneyValue = 274 + 1.18 * (consumption-350);
                tarrif=5;
            }
            else if (consumption >650 && consumption <=1000){
                moneyValue = 1.18 * consumption ;
                tarrif=6;
            }
            else{
                moneyValue = 1.45*consumption;
                tarrif=7;
            }
        }
        
        else {
            if (consumption<=100){
                moneyValue = consumption*.65;
                tarrif=1;
            }
            else if (consumption<=200){
                moneyValue = consumption *1.20;
                tarrif=2;
            }
            else if (consumption<=600){
                moneyValue = consumption *1.40;
                tarrif=3;
            }
            else if (consumption >600 && consumption <=1000){
                moneyValue = 840 + (consumption-600) *1.55;
                tarrif=4;
            }
            else{
                moneyValue = consumption *1.60;
                tarrif=5;
            }
        }

        return moneyValue;
    }

    public int getTarrif (String meterCode){
        return tarrif;
    }
}
