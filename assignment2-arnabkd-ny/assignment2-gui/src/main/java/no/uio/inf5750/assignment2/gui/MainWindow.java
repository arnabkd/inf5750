package no.uio.inf5750.assignment2.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import no.uio.inf5750.assignment2.event.CloseListener;
import no.uio.inf5750.assignment2.gui.dialog.DialogManager;
import no.uio.inf5750.assignment2.gui.dialog.SelectDialog;
import no.uio.inf5750.assignment2.gui.internalframe.InternalFrameManager;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: MainWindow.java 76 2007-09-12 11:29:10Z torgeilo $
 */
public class MainWindow
    extends JFrame
    implements UserInterface
{
    // -------------------------------------------------------------------------
    // Action commands
    // -------------------------------------------------------------------------

    private static final String MENU_NEW_COURSE = "NewCourse";

    private static final String MENU_NEW_DEGREE = "NewDegree";

    private static final String MENU_NEW_STUDENT = "NewStudent";

    private static final String MENU_OPEN_COURSE = "OpenCourse";

    private static final String MENU_OPEN_DEGREE = "OpenDegree";

    private static final String MENU_OPEN_STUDENT = "OpenStudent";

    private static final String MENU_EXIT = "Exit";

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private InternalFrameManager internalFrameManager;

    public void setInternalFrameManager( InternalFrameManager internalFrameManager )
    {
        this.internalFrameManager = internalFrameManager;
    }

    private DialogManager dialogManager;

    public void setDialogManager( DialogManager dialogManager )
    {
        this.dialogManager = dialogManager;
    }

    // -------------------------------------------------------------------------
    // Desktop
    // -------------------------------------------------------------------------

    private JDesktopPane desktop = new JDesktopPane();

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public MainWindow()
    {
        super( "Student System" );
    }

    // -------------------------------------------------------------------------
    // UserInterface implementation
    // -------------------------------------------------------------------------

    public JFrame getMainFrame()
    {
        return this;
    }

    public void initialize()
    {
        addWindowListener( new WindowAdapter()
        {
            public void windowClosing( WindowEvent e )
            {
                exit();
            }
        } );

        setJMenuBar( createMenuBar() );
        setContentPane( desktop );

        setSize( 800, 600 );
        setVisible( true );
    }

    // -------------------------------------------------------------------------
    // CloseListener
    // -------------------------------------------------------------------------

    private Set<CloseListener> closeListeners = new HashSet<CloseListener>();

    public void addCloseListener( CloseListener closeListener )
    {
        closeListeners.add( closeListener );
    }

    public void removeCloseListener( CloseListener closeListener )
    {
        closeListeners.remove( closeListener );
    }

    // -------------------------------------------------------------------------
    // Support methods
    // -------------------------------------------------------------------------

    private JMenuBar createMenuBar()
    {
        ActionListener menuListener = new MenuListener();

        JMenuBar menuBar = new JMenuBar();

        JMenu actionMenu = new JMenu( "Action" );
        menuBar.add( actionMenu );

        JMenu newMenu = new JMenu( "New" );
        JMenu openMenu = new JMenu( "Open" );

        JMenuItem exitItem = new JMenuItem( MENU_EXIT );
        exitItem.addActionListener( menuListener );

        actionMenu.add( newMenu );
        actionMenu.add( openMenu );
        actionMenu.addSeparator();
        actionMenu.add( exitItem );

        JMenuItem newCourseItem = new JMenuItem( "Course" );
        newCourseItem.setActionCommand( MENU_NEW_COURSE );
        newCourseItem.addActionListener( menuListener );

        JMenuItem newDegreeItem = new JMenuItem( "Degree" );
        newDegreeItem.setActionCommand( MENU_NEW_DEGREE );
        newDegreeItem.addActionListener( menuListener );

        JMenuItem newStudentItem = new JMenuItem( "Student" );
        newStudentItem.setActionCommand( MENU_NEW_STUDENT );
        newStudentItem.addActionListener( menuListener );

        JMenuItem openCourseItem = new JMenuItem( "Course" );
        openCourseItem.setActionCommand( MENU_OPEN_COURSE );
        openCourseItem.addActionListener( menuListener );

        JMenuItem openDegreeItem = new JMenuItem( "Degree" );
        openDegreeItem.setActionCommand( MENU_OPEN_DEGREE );
        openDegreeItem.addActionListener( menuListener );

        JMenuItem openStudentItem = new JMenuItem( "Student" );
        openStudentItem.setActionCommand( MENU_OPEN_STUDENT );
        openStudentItem.addActionListener( menuListener );

        newMenu.add( newCourseItem );
        newMenu.add( newDegreeItem );
        newMenu.add( newStudentItem );

        openMenu.add( openCourseItem );
        openMenu.add( openDegreeItem );
        openMenu.add( openStudentItem );

        return menuBar;
    }

    private void addInternalFrame( JInternalFrame frame )
    {
        desktop.add( frame );

        frame.setVisible( true );
    }

    private void exit()
    {
        setVisible( false );

        for ( CloseListener closeListener : closeListeners )
        {
            closeListener.close();
        }

        dispose();

        System.exit( 0 );
    }

    // -------------------------------------------------------------------------
    // Menu listener
    // -------------------------------------------------------------------------

    private class MenuListener
        implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            String actionCommand = e.getActionCommand();

            if ( actionCommand.equals( MENU_EXIT ) )
            {
                exit();
            }
            else if ( actionCommand.equals( MENU_NEW_COURSE ) )
            {
                addInternalFrame( internalFrameManager.createCourseInternalFrame() );
            }
            else if ( actionCommand.equals( MENU_NEW_DEGREE ) )
            {
                addInternalFrame( internalFrameManager.createDegreeInternalFrame() );
            }
            else if ( actionCommand.equals( MENU_NEW_STUDENT ) )
            {
                addInternalFrame( internalFrameManager.createStudentInternalFrame() );
            }
            else if ( actionCommand.equals( MENU_OPEN_COURSE ) )
            {
                SelectDialog dialog = dialogManager.createSelectCourseDialog();

                if ( dialog.success() )
                {
                    Course course = (Course) dialog.getSelectedObject();

                    addInternalFrame( internalFrameManager.createCourseInternalFrame( course.getId() ) );
                }
            }
            else if ( actionCommand.equals( MENU_OPEN_DEGREE ) )
            {
                SelectDialog dialog = dialogManager.createSelectDegreeDialog();

                if ( dialog.success() )
                {
                    Degree degree = (Degree) dialog.getSelectedObject();

                    addInternalFrame( internalFrameManager.createDegreeInternalFrame( degree.getId() ) );
                }
            }
            else if ( actionCommand.equals( MENU_OPEN_STUDENT ) )
            {
                SelectDialog dialog = dialogManager.createSelectStudentDialog();

                if ( dialog.success() )
                {
                    Student student = (Student) dialog.getSelectedObject();

                    addInternalFrame( internalFrameManager.createStudentInternalFrame( student.getId() ) );
                }
            }
        }
    }
}
