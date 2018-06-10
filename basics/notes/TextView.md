# TextView

```java
// added in API level 1
TextView
public class TextView 
extends View implements ViewTreeObserver.OnPreDrawListener

java.lang.Object
   ↳	android.view.View
 	   ↳	android.widget.TextView

//直接子类
  Button, CheckedTextView, Chronometer, DigitalClock, EditText, TextClock

//间接子类
  AutoCompleteTextView, CheckBox, CompoundButton, ExtractEditText, MultiAutoCompleteTextView, RadioButton, Switch, ToggleButton

```

### 常用xml属性及方法

1:参考官方文档：https://developer.android.google.cn/reference/android/widget/TextView
2:参考实例demo： /basics/codes/TextviewDemo 工程


### 常用问题

1. android:ellipsize 不生效?
设置： `android:singleLine="true" `

2. layout_gravity与gravity的区别?

gravity:指的是文字在控件中的对齐方式。或者子view在父view的对齐方式(在父view中设置)。
layout_gravity:是用于指定控件在布局中的对齐方式，例如 android:layout_gravity="center";实现整个控件的位置在布局中居中。



