package fr.epita.quiz.rest.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.rest.dto.ChoiceDTO;
import fr.epita.quiz.rest.dto.QuestionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizDataService {

    @PersistenceContext
    private EntityManager em;

    public QuestionDTO findQuestionById(int id) {
        Question question = em.find(Question.class, id);
        TypedQuery<Choice> query = em.createQuery("from Choice c where c.question = :q", Choice.class);
        query.setParameter("q", question);
        List<Choice> resultList = query.getResultList();
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(id);
        questionDTO.setTitle(question.getTitle());
        List<ChoiceDTO> choiceDTOS = new ArrayList<>();
        for (Choice choice : resultList) {
            ChoiceDTO e = new ChoiceDTO();
            e.setId(choice.getId());
            e.setChoiceTitle(choice.getChoiceTitle());
            e.setChoiceValidity(choice.getChoiceValidity());
            choiceDTOS.add(e);
        }
        questionDTO.setChoices(choiceDTOS);
        return questionDTO;
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
