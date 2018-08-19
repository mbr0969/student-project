package edu.pushnoe.student.domain;

import java.time.LocalDate;

public class Child extends Person {

    private String certificateNumber;   // номер свитедества о рождении
    private LocalDate issueDate;        // дата выдачи С о рождении
    private String issueDepartment;     // кем выдано Свед о  рождении.



    public Child(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
