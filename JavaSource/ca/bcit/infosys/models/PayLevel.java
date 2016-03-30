/**
 * 
 */
package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Brendan Voon
 *
 */
@Entity
@Table(name = "PayLevel")
public class PayLevel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PayLevelID")
	public int payLevelID;
	
	@Column(name = "TotalVacationDays")
	private int totalVacationDays;
	
	@Column(name = "AvgPayRate")
	private Double avgPayRate;
	
	public int getPayLevelID() {
		return payLevelID;
	}

	public void setPayLevelID(int payLevelID) {
		this.payLevelID = payLevelID;
	}

	public int getTotalVacationDays() {
		return totalVacationDays;
	}

	public void setTotalVacationDays(int totalVacationDays) {
		this.totalVacationDays = totalVacationDays;
	}

	public Double getAvgPayRate() {
		return avgPayRate;
	}

	public void setAvgPayRate(Double avgPayRate) {
		this.avgPayRate = avgPayRate;
	}

	public List<PayLevel> getPayLevelManagers() {
		return payLevelManagers;
	}

	public void setPayLevelManagers(List<PayLevel> payLevelManagers) {
		this.payLevelManagers = payLevelManagers;
	}

	@OneToMany(mappedBy = "payLevelID")
	private List<PayLevel> payLevelManagers;

}
