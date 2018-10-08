package com.gtyagi.stockapp.model;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "stocks")
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "stock_Sequence")
    @SequenceGenerator(name = "stock_Sequence", sequenceName = "STOCK_SEQ")
    @Column(name = "id", nullable = false)
    private int id;
	
	@Column(name = "symbol")
	private String symbol;
	
	@Column(name = "units")
    private int units;
    
	@Column(name = "price")
    private double price;
    
    public Stock() {
    	
    }
    public Stock(String symbol, int units, double price) {
    	super();
    	checkArgument(isNotBlank(symbol), "Mandatory field symbol");
    	checkArgument(units >0, "Amount cant be less than or equal to 0");
    	checkArgument(price >0.0, "Price cant be less than or equal to 0");
		this.symbol = symbol;
		this.units = units;
		this.price = price;
	}

	@Override
	public String toString() {
		 return MoreObjects.toStringHelper(this)
	                .add("symbol", symbol)
	                .add("units", units)
	                .add("price", price)
	                .toString();
	}

	public int getAmount() {
		return units;
	}

	public void setAmount(int amount) {
		this.units = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}
    
    
}
