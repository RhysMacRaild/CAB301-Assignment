package com.company;

public class MovieCollection {
    Movie rootMovie;
    int collectionSize = 0;

    //    Used only for sort by borrow count
    Movie[] orderedCollection;
    int orderedCollectionSize = 0;

    Movie[] removedDuplicates;
    int removedDuplicatesCount = 0;


    //    Add a movie to the binary search tree (Which branch determined lexographically)
    public void add(Movie newMovie) {
        //Set new root movie if it doesn't exist already
        if (this.rootMovie == null) {
            this.rootMovie = newMovie;
            collectionSize++;
        }

        //Add to existing tree
        else {
            placeMovie(newMovie, this.rootMovie);
        }
    }

    //    Find an appropriate place in the tree to add a new movie and add it
    public void placeMovie(Movie newMovie, Movie node) {
        if (newMovie.title.compareToIgnoreCase(node.title) <= 0) {
            if (node.Left != null) {
                placeMovie(newMovie, node.Left);
            } else {
                node.Left = newMovie;
                newMovie.Parent = node;
                collectionSize++;
            }

        } else {
            if (node.Right != null) {
                placeMovie(newMovie, node.Right);
            } else {
                node.Right = newMovie;
                newMovie.Parent = node;
                collectionSize++;
            }
        }
    }

    //    Base case using root node
    public Movie returnMovieFromString(String movieTitleToFind) {
        return returnMovieFromString(movieTitleToFind, rootMovie);
    }

    //    Return the first instance of a movie with the same title in a collection
    public Movie returnMovieFromString(String movieTitleToFind, Movie node) {
//        Return null if the BST is empty
        if (rootMovie == null) {
            return null;
        }
        if (node.Left != null){
            System.out.println(movieTitleToFind.compareToIgnoreCase(node.Left.title));
        }

//        Stop searching, the current node is the movie to return
        if (movieTitleToFind.equals(node.title)) {
            return node;
        }

//         Check path to take by checking if left and right nodes exist
        else if (node.Left == null && node.Right == null) {
            return null;
        } else if (node.Left == null && node.Right != null) {
            return returnMovieFromString(movieTitleToFind, node.Right);
        } else if (node.Left != null && node.Right == null) {
            return returnMovieFromString(movieTitleToFind, node.Left);
        }
//        Check which path in the tree to take if both options exist
        else if (movieTitleToFind.compareToIgnoreCase(node.Left.title) <= 0) {
            return returnMovieFromString(movieTitleToFind, node.Left);
        } else if (movieTitleToFind.compareToIgnoreCase(node.Left.title) > 0) {
            return returnMovieFromString(movieTitleToFind, node.Right);
        }
        return null;
    }

    public void removeMovie(Movie movieToRemove) {
//        Case 0: movieToRemove does not exist
        if (movieToRemove == null) {
            return;
        }
//      Case 1: movieToRemove has no subtrees
        if (movieToRemove.Left == null && movieToRemove.Right == null) {
//            Link parent to replacementNode (Remove in this case)
            if (movieToRemove == rootMovie) {
                rootMovie = null;
                collectionSize--;
            } else if (movieToRemove.title.compareToIgnoreCase(movieToRemove.Parent.title) <= 0) {
                movieToRemove.Parent.Left = null;
                collectionSize--;
            } else {
                movieToRemove.Parent.Right = null;
                collectionSize--;
            }
        }
//        Case 2: movieToRemove has only one subtree
        else if (movieToRemove.Left != null && movieToRemove.Right == null) {
            Movie replacementNode = movieToRemove.Left;
            replacementNode.Parent = movieToRemove.Parent;
//            Link parent to replacementNode
            if (movieToRemove == rootMovie) {
                rootMovie = replacementNode;
                collectionSize--;
            } else if (movieToRemove.title.compareToIgnoreCase(movieToRemove.Parent.title) <= 0) {
                replacementNode.Parent.Left = replacementNode;
                collectionSize--;
            } else {
                replacementNode.Parent.Right = replacementNode;
                collectionSize--;
            }

        } else if (movieToRemove.Left == null && movieToRemove.Right != null) {
            Movie replacementNode = movieToRemove.Right;
            replacementNode.Parent = movieToRemove.Parent;
//            Link parent to replacementNode
            if (movieToRemove == rootMovie) {
                rootMovie = replacementNode;
                collectionSize--;
            } else if (movieToRemove.title.compareToIgnoreCase(movieToRemove.Parent.title) <= 0) {
                replacementNode.Parent.Left = replacementNode;
                collectionSize--;
            } else {
                replacementNode.Parent.Right = replacementNode;
                collectionSize--;
            }
            if (replacementNode != rootMovie) {
                //            Update parents
                replacementNode.Left.Parent = replacementNode;
                replacementNode.Right.Parent = replacementNode;
            }

        }

//        Case 3: movieToRemove has two subtrees (Replace movie with the minimum node on right subtree)
        else {
            Movie replacementNode = findMinimumNode(movieToRemove.Right);
            replacementNode.Left = movieToRemove.Left;
            replacementNode.Right = movieToRemove.Right;
//            Update Parents
            replacementNode.Parent.Left = null;
            replacementNode.Left.Parent = replacementNode;
            replacementNode.Right.Parent = replacementNode;
            replacementNode.Parent = movieToRemove.Parent;

//            Link replacementNode to its new parent
            if (movieToRemove == rootMovie) {
                rootMovie = replacementNode;
                collectionSize--;
            } else if (replacementNode.title.compareToIgnoreCase(replacementNode.Parent.title) <= 0) {
                replacementNode.Parent.Left = replacementNode;
                collectionSize--;
            } else if (replacementNode.title.compareToIgnoreCase(replacementNode.Parent.title) > 0) {
                replacementNode.Parent.Right = replacementNode;
                collectionSize--;
            }

//            Confirm that the replacementNode doesn't point to itself
            if (replacementNode.Left == replacementNode) {
                replacementNode.Left = null;
            } else if (replacementNode.Right == replacementNode) {
                replacementNode.Right = null;
            }
        }
    }

