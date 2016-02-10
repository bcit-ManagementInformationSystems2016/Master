package ca.bcit.infosys.models;

import java.io.Serializable;

public class Vacation implements Serializable{
	
	private int vacatationId;
	
	private Double vacationHours;

	public int getVacatationId() {
		return vacatationId;
	}

	public void setVacatationId(int vacatationId) {
		this.vacatationId = vacatationId;
	}

	public Double getVacationHours() {
		return vacationHours;
	}

	public void setVacationHours(Double vacationHours) {
		this.vacationHours = vacationHours;
	}
	
	

}
