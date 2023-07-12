package com.coursehub.courseservice.controller;

import com.coursehub.courseservice.model.Course;
import com.coursehub.courseservice.response.MessageResponse;
import com.coursehub.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/all-course")
    public ResponseEntity< List<Course>> getAllCourse(){
       List<Course> courseList= courseService.getAllCourse();
       return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try{
            Course course = courseService.getCourseById(id);
            map.put("message", 1);
            map.put("data", course);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception ex){
            map.clear();
            map.put("status", 0);
            map.put("message", "Course not found ");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        /*Course course= courseService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);*/
    }

    @PostMapping("/add-course")
    public  ResponseEntity<Course>   addCourse(@RequestBody Course course){
      Course course1= courseService.addCourse(course);
        return new ResponseEntity<>(course1, HttpStatus.CREATED);
    }

    @PutMapping("/update-course/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody Course course, @PathVariable Long id){
        Course course1= courseService.updateCourse(course, id);
        return new ResponseEntity<>(course1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try{
            Course course = courseService.getCourseById(courseId);
            courseService.deleteCourse(course);
            map.put("status", 1);
            map.put("message", "Course is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception ex){
            map.clear();
            map.put("status", 0);
            map.put("message", "Course is not found ");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }



}
