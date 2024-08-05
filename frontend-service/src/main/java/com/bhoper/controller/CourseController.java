package com.bhoper.controller;

import com.bhoper.client.course.Course;
import com.bhoper.client.course.CourseClient;
import com.bhoper.client.course.CourseCreateRequest;
import com.bhoper.client.enrollment.Enrollment;
import com.bhoper.client.enrollment.EnrollmentClient;
import com.bhoper.client.enrollment.EnrollmentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


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
    public String enrollCourse(@RequestParam("courseId") String courseId, Principal principal,
                               RedirectAttributes redirectAttributes) {
        System.out.println(principal.getName());
        System.out.println(courseId);

        Boolean active = this.enrollmentClient.createEnrollment(new EnrollmentCreateRequest(
                principal.getName(),
                courseId,
                "active"));

        if (active) {
            redirectAttributes.addFlashAttribute("successMessage", "You have successfully enrolled in the course!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "You have already enrolled in the course!");
        }
        return "redirect:/home";
    }

    @GetMapping("/user-info")
    public String getUserInfoPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("username", principal.getAttribute("preferred_username"));
        model.addAttribute("firstName", principal.getAttribute("given_name"));
        model.addAttribute("lastName", principal.getAttribute("family_name"));
        model.addAttribute("email", principal.getAttribute("email"));

        return "user-info";
    }

    @GetMapping("/course/create")
    public String getCourseCreatePage(Model model) {
        model.addAttribute("payload", new Course());
        return "create-new-course";
    }

    @PostMapping("/course/create")
    public String createCourse(@ModelAttribute("payload") CourseCreateRequest courseCreateRequest) {
        this.courseClient.createCourse(courseCreateRequest);
        return "redirect:/home";
    }

    @GetMapping("/course/details/{courseId}")
    public String getCourseDetails(@PathVariable("courseId") String id, Model model) {
        Course course = this.courseClient.getCourseById(id);
        model.addAttribute("course", course);
        return "course-details";
    }

    @GetMapping("/enrolled-courses")
    public String getUserEnrolledCoursesPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        List<Enrollment> enrollmentList = this.enrollmentClient.getEnrollmentsByUsername(principal.getAttribute("preferred_username"));
        List<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            courses.add(this.courseClient.getCourseById(enrollment.getCourseId()));
        }
        model.addAttribute("enrolledCourses", courses);
        return "enrolled-courses";
    }
}
