package com.company;

public class MovieCollection
{
    Movie rootMovie;

    public void add(Movie newMovie)
    {
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
    public void placeMovie(Movie newMovie, Movie node)
    {
        if (newMovie.title.compareTo(node.title) <= 0)
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
}
