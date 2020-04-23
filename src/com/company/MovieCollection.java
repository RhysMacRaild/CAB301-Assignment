package com.company;

public class MovieCollection
{
    Movie Root;

    public MovieCollection(Movie rootMovie)
    {
        this.Root = rootMovie;
        this.Root.Key = rootMovie.Title;
    }

    void add(Movie movie)
    {
        movie.Key = movie.Title;
//        IF movie.Key < this.Root.movie.Key{
//        search down tree
    }
}
