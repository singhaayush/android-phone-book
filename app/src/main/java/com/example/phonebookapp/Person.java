package com.example.phonebookapp;

public class Person {
    private String personName;
    private String personEmail;
    private  String personPhoneNumber;
    private  String  personDOB;
    private boolean expanded;

    public Person(String personName, String personEmail, String personPhoneNumber, String personDOB) {
        expanded=false;
        this.personName = personName;
        this.personEmail = personEmail;
        this.personPhoneNumber = personPhoneNumber;
        this.personDOB = personDOB;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public String getPersonDOB() {
        return personDOB;
    }
}
