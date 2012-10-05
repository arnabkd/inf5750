package no.uio.inf5750.assignment2.gui.internalframe;

import javax.swing.JInternalFrame;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: InternalFrameManager.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public interface InternalFrameManager
{
    boolean INTERNAL_FRAME_RESIZABLE = false;

    boolean INTERNAL_FRAME_CLOSABLE = true;

    boolean INTERNAL_FRAME_MAXIMIZABLE = false;

    boolean INTERNAL_FRAME_ICONIFIABLE = true;

    String ID = InternalFrameManager.class.getName();

    JInternalFrame createCourseInternalFrame();

    JInternalFrame createCourseInternalFrame( int courseId );

    JInternalFrame createDegreeInternalFrame();

    JInternalFrame createDegreeInternalFrame( int degreeId );

    JInternalFrame createStudentInternalFrame();

    JInternalFrame createStudentInternalFrame( int studentId );
}
