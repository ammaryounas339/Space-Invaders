/*
      author @ammar
 */
package Game;

import java.awt.Color;
import java.awt.Rectangle;


public  abstract class GameObject implements Drawable {

    int xPos;
    int yPos;
    Color color;
    boolean isColliding;
    
    public GameObject(){};
    
    // Constuctor for any Game Object
    public GameObject(int xPosition, int yPosition, Color color) {
        this.xPos = xPosition;
        this.yPos = yPosition;
        this.color = color;
    }

    public  abstract  Rectangle getBounds();

    public int getXPosition() {
        return xPos;
    }


    public int getYPosition() {
        return yPos;
    }

    // Gets the color of any object
    public Color getColor() {
        return color;
    }


    public void setXPosition(int xPosition) {
        this.xPos = xPosition;
    }
 
    public void setYPosition(int yPosition) {
        this.yPos = yPosition;
    }

    // Sets the color of any object
    public void setColor(Color color) {
        this.color = color;
    }

    // Checks if the hitboxes of any two objects are intersecting
    public boolean isColliding(GameObject g1) {
        isColliding = g1.getBounds().intersects(this.getBounds());
        return isColliding;

    }

    //  if (bullet.isColliding(enemyList.get(index))) 
    //enemy.getBounds().intersects((bullet.getBounds())
    //enemyhitbox.intersects(bullethitbox)
    //r1.intersects(r2)
}
