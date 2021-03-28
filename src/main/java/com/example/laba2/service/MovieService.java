package com.example.laba2.service;

import com.example.laba2.entity.MovieSession;
import com.example.laba2.entity.Ticket;

import java.util.Date;
import java.util.Set;

//Здесь в рамках лабы 2 можно оставить все как есть
public interface MovieService {

    Set<MovieSession> getAllMovies();

    Set<MovieSession> getSessionByMovieName(String name);

    Ticket buyTicket(String movieName, Date date, int row, int place);

}
