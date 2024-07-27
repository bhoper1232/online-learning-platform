package com.bhoper.client.course;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "course-service")
public interface CourseClient {

    @GetMapping("/courses/{courseId}")
    Course getCourseById(@PathVariable("courseId") String id);

    @GetMapping("/courses")
    List<Course> getAllCourses();

    @PostMapping("/courses")
    Course createCourse(CourseCreateRequest courseCreateRequest);

}
