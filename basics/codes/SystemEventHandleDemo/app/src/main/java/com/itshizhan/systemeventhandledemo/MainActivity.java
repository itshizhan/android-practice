package com.itshizhan.systemeventhandledemo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 获取界面组件
        TextView configurationTv = (TextView) findViewById(R.id.configuration_tv);

        // 获取系统的Configuration对象
        Configuration cfg = getResources().getConfiguration();

        // 获取系统的配置信息
        StringBuffer status = new StringBuffer();
        status.append("屏幕密度：" + cfg.densityDpi + "\n");
        status.append("字体缩放因子：" + cfg.fontScale + "\n");
        status.append("硬键盘是否可见：" + cfg.hardKeyboardHidden + "\n");
        status.append("键盘类型：" + cfg.keyboard + "\n");
        status.append("当前键盘是否可用：" + cfg.keyboardHidden + "\n");
        status.append("语言环境：" + cfg.locale + "\n");
        status.append("移动信号的国家码：" + cfg.mcc + "\n");
        status.append("移动信号的网络码：" + cfg.mnc + "\n");
        status.append("方向导航设备的类型：" + cfg.navigation + "\n");
        status.append("方向导航设备是否可用：" + cfg.navigationHidden + "\n");
        status.append("系统屏幕的方向：" + cfg.orientation + "\n");
        status.append("屏幕可用高：" + cfg.screenHeightDp + "\n");
        status.append("屏幕可用宽：" + cfg.screenWidthDp + "\n");
        status.append("系统触摸屏的触摸方式：" + cfg.touchscreen + "\n");
        status.append("screenLayout：" + cfg.screenLayout + "\n");
        status.append("smallestScreenWidthDp：" + cfg.smallestScreenWidthDp + "\n");
        status.append("uiMode：" + cfg.uiMode + "\n");

        // 配置信息输出
        configurationTv.setText(status.toString());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 获取更改后的屏幕方向
        int screen = newConfig.orientation;
        String ori = (Configuration.ORIENTATION_LANDSCAPE == screen) ? "横屏" : "竖屏";

        // 消息提示
        Toast.makeText(this, "系统的屏幕方向改变为：" + ori, Toast.LENGTH_SHORT).show();
    }
}
