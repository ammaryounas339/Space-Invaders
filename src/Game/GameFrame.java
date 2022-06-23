/*
      author @ammar
 */
package Game;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame
{
    public GamePanel game; 
    public GameFrame()
    {
        // Add text to title bar 
        super("Space Invaders ");
        //add image to title bar
        ImageIcon icon = new ImageIcon("images/logo.jpg");
        setIconImage(icon.getImage());
        // Make sure the program exits when the close button is clicked
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Create an instance of the Game class and turn on double buffering
        //  to ensure smooth animation
        game = new GamePanel();
        game.setDoubleBuffered(true);
    
        this.getContentPane().add(game); 
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);

        // Start the game
        game.start();  

    }
    
    public static void main(String[] args) 
    {
            new GameFrame();
              
    }
      
    }
       
                
    
        
        
    
