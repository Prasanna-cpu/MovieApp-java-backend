package org.spring.kumar.backend.Controller;


import org.bson.types.ObjectId;
import org.spring.kumar.backend.Entity.Movie;
import org.spring.kumar.backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "http://localhost:7000", allowedHeaders = "*")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Movie>> allMovies(){
        List<Movie> allMovies = movieService.getAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }


    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id){
        Movie movie = movieService.getMovieByImdbId(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
