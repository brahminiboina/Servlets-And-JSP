package com.wicore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wicore.data.MenuDataService;
import com.wicore.domain.MenuItem;


@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><h1>Bhanu's Restaurant</h1>");
		out.println("<h2>Order your food</h2>");
		
		out.println("<form action='orderReceived' method='POST' >");
		out.println("<ul>");
		
		MenuDataService menuDataService = new MenuDataService();
		List<MenuItem> menuItems = menuDataService.getFullMenu();
		
		for (MenuItem menuItem : menuItems) {
			out.println("<li>" + menuItem + "<input type='text' name='item_" +menuItem.getId() +"' /> </li>");
		}
		
		out.println("</ul>");
		out.println("<input type='submit' />");
		out.println("</form></body></html>");
		out.close();
	}
}
