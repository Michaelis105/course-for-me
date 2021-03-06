package com.michael.courser.service;

import com.michael.courser.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Course newCourse);
    Course getCourseById(Integer id);
    List<Course> getCoursesByAttributes(String subject, String number, String title, String minCredit, String maxCredit);
    List<Course> getAllCourses();
    Course updateCourseById(Integer id, Course modifiedCourse);
    Course deleteCourseById(Integer id);

}
