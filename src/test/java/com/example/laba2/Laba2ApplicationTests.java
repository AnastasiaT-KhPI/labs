package com.example.laba2;

import com.example.laba2.entity.Movie;
import com.example.laba2.entity.MovieSession;
import com.example.laba2.entity.Ticket;
import com.example.laba2.repository.MovieSessionRepository;
import com.example.laba2.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@Transactional
@Rollback
class Laba2ApplicationTests {

	@Autowired
	private MovieSessionRepository movieSessionRepository;

	@Autowired
	private MovieService movieService;

	//Константы перенесены из класса Main
	private final static Date DATE = new Date(2021 - 1900, 3, 8, 12, 0, 0);
	private final static String MOVIE_NAME = "Movie 1";

	//Так как у нас над полями в MovieSession указано cascade = CascadeType.ALL,
	//нет необходимости сохранять сначала Movie, потом Ticket.
	//Просто заполните эти все поля в MovieSession и hibernate выполнит каскадный insert

	@BeforeEach //метод будет выполнен перед каждым тестом
	private void fillData() {
		movieSessionRepository.saveAll(getAllMovieSessionsData());
	}

	/*
	Было
		Set<MovieSession> movieSessions = movieService.getAllMovies();
        System.out.println("All movie sessions amount: " + movieSessions.size());

    Теперь переносим такую же логику в тест. Меняется только одна строчка.
	*/
	@Test
	public void findAllTest() {
		Set<MovieSession> movieSessions = movieService.getAllMovies();
		Assertions.assertEquals(2, movieSessions.size());
	}

	/*
	Было
    Set<MovieSession> movieSessions1 = movieService.getSessionByMovieName(MOVIE_NAME);
    System.out.println("Movie 1 sessions count: " + movieSessions1.size());

    Аналогично findAllTest заменяем на Assertions.assertEquals(...)
    */
	@Test
	public void getSessionByMovieNameTest() {
		Set<MovieSession> movieSessions = movieService.getSessionByMovieName(MOVIE_NAME);
		Assertions.assertEquals(1, movieSessions.size());
	}

	//Проверим покупку билета
	@Test
	public void buyTicketTest() {
		Ticket ticket = movieService.buyTicket(MOVIE_NAME, DATE, 1, 10);
		//Проверим, что билет тепер не доступен
		Assertions.assertFalse(ticket.isAvailable());
	}

	//Тест на проверку исключения
	@Test
	public void buyTicketExceptionExpectedTest() {
		Assertions.assertThrows(RuntimeException.class, () ->
				movieService.buyTicket(MOVIE_NAME, DATE, 1, 101));
	}


	//В первой лабе это было в MovieSessionServiceImpl.
	// Важно убрать ВСЕ вызовы setId, так как теперь все Id генерируются автоматически
	private Set<MovieSession> getAllMovieSessionsData() {
		Set<MovieSession> movieSessions = new HashSet<>();
		List<Movie> movies = getAllMoviesData();
		Set<Ticket> tickets = getAllTicketsData();
		MovieSession movieSession1 = new MovieSession();
		movieSession1.setMovieInformation(movies.get(0));

		//Почему дата задается так посмотрите в описании конструктора Date (перейдите на него по Ctrl)
		//В рамках лабы на то, что метод deprecated, можно не обращать пока внимания
		movieSession1.setDate(new Date(2021 - 1900, 3, 8, 12, 0, 0));
		movieSession1.setTickets(tickets);

		MovieSession movieSession2 = new MovieSession();
		movieSession2.setMovieInformation(movies.get(1));
		movieSession2.setDate(new Date(2021 - 1900, 3, 8, 16, 0, 0));
		movieSession2.setTickets(tickets);

		movieSessions.add(movieSession1);
		movieSessions.add(movieSession2);

		return movieSessions;
	}

	private List<Movie> getAllMoviesData() {
		Movie movie1 = new Movie();
		movie1.setName("Movie 1");
		movie1.setDescription("Description 1");

		Movie movie2 = new Movie();
		movie2.setName("Movie 2");
		movie2.setDescription("Description 2");
		//Обратите внимание, что этот метод возращает неизменяемый список
		//То есть при попытке изменить содержимое списка, у вас ничего не получится
		return Arrays.asList(movie1, movie2);
	}

	private Set<Ticket> getAllTicketsData() {
		Set tickets = new HashSet();

		Ticket ticket1 = new Ticket();
		ticket1.setRow(1);
		ticket1.setPlace(10);

		Ticket ticket2 = new Ticket();
		ticket2.setRow(2);
		ticket2.setPlace(10);

		Ticket ticket3 = new Ticket();
		ticket3.setRow(3);
		ticket3.setPlace(10);

		tickets.add(ticket1);
		tickets.add(ticket2);
		tickets.add(ticket3);

		return tickets;
	}


}
