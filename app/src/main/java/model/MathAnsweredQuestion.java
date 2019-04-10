package model;

import java.util.ArrayList;
import java.util.List;

public class MathAnsweredQuestion extends QuestionDecorator {

    private Answer userAnswer;
    private int elapsedTime;
    private boolean status;

    private static List<MathAnsweredQuestion> list = new ArrayList<>();

    public MathAnsweredQuestion(Question question, Answer userAnswer, int elapsedTime) {
        super(question);
        this.userAnswer = userAnswer;
        this.elapsedTime = elapsedTime;
        setStatus();
        list.add(this);
    }

    public void setElapsedTime(int elapsedTime) {

        this.elapsedTime = elapsedTime;

    }

    public static MathAnsweredQuestion getQuestionAt(int index){
        return  list.get(index);
    }

    public int getElapsedTime() {

        return this.elapsedTime;
    }

    public String getUserAnswer() {
        return userAnswer.getAnswer();
    }

    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public static Question getAnsweredQuestion(int index) {

        return list.get(index);

    }

    public boolean getStatus() {
        return status;
    }

    private void setStatus(){

        //Validator
        correctAnswer = this.getCorrectAnswer();

        if(correctAnswer.getAnswer().equals(userAnswer.getAnswer())) {

            this.status = true;

        } else {

            this.status = false;
        }

    }

    public static List<MathAnsweredQuestion> getAllQuestions(){

        return MathAnsweredQuestion.list;

    }


}
