package com.company;

public class Main {

    public static void main(String[] args) {
        Movie test = new Movie("Name123");
        Movie test2 = new Movie("Abb");
        Movie test3 = new Movie("Zamp");
        MovieCollection collection = new MovieCollection();
        collection.add(test);
        collection.add(test2);
        collection.add(test3);
        Movie test4 = new Movie("Zamp");
    }
}
