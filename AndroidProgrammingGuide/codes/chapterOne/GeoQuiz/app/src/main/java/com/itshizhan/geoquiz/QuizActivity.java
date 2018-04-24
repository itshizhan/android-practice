package com.itshizhan.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.itshizhan.geoquiz.R.id.next_button;

public class QuizActivity extends AppCompatActivity {

    private Question[] mQuestionBank = new Question[]{
        new Question(R.string.question_australia,true),
        new Question(R.string.question_oceans,false),
        new Question(R.string.question_africa,true),
        new Question(R.string.question_mideast,false),
        new Question(R.string.question_americas,true),
        new Question(R.string.question_asia,true)
    };

    int mCurrentIndex = 0;
    TextView mQuestionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();

    }


    private void initView(){

        Button trueButton = (Button) findViewById(R.id.true_button);
        Button falseButton = this.<Button>findViewById(R.id.false_button);
        Button nextButton = this.<Button>findViewById(next_button);
        mQuestionTextView  = findViewById(R.id.question_text_view);
        // 更新题目展示
        updateQuestion();

        trueButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                /*
                Toast toast = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.center,100,10);
                toast.setGravity(Gravity.TOP, 0, 150);
                toast.show();
                */
                checkAnswer(true);

            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

    }

    // 更新题目
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    // 校验答案是否正确
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT) .show();
    }
}

