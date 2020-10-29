package com.evertix.userservice.controller;

import com.evertix.userservice.entities.WorkExperience;
import com.evertix.userservice.service.WorkExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "WorkExperience", description = "API")
@RestController
@RequestMapping("/api/workexp")
@CrossOrigin(origins = "*")
public class WorkExperienceController {


    @Autowired
    private WorkExperienceService workExperienceService;

    @GetMapping("/")
    @Operation(summary = "Get All WorkExperiences By User", description = "Get All WorkExperiences By User", tags = {"WorkExperience"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<WorkExperience> getAllWorkExperiences(@PageableDefault @Parameter(hidden = true) Pageable pageable){
        return workExperienceService.getAllWorkExperiences(pageable);
    }

    @GetMapping("/users/{userId}/")
    @Operation(summary = "Get All WorkExperiences By User", description = "Get All WorkExperiences By User", tags = {"WorkExperience"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<WorkExperience> getAllWorkExperiencesByUserId(@PathVariable(name = "userId") Long userId, @PageableDefault @Parameter(hidden = true) Pageable pageable){
        return workExperienceService.getAllWorkExperiencesByUserId(userId, pageable);
    }

}
