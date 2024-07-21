package com.bhoper.service;

import com.bhoper.dto.CourseCreateRequest;
import com.bhoper.dto.CourseUpdateRequest;
import com.bhoper.model.Course;
import com.bhoper.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return this.courseRepository.findById(id).orElseThrow();
    }

    public Course createCourse(CourseCreateRequest courseCreateRequest) {
        Course course = Course.builder()
                .name(courseCreateRequest.name())
                .description(courseCreateRequest.description())
                .instructor(courseCreateRequest.instructor())
                .build();
        return this.courseRepository.save(course);
    }

    public Course updateCourse(CourseUpdateRequest courseUpdateRequest) {
        // todo:
        return null;
    }

    public List<Course> getCoursesByNameContaining(String word) {
        return courseRepository.findByNameContainingIgnoreCase(word);
    }

    public void deleteCourse(String id) {
        this.courseRepository.deleteById(id);
    }

}
