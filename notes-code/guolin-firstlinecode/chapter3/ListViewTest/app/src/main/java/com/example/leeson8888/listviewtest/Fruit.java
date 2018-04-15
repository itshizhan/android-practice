package com.example.leeson8888.listviewtest;

/**
 * Created by leeson8888 on 2017/7/6.
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name ,int imageId){
        this.name = name;
        this.imageId =imageId;
    }
    public  String getName(){
        return  name;
    }
    public int getImageId(){
        return imageId;
    }
}
