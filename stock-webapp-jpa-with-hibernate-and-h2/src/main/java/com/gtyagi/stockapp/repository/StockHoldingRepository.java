package com.gtyagi.stockapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.gtyagi.stockapp.model.StockHolding;
import com.gtyagi.stockapp.model.Stock;
import com.gtyagi.stockapp.model.User;

public interface StockHoldingRepository extends CrudRepository<StockHolding,Integer> {
	
	StockHolding findBySymbolAndOwnerId(String symbol, int ownerId);


}
