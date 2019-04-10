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
                     "\nElapsed Time: "+elapsetime;

        textViewResults.setText(str);


    }

    private String getTotalQuestion() {

        String str = String.valueOf(MathAnsweredQuestion.getAllQuestions().size());
        return str;


    }

    private String getTotalAnsweredQuestions(){

        int count = 0;
        for(int i = 0; i < MathAnsweredQuestion.getAllQuestions().size(); i++){

            MathAnsweredQuestion currentQuestion = MathAnsweredQuestion.getQuestionAt(i);

            if (!currentQuestion.getUserAnswer().equals("")){
                count++;
            }
        }

        return String.valueOf(count);

    }


}
