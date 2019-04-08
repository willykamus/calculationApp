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


    }
}
