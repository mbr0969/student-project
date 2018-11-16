package edu.pushnoe.student.domain;

public class University {

    private Long universityId;
    private String universityName;

    public University() {
    }

    public University(Long universityId, String universityName) {
        this.universityId = universityId;
        this.universityName = universityName;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("University{");
        sb.append("universityId=").append(universityId);
        sb.append(", universityName='").append(universityName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
