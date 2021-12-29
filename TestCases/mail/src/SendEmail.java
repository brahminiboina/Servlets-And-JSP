import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
public class SendEmail  
{  
 public static void main(String [] args){  
      String to = "brahminiboina7@gmail.com";//change accordingly  
      String from = "bhanuboina7@gmail.com";
      String host = "localhost";
      final String password="Brahmini@401";//or IP address  
  
     //Get the session object  
      Properties props = new Properties();   
      props.put("mail.smtp.host", "smtp.gmail.com");   
      props.put("mail.smtp.socketFactory.port", "8080");   
      props.put("mail.smtp.socketFactory.class",   
                "javax.net.ssl.SSLSocketFactory");   
     props.put("mail.smtp.auth", "true");   
     props.put("mail.smtp.port", "8080");
      Session session = Session.getDefaultInstance(props,  
    		    new javax.mail.Authenticator() {  
          protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(from,password);  
          }  
        });    
  
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to,false));  
         message.setSubject("Ping");  
         message.setText("Hello, this is example of sending email  ");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  
}  