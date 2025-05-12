package fr.epita.quiz.tests.spring;


import fr.epita.quiz.services.AnswerJDBCDAO;
import fr.epita.quiz.services.QuestionJDBCDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
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

    @Bean
    public String getHelloFromSpring(){
        return "test from spring!";
    }

    @Bean("answerDAO")
    public AnswerJDBCDAO answerJDBCDAO(
            @Autowired
            @Qualifier("mainDatasource")
            DataSource dataSource) throws SQLException {
        return new AnswerJDBCDAO(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("fr.epita.quiz.datamodel");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty("hibernate.show_sql", "true");

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
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

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

}
