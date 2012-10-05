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
import no.uio.inf5750.assignment2.service.StudentSystem;
import no.uio.inf5750.assignment2.util.comparator.CourseCodeComparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: DegreeInternalFrame.java 28 2007-08-23 11:06:31Z torgeilo $
 */
public class DegreeInternalFrame
    extends JInternalFrame
{
    private static final Log LOG = LogFactory.getLog( DegreeInternalFrame.class );

    // -------------------------------------------------------------------------
    // Action commands
    // -------------------------------------------------------------------------

    private static final String BUTTON_SAVE = "Save";

    private static final String BUTTON_CLOSE = "Close";

    private static final String BUTTON_DELETE_AND_CLOSE = "Delete";

    private static final String BUTTON_ADD_REQUIRED_COURSE = "Add";

    private static final String BUTTON_DEL_REQUIRED_COURSE = "Remove";

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private StudentSystem service;

    private DialogManager dialogManager;

    private ListChangeManager degreeListChangeManager;

    private ListChangeManager courseListChangeManager;

    // -------------------------------------------------------------------------
    // Private members
    // -------------------------------------------------------------------------

    private JTextField fieldType = new JTextField( 20 );

    private RequiredCoursesListModel requiredCoursesListModel;

    private JList requiredCoursesList;

    private int degreeId;

    private boolean stored;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public DegreeInternalFrame( StudentSystem service, DialogManager dialogManager,
        ListChangeManager degreeListChangeManager, ListChangeManager courseListChangeManager, String title,
        boolean stored )
    {
        this( service, dialogManager, degreeListChangeManager, courseListChangeManager, title, stored, 0 );
    }

    public DegreeInternalFrame( StudentSystem service, DialogManager dialogManager,
        ListChangeManager degreeListChangeManager, ListChangeManager courseListChangeManager, String title,
        boolean stored, int degreeId )
    {
        super( "Degree", InternalFrameManager.INTERNAL_FRAME_RESIZABLE, InternalFrameManager.INTERNAL_FRAME_CLOSABLE,
            InternalFrameManager.INTERNAL_FRAME_MAXIMIZABLE, InternalFrameManager.INTERNAL_FRAME_ICONIFIABLE );

        this.service = service;
        this.dialogManager = dialogManager;
        this.degreeListChangeManager = degreeListChangeManager;
        this.courseListChangeManager = courseListChangeManager;
        this.degreeId = degreeId;
        this.stored = stored;

        setTitle( title );

        createGUI();

        if ( stored )
        {
            getDegree( degreeId );
            requiredCoursesListModel.refresh();
        }
    }

    // -------------------------------------------------------------------------
    // Private methods
    // -------------------------------------------------------------------------

    private void createGUI()
    {
        JLabel labelType = new JLabel( "Type" );
        JLabel labelRequiredCourses = new JLabel( "Required Courses" );

        requiredCoursesListModel = new RequiredCoursesListModel();
        courseListChangeManager.addListChangeListener( requiredCoursesListModel );

        requiredCoursesList = new JList( requiredCoursesListModel );
        requiredCoursesList.setCellRenderer( new CellRenderer() );
        requiredCoursesList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        JScrollPane requiredCoursesPane = new JScrollPane( requiredCoursesList );
        requiredCoursesPane.setPreferredSize( new Dimension( 200, 150 ) );

        JButton addRequiredCourse = new JButton( BUTTON_ADD_REQUIRED_COURSE );
        addRequiredCourse.addActionListener( new ButtonListener() );

        JButton delRequiredCourse = new JButton( BUTTON_DEL_REQUIRED_COURSE );
        delRequiredCourse.addActionListener( new ButtonListener() );

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
        layout.setConstraints( labelType, con );
        panel.add( labelType );

        con.gridx = 1;
        con.gridy = 0;
        layout.setConstraints( fieldType, con );
        panel.add( fieldType );

        con.gridx = 0;
        con.gridy = 1;
        layout.setConstraints( labelRequiredCourses, con );
        panel.add( labelRequiredCourses );

        con.gridx = 1;
        con.gridy = 1;
        con.gridheight = 2;
        layout.setConstraints( requiredCoursesPane, con );
        panel.add( requiredCoursesPane );
        con.gridheight = 1;

        con.gridx = 2;
        con.gridy = 1;
        layout.setConstraints( addRequiredCourse, con );
        panel.add( addRequiredCourse );

        con.gridx = 2;
        con.gridy = 2;
        layout.setConstraints( delRequiredCourse, con );
        panel.add( delRequiredCourse );

        con.gridx = 0;
        con.gridy = 3;
        layout.setConstraints( deleteAndClose, con );
        panel.add( deleteAndClose );

        con.gridx = 1;
        con.gridy = 3;
        layout.setConstraints( save, con );
        panel.add( save );

        con.gridx = 2;
        con.gridy = 3;
        layout.setConstraints( close, con );
        panel.add( close );

        getContentPane().add( panel );
        pack();
    }

    private void getDegree( int degreeId )
    {
        Degree degree = null;

        try
        {
            degree = service.getDegree( degreeId );
        }
        catch ( RuntimeException e )
        {
            LOG.error( "Failed to get degree", e );

            dialogManager.displayErrorMessage( "Failed to get degree: " + e.getMessage() );

            return;
        }

        if ( degree == null )
        {
            dialogManager.displayErrorMessage( "Degree with id " + degreeId + " doesn't exist" );

            return;
        }

        fieldType.setText( degree.getType() );
    }

    private void save()
    {
        String type = fieldType.getText();

        if ( type.trim().equals( "" ) )
        {
            dialogManager.displayErrorMessage( "Please specify a degree type" );

            return;
        }

        Degree degree = service.getDegreeByType( type );

        if ( degree != null && (!stored || stored && degree.getId() != degreeId) )
        {
            dialogManager.displayErrorMessage( "The degree type is not unique" );

            return;
        }

        if ( !stored )
        {
            degreeId = service.addDegree( type );
            stored = true;
        }
        else
        {
            service.updateDegree( degreeId, type );

            degreeListChangeManager.fireListChanged();
        }
    }

    private void deleteAndClose()
    {
        if ( stored )
        {
            service.delDegree( degreeId );
            stored = false;
        }

        degreeListChangeManager.fireListChanged();

        close();
    }

    private void addRequiredCourse()
    {
        if ( !stored )
        {
            dialogManager.displayErrorMessage( "You need to save the course first" );

            return;
        }

        Degree degree = service.getDegree( degreeId );

        SelectDialog dialog = dialogManager.createSelectCourseDialog( degree.getRequiredCourses() );

        if ( dialog.success() )
        {
            Course course = (Course) dialog.getSelectedObject();

            service.addRequiredCourseToDegree( degreeId, course.getId() );

            requiredCoursesListModel.refresh();
        }
    }

    private void delRequiredCourse()
    {
        Object course = requiredCoursesList.getSelectedValue();

        if ( course != null )
        {
            service.removeRequiredCourseFromDegree( degreeId, ((Course) course).getId() );

            requiredCoursesListModel.refresh();
        }
    }

    private void close()
    {
        courseListChangeManager.removeListChangeListener( requiredCoursesListModel );

        setVisible( false );
        dispose();
    }

    // -------------------------------------------------------------------------
    // List model
    // -------------------------------------------------------------------------

    private class RequiredCoursesListModel
        extends AbstractListModel
        implements ListChangeListener
    {
        private List<Course> requiredCourses = new ArrayList<Course>();

        public int getSize()
        {
            return requiredCourses.size();
        }

        public Object getElementAt( int i )
        {
            return requiredCourses.get( i );
        }

        public void refresh()
        {
            Degree degree = null;

            try
            {
                degree = service.getDegree( degreeId );
            }
            catch ( RuntimeException e )
            {
                LOG.error( "Failed to get course", e );

                dialogManager.displayErrorMessage( "Failed to get course: " + e.getMessage() );

                return;
            }

            if ( degree == null )
            {
                return;
            }

            requiredCourses = new ArrayList<Course>( degree.getRequiredCourses() );

            Collections.sort( requiredCourses, new CourseCodeComparator() );

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
                else if ( actionCommand.equals( BUTTON_ADD_REQUIRED_COURSE ) )
                {
                    addRequiredCourse();
                }
                else if ( actionCommand.equals( BUTTON_DEL_REQUIRED_COURSE ) )
                {
                    delRequiredCourse();
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
