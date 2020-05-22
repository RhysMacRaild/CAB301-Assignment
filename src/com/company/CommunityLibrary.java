package com.company;

import java.util.Scanner;

public class CommunityLibrary {
    MemberCollection members;
    MovieCollection allMovies;
    MovieCollection availableToRentMovies;

    public CommunityLibrary() {
        members = new MemberCollection();
        allMovies = new MovieCollection();
        availableToRentMovies = new MovieCollection();
    }

    public void mainMenu() {
        showMainMenu();
        int selection = getSelection(2);

        if (selection < 0) {
            mainMenu();
        } else if (selection == 1) {
            staffLogin();
        } else if (selection == 2) {
            memberLogin();
        }
    }

    public void staffMenu() {
        showStaffMenu();
        int selection = getSelection(4);

        if (selection < 0) {
            staffMenu();
        } else if (selection == 1) {
            addNewMovie();
            staffMenu();
        } else if (selection == 2) {

        } else if (selection == 3) {

        } else if (selection == 4) {

        } else if (selection == 0) {
            mainMenu();
        }
    }

    public void memberMenu() {

    }

    private void memberLogin() {
        Scanner sc = new Scanner(System.in);

//      Have the user enter there username and password
        System.out.println("Please enter username:");
        String inputtedUsername = sc.nextLine();
        System.out.println("Please enter password:");
        String inputtedPasswordString = sc.nextLine();
        int inputtedPasswordInt = Integer.parseInt(inputtedPasswordString);

//        Find the member if they exist, and test if the password is valid
        Member memberToValidate = this.members.returnMemberFromUsername(inputtedUsername);
        if (memberToValidate != null){
            if (memberToValidate.password == inputtedPasswordInt){
                memberMenu();
            }
        }
        System.out.println("Invalid username or password...\n\n");
        mainMenu();
    }

    private void staffLogin() {
        String validUsername = "staff";
        String validPassword = "today123";
        Scanner sc = new Scanner(System.in);

//      Have the user enter there username and password
        System.out.print("Please enter username: ");
        String inputtedUsername = sc.nextLine();
        System.out.print("Please enter password: ");
        String inputtedPassword = sc.nextLine();

//        Verify username/password
        if (inputtedUsername.equals(validUsername) && inputtedPassword.equals(validPassword)) {
            System.out.println("Username and password accepted...\n\n");
            staffMenu();
        } else {
            System.out.println("Invalid username or password...\n\n");
            mainMenu();
        }
    }


    public void showMainMenu() {
        System.out.println("Welcome to the Community Library");
        System.out.println("============Main Menu===========");
        System.out.println(" 1. Staff Login");
        System.out.println(" 2. Member Login");
        System.out.println(" 0. Exit");
        System.out.println("================================");
        System.out.print("Please make a selection (1-2, or 0 to exit): ");
    }


    public void showStaffMenu() {
        System.out.println("===========Staff Menu===========");
        System.out.println(" 1. Add a new movie DVD");
        System.out.println(" 2. Remove a movie DVD");
        System.out.println(" 3. Register a new Member");
        System.out.println(" 4. Find a registered member's phone number");
        System.out.println(" 0. Return to main menu");
        System.out.println("================================");
        System.out.print("Please make a selection (1-4 or 0 to return to main menu): ");
    }


    public void showMemberMenu() {
        System.out.println("==========Member Menu===========");
        System.out.println(" 1. Display all movies");
        System.out.println(" 2. Borrow a movie DVD");
        System.out.println(" 3. Return a movie DVD");
        System.out.println(" 4. List current borrowed movie DVDs");
        System.out.println(" 5. Display top 10 most popular movies");
        System.out.println(" 0. Return to main menu");
        System.out.println("================================");
        System.out.print("Please make a selection (1-5 or 0 to return to main menu): ");
    }

    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int getSelection(int largestValidSelection) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

//        Check if the selection is valid
        if (isInt(input)){
            int selection = Integer.parseInt(input);
            if ((selection > largestValidSelection) || (selection < 0)) {
                System.out.println("Please input a valid selection\n\n");
            } else {
                return selection;
            }
        } else {
            System.out.println("Please input a valid selection\n\n");
        }
        return -1;
    }

    public void addNewMovie(){
//        Create a mew movie
        Scanner sc = new Scanner(System.in);
        System.out.println("============Add Movie============");
        System.out.print("Title: ");
        Movie newMovie  = new Movie(sc.nextLine());

        System.out.print("Starring: ");
        newMovie.starring = sc.nextLine();
        System.out.print("Director: ");
        newMovie.director = sc.nextLine();

//        Input and validate genre
        while (true){
            System.out.print("Genre (Drama,Adventure,Action,Sci-Fi,Comedy,Animated,Thriller,Other): ");
             String genre = sc.nextLine();
            if (newMovie.isValidGenre(genre) || genre.isEmpty()){
                newMovie.starring = genre;
                break;
            } else {
                System.out.println("Please enter a valid genre or leave blank...");
            }
        }

//        Input and validate classification
        while (true){
            System.out.print("Classification (G,PG,M15+,MA15+): ");
            String classification = sc.nextLine();
            if (newMovie.isValidClassification(classification) || classification.isEmpty()){
                newMovie.classification = classification;
                break;
            } else {
                System.out.println("Please enter a valid classification or leave blank...");
            }
        }
        System.out.print("Release Date: ");
        newMovie.releaseDate = sc.nextLine();

//        Add the new movie to the library
        allMovies.add(newMovie);
        availableToRentMovies.add(newMovie);
    }

    public void removeMovie(String movieToRemove){
        allMovies.removeMovieByString(movieToRemove);
        members.removeMovieFromAllMembersCollections(movieToRemove);
    }
}
