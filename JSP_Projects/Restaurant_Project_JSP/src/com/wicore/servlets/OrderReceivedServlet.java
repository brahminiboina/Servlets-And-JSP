package com.wicore.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wicore.data.MenuDao;
import com.wicore.data.MenuDaoFactory;
import com.wicore.data.MenuDataService;
import com.wicore.domain.Order;


@WebServlet("/orderReceived")
public class OrderReceivedServlet extends HttpServlet {
	
MenuDao menuDao = MenuDaoFactory.getMenuDao();
	
	public void service (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int maxId = menuDao.getFullMenu().size();
		Order order = menuDao.newOrder(request.getUserPrincipal().getName());
		for (int i = 0; i <maxId+1; i++) {
			String quantity = request.getParameter("item_" + i);
			 try  
			  {  
			    int q = Integer.parseInt(quantity);
			    if (q > 0) {
			    	menuDao.addToOrder(order.getId(), menuDao.getItem(i), q);
			    	order.addToOrder(menuDao.getItem(i), q);
			    }
			  }  
			  catch(NumberFormatException nfe)  
			  {  
			    //that's fine it just means there wasn't an order for this item 
			  }  
			  
		}
		
		System.out.println("A new order has been received.");
		
		HttpSession session = request.getSession();
		session.setAttribute("orderId", order.getId());
		
		String redirectUrl = "thankYou";
		redirectUrl = response.encodeURL(redirectUrl);
		response.sendRedirect(redirectUrl);

	}
}
