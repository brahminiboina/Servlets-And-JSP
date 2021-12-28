package com.wicore.App17.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet(name="serv2", value="/s2")
public class ServletC extends GenericServlet{
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		Map<K, V> m =request.getParameterMap();
		String a[] = (String[])m.get("x");
		String b[] = (String[])m.get("y");
		out.println("<h1>");
		out.println("<br><u>x</u>");
		for(int i=0;i<a.length;i++) {
			out.println("<br>"+a[i]);
		}
		out.println("<br><u>x</u>");
		for(int i=0;i<b.length;i++) {
			out.println("<br>"+b[i]);
		}
		out.flush();
		out.close();
	}
}

