package com.wicore.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int a = Integer.parseInt(request.getParameter("num1"));
		int b = Integer.parseInt(request.getParameter("num2"));
		int k = a+b;
		
		/*---------------------Cookies -------------------*/
		Cookie ck = new Cookie("result", k+"");
		response.addCookie(ck);
		response.sendRedirect("sq");
		
		/*---------------------Http Session -------------------*/
		//HttpSession session = request.getSession();
		//session.setAttribute("result", k);
		//response.sendRedirect("sq");
		
		/*---------------------Request Dispatcher and Redirect-------------------*/
		//	response.sendRedirect("sq?k="+k);
		//	request.setAttribute("result", k);
		//	RequestDispatcher rd = request.getRequestDispatcher("sq");
		//	rd.forward(request, response);
		//	PrintWriter out = response.getWriter();
		//	out.println("The result is: "+k);
	}
	


}
