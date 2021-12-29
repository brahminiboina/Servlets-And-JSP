package com.wicore.testcases;

import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SoapTest {
	public static void main(String[] args) {
		
		LinkedHashMap<String,String> lmap=new LinkedHashMap<String,String>();
		String mt_message="Hello Vi"; //I think it has to be commented. @Brahmini127
		String mobileNumber = "9398376719";
		String shortCode = "55321";
		
		lmap.put("mobileNumber", mobileNumber);
		lmap.put("shortCode", shortCode);
		lmap.put("msg_type","text");
		lmap.put("msg", mt_message);
		/*lmap.put("mo_keyword", mo_keyword);
		lmap.put("developer_content_id", developer_content_id);
		lmap.put("content_type", content_type);			
		lmap.put("msgid", transId+"");*/
		String xmlMsg=prepareViMTSOAP(lmap);
		System.out.println("xmlMsg : " +xmlMsg);
	}
	private static String prepareViMTSOAP(LinkedHashMap<String, String> lmap) {
		try{  

	        // Create SOAP Connection
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	       // SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	      // createSOAPRequest();
	        // Send SOAP Message to SOAP Server
	       // String url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
	      // SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

	        // print SOAP Response
	       // System.out.print("Response SOAP Message:");
	        //soapResponse.writeTo(System.out);

	       // soapConnection.close();
	   // }

	  //  private static SOAPMessage createSOAPRequest() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://eai.vodafone.com/sendSmsMSDP";
	        String serverURI2 = "http://eai.vodafone.com/MetaInfoReq";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("sen", serverURI);
	        envelope.addNamespaceDeclaration("met", serverURI2);
	        

	        /*
	         Constructed SOAP Request Message:
	         <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
	         <SOAP-ENV:Header/>
	         <SOAP-ENV:Body>
	         <example:VerifyEmail>
	         <example:email>mutantninja@gmail.com</example:email>
	         <example:LicenseKey>123</example:LicenseKey>
	         </example:VerifyEmail>
	         </SOAP-ENV:Body>
	         </SOAP-ENV:Envelope>
	         */
	        //SOAP Header
	        SOAPHeader header = envelope.getHeader();
	       // header.addTextNode("Training Details");
	        SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
	        SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
	        SOAPElement username = usernameToken.addChildElement("Username", "wsse");
	        username.addTextNode("SCRMUser");
	        SOAPElement password = usernameToken.addChildElement("Password", "wsse");
	        password.addTextNode("8571a3fbcfbbb6b748e1131d966e51b4");

	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElement = soapBody.addChildElement("SendSmsMSDPRequest", "sen");
	        
	        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("MetaInfo", "sen");
	         
	        SOAPElement soapBodyElem1 = soapBodyElement1.addChildElement("ConsumerReqInfo", "met");
	        
	        SOAPElement soapBodyElem11 = soapBodyElem1.addChildElement("circleId", "met");
	        soapBodyElem11.addTextNode("23");
	        SOAPElement soapBodyElem12 = soapBodyElem1.addChildElement("serviceName", "met");
	        soapBodyElem12.addTextNode("sendSmsMSDP");
	        SOAPElement soapBodyElem13 = soapBodyElem1.addChildElement("channelName", "met");
	        soapBodyElem13.addTextNode("WICORE");
	        SOAPElement soapBodyElem14 = soapBodyElem1.addChildElement("segment", "met");
	        soapBodyElem14.addTextNode("PREPAID");
	        SOAPElement soapBodyElem15 = soapBodyElem1.addChildElement("key", "met");
	        soapBodyElem15.addTextNode("123");
	        SOAPElement soapBodyElem16 = soapBodyElem1.addChildElement("version", "met");
	        soapBodyElem16.addTextNode("1.0");
	        
	        SOAPElement soapBodyElement2 = soapBodyElement.addChildElement("SRVsendSmsMSDPReq", "sen");
	        
	        SOAPElement soapBodyElem21 = soapBodyElement2.addChildElement("requestType", "sen");
	        soapBodyElem21.addTextNode("SMSSubmitReq");
	        SOAPElement soapBodyElem22 = soapBodyElement2.addChildElement("userName", "sen");
	        soapBodyElem22.addTextNode("sdpmgeaimt");
	        SOAPElement soapBodyElem23 = soapBodyElement2.addChildElement("password", "sen");
	        soapBodyElem23.addTextNode("dN9B@eUT");
	        
	     /*   SOAPElement soapBodyElem24 = soapBodyElement2.addChildElement("mobileNumber", "sen");
	        soapBodyElem24.addTextNode("9700425814");
	        SOAPElement soapBodyElem25 = soapBodyElement2.addChildElement("message", "sen");
	        soapBodyElem25.addTextNode("Hello Vi");
	        SOAPElement soapBodyElem26 = soapBodyElement2.addChildElement("originAddress", "sen");
	        soapBodyElem26.addTextNode("55321"); */
	        
	     /*   SOAPElement soapBodyElem24 = soapBodyElement2.addChildElement("mobileNumber",
         		   "sen");
            soapBodyElem24.addTextNode(lmap.get("mobileNumber"));
            SOAPElement soapBodyElem25 = soapBodyElement2.addChildElement("message", 
         		   "sen");
            soapBodyElem25.addTextNode(lmap.get("msg"));
            SOAPElement soapBodyElem26 = soapBodyElement2.addChildElement("originAddress",
         		   "sen");
            soapBodyElem26.addTextNode(lmap.get("SourseCode")); */
	        
	        SOAPElement soapBodyElem24 = soapBodyElement2.addChildElement("mobileNumber", "sen");
            if (lmap.get("mobileNumber") != null && !lmap.get("mobileNumber").equals("")) {
			       soapBodyElem24.addTextNode(lmap.get("mobileNumber"));
		       }
            SOAPElement soapBodyElem25 = soapBodyElement2.addChildElement("message", "sen");
		       if (lmap.get("message") != null && !lmap.get("message").equals("")) {
			       soapBodyElem25.addTextNode(lmap.get("message"));
		       }
		       SOAPElement soapBodyElem26 = soapBodyElement2.addChildElement("originAddress", "sen");
		       if (lmap.get("shortCode") != null && !lmap.get("shortCode").equals("")) {
			       soapBodyElem26.addTextNode(lmap.get("shortCode"));
		       }
	        SOAPElement soapBodyElem27 = soapBodyElement2.addChildElement("type", "sen");
	        soapBodyElem27.addTextNode("0");

	       
	        soapMessage.saveChanges();

	        /* Print the request message */
	       // System.out.print("Request SOAP Message:");
	       // soapMessage.writeTo(System.out);
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        soapMessage.writeTo(out);
	        String strMsg = new String(out.toByteArray());
	        System.out.print("Request SOAP Message:" +strMsg);
	        System.out.println("");
	        return strMsg;
	      //  return soapMessage;
	    
		}catch (Exception e) {
			System.out.println("exception: "+e);
			return "hii you got an exception";
		}
	}


}
