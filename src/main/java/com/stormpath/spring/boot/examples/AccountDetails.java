package com.stormpath.spring.boot.examples;

public class AccountDetails {
    private String givenName;
    private String lastName;
    private String fullName;
    private String email;
    private String username;

    public AccountDetails(String givenName, String lastName, String fullName, String email, String username) {
        this.givenName = givenName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
