package fr.epita.quiz.rest.controller;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.rest.dto.QuestionDTO;
import fr.epita.quiz.rest.services.QuizDataService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {

    private static final Logger LOGGER = LogManager.getLogger(QuestionController.class);


    @Autowired
    QuizDataService quizDataService;
    @Autowired
    private DataSource dataSource;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        try {
            List<Question> questions = quizDataService.findAll();
            List<QuestionDTO> questionDTOs = questions.stream()
                    .map(question -> {
                        QuestionDTO dto = new QuestionDTO();
                        dto.setId(question.getId());
                        dto.setTitle(question.getTitle());
                        return dto;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(questionDTOs);
        } catch (Exception e) {
            LOGGER.error("Error fetching questions", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<QuestionDTO> getOneQuestion(@PathVariable(name="id") int id) {
        Question question = quizDataService.findQuestionById(id);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(id);
        questionDTO.setTitle(question.getTitle());
        return ResponseEntity.ok(questionDTO);
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDTO questionDTO) {
        LOGGER.info("Adding questionDTO {}", questionDTO);
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        quizDataService.save(question);
        return ResponseEntity.ok("questionDTO added");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable(name = "id") int id) {
        LOGGER.info("Updating questionDTO with id {}", id);

        Question questionToUpdate = quizDataService.findQuestionById(id);
        questionToUpdate.setTitle(questionDTO.getTitle());
        quizDataService.update(questionToUpdate);
        return ResponseEntity.ok("questionDTO updated : " + id);
    }

    @PatchMapping
    public ResponseEntity<String> patchQuestion(@RequestBody QuestionDTO question, @PathVariable(name = "id") int id) {
        LOGGER.info("Updating question with id {}", id);
        return ResponseEntity.ok("question updated : " + id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable(name = "id") int id) {
        LOGGER.debug("question deleted :  {}", id);
        Question question = quizDataService.findQuestionById(id);
        quizDataService.delete(question);
        return ResponseEntity.ok("question deleted : " + id);
    }




} 