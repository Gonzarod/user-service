package com.evertix.userservice.service;

import com.evertix.userservice.entities.WorkExperience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface WorkExperienceService {
    Page<WorkExperience> getAllWorkExperiences(Pageable pageable);
    Page<WorkExperience> getAllWorkExperiencesByUserId(Long userId, Pageable pageable);
    WorkExperience createWorkExperience(Long userId, WorkExperience workExperience);
    WorkExperience updateWorkExperience(Long userId, Long workExperienceId, WorkExperience workExperienceDetails);
    ResponseEntity<?> deleteWorkExperience(Long userId, Long workExperienceId);
}
