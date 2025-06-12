package fr.epita.quiz.tests.hibernate;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.tests.spring.ApplicationConfiguration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Transactional
public class TestAnswerWithJPA {

    @PersistenceContext
    EntityManager em;


    @Test
    public void test() {
        //given
        Question question = new Question();
        question.setTitle("Test Question");
        em.persist(question);

        Choice choice = new Choice();
        choice.setQuestion(question);
        em.persist(choice);

        Student student = new Student();
        student.setName("John");
        em.persist(student);

        //when
        Answer answer = new Answer();
        answer.setChoice(choice);
        em.persist(answer);


        //implement the verification code ("then")




    }
}
