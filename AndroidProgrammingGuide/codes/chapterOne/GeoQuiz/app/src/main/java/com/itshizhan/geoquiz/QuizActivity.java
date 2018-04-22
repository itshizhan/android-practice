package com.itshizhan.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();

    }


    private void initView(){
        mTrueButton  = (Button)findViewById(R.id.true_button);
        mFalseButton = this.<Button>findViewById(R.id.false_button);


        mTrueButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.center,100,10);
                toast.setGravity(Gravity.TOP, 0, 150);
                toast.show();

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            }
        });

    }
}

