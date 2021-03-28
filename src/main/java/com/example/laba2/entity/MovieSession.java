package com.example.laba2.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movie_session")
public class MovieSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Чтобы упростить себе жизнь, поставим CascadeType.ALL.
    //Это поможет нам в тестах. Как именно - смотрите комментарий над методом Laba2ApplicationTests.findAllTest()
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    //Такое объявление @OneToMany называется unidirectional
    //Если бы в классе Ticket мы бы указали ссылку на MovieSession с аннотацией @ManyToOne,
    //то такая связь бы называлась bidirectional.
    //В базе будет создано только одно поле movie_session_id в таблице Ticket
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="movie_session_id", referencedColumnName="id")
    private Set<Ticket> tickets;

    //Если создаете поле с типом Date, важно указать ему имя отличное от 'date',
    // т. к. это зарезервированое слово
    @Column(name = "movie_session_date", nullable = false)
    private Date date = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovieInformation() {
        return movie;
    }

    public void setMovieInformation(Movie movie) {
        this.movie = movie;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieSession that = (MovieSession) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(tickets, that.tickets) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, tickets, date);
    }
}
