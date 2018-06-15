# CheckBox

CheckBox（复选框）是Android中的复选框，主要有两种状态：选中和未选中。通过isChecked方法来判断是否被选中，当用户单击时可以在这两种状态间进行切换，会触发一个OnCheckedChange事件。

实例代码：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="选择喜欢的城市"/>
    <CheckBox
        android:id="@+id/shanghai_cb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="上海"
        android:checked="true"/>
    <CheckBox
        android:id="@+id/beijing_cb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="北京"/>
    <CheckBox
        android:id="@+id/chongqing_cb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="重庆"/>
</LinearLayout>

```



```java
public class MainActivity extends AppCompatActivity {
    private CheckBox mShanghaiCb;
    private CheckBox mBeijingCb;
    private CheckBox mChongqingCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取组件
        mShanghaiCb = (CheckBox) findViewById(R.id.shanghai_cb);
        mBeijingCb = (CheckBox) findViewById(R.id.beijing_cb);
        mChongqingCb = (CheckBox) findViewById(R.id.chongqing_cb);

        // 绑定事件
        mShanghaiCb.setOnCheckedChangeListener(onCheckBoxChangeListener());
        mBeijingCb.setOnCheckedChangeListener(onCheckBoxChangeListener());
        mChongqingCb.setOnCheckedChangeListener(onCheckBoxChangeListener());
    }

    @NonNull
    private CompoundButton.OnCheckedChangeListener onCheckBoxChangeListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, 
                                         boolean isChecked) {
                showSelectCity(compoundButton);
            }
        };
    }

    private void showSelectCity(CompoundButton compoundButton) {
        String city = compoundButton.getText().toString();
        // 根据复选框的选中状态进行相应提示
        if (compoundButton.isChecked()) {
            Toast.makeText(MainActivity.this, "选中" + city,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "取消选中" + city,
                    Toast.LENGTH_SHORT).show();
        }
    }
}

```





# [ 两种自定义样式的Checkbox,参考网络](https://www.cnblogs.com/lihualuo/p/3665847.html)

1. 设置一个背景选择器checkbox_style.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@android:drawable/checkbox_on_background"
        android:state_checked="true"/>
    <item android:drawable="@android:drawable/checkbox_off_background"
        android:state_checked="false"/>
    <item android:drawable="@android:drawable/checkbox_off_background"/>
</selector>
```



2. 将checkbox_style 条件到布局文件

   - 一种是直接在布局文件的android：button属性中设置：

     ```xml
         <CheckBox
             android:id="@+id/chongqing_cb"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:text="重庆"
             android:button="@drawable/checkbox_style"/>
     ```

     

   - 一种是在style.xml文件中添加样式MyCheckboxStyle，并在布局文件中的style属性中设置

     ```xml
     <style name="MyCheckboxStyle 	 
                  parent="@android:style/Widget.CompoundButton.CheckBox">
         <item name="android:button">@drawable/checkbox_style</item>
     </style>
     ```

     然后布局中引入样式：` style="@style/MyCheckboxStyle"`

​		

