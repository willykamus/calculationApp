package model;

public abstract class Question {

    protected String questionText;
    protected Answer correctAnswer;

    public abstract String getQuestionText();
    public abstract Answer getCorrectAnswer();

}
