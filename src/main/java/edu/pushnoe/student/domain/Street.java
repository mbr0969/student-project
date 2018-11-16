package edu.pushnoe.student.domain;

public class Street {

    private long streetCode;
    private String streetName;

    public Street() {
    }

    public Street(long streetCode, String streetName) {
        this.streetCode = streetCode;
        this.streetName = streetName;
    }

    public long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(long streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Street{");
        sb.append("streetCode=").append(streetCode);
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
