package ca.bcit.infosys.models;

import java.io.Serializable;

public class WorkPackage implements Serializable{
	
	private int wpId;
	
	private String wpDescription;

	public int getWpId() {
		return wpId;
	}

	public void setWpId(int wpId) {
		this.wpId = wpId;
	}

	public String getWpDescription() {
		return wpDescription;
	}

	public void setWpDescription(String wpDescription) {
		this.wpDescription = wpDescription;
	}
	
	
	

}
