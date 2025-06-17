package fr.epita.quiz.rest.controller;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.rest.dto.QuestionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private static final Logger LOGGER = LogManager.getLogger(QuestionController.class);

    @PersistenceContext
    private EntityManager em;


    @GetMapping
    public String getAllQuestions() {
      return "hello";
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<QuestionDTO> getOneQuestion(@PathVariable(name="id") int id) {
        Question question = em.find(Question.class, id);
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(id);
        questionDTO.setTitle(question.getTitle());
        return ResponseEntity.ok(questionDTO);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDTO questionDTO) {
        LOGGER.info("Adding questionDTO {}", questionDTO);
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        em.persist(question);

        return ResponseEntity.ok("questionDTO added");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody QuestionDTO question, @PathVariable(name = "id") int id) {
        LOGGER.info("Updating question with id {}", id);
        return ResponseEntity.ok("question updated : " + id);
    }

    @PatchMapping
    public ResponseEntity<String> patchQuestion(@RequestBody QuestionDTO question, @PathVariable(name = "id") int id) {
        LOGGER.info("Updating question with id {}", id);
        return ResponseEntity.ok("question updated : " + id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable(name = "id") int id) {
        LOGGER.debug("question deleted :  {}", id);
        return ResponseEntity.ok("question deleted : " + id);
    }




} 