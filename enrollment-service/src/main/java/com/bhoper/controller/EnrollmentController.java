package com.bhoper.controller;

import com.bhoper.client.FeignRequestInterceptor;
import com.bhoper.dto.EnrollmentCreateRequest;
import com.bhoper.model.Enrollment;
import com.bhoper.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/create")
    public Boolean createEnrollment(@RequestBody EnrollmentCreateRequest enrollmentCreateRequest,
                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        System.out.println(token);
        FeignRequestInterceptor.token = token;
        return this.enrollmentService.createEnrollment(enrollmentCreateRequest);
    }

    @GetMapping("/enrolled-courses")
    public List<Enrollment> getEnrollmentsByUsername(@RequestParam("preferred_username") String username) {
        return this.enrollmentService.findEnrollmentsByUsername(username);
    }
}
