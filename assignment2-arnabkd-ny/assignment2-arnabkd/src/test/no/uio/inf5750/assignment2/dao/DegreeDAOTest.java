/**
 * 
 */
package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import no.uio.inf5750.assignment2.model.Degree;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author arnabkd
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/assignment2/beans.xml" })
@Transactional
public class DegreeDAOTest {

	@Autowired
	DegreeDAO degreeDAO;

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

	/**
	 * Test method for
	 * {@link no.uio.inf5750.assignment2.dao.DegreeDAO#saveDegree(no.uio.inf5750.assignment2.model.Degree)}
	 * .
	 */
	@Test
	public void testSaveDegree() {
		Degree degree = new Degree("Test degree");
		degreeDAO.saveDegree(degree);
		assertNotNull(degreeDAO.getDegree(degree.getId()));
	}


	@Test
	public void testGetDegree() {
		Degree degree = new Degree("Test degree");
		degreeDAO.saveDegree(degree);
		assertNotNull(degreeDAO.getDegree(degree.getId()));
	}

	/**
	 * Test method for
	 * {@link no.uio.inf5750.assignment2.dao.DegreeDAO#getDegreeByType(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetDegreeByType() {
		Degree degree = new Degree("Test degree");
		degreeDAO.saveDegree(degree);
		assertNotNull(degreeDAO.getDegreeByType("Test degree"));
	}

	/**
	 * Test method for
	 * {@link no.uio.inf5750.assignment2.dao.DegreeDAO#getAllDegrees()}.
	 */
	@Test
	public void testGetAllDegrees() {
		Collection<Degree> degrees = new ArrayList<Degree>();
		Degree degree = new Degree("Test degree");
		degrees.add(degree);
		Degree degree1 = new Degree("Test degree1");
		degrees.add(degree1);

		degreeDAO.saveDegree(degree);
		degreeDAO.saveDegree(degree1);

		assertTrue(degreeDAO.getAllDegrees().containsAll(degrees));
	}


	@Test
	public void testDelDegree() {
		Degree degree = new Degree("Test degree");
		degreeDAO.saveDegree(degree);
		degreeDAO.delDegree(degree);
		assertNull(degreeDAO.getDegree(degree.getId()));		
	}

}
