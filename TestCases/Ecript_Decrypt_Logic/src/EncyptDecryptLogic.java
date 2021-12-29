
public class EncyptDecryptLogic {
	static String username=null;
	static String password=null;
	
	public static String getEncString(String orgString){
		String encPass = "";
		try{
		String encryptionKey = "nanowicorelab@2011-2020begumpethyderabad";
	    StringEncrypter encrypter = 
	          new StringEncrypter( StringEncrypter.DESEDE_ENCRYPTION_SCHEME, encryptionKey );
	     encPass = encrypter.encrypt( orgString );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return encPass;
	}
	public static String getDecString(String orgString){
		String decPass = "";
		try{
			String encryptionKey = "nanowicorelab@2011-2020begumpethyderabad";
		    StringEncrypter encrypter = 
		          new StringEncrypter( StringEncrypter.DESEDE_ENCRYPTION_SCHEME, encryptionKey );
		    decPass = encrypter.decrypt( orgString );
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
		return decPass;
	}
	
	public static void main(String args[]) {
		try {
			String un = "root";
			//String pwd = "Mysql@123";
			String dbuname = "root";
			String dbpwd = "wicore123" + 
					"";
			String dbpwd1 = "LVEEMNjUDdYTY==" + 
					"";
			//String xx="fly";
			
			System.out.println("EncryptedStr : "+getEncString(dbuname));
			System.out.println("EncryptedStr : "+getEncString(dbpwd));
			
			//System.out.println("pavan:"+getEncString(xx));
			
			//System.out.println("DecryptedStrpw : "+getDecString("mH/snmhH2GNDbtuyWDMiAA=="));
			//System.out.println("DecryptedStrpwsdfes : "+getDecString(dbpwd1));
			//System.out.println("DecryptedStrdb : "+getDecString(dbuname));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}//class
