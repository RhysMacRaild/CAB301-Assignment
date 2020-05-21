package com.company;

public class Movie extends Node
{
//    MAYBE SOME SHOULDNT BE STRING BUT CUSTOM DATA TYPE
    String title;
    String starring;
    String director;
    String genre;
    String classification;
    String releaseDate;

    int BorrowCount;

    public Movie(String title){
        this.title = title;
    }
}
