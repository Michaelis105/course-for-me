package com.michael.courser.service;

import com.michael.courser.model.Class;
import com.michael.courser.model.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final Logger _log = LoggerFactory.getLogger(StudentServiceImpl.class);

    //@Autowired
    //StudentDao studentDao;

    @Override
    public Schedule getStudentSchedule(Integer scheduleId) {
        _log.trace("Enter...", scheduleId);
        // TODO: IMPLEMENT
        Schedule dummySchedule = null;
        _log.trace("Exit...");
        return dummySchedule;
    }

    @Override
    public Class registerClassById(Integer courseId, Integer scheduleId) {
        _log.trace("Enter...", courseId, scheduleId);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(courseId);
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public Class deregisterClassById(Integer courseId, Integer scheduleId) {
        _log.trace("Enter...", courseId, scheduleId);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(courseId);
        _log.trace("Exit...");
        return dummyClass;
    }
}
