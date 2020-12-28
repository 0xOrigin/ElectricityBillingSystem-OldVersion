package Datebase;
import java.sql.*;
import Datebase.OldCustomerDatabase;

public class AdministratorDatabase {


    public static void collectPayments(double moneyValue){
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("update TotalCollected set TotalColl = TotalColl + ?");
        ){

            ps.setDouble(1, moneyValue);

            ps.executeUpdate();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    
    public static double viewTotalCollected(){
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("select TotalColl FROM TotalCollected");
        ){

            ResultSet r = ps.executeQuery();
            
            return r.getDouble("TotalColl");
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return 0;
    }
    
    
    public static void addAdministrator(
            
            String name, String nationalID, String address, String email, String phoneNumber, String gender,
            String dateOfBirth, String administratorID, String administratorPass, String contractDate, String administratorRole
    
    ){
        
        String sqlAdd = "insert into Administrators (Name, NationalID, Address, Email, PhoneNumber, Gender, DateOfBirth, AdministratorID, AdministratorPass, ContractDate, AdministratorRole) "
                   + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement(sqlAdd);
        ){
            
            ps.setString(1, name);
            ps.setString(2, nationalID);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, phoneNumber);
            ps.setString(6, gender);
            ps.setString(7, dateOfBirth);
            ps.setString(8, administratorID);
            ps.setString(9, administratorPass);
            ps.setString(10, contractDate);
            ps.setString(11, administratorRole);
            
            ps.execute();
             
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
    }
    
    
    public static void deleteCustomer(String meterCode){
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("delete FROM NewCustomer where MeterCode = ?");
        ){
            
            ps.setString(1, meterCode);
            
            ps.execute();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void deleteAdministrator(String administratorID){
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("delete FROM Administrators where AdministratorID = ?");
        ){
            
            ps.setString(1, administratorID);
            
            ps.execute();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void updateCustomer(final String columnName, String value, String meterCode){
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("update NewCustomer set " + columnName + " = ? where MeterCode = ?");
        ){
            
            ps.setString(1, value);
            ps.setString(2, meterCode);
            
            ps.execute();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
    public static void updateAdministrator(final String columnName, String value, String administratorID){
        
        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("update Administrators set " + columnName + " = ? where AdministratorID = ?");
        ){
            
            ps.setString(1, value);
            ps.setString(2, administratorID);
            
            ps.execute();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
