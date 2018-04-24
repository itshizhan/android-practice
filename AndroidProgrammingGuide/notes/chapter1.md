#  1. Android 开发基础

#### activity 和 layout

activity: 是android SDK 中Activity类的一个实例，负责管理用户与应用界面的交互。通常，应用的功能是通过编写Activity 的子类来实现的。

layout: 布局定义一系列用户界面对象，以及他们显示在屏幕上的位置。布局的定义保存在XML文件中。

> 布局的命名规则：颠倒Activity子类名的单词顺序并全部转为小写，然后再单词之间添加下划线。例如，Activity 名称为：`QuizActivity.java`， Layout 名称为：`activity_quiz.xml`

#### 组件
组件定义在Activity xml文件中，例如RelativeLayout 和TextView 组件。每一个组件都是View类或其子类的一个具体实例。

LinearLayout组件继承自ViewGroup组件(也是个View子类)。ViewGroup组件是包含并配 置其他组件的特殊组件。想要以一列或一排的样式布置组件，就可以使用LinearLayout组件。 其他ViewGroup子类还有FrameLayout、TableLayout和RelativeLayout。

若某个组件包含在一个ViewGroup中，该组件与ViewGroup即构成父子关系。

#### 组件属性

几乎每类组件都需要android:layout_width和android:layout_height属性, 其他值为：
- match_parent:视图与其父视图大小相同。
- wrap_content:视图将根据其显示内容自动调整大小。（同已经废弃的：fill_parent)



# 2. 资源与资源ID

布局是一种资源。资源是应用非代码形式的内容，如图像文件、音频文件以及XML文件等。
项目的所有资源文件都存放在目录app/res的子目录下。

可以使用资源ID在代码中获取相应的资源。例如：activity_quiz.xml布局的资源ID为R.layout. activity_quiz。

在项目工具窗口的最上部找到下拉菜单，从Android项目视图切换至Project视图。Project视图 会显示出当前项目的所有文件和目录。
展开目录app/build/generated/source/r/debug，找到项目包名称并打开其中的R.java文件，即可 看到GeoQuiz应用当前所有的资源。R.java文件在Android项目编译过程中自动生成，如该文件头 部的警示所述，请不要修改该文件的内容。

修改布局或字符串等资源后，R.java文件不会实时刷新。Android Studio另外还存有一份代码 编译用的R.java隐藏文件。当前代码编辑区打开的R.java文件仅在应用安装至设备或模拟器前产 生，因此只有在Android Studio中点击运行应用时，它才会得到更新。

> 要为组件生成资源ID，请在定义组件时为其添加android:id属性。android:id属性值前面有一个+标志，而android:text属性值则没有。这是因为我 们在创建资源ID，而对字符串资源只是做引用。

# 3. 引用组件

```java
//该方法以组件的资源ID作为参数，返回一个视图对象。
public View findViewById(int id) 5
```

# 4. 设置监听器

Android应用属于典型的事件驱动类型。

应用等待某个特定事件的发生，也可以说应用正在“监听”特定事件。为响应某个事件而创 建的对象叫作监听器(listener)。监听器会实现特定事件的监听器接口(listener interface)。

无需自己动手，Android SDK已经为各种事件内置了很多监听器接口。当前应用需要监听用 户的按钮“点击”事件，因此监听器需实现View.OnClickListener接口。

例如：

```java
Button mTrueButton = (Button) findViewById(R.id.true_button);
mTrueButton.setOnClickListener(new View.OnClickListener() { 
	@Override
	public void onClick(View view) {
	
	}
});
```

# 5. Gradle 编译

```sh
# linux
$ ./gradlew tasks
#window
> gradlew.bat tasks
```

# 6. 定制 toast 消息

通常默认的Toast 消息弹出在底部，可以使用 setGravity 进行定制位置。

例如：

```java
Toast toast = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
//toast.setGravity(Gravity.center,100,10);
toast.setGravity(Gravity.TOP, 0, 150);
toast.show();
```