public static JSONObject fireURL(JSONObject jsonObj,String Dest_IP) //jsoon object and url where we want to hit.
	{	/*used in MBill */
		HttpURLConnection huc  = null;
		BufferedReader in =null;		
		StringBuffer response = new StringBuffer();
		DataOutputStream wr =null;	
		URL url=null;
		JSONObject jsonResp=null;
		try
		{	url = new URL(Dest_IP);
			huc  = (HttpURLConnection)url.openConnection();
			huc.setRequestMethod("POST");
			huc.setRequestProperty("Content-Type", "application/json");
			huc.setConnectTimeout(60000);
			huc.setReadTimeout(60000);		
			huc.setDoOutput(true);
			wr= new DataOutputStream(huc.getOutputStream());
			log.info("jsonObj.toString() :: "+jsonObj.toString());			
			wr.writeBytes(jsonObj.toString());
			wr.flush();
			int responseCode = huc.getResponseCode();
			log.info("Dest_IP : "+Dest_IP+" | Req.Params : " +jsonObj.toString()+" | RC : "+responseCode); 
				in = new BufferedReader(new InputStreamReader(huc.getInputStream()));
				String inputLine; 
				while ((inputLine = in.readLine()) != null) 
				{	
					log.info("inputLine:"+inputLine);
					response.append(inputLine);
				}
			if(!response.equals(""))
			{	jsonResp = new JSONObject(response.toString());	
			}	
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally
		{	try{
				if(wr != null) 
				{	wr.close();wr = null;
				}
				if (in != null) 
				{	in.close();
					in = null;
				}
				if (huc != null) 
				{	huc = null;
				}		
			}catch(Exception e)
			{
			e.printStackTrace();
			}
		}
		log.info(" R:"+((jsonResp!=null)?jsonResp.toString():"null")); 	
		return jsonResp;
	}	
  }