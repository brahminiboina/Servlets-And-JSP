package com.wicore.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

//@WebServlet("/sq")
public class SqServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//int p =Integer.parseInt(request.getParameter("k"));
		int p =0;
		 Cookie[] cookies = request.getCookies();
		for(Cookie ck:cookies) {
			if(ck.getName().equals("result")) {
				p=Integer.parseInt(ck.getValue());
			}
		}
	//	HttpSession session = request.getSession();
		//int p = (int) session.getAttribute("result");
		//int p = (int)request.getAttribute("result");
		
		int q = p*p;
		PrintWriter out = response.getWriter();
		out.println("<html><body bgcolor='cyan' >");
		out.println("The Square of " +p+" is : "+q);
		out.println("</html></body>");
	}

}
