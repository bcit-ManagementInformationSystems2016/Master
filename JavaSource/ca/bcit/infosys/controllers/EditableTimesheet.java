package ca.bcit.infosys.controllers;


import ca.bcit.infosys.models.Timesheet;

/**
 * Product which has fields indicating it is editable or to be deleted.
 * @author blink
 * @version 1
 */
public class EditableTimesheet {
    /** Indicates associated timesheet can be edited on a form.*/
    private boolean editable;
    /** Holds timesheet to be displayed, edited or deleted.*/
    private Timesheet timesheet;
    /**
     * Convenience constructor.
     * @param timesheet timesheet to be displayed, edited or deleted.
     */
    public EditableTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }

    /**
     * Returns true if associated product is editable.
     * @return the editable state
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Set whether associated timesheet is editable.
     * @param editable the state of editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the timesheet
     */
    public Timesheet getTimesheet() {
        return timesheet;
    }

    /**
     * @param timesheet the timesheet to set
     */
    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }
}