# ToggleButton



ToggleButton（开关按钮）:

具有**选中、未选中**两种状态。不同状态需要设置不同的显示文本。

状态切换时触发事件:OnCheckedChange

```java
android:checked : setChecked  //设置是否选中
android:textOn="喜欢"    // 状态打开时显示的文本
android:textOff="不喜欢"  // 状态关闭时显示的文本
```



示例代码：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="你喜欢学习Android开发吗？"
        android:textSize="22sp"/>

    <ToggleButton
        android:id="@+id/like_tb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textOn="喜欢"
        android:textOff="不喜欢" />
</LinearLayout>
```



```java
public class MainActivity extends AppCompatActivity {
    private ToggleButton mLikeTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取界面组件
        mLikeTb = (ToggleButton) findViewById(R.id.like_tb);

        // 为开关按钮设置OnCheckedChangeListener监听器
        mLikeTb.setOnCheckedChangeListener(onChange());
    }
    
    @NonNull
    private CompoundButton.OnCheckedChangeListener onChange() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 消息提示
                if (compoundButton.isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "喜欢Android开发", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "不喜欢Android开发", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

}

```

