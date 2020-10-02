package com.evertix.userservice.resource;

import com.evertix.userservice.entities.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserResource {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private LocalDate birthday;
    private String address;
    private int totalStar;
    private Boolean active;
    private String linkedin;
}
