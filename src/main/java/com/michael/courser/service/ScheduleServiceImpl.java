package com.michael.courser.service;

import com.michael.courser.model.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final Logger _log = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Override
    public Schedule createSchedule(Schedule newCourse) {
        _log.trace("Enter...", newCourse);
        // TODO: IMPLEMENT
        Schedule dummySchedule = new Schedule();
        _log.trace("Exit...");
        return dummySchedule;
    }

    @Override
    public Schedule getScheduleById(String id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Schedule dummySchedule = new Schedule();
        _log.trace("Exit...");
        return dummySchedule;
    }

    @Override
    public Schedule updateScheduleById(String id, Schedule modifiedSchedule) {
        _log.trace("Enter...", id, modifiedSchedule);
        // TODO: IMPLEMENT
        Schedule dummySchedule = new Schedule();
        _log.trace("Exit...");
        return dummySchedule;
    }

    @Override
    public Schedule deleteScheduleById(String id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Schedule dummySchedule = new Schedule();
        _log.trace("Exit...");
        return dummySchedule;
    }
}
