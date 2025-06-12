package fr.epita.quiz.rest.config;

import fr.epita.quiz.services.QuestionJDBCDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class RestConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:mem:quizdb;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.h2.Driver");
        return dataSource;
    }

    @Bean
    public QuestionJDBCDAO questionJDBCDAO(DataSource dataSource) throws SQLException {
        return new QuestionJDBCDAO(dataSource);
    }
} 