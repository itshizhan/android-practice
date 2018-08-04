package com.itshizhan.okhttptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static final String TAG = "MainActivity";
    public static final String URL = "https://api.douban.com/v2/book/17604305";


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.send_request_btn);
        textView = (TextView) findViewById(R.id.text_view);
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_request_btn:
                //Toast.makeText(MainActivity.this,"request",Toast.LENGTH_SHORT).show();
                //sendRequestWithHttpURLConnection();
                sendRequestWithOkHttp();
                break;
            default:
                break;
        }

    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                doOkhttp();
            }
        }).start();
    }

    private void doOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            String resData = response.body().string();

            //showResponse(resData);
            parsonJsonWithJsonObject(resData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parsonJsonWithJsonObject(String jsonData) {
        Log.d(TAG,jsonData);
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            //String title = jsonObject.getString("subtitle");
            //showResponse(title);
            String tags = jsonObject.getString("tags");
            JSONArray jsonArray = new JSONArray(tags);

            StringBuilder stringBuilder = new StringBuilder();

            for(int i=0;i<jsonArray.length();i++){
                JSONObject tagObj = jsonArray.getJSONObject(i);
                String name = tagObj.getString("name");
                stringBuilder.append(name+"\n");
                showResponse(stringBuilder.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void sendRequestWithHttpURLConnection() {
        // 开启子线程发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder text = new StringBuilder();
                    String line ;
                    while ((line = reader.readLine())!=null){
                        text.append(line);
                    }
                    showResponse(text.toString());


                }catch (Exception e){
                    e.printStackTrace();

                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(connection!=null){
                        connection.disconnect();
                    }

                }
            }
        }).start();

    }

    private void showResponse(final String s) {
        //切换到主线程UI线程更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //UI操作
                textView.setText(s);
            }
        });
    }

}
