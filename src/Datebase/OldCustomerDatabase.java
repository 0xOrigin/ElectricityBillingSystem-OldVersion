package Datebase;
import java.sql.*;
import java.util.ArrayList;

public class OldCustomerDatabase {
    
    private int tarrif;
    
    // --------------------------------------------- Public functions --------------------------------------------- //
    
    public static String[] getFirstUnpaidBillInfo(String meterCode){
        
        String[] billInfo = new String[7];
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT GovernmentCode, PastReading, CurrentReading, Consumption, Tariff, MoneyValue, DateOfBill FROM OldCustomer " +
                                    "WHERE MeterCode = ? and Status = 'Unpaid' ORDER by Num LIMIT 1");
        ){
            
            ps.setString(1, meterCode);
            
            ResultSet r = ps.executeQuery();
            r.next();
            
            billInfo[0] = r.getString("GovernmentCode");
            billInfo[1] = String.valueOf(r.getInt("PastReading"));
            billInfo[2] = String.valueOf(r.getInt("CurrentReading"));
            billInfo[3] = String.valueOf(r.getInt("Consumption"));
            billInfo[4] = String.valueOf(r.getInt("Tariff"));
            billInfo[5] = String.valueOf(r.getDouble("MoneyValue"));
            billInfo[6] = r.getString("DateOfBill");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return billInfo;
    }
    
    
    public static void payBill(String meterCode){
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("update OldCustomer set Status = 'Paid' "
                    + "where Num in (SELECT Num FROM OldCustomer WHERE MeterCode = ? and Status = 'Unpaid' ORDER by Num LIMIT 1)");
        ){

            ps.setString(1, meterCode);

            ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
   
    }
    
    public static void enterMonthlyReading(String meterCode, int currentReading, int tariff, double moneyValue){
        
        // Creating a new bill
        
        int pastReading = getLastReading(meterCode);
        int consumption = currentReading - pastReading;
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("insert into OldCustomer "
                    + "(GovernmentCode, MeterCode, Tariff, PastReading, CurrentReading, Consumption, MoneyValue, Status, DateOfBill) "
                    + "values(?, ?, ?, ?, ?, ?, ?, 'Unpaid', ?)");
        ){
            
            ps.setString(1, getGovernmentCodeForMeter(meterCode));
            ps.setString(2, meterCode);
            ps.setInt(3, tariff);
            ps.setInt(4, pastReading);
            ps.setInt(5, currentReading);
            ps.setInt(6, consumption);
            ps.setDouble(7, moneyValue);
            ps.setString(8, generateNewDateOfBill( getDateOfLastBill(meterCode) ));
            
            ps.execute();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    
    public static String getTypeOfUse(String meterCode){
        return NewCustomerDatabase.getTypeOfUse(meterCode);
    }
    
    
    public static int getConsumption(String meterCode, int currentReading){
        return currentReading - getLastReading(meterCode);
    }
    
    public static int countUnpaidBills(String meterCode){
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT count(Status) FROM OldCustomer "
                    + "WHERE MeterCode = ? and Status = 'Unpaid'");
        ){
            
            ps.setString(1, meterCode);
            ResultSet r = ps.executeQuery();
            
            return r.getInt(1);
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return 0;
    }
    
    
    public static void complainAboutBill(String complain, String meterCode){
        
        String updateSQL = "UPDATE OldCustomer set Complain = ? "
                + "WHERE Num in (SELECT Num FROM OldCustomer WHERE MeterCode = ? and Status = 'Unpaid' ORDER by Num LIMIT 1)";
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement(updateSQL)
        ){

            
            ps.setString(1, complain);
            ps.setString(2, meterCode);

            ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
    public static String[] getLastBillInfo(String meterCode){
        
        String[] billInfo = new String[8];
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT GovernmentCode, PastReading, CurrentReading, Consumption, Tariff, MoneyValue, Status, DateOfBill FROM OldCustomer " +
                                    "WHERE MeterCode = ? ORDER by Num DESC LIMIT 1");
        ){
            
            ps.setString(1, meterCode);
            
            ResultSet r = ps.executeQuery();
            r.next();
            
            billInfo[0] = r.getString("GovernmentCode");
            billInfo[1] = String.valueOf(r.getInt("PastReading"));
            billInfo[2] = String.valueOf(r.getInt("CurrentReading"));
            billInfo[3] = String.valueOf(r.getInt("Consumption"));
            billInfo[4] = String.valueOf(r.getInt("Tariff"));
            billInfo[5] = String.valueOf(r.getDouble("MoneyValue"));
            billInfo[6] = r.getString("Status");
            billInfo[7] = r.getString("DateOfBill");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return billInfo;
    }
    
    
    public static ArrayList<String[]> getBillsInfoOfSpecificRegion(String governmentCode){
        
        ArrayList<String[]> billsContainer = new ArrayList<>();
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT MeterCode, PastReading, CurrentReading, Consumption, Tariff, MoneyValue, Status, DateOfBill FROM OldCustomer " +
                                    "WHERE GovernmentCode = ? ORDER by Num ASC");
        ){
            
            ps.setString(1, governmentCode);
            
            ResultSet r = ps.executeQuery();
            
            while(r.next()){
            
                String[] billInfo = new String[8];
                billInfo[0] = r.getString("MeterCode");
                billInfo[1] = String.valueOf(r.getInt("PastReading"));
                billInfo[2] = String.valueOf(r.getInt("CurrentReading"));
                billInfo[3] = String.valueOf(r.getInt("Consumption"));
                billInfo[4] = String.valueOf(r.getInt("Tariff"));
                billInfo[5] = String.valueOf(r.getDouble("MoneyValue"));
                billInfo[6] = r.getString("Status");
                billInfo[7] = r.getString("DateOfBill");
                billsContainer.add(billInfo);
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return billsContainer;
    }
    
    
    public static double getMoneyValueofLastPaidBill(String meterCode){
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("select MoneyValue from OldCustomer "
                    + "where MeterCode = ? and Status = 'Paid' order by Num DESC limit 1");
        ){
            
            ps.setString(1, meterCode);
            ResultSet r = ps.executeQuery();
            
            return r.getDouble("MoneyValue");
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return 0;
    }
    
    
    // --------------------------------------------- Private functions --------------------------------------------- //
    
    private static int getLastReading(String meterCode){
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("select CurrentReading from OldCustomer "
                    + "where MeterCode = ? order by Num DESC limit 1");
        ){
            
            ps.setString(1, meterCode);
            ResultSet r = ps.executeQuery();
            
            return r.getInt("CurrentReading");
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return 0;
    }
    
    
    private static String getDateOfLastBill(String meterCode){
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("select DateOfBill from OldCustomer "
                    + "where MeterCode = ? order by Num DESC limit 1");
        ){
            
            ps.setString(1, meterCode);
            ResultSet r = ps.executeQuery();
            
            return r.getString("DateOfBill");
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return "";
    }
    
    
    private static String generateNewDateOfBill(String latestDate){
        
        int months = Integer.parseInt(latestDate.substring(0, 2));
        int years = Integer.parseInt(latestDate.substring(4, 7));
        
        months = (months + 1) % 12;
        if(months == 1)
            years++;
        
        return (String.format("%02d", months) + "/" + latestDate.substring(3, 4) + String.format("%03d", years));
    }
    
    
    private static String getGovernmentCodeForMeter(String meterCode){
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("select GovernmentCode from OldCustomer "
                    + "where MeterCode = ? order by Num DESC limit 1");
        ){
            
            ps.setString(1, meterCode);
            ResultSet r = ps.executeQuery();
            r.next();
            
            return r.getString("GovernmentCode");
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return "";
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
            }else if (consumption<=200){
                moneyValue = consumption *1.20;
                tarrif=2;
            }else if (consumption<=600){
                moneyValue = consumption *1.40;
                tarrif=3;
            }else if (consumption >600 && consumption <=1000){
                moneyValue = 840 + (consumption-600) *1.55;
                tarrif=4;
            }else{
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
