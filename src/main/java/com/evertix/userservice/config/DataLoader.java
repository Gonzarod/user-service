package com.evertix.userservice.config;


import com.evertix.userservice.entities.*;
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

        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role(ERole.ROLE_STUDENT));
        roles.add(new Role(ERole.ROLE_TEACHER));
        roles.add(new Role(ERole.ROLE_ADMIN));

        this.roleRepository.saveAll(roles);

        List<Course> courseList = new ArrayList<Course>();

        //Course 1
        Course course01 = new Course("Matematicas", "Course that belongs to the science area");
        courseList.add(course01);
        this.courseRepository.saveAll(courseList);

        List<User> usersList = new ArrayList<User>();
        //User Student
        User user1 = new User("jesus.estudiante","123456789","jesus@gmail.com","Jesus",
                "Duran","77332215","994093796",LocalDate.now(), "Jr Monte Algarrobo");
        Optional<Role> roleStudent = this.roleRepository.findByName(ERole.ROLE_STUDENT);
        Set<Role> roles1 = new HashSet<Role>();
        roles1.add(roleStudent.get());
        user1.setRoles(roles1);
        //User 2
        User user2 = new User("albert.profesor","123456789","albert@gmail.com","Albert",
                "Mayta","77332216","994093797",LocalDate.now(), "Jr Monte Cedro");
        Optional<Role> roleTeacher = this.roleRepository.findByName(ERole.ROLE_TEACHER);
        Set<Role> roles2 = new HashSet<Role>();
        roles2.add(roleTeacher.get());
        user2.setRoles(roles2);
        // user2.setCourses(Set.of(courseRepository.findById(course01.getId())));
        //User 3
        User user3 = new User("jose.admin","123456789","jose@gmail.com","Jose",
                "Flores","77332217","994093798",LocalDate.now(), "Jr Monte Caoba");
        Optional<Role> roleAdmin = this.roleRepository.findByName(ERole.ROLE_ADMIN);
        Set<Role> roles3 = new HashSet<Role>();
        roles3.add(roleAdmin.get());
        user3.setRoles(roles3);

        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);

        this.userRepository.saveAll(usersList);

        List<WorkExperience> workExperienceList = new ArrayList<WorkExperience>();
        // WorkExperience
        WorkExperience workExperience01 = new WorkExperience(LocalDate.now(), LocalDate.parse("2020-08-23"), "Colegio María de la Encarnación");
        workExperience01.setUser(user2);
        List<WorkExperience> workExperiences = new ArrayList<WorkExperience>();
        workExperiences.add(workExperience01);
        this.workExperienceRepository.saveAll(workExperiences);
    }
}