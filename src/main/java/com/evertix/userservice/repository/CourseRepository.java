package com.evertix.userservice.repository;

import com.evertix.userservice.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findById(Long aLong);
    Optional<Course> findByNameEquals(String name);

    Page<Course> findAllByNameContains(String name, Pageable pageable);
    List<Course> findAllByNameContains(String name);

    Page<Course> findAllByTeachersId(Long teacherId, Pageable pageable);
    List<Course> findAllByTeachersId(Long teacherId);


}
