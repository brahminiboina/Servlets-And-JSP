
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.log4j.Logger;

import com.wicore.wipay.postbill.PostBillBean;

public class WipayMail {
	public static String from="";
	public static String passwd="";
	static final int BUFFER = 2048;
	
	private static Logger log = Logger.getLogger(WipayMail.class.getName());

	public static void sendMail(PostBillBean postbillBean) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			//states - NEW, RENEWAL, UNSUB
			/* For getting message string of that merchant */
			String qry = "select mtc_template_id, mtc_template_name, mtc_template_body, mtc_from_address, mtc_to_address, mtc_cc_address, mtc_bcc_address, mtc_template_subject from mail_template_config where mtc_merchant_id = '"+postbillBean.getGroupId()+"' and mtc_template_name = '"+postbillBean.getState()+"'";
			ps = postbillBean.getCon().prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				String templateSubject = rs.getString("mtc_template_subject")!=null?rs.getString("mtc_template_subject"):"";
				String templateBody = rs.getString("mtc_template_body")!=null?rs.getString("mtc_template_body"):"";
				String fromAddress = rs.getString("mtc_from_address")!=null?rs.getString("mtc_from_address"):"";
				String toAddressFromConfig = rs.getString("mtc_to_address")!=null?rs.getString("mtc_to_address"):"";
				String ccAddress = rs.getString("mtc_cc_address")!=null?rs.getString("mtc_cc_address"):"";
				String bccAddress = rs.getString("mtc_bcc_address")!=null?rs.getString("mtc_bcc_address"):"";
				
				/* Gets customer name associated to mail id */
				String customerName = getNameOfMail(postbillBean);
				
				String toAddress = postbillBean.getMSISDN();
				if (!toAddressFromConfig.equals("")) {
					toAddress = postbillBean.getMSISDN()+";"+toAddressFromConfig;
				}
				
				templateBody = templateBody.replaceAll("##TITLE##", postbillBean.getTitle()).replaceAll("##PRICE##", postbillBean.getPrice()+"").replaceAll("##ACCESS_URL##", postbillBean.getAccessURL()).replaceAll("##RENEWAL_DATE##", postbillBean.getValidTo()+"".split(" ")[0]);
				
				sendMail(postbillBean.getMSISDN(), postbillBean.getCon(), fromAddress, toAddress, ccAddress, bccAddress, templateSubject, templateBody, customerName);
				
