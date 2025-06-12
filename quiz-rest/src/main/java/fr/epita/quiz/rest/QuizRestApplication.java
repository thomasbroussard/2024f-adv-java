package fr.epita.quiz.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"fr.epita.quiz"})
public class QuizRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuizRestApplication.class, args);
    }
} 