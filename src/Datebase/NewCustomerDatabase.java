package Datebase;
import java.io.*;
import java.sql.*;

public class NewCustomerDatabase {
  
    // --------------------------------------------- Public functions --------------------------------------------- //
    
    public static void insertNewCustomer(
            
            String name, String nationalID, String address, String email, String governmentCode, String phoneNumber, String gender,
            String dateOfBirth, String typeOfUse, String meterCode, String contractDate
            
    ){
        
        String sqlNC = "insert into NewCustomer (Name, NationalID, Address, Email, GovernmentCode, PhoneNumber, Gender, DateOfBirth, TypeOfUse, MeterCode, ContractDate) "
                   + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlOC = "insert into OldCustomer (GovernmentCode, MeterCode, Tariff, PastReading, CurrentReading, Consumption, MoneyValue, Status, DateOfBill)" 
                   + "values(?, ?, 0, 0, 0, 0, 0.0, 'Paid', ?)";
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement pss = connect.prepareStatement(sqlOC);
            PreparedStatement ps = connect.prepareStatement(sqlNC);
        ){
            
            ps.setString(1, name);
            ps.setString(2, nationalID);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, governmentCode);
            ps.setString(6, phoneNumber);
            ps.setString(7, gender);
            ps.setString(8, dateOfBirth);
            ps.setString(9, typeOfUse);
            ps.setString(10, meterCode);
            ps.setString(11, contractDate);
            
            ps.execute();
            
            
            pss.setString(1, governmentCode);
            pss.setString(2, meterCode);
            pss.setString(3, contractDate.substring(8, 15));
            
            pss.execute();
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
    }
    
    
    public static String getTypeOfUse(String meterCode){
        
        try(Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT TypeOfUse from NewCustomer where MeterCode = ?");
        ){
            
            ps.setString(1, meterCode);
            ResultSet r = ps.executeQuery();
            
            return r.getString("TypeOfUse");
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    
        return "";
    }
    
    
    public static int getNumberOfCustomers(){
        
        int result = 0;
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT count(*) from NewCustomer");
        ){
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(1);
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    
        return result;
    }
    
    
    public static String getEmail(String meterCode){
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT Email from NewCustomer where MeterCode = ?");
        ){
            
            ps.setString(1, meterCode);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            return rs.getString("Email");
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return "";
    }
    
    
    public static String getName(String meterCode){
        
        try(
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT Name from NewCustomer where MeterCode = ?");
        ){
            
            ps.setString(1, meterCode);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            return rs.getString("Name");
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return "";
    }
    
    
    public static boolean isMeterCodeExists(String meterCode){
        
        try(Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT count(MeterCode) from NewCustomer where MeterCode = ?");
        ){
            
            ps.setString(1, meterCode);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            return (rs.getInt(1) > 0);
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    
   
        return false;
    }
    
    
    public static void attachApartmentContract(String filename, String meterCode){
        
        String updateSQL = "update NewCustomer set ApartmentContract = ? where MeterCode = ?";

        try(   
            Connection connect = DbConnection.connect();
            PreparedStatement ps = connect.prepareStatement(updateSQL)
        ){

            ps.setBytes(1, readFile(filename));
            ps.setString(2, meterCode);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    // --------------------------------------------- Private functions --------------------------------------------- //
    
    private static byte[] readFile(String file) {
        
        ByteArrayOutputStream bos = null;
        try {
            
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            
            for (int len; (len = fis.read(buffer)) != -1;)
                bos.write(buffer, 0, len);
            
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        
        return bos != null ? bos.toByteArray() : null;
    }
    
    
}
