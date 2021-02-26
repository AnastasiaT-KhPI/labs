package entity;

import java.util.Date;
import java.util.Set;

public class MovieSession {

    private Integer id;
    private Movie movie;
    private Set<Ticket> tickets;
    private Date date;

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
}
