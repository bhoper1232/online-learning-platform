package com.bhoper.controller;

import com.bhoper.client.CoursesRestClient;
import com.bhoper.model.Course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CoursesRestClient restClient;

    @GetMapping("/home")
    public String getCourses(Model model) {
        model.addAttribute("courses", this.restClient.findAllCourses());
        return "courses";
    }
}
