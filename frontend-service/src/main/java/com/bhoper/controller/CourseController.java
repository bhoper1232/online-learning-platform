package com.bhoper.controller;

import com.bhoper.client.course.CourseClient;
import com.bhoper.client.enrollment.EnrollmentClient;
import com.bhoper.client.enrollment.EnrollmentCreateRequest;
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
        System.out.println(principal.getName());
        System.out.println(courseId);

        Boolean active = this.enrollmentClient.createEnrollment(new EnrollmentCreateRequest(
                principal.getName(),
                courseId,
                "active"));

        if (active) return "redirect:/home";

        throw new RuntimeException();
    }

}
