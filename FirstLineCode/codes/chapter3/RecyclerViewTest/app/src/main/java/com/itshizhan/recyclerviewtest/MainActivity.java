package com.itshizhan.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> mFruitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化水果数据
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        // 布局管理器
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(mFruitList);

        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruits() {
        for(int i=0;i<2;i++){
            Fruit apple = new Fruit(
                    getRandomLengthName("Apple"),R.drawable.apple_pic);
            mFruitList.add(apple);
            Fruit banana = new Fruit(
                    getRandomLengthName("Banana"),R.drawable.banana_pic);
            mFruitList.add(banana);
            Fruit orange = new Fruit(
                    getRandomLengthName("Orange"),R.drawable.orange_pic);
            mFruitList.add(orange);
            Fruit watermelon = new Fruit(
                    getRandomLengthName("Watermelon"),R.drawable.watermelon_pic);
            mFruitList.add(watermelon);
            Fruit pear = new Fruit(
                    getRandomLengthName("Pear"),R.drawable.pear_pic);
            mFruitList.add(pear);
            Fruit grape = new Fruit(
                    getRandomLengthName("Grape"),R.drawable.grape_pic);
            mFruitList.add(grape);
            Fruit pineapple = new Fruit(
                    getRandomLengthName("Pineapple"),R.drawable.pineapple_pic);
            mFruitList.add(pineapple);
            Fruit strawberry = new Fruit(
                    getRandomLengthName("Strawberry"),R.drawable.strawberry_pic);
            mFruitList.add(strawberry);
            Fruit cherry = new Fruit(
                    getRandomLengthName("Cherry"),R.drawable.cherry_pic);
            mFruitList.add(cherry);
            Fruit mango = new Fruit(
                    getRandomLengthName("Mango"),R.drawable.mango_pic);
            mFruitList.add(mango);



        }

    }

    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }

}
