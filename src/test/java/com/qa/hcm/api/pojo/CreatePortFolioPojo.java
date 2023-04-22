package com.qa.hcm.api.pojo;

import java.util.ArrayList;

public class CreatePortFolioPojo {
	private Portfolio portfolio;
	private ArrayList<Instrument> instruments;
	private Ranking ranking;
	private Filter filter;
	private Object exclusions;
	private Weights weights;
	
	public Portfolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	public ArrayList<Instrument> getInstruments() {
		return instruments;
	}
	public void setInstruments(ArrayList<Instrument> instruments) {
		this.instruments = instruments;
	}
	public Ranking getRanking() {
		return ranking;
	}
	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	public Object getExclusions() {
		return exclusions;
	}
	public void setExclusions(Object exclusions) {
		this.exclusions = exclusions;
	}
	public Weights getWeights() {
		return weights;
	}
	public void setWeights(Weights weights) {
		this.weights = weights;
	}
	
}
