package com.ching.calculationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.CustomAdapter;
import model.MathAnsweredQuestion;
import model.MathQuestion;
import model.Question;

public class ResultActivity extends AppCompatActivity {

    ListView listView;

    TextView textViewResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {

        textViewResults = findViewById(R.id.textView_Results);

        listView = findViewById(R.id.listView);

        List<MathAnsweredQuestion> list = MathAnsweredQuestion.getAllQuestions();

        CustomAdapter adapter = new CustomAdapter(this,R.layout.listview_adapter,list);

        listView.setAdapter(adapter);

        int totaltime = 0;
        int elapsetime = 0;

        for (int i = 0; i < MathAnsweredQuestion.getAllQuestions().size(); i++){


            totaltime += 10;

            MathAnsweredQuestion question = MathAnsweredQuestion.getQuestionAt(i);

            elapsetime += question.getElapsedTime();

        }


        String str = "Total questions: "+ getTotalQuestion() +
                     "\nTotal answered questions: "+ getTotalAnsweredQuestions()+
                     "\n"+"Total: " + totaltime +
                     "\nElapsed Time: "+elapsetime +
                     "\n% Correct answer: "+ (int)(((double)getTotalCorrectAnswers()/(double)getTotalQuestion())*100) +"%" +
                     "\n% Fail answer " + (int)(((double)getTotalIncorrect()/(double)getTotalQuestion())*100) + "%" +
                     "\nVelocity: " + (int)(((double)elapsetime/(double)totaltime)*100) + "%" +
                     "\n*Velocity means the amount of time in % that you need to answer a question, lower is better";

        textViewResults.setText(str);


    }

    private int getTotalQuestion() {

        return MathAnsweredQuestion.getAllQuestions().size();

    }

    private String getTotalAnsweredQuestions(){

        int count = 0;
        for(int i = 0; i < getTotalQuestion(); i++){

            MathAnsweredQuestion currentQuestion = MathAnsweredQuestion.getQuestionAt(i);

            if (!currentQuestion.getUserAnswer().equals("")){
                count++;
            }
        }

        return String.valueOf(count);

    }

    private int getTotalCorrectAnswers(){

        int count = 0;
        for(int i = 0; i < getTotalQuestion(); i++){

            MathAnsweredQuestion currentQuestion = MathAnsweredQuestion.getQuestionAt(i);

            if(currentQuestion.getStatus()){
                count++;
            }
        }

        return count;
    }

    private int getTotalIncorrect(){

        int count = 0;
        for(int i = 0; i < getTotalQuestion(); i++){

            MathAnsweredQuestion currentQuestion = MathAnsweredQuestion.getQuestionAt(i);

            if(!currentQuestion.getStatus()){
                count++;
            }
        }

        return count;
    }


}
