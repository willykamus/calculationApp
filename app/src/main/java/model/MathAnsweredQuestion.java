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

    public static MathAnsweredQuestion getAnsweredQuestion(int index) {

        return list.get(index);

    }

    public void setStatus(){

        //Validator
        correctAnswer = this.getCorrectAnswer();

        if(correctAnswer.getAnswer() == userAnswer.getAnswer()) {

            this.status = true;

        } else {

            this.status = false;
        }

    }


}
