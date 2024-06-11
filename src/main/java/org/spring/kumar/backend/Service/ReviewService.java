package org.spring.kumar.backend.Service;


import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.kumar.backend.Entity.Movie;
import org.spring.kumar.backend.Entity.Review;
import org.spring.kumar.backend.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {


     private static final Logger logger=LoggerFactory.getLogger(ReviewService.class);

     private final ReviewRepository reviewRepository;
     private final MongoTemplate mongoTemplate;

     @Autowired
     public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate) {
         this.reviewRepository = reviewRepository;
         this.mongoTemplate = mongoTemplate;

     }

      public Review createReview(String reviewBody,String imdbId) {

          logger.debug("Creating review with body:{} for imdbId:{}", reviewBody, imdbId);
          Optional<Movie> movieOptional = Optional.ofNullable(mongoTemplate.findOne(
                  new Query(Criteria.where("imdbId").is(imdbId)), Movie.class));

          if (movieOptional.isEmpty()) {
              logger.warn("No movie found with imdbId: {}", imdbId);
              throw new IllegalArgumentException("Movie with provided imdbId does not exist.");
          }



          Review review = reviewRepository.insert(new Review(reviewBody, LocalDateTime.now(),LocalDateTime.now()));

          logger.info("Review: {}", review);

          UpdateResult result=mongoTemplate.update(Movie.class)
                  .matching(Criteria.where("imdbId").is(imdbId))
                  .apply(new Update().push("reviewIds", review))
                  .first();

          logger.info("UpdateResult: {}", result);


          return review;


      }
}
