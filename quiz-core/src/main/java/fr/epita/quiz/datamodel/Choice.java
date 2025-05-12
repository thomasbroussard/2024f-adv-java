package fr.epita.quiz.datamodel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CHOICES")
public class Choice {

    @Id
    private int id;

    @Column(name = "CHOICE_TITLE")
    private String choiceTitle;

    @Column(name = "CHOICE_VALIDITY")
    private Boolean choiceValidity;

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
}
