package com.wicore.app22;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="serv1", value="/s1")
public class Servlet1 extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		String s1 = request.getParameter("n1");
		String s2 = request.getParameter("n2");
		int a = Integer.parseInt(s1);
		int b = Integer.parseInt(s2);
		int c = a+b;
		out.println("<h1>Sum = "+c);
		out.flush();
		out.close();
		
	}

}
