package com.bhoper.client;

import com.bhoper.model.Course;

import java.util.List;

public interface CoursesRestClient {

    List<Course> findAllCourses();

}
