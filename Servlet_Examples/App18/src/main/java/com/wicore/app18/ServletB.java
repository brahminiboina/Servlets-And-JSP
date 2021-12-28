package com.wicore.app18;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="servb", value="/s2")
public class ServletB extends GenericServlet {
	public void service(ServletRequest request, ServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		out.println("<h1>Response from ServletB");
		out.flush();
		out.close();
	}

}
