package main.java.com.source.HospitalDB.Classes;

public class ChiefComplaint {
    private int complaintID;
    private String complaintDescription;

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
