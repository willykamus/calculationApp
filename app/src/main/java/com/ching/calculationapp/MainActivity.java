package com.ching.calculationapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.util.ArrayList;

import model.Answer;
import model.MathAnsweredQuestion;
import model.MathQuestion;
import model.Question;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int buttonsNumbers[] = {R.id.button0,R.id.button1, R.id.button2, R.id.button3, 
                            R.id.button4,R.id.button5, R.id.button6, R.id.button7, 
                            R.id.button8,R.id.button9};
    
    ArrayList<Button> listNumbers = new ArrayList<>();
    
    Button buttonClear, buttonValidate, buttonResults, buttonQuit, buttonStart, buttonSave, buttonStop;

    Question currentQuestion;
    
    CountDownTimer countDownTimer;

    TextView textViewQuestion, textViewAnswer;

    long elapseTime;

    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        textViewQuestion = findViewById(R.id.textView_question);

        textViewAnswer = findViewById(R.id.textView_answer);
        textViewAnswer.setText("");
        
        buttonClear = findViewById(R.id.button_Clear);
        buttonClear.setOnClickListener(this);
        
        buttonQuit = findViewById(R.id.button_Quit);
        buttonQuit.setOnClickListener(this);
        
        buttonResults = findViewById(R.id.button_Result);
        buttonResults.setOnClickListener(this);
        
        buttonValidate = findViewById(R.id.button_Validate);
        buttonValidate.setOnClickListener(this);
        
        buttonStart = findViewById(R.id.button_Start);
        buttonStart.setOnClickListener(this);
        
        buttonSave = findViewById(R.id.button_Save);
        buttonSave.setOnClickListener(this);
        
        buttonStop = findViewById(R.id.button_Stop);
        buttonStop.setOnClickListener(this);
        
        for(int i = 0; i < buttonsNumbers.length; i++){
            
            Button button = findViewById(buttonsNumbers[i]);
            button.setOnClickListener(this);
            listNumbers.add(button);
            
        }
        
    }


    @Override
    public void onClick(View v) {

        //Pass the number pressed to the textview

        Button numberPressed = (Button)v;

        if(listNumbers.contains(numberPressed)) {

            String tmp = textViewAnswer.getText().toString() + numberPressed.getText().toString();
            textViewAnswer.setText(tmp);

        }
        
        
        switch (v.getId()){
        
            case R.id.button_Start:

                startTimer();

                break;

            case R.id.button_Validate:

                countDownTimer.cancel();
                // Save the answer
                saveQuestion();
                // Start timer again
                startTimer();

                // Save the question and answer in the file

                break;

            case R.id.button_Stop:
                countDownTimer.cancel();
                break;

            case R.id.button_Clear:
                textViewAnswer.setText("");
                break;

            case R.id.button_Quit:
                finish();
                break;

            case R.id.button_Result:
                countDownTimer.cancel();
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
                break;
            
            
        }
        
    }

    private void startTimer() {

        currentQuestion= new MathQuestion();
        textViewQuestion.setText(currentQuestion.getQuestionText());

        countDownTimer = new CountDownTimer(getResources().getInteger(R.integer.time),1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                elapseTime = getResources().getInteger(R.integer.time) - millisUntilFinished;

            }

            @Override
            public void onFinish() {

                elapseTime = 0;
                saveQuestion();
                startTimer();
                total = total + getResources().getInteger(R.integer.time);

            }
        }.start();

    }

    private void resetTimer() {

        countDownTimer.cancel();

    }

    private void saveQuestion(){

        // Save the answer
        int userAnswer;
        String text = textViewAnswer.getText().toString();
        if(text.equals("")){
            userAnswer = 0;
        } else {
            userAnswer = Integer.valueOf(textViewAnswer.getText().toString());
        }
        Answer answer = new Answer(userAnswer);
        Question answeredQuestion = new MathAnsweredQuestion(currentQuestion,answer,(int) elapseTime/1000);

    }
}
