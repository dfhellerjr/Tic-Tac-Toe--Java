// ---------------------------------------------------------------------------------
//  Name:     Douglas Heller
//  Class:    Independent Work
//  Abstract: Create a Tic-Tac-Toe game using Java
//  The game is designed for a user to play against the computer. It allows the
//  a choice from three different skill-levels as well as whether the user or 
//  computer makes the first move.
// ---------------------------------------------------------------------------------


// ---------------------------------------------------------------------------------
// Imports
// ---------------------------------------------------------------------------------
import javax.swing.*;       


// ---------------------------------------------------------------------------------
// Name:     CTicTacToe
// Abstract: Create an instance of the main window (form) and make it visible
// ---------------------------------------------------------------------------------
class CTicTacToe
{
    // -----------------------------------------------------------------------------
    // Name:     main
    // Abstract: Program starts here
    // -----------------------------------------------------------------------------
    public static void main(String astrCommandLine[])
    {
        try
        {   
            // Declare & make an instance of FGameBoard 
            FGameBoard frmGameBoard = new FGameBoard();
            
            // Make it visible
            frmGameBoard.setVisible(true);
        }
        catch(Exception expError)
        {
            // Display Error Message (owner, message, title, buttons)
            JOptionPane.showMessageDialog(null, expError.toString(), 
                                          "CTicTacToee::main", JOptionPane.OK_OPTION );
        }
    }
}
