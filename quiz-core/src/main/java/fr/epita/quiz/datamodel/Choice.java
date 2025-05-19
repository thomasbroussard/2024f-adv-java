package fr.epita.quiz.datamodel;


import jakarta.persistence.*;

@Entity
@Table(name="CHOICES")
public class Choice {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "CHOICE_TITLE")
    private String choiceTitle;

    @Column(name = "CHOICE_VALIDITY")
    private Boolean choiceValidity;

    @ManyToOne()
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoiceTitle() {
        return choiceTitle;
    }

    public void setChoiceTitle(String choiceTitle) {
        this.choiceTitle = choiceTitle;
    }

    public Boolean getChoiceValidity() {
        return choiceValidity;
    }

    public void setChoiceValidity(Boolean choiceValidity) {
        this.choiceValidity = choiceValidity;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
