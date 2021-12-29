package com.wicore.testcases;

public class SplitCases {
	public static void main(String[] args) {
		String sms_billing_auth_info = "sdpmgeaimt:dN9B@eUT";
		String username= "", password="" ; 
        username = sms_billing_auth_info.split(":")[0];
        password = sms_billing_auth_info.split(":")[1];
        System.out.println("username::: " +username);
        System.out.println("password::: " +password);
	}

}
