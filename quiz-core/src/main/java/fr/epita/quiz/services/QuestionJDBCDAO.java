package fr.epita.quiz.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.epita.quiz.datamodel.Question;

public class QuestionJDBCDAO {



    public QuestionJDBCDAO() throws SQLException {
        getConnection();
        initializeDatabase();
    }

    private static Connection getConnection() throws SQLException {
       return DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
    }

    private void initializeDatabase() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS questions ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "title VARCHAR(255) NOT NULL)";
        try (PreparedStatement stmt = getConnection().prepareStatement(createTableSQL)) {
            stmt.execute();
        }
    }

    // CREATE
    public void createQuestion(Question question) throws SQLException {
        String sql = "INSERT INTO questions (title) VALUES (?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, question.getTitle());
            pstmt.executeUpdate();
        }
    }

    // READ
    public List<Question> readAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT title FROM questions";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Question q = new Question();
                q.setTitle(rs.getString("title"));
                questions.add(q);
            }
        }
        return questions;
    }

    // UPDATE (by title — just an example, since there's no id field in Question)
    public void updateQuestionTitle(String oldTitle, String newTitle) throws SQLException {
        String sql = "UPDATE questions SET title = ? WHERE title = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setString(2, oldTitle);
            pstmt.executeUpdate();
        }
    }

    // DELETE
    public void deleteAllQuestions() throws SQLException {
        String sql = "DELETE FROM questions";
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(sql);
        }
    }


}
