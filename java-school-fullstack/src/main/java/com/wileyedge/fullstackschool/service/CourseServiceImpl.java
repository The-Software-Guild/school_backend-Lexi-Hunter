package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.CourseDao;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

    	return courseDao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE

    	Course course = courseDao.findCourseById(id);
        if(course == null) {
            course = new Course();
            course.setCourseName("Course Not Found");
            course.setCourseDesc("Course Not Found");
        }
        return course;

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

    	boolean isCourseNameBlank = course.getCourseName().isBlank();
        boolean isCourseDescBlank = course.getCourseDesc().isBlank();

        if(isCourseNameBlank) {
            course.setCourseName("Name blank, course NOT added");
        }

        if(isCourseDescBlank) {
            course.setCourseDesc("Description blank, course NOT added");
        }

        if (!isCourseNameBlank && !isCourseDescBlank) {
            return courseDao.createNewCourse(course);
        }

        return course;

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

    	if(course.getCourseId() != id){
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
            return course;
        }
        courseDao.updateCourse(course);
        return course;

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

    	courseDao.deleteCourse(id);
        System.out.println("Course ID: " + id + " deleted");

        //YOUR CODE ENDS HERE
    }
}