				sendMail(postbillBean.getGroupId(), postbillBean.getMSISDN(), postbillBean.getCon(), fromAddress, toAddress, ccAddress, bccAddress, templateSubject, templateBody, customerName);
			} else {
				log.info("M : "+postbillBean.getMSISDN()+" | Invalid configuration..");
			}
			if (rs!=null) {rs.close();rs=null;}
			if (ps!=null) {ps.close();ps=null;}
		} catch (Exception e) {
			log.error("M : "+postbillBean.getMSISDN()+" | Exception in sendMail : "+e);
			e.printStackTrace();
		}
	}
	
	//For getting name of mail id
	public static String getNameOfMail(PostBillBean postbillBean) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		try {
			/* For getting message string of that merchant */
			String qry = "select wui_given_name from wipay_user_info where wui_user_id = '"+postbillBean.getMSISDN()+"'";
			ps = postbillBean.getCon().prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("wui_given_name")!=null?rs.getString("wui_given_name"):"";
			}
			if (rs!=null) {rs.close();rs=null;}
			if (ps!=null) {ps.close();ps=null;}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
		
	public static void sendMail(String msisdn, Connection con, String from, String toAddress, String ccAddress, String bccAddress, String subject, String bodyText, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String host = "";
		String port = "";
		String mails = "";
		try {
			
			ps = con.prepareStatement("SELECT s_value,s_field FROM settings where display_title='SMTP'");
			rs = ps.executeQuery();
			while(rs.next())
			{	
				String str = rs.getString("s_field");
				if (str.equalsIgnoreCase("Host"))  host=rs.getString("s_value");
				if (str.equalsIgnoreCase("Port"))  port=rs.getString("s_value");
				if (str.equalsIgnoreCase("From"))  WipayMail.from=from;					
			}
			rs.close();ps.close();
			
			ps = con.prepareStatement("SELECT decode(s_value,'wicore') s,s_field FROM settings where display_title='SMTP'");
			rs = ps.executeQuery();
			while(rs.next()) {
				String temp = rs.getString("s_field");
				if (temp.equalsIgnoreCase("Password"))  passwd = rs.getString("s");
			}
			if(rs!=null){rs.close();rs=null;}
			if(ps!=null){ps.close();ps=null;}
			
			
			log.info("host : "+host+" | port : "+port+" | from : "+WipayMail.from+" | passwd : "+passwd);
			
			// Get system properties
		    Properties properties = System.getProperties();
	
		    // Setup mail server
		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port); 
			props.put("mail.smtp.auth", "true");
			
		
			javax.mail.Session session = javax.mail.Session.getInstance(props,new javax.mail.Authenticator(){protected javax.mail.PasswordAuthentication getPasswordAuthentication() {return new javax.mail.PasswordAuthentication(WipayMail.from,passwd);}});
		

			//Session.getInstance(props, authenticator)
			
			// Get the default Session object.
			// Session session = Session.getDefaultInstance(properties);
	    
			// Create a default MimeMessage object.
	        MimeMessage message = new MimeMessage(session);

	        // Set From: header field of the header.
	        message.setFrom(new InternetAddress(from));
			
	        if (toAddress!=null && !toAddress.equals("")) {
	        	int x =0;
				int tokens = 0;
				StringTokenizer st = new StringTokenizer(toAddress,";");
				tokens = st.countTokens();
				String toList[] = new String[tokens];
				while (st.hasMoreTokens()){
					toList[x] = st.nextToken().toString().trim();
					x++;
				}
				x=0;
				InternetAddress[] addressTo = new InternetAddress[toList.length]; 
			    for (int z = 0; z < toList.length; z++)
				{
			        addressTo[z] = new InternetAddress(toList[z]);
				}	
		    	// Set To: header field of the header.
		        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.addRecipients(Message.RecipientType.TO, addressTo);
	        } else {
	        	log.info("M : "+msisdn+" | toAddress is empty..");
	        }
	        
	        /* ccAddress */
	        if (ccAddress!=null && !ccAddress.equals("")) {
	        	int x =0;
				int tokens = 0;
				StringTokenizer st = new StringTokenizer(ccAddress,";");
				tokens = st.countTokens();
				String ccList[] = new String[tokens];
				while (st.hasMoreTokens()){
					ccList[x] = st.nextToken().toString().trim();
					x++;
				}
				x=0;
				InternetAddress[] addressTo = new InternetAddress[ccList.length]; 
			    for (int z = 0; z < ccList.length; z++)
				{
			        addressTo[z] = new InternetAddress(ccList[z]);
				}	
		    	// Set To: header field of the header.
		        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.addRecipients(Message.RecipientType.CC, addressTo);
	        } else {
	        	log.info("M : "+msisdn+" | ccList is empty..");
	        }
	        
	        /* bccAddress */
	        if (bccAddress!=null && !bccAddress.equals("")) {
	        	int x =0;
				int tokens = 0;
				StringTokenizer st = new StringTokenizer(bccAddress,";");
				tokens = st.countTokens();
				String bccList[] = new String[tokens];
				while (st.hasMoreTokens()){
					bccList[x] = st.nextToken().toString().trim();
					x++;
				}
				x=0;
				InternetAddress[] addressTo = new InternetAddress[bccList.length]; 
			    for (int z = 0; z < bccList.length; z++)
				{
			        addressTo[z] = new InternetAddress(bccList[z]);
				}	
		    	// Set To: header field of the header.
		        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.addRecipients(Message.RecipientType.BCC, addressTo);
	        } else {
	        	log.info("M : "+msisdn+" | BccList is empty..");
	        }

			// Set Subject: header field
	        message.setSubject(subject);

	        //Now set the actual message
	        //message.setText("This is actual message");
	       
	        //Attachment
	        String body =" <br><p>Dear "+name+","+bodyText;
	        log.info("Body : "+body);
	        /*
	        String body =" <br> <p>Dear "+name+",</p><br><br><p>"+bodyText+"<p><br><br>Regards,</p><p style=\"margin-top:-18px\">"+regardsText+"</p>";
	        */
	        
	        log.info("Body : "+body);
	        MimeBodyPart messageBodyPart =  new MimeBodyPart();
			messageBodyPart.setText(body,"UTF-8");
			messageBodyPart.setHeader("Content-Type", "text/html; charset=UTF-8");	
			Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
		    message.setContent(multipart);
	        
	        // Send message
	        Transport.send(message);
	        log.info("Sent message successfully....");
	     }catch (Exception mex) {
	    	log.error("Exception while sending messages : "+mex);
	        mex.printStackTrace();
	     }

	}
	
	
	
	
	
	
	
	public static void sendMail(int mid, String msisdn, Connection con, String from, String toAddress, String ccAddress, String bccAddress, String subject, String bodyText, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String host = "";
		String port = "";
		String mails = "";
		String cert_name = "";
		String cert_path = "";
		String socket_fact_port = "";
		String auth= "";
		String IsSSL = "";
		try {
			
			ps = con.prepareStatement("SELECT wss_server, wss_port, wss_password, wss_authentication, wss_is_ssl, wss_ssl_certificate_name, wss_ssl_certifcate_path, wss_socket_factory_port from wipay_smtp_settings where wss_merchant_id = '"+mid+"' ");
			rs = ps.executeQuery();
			if(rs.next())
			{	
				
				 host=rs.getString("wss_server");
				 port=rs.getString("wss_port");
				 auth = rs.getString("wss_authentication");
				 IsSSL = rs.getString("wss_is_ssl");
				 cert_name = rs.getString("wss_ssl_certificate_name");
				 cert_path  = rs.getString("wss_ssl_certificate_path");
				 socket_fact_port = rs.getString("wss_socket_factory_port");
				 
				  
				  WipayMail.passwd = rs.getString("wss_password");
			}
		
			
			if(rs!=null){rs.close();rs=null;}
			if(ps!=null){ps.close();ps=null;}
			
			WipayMail.from=from;
			
			log.info("host : "+host+" | port : "+port+" | from : "+WipayMail.from+" | passwd : "+passwd+" |authentication : "+auth+" |IsSSL : "+IsSSL+" | certificate_name : "+cert_name+" | certificate_path : "+cert_path+" | socket_factory_port : "+socket_fact_port);
			
			// Get system properties
		    Properties properties = System.getProperties();
	
		    // Setup mail server
		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port); 
			
			if(IsSSL.equalsIgnoreCase("Y")) {
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
			}
			
			props.put("mail.smtp.auth", auth);
					
		
			javax.mail.Session session = javax.mail.Session.getInstance(props,new javax.mail.Authenticator(){protected javax.mail.PasswordAuthentication getPasswordAuthentication() {return new javax.mail.PasswordAuthentication(WipayMail.from,passwd);}});
		

			//Session.getInstance(props, authenticator)
			
			// Get the default Session object.
			// Session session = Session.getDefaultInstance(properties);
	    
			// Create a default MimeMessage object.
	        MimeMessage message = new MimeMessage(session);

	        // Set From: header field of the header.
	        message.setFrom(new InternetAddress(from));
			
	        if (toAddress!=null && !toAddress.equals("")) {
	        	int x =0;
				int tokens = 0;
				StringTokenizer st = new StringTokenizer(toAddress,";");
				tokens = st.countTokens();
				String toList[] = new String[tokens];
				while (st.hasMoreTokens()){
					toList[x] = st.nextToken().toString().trim();
					x++;
				}
				x=0;
				InternetAddress[] addressTo = new InternetAddress[toList.length]; 
			    for (int z = 0; z < toList.length; z++)
				{
			        addressTo[z] = new InternetAddress(toList[z]);
				}	
		    	// Set To: header field of the header.
		        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.addRecipients(Message.RecipientType.TO, addressTo);
	        } else {
	        	log.info("M : "+msisdn+" | toAddress is empty..");
	        }
	        
	        /* ccAddress */
	        if (ccAddress!=null && !ccAddress.equals("")) {
	        	int x =0;
				int tokens = 0;
				StringTokenizer st = new StringTokenizer(ccAddress,";");
				tokens = st.countTokens();
				String ccList[] = new String[tokens];
				while (st.hasMoreTokens()){
					ccList[x] = st.nextToken().toString().trim();
					x++;
				}
				x=0;
				InternetAddress[] addressTo = new InternetAddress[ccList.length]; 
			    for (int z = 0; z < ccList.length; z++)
				{
			        addressTo[z] = new InternetAddress(ccList[z]);
				}	
		    	// Set To: header field of the header.
		        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.addRecipients(Message.RecipientType.CC, addressTo);
	        } else {
	        	log.info("M : "+msisdn+" | ccList is empty..");
	        }
	        
	        /* bccAddress */
	        if (bccAddress!=null && !bccAddress.equals("")) {
	        	int x =0;
				int tokens = 0;
				StringTokenizer st = new StringTokenizer(bccAddress,";");
				tokens = st.countTokens();
				String bccList[] = new String[tokens];
				while (st.hasMoreTokens()){
					bccList[x] = st.nextToken().toString().trim();
					x++;
				}
				x=0;
				InternetAddress[] addressTo = new InternetAddress[bccList.length]; 
			    for (int z = 0; z < bccList.length; z++)
				{
			        addressTo[z] = new InternetAddress(bccList[z]);
				}	
		    	// Set To: header field of the header.
		        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.addRecipients(Message.RecipientType.BCC, addressTo);
	        } else {
	        	log.info("M : "+msisdn+" | BccList is empty..");
	        }

			// Set Subject: header field
	        message.setSubject(subject);

	        //Now set the actual message
	        //message.setText("This is actual message");
	       
	        //Attachment
	        String body =" <br><p>Dear "+name+","+bodyText;
	        log.info("Body : "+body);
	        /*
	        String body =" <br> <p>Dear "+name+",</p><br><br><p>"+bodyText+"<p><br><br>Regards,</p><p style=\"margin-top:-18px\">"+regardsText+"</p>";
	        */
	        
	        log.info("Body : "+body);
	        MimeBodyPart messageBodyPart =  new MimeBodyPart();
			messageBodyPart.setText(body,"UTF-8");
			messageBodyPart.setHeader("Content-Type", "text/html; charset=UTF-8");	
			Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
		    message.setContent(multipart);
	        
	        // Send message
	        Transport.send(message);
	        log.info("Sent message successfully....");
	     }catch (Exception mex) {
	    	log.error("Exception while sending messages : "+mex);
	        mex.printStackTrace();
	     }

	}
	
	
		
}
