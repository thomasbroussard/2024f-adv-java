package fr.epita.quiz.tests.hibernate;

import fr.epita.quiz.tests.spring.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class TestHibernate {

    @PersistenceContext
    EntityManager em;


    @Test
    public void test(){
        Assertions.assertNotNull(em);
    }

}
