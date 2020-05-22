package com.company;

public class MovieCollection
{
    Movie rootMovie;

//    Add a movie to the binary search tree (Which branch determined lexographically)
    public void add(Movie newMovie) {
        //Set new root movie if it doesn't exist already
        if (this.rootMovie == null)
        {
            this.rootMovie = newMovie;
        }

        //Add to existing tree
        else
        {
            placeMovie(newMovie, this.rootMovie);
        }
    }

    //    Find an appropriate place in the tree to add a new movie and add it
    public void placeMovie(Movie newMovie, Movie node) {
        if (newMovie.title.compareToIgnoreCase(node.title) <= 0)
        {
            if (node.Left != null)
            {
                placeMovie(newMovie, node.Left);
            } else
            {
                node.Left = newMovie;
                newMovie.Parent = node;
            }

        } else
        {
            if (node.Right != null)
            {
                placeMovie(newMovie, node.Right);
            } else
            {
                node.Right = newMovie;
                newMovie.Parent = node;
            }
        }
    }

//    Base case using root node
    public Movie returnMovieFromString(String movieTitleToFind){
        return returnMovieFromString(movieTitleToFind, rootMovie);
    }

    public Movie returnMovieFromString(String movieTitleToFind, Movie node){
//        Return null if the BST is empty
        if (rootMovie == null){
            return null;
        }

//        Stop searching, the current node is the movie to return
        if (movieTitleToFind.equals(node.title)){
            return node;
        }

//         Check path to take by checking if left and right nodes exist
        else if (node.Left == null && node.Right == null){
            return null;
        } else if (node.Left == null && node.Right!= null){
            return returnMovieFromString(movieTitleToFind, node.Right);
        } else if (node.Left !=null && node.Right == null){
            return returnMovieFromString(movieTitleToFind, node.Left);
        }

//        Check which path in the tree to take if both options exist
         else if (movieTitleToFind.compareToIgnoreCase(node.Left.title) <= 0){
            return returnMovieFromString(movieTitleToFind, node.Left);
        } else if (movieTitleToFind.compareToIgnoreCase(node.Left.title) > 0){
            return returnMovieFromString(movieTitleToFind, node.Right);
        }
         return null;
    }

    public void removeMovie(Movie movieToRemove) {

//      Case 1: movieToRemove has no subtrees
        if (movieToRemove.Left == null && movieToRemove.Right == null) {
//            Link parent to replacementNode (Remove in this case)
            if (movieToRemove == rootMovie){
                rootMovie = null;
            }
            else if (movieToRemove.title.compareToIgnoreCase(movieToRemove.Parent.title) <= 0) {
                movieToRemove.Parent.Left = null;
            } else {
                movieToRemove.Parent.Right = null;
            }
        }
//        Case 2: movieToRemove has only one subtree
        else if (movieToRemove.Left != null && movieToRemove.Right == null) {
            Movie replacementNode = movieToRemove.Left;
            replacementNode.Parent = movieToRemove.Parent;
//            Link parent to replacementNode
            if (movieToRemove == rootMovie){
                rootMovie = replacementNode;
            }
            else if (movieToRemove.title.compareToIgnoreCase(movieToRemove.Parent.title) <= 0) {
                replacementNode.Parent.Left = replacementNode;
            } else {
                replacementNode.Parent.Right = replacementNode;
            }

        } else if (movieToRemove.Left == null && movieToRemove.Right != null) {
            Movie replacementNode = movieToRemove.Right;
            replacementNode.Parent = movieToRemove.Parent;
//            Link parent to replacementNode
            if (movieToRemove == rootMovie){
                rootMovie = replacementNode;
            }
            else if (movieToRemove.title.compareToIgnoreCase(movieToRemove.Parent.title) <= 0) {
                replacementNode.Parent.Left = replacementNode;
            } else {
                replacementNode.Parent.Right = replacementNode;
            }
            if (replacementNode != rootMovie){
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
            } else if (replacementNode.title.compareToIgnoreCase(replacementNode.Parent.title) <= 0) {
                replacementNode.Parent.Left = replacementNode;
            } else if (replacementNode.title.compareToIgnoreCase(replacementNode.Parent.title) > 0) {
                replacementNode.Parent.Right = replacementNode;
            }

//            Confirm that the replacementNode doesn't point to itself
            if (replacementNode.Left == replacementNode){
                replacementNode.Left = null;
            } else if (replacementNode.Right == replacementNode){
                replacementNode.Right = null;
            }
        }
    }

    public void removeMovieByString(String movieToRemoveString){
        Movie movieToRemove = returnMovieFromString(movieToRemoveString);
        if (movieToRemove != null){
            removeMovie(movieToRemove);
        }
    }

    public Movie findMinimumNode(Movie startingNode){
        if (startingNode == null){
            return null;
        }
        Movie currentNode = startingNode;
        while (currentNode.Left != null){
            currentNode = currentNode.Left;
        }
        return currentNode;
    }
}


