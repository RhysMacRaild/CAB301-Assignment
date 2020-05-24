package com.company;

public class Main {

    public static void main(String[] args) {
        CommunityLibrary run = new CommunityLibrary();
        run.allMovies.add(new Movie("5"));
        run.allMovies.add(new Movie("4"));
        run.allMovies.add(new Movie("8"));
        run.allMovies.add(new Movie("1"));
        run.allMovies.add(new Movie("6"));
        run.allMovies.add(new Movie("2"));
        run.allMovies.add(new Movie("3"));
        run.allMovies.add(new Movie("9"));
        run.registerNewMember();
        run.mainMenu();
//        MemberCollection members = new MemberCollection();
//        members.addMember(new Member("John","Stick","","1234",1234));
//        Member test = members.returnMemberFromUsername("JohnStick");
//        System.out.println(test.phoneNumber);

        MovieCollection collection = new MovieCollection();

        collection.listMovieLexicographically();
//        System.out.println();
//        collection.removeMovieByString("10");
//        System.out.println();
//        System.out.println();
//        System.out.println();

    }

}
