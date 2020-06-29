package com.michael.courser.service;

import com.michael.courser.dao.StudentDao;
import com.michael.courser.model.Class;

import com.michael.courser.model.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final Logger _log = LoggerFactory.getLogger(StudentServiceImpl.class);

    //@Autowired
    //StudentDao studentDao;

    @Override
    public Schedule getStudentSchedule(String scheduleId) {
        _log.trace("Enter...", scheduleId);
        // TODO: IMPLEMENT
        Schedule dummySchedule = new Schedule();
        _log.trace("Exit...");
        return dummySchedule;
    }

    @Override
    public Class registerClassById(String courseId, String scheduleId) {
        _log.trace("Enter...", courseId, scheduleId);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(courseId);
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public Class deregisterClassById(String courseId, String scheduleId) {
        _log.trace("Enter...", courseId, scheduleId);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(courseId);
        _log.trace("Exit...");
        return dummyClass;
    }
}
