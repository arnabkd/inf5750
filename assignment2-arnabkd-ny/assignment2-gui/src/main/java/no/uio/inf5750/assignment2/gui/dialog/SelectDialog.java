package no.uio.inf5750.assignment2.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import no.uio.inf5750.assignment2.gui.list.CellRenderer;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: SelectDialog.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public class SelectDialog
    extends JDialog
{
    // -------------------------------------------------------------------------
    // Action commands
    // -------------------------------------------------------------------------

    private static final String OPTION_OPEN = "Select";

    private static final String OPTION_CANCEL = "Cancel";

    // -------------------------------------------------------------------------
    // Contents
    // -------------------------------------------------------------------------

    private JList list;

    private Object selectedObject = null;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public SelectDialog( JFrame parent, String title, List<?> elements )
    {
        super( parent, title, true );

        addWindowListener( new WindowAdapter()
        {
            public void windowClosing( WindowEvent e )
            {
                close();
            }
        } );

        list = new JList( elements.toArray() );
        list.setCellRenderer( new CellRenderer() );
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        JScrollPane listPane = new JScrollPane( list );
        listPane.setPreferredSize( new Dimension( 250, 300 ) );

        JButton open = new JButton( OPTION_OPEN );
        open.addActionListener( new ButtonListener() );

        JButton cancel = new JButton( OPTION_CANCEL );
        cancel.addActionListener( new ButtonListener() );

        JPanel buttonPanel = new JPanel( new GridLayout( 1, 2, 2, 2 ) );
        buttonPanel.add( open );
        buttonPanel.add( cancel );

        JPanel mainPanel = new JPanel( new BorderLayout( 2, 2 ) );
        mainPanel.setBorder( BorderFactory.createEmptyBorder( 4, 4, 4, 4 ) );
        mainPanel.add( BorderLayout.CENTER, listPane );
        mainPanel.add( BorderLayout.SOUTH, buttonPanel );

        getContentPane().add( mainPanel );

        pack();

        Rectangle rect = parent.getBounds();
        Dimension size = getSize();
        setLocation( rect.x + (rect.width - size.width) / 2, rect.y + (rect.height - size.height) / 2 );

        setVisible( true );
    }

    // -------------------------------------------------------------------------
    // Dialog result methods
    // -------------------------------------------------------------------------

    public boolean success()
    {
        return selectedObject != null;
    }

    public Object getSelectedObject()
    {
        return selectedObject;
    }

    // -------------------------------------------------------------------------
    // Close
    // -------------------------------------------------------------------------

    private void close()
    {
        setVisible( false );
    }

    // -------------------------------------------------------------------------
    // Button listener
    // -------------------------------------------------------------------------

    class ButtonListener
        implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            String actionCommand = e.getActionCommand();

            if ( actionCommand.equals( OPTION_OPEN ) )
            {
                selectedObject = list.getSelectedValue();

                if ( selectedObject != null )
                {
                    close();
                }
            }
            else if ( actionCommand.equals( OPTION_CANCEL ) )
            {
                close();
            }
        }
    }
}
