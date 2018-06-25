# android 事件处理



#### android 中事件处理的两种方式：基于回调和基于监听

- 基于监听的事件处理： 为android界面组件绑定特定的事件监听器
- 基于回调的事件处理：重写android组件特定的回调方法，或者重写Activity的回调方法。因为android为绝大部分的界面组件都提供了事件响应的回调方法。



## 1. 基于监听的事件处理概述

基于监听的事件处理是一种更面向对象的事件处理，在事件监听的处理模型中主要涉及三类对象：

- 事件源：Event Source，事件发生的场所，通常就是各个组件，例如按钮、窗口、菜单等。
- 事件：Event，事件封装了界面组件上发生的特定事情（通常就是一次用户操作）。如果程序需要获得界面组件上所发生事件的相关信息，一般通过Event对象来取得。
- 事件监听器：Event Listener，负责监听事件源所发生的事件，并对各种事件做出相应的响应。



​      当用户按下一个按钮或者单击某个菜单项时，这些动作就会激发一个相应的事件，该事件就会触发事件源上注册的事件监听器（特殊的Java对象），事件监听器调用对应的事件处理器 （事件监听器里的实例方法）来做出相应的响应。

​        每个组件均可以针对特定的事件指定一个事件监听器，每个事件监听器也可监听一个或多个事件源。因为同一个事件源上可能发生多种事件，委派式事件处理方式可以把事件源上所有可能发生的事件分别授权给不同的事件监听器来处理；同时也可以让一类事件都使用同一个事件监听器来处理。



​       **在基于监听的事件处理模型中，事件监听器必须实现事件监听器接口**，Android为不同的界面组件提供了不同的监听器接口，这些接口通常以内部类的形式存在。以View类为例，它包含了如下几个内部接口。

- View.OnClickListener：单击事件的事件监听器必须实现的接口。
- View.OnCreateContextMenuListener ：创建上下文菜单事件的事件监听器必须实现的接口。
- View.onFocusChangeListener：焦点改变事件的事件监听器必须实现的接口。
- View.OnKeyListener：按键事件的事件监听器必须实现的接口。
- View.OnLongClickListener：长按事件的事件监听器必须实现的接口。
- View.OnTouchListener：触摸事件的事件监听器必须实现的接口。



#### 在程序中实现事件监听器，通常有如下几种形式:

- 匿名内部类形式：使用匿名内部类创建事件监听器对象。
- 内部类形式：将事件监听器类定义成当前类的内部类。
- 外部类形式：将事件监听器类定义成一个外部类(**不推荐**)。
- Activity本身作为事件监听器类：让Activity本身实现监听器接口，并实现事件处理方法。
- 直接绑定到标签形式：直接在xml布局文件对应的Activity中定义一个事件处理方法，然后在布局文件中引用要触发的事件。



示例：

xml文件：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="handleClick"
        android:text="xml绑定事件" />

    <Button
        android:id="@+id/btn_one"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="匿名内部类绑定事件"/>

    <Button
        android:id="@+id/btn_two"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="实现接口绑定事件"/>

    <Button
        android:id="@+id/btn_three"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="自定义内部类实现接口绑定事件"/>

</LinearLayout>
```



java 文件：

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnOne;
    Button mBtnTwo;
    Button mBtnThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 匿名内部类
        mBtnOne = findViewById(R.id.btn_one);
        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                               "匿名内部类点击了!",Toast.LENGTH_SHORT).show();
            }
        });

        //2. 实现接口
        mBtnTwo = findViewById(R.id.btn_two);
        mBtnTwo.setOnClickListener(this);

        //3. 内部类监听
        mBtnThree = findViewById(R.id.btn_three);
        mBtnThree.setOnClickListener(new InnerListner());
    }

    //4. xml 绑定
    public void handleClick(View view) {
        Toast.makeText(MainActivity.this,"xml绑定点击了!",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this,
                       "实现接口绑定点击!",Toast.LENGTH_SHORT).show();
    }

    private class InnerListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,
                           "自定义内部类实现接口绑定事件!",Toast.LENGTH_SHORT).show();

        }
    }
}
```





## 2. 基于回调的事件处理 

    

​      对于基于回调的事件处理模型来说，事件源与事件监听器是统一的，或者说事件监听器完全消失了。当用户在GUI组件上激发某个事件时，组件自己特定的方法将会负责处理该事件。



​      为了实现回调机制的事件处理，Android为所有GUI组件都提供了一些事件处理的回调方法，以View为例，该类包含如下方法。

