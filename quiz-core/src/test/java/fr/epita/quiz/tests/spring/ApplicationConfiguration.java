package fr.epita.quiz.tests.spring;


import fr.epita.quiz.services.QuestionJDBCDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ApplicationConfiguration {


    @Bean("questionJdbcDao")
    public QuestionJDBCDAO questionJDBCDAO(
            @Autowired
            @Qualifier("mainDatasource")
            DataSource dataSource) throws SQLException {
        return new QuestionJDBCDAO(dataSource);
    }


    @Bean("mainDatasource")
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource =
                new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        driverManagerDataSource.setPassword("");
        driverManagerDataSource.setUsername("test");
        return driverManagerDataSource;
    }
}
