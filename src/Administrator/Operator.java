package Administrator;

public class Operator extends AdministratorDriver {


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


    
}
