package fr.epita.quiz.rest.controller;

import fr.epita.quiz.datamodel.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    @GetMapping("teachers/{id}")
    //TODO use a DTO
    public ResponseEntity<Teacher> getTeacherById(
            @PathVariable(name = "id") int id) {
        Teacher body = new Teacher();
        //TODO replace by sth that comes from the database
        return ResponseEntity.ok(body);
    }

}
