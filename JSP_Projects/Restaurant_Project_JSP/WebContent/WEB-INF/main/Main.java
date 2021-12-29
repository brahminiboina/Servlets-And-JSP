package com.wicore.main;

import org.apache.catalina.startup.Tomcat;

public class Main {

    public static void main(String[] args) throws Exception {
      
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addUser("bhanu", "brahmini");
        tomcat.addRole("bhanu", "user");
        tomcat.start();
        tomcat.getServer().await();
    }
}