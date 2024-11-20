package com.source.HospitalDB.Classes;

public class Consult_ChiefComplaint {
    private final int consultationID;
    private final int complaintID;

    public Consult_ChiefComplaint(int consultationID, int complaintID) {
        this.consultationID = consultationID;
        this.complaintID = complaintID;
    }

    // Getters and Setters
    public int getConsultationID() { return consultationID; }

    public int getComplaintID() { return complaintID; }
}
