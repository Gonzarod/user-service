package com.evertix.userservice.controller;

import com.evertix.userservice.entities.Course;
import com.evertix.userservice.service.CourseService;
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

import java.util.List;

@Tag(name = "Course", description = "API")
@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    @Operation(summary = "Get All Courses List. Can filter by name", description = "Get All Courses List. Can filter by name", tags = {"Course"})
    public List<Course> getAllCourses(@RequestParam(required = false) String name) {
        return courseService.getAllCourses(name);
    }

    @GetMapping("/page")
    @Operation(summary = "Get All Courses Page. Can filter by name", description = "Get All Courses Page. Can filter by name", tags = {"Course"},
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
    public Page<Course> getAllCoursesPage(@RequestParam(required = false) String name,@PageableDefault @Parameter(hidden = true) Pageable pageable) {
        return courseService.getAllCoursesPage(name,pageable);
    }

    @GetMapping("/{courseId}")
    @Operation(summary = "Get All Course By Id ", description = "Get All Course By Id", tags = {"Course"})
    public Course getAllCoursesById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get Course By Exact Name ", description = "Get Course By Exact Name", tags = {"Course"})
    public Course getCourseByName(@PathVariable String name) {
        return courseService.getCourseByName(name);
    }

}






