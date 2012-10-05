package no.uio.inf5750.assignment2;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import no.uio.inf5750.assignment2.event.CloseListenerProxy;
import no.uio.inf5750.assignment2.gui.UserInterface;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: StudentSystemMain.java 76 2007-09-12 11:29:10Z torgeilo $
 */
public class StudentSystemMain
{
    public static void main( String[] args )
    {
        // ---------------------------------------------------------------------
        // Change look and feel
        // ---------------------------------------------------------------------

        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( InstantiationException e )
        {
        }
        catch ( ClassNotFoundException e )
        {
        }
        catch ( UnsupportedLookAndFeelException e )
        {
        }
        catch ( IllegalAccessException e )
        {
        }

        // ---------------------------------------------------------------------
        // Start Spring
        // ---------------------------------------------------------------------

        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "classpath*:/META-INF/assignment2/beans.xml" );

        // ---------------------------------------------------------------------
        // Start the user interface
        // ---------------------------------------------------------------------

        UserInterface ui = (UserInterface) applicationContext.getBean( "userInterface" );

        ui.addCloseListener( new CloseListenerProxy( applicationContext ) );

        ui.initialize();
    }
}
