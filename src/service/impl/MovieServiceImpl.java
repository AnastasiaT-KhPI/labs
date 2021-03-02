package service.impl;

import entity.Movie;
import entity.MovieSession;
import entity.Ticket;
import service.MovieService;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    @Override
    public Set<MovieSession> getAllMovies() {
        return getAllMovieSessionsData();
    }

    @Override
    public Set<MovieSession> getSessionByMovieName(String name) {
        Set<MovieSession> movies = getAllMovieSessionsData();
        //Оставим пока фильтр по имени вручную
        //Дадльше это будет решено в одну строку с помощью hibernate
        return movies.stream()
                .filter(movie -> movie.getMovieInformation().getName().equals(name))
                .collect(Collectors.toSet());
    }

    //Ищем сеанс по названию фильма и дате
    //Билет - по номеру ряда и места и ставим ему флаг isAvailable = false, что означает - билет куплен
    @Override
    public Ticket buyTicket(String movieName, Date date, int row, int place) {
        Set<MovieSession> movieSessions = getSessionByMovieName(movieName);

        MovieSession movieSession = movieSessions.stream()
                .filter(session -> date.equals(session.getDate()))
                .findFirst()
                .orElse(null);

        //Здесь можно добавить исключение, если вдруг билет уже куплен
        Ticket ticket = movieSession.getTickets().stream()
                .filter(value -> value.getRow() == row
                        && value.getPlace() == place)
                .findFirst()
                .orElse(null);
        //Если все ок, то осуществляем покупку билета
        if (ticket == null) {
            throw new RuntimeException("Ticket not found");
        }
        ticket.setAvailable(false);
        return ticket;
    }

    //Эти методы временные и будут перенесен в тесты в следующей лабе
    //Так как пока что базы у нас нет, создаем такую заглушку с данными
    private Set<MovieSession> getAllMovieSessionsData() {
        Set<MovieSession> movieSessions = new HashSet<>();
        List<Movie> movies = getAllMoviesData();
        Set<Ticket> tickets = getAllTicketsData();
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setId(1);
        movieSession1.setMovieInformation(movies.get(0));

        //Почему дата задается так посмотрите в описании конструктора Date (перейдите на него по Ctrl)
        //В рамках лабы на то, что метод deprecated, можно не обращать пока внимания
        movieSession1.setDate(new Date(2021 - 1900, 3, 8, 12, 0, 0));
        movieSession1.setTickets(tickets);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setId(2);
        movieSession2.setMovieInformation(movies.get(1));
        movieSession2.setDate(new Date(2021 - 1900, 3, 8, 16, 0, 0));
        movieSession2.setTickets(tickets);

        movieSessions.add(movieSession1);
        movieSessions.add(movieSession2);

        return movieSessions;
    }

    private List<Movie> getAllMoviesData() {
        Movie movie1 = new Movie();
        movie1.setId(1);
        movie1.setName("Movie 1");
        movie1.setDescription("Description 1");

        Movie movie2 = new Movie();
        movie2.setId(2);
        movie2.setName("Movie 2");
        movie2.setDescription("Description 2");
        //Обратите внимание, что этот метод возращает неизменяемый список
        //То есть при попытке изменить содержимое списка, у вас ничего не получится
        return Arrays.asList(movie1, movie2);
    }

    private Set<Ticket> getAllTicketsData() {
        Set tickets = new HashSet();

        Ticket ticket1 = new Ticket();
        ticket1.setId(1);
        ticket1.setRow(1);
        ticket1.setPlace(10);

        Ticket ticket2 = new Ticket();
        ticket2.setId(2);
        ticket2.setRow(2);
        ticket2.setPlace(10);

        Ticket ticket3 = new Ticket();
        ticket3.setId(3);
        ticket3.setRow(3);
        ticket3.setPlace(10);

        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);

        return tickets;
    }
}
