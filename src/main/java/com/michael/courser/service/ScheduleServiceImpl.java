package com.michael.courser.service;

import com.michael.courser.dao.*;
import com.michael.courser.model.*;
import com.michael.courser.model.Class;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final Logger _log = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    ClassDao classDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    PersonDao personDao;

    @Override
    public Schedule createSchedule(Schedule newCourse) {
        _log.trace("Enter...", newCourse);
        Schedule schedule = null;
        _log.trace("Exit...");
        return schedule;
    }

    private boolean satisfiesRules(Course c, List<Rule> rules) {
        for (Rule r : rules) {
            if (!r.doesCourseSatisfyRule(c)) return false;
        }
        return true;
    }

    private boolean satisfiesRules(Class c, List<Rule> rules) {
        for (Rule r : rules) {
            if (!r.doesClassSatisfyRule(c)) return false;
        }
        return true;
    }

    @Override
    public Schedule createScheduleByRules(Integer degreeId, Integer minCredit, Integer maxCredit, List<Rule> rules) {
        _log.trace("Enter...", degreeId);

        DegreeRequirements dr = scheduleDao.getCoursesByDegreeRequirements(degreeId);

        List<Course> courseList = new LinkedList<>();
        for (int i : dr.getCourseRequirements()) {
             courseList.add(courseDao.getCourseById(i));
        }

        List<Grade> takenCourses = studentDao.getGradesByStudentId(1);

        Set<Integer> takenCourseIds = new HashSet<>();
        for (Grade g : takenCourses) {
            takenCourseIds.add(g.getCourseId());
        }

        // Filter out courses not applicable or eligible for scheduling.
        Iterator<Course> itrc = courseList.iterator();
        while (itrc.hasNext()) {

            Course c = itrc.next();

            // Filter out courses already taken.
            if (takenCourseIds.contains(c.getCourseId())) {
                itrc.remove();
                continue;
            }

            // Filter eligible courses per prerequisites.
            // If some degree required course A has prereq for some course B not yet credited, then remove course A.
            Set<Integer> preReqsLookup = new HashSet<>();
            if (c.getPrerequisiteIds() != null) {
                preReqsLookup.addAll(c.getPrerequisiteIds());
                if (!takenCourseIds.containsAll(preReqsLookup)) {
                    itrc.remove();
                    continue;
                }
            }

            if (!satisfiesRules(c, rules)) {
                itrc.remove();
                continue;
            }
        }

        List<Class> classesCart = new LinkedList<>();
        itrc = courseList.iterator();
        int totalCredit = 0;
        while (itrc.hasNext() && totalCredit < maxCredit) {
            Course c = itrc.next();
            if (totalCredit + c.getCreditCount() > maxCredit) {
                continue;
            }
            List<Class> classList = classDao.getClassesByCourseNumber(String.valueOf(c.getNumber()));
            for (Class cl : classList) {
                if (satisfiesRules(cl, rules)) {
                    classesCart.add(cl);
                    totalCredit += c.getCreditCount();
                    cl.getAttributes().put("subject", String.valueOf(c.getSubject()));
                    cl.getAttributes().put("number", String.valueOf(c.getNumber()));
                    cl.getAttributes().put("courseTitle", String.valueOf(c.getCourseTitle()));
                    Person instru = personDao.getPersonById(Integer.valueOf(cl.getInstructor()));
                    cl.getAttributes().put("instructor", instru.getFirstName() + " " + instru.getLastName());
                    cl.getAttributes().put("creditHours", String.valueOf(c.getCreditCount()));
                    break;
                }
            }
        }

        Schedule schedule = new Schedule(-1, -1, courseList, classesCart);

        _log.trace("Exit...");
        return schedule;
    }

    @Override
    public Schedule getScheduleById(Integer id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Schedule dummySchedule = null;
        _log.trace("Exit...");
        return dummySchedule;
    }

    @Override
    public Schedule updateScheduleById(Integer id, Schedule modifiedSchedule) {
        _log.trace("Enter...", id, modifiedSchedule);
        // TODO: IMPLEMENT
        Schedule dummySchedule = null;
        _log.trace("Exit...");
        return dummySchedule;
    }

    @Override
    public Schedule deleteScheduleById(Integer id) {
        _log.trace("Enter...", id);
        // TODO: IMPLEMENT
        Schedule dummySchedule = null;
        _log.trace("Exit...");
        return dummySchedule;
    }
}
