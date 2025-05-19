package fr.epita.quiz.datamodel;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ANSWERS")
public class Answer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "text")
    private String text;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
