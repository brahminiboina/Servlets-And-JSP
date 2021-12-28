package com.wicore.app18;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.GenericSignatureFormatError;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="serva", value="/s1")
public class ServletA extends GenericServlet {
	public void service(ServletRequest request, ServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		out.println("<h1>Response from ServletA");
		out.flush();
		out.close();
	}
	

}
