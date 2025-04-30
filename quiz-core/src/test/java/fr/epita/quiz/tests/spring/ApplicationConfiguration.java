package fr.epita.quiz.tests.spring;


import fr.epita.quiz.services.QuestionJDBCDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class ApplicationConfiguration {


    @Bean("questionJdbcDao")
    public QuestionJDBCDAO questionJDBCDAO() throws SQLException {
        return new QuestionJDBCDAO();
    }


}
