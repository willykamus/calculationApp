package model;

import java.util.Random;

public class MathQuestion extends Question {

    public MathQuestion() {

        MathQuestionGenerator question = new MathQuestionGenerator();
        this.questionText = question.text;
        this.correctAnswer = question.result;

    }

    @Override
    public String getQuestionText() {
        return this.questionText;
    }

    @Override
    public Answer getCorrectAnswer() {
        return this.correctAnswer;
    }

    private class MathQuestionGenerator {

        private char operations[] = {'+','-','*'};
        private String text;
        private Answer result;

        private MathQuestionGenerator(){
            questionGenerator();
        }

        private void questionGenerator(){

            //First get a random number
            Random random = new Random();
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(10);
            int operation = random.nextInt(operations.length);
            int questionResult;

            switch (operations[operation]) {

                case '+':

                    questionResult = num1 + num2;
                    this.result = new Answer(String.valueOf(questionResult));
                    this.text = "" + num1 + " + " + num2;
                    break;

                case '-':
                    questionResult = num1 - num2;
                    this.result = new Answer(String.valueOf(questionResult));;
                    this.text = "" + num1 + " - " + num2;
                    break;

                case '*':
                    questionResult = num1 * num2;
                    this.result = new Answer(String.valueOf(questionResult));
                    this.text = "" + num1 + " * " + num2;
                    break;

            }

        }

    }

}
