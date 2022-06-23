/*
      author @ammar
 */
package Game;

import Controller.KeyboardController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Ship extends ControlledGameObject {

    ImageIcon ship = new ImageIcon("images/shipSkin.gif");
    ImageIcon lifeCounterShip = new ImageIcon("images/shipSkinSmall.gif");
    ImageIcon explosion = new ImageIcon("images/explosion2.gif");

    // Constructor for all ship objects
    public Ship(int xPosition, int yPosition, Color color, KeyboardController control) {
        super(xPosition, yPosition, color, control);
    
    }

   

    // Draw ships for life counter
    public void lifeDraw(Graphics g) {

        lifeCounterShip.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    }

    // Draw player controlled ship
    @Override
    public void draw(Graphics g) {
        ship.paintIcon(null, g, this.getXPosition(), this.getYPosition());

    }
    //draws explosion when 3 lives are lost 
    public  void drawExplosion(Graphics g){
        explosion.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    }

    // Gets the hit box for all ship objects
    @Override
    public Rectangle getBounds() {
        Rectangle shipHitbox = new Rectangle(this.getXPosition(), this.getYPosition(), 50, 50);
        return shipHitbox;
    }

    // Used to move all ship objects
    @Override
    public void move() {
        // Left arrow key press
        int k = control.getKey();
        if ( k == KeyEvent.VK_LEFT) {
            xPos -= 10;
        }
        // Right arrow key press
        if ( k == KeyEvent.VK_RIGHT) {
            xPos += 10;
        }
        
        // Move from edge to edge without stopping
        if (xPos > 800) {
            xPos = 0;
        }
        if (xPos < 0) {
            xPos = 800;
        }
    }
}
