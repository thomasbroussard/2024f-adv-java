package fr.epita.quiz.datamodel;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="QUESTIONS")
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;

    @Column
    String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
