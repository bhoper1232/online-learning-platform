package com.bhoper.repository;

import com.bhoper.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findEnrollmentsByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
            "FROM Enrollment e " +
            "WHERE e.username = :username AND e.courseId = :courseId")
    Boolean existsEnrollmentByUsernameAndCourseId(@Param("username") String username,
                                                  @Param("courseId") String courseId);

}
