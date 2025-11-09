/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Image;

/**
 *
 * @author bli
 */
public class Ball extends Sprite {

    // These fields represent the velocity of the ball in the x and y directions. They control how the ball moves on the screen.
    private double velx;
    private double vely;

    public Ball(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velx = 1;
        vely = 1;
    }
    
    // The moveX method updates the ball's x-coordinate by adding the x-velocity (velx). 
    // It checks if the ball hits the left or right boundary of the screen. 
    // If it does, it calls invertVelX to reverse the x-velocity, making the ball bounce back.
    public void moveX() {
        x += velx;
        if (x + width >= 800 || x <= 0) {
            invertVelX();
        }
    }

    // The moveY method updates the ball's y-coordinate by adding the y-velocity (vely).
    // It checks if the ball hits the top boundary of the screen. 
    // If it does, it calls invertVelY to reverse the y-velocity. 
    // If the ball hits the bottom boundary, it returns false, indicating the game is over or the ball is out of play. Otherwise, it returns true.
    public boolean moveY() {
        y += vely;
        if (y <= 0) {
            invertVelY();
        }
        if (y >= 600) {
            return false;
        }
        return true;
    }

    public void invertVelX() {
        velx = -velx;
    }

    public void invertVelY() {
        vely = -vely;

    }

}
