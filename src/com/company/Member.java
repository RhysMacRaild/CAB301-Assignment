package com.company;

public class Member
{
    public String userName;
    public String firstName;
    public String lastName;
    public String residentialAddress;
    public int phoneNumber;
    public int password;
    MovieCollection borrowedMovies;

    public Member(String firstName, String lastName, String residentialAddress, int phoneNumber, int password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = firstName+lastName;
        this.password = password;
        this.residentialAddress = residentialAddress;
        this.phoneNumber = phoneNumber;
        this.borrowedMovies = new MovieCollection();
    }
}
