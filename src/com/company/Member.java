package com.company;

public class Member
{
    public String userName;
    public String firstName;
    public String lastName;
    public int password;

    public Member(String firstName, String lastName, int password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = firstName+lastName;
        this.password = password;
    }
}
