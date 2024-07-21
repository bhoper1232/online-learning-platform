package com.bhoper.controller;

import com.bhoper.dto.CourseCreateRequest;
import com.bhoper.dto.CourseUpdateRequest;
import com.bhoper.model.Course;
import com.bhoper.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseRestController {

    private final CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable("courseId") String id) {
        return this.courseService.getCourseById(id);
    }

    @GetMapping("/search")
    public List<Course> findAllByName(@RequestParam("word") String word) {
        return this.courseService.getCoursesByNameContaining(word);
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseCreateRequest courseCreateRequest) {
        return this.courseService.createCourse(courseCreateRequest);
    }

    @PatchMapping
    public Course updateCourse(@RequestBody CourseUpdateRequest courseUpdateRequest) {
        return this.courseService.updateCourse(courseUpdateRequest);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable("courseId") String id) {
        this.courseService.deleteCourse(id);
    }

}
