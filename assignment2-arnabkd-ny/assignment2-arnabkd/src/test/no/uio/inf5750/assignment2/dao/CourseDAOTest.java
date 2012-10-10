package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Set;

import no.uio.inf5750.assignment2.model.Course;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/assignment2/beans.xml" })
@Transactional
public class CourseDAOTest {
	@Autowired
	CourseDAO courseDAO;
	Course course = new Course("INF1000", "INF1000-navn");
	int courseId;

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSaveCourse() {
		//Course testCourse = courseDAO.getCourse(course.getId());
		//assertNotNull(testCourse);
		courseDAO.saveCourse(course);
		assertNotNull(courseDAO.getCourse(course.getId()));
	}

	@Test
	public void testGetCourse() {
		Course test = new Course("11", "11");
		courseDAO.saveCourse(test);
		assertNotNull(courseDAO.getCourse(test.getId()));
	}

	@Test
	public void testGetCourseByCourseCode() {
		Course test = new Course("Test1000", "Testcourse");
		courseDAO.saveCourse(test);
		assertNotNull(courseDAO.getCourseByCourseCode("Test1000"));
	}

	@Test
	public void testGetCourseByName() {
		Course test = new Course("Test1000", "Testcourse");
		courseDAO.saveCourse(test);
		assertNotNull(courseDAO.getCourseByName("Testcourse"));
	}

	@Test
	public void testGetAllCourses() {
		Course test1 = new Course("Test1001", "Testcourse1");
		Course test2 = new Course("Test1002", "Testcourse2");
		Course test3 = new Course("Test1003", "Testcourse3");
		courseDAO.saveCourse(test1);
		courseDAO.saveCourse(test2);
		courseDAO.saveCourse(test3);
		
		assertTrue(courseDAO.getAllCourses().contains(test1));		
		assertTrue(courseDAO.getAllCourses().contains(test2));	
		assertTrue(courseDAO.getAllCourses().contains(test3));	
	}

	@Test
	public void testDelCourse() {
		Course test = new Course("Test1000", "Testcourse");
		courseDAO.saveCourse(test);
		courseDAO.delCourse(test);
		assertFalse(courseDAO.getAllCourses().contains(test));
	}

}
