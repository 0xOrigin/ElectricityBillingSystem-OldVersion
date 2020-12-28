package Email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

public class Sendmail {
    
    //This method will send email if customer didn't pay for three months
    
    public static void unpaidEmail(String recepient, String name, String meterCode) throws Exception{
        
        
        try {
            
            Properties properties =new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");         //gmail host
            properties.put("mail.smtp.port", "587");                    //gmail port

            final String myAccountEmail= "PL2Project.EGKMS@gmail.com";  //sender mail
            final String password="PL2_123456";                         //sender password

            Session session=Session.getInstance(properties, new Authenticator(){

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }

            }); 

            Message message= unpaidBills(session,myAccountEmail,recepient, name, meterCode);
            Transport.send(message);
            
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println("\n\t[-] You haven't paid your bills for three months or more at the meter number: " + meterCode + " , please pay.");
        }
        
    }
    
    
    private static Message unpaidBills(Session session,String myAccountEmail,String recepient, String name, String meterCode) throws UnsupportedEncodingException {
        
        try {
            
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail, "Electricity Company"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Alert: bills are accumulated!");  //subject
            message.setText("\nDear "+ name +",\n\n\tYou haven't paid your bills for three months or more at the meter number: " + meterCode + " , please pay.");    //mail, you can edit this
            
            return message;
            
        } catch (MessagingException ex) {
            System.out.println("\n\t[-] You haven't paid your bills for three months or more at the meter number: " + meterCode + " , please pay.");
        }
        
        return null;
    }
    
    
    //This method will send Email if meter is ready.
    //To excute this method type Sendmail.meterReady("xxxx@gmail.com", "name", "materCode");
    
    
    public static void meterReady(String recepient, String name, String meterCode){
        
        System.out.println("\n[-] Please wait a moment.");
        
        try {
            
            Properties properties =new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");         //gmail host
            properties.put("mail.smtp.port", "587");                    //gmail port

            final String myAccountEmail= "PL2Project.EGKMS@gmail.com";  //Sender mail
            final String password="PL2_123456";                         //sender password

            Session session=Session.getInstance(properties, new Authenticator(){

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }

            }); 

            Message message= meterReady(session,myAccountEmail,recepient, name, meterCode);
            Transport.send(message);
            
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println("\n\t[-] Your meter code is: " + meterCode + " . You can pay bills using this code only.");
        }
        
    }

    

    //Method for the ready meter code
    
    private static Message meterReady(Session session, String myAccountEmail, String recepient, String name, String meterCode) throws UnsupportedEncodingException {
        
        String mailMessege = "\nDear " + name + ", meter is ready.\n" 
                + "\n\tYour meter code is: " + meterCode + " . You can pay bills using this code only.";
        
        try {
            
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail, "Electricity Company"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Your meter is ready!");     //Subject
            message.setText(mailMessege);                   //Mail, You can edit this
            
            return message;
            
        } catch (MessagingException ex) {
            System.out.println("\n\t[-] Your meter code is: " + meterCode + " . You can pay bills using this code only.");
        }
        
        return null;
    }
}

