package edu.pushnoe.student.domain.register;


import edu.pushnoe.student.domain.Person;

public class AnswerCityRegisterItem {



    public enum CityStatus{
        YES, NO, ERROR;
    }

    public static class CityError{
        private String code;
        private String test;

        public CityError(String code, String test) {
            this.code = code;
            this.test = test;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }

    private CityStatus status;
    private Person person;
    private CityError error;


    public AnswerCityRegisterItem(CityStatus status, Person person) {
        this.status = status;
        this.person = person;
    }

    public AnswerCityRegisterItem(CityStatus status, Person person, CityError error) {
        this.status = status;
        this.person = person;
        this.error = error;
    }

    public CityStatus getStatus() {
        return status;
    }

    public void setStatus(CityStatus status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CityError getError() {
        return error;
    }

    public void setError(CityError error) {
        this.error = error;
    }
}
