/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasigudang;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author Tria Henrico
 */
public class viewretailer {
    // Instance attributes used in this example
	private	JPanel		topPanel;
	private	JTable		table;
	private	JScrollPane scrollPane;
        private JFrame bingkai;

	// Constructor of main frame
	public viewretailer() throws SQLException
	{
                bingkai = new JFrame("View Retailer");
		// Set the frame characteristics
		//bingkaisetTitle( "Simple Table Application" );
		bingkai.setSize( 600, 400 );
		bingkai.setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		//getContentPane().add( topPanel );
                bingkai.add( topPanel );

		// Create columns names
		String columnNames[] = { "id_retailer", "nama retailer", "alamat", "telepon"};
                
                //inisialisasi class DatabaseService
                DatabaseService ds = new DatabaseService();
                String[][] isiTabel = ds.getRetailer();
		// Create some data
		/*String dataValues[][] =
		{
			{ "12", "234", "67", "2" },
			{ "-123", "43", "853", "2" },
			{ "93", "89.2", "109", "2" },
			{ "279", "9033", "3092", "2" },
                        { "279", "9033", "3092", "2" }
		}; */

		// Create a new table instance
		table = new JTable( isiTabel, columnNames );

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );
	}

	// Main entry point for this example
	public void tampilkan()
	{
		// Create an instance of the test application
		//SimpleTableExample mainFrame	= new SimpleTableExample();
                bingkai.setLocationRelativeTo(null);
		bingkai.setVisible( true );
	}
}
