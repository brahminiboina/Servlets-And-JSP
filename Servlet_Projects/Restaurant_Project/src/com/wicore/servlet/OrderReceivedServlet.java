package com.wicore.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wicore.data.MenuDataService;


@WebServlet("/orderReceived")
public class OrderReceivedServlet extends HttpServlet {
	
	MenuDataService menuDataService = new MenuDataService();
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int maxId = menuDataService.getFullMenu().size();
		for (int i = 0; i <maxId; i++) {
			String quantity = request.getParameter("item_" + i);
			 try  
			  {  
			    int q = Integer.parseInt(quantity);
			    if (q > 0) menuDataService.addToOrder(menuDataService.getItem(i), q);
			  }  
			  catch(NumberFormatException nfe)  
			  {  
			    //that's fine it just means there wasn't an order for this item 
			  }  
			  
		}
		
		System.out.println("A new order has been received.");
		
		Double total = menuDataService.getOrderTotal();
		
		HttpSession session = request.getSession();
		session.setAttribute("total", total);
		
		String redirectUrl = "thankYou";
		redirectUrl = response.encodeURL(redirectUrl);
		response.sendRedirect(redirectUrl);

	}
}
