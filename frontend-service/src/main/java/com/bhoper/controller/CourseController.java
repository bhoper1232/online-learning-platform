package com.bhoper.controller;

import com.bhoper.client.CourseClient;
import com.bhoper.client.EnrollmentClient;
import com.bhoper.controller.payload.EnrollmentCreateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseClient courseClient;

    private final EnrollmentClient enrollmentClient;

    @GetMapping("/home")
    public String getCourses(Model model) {
        model.addAttribute("courses", this.courseClient.getAllCourses());
        return "courses";
    }

    @PostMapping("/enrollments")
    public String enrollCourse(@RequestParam("courseId") String courseId, Principal principal) {
        this.enrollmentClient.createEnrollment(new EnrollmentCreateRequest(principal.getName(),
                courseId, "active"));
        return "redirect:/home";
    }

}
