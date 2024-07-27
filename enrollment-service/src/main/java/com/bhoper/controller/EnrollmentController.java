package com.bhoper.controller;

import com.bhoper.client.FeignRequestInterceptor;
import com.bhoper.dto.EnrollmentCreateRequest;
import com.bhoper.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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
}
