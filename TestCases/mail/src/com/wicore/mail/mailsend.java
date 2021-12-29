package com.wicore.mail;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;


import javax.activation.*;  
  
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
class Mailer{
    public static void send(final String from,final String password,String to,String sub,String msg){
          //Get properties object
          Properties props = new Properties();
          props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.starttls.enable", "true");
          //props.put("mail.smtp.socketFactory.port", "587");
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "587");
          
          

          //get Session
          Session session = Session.getDefaultInstance(props,
           new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(from,password);
           }
          });
          //compose message
          try {
           MimeMessage message = new MimeMessage(session);
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject(sub);
           message.setText(msg);
           //send message
           Transport.send(message);
           System.out.println("message sent successfully");
          } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}
public class mailsend{
 public static void main(String[] args) {
     //from,password,to,subject,message
Mailer.send("wicore1234@gmail.com","w!c0r3@wicore","nagaraju@wicore.com","This is a test subject","Hi, This is a test mail");
     //change from, password and to
 }
} 