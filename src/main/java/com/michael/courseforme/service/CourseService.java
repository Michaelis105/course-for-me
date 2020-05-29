package com.michael.courseforme.service;

import com.michael.courseforme.model.Course;

public interface CourseService {

    String createCourse(Course newCourse);
    Course getCourseById(String id);
    void updateCourseById(String id, Course modifiedCourse);
    void deleteCourseById(String id);

}
