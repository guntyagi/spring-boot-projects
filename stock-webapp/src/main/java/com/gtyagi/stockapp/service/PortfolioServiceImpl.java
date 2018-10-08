package com.gtyagi.stockapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtyagi.stockapp.model.Stock;
import com.gtyagi.stockapp.model.User;
import com.gtyagi.stockapp.repository.StockHoldingRepository;
import com.gtyagi.stockapp.repository.StockRepository;
import com.gtyagi.stockapp.repository.UserRepository;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockHoldingRepository stockHoldingRepository;
	

	@Override
	public Stock getStock(String symbol) {
		return stockRepository.findBySymbol(symbol);
	}


	@Override
	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}


	@Override
	public User getUserDetails(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> getAllUserDetails() {
		return userRepository.findAll();
	}


	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}


	@Override
	public void saveStock(Stock stock) {
		stockRepository.save(stock);
		
	}



}
