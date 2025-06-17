package fr.epita.quiz.tests.dao;


import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.services.AnswerJDBCDAO;
import fr.epita.quiz.tests.spring.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class TestAnswerJDBCDAO {

    @Autowired
    AnswerJDBCDAO dao;

    @Autowired
    DataSource dataSource;

    @Test
    public void testConfiguration(){
        Assertions.assertNotNull(dao);
    }

    @Test
    public void testCreate() throws SQLException {
        //given
        Answer answer = new Answer();
        answer.setId(1);
        answer.setText("test");

        //when
        dao.createAnswer(answer);

        //then
        PreparedStatement preparedStatement = dataSource
                .getConnection()
                .prepareStatement("SELECT * from ANSWERS");
        ResultSet resultSet = preparedStatement.executeQuery();
        int instances = 0;
        while (resultSet.next()){
            instances++;
        }
        Assertions.assertEquals(instances, 1);
    }


}
