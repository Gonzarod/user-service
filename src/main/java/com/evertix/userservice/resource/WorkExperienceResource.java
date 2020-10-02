package com.evertix.userservice.resource;

import com.evertix.userservice.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WorkExperienceResource {
    private Long id;
    private LocalDate start_at;
    private LocalDate end_at;
    private String workplace;
    private User user;
}
