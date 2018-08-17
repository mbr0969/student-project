package edu.pushnoe.student.domain;

import java.time.LocalDate;

public abstract class Person {

// Личные данные персоны
    protected String surName;       //Фамилия
    protected String givenName;     // Имя
    private String patronymic;      // Отчество.
    private LocalDate dateOfBirth;  // День Рождения.
    private Address address;        //Адрес

    public Person(){

    }

    public Person(String surName, String givenName, String patronymic, LocalDate dateOfBirth, Address address) {
        this.surName = surName;
        this.givenName = givenName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}