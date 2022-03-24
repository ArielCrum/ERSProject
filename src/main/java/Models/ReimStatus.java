package Models;

public class ReimType {
    private Integer rStatusID;
    private String rStatus;
    private Integer rIDFK;

    public ReimType() {
    }

    public ReimType(Integer rStatusID, String rStatus, Integer rIDFK) {
        this.rStatusID = rStatusID;
        this.rStatus = rStatus;
        this.rIDFK = rIDFK;
    }

    public Integer getrStatusID() {
        return rStatusID;
    }

    public void setrStatusID(Integer rStatusID) {
        this.rStatusID = rStatusID;
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }

    public Integer getrIDFK() {
        return rIDFK;
    }

    public void setrIDFK(Integer rIDFK) {
        this.rIDFK = rIDFK;
    }

    @Override
    public String toString() {
        return "ReimType{" +
                "rStatusID=" + rStatusID +
                ", rStatus='" + rStatus + '\'' +
                ", rIDFK=" + rIDFK +
                '}';
    }
}
