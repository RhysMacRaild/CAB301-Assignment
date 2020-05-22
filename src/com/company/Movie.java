package com.company;

public class Movie extends Node {
    String[] validGenres = {"Drama", "Adventure", "Family", "Action", "Sci-Fi", "Comedy", "Animated", "Thriller",
            "Other"};
    String[] validClassifications = {"G","PG","M15+","MA15+"};
    String title;
    String starring;
    String director;
    String genre;
    String classification;
    String releaseDate;


    int BorrowCount;

    public Movie(String title) {
        this.title = title;
    }

//    Iterate through valid genre list to check if the inputted genre is a valid one
    public boolean isValidGenre(String genre) {
        return this.isValid(genre,this.validGenres);
    }

    //    Iterate through valid classification list to check if the inputted genre is a valid one
    public boolean isValidClassification(String classification){
        return this.isValid(classification, this.validClassifications);
    }

    //    Iterate through valid list to check if the inputted string is a valid one
    public boolean isValid(String stringToTest, String[] listOfValidOptions) {
        int index = 0;
//      Format as upper case so that way too genres with different case letters will still match
        String thingToTest = stringToTest.toUpperCase();
        try {
            while (true) {
                String validOption = listOfValidOptions[index].toUpperCase();
                if (thingToTest.equals(validOption)) {
                    return true;
                }
                index++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }
        return false;
    }
}
