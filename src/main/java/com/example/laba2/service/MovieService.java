package service;

import entity.MovieSession;
import entity.Ticket;

import java.util.Date;
import java.util.Set;

public interface MovieService {

    Set<MovieSession> getAllMovies();

    Set<MovieSession> getSessionByMovieName(String name);

    Ticket buyTicket(String movieName, Date date, int row, int place);

}
