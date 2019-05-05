// --------------------------------------------------------------------------
//	Name:     Douglas Heller
//	Abstract: Create the game board and logical moves for a Tic-Tac-Toe
//  game where a player competes against the computer. The player chooses
//  from three different skill levels and who makes the first move. 
// --------------------------------------------------------------------------


// --------------------------------------------------------------------------
// Imports
// --------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;	
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;		
import Utilities.*;  


// --------------------------------------------------------------------------
// Name: FGameBoard
// Abstract: Make an instance of the class
// --------------------------------------------------------------------------
@SuppressWarnings({ "serial", "unused" })
class FGameBoard extends JFrame implements ActionListener 
{
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Constants
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	
	
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Properties
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	private boolean m_blnPlayerX = true; 
	private int m_intMoveCount   = 0;
	private String m_strWinChar  = "";
	private int m_intWinCount    = 0;
	

	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Controls
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------	
	private JPanel		    m_pnlSkillButtons     = null;
	private ButtonGroup     m_bgSkillLevel        = null;
  	private JRadioButton    m_rdoBeginner	      = null;
  	private JRadioButton    m_rdoIntermediate     = null;
  	private JRadioButton    m_rdoExpert	          = null;  	
  	
  	private JPanel		    m_pnlFirstMoveButtons = null;
  	private ButtonGroup     m_bgFirstMove         = null;
  	private JRadioButton    m_rdoPlayer	          = null;
  	private JRadioButton    m_rdoComputer         = null;
  	
  	private JPanel		    m_pnlGameBoard        = null;
  	private final JButton[] m_abtnSquares         = new JButton[9];	 	
  	private JButton         m_btnReset            = null;
	private JButton         m_btnExit             = null;
	
			
	// ----------------------------------------------------------------------
	// Name: FGameBoard
	// Abstract: Default form constructor
	// ----------------------------------------------------------------------
	public FGameBoard() 
	{
		try
		{
			// Initialize frame
			Initialize();
			
			// Add controls 
			AddControls();
		}
		catch(Exception expError)
		{ 
			CUtilities.WriteLog(expError);
		}
	}

	// ----------------------------------------------------------------------
	// Name: Initialize
	// Abstract: Center, set the title, height, width, etc
	// ----------------------------------------------------------------------
	private void Initialize()
	{
		try
		{
			int intWidth  = 500;            
			int intHeight = 500;
			
			this.setTitle("Tic-Tac-Toe");	
			this.setSize(intWidth, intHeight);			
			CUtilities.CenterScreen(this);					  
			setResizable(false);
		}
		catch(Exception expError)
		{ 
			CUtilities.WriteLog(expError);
		}
	}
	
	// -----------------------------------------------------------------------------
    // Name: 	 paint
    // Abstract: Override so that grid marks can be drawn during form development 
    // (uncomment to use)
    // -----------------------------------------------------------------------------
    public void paint(Graphics g)
    {
    	super.paint(g);    	
       	try
    	{
    		//CUtilities.DrawGridMarks(this, g);
    	}
    	catch(Exception expError)
    	{
    		CUtilities.WriteLog(expError);
    	}
    }
		
	// -----------------------------------------------------------------------------
    // Name:     AddControls
    // Abstract: Add all controls to the form 
    //------------------------------------------------------------------------------
    private void AddControls()
    {
        try
        {
            // Clear default layout manager so we can set top, left, height & width of controls
            this.getContentPane().setLayout(null);
            
            // Skill-level panel           
            m_pnlSkillButtons = CUtilities.AddPanel(this, 7, 20, 50, 250, "Skill Level");
            m_pnlSkillButtons.setLayout(new GridLayout(1,3));
            m_bgSkillLevel = new ButtonGroup();
            m_rdoBeginner = CUtilities.AddRadioButton(this, this, m_bgSkillLevel, "Beginner", 0, 0);
            m_rdoBeginner.setSelected(true);
            m_rdoIntermediate = CUtilities.AddRadioButton(this, this, m_bgSkillLevel, "Average", 0, 0);                                    
            m_rdoExpert = CUtilities.AddRadioButton(this, this, m_bgSkillLevel, "Expert", 0, 0);          
            m_pnlSkillButtons.add(m_rdoBeginner);
            m_pnlSkillButtons.add(m_rdoIntermediate);
            m_pnlSkillButtons.add(m_rdoExpert);
            
            // First-move panel 
            m_pnlFirstMoveButtons = CUtilities.AddPanel(this, 7, 290, 50, 180, "First Move");
            m_pnlFirstMoveButtons.setLayout(new GridLayout(1,2));      
            m_bgFirstMove = new ButtonGroup();
            m_rdoPlayer = CUtilities.AddRadioButton(this, this, m_bgFirstMove, "Player", 0, 0);
            m_rdoPlayer.setSelected(true);
            m_rdoComputer = CUtilities.AddRadioButton(this, this, m_bgFirstMove, "Computer", 0, 0);
            m_pnlFirstMoveButtons.add(m_rdoPlayer);
            m_pnlFirstMoveButtons.add(m_rdoComputer);
            
            // Gameboard panel 
            m_pnlGameBoard = CUtilities.AddPanel(this, 67, 85, 310, 323, "Game Board");            
            addSquares(); 
            
            // Reset/exit buttons
            m_btnReset = CUtilities.AddButton(this.getContentPane(), this, "New Game", 390, 360, 30, 100);
            m_btnExit  = CUtilities.AddButton(this.getContentPane(), this, "Exit", 410, 195, 40, 100);            
        }
        catch(Exception expError)
        {
    		CUtilities.WriteLog(expError);
        }
    }
               
