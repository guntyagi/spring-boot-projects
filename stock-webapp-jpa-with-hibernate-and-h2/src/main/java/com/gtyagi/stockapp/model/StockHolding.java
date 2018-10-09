package com.gtyagi.stockapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "StockHoldings")
public class StockHolding {

    @Id
    @GeneratedValue
    private int id;


	@Column(name = "symbol", nullable = false)
    private String symbol;
    

    @Column(name = "shares_owned", nullable = false)
    private int stocksOwned;

    @Column(name = "owner_id", nullable = false)
    private int ownerId;


    public StockHolding() {
    }
    public StockHolding(String symbol, int ownerId) {
        this.symbol = symbol.toUpperCase();
        this.stocksOwned = 0;
        this.ownerId = ownerId;
        
    }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public int getStocksOwned() {
		return stocksOwned;
	}


	public void setStocksOwned(int stocksOwned) {
		this.stocksOwned = stocksOwned;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}


	@Override
	public String toString() {
		return "StockHolding [id=" + id + ", symbol=" + symbol + ", stocksOwned=" + stocksOwned + ", ownerId=" + ownerId
				+ "]";
	}
}
