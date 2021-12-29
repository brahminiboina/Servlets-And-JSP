package com.wicore.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Driver;
import com.wicore.domain.MenuCategory;
import com.wicore.domain.MenuItem;
import com.wicore.domain.Order;

public class MenuDao {

	public MenuDao() {
//		DatabaseBootstrap bootstrap = new DatabaseBootstrap();
//		bootstrap.initializeDatabase();
	}
	
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();

		try {	
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
		 Statement stm = conn.createStatement();
			ResultSet results = stm.executeQuery("SELECT * FROM orders");
			
			while (results.next()) {
				Order order = new Order();
				order.setId(results.getLong("id"));
				order.setStatus(results.getString("status"));
				Map<MenuItem,Integer> orderMap = convertContentsToOrderMap(results.getString("contents"));
				order.setContents(orderMap);
				order.setCustomer(results.getString("customer"));
				orders.add(order);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	private List<MenuItem> buildMenu(ResultSet results) throws SQLException {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		while (results.next()) {
			MenuItem menuItem = new MenuItem();
			menuItem.setId(results.getInt("id"));
			menuItem.setDescription(results.getString("description"));
			menuItem.setName(results.getString("name"));
			menuItem.setPrice(results.getDouble("price"));
			menuItem.setCategory(MenuCategory.valueOf(results.getString("category")));
			menuItems.add(menuItem);
		}
		return menuItems;
	}

	public List<MenuItem>  getFullMenu() {
		List<MenuItem> menuItems = null;
		try {	
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
		 Statement stm = conn.createStatement();
		 ResultSet results = stm.executeQuery("SELECT * FROM menuitems");
			menuItems = buildMenu(results);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItems;
	}

	public List<MenuItem> find(String searchString) {
		List<MenuItem> menuItems = null;
		try {
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root","wicore123");
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM menuitems WHERE name LIKE ? OR description LIKE ?");

			stm.setString(1, "%" + searchString + "%");
			stm.setString(2, "%" + searchString + "%");

			ResultSet results = stm.executeQuery();
			menuItems = buildMenu(results);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItems;
	}

	public MenuItem getItem(int id) {
		List<MenuItem> menuItems = null;
		try {	
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM menuitems WHERE id = ?");
			
			stm.setInt(1, id);

			ResultSet results = stm.executeQuery();
			menuItems = buildMenu(results);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItems.get(0);
	}


	public Order newOrder(String customer)  {
		Order order = new Order(); 
		order.setStatus("pending");
		order.setCustomer(customer);
		try{	
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
			PreparedStatement stm = conn.prepareStatement("INSERT INTO orders (status, customer) values (?,?)", Statement.RETURN_GENERATED_KEYS);
			
			
			stm.setString(1, order.getStatus());
			stm.setString(2,  order.getCustomer());
			stm.execute();
			
			try(ResultSet generatedKeys = stm.getGeneratedKeys()) {
				generatedKeys.next();
		        order.setId(generatedKeys.getLong(1));		        
		    }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	private Map<MenuItem,Integer> convertContentsToOrderMap(String contents) throws NumberFormatException, ClassNotFoundException {
		Map<MenuItem,Integer> orderMap = new HashMap<MenuItem,Integer>();
		if (contents == null || contents.equals("")) {
			return orderMap;
		}
			String[] items = contents.split(":");
			for (int i = 0; i < items.length; i++) {
				String key = items[i].split(",")[0];
				String value = items[i].split(",")[1];
				MenuItem menuItem = getItem(Integer.valueOf(key));
				orderMap.put(menuItem, Integer.valueOf(value));
			}
		return orderMap;
	}

	private String convertOrderMapToContents(Map<MenuItem,Integer> orderMap) {
		String contents = "";
		if (orderMap.keySet().size() == 0) {
			return contents;
		}
		for (MenuItem menuItem : orderMap.keySet() ) {
			contents = contents + menuItem.getId() + "," + orderMap.get(menuItem) + ":";
		}
		contents = contents.substring(0, contents.length()-1);
		return contents;
	}

	public void addToOrder(Long id, MenuItem menuItem, int quantity){
		try {	
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM orders WHERE id = " + id);
			PreparedStatement stmUpdate = conn.prepareStatement("UPDATE orders SET contents = ? WHERE id = ?");
			
			res.next();
			String contents = res.getString("contents");
			Map<MenuItem, Integer> orderMap = convertContentsToOrderMap(contents);
			if (orderMap.get(menuItem) != null) {
				orderMap.put(menuItem, orderMap.get(menuItem) + quantity);
			}
			else {
				orderMap.put(menuItem, quantity);
			}
			contents = convertOrderMapToContents(orderMap);
			stmUpdate.setString(1, contents);
			stmUpdate.setLong(2, id);
			stmUpdate.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateOrderStatus(Long id, String status) {
		try {	
			
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
			Statement stm = conn.createStatement();
			PreparedStatement stmUpdate = conn.prepareStatement("UPDATE orders SET status = ? WHERE id = ?");
			
			
			stmUpdate.setString(1, status);
			stmUpdate.setLong(2, id);
			stmUpdate.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Double getOrderTotal(Long id) {
		double d = 0d;
		try  {
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM orders WHERE id = " + id);
			
			res.next();
			Map<MenuItem,Integer> orderMap = convertContentsToOrderMap(res.getString("contents"));
			for (MenuItem menuItem : orderMap.keySet()) {
				d += menuItem.getPrice() * orderMap.get(menuItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}
	
	public Order getOrder(Long id){
		try {
			//Class.forName("com.mysql.jdbc.driver");
			Class driver_Class = Class.forName("com.mysql.jdbc.Driver");
    		Driver driver = (Driver) driver_Class.newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","wicore123");
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM orders WHERE id = " + id);
			
			res.next();
			Map<MenuItem,Integer> orderMap = convertContentsToOrderMap(res.getString("contents"));
			Order order = new Order();
			order.setCustomer(res.getString("customer"));
			order.setId(res.getLong("id"));
			order.setStatus(res.getString("status"));
			order.setContents(orderMap);
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
		
	}
}
