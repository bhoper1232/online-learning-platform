package com.bhoper.repository;

import com.bhoper.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findEnrollmentsByUsername(String username);

}
