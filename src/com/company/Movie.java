package com.company;

public class Movie extends Node {
    String[] VALID_GENRES = {"Drama", "Adventure", "Family", "Action", "Sci-Fi", "Comedy", "Animated", "Thriller",
            "Other"};
    String[] VALID_CLASSIFICATIONS = {"G","PG","M15+","MA15+"};
    String title;
    String starring;
    String director;
    String genre;
    String classification;
    String releaseDate;
    int borrowCount;


    public Movie(String title) {
        this.title = title;
        this.borrowCount = 0;
    }

//    Create a deep copy of a movie
    public Movie(Movie movieToCopy){
        this.title = movieToCopy.title;
        this.starring = movieToCopy.starring;
        this.director = movieToCopy.director;
        this.genre = movieToCopy.genre;
        this.classification = movieToCopy.classification;
        this.releaseDate = movieToCopy.releaseDate;
        this.borrowCount = movieToCopy.borrowCount;
    }

//    Iterate through valid genre list to check if the inputted genre is a valid one
    public boolean isValidGenre(String genre) {
        return this.isValid(genre,this.VALID_GENRES);
    }

    //    Iterate through valid classification list to check if the inputted genre is a valid one
    public boolean isValidClassification(String classification){
        return this.isValid(classification, this.VALID_CLASSIFICATIONS);
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