    // -----------------------------------------------------------------------------
    // Name:     AddSquares
    // Abstract: Add all game squares (buttons) to the game board 
    //------------------------------------------------------------------------------
    private void addSquares()
	{    	
    	try    		
        {
    		int index   = 0;
    		int top     = 20;
    		int left    = 25;    		
    		int counter = 0; 
        	 			
			// Button font-style
	 		Font squareFont = new Font("Arial", Font.BOLD, 48);
			
			// Loop to create & position game squares (buttons) in a 3 X 3 grid layout
			for(index = 0; index < m_abtnSquares.length; index += 1)
			{						    															
				if(counter == 3)
				{
					top     += 100;
					left    = 25;
					counter = 0;    				
				}				
				m_abtnSquares[index] = CUtilities.AddButton( this.getContentPane(), this, "", top, left, 75, 75);
				m_abtnSquares[index].setFont(squareFont);
				
				// Add button to panel
				m_pnlGameBoard.add(m_abtnSquares[index]);
												
				// Increment left positioning variable and counter
	 			left    += 100;
				counter += 1;								
			}
        }
        catch(Exception expError)
        {
    		CUtilities.WriteLog(expError);          
        }
	}    

    // -----------------------------------------------------------------------------
    // Name:     actionPerformed
    // Abstract: Event handlers for controls
    // -----------------------------------------------------------------------------
    public void actionPerformed(ActionEvent aeSource)
    {
        try
        {                      	
        		 // Button click events 
        	     if(aeSource.getSource() == m_rdoComputer) rdoComputer_OnClick();   
        	else if(aeSource.getSource() == m_btnReset)    Reset();          	 
        	else if(aeSource.getSource() == m_btnExit)     Exit();
        	else if(aeSource.getSource() instanceof JButton) 
			{        		        		        		        		        		        		        		        		     		        		
        		int index = 0;
        		
        		for(index = 0; index < m_abtnSquares.length; index += 1)
        		{          			        			
        			// Square click
        			if(aeSource.getSource() == m_abtnSquares[index])        			
        			{         				
        				//Assign appropriate button text
        				if(m_blnPlayerX)
        				{
        					m_abtnSquares[index].setText("X"); 
        					PlayerFirst();
        				}
        				else m_abtnSquares[index].setText("O");        			        				        				
        				
        				// Disable selected square
                		m_abtnSquares[index].setEnabled(false);                		
                			
                		// Increment move count
                		m_intMoveCount += 1; 
                		break;
        			}
        		}        		        		        	
			}	        	  
    		// Computer first?        		
    		if(m_rdoComputer.isSelected())
    		{        			 
    			ComputerFirst();        			
    		}          						       		       	
        }
        catch(Exception expError)
        {
    		CUtilities.WriteLog(expError);
        }        
    }
    
    // -----------------------------------------------------------------------------
    // Name: 	 PlayerFirst
    // Abstract: Disable appropriate radio buttons after player's 1st move
    // & select appropriate game based upon skill-level selected
    // -----------------------------------------------------------------------------
    private void PlayerFirst()
    {
    	try
    	{	    											
    		m_rdoComputer.setEnabled(false);	   		
    		if(m_rdoBeginner.isSelected())
    		{
    			m_rdoIntermediate.setEnabled(false);
    			m_rdoExpert.setEnabled(false); 
    			PlayerFirstBeginner();
    		}
    		else if(m_rdoIntermediate.isSelected())
    		{
    			m_rdoBeginner.setEnabled(false);
    			m_rdoExpert.setEnabled(false);
    			PlayerFirstIntermediate();
    		}
    		else if(m_rdoExpert.isSelected())
    		{
    			m_rdoBeginner.setEnabled(false);
    			m_rdoIntermediate.setEnabled(false);
    			PlayerFirstExpert();
    		}        						
    	}
        catch(Exception expError)
        { 
            CUtilities.WriteLog(expError);
        }    			
    }
    
    // -----------------------------------------------------------------------------
    // Name: 	 PlayerFirstBeginner
    // Abstract: Computer moves for a player-first beginner's game 
    // -----------------------------------------------------------------------------
    private void PlayerFirstBeginner()
    {
    	try
    	{    		    		 
    		if(m_intMoveCount >= 4 && isWin()) 
        	{
    	    	DisableSquares();
        		getWinner();
        		m_intWinCount += 1;
        	}
        	else
        	{         		        
        		SelectFirstAvailableSquare();
	        	m_intMoveCount += 1;
	        	
	        	if(m_intMoveCount >= 4 && isWin())
	        	{	    			
	        		DisableSquares();
	        		getWinner();
	        		m_intWinCount += 1;
	    		}
        	}		
    	}
        catch(Exception expError)
        { 
            CUtilities.WriteLog(expError);
        }    			
    }
    
