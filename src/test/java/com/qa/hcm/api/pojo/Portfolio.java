package com.qa.hcm.api.pojo;

import java.util.ArrayList;

public class Portfolio {
	private int currency;
	private String launch_date;
	private String backtest_available_since;
	private int no_of_stock;
	private int rebalance_frequency;
	private Esg esg;
	private ArrayList<Integer> market_cap;
	private int weighting_type;
	private boolean research_portfolio;
	private boolean rebalance;
	private ArrayList<Integer> index_type;
	private int period;
	private MetaData meta_data;
	private String name;
	private String description;
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public String getLaunch_date() {
		return launch_date;
	}
	public void setLaunch_date(String launch_date) {
		this.launch_date = launch_date;
	}
	public String getBacktest_available_since() {
		return backtest_available_since;
	}
	public void setBacktest_available_since(String backtest_available_since) {
		this.backtest_available_since = backtest_available_since;
	}
	public int getNo_of_stock() {
		return no_of_stock;
	}
	public void setNo_of_stock(int no_of_stock) {
		this.no_of_stock = no_of_stock;
	}
	public int getRebalance_frequency() {
		return rebalance_frequency;
	}
	public void setRebalance_frequency(int rebalance_frequency) {
		this.rebalance_frequency = rebalance_frequency;
	}
	public Esg getEsg() {
		return esg;
	}
	public void setEsg(Esg esg) {
		this.esg = esg;
	}
	public ArrayList<Integer> getMarket_cap() {
		return market_cap;
	}
	public void setMarket_cap(ArrayList<Integer> market_cap) {
		this.market_cap = market_cap;
	}
	public int getWeighting_type() {
		return weighting_type;
	}
	public void setWeighting_type(int weighting_type) {
		this.weighting_type = weighting_type;
	}
	public boolean isResearch_portfolio() {
		return research_portfolio;
	}
	public void setResearch_portfolio(boolean research_portfolio) {
		this.research_portfolio = research_portfolio;
	}
	public boolean isRebalance() {
		return rebalance;
	}
	public void setRebalance(boolean rebalance) {
		this.rebalance = rebalance;
	}
	public ArrayList<Integer> getIndex_type() {
		return index_type;
	}
	public void setIndex_type(ArrayList<Integer> index_type) {
		this.index_type = index_type;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public MetaData getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(MetaData meta_data) {
		this.meta_data = meta_data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	


}
