package service;

import entity.Movie;
import entity.MovieSession;

import java.util.Date;
import java.util.Set;

public interface MovieService {

    Set<MovieSession> getAllMovies();

    Set<Movie> getMovieByName(String name);

    void buyTicket(Movie movie, Date date, int row, int place);

    void returnTicket(Integer ticketId);

}
