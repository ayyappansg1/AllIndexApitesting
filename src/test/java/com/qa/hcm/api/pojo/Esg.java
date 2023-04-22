package com.qa.hcm.api.pojo;

import java.util.ArrayList;

public class Esg {
	 private ArrayList<Integer> environment;
	 private ArrayList<Integer> social;
	 private ArrayList<Integer> governance;
	public ArrayList<Integer> getEnvironment() {
		return environment;
	}
	public void setEnvironment(ArrayList<Integer> environment) {
		this.environment = environment;
	}
	public ArrayList<Integer> getSocial() {
		return social;
	}
	public void setSocial(ArrayList<Integer> social) {
		this.social = social;
	}
	public ArrayList<Integer> getGovernance() {
		return governance;
	}
	public void setGovernance(ArrayList<Integer> governance) {
		this.governance = governance;
	}

}
