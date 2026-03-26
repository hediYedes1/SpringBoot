package tn.esprit._4ds11.championnat.championnat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit._4ds11.championnat.championnat.entities.Course;
import tn.esprit._4ds11.championnat.championnat.services.ICourseService;

@Tag(name = "Gestion Course")
@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
public class courseController {

    private final ICourseService courseService;

    @Operation(description = "ajouter une course")
    @PostMapping("/add-course")
    @ResponseBody
    public Course ajouterCourse(@RequestBody Course course) {
        return courseService.ajouterCourse(course);
    }

    @Operation(description = "ajouter course avec positions associees")
    @PostMapping("/addCourseEtPositionAssocie")
    @ResponseBody
    public Course addCourseEtPositionAssocie(@RequestBody Course c) {
        return courseService.addCourseEtPositionAssocie(c);
    }
}
