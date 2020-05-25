package com.company;

public class Main {

    public static void main(String[] args) {
        CommunityLibrary run = new CommunityLibrary();

        run.addNewMovie(new Movie("A"));
        run.addNewMovie(new Movie("G"));
        run.addNewMovie(new Movie("D"));
        run.addNewMovie(new Movie("G"));
        run.addNewMovie(new Movie("g"));
        run.addNewMovie(new Movie("g"));
        run.addNewMovie(new Movie("AC"));
        run.addNewMovie(new Movie("a"));
        run.addNewMovie(new Movie("g"));
        run.addNewMovie(new Movie("j"));
        run.addNewMovie(new Movie("k"));
        run.addNewMovie(new Movie("l"));
        run.addNewMovie(new Movie("b"));
        run.addNewMovie(new Movie("s"));
        run.addNewMovie(new Movie("t"));
        run.addNewMovie(new Movie("ttC"));
        run.addNewMovie(new Movie("v"));
        run.addNewMovie(new Movie("k"));
        Member john = new Member("John","Smith","","",1234);
        Member sam = new Member("Sam","Smith","","",1234);
        run.members.addMember(john);
        run.members.addMember(sam);

        Movie movieToRent = run.availableToRentMovies.returnMovieFromString("g");
        if (movieToRent != null){
            run.availableToRentMovies.removeMovie(movieToRent);
            john.borrowedMovies.add(new Movie(movieToRent));

//            Increase borrow count in allMovies collection
            Movie movieToIncreaseBorrowCount = run.allMovies.returnMovieFromString("g");
            movieToIncreaseBorrowCount.borrowCount++;
            System.out.println("Movie borrowed successfully");
        } else {
            System.out.println("Unable to borrow selected movie. Please insure title is correct...");
        }

        movieToRent = run.availableToRentMovies.returnMovieFromString("g");
        if (movieToRent != null){
            run.availableToRentMovies.removeMovie(movieToRent);
            sam.borrowedMovies.add(new Movie(movieToRent));

//            Increase borrow count in allMovies collection
            Movie movieToIncreaseBorrowCount = run.allMovies.returnMovieFromString("g");
            movieToIncreaseBorrowCount.borrowCount++;
            System.out.println("Movie borrowed successfully");
        } else {
            System.out.println("Unable to borrow selected movie. Please insure title is correct...");
        }

        run.memberMenu(john);

//        Movie test = run.availableToRentMovies.returnMovieFromString("g");
//        if (test != null){
//            run.availableToRentMovies.removeMovie(test);
//            System.out.println("Not Null");
//        }
//        Movie test2 = run.availableToRentMovies.returnMovieFromString("g");
//        if (test2 != null){
//            run.availableToRentMovies.removeMovie(test2);
//            System.out.println("Not Null");
//        }






//        run.displayMostPopularMovies();
//        run.mainMenu();
//        MemberCollection members = new MemberCollection();
//        members.addMember(new Member("John","Stick","","1234",1234));
//        Member test = members.returnMemberFromUsername("JohnStick");
//        System.out.println(test.phoneNumber);

//        MovieCollection collection = new MovieCollection();

//        collection.listMovieLexicographically();
//        System.out.println();
//        collection.removeMovieByString("10");
//        System.out.println();
//        System.out.println();
//        System.out.println();

    }

}
