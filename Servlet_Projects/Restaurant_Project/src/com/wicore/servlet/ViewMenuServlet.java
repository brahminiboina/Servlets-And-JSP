package com.wicore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wicore.data.MenuDataService;
import com.wicore.domain.MenuItem;

@WebServlet("")
public class ViewMenuServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		MenuDataService menuDataService = new MenuDataService();
		List<MenuItem> menuItems = menuDataService.getFullMenu();
		
		out.println("<html><body><h1>Bhanu's Restaurant</h1>");
		out.println("<h2>Menu</h2><ul>");
		for (MenuItem menuItem : menuItems) {
			out.println("<li><p>" + menuItem + "</p></li><br/>");
		}
		out.println("</ul>");
		out.println("<a href='searchResults?searchTerm=chicken' >View all of our chicken dishes</a>");
		out.println("</body></html>");
		out.close();
	}
	
}
