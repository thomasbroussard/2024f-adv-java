package fr.epita.quiz.tests.dao;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.QuestionJDBCDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestQuestionJDBCDAO {


    private QuestionJDBCDAO dao;

    @BeforeEach
    public void setup() throws SQLException {
        //this.dao = new QuestionJDBCDAO();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        this.dao.deleteAllQuestions();
    }



    @Test
    public void testCreate() throws SQLException {
        // given
        Question question = new Question();
        question.setTitle("What is polymorphism?");

        // when
        dao.createQuestion(question);
        List<Question> allQuestions = dao.readAllQuestions();

        // then
        assertEquals(1, allQuestions.size());
        assertEquals("What is polymorphism?", allQuestions.get(0).getTitle());
    }


    @Test
    public void testUpdate() throws SQLException {
        // given
        Question question = new Question();
        question.setTitle("Old Title");
        dao.createQuestion(question);

        // when
        dao.updateQuestionTitle("Old Title", "New Title");
        List<Question> questions = dao.readAllQuestions();

        // then
        assertEquals(1, questions.size());
        assertEquals("New Title", questions.get(0).getTitle());
    }




}
