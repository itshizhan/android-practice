package com.itshizhan.imageswitcherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory{

    private ImageSwitcher mImageSwitcher = null;
    private int[] mImageIds = {
            R.drawable.icon1, R.drawable.icon2, R.drawable.icon3,
            R.drawable.icon4
    };

    private int mIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.switcher);

        // 为他它指定一个ViewFactory，也就是定义它是如何把内容显示出来的，
        // 实现ViewFactory接口并覆盖对应的makeView方法。
        mImageSwitcher.setFactory(this);

        // 设置动画效果
        mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        // 为ImageSwitcher绑定监听事件
        mImageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIndex ++;
                if(mIndex >= mImageIds.length){
                    mIndex = 0;
                }
                mImageSwitcher.setImageResource(mImageIds[mIndex]);
            }
        });

        mImageSwitcher.setImageResource(mImageIds[0]);


    }


    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundColor(0xFF000000);
        // 设置填充方式
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }
}
