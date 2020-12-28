package Datebase;
import java.sql.*;

public class OldCustomerDatabase {
    
    
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
    
    
    
    public static String getTypeOfUse(String meterCode){
        return NewCustomerDatabase.getTypeOfUseFromNC(meterCode);
    }
    
    
    
    public static int getConsumption(String meterCode, int currentReading){
        return currentReading - getLastReading(meterCode);
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
    
    
}
