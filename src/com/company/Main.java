package com.company;

public class Main {

    public static void main(String[] args) {
//        CommunityLibrary run = new CommunityLibrary();
//        run.mainMenu();
        MemberCollection members = new MemberCollection();
        members.addMember(new Member("John","Stick","","1234",1234));
        Member test = members.returnMemberFromUsername("JohnStick");
        System.out.println(test.phoneNumber);

//        MovieCollection collection = new MovieCollection();
//        collection.add(new Movie("5"));
//        collection.add(new Movie("4"));
//        collection.add(new Movie("8"));
//        collection.add(new Movie("1"));
//        collection.add(new Movie("6"));
//        collection.add(new Movie("10"));
//        collection.add(new Movie("9"));
//        System.out.println();
//        System.out.println();
//        collection.removeMovieByString("10");
//        System.out.println();
//        System.out.println();
//        System.out.println();

    }

}
