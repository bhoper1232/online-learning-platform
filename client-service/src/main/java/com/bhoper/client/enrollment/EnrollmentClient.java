package com.bhoper.client.enrollment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "enrollment-service")
public interface EnrollmentClient {

    @PostMapping("/enrollments/create")
    Boolean createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest);

}
