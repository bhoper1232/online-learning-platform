package com.bhoper.client.enrollment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "enrollment-service")
public interface EnrollmentClient {

    @PostMapping("/enrollments/create")
    Boolean createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest);

    @GetMapping("/enrollments/enrolled-courses")
    List<Enrollment> getEnrollmentsByUsername(@RequestParam("preferred_username") String username);
}
