package fr.epita.quiz.rest.controller;

import fr.epita.quiz.datamodel.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    @GetMapping("teachers/{id}")
    //TODO use a DTO
    public ResponseEntity<TeacherDTO> getTeacherById(
            @PathVariable(name = "id") int id){
        //TODO replace by sth that comes from the database

         TeacherDTO dto = aDataService.findTeacherById(id);


        return ResponseEntity.ok(dto);
    }

    @PostMapping("teachers/{id}")
    //TODO use a DTO
    public ResponseEntity<String> postTeacher(@RequestBody TeacherDTO dto) throws URISyntaxException, MalformedURLException {
        //TODO replace by sth that comes from the database

        Integer id = aDataService.saveTeacher(dto);


        return ResponseEntity.created(new URI("/api/v1/users/"+ id)).build();
    }

}
