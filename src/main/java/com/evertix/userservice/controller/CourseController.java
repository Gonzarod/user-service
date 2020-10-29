package com.evertix.userservice.controller;

import com.evertix.userservice.entities.Course;
import com.evertix.userservice.resource.CourseResource;
import com.evertix.userservice.resource.CourseSaveResource;
import com.evertix.userservice.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Course", description = "API")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    @Operation(summary = "Get All Courses", description = "Get All Courses", tags = {"Course"},
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
    public Page<CourseResource> getAllCourses(@PageableDefault @Parameter(hidden = true) Pageable pageable){
        List<CourseResource> courses = courseService.getAllCourses(pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int coursesCount = courses.size();
        return new PageImpl<>(courses,pageable,coursesCount);
    }

    private Course convertToEntity(CourseSaveResource resource){return mapper.map(resource, Course.class);}
    private CourseResource convertToResource(Course entity){return mapper.map(entity, CourseResource.class);}
}
