package com.evertix.userservice.config;

import com.evertix.userservice.entities.*;
import com.evertix.userservice.repository.CourseRepository;
import com.evertix.userservice.repository.RoleRepository;
import com.evertix.userservice.repository.UserRepository;
import com.evertix.userservice.repository.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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



        this.roleRepository.saveAll(List.of(new Role(ERole.ROLE_STUDENT),
                                            new Role(ERole.ROLE_TEACHER),
                                            new Role(ERole.ROLE_ADMIN)));

        List<Course> courseList = new ArrayList<Course>();

        //Course 1
        Course course01 = new Course("Matematicas", "Course that belongs to the science area");
        this.courseRepository.saveAll(List.of(course01));

        List<User> usersList = new ArrayList<User>();
        //User Student
        User user1 = new User("jesus.estudiante","123456789","jesus@gmail.com","Jesus",
                "Duran","77332215","994093796",LocalDate.now(), "Jr Monte Algarrobo");
        Optional<Role> roleStudent = this.roleRepository.findByName(ERole.ROLE_STUDENT);
        user1.setRoles(Set.of(roleStudent.get()));
        //User 2
        User user2 = new User("albert.profesor","123456789","albert@gmail.com","Albert",
                "Mayta","77332216","994093797",LocalDate.now(), "Jr Monte Cedro");
        Optional<Role> roleTeacher = this.roleRepository.findByName(ERole.ROLE_TEACHER);
        user2.setRoles(Set.of(roleTeacher.get()));
        // user2.setCourses(Set.of(courseRepository.findById(course01.getId())));
        //User 3
        User user3 = new User("jose.admin","123456789","jose@gmail.com","Jose",
                "Flores","77332217","994093798",LocalDate.now(), "Jr Monte Caoba");
        Optional<Role> roleAdmin = this.roleRepository.findByName(ERole.ROLE_ADMIN);
        user3.setRoles(Set.of(roleAdmin.get()));
        this.userRepository.saveAll(List.of(user1,user2,user3));

        List<WorkExperience> workExperienceList = new ArrayList<WorkExperience>();
        // WorkExperience
        WorkExperience workExperience01 = new WorkExperience(LocalDate.now(), LocalDate.parse("2020-08-23"), "Colegio María de la Encarnación");
        workExperience01.setUser(user2);
        this.workExperienceRepository.saveAll(List.of(workExperience01));
    }
}