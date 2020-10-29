package com.evertix.userservice.config;


import com.evertix.userservice.entities.*;
import com.evertix.userservice.exception.ResourceNotFoundException;
import com.evertix.userservice.repository.CourseRepository;
import com.evertix.userservice.repository.RoleRepository;
import com.evertix.userservice.repository.UserRepository;
import com.evertix.userservice.repository.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataLoader {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private WorkExperienceRepository workExperienceRepository;
    private CourseRepository courseRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, WorkExperienceRepository workExperienceRepository,
                      CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.courseRepository = courseRepository;
        LoadData();
    }

    private void LoadData() {
        addRoles();
        addCourses();
        registerUserStudent();
        registerUserTeacher();
    }

    void addCourses(){
        List<Course> courseList = new ArrayList<>();

        //Course 1
        Course course1 = new Course("Spanish", "Spanish");
        Course course2 = new Course("History", "History");
        Course course3 = new Course("Arithmetics", "Arithmetics");
        Course course4 = new Course("Geometry", "Geometry");
        Course course5 = new Course("Geography", "Geography");
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        courseList.add(course5);
        this.courseRepository.saveAll(courseList);


    }

    void addRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ERole.ROLE_STUDENT));
        roles.add(new Role(ERole.ROLE_TEACHER));
        roles.add(new Role(ERole.ROLE_ADMIN));

        this.roleRepository.saveAll(roles);
    }

    void registerUserStudent(){
        //User Student
        User user1 = new User("jesus.student","jesus@gmail.com","Jesus",
                "Duran","77332215","994093796",LocalDate.now(), "Jr Monte Algarrobo");
        Optional<Role> roleStudent = this.roleRepository.findByName(ERole.ROLE_STUDENT);
        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleStudent.get());
        user1.setRoles(roles1);
        userRepository.save(user1);

    }

    void registerUserTeacher(){
        //User teacher
        User user2 = new User("albert.teacher","albert@gmail.com","Albert",
                "Mayta","77332216","994093797",LocalDate.now(), "Jr Monte Cedro");

        Optional<Role> roleTeacher = this.roleRepository.findByName(ERole.ROLE_TEACHER);
        Set<Role> roles2 = new HashSet<Role>();
        roles2.add(roleTeacher.get());
        user2.setRoles(roles2);

        userRepository.save(user2);

        User userteach = userRepository.findByUsername("albert.teacher").orElseThrow(()->new ResourceNotFoundException("Not found"));
        Course course1 = this.courseRepository.findByNameEquals("Spanish").orElseThrow(()->new ResourceNotFoundException("Not found"));
        Course course2 = this.courseRepository.findByNameEquals("History").orElseThrow(()->new ResourceNotFoundException("Not found"));
        List<Course> courses =  new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        userteach.setCourses(courses);
        userRepository.save(userteach);

        // WorkExperience
        WorkExperience workExperience01 = new WorkExperience(LocalDate.now(), LocalDate.parse("2020-08-23"), "Colegio María de la Encarnación");
        workExperience01.setUser(user2);
        List<WorkExperience> workExperiences = new ArrayList<>();
        workExperiences.add(workExperience01);
        this.workExperienceRepository.saveAll(workExperiences);

    }
}
