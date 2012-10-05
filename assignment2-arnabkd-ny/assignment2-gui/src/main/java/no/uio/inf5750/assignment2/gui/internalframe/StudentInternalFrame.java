package no.uio.inf5750.assignment2.gui.internalframe;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import no.uio.inf5750.assignment2.gui.dialog.DialogManager;
import no.uio.inf5750.assignment2.gui.dialog.SelectDialog;
import no.uio.inf5750.assignment2.gui.list.CellRenderer;
import no.uio.inf5750.assignment2.gui.list.ListChangeListener;
import no.uio.inf5750.assignment2.gui.list.ListChangeManager;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;
import no.uio.inf5750.assignment2.util.comparator.CourseCodeComparator;
import no.uio.inf5750.assignment2.util.comparator.DegreeTypeComparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: StudentInternalFrame.java 28 2007-08-23 11:06:31Z torgeilo $
 */
public class StudentInternalFrame
    extends JInternalFrame
{
    private static final Log LOG = LogFactory.getLog( StudentInternalFrame.class );

    // -------------------------------------------------------------------------
    // Action commands
    // -------------------------------------------------------------------------

    private static final String BUTTON_SAVE = "Save";

    private static final String BUTTON_CLOSE = "Close";

    private static final String BUTTON_DELETE_AND_CLOSE = "Delete";

    private static final String BUTTON_ADD_COURSE = "Add course";

    private static final String BUTTON_DEL_COURSE = "Remove course";

    private static final String BUTTON_ADD_DEGREE = "Add degree";

    private static final String BUTTON_DEL_DEGREE = "Remove degree";

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private StudentSystem service;

    private DialogManager dialogManager;

    private ListChangeManager studentListChangeManager;

    private ListChangeManager courseListChangeManager;

    private ListChangeManager degreeListChangeManager;

    // -------------------------------------------------------------------------
    // Private members
    // -------------------------------------------------------------------------

    private JTextField fieldName = new JTextField( 20 );

    private CoursesListModel coursesListModel;

    private JList coursesList;

    private DegreesListModel degreesListModel;

    private JList degreesList;

    private int studentId;

    private boolean stored;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public StudentInternalFrame( StudentSystem service, DialogManager dialogManager,
        ListChangeManager studentListChangeManager, ListChangeManager courseListChangeManager,
        ListChangeManager degreeListChangeManager, String title, boolean stored )
    {
        this( service, dialogManager, studentListChangeManager, courseListChangeManager, degreeListChangeManager,
            title, stored, 0 );
    }

    public StudentInternalFrame( StudentSystem service, DialogManager dialogManager,
        ListChangeManager studentListChangeManager, ListChangeManager courseListChangeManager,
        ListChangeManager degreeListChangeManager, String title, boolean stored, int studentId )
    {
        super( "Student", InternalFrameManager.INTERNAL_FRAME_RESIZABLE, InternalFrameManager.INTERNAL_FRAME_CLOSABLE,
            InternalFrameManager.INTERNAL_FRAME_MAXIMIZABLE, InternalFrameManager.INTERNAL_FRAME_ICONIFIABLE );

        this.service = service;
        this.dialogManager = dialogManager;
        this.studentListChangeManager = studentListChangeManager;
        this.courseListChangeManager = courseListChangeManager;
        this.degreeListChangeManager = degreeListChangeManager;
        this.studentId = studentId;
        this.stored = stored;

        setTitle( title );

        createGUI();

        if ( stored )
        {
            getStudent( studentId );
            coursesListModel.refresh();
            degreesListModel.refresh();
        }
    }

    // -------------------------------------------------------------------------
    // Private methods
    // -------------------------------------------------------------------------

    private void createGUI()
    {
        JLabel labelName = new JLabel( "Name" );
        JLabel labelCourses = new JLabel( "Courses" );
        JLabel labelDegrees = new JLabel( "Degrees" );

        coursesListModel = new CoursesListModel();
        courseListChangeManager.addListChangeListener( coursesListModel );

        coursesList = new JList( coursesListModel );
        coursesList.setCellRenderer( new CellRenderer() );
        coursesList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        JScrollPane coursesPane = new JScrollPane( coursesList );
        coursesPane.setPreferredSize( new Dimension( 200, 150 ) );

        degreesListModel = new DegreesListModel();
        degreeListChangeManager.addListChangeListener( degreesListModel );

        degreesList = new JList( degreesListModel );
        degreesList.setCellRenderer( new CellRenderer() );
        degreesList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        JScrollPane degreesPane = new JScrollPane( degreesList );
        degreesPane.setPreferredSize( new Dimension( 200, 150 ) );

        JButton addCourse = new JButton( "Add" );
        addCourse.setActionCommand( BUTTON_ADD_COURSE );
        addCourse.addActionListener( new ButtonListener() );

        JButton delCourse = new JButton( "Remove" );
        delCourse.setActionCommand( BUTTON_DEL_COURSE );
        delCourse.addActionListener( new ButtonListener() );

        JButton addDegree = new JButton( "Add" );
        addDegree.setActionCommand( BUTTON_ADD_DEGREE );
        addDegree.addActionListener( new ButtonListener() );

        JButton delDegree = new JButton( "Remove" );
        delDegree.setActionCommand( BUTTON_DEL_DEGREE );
        delDegree.addActionListener( new ButtonListener() );

        JButton save = new JButton( BUTTON_SAVE );
        save.addActionListener( new ButtonListener() );

        JButton close = new JButton( BUTTON_CLOSE );
        close.addActionListener( new ButtonListener() );

        JButton deleteAndClose = new JButton( BUTTON_DELETE_AND_CLOSE );
        deleteAndClose.addActionListener( new ButtonListener() );

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        JPanel panel = new JPanel( layout );
        panel.setBorder( BorderFactory.createEmptyBorder( 2, 2, 2, 2 ) );

        con.fill = GridBagConstraints.HORIZONTAL;
        con.insets = new Insets( 2, 2, 2, 2 );
        con.anchor = GridBagConstraints.NORTHWEST;

        con.gridx = 0;
        con.gridy = 0;
        layout.setConstraints( labelName, con );
        panel.add( labelName );

        con.gridx = 1;
        con.gridy = 0;
        layout.setConstraints( fieldName, con );
        panel.add( fieldName );

        con.gridx = 0;
        con.gridy = 1;
        layout.setConstraints( labelCourses, con );
        panel.add( labelCourses );

        con.gridx = 1;
        con.gridy = 1;
        con.gridheight = 2;
        layout.setConstraints( coursesPane, con );
        panel.add( coursesPane );
        con.gridheight = 1;

        con.gridx = 2;
        con.gridy = 1;
        layout.setConstraints( addCourse, con );
        panel.add( addCourse );

        con.gridx = 2;
        con.gridy = 2;
        layout.setConstraints( delCourse, con );
        panel.add( delCourse );

        con.gridx = 0;
        con.gridy = 3;
        layout.setConstraints( labelDegrees, con );
        panel.add( labelDegrees );

        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 2;
        layout.setConstraints( degreesPane, con );
        panel.add( degreesPane );
        con.gridheight = 1;

        con.gridx = 2;
        con.gridy = 3;
        layout.setConstraints( addDegree, con );
        panel.add( addDegree );

        con.gridx = 2;
        con.gridy = 4;
        layout.setConstraints( delDegree, con );
        panel.add( delDegree );

        con.gridx = 0;
        con.gridy = 5;
        layout.setConstraints( deleteAndClose, con );
        panel.add( deleteAndClose );

        con.gridx = 1;
        con.gridy = 5;
        layout.setConstraints( save, con );
        panel.add( save );

        con.gridx = 2;
        con.gridy = 5;
        layout.setConstraints( close, con );
        panel.add( close );

        getContentPane().add( panel );
        pack();
    }

    private void getStudent( int studentId )
    {
        Student student = null;

        try
        {
            student = service.getStudent( studentId );
        }
        catch ( RuntimeException e )
        {
            LOG.error( "Failed to get student", e );

            dialogManager.displayErrorMessage( "Failed to get student: " + e.getMessage() );

            return;
        }

        if ( student == null )
        {
            dialogManager.displayErrorMessage( "Student with id " + studentId + " doesn't exist" );

            return;
        }

        fieldName.setText( student.getName() );
    }

    private void save()
    {
        String name = fieldName.getText();

        if ( name.trim().equals( "" ) )
        {
            dialogManager.displayErrorMessage( "Please specify a student name" );

            return;
        }

        Student student = service.getStudentByName( name );

        if ( student != null && (!stored || stored && student.getId() != studentId) )
        {
            dialogManager.displayErrorMessage( "The student name is not unique" );

            return;
        }

        if ( !stored )
        {
            studentId = service.addStudent( name );
            stored = true;
        }
        else
        {
            service.updateStudent( studentId, name );

            studentListChangeManager.fireListChanged();
        }
    }

    private void deleteAndClose()
    {
        if ( stored )
        {
            service.delStudent( studentId );
            stored = false;
        }

        studentListChangeManager.fireListChanged();

        close();
    }

    private void addCourse()
    {
        if ( !stored )
        {
            dialogManager.displayErrorMessage( "You need to save the student first" );

            return;
        }

        Student student = service.getStudent( studentId );

        SelectDialog dialog = dialogManager.createSelectCourseDialog( student.getCourses() );

        if ( dialog.success() )
        {
            Course course = (Course) dialog.getSelectedObject();

            service.addAttendantToCourse( course.getId(), studentId );

            coursesListModel.refresh();
            studentListChangeManager.fireListChanged();
        }
    }

    private void delCourse()
    {
        Object course = coursesList.getSelectedValue();

        if ( course != null )
        {
            service.removeAttendantFromCourse( ((Course) course).getId(), studentId );

            coursesListModel.refresh();
            studentListChangeManager.fireListChanged();
        }
    }

    private void addDegree()
    {
        if ( !stored )
        {
            dialogManager.displayErrorMessage( "You need to save the student first" );

            return;
        }

        Student student = service.getStudent( studentId );

        SelectDialog dialog = dialogManager.createSelectDegreeDialog( student.getDegrees() );

        if ( dialog.success() )
        {
            Degree degree = (Degree) dialog.getSelectedObject();

            if ( service.studentFulfillsDegreeRequirements( studentId, degree.getId() ) )
            {
                service.addDegreeToStudent( studentId, degree.getId() );

                degreesListModel.refresh();
            }
            else
            {
                dialogManager.displayErrorMessage( "This student doesn't fulfill the requirements of this degree!" );
            }
        }
    }

    private void delDegree()
    {
        Object degree = degreesList.getSelectedValue();

        if ( degree != null )
        {
            service.removeDegreeFromStudent( studentId, ((Degree) degree).getId() );

            degreesListModel.refresh();
        }
    }

    private void close()
    {
        courseListChangeManager.removeListChangeListener( coursesListModel );
        degreeListChangeManager.removeListChangeListener( degreesListModel );

        setVisible( false );
        dispose();
    }

    // -------------------------------------------------------------------------
    // List models
    // -------------------------------------------------------------------------

    private class CoursesListModel
        extends AbstractListModel
        implements ListChangeListener
    {
        private List<Course> courses = new ArrayList<Course>();

        public int getSize()
        {
            return courses.size();
        }

        public Object getElementAt( int i )
        {
            return courses.get( i );
        }

        public void refresh()
        {
            Student student = null;

            try
            {
                student = service.getStudent( studentId );
            }
            catch ( RuntimeException e )
            {
                LOG.error( "Failed to get student", e );

                dialogManager.displayErrorMessage( "Failed to get student: " + e.getMessage() );

                return;
            }

            if ( student == null )
            {
                return;
            }

            courses = new ArrayList<Course>( student.getCourses() );

            Collections.sort( courses, new CourseCodeComparator() );

            fireContentsChanged( this, 0, getSize() );
        }

        public void fireListChanged()
        {
            refresh();
        }
    }

    private class DegreesListModel
        extends AbstractListModel
        implements ListChangeListener
    {
        private List<Degree> degrees = new ArrayList<Degree>();

        public int getSize()
        {
            return degrees.size();
        }

        public Object getElementAt( int i )
        {
            return degrees.get( i );
        }

        public void refresh()
        {
            Student student = null;

            try
            {
                student = service.getStudent( studentId );
            }
            catch ( RuntimeException e )
            {
                LOG.error( "Failed to get student", e );

                dialogManager.displayErrorMessage( "Failed to get student: " + e.getMessage() );

                return;
            }

            if ( student == null )
            {
                return;
            }

            degrees = new ArrayList<Degree>( student.getDegrees() );

            Collections.sort( degrees, new DegreeTypeComparator() );

            fireContentsChanged( this, 0, getSize() );
        }

        public void fireListChanged()
        {
            refresh();
        }
    }

    // -------------------------------------------------------------------------
    // Button listener
    // -------------------------------------------------------------------------

    private class ButtonListener
        implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            String actionCommand = e.getActionCommand();

            try
            {
                if ( actionCommand.equals( BUTTON_SAVE ) )
                {
                    save();
                }
                else if ( actionCommand.equals( BUTTON_CLOSE ) )
                {
                    close();
                }
                else if ( actionCommand.equals( BUTTON_DELETE_AND_CLOSE ) )
                {
                    deleteAndClose();
                }
                else if ( actionCommand.equals( BUTTON_ADD_COURSE ) )
                {
                    addCourse();
                }
                else if ( actionCommand.equals( BUTTON_DEL_COURSE ) )
                {
                    delCourse();
                }
                else if ( actionCommand.equals( BUTTON_ADD_DEGREE ) )
                {
                    addDegree();
                }
                else if ( actionCommand.equals( BUTTON_DEL_DEGREE ) )
                {
                    delDegree();
                }
            }
            catch ( RuntimeException ex )
            {
                LOG.error( "Unable to complete operation", ex );

                dialogManager.displayErrorMessage( "Unable to complete operation: " + ex.getMessage() );
            }
        }
    }
}
