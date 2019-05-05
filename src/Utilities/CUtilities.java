package Utilities;
// ----------------------------------------------------------------------
// Name: Pat Callahan
//		Email: Patrick.Callahan At CincinnatiState Dot edu
//		Phone: 513-569-1751
// Class: CUtilities.  May be freely used by any of my students.
//
// Version		Changed By	Notes
// 2008/08/31	P.C.		Updated to 1.1: Top, left, shortcut
// 2012/11/28	P.C.		Updated to 1.2
// ----------------------------------------------------------------------


// ----------------------------------------------------------------------
// Imports
// ----------------------------------------------------------------------
import java.text.*;			// DateFormat, SimpleDateFormat
import java.util.*;			// Date
import java.io.*;			// BufferedReader
import java.math.BigDecimal;// For database currency/money data types
import java.awt.*;			// All kinds of stuff: container, window, etc
import java.awt.event.*;	// ActionListener
import javax.swing.*;		// JEverything
import javax.swing.event.*;	// DocumentListener


// ----------------------------------------------------------------------
// Name: CUtilities
// Abstract: Some general purpose utilities
// ----------------------------------------------------------------------
public class CUtilities
{
	
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Constants
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	private static final String m_strLOG_FILE_EXTENSION = ".log";
	
	// days * hours * minutes * seconds * milliseconds ( for WriteLog )
	private static final long lng10_DAYS = 10 * 24 * 60 * 60 * 1000; 


	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Properties( never make public )
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	private static String m_strOldLogFileAndPath = "";
	private static BufferedWriter m_buwLogFile = null;
	private static NumberFormat numberFormat = new DecimalFormat("$#,###.00");
	

	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Methods
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------


