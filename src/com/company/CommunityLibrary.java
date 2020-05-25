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
            removeMovie();
            staffMenu();
        } else if (selection == 3) {
            registerNewMember();
            staffMenu();
        } else if (selection == 4) {
            findMemberPhoneNumber();
            staffMenu();
        } else if (selection == 0) {
            mainMenu();
        }
    }

    public void memberMenu(Member loggedInMember) {
        showMemberMenu();
        int selection = getSelection(5);
        if (selection < 0) {
            memberMenu(loggedInMember);
        } else if (selection == 1) {
            displayAllMovies();
            memberMenu(loggedInMember);
        } else if (selection == 2) {
            borrowMovie(loggedInMember);
            memberMenu(loggedInMember);
        } else if (selection == 3) {
            returnMovie(loggedInMember);
            memberMenu(loggedInMember);
        } else if (selection == 4) {
            listCurrentBorrowedMovies(loggedInMember);
            memberMenu(loggedInMember);
        } else if (selection == 5) {
            displayMostPopularMovies();
            memberMenu(loggedInMember);
        } else if (selection == 0) {
            mainMenu();
        }
    }

    public void displayMostPopularMovies(){
        allMovies.listByBorrowCount();
    }

    private void listCurrentBorrowedMovies(Member member){
        member.borrowedMovies.listMovieLexicographically();
    }

    private void returnMovie(Member memberReturningMovie) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Title of movie to return: ");
        String movieTitle = sc.nextLine();
        Movie movieToReturn = memberReturningMovie.borrowedMovies.returnMovieFromString(movieTitle);
        if (movieToReturn != null){
            memberReturningMovie.borrowedMovies.removeMovie(movieToReturn);
            availableToRentMovies.add(new Movie(movieToReturn));
        }

    }
    public void borrowMovie(Member memberBorrowingMovie){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter title of movie to borrow: ");
        String movieToBorrow = sc.nextLine();
        Movie movieToRent = availableToRentMovies.returnMovieFromString(movieToBorrow);

//        Add the movie to the members movie collection if the movie exists
        if (movieToRent != null){
            availableToRentMovies.removeMovie(movieToRent);
            memberBorrowingMovie.borrowedMovies.add(new Movie(movieToRent));

//            Increase borrow count in allMovies collection
            Movie movieToIncreaseBorrowCount = allMovies.returnMovieFromString(movieToBorrow);
            movieToIncreaseBorrowCount.borrowCount++;
            System.out.println("Movie borrowed successfully");
        } else {
            System.out.println("Unable to borrow selected movie. Please insure title is correct...");
        }

    }

    private void displayAllMovies() {
        System.out.println("Number of Movies in Library: " + allMovies.collectionSize);
        allMovies.listMovieLexicographically();
    }

    private void memberLogin() {
        Scanner sc = new Scanner(System.in);

        try {
//      Have the user enter there username and password
            System.out.print("Please enter username:");
            String inputtedUsername = sc.nextLine();
            System.out.print("Please enter password:");
            String inputtedPasswordString = sc.nextLine();
            int inputtedPasswordInt = Integer.parseInt(inputtedPasswordString);

//        Find the member if they exist, and test if the password is valid
            Member memberToValidate = this.members.returnMemberFromUsername(inputtedUsername);
            if (memberToValidate != null) {
                if (memberToValidate.password == inputtedPasswordInt) {
                    memberMenu(memberToValidate);
                }
            }
            System.out.println("Invalid username or password...\n\n");
        } catch (Exception e){
            System.out.println("Invalid username or password...\n\n");
        }

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
        if (isInt(input)) {
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

    public void addNewMovie(Movie newMovie){
        allMovies.add(new Movie(newMovie));
        availableToRentMovies.add(new Movie(newMovie));
    }

    public void addNewMovie() {
//        Create a mew movie
        Scanner sc = new Scanner(System.in);
        System.out.println("============Add Movie============");
        System.out.print("Title: ");
        Movie newMovie = new Movie(sc.nextLine());

        System.out.print("Starring: ");
        newMovie.starring = sc.nextLine();
        System.out.print("Director: ");
        newMovie.director = sc.nextLine();

//        Input and validate genre
        while (true) {
            System.out.print("Genre (Drama,Adventure,Action,Sci-Fi,Comedy,Animated,Thriller,Other): ");
            String genre = sc.nextLine();
            if (newMovie.isValidGenre(genre) || genre.isEmpty()) {
                newMovie.genre = genre;
                break;
            } else {
                System.out.println("Please enter a valid genre or leave blank...");
            }
        }

//        Input and validate classification
        while (true) {
            System.out.print("Classification (G,PG,M15+,MA15+): ");
            String classification = sc.nextLine();
            if (newMovie.isValidClassification(classification) || classification.isEmpty()) {
                newMovie.classification = classification;
                break;
            } else {
                System.out.println("Please enter a valid classification or leave blank...");
            }
        }
        System.out.print("Release Date: ");
        newMovie.releaseDate = sc.nextLine();

//        Add the new movie to the library
//        Note that due to the implementation of the BST, no movie object can exist in two collections
//        simultaneously, hence a Deep copy must be used to store the movie in the two collections
        allMovies.add(new Movie(newMovie));
        availableToRentMovies.add(new Movie(newMovie));
    }

    public void removeMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("============Add Movie============");
        System.out.print("Enter title of movie to remove: ");
        String movieToRemove = sc.nextLine();

//        Remove all instances of a movie from the allMovies collection
        boolean instanceOfMovieInCollection = true;
        while (instanceOfMovieInCollection){
            instanceOfMovieInCollection = allMovies.removeMovieByString(movieToRemove);
            availableToRentMovies.removeMovieByString(movieToRemove);
        }
//        No loop needed as members can't borrow more then one instance of the same movie
        members.removeMovieFromAllMembersCollections(movieToRemove);
    }


    public void registerNewMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("============Register Member============");
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Residential Address: ");
        String residentialAddress = sc.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = sc.nextLine();

//        Check if password is valid
        while (true) {
            System.out.print("Password (4 Digits): ");
            String password = sc.nextLine();
            if (password.length() == 4) {
                try {
                    int passwordInt = Integer.parseInt(password);
                    members.addMember(new Member(firstName, lastName, residentialAddress, phoneNumber, passwordInt));
                    return;
                } catch (Exception e) {
                    System.out.println("Please enter a valid password...");
                }
            } else {
                System.out.println("Please enter a valid password...");
            }

        }
    }

    public void findMemberPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.println("============Recover Phone No.============");
        System.out.print("Member first name: ");
        String firstName = sc.nextLine();
        System.out.print("Member last name: ");
        String lastName = sc.nextLine();
        try {
            Member member = members.returnMemberFromUsername(firstName + lastName);
            System.out.println(firstName + " " + lastName + "s phone number: " + member.phoneNumber);
        } catch (NullPointerException e) {
            System.out.println("Member not found...");
        }
    }
}
