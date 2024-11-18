package main.java.com.source.HospitalDB.Classes;

public class Consult_ChiefComplaint {
    private int consultationID;
    private int complaintID;

    public Consult_ChiefComplaint(int consultationID, int complaintID) {
        this.consultationID = consultationID;
        this.complaintID = complaintID;
    }

    // Getters and Setters
    public int getConsultationID() { return consultationID; }
    public void setConsultationID(int consultationID) { this.consultationID = consultationID; }

    public int getComplaintID() { return complaintID; }
    public void setComplaintID(int complaintID) { this.complaintID = complaintID; }


}
