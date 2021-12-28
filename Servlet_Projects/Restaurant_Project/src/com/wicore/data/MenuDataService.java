package com.wicore.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wicore.domain.MenuCategory;
import com.wicore.domain.MenuItem;


public class MenuDataService {

	List<MenuItem> menuItems = new ArrayList<MenuItem>();
	Map<MenuItem,Integer> currentOrder = new HashMap<MenuItem,Integer>();
	
	public MenuDataService() {
		menuItems.add(new MenuItem(1, "Biryani", "A delicious biryani made from the chef's choice of India gate Basmati rice and fresh vegetables with panneer. Served with onions and gravy.", MenuCategory.MAIN_COURSE, 499));
		menuItems.add(new MenuItem(2, "sambar (v)", "Fresh seasonal bottlegaurds with different veggies served with a warm rice.", MenuCategory.MAIN_COURSE, 199));
		menuItems.add(new MenuItem(3, "Chicken curry", "Our terrine tastes of summer! We use only the finest organic chicken. Served with a mixed leaf salad. (contains nuts)", MenuCategory.MAIN_COURSE, 199));
		menuItems.add(new MenuItem(4, "Lamb curry", "Slow cooked to perfection, our organic lamb melts in the mouth. Served with rice and Biryani.", MenuCategory.MAIN_COURSE, 699));
		menuItems.add(new MenuItem(5, "fish curry", "We pan fry our freshly caught fish to seal in the flavour. Served with rice.", MenuCategory.MAIN_COURSE, 399));
		menuItems.add(new MenuItem(6, "Butter Naan (v)", "People come from far and wide for our famous naans. Served with a mixed panneer butter masala. (v)", MenuCategory.MAIN_COURSE, 399));
		menuItems.add(new MenuItem(7, "Eggless cake", "A delightfully sweet cheesecake, served with a sour raspberry compot, to form a perfect balance to end your meal.", MenuCategory.DESERT, 99));
		menuItems.add(new MenuItem(8, "Gulab Jamun", "Feeling full? Our jamun is delightfully light and fluffy. Served with home baked cova.", MenuCategory.DESERT, 99));
		menuItems.add(new MenuItem(9, "Fruit salad", "Our nostalgic 80s desert is super healthy... then we add luxurious vanilla ice-cream and chocolate sauce. ", MenuCategory.DESERT, 149));
		menuItems.add(new MenuItem(10, "Coffee", "Espresso, Americano, Latte or Capuccino? Tell us how you like it!", MenuCategory.DRINK, 99));
		menuItems.add(new MenuItem(11, "Tea", "masala chai, Allam tea, Bellam Tea, Pot tea?  We have a full range of classic and herbal teas.", MenuCategory.DRINK, 49));	
	}
	
	public List<MenuItem>  getFullMenu() {
		return menuItems;
	}
	
	public List<MenuItem> find(String searchString) {
		List<MenuItem> results = new ArrayList<MenuItem>();
		for (MenuItem menuItem : menuItems) {
			if (menuItem.getName().toLowerCase().contains(searchString.toLowerCase()) || menuItem.getDescription().toLowerCase().contains(searchString.toLowerCase())) {
				results.add(menuItem);
			}
		}
		
		return results;
	}
	
	public MenuItem getItem(int id) {
		return menuItems.get(id);
	}
	
	public void addToOrder(MenuItem menuItem, int quantity) {
		int currentQuantity = 0;
		if (currentOrder.get(menuItem) != null) currentQuantity = currentOrder.get(menuItem);
		currentOrder.put(menuItem, currentQuantity + quantity);
	}
	
	public Double getOrderTotal() {
		double d = 0d;
		for (MenuItem menuItem : currentOrder.keySet()) {
			System.out.println("price :" +menuItem.getPrice());
			d += currentOrder.get(menuItem) * menuItem.getPrice();
		}
		return d;
	}
}
