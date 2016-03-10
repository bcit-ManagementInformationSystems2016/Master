package ca.bcit.infosys.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TimesheetRow database table.
 * 
 */
@Entity
@NamedQuery(name="TimesheetRow.findAll", query="SELECT t FROM TimesheetRow t")
public class TimesheetRow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TimesheetRowID")
	private int timesheetRowID;


	@Column(name="HoursFri")
	private double hoursFri;

	@Column(name="HoursMon")
	private double hoursMon;

	@Column(name="HoursThurs")
	private double hoursThurs;

	@Column(name="HoursTues")
	private double hoursTues;

	@Column(name="HoursWed")
	private double hoursWed;

    @Column(name="HoursSat")
    private double hoursSat;

    @Column(name="HoursSun")
    private double hoursSun;
    
	@Column(name="TimesheetID")
	private int timesheetID;

	@Column(name="WorkPackageID")
	private String workPackageID;

	//bi-directional many-to-one association to Timesheet
	@ManyToOne
	@JoinColumn(name="timesheetID", updatable = false, insertable = false)
	private Timesheet timesheet;

	public TimesheetRow() {
	}
	
	public TimesheetRow(int tsrowID, int tsID, double hourMon, double hoursTues, double hoursWed, double hoursThrus, double hoursFri, double hoursSat, double hoursSun){
	    this.timesheetRowID = tsrowID;
	    this.timesheetID = tsID;
	    this.hoursMon = hourMon;
	    this.hoursTues = hoursTues;
	    this.hoursWed = hoursWed;
	    this.hoursThurs = hoursThrus;
	    this.hoursFri = hoursFri;
        this.hoursSat = hoursSat;
        this.hoursSun = hoursSun;
	    
	}
	
    public int getTimesheetRowID() {
		return this.timesheetRowID;
	}

	public void setTimesheetRowID(int timesheetRowID) {
		this.timesheetRowID = timesheetRowID;
	}

	public double getHoursFri() {
		return this.hoursFri;
	}

	public void setHoursFri(double hoursFri) {
		this.hoursFri = hoursFri;
	}

	public double getHoursMon() {
		return this.hoursMon;
	}

	public void setHoursMon(double hoursMon) {
		this.hoursMon = hoursMon;
	}

	public double getHoursThurs() {
		return this.hoursThurs;
	}

	public void setHoursThurs(double hoursThurs) {
		this.hoursThurs = hoursThurs;
	}

	public double getHoursTues() {
		return this.hoursTues;
	}

	public void setHoursTues(double hoursTues) {
		this.hoursTues = hoursTues;
	}

	public double getHoursWed() {
		return this.hoursWed;
	}

	public void setHoursWed(double hoursWed) {
		this.hoursWed = hoursWed;
	}

    public double getHoursSat() {
        return hoursSat;
    }

    public void setHoursSat(double hoursSat) {
        this.hoursSat = hoursSat;
    }

    public double getHoursSun() {
        return hoursSun;
    }

    public void setHoursSun(double hoursSun) {
        this.hoursSun = hoursSun;
    }

	public int getTimesheetID() {
		return this.timesheetID;
	}

	public void setTimesheetID(int timesheetID) {
		this.timesheetID = timesheetID;
	}

	public String getWorkPackageID() {
		return this.workPackageID;
	}

	public void setWorkPackageID(String workPackageID) {
		this.workPackageID = workPackageID;
	}

	public Timesheet getTimesheet() {
		return this.timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}

}