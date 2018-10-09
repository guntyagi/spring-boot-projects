package com.gtyagi.stockapp.service;

import java.util.List;

import com.gtyagi.stockapp.model.Stock;
import com.gtyagi.stockapp.model.User;

public interface PortfolioService {	
	
	public Stock getStock(String symbol);
	public List<Stock> getAllStocks();
	
	public User getUserDetails(String userName);
	public List<User> getAllUserDetails();
	
	public void saveUser(User user);
	public void saveStock(Stock stock);

}
