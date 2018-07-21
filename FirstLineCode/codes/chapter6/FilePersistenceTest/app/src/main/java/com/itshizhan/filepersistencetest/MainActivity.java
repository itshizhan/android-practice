package com.itshizhan.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit_text);
        String savedInput = load();
        if(!TextUtils.isEmpty(savedInput)){
            mEditText.setText(savedInput);
        }
    }

    private String load() {
        StringBuilder content = new StringBuilder();
        FileInputStream in;
        BufferedReader reader;
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                String line = reader.readLine();
                if(line!=null){
                    content.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content.toString();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String text = mEditText.getText().toString();
        Log.i(TAG,text);
        if(!text.equals("")){
            save(text);
        }
    }

    private void save(String text) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = openFileOutput("data", Context.MODE_APPEND);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            try {
                bufferedWriter.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }finally {

            try {
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
