package ca.bcit.infosys.models;

import java.io.Serializable;

public class PayLevel implements Serializable{
	
	private int payLevelId;
	
	private Double avgPayRate;

	public int getPayLevelId() {
		return payLevelId;
	}

	public void setPayLevelId(int payLevelId) {
		this.payLevelId = payLevelId;
	}

	public Double getAvgPayRate() {
		return avgPayRate;
	}

	public void setAvgPayRate(Double avgPayRate) {
		this.avgPayRate = avgPayRate;
	}
	
	

}
