package com.gtyagi.stockapp.model;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "users")
public class User {
	
	
	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;
	
	@Column(name = "username")
	private String userName; 
	
	@Column(name = "budget")
    private double budget;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
	private Map<String, StockHolding> portfolio; // keys are stock symbol
	
    

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", budget=" + budget + ", portfolio=" + portfolio + "]";
	}


	public User() {
		
	}


	public User(String userName, double budget) {
		super();
		checkArgument(isNotBlank(userName), "Mandatory field UserName");
    	checkArgument(budget >0.0, "Budget cant be less than or equal to 0");
		this.userName = userName;
		this.budget = budget;
		this.portfolio = new HashMap<String, StockHolding>();
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public double getBudget() {
		return budget;
	}


	public void setBudget(double budget) {
		this.budget = budget;
	}


	public Map<String, StockHolding> getPortfolio() {
		return portfolio;
	}


	public void setPortfolio(Map<String, StockHolding> portfolio) {
		this.portfolio = portfolio;
	}
	
	public void addHolding (StockHolding holding) throws IllegalArgumentException {

        // Ensure a holding for the symbol doesn't already exist
        if (portfolio.containsKey(holding.getSymbol())) {
            throw new IllegalArgumentException("A holding for symbol " + holding.getSymbol()+ " already exits for user " + getId());
        }

        portfolio.put(holding.getSymbol(), holding);
    }



}
