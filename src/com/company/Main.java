package com.company;

public class Main {

    public static void main(String[] args) {
        CommunityLibrary run = new CommunityLibrary();
        Movie a = new Movie("1");
        Movie b = new Movie("1");
        Movie c = new Movie("1");
        Movie d = new Movie("2");
        Movie e = new Movie("2");
        Movie f = new Movie("3");
        Movie g = new Movie("4");

        a.borrowCount = 5;
        b.borrowCount = 3;
        d.borrowCount = 2;
        f.borrowCount = 12;
        g.borrowCount = 1;

        MovieCollection testing = new MovieCollection();
        testing.add(a);
        testing.add(b);
        testing.add(c);
        testing.add(d);
        testing.add(e);
        testing.add(f);
        testing.add(g);
        testing.listByBorrowCount();

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
