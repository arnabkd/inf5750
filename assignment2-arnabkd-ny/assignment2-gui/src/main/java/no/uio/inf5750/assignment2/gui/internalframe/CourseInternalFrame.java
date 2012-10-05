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
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;
import no.uio.inf5750.assignment2.util.comparator.StudentNameComparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: CourseInternalFrame.java 28 2007-08-23 11:06:31Z torgeilo $
 */
public class CourseInternalFrame
    extends JInternalFrame
{
    private static final Log LOG = LogFactory.getLog( CourseInternalFrame.class );

    // -------------------------------------------------------------------------
    // Action commands
    // -------------------------------------------------------------------------

    private static final String BUTTON_SAVE = "Save";

    private static final String BUTTON_CLOSE = "Close";

    private static final String BUTTON_DELETE_AND_CLOSE = "Delete";

    private static final String BUTTON_ADD_ATTENDANT = "Add";

    private static final String BUTTON_DEL_ATTENDANT = "Remove";

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private StudentSystem service;

    private DialogManager dialogManager;

    private ListChangeManager courseListChangeManager;

    private ListChangeManager attendantsListChangeManager;

    // -------------------------------------------------------------------------
    // Private members
    // -------------------------------------------------------------------------

    private JTextField fieldCode = new JTextField( 20 );

    private JTextField fieldName = new JTextField( 20 );

    private AttendantsListModel attendantsListModel;

    private JList attendantsList;

    private int courseId;

    private boolean stored;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public CourseInternalFrame( StudentSystem service, DialogManager dialogManager,
        ListChangeManager courseListChangeManager, ListChangeManager studentListChangeManager, String title,
        boolean stored )
    {
        this( service, dialogManager, courseListChangeManager, studentListChangeManager, title, stored, 0 );
    }

    public CourseInternalFrame( StudentSystem service, DialogManager dialogManager,
        ListChangeManager courseListChangeManager, ListChangeManager studentListChangeManager, String title,
        boolean stored, int courseId )
    {
        super( "Course", InternalFrameManager.INTERNAL_FRAME_RESIZABLE, InternalFrameManager.INTERNAL_FRAME_CLOSABLE,
            InternalFrameManager.INTERNAL_FRAME_MAXIMIZABLE, InternalFrameManager.INTERNAL_FRAME_ICONIFIABLE );

        this.service = service;
        this.dialogManager = dialogManager;
        this.courseListChangeManager = courseListChangeManager;
        this.attendantsListChangeManager = studentListChangeManager;
        this.courseId = courseId;
        this.stored = stored;

        setTitle( title );

        createGUI();

        if ( stored )
        {
            getCourse( courseId );
            attendantsListModel.refresh();
        }
    }

    // -------------------------------------------------------------------------
    // Private methods
    // -------------------------------------------------------------------------

    private void createGUI()
    {
        JLabel labelCode = new JLabel( "Course Code" );
        JLabel labelName = new JLabel( "Name" );
        JLabel labelAttendants = new JLabel( "Attendants" );

        attendantsListModel = new AttendantsListModel();
        attendantsListChangeManager.addListChangeListener( attendantsListModel );

        attendantsList = new JList( attendantsListModel );
        attendantsList.setCellRenderer( new CellRenderer() );
        attendantsList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        JScrollPane attendantsPane = new JScrollPane( attendantsList );
        attendantsPane.setPreferredSize( new Dimension( 200, 150 ) );

        JButton addAttendant = new JButton( BUTTON_ADD_ATTENDANT );
        addAttendant.addActionListener( new ButtonListener() );

        JButton delAttendant = new JButton( BUTTON_DEL_ATTENDANT );
        delAttendant.addActionListener( new ButtonListener() );

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
        layout.setConstraints( labelCode, con );
        panel.add( labelCode );

        con.gridx = 1;
        con.gridy = 0;
        layout.setConstraints( fieldCode, con );
        panel.add( fieldCode );

        con.gridx = 0;
        con.gridy = 1;
        layout.setConstraints( labelName, con );
        panel.add( labelName );

        con.gridx = 1;
        con.gridy = 1;
        layout.setConstraints( fieldName, con );
        panel.add( fieldName );

        con.gridx = 0;
        con.gridy = 2;
        layout.setConstraints( labelAttendants, con );
        panel.add( labelAttendants );

        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 2;
        layout.setConstraints( attendantsPane, con );
        panel.add( attendantsPane );
        con.gridheight = 1;

        con.gridx = 2;
        con.gridy = 2;
        layout.setConstraints( addAttendant, con );
        panel.add( addAttendant );

        con.gridx = 2;
        con.gridy = 3;
        layout.setConstraints( delAttendant, con );
        panel.add( delAttendant );

        con.gridx = 0;
        con.gridy = 4;
        layout.setConstraints( deleteAndClose, con );
        panel.add( deleteAndClose );

        con.gridx = 1;
        con.gridy = 4;
        layout.setConstraints( save, con );
        panel.add( save );

        con.gridx = 2;
        con.gridy = 4;
        layout.setConstraints( close, con );
        panel.add( close );

        getContentPane().add( panel );
        pack();
    }

    private void getCourse( int courseId )
    {
        Course course = null;

        try
        {
            course = service.getCourse( courseId );
        }
        catch ( RuntimeException e )
        {
            LOG.error( "Failed to get course", e );

            dialogManager.displayErrorMessage( "Failed to get course: " + e.getMessage() );

            return;
        }

        if ( course == null )
        {
            dialogManager.displayErrorMessage( "Course with id " + courseId + " doesn't exist" );

            return;
        }

        fieldCode.setText( course.getCourseCode() );
        fieldName.setText( course.getName() );
    }

    private void save()
    {
        String courseCode = fieldCode.getText();
        String name = fieldName.getText();

        if ( courseCode.trim().equals( "" ) )
        {
            dialogManager.displayErrorMessage( "Please specify a course code" );

            return;
        }

        Course course = service.getCourseByCourseCode( courseCode );

        if ( course != null && (!stored || stored && course.getId() != courseId) )
        {
            dialogManager.displayErrorMessage( "The course code is not unique" );

            return;
        }
        if ( name.trim().equals( "" ) )
        {
            dialogManager.displayErrorMessage( "Please specify a course name" );

            return;
        }

        course = service.getCourseByName( name );

        if ( course != null && (!stored || stored && course.getId() != courseId) )
        {
            dialogManager.displayErrorMessage( "The course name is not unique" );

            return;
        }

        if ( !stored )
        {
            courseId = service.addCourse( courseCode, name );
            stored = true;
        }
        else
        {
            service.updateCourse( courseId, courseCode, name );

            courseListChangeManager.fireListChanged();
        }
    }

    private void deleteAndClose()
    {
        if ( stored )
        {
            service.delCourse( courseId );
            stored = false;
        }

        courseListChangeManager.fireListChanged();

        close();
    }

    private void addAttendant()
    {
        if ( !stored )
        {
            dialogManager.displayErrorMessage( "You need to save the course first" );

            return;
        }

        Course course = service.getCourse( courseId );

        SelectDialog dialog = dialogManager.createSelectStudentDialog( course.getAttendants() );

        if ( dialog.success() )
        {
            Student attendant = (Student) dialog.getSelectedObject();

            service.addAttendantToCourse( courseId, attendant.getId() );

            attendantsListModel.refresh();
            courseListChangeManager.fireListChanged();
        }
    }

    private void delAttendant()
    {
        Object attendant = attendantsList.getSelectedValue();

        if ( attendant != null )
        {
            service.removeAttendantFromCourse( courseId, ((Student) attendant).getId() );

            attendantsListModel.refresh();
            courseListChangeManager.fireListChanged();
        }
    }

    private void close()
    {
        attendantsListChangeManager.removeListChangeListener( attendantsListModel );

        setVisible( false );
        dispose();
    }

    // -------------------------------------------------------------------------
    // List model
    // -------------------------------------------------------------------------

    private class AttendantsListModel
        extends AbstractListModel
        implements ListChangeListener
    {
        private List<Student> attendants = new ArrayList<Student>();

        public int getSize()
        {
            return attendants.size();
        }

        public Object getElementAt( int i )
        {
            return attendants.get( i );
        }

        public void refresh()
        {
            Course course = null;

            try
            {
                course = service.getCourse( courseId );
            }
            catch ( RuntimeException e )
            {
                LOG.error( "Failed to get course", e );

                dialogManager.displayErrorMessage( "Failed to get course: " + e.getMessage() );

                return;
            }

            if ( course == null )
            {
                return;
            }

            attendants = new ArrayList<Student>( course.getAttendants() );

            Collections.sort( attendants, new StudentNameComparator() );

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
                else if ( actionCommand.equals( BUTTON_ADD_ATTENDANT ) )
                {
                    addAttendant();
                }
                else if ( actionCommand.equals( BUTTON_DEL_ATTENDANT ) )
                {
                    delAttendant();
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
