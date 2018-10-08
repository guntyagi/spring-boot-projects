package com.gtyagi.stockapp.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtyagi.stockapp.model.Stock;
import com.gtyagi.stockapp.model.StockHolding;
import com.gtyagi.stockapp.model.StockRequest;
import com.gtyagi.stockapp.model.User;
import com.gtyagi.stockapp.service.PortfolioService;

@RestController
public class PortfolioController {
	
	@Autowired
	private PortfolioService portfolioService;
	private static final Logger LOGGER = LoggerFactory.getLogger(PortfolioController.class);
	
	
	
	@RequestMapping(value="/stocks/{symbol}", method=RequestMethod.GET)
	public Stock getStock(@PathVariable String symbol){
		return portfolioService.getStock(symbol);
	}
	
	@RequestMapping(value="/stocks", method=RequestMethod.GET)
	public List<Stock> getAllStocks(){
		return portfolioService.getAllStocks();
	}
	
	@RequestMapping(value="/users/{userName}", method=RequestMethod.GET)
	public User getUser(@PathVariable String userName){
		return portfolioService.getUserDetails(userName);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getUsers(){
		return portfolioService.getAllUserDetails();
	}
	
	/**
	 * @param
	 * 
	 */
	@RequestMapping(value="/stocks/buy",method=RequestMethod.POST)
	public void buyStock(@RequestBody StockRequest stockRequest) {
		User user = portfolioService.getUserDetails(stockRequest.getUserName());
		String symbol = stockRequest.getSymbol();
		int numberOfShares = stockRequest.getQuanity();
		
		// Get the existing portfolio
        Map<String, StockHolding> userPortfolio = user.getPortfolio();
        StockHolding holding;

        // Create a new holding, if not exists
        if (!userPortfolio.containsKey(symbol)) {
            holding = new StockHolding(symbol, user.getId());
            user.addHolding(holding);
        }

        // Validate the transaction
        holding = userPortfolio.get(symbol);
        Stock stockInventory = portfolioService.getStock(symbol);
        double buyCost = (stockInventory.getPrice()*numberOfShares);
        
        if (numberOfShares < 0) 
            throw new IllegalArgumentException("Cant buy negative number of shares");        
        if(user.getBudget()<buyCost)
        	 throw new IllegalArgumentException("Budget insufficient. Required Amount "+buyCost+ " and budget available "+user.getBudget() );
        if(stockInventory.getAmount()<numberOfShares)
        	throw new IllegalArgumentException("Insufficent number of shares in the inventory");
        
        // adjust the items with the new values 
        stockInventory.setAmount(stockInventory.getAmount()-numberOfShares);// update the stock inventory
        user.setBudget(user.getBudget()-buyCost);// adjust the user budget
        holding.setStocksOwned(holding.getStocksOwned()+numberOfShares); // adjust the user's holdings
        
        // save the items
        portfolioService.saveUser(user);
        portfolioService.saveStock(stockInventory);
		
	}
	@RequestMapping(value="/stocks/sell",method=RequestMethod.POST)
	public void sellStock(@RequestBody StockRequest stockRequest) {
		User user = portfolioService.getUserDetails(stockRequest.getUserName());
		String symbol = stockRequest.getSymbol();
		int numberOfShares = stockRequest.getQuanity();
		
		// Get the existing portfolio
        Map<String, StockHolding> userPortfolio = user.getPortfolio();
    
        // Validate the transaction
        if (numberOfShares < 0) 
            throw new IllegalArgumentException("Cant sell negative number of shares");
        
        if (!userPortfolio.containsKey(symbol)) 
        	throw new IllegalArgumentException("User doesnt have any shares to sell of the type "+symbol);   
        
        StockHolding holding = userPortfolio.get(symbol);
        if (holding.getStocksOwned()<numberOfShares) 
        	throw new IllegalArgumentException("User doesnt have enough shares to sell of the type "+symbol);   
        
        
        // adjust the items with the new values 
        Stock stockInventory = portfolioService.getStock(symbol);
        double sellCost = (stockInventory.getPrice()*numberOfShares);
        stockInventory.setAmount(stockInventory.getAmount()+numberOfShares); // update the stock inventory
        holding.setStocksOwned(holding.getStocksOwned()-numberOfShares);
        user.setBudget(user.getBudget()+sellCost);
        
        portfolioService.saveUser(user);
        portfolioService.saveStock(stockInventory);

	}
	
}
