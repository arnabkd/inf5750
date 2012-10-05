package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import no.uio.inf5750.assignment2.model.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseDAOTest {
	@Autowired
	CourseDAO courseDAO;
	Course course;
	int courseId;
	
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Before
	public void setUp() throws Exception {
		course = new Course("INF1000", "lolwut");
		courseDAO.saveCourse(course);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCourseByCourseCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCourseByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelCourse() {
		fail("Not yet implemented");
	}

}
