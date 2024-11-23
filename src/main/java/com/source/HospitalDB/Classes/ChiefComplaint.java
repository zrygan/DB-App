package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;

public class ChiefComplaint {
    private int complaintID;
    private String complaintDescription;

    public ChiefComplaint(String complaintDescription) {
        App.inc_count_chiefComplaint();
        complaintID = App.get_count_chiefComplaint();
        this.complaintDescription = complaintDescription;
    }

    public ChiefComplaint(int complaintID, String complaintDescription) {
        this.complaintID = complaintID;
        this.complaintDescription = complaintDescription;
    }

    // Getters and Setters
    public int getComplaintID() { return complaintID; }
    public void setComplaintID(int complaintID) { this.complaintID = complaintID; }

    public String getComplaintDescription() { return complaintDescription; }
    public void setComplaintDescription(String complaintDescription) { this.complaintDescription = complaintDescription; }

}
