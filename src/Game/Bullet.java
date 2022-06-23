/*
      author @ammar
 */
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bullet extends MovingGameObject {

   private  int diameter;
 

    // Constructor for bullet
    public Bullet(int xPosition, int yPosition, int diameter, Color color) {
        super(xPosition, yPosition, 0, 0, color);
        this.diameter = diameter;
    }

    // Gets the diameter of the bullet
    public int getDiameter() {
        return diameter;
    }

    // Used to draw the bullet
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosition(), this.getYPosition(), 3, 15);

    }
    //used to get hitbox of bullet
    @Override
    public Rectangle getBounds() {
        Rectangle bulletHitbox = new Rectangle(xPos, yPos, 3, 15);
        return bulletHitbox;
    }
}
