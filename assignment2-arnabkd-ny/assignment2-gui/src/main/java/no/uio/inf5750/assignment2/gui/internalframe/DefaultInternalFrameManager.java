package no.uio.inf5750.assignment2.gui.internalframe;

import javax.swing.JInternalFrame;

import no.uio.inf5750.assignment2.gui.dialog.DialogManager;
import no.uio.inf5750.assignment2.gui.list.ListChangeManager;
import no.uio.inf5750.assignment2.service.StudentSystem;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: DefaultInternalFrameManager.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public class DefaultInternalFrameManager
    implements InternalFrameManager
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private StudentSystem service;

    public void setService( StudentSystem service )
    {
        this.service = service;
    }

    private DialogManager dialogManager;

    public void setDialogManager( DialogManager dialogManager )
    {
        this.dialogManager = dialogManager;
    }

    private ListChangeManager courseListChangeManager;

    public void setCourseListChangeManager( ListChangeManager courseListChangeManager )
    {
        this.courseListChangeManager = courseListChangeManager;
    }

    private ListChangeManager degreeListChangeManager;

    public void setDegreeListChangeManager( ListChangeManager degreeListChangeManager )
    {
        this.degreeListChangeManager = degreeListChangeManager;
    }

    private ListChangeManager studentListChangeManager;

    public void setStudentListChangeManager( ListChangeManager studentListChangeManager )
    {
        this.studentListChangeManager = studentListChangeManager;
    }

    // -------------------------------------------------------------------------
    // InternalFrameManager implementation
    // -------------------------------------------------------------------------

    public JInternalFrame createCourseInternalFrame()
    {
        return new CourseInternalFrame( service, dialogManager, courseListChangeManager, studentListChangeManager,
            "New course", false );
    }

    public JInternalFrame createCourseInternalFrame( int courseId )
    {
        return new CourseInternalFrame( service, dialogManager, courseListChangeManager, studentListChangeManager,
            "Update course", true, courseId );
    }

    public JInternalFrame createDegreeInternalFrame()
    {
        return new DegreeInternalFrame( service, dialogManager, degreeListChangeManager, courseListChangeManager,
            "New degree", false );
    }

    public JInternalFrame createDegreeInternalFrame( int degreeId )
    {
        return new DegreeInternalFrame( service, dialogManager, degreeListChangeManager, courseListChangeManager,
            "Update degree", true, degreeId );
    }

    public JInternalFrame createStudentInternalFrame()
    {
        return new StudentInternalFrame( service, dialogManager, studentListChangeManager, courseListChangeManager,
            degreeListChangeManager, "New student", false );
    }

    public JInternalFrame createStudentInternalFrame( int studentId )
    {
        return new StudentInternalFrame( service, dialogManager, studentListChangeManager, courseListChangeManager,
            degreeListChangeManager, "Update student", true, studentId );
    }
}
