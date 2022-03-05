/* for datapartition */
String command = "df -h";
int port = 22;

/* for gunzip */
String command = "gzip "+file_name; //[it is the location of the csv file that we want to zip]

InputStream responseString = DataBasePartition.listFolderStructure(username, password, host, port, command);
/* This method used to fetch the memory of the server using SSH connection. */
public static InputStream listFolderStructure(String username, String password, 
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
			     /*   BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream));
					  
			        while (buffReader.ready()) {
			            buffReader.readLine();
			           // System.out.println("----------------------------------------");
			        }
			        String responseString = new String(responseStream.toByteArray());*/
			        
			       //s log.info(inStream);
			        return inStream;
			    } finally {
			        if (session != null) {
			            session.disconnect();
			        }
			        if (channelExec != null) {
			        	channelExec.disconnect();
			        }
			    }
			}