package com.wicore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet(name="serv1", value="/s1")
public class ServletA extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		String a[] = request.getParameterValues("course");
		int fees = 0;
		for(int i=0;i<a.length;i++) {
			if(a[i].equals("java")) {
				fees+=4500;
			}
			if(a[i].equals("oracle")) {
				fees+=1500;
			}
			if(a[i].equals("c")) {
				fees+=2100;
			}
			out.println("<h1><br>course:" +a[i]);
		}
		out.println("<br>fees to be paid: "+fees);
		out.flush();
		out.close();
	}

}
