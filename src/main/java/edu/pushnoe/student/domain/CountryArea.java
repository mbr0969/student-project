package edu.pushnoe.student.domain;

public class CountryArea {

    private String areaId;
    private String areaName;

    public CountryArea() {
    }

    public CountryArea(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CountryArea{");
        sb.append("areaId='").append(areaId).append('\'');
        sb.append(", areaName='").append(areaName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
