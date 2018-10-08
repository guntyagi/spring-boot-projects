package com.gtyagi.stockapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gtyagi.stockapp.model.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock,Integer> {
	Stock findBySymbol(String symbol);
	List<Stock> findAll();

}
