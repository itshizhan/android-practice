package com.itshizhan.viewswitcherdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ViewSwitcher;

import com.itshizhan.viewswitcherdemo.entity.ViewSwitcherItemData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity  implements View.OnClickListener{

    // 定义一个常量，用于显示每屏显示的应用程序数
    public static final int NUMBER_PER_SCREEN = 12;
    // 记录当前正在显示第几屏的程序
    public static int screenNo = -1;
    // 保存程序所占的总屏数
    public static int screenCount;

    private ViewSwitcher mViewSwitcher = null;
    private Button mPrevBtn = null;
    private Button mNextBtn = null;
    private ViewSwitcherBaseAdapter mAdapter = null;
    // 保存系统所有应用程序的List集合
    private List<ViewSwitcherItemData> mItemDatas = new ArrayList<ViewSwitcherItemData>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mViewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        mPrevBtn = (Button) findViewById(R.id.prev_btn);
        mNextBtn = (Button) findViewById(R.id.next_btn);


        mViewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            // 实际上就是返回一个GridView组件
            @Override
            public View makeView() {
                // 加载R.layout.slide_gridview组件，实际上就是一个GridView组件
                return MainActivity.this.getLayoutInflater()
                        .inflate(R.layout.slide_gridview, null);
            }
        });

        // 创建一个包含40个元素的List集合，用于模拟包含40个应用程序
        for (int i = 0; i < 40; i++) {
            ViewSwitcherItemData item =
                    new ViewSwitcherItemData("item" + i, R.mipmap.ic_launcher);
            mItemDatas.add(item);
        }


        // 计算应用程序所占的总屏数
        // 如果应用程序的数量能整除NUMBER_PER_SCREEN，除法的结果就是总屏数
        // 如果不能整除，总屏数应该是除法的结果再加1
        screenCount = mItemDatas.size() % NUMBER_PER_SCREEN == 0 ?
                mItemDatas.size() / NUMBER_PER_SCREEN :
                mItemDatas.size() / NUMBER_PER_SCREEN + 1;

        mAdapter = new ViewSwitcherBaseAdapter(this, mItemDatas);

        mPrevBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        // 页面加载时先显示第一屏
        next();
    }

    private void next() {
        if (screenNo < screenCount - 1) {
            screenNo++;
            // 为ViewSwitcher的组件显示过程设置动画
            mViewSwitcher.setInAnimation(this, R.anim.slide_in_right);
            // 为ViewSwitcher的组件隐藏过程设置动画
            mViewSwitcher.setOutAnimation(this, R.anim.slide_out_left);
            // 控制下一屏将要显示的GridView对应的Adapter
            ((GridView) mViewSwitcher.getNextView()).setAdapter(mAdapter);
            // 单击右边按钮，显示下一屏
            // 学习手势检测后，也可通过手势检测实现显示下一屏
            mViewSwitcher.showNext();
        }
    }

    private void prev() {
        if (screenNo > 0) {
            screenNo--;
            // 为ViewSwitcher的组件显示过程设置动画
            mViewSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
            // 为ViewSwitcher的组件隐藏过程设置动画
            mViewSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
            // 控制下一屏将要显示的GridView对应的 Adapter
            ((GridView) mViewSwitcher.getNextView()).setAdapter(mAdapter);
            // 单击左边按钮，显示上一屏，当然可以采用手势
            // 学习手势检测后，也可通过手势检测实现显示上一屏
            mViewSwitcher.showPrevious();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_btn:
                prev();
                break;
            case R.id.next_btn:
                next();
                break;
            default:
                break;


        }

    }

}
