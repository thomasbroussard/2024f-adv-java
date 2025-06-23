package fr.epita.quiz.rest.dto;

import fr.epita.quiz.datamodel.Choice;

import java.util.List;

public class QuestionDTO {
    private String title;
    private int id;
    private List<ChoiceDTO> choices;


    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }
}