	// ----------------------------------------------------------------------
	// Name:     CenterScreen
	// Abstract: Center the dialog in the screen
	// ----------------------------------------------------------------------
	public static void CenterScreen( JDialog dlgChild )
	{
		try
		{
			Point pntCenter;
			int intTop = 0;
			int intLeft = 0;

			// Get center of screen
			pntCenter = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
			
			// Center child
			intTop = (int) pntCenter.y - ( dlgChild.getHeight( ) / 2 );
			intLeft = (int) pntCenter.x - ( dlgChild.getWidth( ) / 2 );

			// Center child
			dlgChild.setLocation( intLeft, intTop );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// ----------------------------------------------------------------------
	// Name:     CenterScreen
	// Abstract: Center the frame in the screen
	// ----------------------------------------------------------------------
	public static void CenterScreen( JFrame fraChild )
	{
		try
		{
			Point pntCenter;
			int intTop = 0;
			int intLeft = 0;

			// Get center of screen
			pntCenter = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
			
			// Center child
			intTop = (int) pntCenter.y - ( fraChild.getHeight( ) / 2 );
			intLeft = (int) pntCenter.x - ( fraChild.getWidth( ) / 2 );

			// Center child
			fraChild.setLocation( intLeft, intTop );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// ----------------------------------------------------------------------
	// Name:     CenterOwner
	// Abstract: Center the dialog over the owner.
	// ----------------------------------------------------------------------
	public static void CenterOwner( JDialog dlgChild )
	{
		try
		{
			int intTop = 0;
			int intLeft = 0;

			// Get owner
			Window winOwner = dlgChild.getOwner( );
			
			// Get owner location
			Rectangle recOwner = winOwner.getBounds( );
			
			// Center child
			intTop = (int) recOwner.getCenterY( ) - ( dlgChild.getHeight( ) / 2 );
			intLeft = (int) recOwner.getCenterX( ) - ( dlgChild.getWidth( ) / 2 );

			// Center child
			dlgChild.setLocation( intLeft, intTop );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// ----------------------------------------------------------------------
	// Name:     CenterOwner
	// Abstract: Center the frame over the owner.
	// ----------------------------------------------------------------------
	public static void CenterOwner( JFrame fraChild )
	{
		try
		{
			int intTop = 0;
			int intLeft = 0;

			// Get owner
			Window winOwner = fraChild.getOwner( );
			
			// Get owner location
			Rectangle recOwner = winOwner.getBounds( );

			intTop = (int) recOwner.getCenterY( ) - ( fraChild.getHeight( ) / 2 );
			intLeft = (int) recOwner.getCenterX( ) - ( fraChild.getWidth( ) / 2 );

			// Center child
			fraChild.setLocation( intLeft, intTop );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// ----------------------------------------------------------------------
	// Name:     DrawGridMarks
	// Abstract: Draw grid marks along the top and the left side to make
	//			 placing controls easier.  Add a paint method to your class
	//			 to override the default behavior and make a call to this
	//			 procedure.  For example:
	//
	//			public void paint( Graphics g )
	//			{
	//				super.paint( g );
	//				CUtilities.DrawGridMarks( this, g );
	//			}
	// ----------------------------------------------------------------------
	public static void DrawGridMarks( JDialog dlgSource, Graphics graTarget )
	{
		try
		{
			int intColumn = 0;
			int intRow = 0;
			int intTopOffset = 0;
			int intLeftOffset = 0;

			// Make the font a little smaller
			graTarget.setFont( new Font( graTarget.getFont( ).getName( ), 
										 graTarget.getFont( ).getStyle( ), 
							     (int) ( graTarget.getFont( ).getSize( ) * 0.75f ) ) );
			
			// Don't count height of title bar
			intTopOffset = dlgSource.getInsets().top;
			intLeftOffset = dlgSource.getInsets().left;
			
			// Grid lines across the top
			for( intColumn = 20; intColumn < dlgSource.getWidth( ); intColumn += 20 )
			{
				// Little mark
				graTarget.drawLine( intLeftOffset + intColumn - 10, intTopOffset, 
									intLeftOffset + intColumn - 10, intTopOffset + 2 );
				// Big mark
				graTarget.drawLine( intLeftOffset + intColumn, intTopOffset, 
									intLeftOffset + intColumn, intTopOffset + 5 );
				// Big mark distances
				graTarget.drawString( "" + intColumn , intLeftOffset + intColumn - 5 , intTopOffset + 15 );
			}

			// Grid lines down the side
			for( intRow = 20; intRow < dlgSource.getHeight( ); intRow += 20 )
			{
				// Little mark
				graTarget.drawLine( intLeftOffset, intTopOffset + intRow - 10, 
									intLeftOffset + 2, intTopOffset + intRow - 10 );
				// Big mark
				graTarget.drawLine( intLeftOffset, intTopOffset + intRow, 
									intLeftOffset + 5, intTopOffset + intRow );
				// Big mark distance
				graTarget.drawString( "" + intRow , intLeftOffset + 5 , intTopOffset + intRow + 3 );
			}
			
			// Add a center mark on the bottom to help center buttons
			graTarget.drawLine( intLeftOffset + dlgSource.getWidth( ) / 2, dlgSource.getHeight( ) - 5, 
								intLeftOffset + dlgSource.getWidth( ) / 2, dlgSource.getHeight( ) );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}

	
	// ----------------------------------------------------------------------
	// Name:     DrawGridMarks
	// Abstract: Draw grid marks along the top and the left side to make
	//			 placing controls easier.  Add a paint method to your class
	//			 to override the default behavior and add a call to this
	//			 procedure.  For example:
	//
	//			public void paint( Graphics g )
	//			{
	//				CUtilities.DrawGridMarks( this, g );
	//			}
	// ----------------------------------------------------------------------
	public static void DrawGridMarks( JFrame fraSource, Graphics graTarget )
	{
		try
		{
			int intColumn = 0;
			int intRow = 0;
			int intTitleBarHeight = 0;

			// Make the font a little smaller
			graTarget.setFont( new Font( graTarget.getFont( ).getName( ), 
										 graTarget.getFont( ).getStyle( ), 
							     (int) ( graTarget.getFont( ).getSize( ) * 0.75f ) ) );
			
			// Don't count height of title bar
			intTitleBarHeight = fraSource.getInsets().top;
			
			// Grid lines across the top
			for( intColumn = 20; intColumn < fraSource.getWidth( ); intColumn += 20 )
			{
				// Little mark
				graTarget.drawLine( intColumn - 10, intTitleBarHeight, intColumn - 10, intTitleBarHeight + 2 );
				// Big mark
				graTarget.drawLine( intColumn, intTitleBarHeight, intColumn, intTitleBarHeight + 5 );
				// Big mark distances
				graTarget.drawString( "" + intColumn , intColumn - 5 , intTitleBarHeight + 15 );
			}

			// Grid lines down the side
			for( intRow = 20; intRow < fraSource.getHeight( ); intRow += 20 )
			{
				// Little mark
				graTarget.drawLine( 0, intTitleBarHeight + intRow - 10, 4, intTitleBarHeight + intRow - 10 );
				// Big mark
				graTarget.drawLine( 0, intTitleBarHeight + intRow, 7, intTitleBarHeight + intRow );
				// Big mark distance
				graTarget.drawString( "" + intRow , 8, intTitleBarHeight + intRow + 3 );
			}
			
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}

	
	// ----------------------------------------------------------------------
	// Name:     AddButton
	// Abstract: Add a button to the content pane.
	// ----------------------------------------------------------------------
	public static JButton AddButton( Container conParent, ActionListener alParent,
									 String strTitle,  
									 int intTop, int intLeft, 
									 int intHeight, int intWidth )
	{
		JButton btnNewButton = null; 
		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );
			
			// No shortcut key so pass in a space
			btnNewButton = AddButton( conParent, alParent, strTitle, ' ',  
									  intTop, intLeft, intHeight, intWidth );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return btnNewButton;
	}


	// ----------------------------------------------------------------------
	// Name:     AddButton
	// Abstract: Add a button to the content pane.
	// ----------------------------------------------------------------------
	public static JButton AddButton( Container conParent, ActionListener alParent,
									 String strTitle, char chrKeyboardShortcut, 
									 int intTop, int intLeft, 
									 int intHeight, int intWidth )
	{
		JButton btnNewButton = null;

		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );

			// New button
			btnNewButton = new JButton( strTitle );
						
			conParent.add( btnNewButton );
			
			// Set position and size
			btnNewButton.setBounds( intLeft, intTop, intWidth, intHeight );
						
			// Keyboard shortcut?
			if( chrKeyboardShortcut != ' ' ) btnNewButton.setMnemonic( chrKeyboardShortcut );
			
			// Click event listener?
			if( alParent != null ) btnNewButton.addActionListener( alParent );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return btnNewButton;
	}


	// ----------------------------------------------------------------------
	// Name:     AddCheckBox
	// Abstract: Add a CheckBox
	// ----------------------------------------------------------------------
	public static JCheckBox AddCheckBox( Container conParent, ActionListener alParent,
										 String strTitle,
										 int intTop, int intLeft )
	{
		JCheckBox chkNewCheckBox = null;
		
		try
		{
			chkNewCheckBox = AddCheckBox( conParent, alParent, strTitle, intTop, intLeft, false );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return chkNewCheckBox;
	}


	// ----------------------------------------------------------------------
	// Name:     AddCheckBox
	// Abstract: Add a CheckBox
	// ----------------------------------------------------------------------
	public static JCheckBox AddCheckBox( Container conParent, ActionListener alParent,
										 String strTitle, int intTop, int intLeft, 
										 boolean blnChecked )
	{
		JCheckBox chkNewCheckBox = null;
		
		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );

			// New CheckBox
			chkNewCheckBox = new JCheckBox( strTitle );
			conParent.add( chkNewCheckBox );

			// Set position
			chkNewCheckBox.setBounds( intLeft, intTop, 
									  chkNewCheckBox.getPreferredSize().width, chkNewCheckBox.getPreferredSize().height );

			// Click event listener?
			if( alParent != null ) chkNewCheckBox.addActionListener( alParent );
			
			// Checked?
			if( blnChecked == true ) chkNewCheckBox.setSelected( true );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return chkNewCheckBox;
	}


	// ----------------------------------------------------------------------
	// Name:     AddComboBox
	// Abstract: Add a ComboBox to the content pane.
	// ----------------------------------------------------------------------
	public static CComboBox AddComboBox( Container conParent, ItemListener mlParent,
										 int intTop, int intLeft, int intHeight, int intWidth)
	{
		CComboBox cmbNewComboBox = null;
		
		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );

			// New cmbNewComboBox
			cmbNewComboBox = new CComboBox( );
			conParent.add( cmbNewComboBox );

			// Set position and size
			cmbNewComboBox.setBounds( intLeft, intTop, intWidth, intHeight );
			
			// Selected Index Changed event listener?
			if( mlParent != null ) cmbNewComboBox.addItemListener( mlParent );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return cmbNewComboBox;
	}
	

	// ----------------------------------------------------------------------
	// Name:     AddLabel
	// Abstract: Add a label
	// ----------------------------------------------------------------------
	public static JLabel AddLabel( Container conParent, String strTitle, 
								   int intTop, int intLeft )
	{
		JLabel lblNewLabel = null;
		
		try
		{
			// Allows us to specify position and size for controls
			conParent.setLayout( null );

			// New Label
			lblNewLabel = new JLabel( strTitle );
			conParent.add( lblNewLabel );

			// Set position and size
			lblNewLabel.setBounds( intLeft, intTop, 
								   lblNewLabel.getPreferredSize().width, lblNewLabel.getPreferredSize().height );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return lblNewLabel;
	}


	// ----------------------------------------------------------------------
	// Name:     AddRequiredFieldLabel
	// Abstract: Add a label with "* = Required" in light gray and slightly
	//			 smaller font.
	// ----------------------------------------------------------------------
	public static JLabel AddRequiredFieldLabel( Container conParent, int intTop, int intLeft )
	{
		JLabel lblRequiredField = null;
		
		try
		{

			lblRequiredField = AddColoredSizedLabel( conParent, intTop, intLeft,
													 "* = Required Field", "999999", 0.90f );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return lblRequiredField;
	}
		
	
	// ----------------------------------------------------------------------
	// Name:     AddColoredSizedLabel
	// Abstract: Add a label with different color and font size (%)
	//			 AddColoredSizedLabel( getContentPane( ), 10, 20, "Warning", "FF0000", 0.50f )
	//		     add a label with text "Warning" in red at 50% the form default font size
	// ----------------------------------------------------------------------
	public static JLabel AddColoredSizedLabel( Container conParent, int intTop, int intLeft,
											   String strTitle, String strRRGGBB,
											   float sngRelativeFontSize )
	{
		JLabel lblRequiredField  = null;
		
		try
		{
			int intRed = Integer.parseInt( strRRGGBB.substring( 0, 2 ) );
			int intGreen = Integer.parseInt( strRRGGBB.substring( 2, 4 ) );
			int intBlue = Integer.parseInt( strRRGGBB.substring( 4, 6 ) );
			
			lblRequiredField = AddLabel( conParent, strTitle, intTop, intLeft );
			
			// Color
			lblRequiredField.setForeground( new Color( intRed, intGreen, intBlue ) );
			
			// 90% the size
			lblRequiredField.setFont( new Font( conParent.getFont( ).getName( ), 
											    conParent.getFont( ).getStyle( ), 
											    (int) ( conParent.getFont( ).getSize( ) * sngRelativeFontSize )));			
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return lblRequiredField;
	}
	

	// ----------------------------------------------------------------------
	// Name:     AddColoredSizedHeaderLabel
	// Abstract: Add a header label with a different color, style, and font size 
	// ----------------------------------------------------------------------
	public static JLabel AddColoredSizedHeaderLabel( Container conParent, int intTop, int intLeft,
											         String strTitle )    
	{
		JLabel lblHeader  = null;
		try
		{
			// Assign the label values from FMain to lblHeader
			lblHeader = AddLabel( conParent, strTitle, intTop, intLeft );
			
			// Over-ride the layout's default label boundaries to accommodate a larger header
			lblHeader.setSize( new Dimension (200, 20 ) );
			
			// Set a custom color for the Header based upon RGB values taken from a color chart (this one's deep blue)
			//lblHeader.setForeground( new Color( 11, 11, 59 ) );
			lblHeader.setForeground( new Color( 0, 0, 255 ) );
			
			// Instantiate a new font object with the desired font face, style & size attributes 
			java.awt.Font fntHeader = new java.awt.Font( "Arial", Font.BOLD, 16 );
			
			// Apply the new font to the Header 
			lblHeader.setFont( fntHeader );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return lblHeader;
	}
		
	
	// ----------------------------------------------------------------------
	// Name:     AddRequiredFieldLabel
	// Abstract: Add a label with different color and font size (%)
	//			 AddColoredLabel( getContentPane( ), 10, 20, "RequiredFieldLabel", "0000255", 0.90f )
	// ----------------------------------------------------------------------
	public static JLabel AddColoredLabel( Container conParent, int intTop, int intLeft, 
										  String strTitle, String strRRGGBB )
	{
		JLabel lblHeader  = null;
		
		try
		{
			int intRed = Integer.parseInt( strRRGGBB.substring( 0, 2 ) );
			int intGreen = Integer.parseInt( strRRGGBB.substring( 2, 4 ) );
			int intBlue = Integer.parseInt( strRRGGBB.substring( 4, 7 ) );
									
			//lblHeader = AddLabel( conParent, "Form Management Tools", intTop, intLeft );
			lblHeader = AddLabel( conParent, strTitle, intTop, intLeft );
			
			// Gray
			lblHeader.setForeground( new Color( intRed, intGreen, intBlue ) );
			
			// 90% the size
			//lblHeader.setFont( new Font( conParent.getFont( ).getName( ), 
			//							 conParent.getFont( ).getStyle( ))); 
										 //(int) ( conParent.getFont( ).getSize( ) * sngRelativeFontSize ) ) );

		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return lblHeader;
	}
	

	// ----------------------------------------------------------------------
	// Name:     AddListBox
	// Abstract: Add a list to the content pane.
	// ----------------------------------------------------------------------
	public static CListBox AddListBox( Container conParent, MouseListener mlParent,
										int intTop, int intLeft,
										int intHeight, int intWidth )
	{
		CListBox lstNewList = null;
		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );

			// New lstNewList
			lstNewList = new CListBox( );
			conParent.add( lstNewList );

			// Set position and size
			lstNewList.setBounds( intLeft, intTop, intWidth, intHeight );

			// Selected Index Changed event listener?
			if( mlParent != null ) lstNewList.addMouseListener( mlParent );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return lstNewList;
	}


   // ----------------------------------------------------------------------
    // Name:     AddMenuBar
    // Abstract: Add a menu bar to the frame
    // ----------------------------------------------------------------------
    public static JMenuBar AddMenuBar( JFrame fraParent )
    {
        
        JMenuBar mbMainMenu = null;
        
        try
        {
            mbMainMenu = new JMenuBar( );
            
            fraParent.setJMenuBar( mbMainMenu );
        }
        catch( Exception expError )
        {
            // Display Error Message
            CUtilities.WriteLog( expError );
        }
        return mbMainMenu;        
    }

    // ----------------------------------------------------------------------
    // Name:     AddMenuBar
    // Abstract: Add a menu bar to the frame
    // ----------------------------------------------------------------------
    public static JMenuBar AddMenuBar( JDialog dlgParent )
    {
        
        JMenuBar mbMainMenu = null;
        
        try
        {
            mbMainMenu = new JMenuBar( );
            
            dlgParent.setJMenuBar( mbMainMenu );
        }
        catch( Exception expError )
        {
            // Display Error Message
            CUtilities.WriteLog( expError );
        }
		
		// Return result
        return mbMainMenu;
    }


	// ----------------------------------------------------------------------
	// Name:     AddMenu
	// Abstract: Add a menu to a menu bar
	// ----------------------------------------------------------------------
	public static JMenu AddMenu( JMenuBar mbParent, String strTitle )
	{
		JMenu mnuNewMenu = null;
		
		try
		{		
			mnuNewMenu = AddMenu( mbParent, strTitle, ' ' );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return mnuNewMenu;
	}


	// ----------------------------------------------------------------------
	// Name:     AddMenu
	// Abstract: Add a menu to a menu bar
	// ----------------------------------------------------------------------
	public static JMenu AddMenu( JMenuBar mbParent, String strTitle, char chrKeyboardShortcut )
	{
		JMenu mnuNewMenu = null;
		
		try
		{		
			// Add to menu bar
			mnuNewMenu = new JMenu( strTitle );
	
			// Set mnemonic if there is one
			if( chrKeyboardShortcut != ' ' ) mnuNewMenu.setMnemonic( chrKeyboardShortcut );
	
			mbParent.add( mnuNewMenu );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return mnuNewMenu;
	}


	// ----------------------------------------------------------------------
	// Name:     AddMenuItem
	// Abstract: Add a menu item to a menu
	// ----------------------------------------------------------------------
	public static JMenuItem AddMenuItem( JMenu mnuParent, 
										 ActionListener alTarget, 
										 String strTitle )
	{

		JMenuItem mniNewMenuItem = null;
		
		try
		{		
			mniNewMenuItem = AddMenuItem( mnuParent, alTarget, strTitle, ' ' );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return mniNewMenuItem;
	}


	// ----------------------------------------------------------------------
	// Name:     AddMenuItem
	// Abstract: Add a menu item to a menu
	// ----------------------------------------------------------------------
	public static JMenuItem AddMenuItem( JMenu mnuParent, ActionListener alTarget,
										 String strTitle, char chrKeyboardShortcut )
	{
		JMenuItem mniNewMenuItem = null;
		
		try
		{		
			// Add to menu
			mniNewMenuItem = mnuParent.add( strTitle );
			
			// Set mnemonic if there is one
			if( chrKeyboardShortcut != ' ' ) mniNewMenuItem.setMnemonic( chrKeyboardShortcut );
			
			// Add click event
			mniNewMenuItem.addActionListener( alTarget );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return mniNewMenuItem;
	}


	// ----------------------------------------------------------------------
	// Name:     AddPanel
	// Abstract: Add a panel/group box to the content pane.  Make a spring
	//			 layout manager for it.
	// ----------------------------------------------------------------------
	public static JPanel AddPanel( Container conParent, 
								   int intTop, int intLeft, 
								   int intHeight, int intWidth, 
								   String strTitle )
	{
		JPanel panNewPanel = null;
		
		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );

			// New Panel
			panNewPanel = new JPanel( );
			panNewPanel.setLayout( null );	// Allows us to specify position and size for child controls
			panNewPanel.setBorder( BorderFactory.createTitledBorder( " " + strTitle + " " ) );
			
			// Add to frame
			conParent.add( panNewPanel );

			// Position and Size
			panNewPanel.setBounds( intLeft, intTop, intWidth, intHeight );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return panNewPanel;
	}
	
	
	// ----------------------------------------------------------------------
	// Name:     AddRadioButton
	// Abstract: Add a RadioButton
	// ----------------------------------------------------------------------
	public static JRadioButton AddRadioButton( Container conParent, ActionListener alParent,
											   ButtonGroup bgpGroup, String strTitle,
											   int intTop, int intLeft )
	{
		JRadioButton optNewRadioButton = null;
		
		try
		{
			optNewRadioButton = AddRadioButton( conParent, alParent, bgpGroup, strTitle, intTop, intLeft, false );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return optNewRadioButton;
	}
/*	
	// ----------------------------------------------------------------------
		// Name:     AddRadioButton
		// Abstract: Add a RadioButton
		// ----------------------------------------------------------------------
		public static JRadioButton AddRadioButtonGroupToPanel( Container conParent, ActionListener alParent,
												   JPanel jpnPanel, String strTitle,
												   int intTop, int intLeft )
		{
			JRadioButton optNewRadioButton = null;
			
			try
			{
				optNewRadioButton = AddRadioButton( conParent, alParent, jpnPanel, strTitle, intTop, intLeft, false );
			}
			catch( Exception expError )
			{
				// Display Error Message
				CUtilities.WriteLog( expError );
			}
			
			// Return result
			return optNewRadioButton;
		}
*/


	// ----------------------------------------------------------------------
	// Name:     AddRadioButton
	// Abstract: Add a RadioButton
	// ----------------------------------------------------------------------
	public static JRadioButton AddRadioButton( Container conParent, ActionListener alParent,
											   ButtonGroup bgpGroup, String strTitle,
											   int intTop, int intLeft, boolean blnChecked )
	{
		JRadioButton optNewRadioButton = null;
		
		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );

			// New RadioButton
			optNewRadioButton = new JRadioButton( strTitle );
			conParent.add( optNewRadioButton );
			
			// Set position
			optNewRadioButton.setBounds( intLeft, intTop, 
										 optNewRadioButton.getPreferredSize().width, optNewRadioButton.getPreferredSize().height );

			// Click event listener?
			if( alParent != null ) optNewRadioButton.addActionListener( alParent );
			
			// Add to the button group so only one can be selected at a time
			if( bgpGroup != null ) bgpGroup.add( optNewRadioButton );
			
			// Checked?
			if( blnChecked == true ) optNewRadioButton.setSelected( true );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result
		return optNewRadioButton;
	}


	// ----------------------------------------------------------------------
	// Name:     AddTextBox
	// Abstract: Add a text box
	//			 Top should be 3-ish higher than matching label
	//			 Good height: 25
	//			 Good width: 180-ish
	// ----------------------------------------------------------------------
	public static CTextBox AddTextBox( Container conParent, DocumentListener dlParent,
										String strDefaultValue, 
										int intTop, int intLeft,
										int intHeight, int intWidth,  
										int intMaximumLength )
	{
		CTextBox txtNewTextBox = null;

		try
		{
			// Allows us to specify position and size for controls
			ClearContainerLayoutManager( conParent );

			// New Textbox
			txtNewTextBox = new CTextBox( strDefaultValue, intMaximumLength );
			conParent.add( txtNewTextBox );

			// Set position
			txtNewTextBox.setBounds( intLeft, intTop, intWidth, intHeight );
			
			// Text Changed event listener?
			if( dlParent != null ) txtNewTextBox.getDocument( ).addDocumentListener( dlParent );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return txtNewTextBox;
	}


	// -------------------------------------------------------------------------
	// Name:     ClearContainerLayoutManager
	// Abstract: Java Frames and Dialogs use a layout manager to automatically
	//			 set the location and size of controls on a form.  In theory
	//			 this sounds good but in practice it doesn't work very well.
	//			 Clear the layout manager for the container by setting it 
	//			 to null.  This allows us to manually set the location
	//			 and size for each control.
	// -------------------------------------------------------------------------
	private static void ClearContainerLayoutManager( Container conTarget )
	{
		try
		{
			// Clear?
			if( conTarget.getLayout( ) != null )
			{
				// No, but now it is
				conTarget.setLayout( null );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}
	

	// -------------------------------------------------------------------------
	// Name:     SetBusyCursor
	// Abstract: Lock the form during database/file operations.
	//			 Unlock when done.
	//		     Needs work: http://www.javaspecialists.eu/archive/Issue075.html
	// -------------------------------------------------------------------------
	public static void SetBusyCursor( JFrame fraTarget, boolean blnBusy )
	{
		try
		{
			// Busy?
			if( blnBusy == true )
			{
				// Yes
				fraTarget.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
				fraTarget.setEnabled( false );
		    }
			else
			{
				// No
				fraTarget.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
				fraTarget.setEnabled( true );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// -------------------------------------------------------------------------
	// Name:     SetBusyCursor
	// Abstract: Lock the form during database/file operations.
	//			 Unlock when done.
	// -------------------------------------------------------------------------
	public static void SetBusyCursor( JDialog dlgTarget, boolean blnBusy )
	{
		try
		{
			// Busy?
			if( blnBusy == true )
			{
				// Yes
				dlgTarget.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
				dlgTarget.setEnabled( false );
			}
			else
			{
				// No
				dlgTarget.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
				dlgTarget.setEnabled( true );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// ----------------------------------------------------------------------
	// Name:     TrimAllFormTextBoxes
	// Abstract: Trim all the text boxes on the form
	// ----------------------------------------------------------------------
	public static void TrimAllFormTextBoxes( JFrame frmTarget )
	{
		try
		{
			TrimAllFormTextBoxes( frmTarget.getContentPane( ) );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}
	
	
	// ----------------------------------------------------------------------
	// Name:     TrimAllFormTextBoxes
	// Abstract: Trim all the text boxes on the form
	// ----------------------------------------------------------------------
	public static void TrimAllFormTextBoxes( JDialog dlgTarget )
	{
		try
		{
			TrimAllFormTextBoxes( dlgTarget.getContentPane( ) );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}
	
	
	// ----------------------------------------------------------------------
	// Name:     TrimAllFormTextBoxes
	// Abstract: Trim all the text boxes on the form
	// ----------------------------------------------------------------------
	public static void TrimAllFormTextBoxes( Container conTarget )
	{
		try
		{	
			int intIndex = 0;
			Component cpnCurrentControl = null;
			CTextBox txtCurrentTextBox = null;
			JTextField txtCurrentTextField = null;
			String strBuffer = "";
			
			// Loop through all the controls in the container
			for( intIndex = 0; intIndex < conTarget.getComponentCount( ); intIndex += 1 )
			{
				// Current control
				cpnCurrentControl = conTarget.getComponent( intIndex );
				
				// CTextBox?
				if( cpnCurrentControl instanceof CTextBox == true )
				{
					// Cast to CTextBox so we can access class specific methods 
					txtCurrentTextBox = (CTextBox) cpnCurrentControl;
					
					// Trim
					strBuffer = txtCurrentTextBox.getText( );
					strBuffer = strBuffer.trim( );
					txtCurrentTextBox.setText( strBuffer );
					
				}
				// JTextField?
				else if( cpnCurrentControl instanceof JTextField == true )
				{
					// Cast to CTextBox so we can access class specific methods 
					txtCurrentTextField = (JTextField) cpnCurrentControl;
					
					// Trim
					strBuffer = txtCurrentTextField.getText( );
					strBuffer = strBuffer.trim( );
					txtCurrentTextField.setText( strBuffer );
				}
				// Another container (e.g. a panel/groupbox with controls inside)?
				else if( cpnCurrentControl instanceof Container == true )
				{
					// Yes, recurse and get any textboxes inside
					TrimAllFormTextBoxes( (Container) cpnCurrentControl );
				}
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// -------------------------------------------------------------------------
	// Name:     Wait
	// Abstract: Wait the specified number of milliseconds
	//		     Needs work:  http://www.catalysoft.com/articles/busyCursor.html
	// -------------------------------------------------------------------------
	public static void Wait( int intMilliseconds )
	{
		try
		{
			long lngWaitUntil = 0;
			
			// Limit to 10 seconds maximum
			if( intMilliseconds > 10000 ) intMilliseconds = 10000;

			lngWaitUntil = System.currentTimeMillis() + intMilliseconds;
			
			// Wait
			while( System.currentTimeMillis() < lngWaitUntil )
			{
				// Bug: need the VB.Net equivalent of Application.DoEvents;
			}

		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}

	
	// -------------------------------------------------------------------------
	// Name: 	 MoveListItems
	// Abstract: Move all selected list items from the source to the destination list
	// -------------------------------------------------------------------------
	public static void MoveListItems( CListBox lstSourceList,  
									  CListBox lstDestinationList )
	{
		try
		{
			int intIndex = 0;
			int intSelectedIndex = 0;
			int intNewIndex = 0;
			int aintSelectedIndices[] = lstSourceList.GetSelectedIndices( );
			int aintIndicesToSelect[] = new int[ aintSelectedIndices.length ];

			// Loop through all the list items and add to destination
			for( intIndex = 0; intIndex < aintSelectedIndices.length; intIndex++ )
			{
				// Selected index
				intSelectedIndex = aintSelectedIndices[ intIndex ];
			
				// Add to destination
				intNewIndex = lstDestinationList.AddItemToList( lstSourceList.GetItem( intSelectedIndex ) );
				
				// Save index so we can select all new items in destination at the end
				aintIndicesToSelect[ intIndex ] = intNewIndex;
			}
			
			// Select all new items in destination list
			lstDestinationList.SetSelectedIndices( aintIndicesToSelect );

			// Remove selected items from source( go from bottom up )
			for( intIndex = aintSelectedIndices.length - 1; intIndex >= 0; intIndex-- )
			{
				// Selected index
				intSelectedIndex = aintSelectedIndices[ intIndex ];

				// Remove from source					
				lstSourceList.RemoveAt( intSelectedIndex );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}


	// ----------------------------------------------------------------------
	// Name: 	 ReadStringFromUser
	// Abstract: Read a string from the user
	// ----------------------------------------------------------------------
	public static String ReadStringFromUser( )
	{
		String strBuffer = "";
		
		try
		{		
			// Input stream
			BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;
	
			// Read a line
			strBuffer = burInput.readLine( );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return strBuffer;
	}
	
	
	// ----------------------------------------------------------------------
	// Name: 	 ConvertStringToUtilDate
	// Abstract: Convert a string in yyyy/mm/dd format to a java.util.Date
	// ----------------------------------------------------------------------
	public static java.util.Date ConvertStringToUtilDate( String strDateToConvert )
	{
		java.util.Date dtmConvertedDate = null;
		
		try
		{		
			dtmConvertedDate = ConvertStringToUtilDate( strDateToConvert, "yyyy/MM/dd" );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return dtmConvertedDate;
	}
		
	
	// ----------------------------------------------------------------------
	// Name: 	 ConvertStringToUtilDate
	// Abstract: Convert a string in the specified format to a java.util.Date
	//			 Month is M or MM.  Minutes is m or mm.
	// ----------------------------------------------------------------------
	public static java.util.Date ConvertStringToUtilDate( String strDateToConvert, String strDateFormat )
	{
		java.util.Date dtmConvertedDate = null;
		
		try
		{		
			SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat( strDateFormat );
			
			dtmConvertedDate = sdfYYYYMMDD.parse( strDateToConvert );
			
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return dtmConvertedDate;
	}
		

	// ----------------------------------------------------------------------
	// Name: 	 ConvertStringToSQLDate
	// Abstract: Convert a string in yyyy/mm/dd format to a java.sql.Date
	// ----------------------------------------------------------------------
	public static java.sql.Date ConvertStringToSQLDate( String strDateToConvert )
	{
		java.sql.Date sdtmConvertedDate = null;
		
		try
		{		
			// Must use slashes (2012/12/12) instead of dashes or dots
			strDateToConvert = strDateToConvert.replace( '-', '/' );
			strDateToConvert = strDateToConvert.replace( '.', '/' );
			
			sdtmConvertedDate = ConvertStringToSQLDate( strDateToConvert, "yyyy/MM/dd" );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return sdtmConvertedDate;
		
	}
	
	
	// ----------------------------------------------------------------------
	// Name: 	 ConvertStringToSQLDate2
	// Abstract: Convert a string in mm/dd/yyyy format to a java.sql.Date
	// ----------------------------------------------------------------------
	public static java.sql.Date ConvertStringToSQLDate2( String strDateToConvert )
	{
		java.sql.Date sdtmConvertedDate = null;
		
		try
		{		
			// Must use slashes (2012/12/12) instead of dashes or dots
			strDateToConvert = strDateToConvert.replace( '-', '/' );
			strDateToConvert = strDateToConvert.replace( '.', '/' );
			
			sdtmConvertedDate = ConvertStringToSQLDate( strDateToConvert, "MM/dd/yyyy" );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return sdtmConvertedDate;
		
	}
		
	
	// ----------------------------------------------------------------------
	// Name: ConvertStringToSQLDate
	// Abstract: Convert a string in the specified format to a java.sql.Date
	// ----------------------------------------------------------------------
	public static java.sql.Date ConvertStringToSQLDate( String strDateToConvert, String strDateFormat )
	{
		java.sql.Date sdtmConvertedDate = null;
		
		try
		{		
			java.util.Date dtmDateToConvert = null;
			
			dtmDateToConvert = ConvertStringToUtilDate( strDateToConvert, strDateFormat );
			
			sdtmConvertedDate = ConvertUtilDateToSQLDate( dtmDateToConvert );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return sdtmConvertedDate;
	}
		

	// ----------------------------------------------------------------------
	// Name: ConvertSQLDateToUtilDate
	// Abstract: Java dates are different than SQL/database dates so we have
	//			 to be able to convert.
	// ----------------------------------------------------------------------
	public static java.util.Date ConvertSQLDateToUtilDate( java.sql.Date sdtmDateToConvert )
	{
		java.util.Date dtmConvertedDate = null;
		
		try
		{		
			dtmConvertedDate = new java.util.Date( sdtmDateToConvert.getTime( ) );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return dtmConvertedDate;
		
	}


	// ----------------------------------------------------------------------
	// Name: ConvertUtilDateToSQLDate
	// Abstract: Java dates are different than SQL/database dates so we have
	//			 to be able to convert.
	// ----------------------------------------------------------------------
	public static java.sql.Date ConvertUtilDateToSQLDate( java.util.Date dtmDateToConvert )
	{
		java.sql.Date sdtmConvertedDate = null;
		
		try
		{		
			sdtmConvertedDate = new java.sql.Date( dtmDateToConvert.getTime( ) );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return sdtmConvertedDate;
		
	}

		
	// ----------------------------------------------------------------------
	// Name: ConvertSQLDateToString
	// Abstract: Convert and repent!
	// ----------------------------------------------------------------------
	public static String ConvertSQLDateToString( java.sql.Date sdtmDateToConvert )
	{
		String strDate = "";
		
		try
		{		
			if( sdtmDateToConvert != null )
			{
				strDate = sdtmDateToConvert.toString( );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return strDate;
		
	}


	// ----------------------------------------------------------------------
	// Name: ConvertUtilDateToString
	// Abstract: Convert and repent!
	// ----------------------------------------------------------------------
	public static String ConvertUtilDateToString( java.util.Date dtmDateToConvert )
	{
		String strDate = "";
		
		try
		{		
			if( dtmDateToConvert != null )
			{
				strDate = dtmDateToConvert.toString( );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return strDate;

	}
	
	
	// ----------------------------------------------------------------------
	// Name: FormatBigDecimalToCurrency
	// Abstract: Format a decimal to $##,###.##			 
	// ----------------------------------------------------------------------
	public static String FormatBigDecimalToCurrency( BigDecimal bgdecValue)
	{
		String strFormattedValue = "";
		BigDecimal bgZeroValue = new BigDecimal("0");
				
		try
		{
			if( bgdecValue.compareTo(bgZeroValue) != 0 )
			{
				strFormattedValue = numberFormat.format( bgdecValue );
			}
		}	
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
							
		return strFormattedValue;
	}

		
	// ----------------------------------------------------------------------
	// Name: IsNumeric
	// Abstract: Use regular expressions to verify that the string is in valid
	//			 numeric format: #.#
	// ----------------------------------------------------------------------
	public static Boolean IsNumeric( String strBuffer )
	{
		boolean blnIsNumeric = false;
		
		try
		{
			char chrStart = '^';
			char chrStop = '$';
			// ( ) denotes a set
			// \ is the escape character in regular expressions and Java so it must be doubled up
			// | is the OR operator
			String strOptionalPlusOrMinus = "(-|\\+)?";

			// +|-#.#
			String strPattern = chrStart + strOptionalPlusOrMinus + "\\d+(\\.\\d+)?" + chrStop;
			
			// Compare
			blnIsNumeric = strBuffer.matches( strPattern );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsNumeric;
		
	}

	
	// ----------------------------------------------------------------------
	// Name: 	 IsCurrency
	// Abstract: Use regular expressions to verify that the string is valid
	//			 currency format: +/-$#,###.##
	//			 From: http://stackoverflow.com/questions/354044/what-is-the-best-u-s-currency-regex
	// ----------------------------------------------------------------------
	public static Boolean IsCurrency( String strBuffer )
	{
		boolean blnIsCurrency = false;
		
		try
		{
			// [0-9]{1,3}(?:[0-9]*(?:[.,][0-9]{2})?|(?:,[0-9]{3})*(?:\.[0-9]{2})?|(?:\.[0-9]{3})*(?:,[0-9]{2})?)
			char chrStart = '^';
			char chrStop = '$';
			// ( ) denotes a set
			// \ is the escape character in regular expressions and Java so it must be doubled up
			// | is the OR operator
			String strOptionalPlusOrMinus = "[+-]?";
			String strOptionalDollarSign = "\\$?";

			String strPattern = chrStart 
							  + strOptionalPlusOrMinus 
							  + strOptionalDollarSign 
							  + "[0-9]{1,3}(?:[0-9]*(?:[.,][0-9]{2})?|(?:,[0-9]{3})*(?:\\.[0-9]{2})?|(?:\\.[0-9]{3})*(?:,[0-9]{2})?)"
							  + chrStop;
			
			// Compare
			blnIsCurrency = strBuffer.matches( strPattern );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsCurrency;
		
	}

	
	// ----------------------------------------------------------------------
	// Name:     ConvertStringToBigDecimal
	// Abstract: Convert a string in currency or numeric format to a 
	//			 big decimal which is what we use for currency fields in
	//			 the database.
	// ----------------------------------------------------------------------
	public static BigDecimal ConvertStringToBigDecimal( String strBuffer )
	{
		BigDecimal bdecValue = new BigDecimal( 0 );
		
		try
		{
			if( strBuffer != null )
			{
				if( CUtilities.IsCurrency( strBuffer ) == true )
				{
					// Remove $ and commas
					strBuffer = strBuffer.replaceAll( "\\$", "" );
					strBuffer = strBuffer.replaceAll( ",", "" );
				}
				
				if( CUtilities.IsNumeric( strBuffer ) == true )
				{
					bdecValue = new BigDecimal( strBuffer );
				}
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return bdecValue;
		
	}

	
	// ----------------------------------------------------------------------
	// Name: 	 IsValidDate
	// Abstract: Use regular expressions to verify that the string is in valid
	//			 date format: yyyy/mm/dd
	//			 Yes, this isn't the most rigorous test and there are better
	//			 regular expressions out there but I leave that as an 
	//			 exercise for the student. ;)
	// ----------------------------------------------------------------------
	public static Boolean IsValidDate( String strBuffer )
	{
		boolean blnIsValidDate = false;
		
		try
		{
			char chrStart = '^';
			char chrStop = '$';
			// ( ) denotes a set
			// \ is the escape character in regular expressions and Java so it must be doubled up
			// | is the OR operator
			String strSlashOrDashOrDot = "(\\/|-|\\.)";

			// yyyy /-. mm /-. dd
			String strPattern = chrStart + "\\d{4}" + strSlashOrDashOrDot + "\\d{2}" + strSlashOrDashOrDot + "\\d{2}" + chrStop;
			
			if( strBuffer.matches( strPattern ) == true ) 
			{
				// Yes
				blnIsValidDate = true; 
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsValidDate;
		
	}
	
	
	// ----------------------------------------------------------------------
	// Name: 	 IsValidDate
	// Abstract: Use regular expressions to verify that the string is in valid
	//			 date format: mm/dd/yyyy
	//			 Yes, this isn't the most rigorous test and there are better
	//			 regular expressions out there but I leave that as an 
	//			 exercise for the student. ;)
	// ----------------------------------------------------------------------
	public static Boolean IsValidDate2( String strBuffer, String strformat )
	{
		boolean blnIsValidDate = false;
		strformat = "mm/dd/yyyy";
		
		try
		{
			char chrStart = '^';
			char chrStop = '$';
			// ( ) denotes a set
			// \ is the escape character in regular expressions and Java so it must be doubled up
			// | is the OR operator
			String strSlashOrDashOrDot = "(\\/|-|\\.)";

			// MM /-. dd /-. yyyy
			String strPattern = chrStart + "\\d{2}" + strSlashOrDashOrDot + "\\d{2}" + strSlashOrDashOrDot + "\\d{4}" + chrStop;
			
			if( strBuffer.matches( strPattern ) == true ) 
			{
				// Yes
				blnIsValidDate = true; 
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsValidDate;
	}

	
	// ----------------------------------------------------------------------
	// Name:     IsValidDate
	// Abstract: Use regular expressions to verify that the string is in valid
	//			 date format.
	// ----------------------------------------------------------------------
	public static Boolean IsValidDate( String strBuffer, String strPattern )
	{
		boolean blnIsValidDate = false;
		
		try
		{		
			// Compare
			blnIsValidDate = strBuffer.matches( strPattern );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsValidDate;
		
	}
	
	
	// ----------------------------------------------------------------------
	// Name: IsValidPhoneNumber
	// Abstract: Use regular expressions to verify that the phone number is in 
	//			 a valid format.
	// ----------------------------------------------------------------------
	public static Boolean IsValidPhoneNumber( String strBuffer )
	{
		boolean blnIsValidPhoneNumber = false;
		
		try
		{	
			char chrStart = '^';
			char chrStop = '$';
			// ( ) denotes a set
			// \ is the escape character in regular expressions and Java so it must be doubled up
			// | is the OR operator
			String strSpaceOrDashOrDot = "( |-|\\.)";
			String strOptionalSpaceOrDashOrDot = strSpaceOrDashOrDot + "?";

			// ###-####
			String strPattern1 = chrStart + "\\d{3}" + strSpaceOrDashOrDot + "\\d{4}" + chrStop;
			
			// ###-###-####
			String strPattern2 = chrStart + "\\d{3}" + strSpaceOrDashOrDot + "\\d{3}" + strSpaceOrDashOrDot + "\\d{4}" + chrStop;
			
			// (###)###-####
			String strPattern3 = chrStart + "\\(\\d{3}\\)" + strOptionalSpaceOrDashOrDot + "\\d{3}" + strSpaceOrDashOrDot + "\\d{4}" + chrStop;

			// Does it match any of the formats?
			if( strBuffer.matches( strPattern1 ) == true ||
				strBuffer.matches( strPattern2 ) == true ||
				strBuffer.matches( strPattern3 ) == true )
			{
				// Yes
				blnIsValidPhoneNumber = true; 
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsValidPhoneNumber;
		
	}

	
	// ----------------------------------------------------------------------
	// Name:     IsValidEmailAddress
	// Abstract: Use regular expressions to verify that the email address is in 
	//			 a valid format.
	// ----------------------------------------------------------------------
	public static Boolean IsValidEmailAddress( String strBuffer )
	{
		boolean blnIsValidEmailAddress = false;
		
		try
		{	
			char chrStart = '^';
			char chrStop = '$';
			// ( ) denotes a set
			// \ is the escape character in regular expressions and Java so it must be doubled up
			// | is the OR operator
			char chrOR = '|';
			String strTopLevelDomainList = "aero|asia|biz|cat|cc|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|xxx";
			String strCountryCodes =  "ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az" + 
									 "|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz" + 
									 "|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cs|cu|cv|cx|cy|cz" + 
									 "|dd|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|eu|fi|fj|fk|fm|fo|fr" +
									 "|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy" + 
									 "|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it" + 
									 "|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz" + 
									 "|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly" + 
									 "|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz" + 
									 "|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om" + 
									 "|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw" +
									 "|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|ss|st|su|sv|sx|sy|sz" +
									 "|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz" +
									 "|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw";
			
			// From http://www.regular-expressions.info/email.html at the end of the page
			String strPattern = chrStart 
							 + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
							 + "(" + strTopLevelDomainList + chrOR + strCountryCodes + ")"
							 + chrStop;

			// Does it match?
			if( strBuffer.matches( strPattern ) == true )
			{
				// Yes
				blnIsValidEmailAddress = true; 
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsValidEmailAddress;
		
	}
	
		
	// ----------------------------------------------------------------------
	// Name:     IsValidZipCode
	// Abstract: Use regular expressions to verify that the string is in valid
	//			 Zip code format.
	// ----------------------------------------------------------------------
	@SuppressWarnings("unused")
	public static Boolean IsValidZipCode( String strBuffer )
	{
		boolean blnIsValidZipCode = false;
		
		try
		{	
			char chrStart = '^';
			char chrStop = '$';
			// ( ) denotes a set
			// \ is the escape character in regular expressions and Java so it must be doubled up
			// | is the OR operator
			String strSpaceOrDash = "( |-)";
			String strOptionalSpaceOrDash = strSpaceOrDash + "?";

			// #####
			String strPattern1 = chrStart + "\\d{5}"  + chrStop;
			
			// #####-####
			String strPattern2 = chrStart + "\\d{5}" + strSpaceOrDash + "\\d{4}" + chrStop;

			// Does it match any of the formats?
			if( strBuffer.matches( strPattern1 ) == true ||
				strBuffer.matches( strPattern2 ) == true )
			{
				// Yes
				blnIsValidZipCode = true; 
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		// Return result		
		return blnIsValidZipCode;
		
	}

	
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// WriteLog
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	/*
	 * What is WriteLog?  It's a procedure that will write an exception or
	 * string to a disk file.  The file is named with the current date using
	 * yyyymmdd format (so it sorts correctly).  The file is created in a Log
	 * directory off of the current working director (usually where the executable
	 * was launched from).
	 * 
	 * Why use WriteLog?  Because when, not if, the user gets an error you 
	 * (the programmer) are going to need the error information to be able
	 * to find and fix the bug in a reasonable period of time.  The exception
	 * will tell you where the error happened and, if you're lucky, why it
	 * happened.
	 * 
	 * How to use?  Wrap procedure code in a try/catch block and put
	 * a call to WriteLog in the catch block.  You really can't do much else
	 * at that point because you don't know why the error happened.  If you
	 * expect an error you should code for it.  So this is only for errors
	 * you don't expect and don't know about.
	 * 
	 * When to use?  When, not if, the technical support phone rings, what 
	 * percentage of the time do you want the exception information to help
	 * you find and fix the problem?  I want it 100%.  That means I have
	 * to use try/catch block with WriteLog in 100% of my procedures.
	 * 
	 * Guidelines for try/catch:
	 * 1) Use in all procedures
	 * 2) ALL code goes in the try block with two exceptions.  If it's a function
	 *    the return variable, and only the return variable, is declared 
	 *    immediately before the try block and the return is immediately
	 *    after the catch block.
	 * 3) Do as little as possible in the catch block so that you don't
	 *    cause another exception.
	 * 4) Use only one try/catch block per procedure.  If you expect
	 *    an error then code for it.  Don't use an empty catch block as
	 *    a crutch to ignore the error.
	 */

	// ----------------------------------------------------------------------
	// Name: WriteLog
	// Abstract: Write to the log file and display a warning to the user.
	// ----------------------------------------------------------------------
	public static void WriteLog( Exception expError )
	{
		try
		{
			boolean blnDisplay = true;
			
			WriteLog( expError, blnDisplay );
		}
		catch( Exception expWriteLogError )
		{
			// Display Error Message
			expWriteLogError.printStackTrace( );
		}
	}
		

	// ----------------------------------------------------------------------
	// Name: WriteLog
	// Abstract: Write to the log file and display a warning to the user.
	// ----------------------------------------------------------------------
	public static void WriteLog( String strMessage )
	{
		try
		{
			boolean blnDisplay = true;
			
			WriteLog( strMessage, blnDisplay );
		}
		catch( Exception expError )
		{
			// Display Error Message
			expError.printStackTrace( );
		}
	}
	

	// ----------------------------------------------------------------------
	// Name: WriteLog
	// Abstract: Write to the log file and optionally display a warning 
	//			to the user.
	// ----------------------------------------------------------------------
	public static void WriteLog( Exception expError, boolean blnDisplay )
	{
		try
		{
			String strMessage = "";
			int intIndex = 0;

			// Get the stack trace messages from the exception class
			StackTraceElement asteStackTrace[] = expError.getStackTrace( );
			
			// Loop through all the procedure calls
			for( intIndex = asteStackTrace.length - 1; intIndex >= 0; intIndex-- )
			{ 
				// Is there a line number?
				if( asteStackTrace[ intIndex ].toString( ).contains( ":" ) == true )
				{
					// Yes, so that means it's one of our classes so add it to the message
					strMessage += asteStackTrace[ intIndex ].toString( ) + "\n\t";
				}
			}
			
			// Remove trailing tab
			strMessage = strMessage.substring( 0, strMessage.length( ) - 1 );
			
			// Add the message
			strMessage += expError.getMessage();
			
			WriteLog( strMessage, blnDisplay );
		}
		catch( Exception expWriteLogError )
		{
			// Display Error Message
			expWriteLogError.printStackTrace( );
		}
	}

	
	// ----------------------------------------------------------------------
	// Name: WriteLog
	// Abstract: Write to the log file and optionally display a warning 
	//			to the user.
	// ----------------------------------------------------------------------
	public static void WriteLog( String strMessage, boolean blnDisplay )
	{
		try
		{
			BufferedWriter buwLogFile = null;
			String astrMessageLines[] = null;
			int intIndex = 0;
			
			// yyyy/mm/dd
			SimpleDateFormat sdfYearMonthDayTime = new SimpleDateFormat( "yyyy/MM/dd hh:mm:ss" );
			
			String strNow = sdfYearMonthDayTime.format( new Date( ) );
			
			// Display the error message?
			if( blnDisplay == true )
			{
				// No, warn the user
				JOptionPane.showMessageDialog( null, strMessage, "WriteLog", JOptionPane.OK_OPTION );
			}
			
			// Append a date/time stamp
			strMessage = strNow + " - " + strMessage + "\n";
			
			// Get a log file
			buwLogFile = GetLogFile( );
			
	    	// Is the file OK?	
			if( buwLogFile != null )
			{
				// Yes, so log message( use newline function to ensure cross-platform compatibility )
				astrMessageLines = strMessage.split( "\n" );
				for( intIndex = 0; intIndex < astrMessageLines.length; intIndex++ )
				{
					buwLogFile.write( astrMessageLines[ intIndex ] );
					buwLogFile.newLine( );
				}
				// Flush buffer
				buwLogFile.flush( );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			expError.printStackTrace( );
		}
	}


	// ----------------------------------------------------------------------
	// Name: GetLogFile
	// Abstract: Open the log file for writing.  Use today's date as part of
	//           the file name.  Each day a new log file will be created.
	//           Makes debug easier.
	// ----------------------------------------------------------------------
	private static BufferedWriter GetLogFile( )
	{
		try
		{
			// yyyy/mm/dd
			SimpleDateFormat sdfYearMonthDay = new SimpleDateFormat( "yyyyMMdd" );
			String strToday = sdfYearMonthDay.format( new Date( ) );
			String strLogFileDirectory = "";
			String strLogFileAndPath = "";
			File filLogDirectory = null; 
	
		    // Log everything in a log directory off of the current application directory
		    strLogFileDirectory = System.getProperty( "user.dir" ) + "\\Log\\";
		    strLogFileAndPath = strLogFileDirectory + strToday + m_strLOG_FILE_EXTENSION;
		    
		    // Is this a new day/log file?
		    if( m_strOldLogFileAndPath != strLogFileAndPath )
		    {
		        // Create the log directory if it doesn't exist
			    filLogDirectory = new File( strLogFileDirectory );
		        if( filLogDirectory.exists( ) == false ) filLogDirectory.mkdir( );
		        	
		        // Save the log file name
		        m_strOldLogFileAndPath = strLogFileAndPath;
		            
		        // Close file if it exists( not first time )
		        if( m_buwLogFile != null ) m_buwLogFile.close( );
	
		        // Open file and append
				m_buwLogFile = new BufferedWriter( new FileWriter( strLogFileAndPath, true ) );
		        
		        // Delete old log files
		        DeleteOldLogFiles( );
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			expError.printStackTrace( );
		}
		
		// Return result
		return m_buwLogFile;
	}


	// ----------------------------------------------------------------------
	// Name: DeleteOldLogFiles
	// Abstract:  Delete any log files old than 10 days.
	// ----------------------------------------------------------------------
	private static void DeleteOldLogFiles( )
	{
		try
		{
			String strLogFileDirectory = "";
			File filLogDirectory = null;
			File afilLogFiles[] = null;
			int intIndex = 0;
			File filLogFile = null;
			long lngMilliSecondsOld = 0;
			Date dtmLastModified = null;
					
			// Log file directory
		    strLogFileDirectory = System.getProperty( "user.dir" ) + "\\Log\\";
			filLogDirectory = new File( strLogFileDirectory );	    
			
		    // Look for any log files
		    afilLogFiles = filLogDirectory.listFiles( );
		    
			// Check the date of each file
			for( intIndex = 0; intIndex < afilLogFiles.length; intIndex++ )
			{
				// Next log file
				filLogFile = afilLogFiles[ intIndex ];
	
				// Is this a log file?
				if( ( filLogFile.getName( ) ).endsWith( m_strLOG_FILE_EXTENSION ) == true )
				{
					// When was the file last modified?
					lngMilliSecondsOld = filLogFile.lastModified( );
					
					// Add 10 days and make a date
					lngMilliSecondsOld += lng10_DAYS;
					dtmLastModified = new Date( lngMilliSecondsOld );
					
					// Is the file older than 10 days?
					if( dtmLastModified.before( new Date( ) ) == true )
					{
						// Yes.  Delete it
						filLogFile.delete( );
					}
				}
			}
		}
		catch( Exception expError )
		{
			// Display Error Message
			expError.printStackTrace( );
		}
	}

}

