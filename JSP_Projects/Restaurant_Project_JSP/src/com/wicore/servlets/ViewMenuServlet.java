package com.wicore.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wicore.data.MenuDao;
import com.wicore.data.MenuDaoFactory;
import com.wicore.data.MenuDataService;
import com.wicore.domain.MenuItem;
@WebServlet("")
public class ViewMenuServlet extends HttpServlet {
		public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			MenuDao menuDao = MenuDaoFactory.getMenuDao();
			List<MenuItem> menuItems;
			menuItems = menuDao.getFullMenu();
			request.setAttribute("menuItems", menuItems);
			ServletContext context = getServletContext();
			RequestDispatcher dispatch = context.getRequestDispatcher("/ViewMenu.jsp");
			dispatch.forward(request, response);
			
			
	}
	

}
