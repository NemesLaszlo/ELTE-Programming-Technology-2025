package databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

public class HighScores {

    private static final String INSERT_QUERY = "INSERT INTO HIGHSCORES (TIMESTAMP, NAME, SCORE) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM HIGHSCORES WHERE SCORE=?";
    private static final String SELECT_QUERY = "SELECT * FROM HIGHSCORES";

    private final int maxScores;
    private final Connection connection;

    /**
     * Constructor for HighScores.
     *
     * @param maxScores The maximum number of high scores to store.
     * @throws SQLException If a database error occurs.
     */
    public HighScores(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        this.connection = createLocalConnection();
    }

    /**
     * Establishes a connection to the local MySQL database.
     *
     * @return A Connection object.
     * @throws SQLException If the connection fails.
     */
    private Connection createLocalConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3333/database_name";
        Properties connectionProps = new Properties();
        connectionProps.put("user", "user_name");
        connectionProps.put("password", "user_password");
        connectionProps.put("serverTimezone", "UTC");

        return DriverManager.getConnection(dbURL, connectionProps);
    }

    /**
     * Retrieves the high scores sorted in descending order.
     *
     * @return A sorted list of HighScore objects.
     * @throws SQLException If the query fails.
     */
    public ArrayList<HighScore> getHighScores() throws SQLException {
        ArrayList<HighScore> highScores = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet results = stmt.executeQuery(SELECT_QUERY)) {
            while (results.next()) {
                String name = results.getString("NAME");
                int score = results.getInt("SCORE");
                highScores.add(new HighScore(name, score));
            }
        }

        // Sort high scores in descending order by score
        highScores.sort(Comparator.comparingInt(HighScore::getScore).reversed());
        return highScores;
    }

    /**
     * Adds a new high score if it qualifies as a top score.
     *
     * @param name  The player's name.
     * @param score The player's score.
     * @throws SQLException If the query fails.
     */
    public void putHighScore(String name, int score) throws SQLException {
        ArrayList<HighScore> highScores = getHighScores();

        if (highScores.size() < maxScores) {
            insertScore(name, score);
        } else {
            int leastScore = highScores.get(highScores.size() - 1).getScore();
            if (leastScore < score) {
                deleteScores(leastScore);
                insertScore(name, score);
            }
        }
    }

    /**
     * Inserts a high score into the database.
     *
     * @param name  The player's name.
     * @param score The player's score.
     * @throws SQLException If the query fails.
     */
    private void insertScore(String name, int score) throws SQLException {
        String timestamp = new Timestamp(System.currentTimeMillis()).toString();

        try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_QUERY)) {
            insertStatement.setTimestamp(1, Timestamp.valueOf(timestamp));
            insertStatement.setString(2, name);
            insertStatement.setInt(3, score);
            insertStatement.executeUpdate();
        }
    }

    /**
     * Deletes all records with the specified score.
     *
     * @param score The score to delete.
     * @throws SQLException If the query fails.
     */
    private void deleteScores(int score) throws SQLException {
        try (PreparedStatement deleteStatement = connection.prepareStatement(DELETE_QUERY)) {
            deleteStatement.setInt(1, score);
            deleteStatement.executeUpdate();
        }
    }

    /**
     * Closes the database connection when the object is no longer needed.
     *
     * @throws SQLException If an error occurs during closure.
     */
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}