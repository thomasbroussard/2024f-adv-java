package fr.epita.quiz.tests.hibernate;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.tests.spring.ApplicationConfiguration;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Transactional
public class TestHibernate {

    @PersistenceContext
    EntityManager em;


    @Test
    public void test(){
        Assertions.assertNotNull(em);

        Answer answer = new Answer();
        answer.setText("test");

        //dao.create(answer)
        em.persist(answer);

        //dao.update()
        em.merge(answer);

        //dao.delete
        em.remove(answer);




    }

}
