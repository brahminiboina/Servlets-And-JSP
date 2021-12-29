package com.wicore.main;

import org.apache.catalina.startup.Tomcat;

public class Main {

    public static void main(String[] args) throws Exception {
      
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addUser("matt", "pass");
        tomcat.addRole("matt", "admin");
        
        tomcat.start();
        tomcat.getServer().await();
    }
}