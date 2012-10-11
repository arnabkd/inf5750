package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import no.uio.inf5750.assignment2.model.Student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/assignment2/beans.xml" })
@Transactional
public class StudentDAOTest {

	@Autowired
	StudentDAO studentDAO;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveStudent() {
		Student testStudent = new Student("Test Student");
		studentDAO.saveStudent(testStudent);
	}

	@Test
	public void testGetStudent() {
		Student testStudent = new Student("Test Student");
		studentDAO.saveStudent(testStudent);
		assertNotNull(studentDAO.getStudent(testStudent.getId()));
	}

	@Test
	public void testGetStudentByName() {
		Student testStudent = new Student("Test Student");
		studentDAO.saveStudent(testStudent);
		assertNotNull(studentDAO.getStudentByName("Test Student"));
	}

	@Test
	public void testGetAllStudents() {
		Student testStudent = new Student("Test Student");
		studentDAO.saveStudent(testStudent);
		assertTrue(studentDAO.getAllStudents().contains(testStudent));
	}

	@Test
	public void testDelStudent() {
		Student testStudent = new Student("Test Student");
		studentDAO.saveStudent(testStudent);
		studentDAO.delStudent(testStudent);
		assertNull(studentDAO.getStudentByName("Test Student"));
	}

}
