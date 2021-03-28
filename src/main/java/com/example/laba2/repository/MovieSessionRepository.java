package com.example.laba2.repository;

import com.example.laba2.entity.MovieSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface MovieSessionRepository extends CrudRepository<MovieSession, Integer> {

    //findAll в класса CrudRepository возвращает Iterable, но здесь нам нужен Set
    //Поэтому переобъявляем метод, но немного меняем сигнатуру
    //Но можно оставить как есть и преобразовать коллекцию в Set уже в самом сервисе
    Set<MovieSession> findAll();

    //Поиск сеанса по имени фильма. Строим имя метода соответствующим образом
    Set<MovieSession> findAllByMovieName(@Param("movieName") String movieName);

    //Ищем сеанс по названию фильма и дате
    //Важно, чтобы метод возвращал Optional
    Optional<MovieSession> findByMovieNameAndDate(
            @Param("movieName") String movieName, @Param("sessionDate") Date sessionDate);
}
