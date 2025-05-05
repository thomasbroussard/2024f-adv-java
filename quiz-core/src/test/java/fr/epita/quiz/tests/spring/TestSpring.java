package fr.epita.quiz.tests.spring;

import fr.epita.quiz.services.QuestionJDBCDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class TestSpring {

    @Autowired
    @Qualifier("questionJdbcDao")
    private QuestionJDBCDAO dao;

    @Autowired
    private DataSource mainDatasource;

    @Test
    public void testDatasource(){
        Assertions.assertNotNull(mainDatasource);
    }

    @Test
    public void test(){
        Assertions.assertNotNull(this.dao);
    }


}
