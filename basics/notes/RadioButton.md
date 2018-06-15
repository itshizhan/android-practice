# RadioButton

在RadioButton没有被选中时，用户能够按下或点击来选中它。但是，与复选框相反，用户一旦选中就不能够取消选中。当用户选中的时候会触发一个OnCheckedChange事件。

实现RadioButton由两部分组成，也就是RadioButton和RadioGroup配合使用。RadioGroup是单选组合框，可以容纳多个RadioButton的容器。在没有RadioGroup的情况下，RadioButton可以全部都选中；当多个RadioButton被RadioGroup包含的情况下，RadioButton只可以选择一个。

**示例代码：**

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
        android:text="请选择性别"/>
    <RadioGroup
        android:id="@+id/sex_rg"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
        <RadioButton
            android:id="@+id/male_rb"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="男"
            android:checked="true"/>
        <RadioButton
            android:id="@+id/female_rb"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="女"/>
    </RadioGroup>

</LinearLayout>
```



```java
public class MainActivity extends AppCompatActivity {

    private RadioButton mMaleRb;
    private RadioButton mFemaleRb;
    private RadioGroup mSexRg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mMaleRb = (RadioButton) findViewById(R.id.male_rb);
        mFemaleRb = (RadioButton) findViewById(R.id.female_rb);
        mSexRg = (RadioGroup) findViewById(R.id.sex_rg);

        mSexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 获取用户选中的性别
                String sex = "";
                switch (checkedId) {
                    case R.id.male_rb:
                        sex = mMaleRb.getText().toString();

                        break;
                    case R.id.female_rb:
                        sex = mFemaleRb.getText().toString();
                        break;
                    default:break;
                }
                // 消息提示
                Toast.makeText(MainActivity.this,
                        "选择的性别是：" + sex, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
```

