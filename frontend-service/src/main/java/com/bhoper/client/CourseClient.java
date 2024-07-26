package com.bhoper.client;

import com.bhoper.model.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "course-service")
public interface CourseClient {

    @GetMapping("/courses")
    List<Course> getAllCourses();

}
