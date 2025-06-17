package fr.epita.quiz.rest.services;

import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class QuizDataService {

    @PersistenceContext
    private EntityManager em;

    public Question findQuestionById(int id) {
        Question question = em.find(Question.class, id);
        return question;
    }

    @Transactional
    public void save(Question question) {
        em.persist(question);
    }

    @Transactional
    public void update(Question question) {
        em.merge(question);
    }

    @Transactional
    public void delete(Question question) {
        em.remove(question);
    }

}
