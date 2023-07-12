package com.coursehub.courseservice.service;

import com.coursehub.courseservice.exception.CourseNotFound;
import com.coursehub.courseservice.model.Course;
import com.coursehub.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourse(){
       List<Course>  courseList =courseRepository.findAll();
        return courseList;
    }

    public Course getCourseById(Long id){
       Optional<Course> course= courseRepository.findById(id);
       return course.get();
    }

    public Course addCourse(Course course){
       return courseRepository.save(course);
    }

    public Course  updateCourse(Course course, Long id){
        return courseRepository.findById(id).map(coursee -> {
            coursee.setCourseName(course.getCourseName());
            coursee.setCourseDescription(course.getCourseDescription());
            coursee.setCourseFee(course.getCourseFee());
            return courseRepository.save(coursee);
        }).orElseGet(() -> {
            course.setId(id);
            return courseRepository.save(course);
        });
    }
    public void deleteCourse(Course course){
        courseRepository.delete(course);
    }


}
