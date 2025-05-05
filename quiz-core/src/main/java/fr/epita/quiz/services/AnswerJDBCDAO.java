package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Answer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerJDBCDAO {

    DataSource dataSource;

    public AnswerJDBCDAO(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initializeTable();
    }

    private void initializeTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS answers ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "text VARCHAR(255) NOT NULL)";

        try (   Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.execute();
        }
    }

    public void createAnswer(Answer answer){
        String sql = "INSERT INTO answers (text) VALUES (?)";

        try ( Connection connection = dataSource.getConnection();
                     PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, answer.getText());
            pstmt.executeUpdate();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

}
