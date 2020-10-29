package com.evertix.userservice.service;

import com.evertix.userservice.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses(String name);
    Page<Course> getAllCoursesPage(String name,Pageable pageable);

    Course getCourseById(Long courseId);

    Course getCourseByName(String courseName);

/*
    Course createCourse(Course course);
    Course updateCourse(Long courseId, Course courseDetails);
    ResponseEntity<?> deleteCourse(Long courseId);

 */


}
