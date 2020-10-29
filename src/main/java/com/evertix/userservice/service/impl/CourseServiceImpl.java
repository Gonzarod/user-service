package com.evertix.userservice.service.impl;

import com.evertix.userservice.exception.ResourceNotFoundException;
import com.evertix.userservice.entities.Course;
import com.evertix.userservice.repository.CourseRepository;
import com.evertix.userservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses(String name) {
        //Course name is optional
        if (name==null){
            return this.courseRepository.findAll();
        }else{
            return this.courseRepository.findAllByNameContains(name);
        }
    }

    @Override
    public Page<Course> getAllCoursesPage(String name,Pageable pageable) {

        //Course name is optional
        if (name==null){
            return this.courseRepository.findAll(pageable);
        }else{
            return this.courseRepository.findAllByNameContains(name,pageable);
        }

    }

    @Override
    public Course getCourseById(Long courseId) {
        return this.courseRepository.findById(courseId)
                .orElseThrow(()->new ResourceNotFoundException("Course with id:"+courseId+" not found"));
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.findByNameEquals(courseName)
            .orElseThrow(()-> new ResourceNotFoundException("Course with name: "+courseName+" not found."));
    }

/*

    @Override
    public Course createCourse(Course course) { return courseRepository.save(course); }

    @Override
    public Course updateCourse(Long courseId, Course courseDetails) {
        return courseRepository.findById(courseId).map(course -> {
            course.setName(courseDetails.getName());
            course.setDescription(courseDetails.getDescription());
            return courseRepository.save(course);
        }).orElseThrow(()-> new ResourceNotFoundException("Course with Id: "+courseId+ " not found"));
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long courseId) {
        return courseRepository.findById(courseId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Course with Id: "+courseId+ " not found"));
    }
    */

}
