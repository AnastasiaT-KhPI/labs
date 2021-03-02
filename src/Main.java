import entity.MovieSession;
import entity.Ticket;
import service.MovieService;
import service.impl.MovieServiceImpl;

import java.util.Date;
import java.util.Set;

public class Main {

    private final static Date DATE = new Date(2021 - 1900, 3, 8, 12, 0, 0);
    private final static String MOVIE_NAME = "Movie 1";


    //Во 2й лабе содержимое данного метода будете перенесено в тесты
    public final static void main(String args[]) {
        MovieService movieService = new MovieServiceImpl();

        //Получим все сеансы и выведем их количество
        Set<MovieSession> movieSessions = movieService.getAllMovies();
        System.out.println("All movie sessions amount: " + movieSessions.size());

        //Получим сеансы по имени фильма
        Set<MovieSession> movieSessions1 = movieService.getSessionByMovieName(MOVIE_NAME);
        System.out.println("Movie 1 sessions count: " + movieSessions1.size());

        //Купим билет
        Ticket ticket = movieService.buyTicket(MOVIE_NAME, DATE, 1, 10);

        //Проверим, что он тепер не доступен
        System.out.println(ticket);

        //Купим невалидный билет специально, чтобы получить Exception
        //На это тоже будет написан тест
        movieService.buyTicket(MOVIE_NAME, DATE, 1, 101);
    }
}
