package fr.epita.quiz.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.rest.QuizRestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = QuizRestApplication.class)
@AutoConfigureWebMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateQuestion() throws Exception {
        Question question = new Question();
        question.setTitle("What is polymorphism?");

        mockMvc.perform(post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Question created successfully"));
    }

    @Test
    public void testCreateQuestionWithEmptyTitle() throws Exception {
        Question question = new Question();
        question.setTitle("");

        mockMvc.perform(post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Question title cannot be empty"));
    }

    @Test
    public void testGetAllQuestions() throws Exception {
        mockMvc.perform(get("/api/questions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
} 