package fr.epita.quiz.tests.hibernate;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.tests.spring.ApplicationConfiguration;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Transactional()
public class TestChoicesWithJPA {

    @PersistenceContext
    EntityManager em;


    @Test
    public void test(){
        Question question = new Question();
        question.setTitle("Question 1");
        em.persist(question);

        Choice choice = new Choice();
        choice.setChoiceTitle("test");
        choice.setChoiceValidity(true);
        choice.setQuestion(question);

        em.persist(choice);

        TypedQuery<Choice> allChoices = em.createQuery("from Choice", Choice.class);
        List<Choice> resultList = allChoices.getResultList();

        Assertions.assertEquals(1, resultList.size());

        TypedQuery<Choice> allChoicesForAQuestion = em.createQuery("from Choice c where c.question = :question", Choice.class);
        allChoicesForAQuestion.setParameter("question", question);

        Assertions.assertEquals(1, allChoicesForAQuestion.getResultList().size());

    }

}
