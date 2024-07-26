package com.bhoper.controller;

import com.bhoper.dto.EnrollmentCreateRequest;
import com.bhoper.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/create")
    public Boolean createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest) {
        return this.enrollmentService.createEnrollment(enrollmentCreateRequest);
    }
}
