package model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private static List<Question> questionList = new ArrayList<>();

    private int result;
    private String text;

    public Question(int result, String text) {
        this.result = result;
        this.text = text;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Question getQuestion(int index){

        return questionList.get(index);

    }
}
