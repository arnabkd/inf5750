/**
 * 
 */
package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

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

	Degree degree = new Degree("Test degree");
	
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
	 * Test method for {@link no.uio.inf5750.assignment2.dao.DegreeDAO#saveDegree(no.uio.inf5750.assignment2.model.Degree)}.
	 */
	@Test
	public void testSaveDegree() {
		degreeDAO.saveDegree(degree);
		assertNotNull(degreeDAO.getDegree(degree.getId()));
	}

	/**
	 * Test method for {@link no.uio.inf5750.assignment2.dao.DegreeDAO#getDegree(int)}.
	 */
	@Test
	public void testGetDegree() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link no.uio.inf5750.assignment2.dao.DegreeDAO#getDegreeByType(java.lang.String)}.
	 */
	@Test
	public void testGetDegreeByType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link no.uio.inf5750.assignment2.dao.DegreeDAO#getAllDegrees()}.
	 */
	@Test
	public void testGetAllDegrees() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link no.uio.inf5750.assignment2.dao.DegreeDAO#delDegree(no.uio.inf5750.assignment2.model.Degree)}.
	 */
	@Test
	public void testDelDegree() {
		fail("Not yet implemented");
	}

}
