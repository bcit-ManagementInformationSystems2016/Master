/**
 * 
 */
package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nguyen
 *
 */
@Entity
@Table(name="vacation")
public class Paylevel implements Serializable{
	
	@Id
	@Column(name="id")
	private int PaylvlID;
	
	@Column(name="vacationDays")
	private int vacationDays;
	
	@Column(name="PayRate")
	private double AvgPayRate;
	
	

	/**
	 * @return the avgPayRate
	 */
	public double getAvgPayRate() {
		return AvgPayRate;
	}

	/**
	 * @param avgPayRate the avgPayRate to set
	 */
	public void setAvgPayRate(double avgPayRate) {
		AvgPayRate = avgPayRate;
	}

	/**
	 * @return the paylvlID
	 */
	public int getPaylvlID() {
		return PaylvlID;
	}

	/**
	 * @param paylvlID the paylvlID to set
	 */
	public void setPaylvlID(int paylvlID) {
		PaylvlID = paylvlID;
	}

	/**
	 * @return the vacationDays
	 */
	public int getVacationDays() {
		return vacationDays;
	}

	/**
	 * @param vacationDays the vacationDays to set
	 */
	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}
	
	

}
