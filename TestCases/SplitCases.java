package com.wicore.testcases;

public class SplitCases {
	public static void main(String[] args) {
		String sms_billing_auth_info = "sdpmgeaimt:dN9B@eUT";
		String username1= "", password1="" ; 
        username1 = sms_billing_auth_info.split(":")[0];
        password1 = sms_billing_auth_info.split(":")[1];
        System.out.println("username::: " +username1); //displays sdpmgeaimt
        System.out.println("password::: " +password1); //displays dN9B@eUT.
        String  auth = "NANO_01&oqPCRc+cEKDqkfFtiOzmPg==&cpid=FG&accountType=EMBED";
        String[] authinfo =auth.split("&");
        String path = "/logos/10/1/4/fileUpload.html";
        String filename = (path.length()>9)?path.split("/")[9]:"";
        String filename2 = (path.length()>1)?path.split("customers")[1]:"";
        System.out.println("filename::: " +filename2);
        String username2 = authinfo[0] ;
		String password2 =authinfo[1];                
        String cpidvalue = (authinfo[2].split("="))[1];		
        String accountType = (authinfo[3].split("="))[1];
        System.out.println("accountType::: " +accountType);
        
        String contentlink = "https://dev.wicore.in/wiapp/Index?qs=[{sp_id:3,sp_name:Famobi,data:{CATEGORIES:23}}]&service_id=2&cust_id=10&page=I";
        String link = contentlink.split("\\?")[1];
        String qs1 = link.split("&")[0];
		String service_id1 = link.split("&")[1];
		String cust_id1 = link.split("&")[2];
		String page1 = link.split("&")[3];
		System.out.println (" link : " +link+ " | qs : " +qs1+" | service_id1: " +service_id1+ " | cust_id1: "+cust_id1+ " | page1: " +page1);
		
		String qs = qs1.split("=")[1];
		System.out.println("QS: " +qs);
		String service_id = service_id1.split("=")[1];
		System.out.println("service_id: " +service_id);
		String cust_id = cust_id1.split("=")[1];
		System.out.println("cust_id: " +cust_id);
		String page = page1.split("=")[1];
		System.out.println("page: " +page);
		
		String category = qs.split(":")[4].replace("}}]", "").trim();
		System.out.println("category: " +category);
		
	}

}
