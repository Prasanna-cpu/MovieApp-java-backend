package org.spring.kumar.backend.Service;


import org.bson.types.ObjectId;
import org.spring.kumar.backend.Entity.Movie;
import org.spring.kumar.backend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieByImdbId(String ImdbId){
        Movie movie=movieRepository.findByImdbId(ImdbId);
        return movie;
    }


}
