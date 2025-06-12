package fr.epita.quiz.rest.controller;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.QuestionJDBCDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {


    @GetMapping
    public String getAllQuestions() {
      return "hello";
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        System.out.println(question);

        return ResponseEntity.ok("question added");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question, @PathVariable(name = "id") int id) {
        System.out.println(question);
        System.out.println("question updated : " + id);
        return ResponseEntity.ok("question updated : " + id);
    }


} 