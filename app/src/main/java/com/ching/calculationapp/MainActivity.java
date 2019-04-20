package com.ching.calculationapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import model.Answer;
import model.FileManager;
import model.MathAnsweredQuestion;
import model.MathQuestion;
import model.Question;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int buttonsNumbers[] = {R.id.button0,R.id.button1, R.id.button2, R.id.button3, 
                            R.id.button4,R.id.button5, R.id.button6, R.id.button7, 
                            R.id.button8,R.id.button9};
    
    ArrayList<Button> listNumbers = new ArrayList<>();
    
    Button buttonClear, buttonValidate, buttonResults, buttonQuit, buttonStart, buttonSave, buttonDot, buttonMinus;

    Question currentQuestion;
    
    CountDownTimer countDownTimer;

    TextView textViewQuestion, textViewAnswer, textViewCounter;

    boolean answeredQuestion = false;
    boolean running = false;
    boolean started = false;

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

        textViewCounter = findViewById(R.id.textView_Counter);

        textViewAnswer = findViewById(R.id.textView_answer);
        textViewAnswer.setText("");
        
        buttonClear = findViewById(R.id.button_Clear);
        buttonClear.setOnClickListener(this);
        
        buttonQuit = findViewById(R.id.button_Quit);
        buttonQuit.setOnClickListener(this);
        
        buttonResults = findViewById(R.id.button_Result);
        buttonResults.setOnClickListener(this);
        buttonResults.setEnabled(false);
        
        buttonValidate = findViewById(R.id.button_Validate);
        buttonValidate.setOnClickListener(this);
        buttonValidate.setEnabled(false);
        
        buttonStart = findViewById(R.id.button_Start);
        buttonStart.setOnClickListener(this);
        
        buttonSave = findViewById(R.id.button_Save);
        buttonSave.setOnClickListener(this);
        buttonSave.setEnabled(false);

        buttonDot = findViewById(R.id.button_dot);
        buttonDot.setOnClickListener(this);

        buttonMinus = findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(this);
        
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

            case R.id.button_minus:

                if(!textViewAnswer.getText().toString().contains("-")) {
                    if (!textViewAnswer.getText().toString().equals("")) {
                        String str = "-" + textViewAnswer.getText().toString();
                        textViewAnswer.setText(str);
                    } else {
                        textViewAnswer.setText("-");
                    }
                } else {
                    String str = textViewAnswer.getText().toString();
                    textViewAnswer.setText(str.substring(1));
                }
                break;

            case R.id.button_dot:

                if(!textViewAnswer.getText().toString().contains(".")) {
                    if(textViewAnswer.getText().toString().equals("")) {
                        textViewAnswer.setText("0.");
                    }else{
                        textViewAnswer.append(".");
                    }
                } else {
                    String str = textViewAnswer.getText().toString();
                    textViewAnswer.setText(str.substring(0, str.length()-1));
                }
                break;
        
            case R.id.button_Start:

                if (running) {
                    buttonStart.setText("Start");
                    running = false;
                    countDownTimer.cancel();

                    resetTimer();

                    setButtons();

                } else {
                    buttonStart.setText("Stop");
                    running = true;
                    started = true;
                    Log.i("checked","Started");

                    setButtons();

                    startTimer();
                }

                break;

            case R.id.button_Validate:

                // Save the answer
                answeredQuestion = true;

                saveQuestion();

                textViewAnswer.setText("");

                resetTimer();

                // Start timer again
                startTimer();

                // Save the question and answer in the file
                break;

            case R.id.button_Clear:
                textViewAnswer.setText("");
                break;

            case R.id.button_Quit:
                //countDownTimer.cancel();
                finish();
                break;

            case R.id.button_Result:
                if(running){

                    Toast.makeText(this,"Test should be stopped first",Toast.LENGTH_LONG).show();

                } else {

                    countDownTimer.cancel();
                    Intent intent = new Intent(this, ResultActivity.class);
                    startActivity(intent);

                }

                break;

            case R.id.button_Save:
                List<MathAnsweredQuestion> questions = MathAnsweredQuestion.getAllQuestions();
                FileManager.writeFile(this, "results.txt", questions);
                break;
            
            
        }
        
    }

    private void setButtons(){

        if(started && running){

            buttonValidate.setEnabled(true);
            buttonSave.setEnabled(true);
            buttonResults.setEnabled(true);

        } else if (started && !running) {

            buttonValidate.setEnabled(true);
            buttonSave.setEnabled(true);
            buttonResults.setEnabled(true);

        } else if (!started) {

            buttonValidate.setEnabled(false);
            buttonSave.setEnabled(false);
            buttonResults.setEnabled(false);

        }

    }


    private void startTimer() {


        answeredQuestion = false;
        currentQuestion= new MathQuestion();
        textViewQuestion.setText(currentQuestion.getQuestionText());

        countDownTimer = new CountDownTimer(getResources().getInteger(R.integer.time),1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                elapseTime = getResources().getInteger(R.integer.time) - millisUntilFinished;
                textViewCounter.setText(String.valueOf(millisUntilFinished/1000));
                Log.i("time", "onTick: "+elapseTime);

            }

            @Override
            public void onFinish() {

                if(elapseTime > getResources().getInteger(R.integer.time)-2000 && answeredQuestion == false){
                    saveQuestion();
                }
                startTimer();
                //total = total + getResources().getInteger(R.integer.time);

            }
        }.start();

    }

    private void resetTimer() {

        countDownTimer.cancel();

    }

    private void saveQuestion(){

        // Save the answer
        String userAnswer;
        if (answeredQuestion == false) {
            userAnswer = "";
            elapseTime = 10000;
        } else {
            userAnswer = textViewAnswer.getText().toString();
        }

        Answer answer = new Answer(userAnswer);
        Question answeredQuestion = new MathAnsweredQuestion(currentQuestion,answer,(int) elapseTime/1000);

    }
}
