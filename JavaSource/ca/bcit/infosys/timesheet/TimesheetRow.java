package ca.bcit.infosys.timesheet;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * A class representing a single row of a Timesheet.
 *
 * @author Soran Shim
 * @author Calvin Yee
 */
@SessionScoped
@Named("timesheetRow")
public class TimesheetRow implements java.io.Serializable {

    /** Editable. */
    private boolean editable;
    /** Saturday. */
    private double sat;
    /** Sunday. */
    private double sun;
    /** Monday. */
    private double mon;
    /** Tueday. */
    private double tue;
    /** Wedday. */
    private double wed;
    /** Thuday. */
    private double thu;
    /** Friday. */
    private double fri;

    /** The projectID. */
    private Integer projectID;
    /** The WorkPackage. Must be a unique for a given projectID. */
    private String workPackage;
    /** Any notes added to the end of a row. */
    private String notes;

    /**
     * @return the editable
     */
    public boolean getEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the projectID
     */
    public Integer getProjectID() {
        return projectID;
    }

    /**
     * @param id the projectID to set
     */
    public void setProjectID(Integer id) {
        this.projectID = id;
    }

    /**
     * @return the workPackage
     */
    public String getWorkPackage() {
        return workPackage;
    }

    /**
     * @param wp the workPackage to set
     */
    public void setWorkPackage(String wp) {
        this.workPackage = wp;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param comments the notes to set
     */
    public void setNotes(String comments) {
        this.notes = comments;
    }
    /**
     *
     * @return the sat
     */
    public double getSat() {
        return sat;
    }

    /**
     *
     * @param sat the sat to set
     */
    public void setSat(double sat) {
        this.sat = sat;
    }

    /**
     *
     * @return the sun
     */
    public double getSun() {
        return sun;
    }

    /**
     *
     * @param sun the sun to set
     */

    public void setSun(double sun) {
        this.sun = sun;
    }

    /**
     *
     * @return the mon
     */
    public double getMon() {
        return mon;
    }

    /**
     *
     * @param mon the mon to set
     */
    public void setMon(double mon) {
        this.mon = mon;
    }

    /**
     *
     * @return the tue
     */
    public double getTue() {
        return tue;
    }

    /**
     *
     * @param tue the tue to set
     */
    public void setTue(double tue) {
        this.tue = tue;
    }

    /**
     *
     * @return the wed
     */
    public double getWed() {
        return wed;
    }

    /**
     *
     * @param wed the wed to set
     */
    public void setWed(double wed) {
        this.wed = wed;
    }

    /**
     *
     * @return the thu
     */
    public double getThu() {
        return thu;
    }

    /**
     *
     * @param thu the thu to set
     */
    public void setThu(double thu) {
        this.thu = thu;
    }

    /**
     *
     * @return the fri
     */
    public double getFri() {
        return fri;
    }

    /**
     *
     * @param fri the fri to set
     */
    public void setFri(double fri) {
        this.fri = fri;
    }

    /**
     * @return the weekly hours
     */
    public double getTotalDay() {
        return fri + thu + tue + wed + mon + sun + sat;
    }

}
