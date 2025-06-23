package fr.epita.quiz.rest.dto;


public class ChoiceDTO {

    private int id;
    private String choiceTitle;
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
