package com.wicore.empser;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class servlet1
 */

public class servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		process(request,response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) {
		try{ 
			System.out.println("hii");
			PrintWriter out = response.getWriter(); 
			URL url=new URL("http://localhost:8080/employeedetails/ser2");    
			HttpURLConnection huc=(HttpURLConnection)url.openConnection();
			//URLConnection  ref =url.openConnection();
			InputStream is =  huc.getInputStream();
			//InputStream is =  ref.getInputStream();
			
			
		       
			   out.write("hii bhanu");
				
				
			    int i;
				while((i = is.read())!=-1) {
					//out.write("hiii bhanu");
					out.write((char)i);
					
				}
				
			    
			    
		
			huc.disconnect();
			
		}catch(Exception e){
			
			System.out.println(e);
			
			}    
}    


}
