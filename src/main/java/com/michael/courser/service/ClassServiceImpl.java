package com.michael.courser.service;

import com.michael.courser.dao.ClassDao;
import com.michael.courser.model.Class;
import com.michael.courser.model.ClassTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final Logger _log = LoggerFactory.getLogger(ClassServiceImpl.class);

    @Autowired
    ClassDao classDao;

    @Override
    public Class createClass(Class newClass) {
        _log.trace("Enter...", newClass);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(newClass.getClassId());
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public Class getClassById(Integer id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(id);
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public Class updateClassById(Integer id, Class modifiedClass) {
        _log.trace("Enter...", id, modifiedClass);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(id);
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public Class deleteClassById(Integer id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(id);
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public Class registerClassById(Integer id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(id);
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public Class deregisterClassById(Integer id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Class dummyClass = new Class(id);
        _log.trace("Exit...");
        return dummyClass;
    }

    @Override
    public List<Class> getClassesByAttributes() {
        _log.trace("Enter...");
        List<Class> classList = new LinkedList<>();

        List<ClassTime> aClassTimeList = new LinkedList<>();
        aClassTimeList.add(new ClassTime(DayOfWeek.MONDAY, LocalTime.NOON, LocalTime.MIDNIGHT));
        Class aClass = new Class(1, 12345, "SUM2020", "G", "C",
                "David Joyner", null, aClassTimeList, 1000, 230, 50, 0);

        List<ClassTime> bClassTimeList = new LinkedList<>();
        bClassTimeList.add(new ClassTime(DayOfWeek.MONDAY, LocalTime.of(8, 45), LocalTime.NOON));
        Class bClass = new Class(2, 54321, "SUM2020", "U", "C",
                "Tucker Balch", null, bClassTimeList, 750, 730, 100, 0);

        classList.add(aClass);
        classList.add(bClass);
        _log.trace("Exit...");
        return classList;
    }
}
