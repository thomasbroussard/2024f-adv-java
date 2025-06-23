package fr.epita.quiz.rest.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.rest.dto.ChoiceDTO;
import fr.epita.quiz.rest.dto.QuestionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizDataService {

    @PersistenceContext
    private EntityManager em;

    public Question findQuestionById(int id) {
        Question question = em.find(Question.class, id);
        return question;
    }

    public List<Question> findAll() {
        return em.createQuery("SELECT q FROM Question q", Question.class).getResultList();
    }

    @Transactional
    //feature from the specs
    public void recordQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        List<ChoiceDTO> choices = questionDTO.getChoices();
        em.persist(question);
        if (choices != null && !choices.isEmpty()) {
            for (ChoiceDTO choiceDTO : choices) {
                Choice choice = new Choice();
                choice.setChoiceTitle(choiceDTO.getChoiceTitle());
                choice.setChoiceValidity(choiceDTO.getChoiceValidity());
                choice.setQuestion(question);
                em.persist(choice);
            }
        }
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
