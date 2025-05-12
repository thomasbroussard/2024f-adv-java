package fr.epita.quiz.tests.hibernate;

import fr.epita.quiz.datamodel.Choice;
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
@Transactional
public class TestChoicesWithJPA {

    @PersistenceContext
    EntityManager em;

    @Test
    public void test(){
        Choice choice = new Choice();
        choice.setChoiceTitle("test");
        choice.setChoiceValidity(true);
        em.persist(choice);

        TypedQuery<Choice> allChoices = em.createQuery("from Choice", Choice.class);
        List<Choice> resultList = allChoices.getResultList();

        Assertions.assertEquals(1, resultList.size());
    }

}
