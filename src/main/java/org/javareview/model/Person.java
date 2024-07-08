package org.javareview.model;

public class Person {

    private Long id;

    private String firstName;

    private String secondName;

    private String phoneNumber;

    public Person(Long id, String firstName, String secondName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
    }

    public Person(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }
}