- boolean onKeyDown(int keyCode, KeyEvent event)：当用户在该组件上按下某个按键时触发该方法。
- boolean onKeyLongPress(int keyCode, KeyEvent event)：当用户在该组件上长按某个按键时触发该方法。
- boolean onKeyShortcut(int keyCode, KeyEvent event)：当一个键盘快捷键事件发生时触发该方法。
- boolean onKeyUp(int keyCode, KeyEvent event)：当用户在该组件上松开某个按键时触发该方法。
- boolean onTouchEvent(MotionEvent event)：当用户在该组件上触发触摸屏事件时触发该方法。
- boolean onTrackballEvent(MotionEvent event)：当用户在该组件上触发轨迹球事件时触发该方法。
- void onFocusChanged(boolean gainFocus, int direction, Rect previously FocusedRect)：当组件的焦点发生改变时触发该方法。和前面的6个方法不同，该方法只能够在View中重写。



**几乎所有基于回调的事件处理方法都有一个boolean类型的返回值，该返回值用于标识该处理方法是否能完全处理该事件。** 

- 如果处理事件的回调方法返回true，表明该处理方法己完全处理该事件，该事件不会传播出去。
- 如果处理事件的回调方法返回false，表明该处理方法并未完全处理该事件，该事件会传播出去。



示例见**basics/code**文件夹





## 3.Android系统事件的响应

 在开发Android应用时，有时候可能需要让应用程序随系统设置而进行调整，比如判断系统的屏幕方向、判断系统方向的方向导航设备等。除此之外，有时候可能还需要让应用程序监听系统设置的更改，对系统设置的更改做出响应。



#### Configuration类

 Configuration类专门用于描述手机设备上的配置信息，**这些配置信息既包括用户特定的配置项，也包括系统的动态设备配置**。程序可调用Activity的如下方法来获取系统的Configuration对象：

```java
Configuration cfg = getResources().getConfiguration();
```



  **一旦获得了系统的Configuration对象，就可以使用该对象提供的如下常用属性来获取系统的配置信息:**



- densityDpi：屏幕密度。
- fontScale：当前用户设置的字体的缩放因子。
- hardKeyboardHidden：判断硬键盘是否可见，有两个可选值：

- HARDKEYBOARDHIDDEN_NO，值为十六进制的0。
- HARDKEYBOARDHIDDEN_YES，值为十六进制的1。

- keyboard：获取当前关联额键盘类型：该属性的返回值： 
  - KEYBOARD_12KEY：只有12个键的小键盘。
  - KEYBOARD_NOKEYS：无键盘。
  - KEYBOARD_QWERTY：普通键盘。

- keyboardHidden：该属性返回一个boolean值用于标识当前键盘是否可用。该属性不仅会判断系统的硬件键盘，也会判断系统的软键盘（位于屏幕）。
- locale：获取用户当前的语言环境。
- mcc：获取移动信号的国家码。
- mnc：获取移动信号的网络码。
- ps:国家代码和网络代码共同确定当前手机网络运营商。
- navigation：判断系统上方向导航设备的类型。该属性的返回值：
  - NAVIGATION_NONAV：无导航。
  - NAVIGATION_DPAD：DPAD导航。
  - NAVIGATION_TRACKBALL：轨迹球导航。
  - NAVIGATION_WHEEL：滚轮导航。

- orientation：获取系统屏幕的方向。该属性的返回值：
  - ORIENTATION_LANDSCAPE：横向屏幕。
  - ORIENTATION_PORTRAIT：竖向屏幕。

- screenHeightDp，screenWidthDp：屏幕可用高和宽，用dp表示。
- touchscreen：获取系统触摸屏的触摸方式。该属性的返回值：
  - TOUCHSCREEN_NOTOUCH：无触摸屏。
  - TOUCHSCREEN_STYLUS：触摸笔式触摸屏。
  - TOUCHSCREEN_FINGER：接收手指的触摸屏。



​        如果程序需要监听系统设置的更改，则可以考虑重写Activity的onConfigurationChanged (Configuration newConfig)方法，该方法是一个基于回调的事件处理方法：当系统设置发生更改时，该方法会被自动触发。



​        当然，为了让Activity能监听系统配置更改的事件，需要在配置Activity时指定 androidiconfigChanges 属性，该属性可以支持 mcc、mnc、locale、touchscreen、keyboard、keyboardHidden、navigation、orientation、screenLayout、uiMode、screenSize、smallestScreenSize、fontScale等属性值。

