package service.impl;

import entity.Movie;
import entity.MovieSession;
import service.MovieService;

import java.util.Date;
import java.util.Set;

public class MovieServiceImpl implements MovieService {

    @Override
    public Set<MovieSession> getAllMovies() {
        return null;
    }

    @Override
    public Set<Movie> getMovieByName(String name) {
        return null;
    }

    @Override
    public void buyTicket(Movie movie, Date date, int row, int place) {

    }

    @Override
    public void returnTicket(Integer ticketId) {

    }
}
