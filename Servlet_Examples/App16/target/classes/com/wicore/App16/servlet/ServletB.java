package com.wicore.App16.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet(name="serv2", value="/s2")
public class ServletB extends GenericServlet{
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		out.println("<h1>request parameters and its values");
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String pn = e.nextElement();
			out.println("<br><u>"+pn+"</u>");
			String pv[];
			pv=request.getParameterValues(pn);
			for(int i=o;i<pv.length;i++) {
				out.println("<br>"+pv[i]);
			}
		}
		out.flush();
		out.close();
	}
}
