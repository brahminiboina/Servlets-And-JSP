package com.wicore.testcases;
import com.jcraft.jsch.*;
import com.sun.midp.io.Properties;
import com.sun.midp.io.SystemOutputStream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LinuxDiskSpaceDetail {
	public static void main(String[] args) throws Exception {
		String username = "brahmini";
		String password = "Boina@w1c0r3";
		String host = "202.65.157.186";
		//String command = "df -h";
		String file_name =  "/data/backupcsv/sms_history_20211224_20220112142820.csv";
		String fileName = file_name.split("/")[3].toString();
		System.out.println("fileName :" +fileName);
		String command = "gunzip "+file_name;
		/* FileInputStream stream=new FileInputStream("/usr/appserver/WiPay/log.cfg"); 
		 System.out.println("filepath::"+stream.toString());
			Properties pr=new Properties();
			*/
		
		listFolderStructure(username, password, host, 22, command);
	}
	public static void listFolderStructure(String username, String password, 
			  String host, int port, String command) throws Exception {
			    
			    Session session = null;
			    ChannelExec channelExec = null;
			    
			    try {
			        session = new JSch().getSession(username, host, port);
			        session.setPassword(password);
			        java.util.Properties config = new java.util.Properties(); 
			        config.put("StrictHostKeyChecking", "no");
			        session.setConfig(config); 
			        session.connect();
			        channelExec = (ChannelExec) session.openChannel("exec"); 
			        channelExec.setCommand(command);
			        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
			        channelExec.setOutputStream(responseStream);
			        channelExec.connect(); 
			        while ((channelExec).isConnected()) {
			    	   Thread.sleep(100); 
			        }
			        InputStream inStream = new ByteArrayInputStream( responseStream.toByteArray());
			        BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream));
					  
			        while (buffReader.ready()) {
			            System.out.println(buffReader.readLine());
			            System.out.println("----------------------------------------");
			        }
			        String responseString = new String(responseStream.toByteArray());
			        System.out.println(responseString);
			    } finally {
			        if (session != null) {
			            session.disconnect();
			        }
			        if (channelExec != null) {
			        	channelExec.disconnect();
			        }
			    }
			}

}
