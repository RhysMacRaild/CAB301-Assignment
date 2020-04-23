package com.company;

public class Movie extends Node
{
//    MAYBE SOME SHOULDNT BE STRING BUT CUSTOM DATA TYPE
    String Title;
    String Starring;
    String Director;
    String Genre;
    String Classification;
    String ReleaseDate;

    int BorrowCount;

    public Movie(String title){
        this.Title = title;
    }
}
