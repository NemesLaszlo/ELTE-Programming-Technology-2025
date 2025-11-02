package colorclicker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author pinter
 */
public class BoardGUI {
    
    // 2D array of buttons representing the game board
    private JButton[][] buttons;
    // Logical model of the board (handles game state, colors, etc.)
    private Board board;
    // Panel that contains the board grid
    private JPanel boardPanel;
    // Label that shows the elapsed time
    private JLabel timeLabel;
     // List of all points (cells) on the board
    private ArrayList<Point> points;

    // Random number generator for colors
    private Random random = new Random();
    private int clickNum = 0;
    private long startTime;
    private Timer timer;

    // Number of random cells that get colored per click
    private final int NUM_COLORED_FIELDS = 4;

    public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
        points = new ArrayList<>();
        
         // Set up a grid layout based on board size
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        
        // Create buttons for each cell in the grid
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                // Add listener to handle user clicks
                button.addActionListener(new ButtonListener(i, j));
                // Set button size
                button.setPreferredSize(new Dimension(80, 40));
                // Store and display button
                buttons[i][j] = button;
                boardPanel.add(button);
                // Store the (i,j) position in the list of points
                points.add(new Point(i, j));
            }
        }
        // Shuffle the list of points for random selection later
        Collections.shuffle(points);

        // Create a label to display elapsed time
        timeLabel = new JLabel(" ");
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        // Initialize a Swing Timer to update time label every 10ms
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(elapsedTime() + " ms");
            }
        });
        startTime = System.currentTimeMillis();
        timer.start();
    }

     /**
     * Calculates how much time has passed since the game started.
     * @return elapsed time in milliseconds
     */
    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

     /**
     * Updates a specific cell in the GUI to match its logical state in the Board.
     * @param x row index
     * @param y column index
     */
    public void refresh(int x, int y) {
        JButton button = buttons[x][y];
        Field field = board.get(x, y);
        button.setBackground(field.getColor());
        if (field.getColor() != null) {
            button.setText(String.valueOf(field.getNumber()));
        } else {
            button.setText("");
        }
    }

     /**
     * Returns the panel containing the board.
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
     /**
     * Inner class that listens to button clicks.
     * When a button is clicked, it colors that button and a few random others.
     */
    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Ignore clicks on already colored fields
            if (board.get(x, y).getColor() == null) {
                
                // Generate a random color
                Color color = new Color(random.nextInt(256),
                        random.nextInt(256), random.nextInt(256));
                
                // Set color and click number for the clicked cell
                board.get(x, y).setColor(color);
                board.get(x, y).setNumber(++clickNum);
                
                // Randomly color a few more uncolored cells
                for (int i = 0; i < NUM_COLORED_FIELDS;) {
                    // Removes and returns the last element of that list
                    // This ensures that each point is used only once — no duplicates
                    // It also gradually shrinks the list, so you won’t color the same cell twice
                    Point point = points.remove(points.size() - 1);
                    // System.out.println(point);
                    if (board.get(point).getColor() == null) {
                        board.get(point).setColor(color);
                        board.get(point).setNumber(clickNum);
                        // Updates the corresponding button on the screen
                        refresh(point.x, point.y);
                        i++;
                    }
                }
                // Refresh the clicked button visually
                refresh(x, y);
                // Check if all cells are colored (game over)
                if (board.isOver()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(boardPanel, "You have won in " + elapsedTime() + " ms.", "Congrats!",
                            JOptionPane.PLAIN_MESSAGE);
                }

            }
        }
    }

     /**
     * Returns the time label for display in the main window.
     */
    public JLabel getTimeLabel() {
        return timeLabel;
    }

}
