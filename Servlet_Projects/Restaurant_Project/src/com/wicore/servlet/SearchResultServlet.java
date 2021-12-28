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
@WebServlet("/searchResults")
public class SearchResultServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String searchTerm = request.getParameter("searchTerm");
		
		MenuDataService menuDataService = new MenuDataService();
		List<MenuItem> menuItems = menuDataService.find(searchTerm);
		
		if (menuItems.size() > 0) {
		
			out.println("<html><body><h1>Bhanu's Restaurant</h1>");
			out.println("<h2>Dishes containing " + searchTerm + "</h2><ul>");
			for (MenuItem menuItem : menuItems) {
				out.println("<li>" + menuItem + " " + menuItem.getDescription() + "</li>");
			}
			
			out.println("</ul></body></html>");
		}
		else {
			out.println("<html><body><h1>Bhanu's Restaurant</h1>");
			out.println("<p>I'm sorry, there are no dishes containing " + searchTerm);
			out.println("</p></body></html>");
		}
		out.close();
	}
}