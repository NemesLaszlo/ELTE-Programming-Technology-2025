package databases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Databases {
    
public static void main(String[] args) {
    try {
        HighScores highScores = new HighScores(5); // Max 5 high scores

        highScores.putHighScore("Alice", 150);
        highScores.putHighScore("Bob", 200);

        ArrayList<HighScore> scores = highScores.getHighScores();
        scores.forEach(System.out::println);

        highScores.close();
        } catch (SQLException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
