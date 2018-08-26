package com.itshizhan.gsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itshizhan.gsondemo.gson.Result;
import com.itshizhan.gsondemo.gson.User;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.text_view);

        testGson();
    }

    private void testGson() {
        Gson gson = new Gson();
        int i = gson.fromJson("100",int.class);
        double d = gson.fromJson("\"99.99\"", double.class);
        String jsonNumber = gson.toJson(1001);
        Log.i(TAG,i+"");
        Log.i(TAG,d+"");
        //textview.setText(jsonNumber);

        //User user = new User("Jack",24);
        //String jsonObject = gson.toJson(user);
        //Log.i(TAG,jsonObject);

        String jsonString = "{\"uname\":\"Jack\",\"age\":28,\"email\":\"jack@163.com\"}";
        User user = gson.fromJson(jsonString,User.class);
        Log.i(TAG,user.toString());
        textview.setText(user.getUname()+ "\n"+ user.getAge()+"\n"+user.getEmailAddress());

        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        List<String> stringList = gson.fromJson(jsonArray,new TypeToken<List<String>>(){}.getType());

        textview.append("\n\n"+stringList.get(1));

        String json = "{\"code\":1,\"message\":\"ok\",\"data\":{\"uname\":\"Lucy\",\"age\":88,\"email\":\"Lucy@163.com\"} }";

        Type userType = new TypeToken<Result<User>>(){}.getType();
        Result<User> userResult = gson.fromJson(json,userType);

        User user1 = userResult.data;
        Log.i(TAG,"----"+ user1.toString());
        textview.append("\n"+ user1.toString());

        /**
        Type userListType = new TypeToken<Result<List<User>>>(){}.getType();
        Result<List<User>> userListResult = gson.fromJson(json,userListType);
        List<User> users = userListResult.data;


        */




    }

    private class ParameterizedTypeImpl implements ParameterizedType {

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[0];
        }
        @Override
        public Type getRawType() {
            return null;
        }
        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}


