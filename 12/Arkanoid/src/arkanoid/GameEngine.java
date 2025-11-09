/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author bli
 */
public class GameEngine extends JPanel {

    private final int FPS = 240;
    private final int PADDLE_Y = 550;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 20;
    private final int PADDLE_MOVEMENT = 2;
    private final int BALL_RADIUS = 20;

    private boolean paused = false;
    private Image background;
    private int levelNum = 0;
    private Level level;
    private Ball ball;
    private Paddle paddle;
    private Timer newFrameTimer; // newFrameTimer is a Timer that triggers game updates.

    public GameEngine() {
        super();
        background = new ImageIcon("data/images/background.jpg").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paddle.setVelx(-PADDLE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paddle.setVelx(PADDLE_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paddle.setVelx(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    public void restart() {
        try {
            level = new Level("data/levels/level0" + levelNum + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Creates new Paddle and Ball objects with their initial positions and images.
        Image paddleImage = new ImageIcon("data/images/paddle.png").getImage();
        paddle = new Paddle(350, PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT, paddleImage);
        Image ballImage = new ImageIcon("data/images/ball.png").getImage();
        ball = new Ball(400, 300, BALL_RADIUS, BALL_RADIUS, ballImage);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        // Draws the background, level, paddle, and ball.
        grphcs.drawImage(background, 0, 0, 800, 600, null);
        level.draw(grphcs);
        paddle.draw(grphcs);
        ball.draw(grphcs);
    }

    // The NewFrameListener class handles game updates.
    class NewFrameListener implements ActionListener {

        // This method is called at regular intervals by a Timer to update the game's state.
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                // This method updates the ball’s horizontal position based on its current velocity.
                ball.moveX();
                // It checks if the ball has collided with any bricks in the level. 
                // If there is a collision, it inverts the ball's horizontal velocity, causing the ball to bounce off in the opposite direction.
                if (level.collides(ball)) {
                    ball.invertVelX();
                }
                // This method updates the ball’s vertical position. If moveY() returns false, it means the ball has gone off the bottom of the screen (missed by the paddle) 
                // causing the game to restart.
                if (!ball.moveY()) {
                    levelNum = 0;
                    restart();
                }
                // It again checks for collisions with bricks, this time considering the vertical movement.
                if (level.collides(ball)) {
                    ball.invertVelY();
                }
                // This checks if the ball has collided with the paddle. If so, the ball's vertical velocity is inverted to simulate a bounce off the paddle.
                if (paddle.collides(ball)) {
                    ball.invertVelY();
                }
                // This updates the paddle’s position based on its current velocity.
                paddle.move();
            }
            if (level.isOver()) {
                levelNum = (levelNum+1) % 2;
                restart();
            }
            repaint();
        }

    }
}