    // -----------------------------------------------------------------------------
    // Name: 	 PlayerFirstIntermediate
    // Abstract: Computer moves for a player-first intermediate game 
    // -----------------------------------------------------------------------------
    private void PlayerFirstIntermediate()
    {
    	try
    	{    		    		
    		JButton btnMove = new JButton(); 
    		
    		if(m_intMoveCount >= 4 && isWin()) 
        	{    	   
    			if(m_intWinCount == 0)
    			{
	    			DisableSquares();
	        		getWinner();
	        		m_intWinCount += 1;        		
    			}
        	} 
    		else if(m_abtnSquares[4].getText() == "")
    		{
    			MakeMove(m_abtnSquares[4]);
    		}
    		else if(FindWinOrBlock("O") != null)
    		{    			
    			btnMove = FindWinOrBlock("O");
    			MakeMove(btnMove);    			    			
    		}
    		else if(FindWinOrBlock("X") != null)
    		{    			
    			btnMove = FindWinOrBlock("X");
    			MakeMove(btnMove);    			    			
    		}
    		else if(m_abtnSquares[0].getText()== "O" && m_abtnSquares[4].getText() == "X" && m_abtnSquares[8].getText() == "X"
    				&& m_abtnSquares[2].getText() == "")
    		{
    			MakeMove(m_abtnSquares[2]);
    		}    		
    		else
    		{
    			SelectFirstAvailableSquare();       			    					
    		}
    		m_intMoveCount += 1;
    		
    		if(m_intMoveCount >= 4 && isWin())
        	{	    			    			    
    			if(m_intWinCount == 0)
    			{
    				DisableSquares();
    				getWinner();
    				m_intWinCount += 1;
    			}
        	}   	    		
    	}
        catch(Exception expError)
        { 
            CUtilities.WriteLog(expError);
        }    			
    }
    
    // -----------------------------------------------------------------------------
    // Name: 	 PlayerFirstExpert
    // Abstract: Computer moves for a player-first expert game 
    // -----------------------------------------------------------------------------
    private void PlayerFirstExpert()
    {
    	try
    	{    		    		
    		JButton btnMove = new JButton();
    		
    		if(m_intMoveCount >= 4 && isWin()) 
        	{    	   
    			if(m_intWinCount == 0)
    			{
	    			DisableSquares();
	        		getWinner();
	        		m_intWinCount += 1;        		
    			}
        	} 
    		else if(m_abtnSquares[4].getText() == "")
    		{
    			MakeMove(m_abtnSquares[4]);
    		}
    		else if(FindWinOrBlock("O") != null)
    		{    			
    			btnMove = FindWinOrBlock("O");
    			MakeMove(btnMove);    			    			
    		}
    		else if(FindWinOrBlock("X") != null)
    		{    			
    			btnMove = FindWinOrBlock("X");
    			MakeMove(btnMove);    			    			
    		}
    		else if(m_abtnSquares[2].getText()== "X" && m_abtnSquares[4].getText() == "O" && m_abtnSquares[6].getText() == "X"
    				&& m_abtnSquares[1].getText() == "")
    		{
    			MakeMove(m_abtnSquares[1]);
    		}
    		else if(m_abtnSquares[0].getText()== "O" && m_abtnSquares[4].getText() == "X" && m_abtnSquares[8].getText() == "X"
    				&& m_abtnSquares[2].getText() == "")
    		{
    			MakeMove(m_abtnSquares[2]);
    		}    		
    		else
    		{
    			SelectFirstAvailableSquare();       			    					
    		}
    		m_intMoveCount += 1;
    		
    		if(m_intMoveCount >= 4 && isWin())
        	{	    			    			    
    			if(m_intWinCount == 0)
    			{
    				DisableSquares();
    				getWinner();
    				m_intWinCount += 1;
    			}
        	}   	    		
    	}
        catch(Exception expError)
        { 
            CUtilities.WriteLog(expError);
        }    			    			
    }
	
	// -----------------------------------------------------------------------------
    // Name:     rdoComputer_OnClick
    // Abstract: Disable radio buttons if computer plays first 
    // -----------------------------------------------------------------------------
    private void rdoComputer_OnClick()
    {    	    	
    	try
        { 
    		m_blnPlayerX = false;
    		m_rdoPlayer.setEnabled(false);
    		
    		// Beginner game
    		if(m_rdoBeginner.isSelected())
    		{
    			m_rdoIntermediate.setEnabled(false);
    			m_rdoExpert.setEnabled(false);    			    			
    		}    		
    		// Intermediate game
    		else if(m_rdoIntermediate.isSelected())
    		{
    			m_rdoBeginner.setEnabled(false);
    			m_rdoExpert.setEnabled(false);    			    			
    		}    		
    		// Expert game
    		else if(m_rdoExpert.isSelected())
    		{
    			m_rdoBeginner.setEnabled(false);
    			m_rdoIntermediate.setEnabled(false);   			
    		}
        }
        catch(Exception expError)
        { 
            CUtilities.WriteLog(expError);
        }    
    }
    
