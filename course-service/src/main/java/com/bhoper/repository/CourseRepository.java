package com.bhoper.repository;

import com.bhoper.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Course> findByNameContainingIgnoreCase(String word);


}
