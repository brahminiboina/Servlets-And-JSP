package com.wicore.app25;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="serv2", value="/s2")
public class Servlet2 extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		System.out.println("Servlet2 service begin");
		out.println("<h1>Servlet2 res begin");
		out.println("<h1>Servlet2 res end");
		System.out.println("Servlet2 service end");
	}

}
