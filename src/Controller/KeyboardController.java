
package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//keyTyped // keyPressed //keyReleased
public class KeyboardController implements KeyListener
    
{
    public int key;
    
    public KeyboardController()
    {
        
    }
   
  
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
         key = ke.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
         key = 0;
    }
    public int getKey(){
      return key;
    }
    
}
