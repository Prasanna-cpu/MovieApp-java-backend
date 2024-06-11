package org.spring.kumar.backend.Repository;


import org.bson.types.ObjectId;
import org.spring.kumar.backend.Entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository  extends MongoRepository<Movie, ObjectId> {
     Movie findByImdbId(String ImdbId);
}
