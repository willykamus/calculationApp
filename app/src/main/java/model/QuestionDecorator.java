package model;

public abstract class QuestionDecorator extends Question {


    private Question question;

    public QuestionDecorator(Question question) {

        this.question = question;

    }

    @Override
    public String getQuestionText() {
        return question.getQuestionText();
    }

    @Override
    public Answer getCorrectAnswer() {
        return question.getCorrectAnswer();
    }
}
