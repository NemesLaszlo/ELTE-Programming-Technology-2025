/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * The Sprite class represents a game object with a position, size, and an image that can be drawn on the screen. It's commonly used in 2D games.
 * @author bli
 */
public class Sprite {
    /**
     * The coordinates of the top left corner of the sprite
     */
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public Sprite(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
    
    public void draw(Graphics g) {
        // The draw method takes a Graphics object as a parameter and uses it to draw the sprite's image at the specified position and size.
        g.drawImage(image, x, y, width, height, null);
    }
    
    /**
     * Returns true if this sprite collides with the other sprite.
     * The collides method checks if this sprite collides with another sprite. 
     * It creates Rectangle objects representing the bounding boxes of both sprites and checks if they intersect.
     * @param other
     * @return 
     */
    public boolean collides(Sprite other) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);        
        return rect.intersects(otherRect);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