    // -----------------------------------------------------------------------------
    // Name: 	 ComputerFirst
    // Abstract: Select appropriate game based upon skill-level selected
    // -----------------------------------------------------------------------------
    private void ComputerFirst()
    {
    	try
    	{    		
			     if(m_rdoBeginner.isSelected())     ComputerFirstBeginnerGame();
			else if(m_rdoIntermediate.isSelected()) ComputerFirstIntermediateGame();
			else if(m_rdoExpert.isSelected())       ComputerFirstExpertGame();
    	}
	    catch(Exception expError)
	    {
	        CUtilities.WriteLog(expError);
	    }
    }            
               
    // -----------------------------------------------------------------------------
    // Name:     ComputerFirstBeginnerGame
    // Abstract: Computer moves for a beginner game when the computer plays first. 
    // -----------------------------------------------------------------------------
    private void ComputerFirstBeginnerGame()
    {
        try
        {        	 
        	if(m_intMoveCount >= 5 && isWin()) 
        	{
        		DisableSquares();
        		getWinner();
        	}
        	else
        	{
        		SelectRandom(); 
	            m_intMoveCount += 1;
	        	 
	        	if(m_intMoveCount >= 5 && isWin())
	        	{	    			
	        		DisableSquares();
	        		getWinner();
	    		}
        	}
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    }
    
    // -----------------------------------------------------------------------------
    // Name:     ComputerFirstIntermediateGame
    // Abstract: Computer moves for an intermediate game when the computer plays first.
    // -----------------------------------------------------------------------------
    private void ComputerFirstIntermediateGame()
    {
        try
        {        	
        	if(m_intMoveCount == 0)
        	{
        		MakeMove(m_abtnSquares[4]);
        		m_intMoveCount += 1;        		
        	}
        	else
        	{        	        	 
	        	if(m_intMoveCount >= 5 && isWin()) 
	        	{
	        		DisableSquares();
	        		getWinner();  
	        	}
	        	else
	        	{        		   				        		    			
	        		ComputerFirstIntermediateMove();
	        		m_intMoveCount += 1;
	        		 
		        	if(m_intMoveCount >= 5 && isWin())
		        	{	    			
		        		DisableSquares();
		        		getWinner(); 
		    		} 	        	
	        	} 
        	}
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    }
	
	// -----------------------------------------------------------------------------
    // Name:     ComputerFirstIntermediateMove
    // Abstract: Computer moves for intermediate game when computer plays first 
    // -----------------------------------------------------------------------------
    private void ComputerFirstIntermediateMove()
    {    	    	
    	try
        {    		
    		JButton btnMove = null;
    		int intMove = GetRandom();
    		
    		if(m_intMoveCount <= 3)
    		{    			
    			// Select adjacent corner if available or random move if not
    			if(!SelectAdjacentCorner()) MakeMove(m_abtnSquares[intMove]);           
    		}
    		else
    		{    			    			
    			if(FindWinOrBlock("X") != null)
    			{    			
    				btnMove = FindWinOrBlock("X");
    				MakeMove(btnMove);    				
    			}    			    			                 
    			else SelectFirstAvailableSquare();
    		}    		
        }    
	    catch(Exception expError)
	    {
	        // Log error 
	        CUtilities.WriteLog(expError);
	    }
    }
    
    // -----------------------------------------------------------------------------
    // Name:     ComputerFirstExpertGame
    // Abstract: Computer moves for expert game when the computer plays first.
    // -----------------------------------------------------------------------------
    private void ComputerFirstExpertGame()
    {
        try
        {
        	if(m_intMoveCount == 0)
        	{
	        	MakeMove(m_abtnSquares[4]);
	    		m_intMoveCount += 1;
        	}
        	else
        	{ 
	        	if(m_intMoveCount >= 5 && isWin()) 
	        	{
	        		DisableSquares();
	        		getWinner();  
	        	}
	        	else
	        	{        		   				        		    			
	        		ComputerFirstExpertMove();
	        		m_intMoveCount += 1;
	        		 
		        	if(m_intMoveCount >= 5 && isWin())
		        	{	    			
		        		DisableSquares();
		        		getWinner(); 
		    		} 	        	
	        	}
        	}
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    }
	
	// -----------------------------------------------------------------------------
    // Name:     ComputerFirstExpertMove
    // Abstract: Computer moves for expert game when computer plays first 
    // -----------------------------------------------------------------------------
    private void ComputerFirstExpertMove()
    {    	    	
    	try
        {    		
    		JButton btnMove = null; 
    		
    		if(m_intMoveCount <= 3)
    		{	    		
    			// Select adjacent corner if available
    			if(!SelectAdjacentCorner())	SelectFirstAvailableSquare();	    		
    		}
    		else
    		{    
    			// Find win
    			if(FindWinOrBlock("X") != null)
    			{    			
    				btnMove = FindWinOrBlock("X"); 
    				MakeMove(btnMove);    				
    			}    			
    			// Find block
    			else if(FindWinOrBlock("O") != null)
    			{  
    				btnMove = FindWinOrBlock("O");    				 
    				MakeMove(btnMove);
    			}    			    			    			
    			else SelectRandom();
    		}    		
        }    
	    catch(Exception expError)
	    {
	        // Log error 
	        CUtilities.WriteLog(expError);
	    }
    }

    // -----------------------------------------------------------------------------
    // Name:     GetRandom
    // Abstract: Get a random index number from an array
    // -----------------------------------------------------------------------------
    private int GetRandom()    
    {
    	int result = 0;
    	
    	try
        { 
	        int[] SquareNumber = {0,1,2,3,4,5,6,7,8};
	    	result = new Random().nextInt(SquareNumber.length);
        }
        catch(Exception expError)
        {
            CUtilities.WriteLog(expError);
        }        
    	return result;
    }
    
    // -----------------------------------------------------------------------------
    // Name:     SelectRandom
    // Abstract: Select a random button
    // -----------------------------------------------------------------------------
    private void SelectRandom() 
    {
    	try
        { 
    		int index = 0;
    		int SelectedIndex = 0;      		
    		while(index < m_abtnSquares.length)
    		{
    			SelectedIndex = GetRandom();
    			if(m_abtnSquares[SelectedIndex].getText() == "")
    			{
    				if(!m_blnPlayerX)
    				{
    					m_abtnSquares[SelectedIndex].setText("X");        			
            			m_abtnSquares[SelectedIndex].setEnabled(false);  
    				}
    				else
    				{
    					m_abtnSquares[SelectedIndex].setText("O");        			
            			m_abtnSquares[SelectedIndex].setEnabled(false);  
    				}
    				break;
    			}    			
    			index += 1;
    		}
        }
        catch(Exception expError)
        {
            CUtilities.WriteLog(expError);
        }
    }
    
    // -----------------------------------------------------------------------------
    // Name:     SelectFirstAvailableSquare
    // Abstract: Select any square still available
    // -----------------------------------------------------------------------------
    private void SelectFirstAvailableSquare()
    {    	  	      	
    	try
        { 
    		int index = 0;    		
    		for(index = 0; index < m_abtnSquares.length; index += 1)
    		{     			
    			if(m_abtnSquares[index].getText() == "")
    			{    				 
    				if(!m_blnPlayerX)
    				{
    					m_abtnSquares[index].setText("X");        			
    					m_abtnSquares[index].setEnabled(false);
    				}
    				else
    				{
    					m_abtnSquares[index].setText("O");        			
    					m_abtnSquares[index].setEnabled(false);
    				}
        			break;        			
    			}
    		}    		    		
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    }
    
    // -----------------------------------------------------------------------------
    // Name:     MakeMove
    // Abstract: Select a specific square
    // -----------------------------------------------------------------------------
    private void MakeMove(JButton btnTarget)
    {   	    	
    	try
        {        	        	        	        	
    		if(!m_blnPlayerX)
    		{
    			btnTarget.setText("X");
    			btnTarget.setEnabled(false); 
    		}
    		else
    		{
    			btnTarget.setText("O");
    			btnTarget.setEnabled(false); 
    		}
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    }
        
    // -----------------------------------------------------------------------------
    // Name:     SelectAdjacentCorner
    // Abstract: A specific set of moves when X occupies the middle square
    // -----------------------------------------------------------------------------
    private boolean SelectAdjacentCorner()
    {    	    	
    	boolean blnResult = false;	    
    	try
	    {    				
			// If player selects a corner square for first move
			if(m_intMoveCount <= 3)
			{	    		
	    		// Select an adjacent corner to the player's selection
				if(m_abtnSquares[8].getText() == "O" &&  m_abtnSquares[4].getText() == "X" && m_abtnSquares[2].getText() == "")
	    		{
	    			MakeMove(m_abtnSquares[2]);
	    			blnResult = true;	
	    		}
	    		else if(m_abtnSquares[0].getText() == "O" && m_abtnSquares[4].getText() == "X" && m_abtnSquares[6].getText() == "")       
	    		{
	    			MakeMove(m_abtnSquares[6]);
	    			blnResult = true;
	    		}
	    		else if(m_abtnSquares[2].getText() == "O" && m_abtnSquares[4].getText() == "X" && m_abtnSquares[8].getText() == "")       
	    		{
	    			MakeMove(m_abtnSquares[8]);
	    			blnResult = true;
	    		}
	    		else if(m_abtnSquares[6].getText() == "O" && m_abtnSquares[4].getText() == "X" && m_abtnSquares[0].getText() == "")       
	    		{
	    			MakeMove(m_abtnSquares[0]);
	    			blnResult = true;
	    		}          
			}			
	    }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    	return  blnResult;
    }
            
    // -----------------------------------------------------------------------------
    // Name:     FindWinOrBlock
    // Abstract: Find winning or blocking moves
    // -----------------------------------------------------------------------------
    private JButton FindWinOrBlock(String strToFind)
    {
    	JButton btnToFind = null;    	    	
    	try
        {        	        	        	        	    	
    		if(WinOrBlockRow(strToFind) != null)
    		{
    			btnToFind = WinOrBlockRow(strToFind);
    		}
    		else if(WinOrBlockColumn(strToFind) != null)
    		{
    			btnToFind = WinOrBlockColumn(strToFind);
    		}
    		else if(WinOrBlockDiagonal(strToFind) != null)
    		{
    			btnToFind = WinOrBlockDiagonal(strToFind);
    		}    		   		    		
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    	return btnToFind;
    }
    
    // -----------------------------------------------------------------------------
    // Name:     WinOrBlockRow
    // Abstract: Find winning or blocking moves along game rows
    // -----------------------------------------------------------------------------
    private JButton WinOrBlockRow(String strToFind)
    {
    	JButton btnToFind = null;    	    	
    	try
        {    		
    		if(m_abtnSquares[0].getText() == strToFind && m_abtnSquares[1].getText() == strToFind && m_abtnSquares[2].getText() == "")
    		{
    			btnToFind = m_abtnSquares[2];
    		}	
    		else if(m_abtnSquares[1].getText() == strToFind && m_abtnSquares[2].getText() == strToFind && m_abtnSquares[0].getText() == "")
    		{
    			btnToFind = m_abtnSquares[0];
    		}	
    		else if(m_abtnSquares[2].getText() == strToFind && m_abtnSquares[0].getText() == strToFind && m_abtnSquares[1].getText() == "")
    		{
    			btnToFind = m_abtnSquares[1];
    		}	
    		else if(m_abtnSquares[3].getText() == strToFind && m_abtnSquares[4].getText() == strToFind && m_abtnSquares[5].getText() == "")
    		{
    			btnToFind = m_abtnSquares[5];
    		}	
    		else if(m_abtnSquares[4].getText() == strToFind && m_abtnSquares[5].getText() == strToFind && m_abtnSquares[3].getText() == "")
    		{
    			btnToFind = m_abtnSquares[3];
    		}	
    		else if(m_abtnSquares[5].getText() == strToFind && m_abtnSquares[3].getText() == strToFind && m_abtnSquares[4].getText() == "")
    		{
    			btnToFind = m_abtnSquares[4];
    		}	
    		else if(m_abtnSquares[6].getText() == strToFind && m_abtnSquares[7].getText() == strToFind && m_abtnSquares[8].getText() == "")
    		{
    			btnToFind = m_abtnSquares[8];
    		}	
    		else if(m_abtnSquares[7].getText() == strToFind && m_abtnSquares[8].getText() == strToFind && m_abtnSquares[6].getText() == "")
    		{
    			btnToFind = m_abtnSquares[6];
    		}			
    		else if(m_abtnSquares[6].getText() == strToFind && m_abtnSquares[8].getText() == strToFind && m_abtnSquares[7].getText() == "")
    		{
    			btnToFind = m_abtnSquares[7];    		
    		}    		
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    	return btnToFind;
    }
    
    // -----------------------------------------------------------------------------
    // Name:     WinOrBlockColumn
    // Abstract: Find winning or blocking moves along game columns
    // -----------------------------------------------------------------------------
    private JButton WinOrBlockColumn(String strToFind)
    {
    	JButton btnToFind = null;    	    	
    	try
        {
    		if(m_abtnSquares[0].getText() == strToFind && m_abtnSquares[3].getText() == strToFind && m_abtnSquares[6].getText() == "")
    		{
    			btnToFind = m_abtnSquares[6];    		
    		}
			else if(m_abtnSquares[3].getText() == strToFind && m_abtnSquares[6].getText() == strToFind && m_abtnSquares[0].getText() == "")
    		{
    			btnToFind = m_abtnSquares[0];    		
    		}
			else if(m_abtnSquares[6].getText() == strToFind && m_abtnSquares[0].getText() == strToFind && m_abtnSquares[3].getText() == "")
    		{
    			btnToFind = m_abtnSquares[3];    		
    		}
			else if(m_abtnSquares[1].getText() == strToFind && m_abtnSquares[4].getText() == strToFind && m_abtnSquares[7].getText() == "")
    		{
    			btnToFind = m_abtnSquares[7];    		
    		}
			else if(m_abtnSquares[4].getText() == strToFind && m_abtnSquares[7].getText() == strToFind && m_abtnSquares[1].getText() == "")
    		{
    			btnToFind = m_abtnSquares[1];    		
    		}
			else if(m_abtnSquares[7].getText() == strToFind && m_abtnSquares[1].getText() == strToFind && m_abtnSquares[4].getText() == "")
    		{
    			btnToFind = m_abtnSquares[4];    		
    		}
			else if(m_abtnSquares[2].getText() == strToFind && m_abtnSquares[5].getText() == strToFind && m_abtnSquares[8].getText() == "")
    		{
    			btnToFind = m_abtnSquares[8];    		
    		}    
			else if(m_abtnSquares[5].getText() == strToFind && m_abtnSquares[8].getText() == strToFind && m_abtnSquares[2].getText() == "")
    		{
    			btnToFind = m_abtnSquares[2];    		
    		}    
			else if(m_abtnSquares[8].getText() == strToFind && m_abtnSquares[2].getText() == strToFind && m_abtnSquares[5].getText() == "")
    		{
    			btnToFind = m_abtnSquares[5];    		
    		}       		
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    	return btnToFind;
    }
    
    // -----------------------------------------------------------------------------
    // Name:     WinOrBlockDiagonal
    // Abstract: Find winning or blocking moves along game diagonals
    // -----------------------------------------------------------------------------
    private JButton WinOrBlockDiagonal(String strToFind)
    {
    	JButton btnToFind = null;    	    	
    	try
        {
    		if(m_abtnSquares[0].getText() == strToFind && m_abtnSquares[4].getText() == strToFind && m_abtnSquares[8].getText() == "")
    		{
    			btnToFind = m_abtnSquares[8];    		
    		}    
			else if(m_abtnSquares[4].getText() == strToFind && m_abtnSquares[8].getText() == strToFind && m_abtnSquares[0].getText() == "")
    		{
    			btnToFind = m_abtnSquares[0];    		
    		}    
			else if(m_abtnSquares[8].getText() == strToFind && m_abtnSquares[0].getText() == strToFind && m_abtnSquares[4].getText() == "")
    		{
    			btnToFind = m_abtnSquares[4];    		
    		}    
			else if(m_abtnSquares[2].getText() == strToFind && m_abtnSquares[4].getText() == strToFind && m_abtnSquares[6].getText() == "")
    		{
    			btnToFind = m_abtnSquares[6];    		
    		}    
			else if(m_abtnSquares[4].getText() == strToFind && m_abtnSquares[6].getText() == strToFind && m_abtnSquares[2].getText() == "")
    		{
    			btnToFind = m_abtnSquares[2];    		
    		}    
			else if(m_abtnSquares[6].getText() == strToFind && m_abtnSquares[2].getText() == strToFind && m_abtnSquares[4].getText() == "")
    		{
    			btnToFind = m_abtnSquares[4];    		
    		}    
        }    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    	return btnToFind;
    }      
    
    // -----------------------------------------------------------------------------
    // Name: 	 isWin
    // Abstract: Test if the game has been won or is a draw
    // -----------------------------------------------------------------------------
    private boolean isWin()
    {
    	boolean blnIsWinner = false;   	    	    	
    	try
    	{      		        		
    		// Test for win
    		if(isWinHorizontal() || isWinVertical() || isWinDiagonal())
    		{
    			blnIsWinner = true; 
    		}    		    		
    		// or draw
    		else if(m_intMoveCount >= 9 && m_strWinChar == "")
    		{
    			//Display result
    			DisableSquares();
        		JOptionPane.showMessageDialog(null, "Cat's Game!", "GAME OVER",  JOptionPane.INFORMATION_MESSAGE);    	
    		}    	
    	}    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }    	
    	return blnIsWinner;
    }
    
    // -----------------------------------------------------------------------------
    // Name: 	 isWinHorizontal
    // Abstract: Test for a horizontal win
    // -----------------------------------------------------------------------------    
    private boolean isWinHorizontal()
    {
    	boolean blnIsWinner = false; 	
		try
		{																											
			if(m_abtnSquares[0].getText() == m_abtnSquares[1].getText() &&  m_abtnSquares[1].getText() == m_abtnSquares[2].getText()
			   && m_abtnSquares[0].getText() != "")
			{    				
				blnIsWinner = true;
				if(m_abtnSquares[0].getText() =="X") m_strWinChar = "X";
				else m_strWinChar = "O";    				
			}
			else if(m_abtnSquares[3].getText() == m_abtnSquares[4].getText() &&  m_abtnSquares[4].getText() == m_abtnSquares[5].getText()
			   && m_abtnSquares[3].getText() != "")
			{
				blnIsWinner = true;
				if(m_abtnSquares[3].getText() =="X") m_strWinChar = "X";
				else m_strWinChar = "O";    	
			}
			else if(m_abtnSquares[6].getText() == m_abtnSquares[7].getText() &&  m_abtnSquares[7].getText() == m_abtnSquares[8].getText()
			   && m_abtnSquares[6].getText() != "")
			{
				blnIsWinner = true;
				if(m_abtnSquares[6].getText() =="X") m_strWinChar = "X";
				else m_strWinChar = "O";    			
			}
		}    
	    catch(Exception expError)
	    {
	        CUtilities.WriteLog(expError);
	    }	
		return blnIsWinner;
	}
    
	// -----------------------------------------------------------------------------
	// Name: 	 isWinVertical
	// Abstract: Test for a vertical win
	// -----------------------------------------------------------------------------    
	private boolean isWinVertical()
	{
	 	boolean blnIsWinner = false; 
	 	try
	 	{		    		    
	 		int Index = 0;	 		
	 		for(Index = 0; Index < 3; Index += 1)
	 		{
	 			if(m_abtnSquares[Index].getText() != "")
	 			{
	 				if(m_abtnSquares[Index].getText() == m_abtnSquares[Index + 3].getText() &&
	 				   m_abtnSquares[Index + 3].getText() == m_abtnSquares[Index + 6].getText())
	 				{
	 					if(m_abtnSquares[Index].getText() =="X") m_strWinChar = "X";
	 					else m_strWinChar = "O";
	 					blnIsWinner = true;
	 					break;
	 				}
	 			}
	 		}				
	 	}    
	 	catch(Exception expError)
	 	{ 
	 		CUtilities.WriteLog(expError);
	 	}	
	 	return blnIsWinner;
	}
    
    // -----------------------------------------------------------------------------
    // Name: 	 isWinDiagonal
    // Abstract: Test for a diagonal win
    // -----------------------------------------------------------------------------    
    private boolean isWinDiagonal()
    {
    	boolean blnIsWinner = false; 	
		try
		{			
			if(m_abtnSquares[0].getText() == m_abtnSquares[4].getText() &&  m_abtnSquares[4].getText() == m_abtnSquares[8].getText()
			   && m_abtnSquares[0].getText() != "")
			{
				blnIsWinner = true;
				if(m_abtnSquares[0].getText() =="X") m_strWinChar = "X";
				else m_strWinChar = "O";    			
			}
			else if(m_abtnSquares[2].getText() == m_abtnSquares[4].getText() &&  m_abtnSquares[4].getText() == m_abtnSquares[6].getText()
	    	   && m_abtnSquares[2].getText() != "")
			{
				blnIsWinner = true;
				if(m_abtnSquares[2].getText() =="X") m_strWinChar = "X";
				else m_strWinChar = "O";    	
			}			
		}    
	    catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }	
		return blnIsWinner;
	}
                
    // -----------------------------------------------------------------------------
    // Name: 	 getWinner
    // Abstract: Display the winner of a game 
    // -----------------------------------------------------------------------------
    private void getWinner()
    {    	
    	try
    	{ 
    		String strWinner = "";
    		
    		// Computer-first games
    		if(!m_blnPlayerX)
    		{
	    		if(m_strWinChar == "O")	
	    		{
	    			strWinner = "Player Wins";
	    		}
	    		else if(m_strWinChar == "X")
	    		{
	    			strWinner = "Computer Wins";
	    		}
    		}
			// Player-first games
    		else
    		{
    			if(m_strWinChar == "X")	
	    		{
	    			strWinner = "Player Wins";
	    		}
	    		else if(m_strWinChar == "O")
	    		{
	    			strWinner = "Computer Wins";
	    		}
    		}   		
    		//Display result
    		JOptionPane.showMessageDialog(null, strWinner, "GAME OVER",  JOptionPane.INFORMATION_MESSAGE);  
    		DisableSquares();
    	}
    	catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }    	
    }
    
    // -----------------------------------------------------------------------------
    // Name: 	 DisableSquares
    // Abstract: Disable all game squares after a win or draw 
    // -----------------------------------------------------------------------------
    private void DisableSquares()
    {    	    	
    	try
    	{
    		int index = 0;
    		for(index = 0; index < m_abtnSquares.length; index += 1)
    		{
    			m_abtnSquares[index].setEnabled(false);
    		}    		
    	}    
		catch(Exception expError)
	    { 
	        CUtilities.WriteLog(expError);
	    }
    }
            
    // -----------------------------------------------------------------------------
    // Name:     Reset
    // Abstract: Reset the game board
    // -----------------------------------------------------------------------------
    private void Reset()
    {
        try
        {        	   	        
        	m_blnPlayerX   = true; 
        	m_intMoveCount = 0;
        	m_strWinChar   = "";
        	m_intWinCount  = 0;        	        	       
        	m_rdoBeginner.setSelected(true);
        	m_rdoBeginner.setEnabled(true);
        	m_rdoIntermediate.setEnabled(true);
        	m_rdoExpert.setEnabled(true);        	
        	m_rdoPlayer.setSelected(true);
        	m_rdoPlayer.setEnabled(true);
        	m_rdoComputer.setEnabled(true);
        	
        	// Clear and enable all squares                  	
        	for(int index = 0; index < m_abtnSquares.length; index += 1)
    		{
        		m_abtnSquares[index].setText("");
        		m_abtnSquares[index].setEnabled(true);        		        			
    		}        	
        }
        catch(Exception expError)
        {
            CUtilities.WriteLog(expError);
        }
     } 
        
    // -----------------------------------------------------------------------------
    // Name:     Exit
    // Abstract: Exit program
    // -----------------------------------------------------------------------------
    private void Exit()
    {
        try
        {
        	Reset();
        	this.setVisible(false);
            this.dispose(); 
        }
        catch(Exception expError)
        { 
            CUtilities.WriteLog(expError);
        }
    }
}


