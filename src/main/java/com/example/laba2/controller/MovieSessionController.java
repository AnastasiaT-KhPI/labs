package com.example.laba2.controller;

import com.example.laba2.controller.dto.BuyTicketDTO;
import com.example.laba2.entity.MovieSession;
import com.example.laba2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MovieSessionController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movieSession", method = RequestMethod.GET)
    @ResponseBody
    public Collection<MovieSession> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/session/{name}")
    @ResponseBody
    public Collection<MovieSession> getSessionByMovieName(@PathVariable("name") String name) {
        return movieService.getSessionByMovieName(name);
    }

    @RequestMapping(value = "/ticket/buy", method = RequestMethod.POST)
    public void buyTicket(@RequestBody BuyTicketDTO buyTicketDTO) {
        movieService.buyTicket(buyTicketDTO.getMovieName(),
                buyTicketDTO.getDate(),
                buyTicketDTO.getRow(),
                buyTicketDTO.getPlace());
    }

}
