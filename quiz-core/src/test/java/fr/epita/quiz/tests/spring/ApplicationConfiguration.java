package fr.epita.quiz.tests.spring;


import fr.epita.quiz.services.AnswerJDBCDAO;
import fr.epita.quiz.services.QuestionJDBCDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class ApplicationConfiguration {


    @Bean("questionJdbcDao")
    public QuestionJDBCDAO questionJDBCDAO(
            @Autowired
            @Qualifier("mainDatasource")
            DataSource dataSource) throws SQLException {
        return new QuestionJDBCDAO(dataSource);
    }

    @Bean("answerDAO")
    public AnswerJDBCDAO answerJDBCDAO(
            @Autowired
            @Qualifier("mainDatasource")
            DataSource dataSource) throws SQLException {
        return new AnswerJDBCDAO(dataSource);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();
        properties.put("hbm2ddl.auto", "create");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("fr.epita.quiz.datamodel");
        return localSessionFactoryBean;
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
