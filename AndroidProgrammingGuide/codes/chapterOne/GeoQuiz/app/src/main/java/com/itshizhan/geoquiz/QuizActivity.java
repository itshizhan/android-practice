package com.itshizhan.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.itshizhan.geoquiz.R.id.cheat_button;
import static com.itshizhan.geoquiz.R.id.next_button;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mCheatButton;
    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final int REQUEST_CODE_CHEAT = 0;

    private boolean mIsCheater = false;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

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
        Log.d(TAG,"onCreate……");

        // 必须放在initView()方法之前。
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        initView();

        // 启动新的Activity
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(QuizActivity.this,CheatActivity.class);
//                intent.putExtra(EXTRA_ANSWER_IS_TRUE,mQuestionBank[mCurrentIndex].get)
//                startActivity(intent);
                Intent intent = QuizActivity.newIntent(QuizActivity.this,mQuestionBank[mCurrentIndex].isAnswerTrue());
                //startActivity(intent);
                // 需要返回结果
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart……");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume……");
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop……");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy……");
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private void initView(){

        Button trueButton = (Button) findViewById(R.id.true_button);
        Button falseButton = this.<Button>findViewById(R.id.false_button);
        ImageButton nextButton = this.<ImageButton>findViewById(R.id.next_button);
        ImageButton prevButton = this.<ImageButton>findViewById(R.id.prev_button);
        mQuestionTextView  = this.<TextView>findViewById(R.id.question_text_view);
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

        // 下一题
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                Log.d(TAG,"下一题…………………………");
                updateQuestion();
            }
        });

        // 上一题
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentIndex==0){
                    mCurrentIndex =  mQuestionBank.length-1;
                }else{
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }

                updateQuestion();
            }
        });

        // 同样是下一题
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

    }

    // 更新题目
    private void updateQuestion(){
        //记录updateQuestion 调用的地方：Log.d(TAG, "Updating question text ", new Exception());
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    // 校验答案是否正确
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(mIsCheater){
            messageResId = R.string.judgment_toast;
        }else{
            if(userPressedTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
            }else{
                messageResId = R.string.incorrect_toast;
            }
        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT) .show();
    }
}

