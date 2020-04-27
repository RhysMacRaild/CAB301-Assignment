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
            PlaceMovie(newMovie, this.rootMovie);
        }
    }

    //    Find an appropriate place in the tree to add a new movie and add it
    public void PlaceMovie(Movie newMovie, Movie node)
    {
        if (newMovie.Title.compareTo(node.Title) <= 0)
        {
            if (node.Left != null)
            {
                PlaceMovie(newMovie, node.Left);
            } else
            {
                node.Left = newMovie;
            }

        } else
        {
            if (node.Right != null)
            {
                PlaceMovie(newMovie, node.Right);
            } else
            {
                node.Right = newMovie;
            }
        }
    }
}
