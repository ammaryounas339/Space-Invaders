/*
      author @ammar
 */
package Game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shield extends GameObject {

  private  int width;
    private int height;

    // Constructor for Shield objects
    public Shield(int xPosition, int yPosition, int width, int height, Color color) {
        super(xPosition, yPosition, color);
        this.width = width;
        this.height = height;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Used to draw shield objects
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosition(), this.getYPosition(), 90, 10);
    }

    // Used to get the hit box of a shield object
    @Override
    public Rectangle getBounds() {
        Rectangle shieldHitbox = new Rectangle(this.getXPosition(), this.getYPosition(), 90, 10);
        return shieldHitbox;
    }
}
