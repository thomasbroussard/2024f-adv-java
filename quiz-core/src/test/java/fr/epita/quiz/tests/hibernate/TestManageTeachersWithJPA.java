package fr.epita.quiz.tests.hibernate;

import fr.epita.quiz.datamodel.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class TestManageTeachersWithJPA {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DataSource ds;


    @Test
    @Transactional
    public void test() {

        //given
        Teacher teacher = new Teacher();
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        //when

        em.persist(teacher);
        em.flush();

        //then
        //TODO do be done: verify that there is one teacher here. ds.getConnection();


    }
}
