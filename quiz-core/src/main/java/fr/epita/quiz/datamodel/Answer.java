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

    @ManyToOne
    Student student;

    @ManyToOne
    Choice choice;

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
