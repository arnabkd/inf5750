package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import no.uio.inf5750.assignment2.model.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
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
	}

	@After
	public void tearDown() throws Exception {
		course = null;
	}

	@Test
	public void testSaveCourse() {
		courseDAO.saveCourse(course);
	}

	@Test
	public void testGetCourse() {
		assertTrue(true);
	}

	@Test
	public void testGetCourseByCourseCode() {
		assertTrue(true);
	}

	@Test
	public void testGetCourseByName() {
		assertTrue(true);
	}

	@Test
	public void testGetAllCourses() {
		assertTrue(true);
	}

	@Test
	public void testDelCourse() {
		assertTrue(true);
	}

}
