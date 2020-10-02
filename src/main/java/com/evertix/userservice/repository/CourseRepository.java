package com.evertix.userservice.repository;

import com.evertix.userservice.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);
    Page<Course> findAllByTeachersId(Long teacherId, Pageable pageable);
}