    //    Return true if movie to remove exists
    public boolean removeMovieByString(String movieToRemoveString) {
        Movie movieToRemove = returnMovieFromString(movieToRemoveString);
        if (movieToRemove != null) {
            removeMovie(movieToRemove);
            return true;
        }
        return false;
    }

    public Movie findMinimumNode(Movie startingNode) {
        if (startingNode == null) {
            return null;
        }
        Movie currentNode = startingNode;
        while (currentNode.Left != null) {
            currentNode = currentNode.Left;
        }
        return currentNode;
    }

    public void listByBorrowCount() {
//        Base cases where the BST has no movies or one movie
        if (rootMovie == null) {
            return;
        } else if (collectionSize == 1) {
            removedDuplicates = new Movie[1];
            removedDuplicates[0] = rootMovie;
            removedDuplicatesCount = 1;
        } else {
            orderedCollection = new Movie[collectionSize];
            orderedCollectionSize = 0;

            addToArray(rootMovie);
            mergeDuplicateMovies();
            quickSort(0, removedDuplicatesCount - 1);
        }

//        List highest 10 borrow count movies
        int startIndex = removedDuplicatesCount - 1;
        int stopIndex = removedDuplicatesCount - 10;
        if (stopIndex < 0) {
            stopIndex = 0;
        }
        for (int i = startIndex; i >= stopIndex; i--) {
            if (removedDuplicates[i] != null) {
                System.out.println("Borrow Count: " + removedDuplicates[i].borrowCount + " Title: " + removedDuplicates[i].title);
            }
        }
    }

    //    If a two copies of one movie exist, merge the borrow count of all duplicates
    public void mergeDuplicateMovies() {
        removedDuplicates = new Movie[orderedCollectionSize];
        removedDuplicatesCount = 0;
        Movie pivotMovie = new Movie(orderedCollection[0]);
        int index = 1;
        int removedDuplicatesIndex = 0;
        while (index < orderedCollectionSize) {
            try {
                while (pivotMovie.title.equals(orderedCollection[index].title)) {
                    pivotMovie.borrowCount += orderedCollection[index].borrowCount;
                    index++;
                }
            } catch (Exception e) {
                break;
            }
            removedDuplicates[removedDuplicatesIndex] = pivotMovie;
            removedDuplicatesCount++;
            pivotMovie = new Movie(orderedCollection[index]);
            index++;
            removedDuplicatesIndex++;
        }
//        Add last movie if it is unique
        if (!pivotMovie.title.equals(orderedCollection[index - 2].title)) {
            pivotMovie = new Movie(orderedCollection[index - 1]);
            removedDuplicates[removedDuplicatesIndex] = pivotMovie;
            removedDuplicatesCount++;
        }
//        Remove null entries in removedDuplicates
        Movie[] tmp = new Movie[removedDuplicatesCount];
        for (index = 0; index < removedDuplicatesCount; index++) {
            tmp[index] = removedDuplicates[index];
        }
        removedDuplicates = tmp;
    }


    public void quickSort(int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int partitionIndex = partition(lowIndex, highIndex);
            quickSort(lowIndex, partitionIndex - 1);
            quickSort(partitionIndex + 1, highIndex);
        }
    }

    public int partition(int lowIndex, int highIndex) {
        Movie pivot = removedDuplicates[highIndex];
        int index = lowIndex;
        int i = lowIndex;
        for (int j = lowIndex; j <= highIndex; j++) {
            if (removedDuplicates[j].borrowCount < pivot.borrowCount) {
                Movie tmp = removedDuplicates[i];
                removedDuplicates[i] = removedDuplicates[j];
                removedDuplicates[j] = tmp;
                i++;
            }
        }
        Movie tmp = removedDuplicates[i];
        removedDuplicates[i] = removedDuplicates[highIndex];
        removedDuplicates[highIndex] = tmp;
        return i;
    }

    public void listMovieLexicographically() {
        if (rootMovie != null) {
            printMovieNamesInOrder(rootMovie);
        } else {
            System.out.println("Collection Empty...");
        }
    }

    public void printMovieNamesInOrder(Movie currentNode) {

        if (currentNode.Left != null) {
            printMovieNamesInOrder(currentNode.Left);
        }

        printCurrentNode(currentNode);

        if (currentNode.Right != null) {
            printMovieNamesInOrder((currentNode.Right));
        }
    }

    public void addToArray(Movie currentNode) {

        if (currentNode.Left != null) {
            addToArray(currentNode.Left);
        }

        orderedCollection[orderedCollectionSize] = currentNode;
        orderedCollectionSize++;

        if (currentNode.Right != null) {
            addToArray((currentNode.Right));
        }

    }

    public void printCurrentNode(Movie currentNode) {
//        orderedCollection[orderedCollectionSize] = currentNode;
//        orderedCollectionSize++;
        System.out.println("========= " + currentNode.title + " =========");
        System.out.println("Starring: " + currentNode.starring);
        System.out.println("Director: " + currentNode.director);
        System.out.println("Genre: " + currentNode.genre);
        System.out.println("Classification: " + currentNode.classification);
        System.out.println("Release Date: " + currentNode.releaseDate + "\n");
    }
}


