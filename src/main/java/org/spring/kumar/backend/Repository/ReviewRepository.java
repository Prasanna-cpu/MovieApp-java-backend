package org.spring.kumar.backend.Repository;

import org.bson.types.ObjectId;
import org.spring.kumar.backend.Entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
