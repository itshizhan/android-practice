# Activity 的生命周期

#### 当应用旋转时，界面状态会进行重置？？

每个Activity实例都有其生命周期，在其生命周期内，Activity总是在运行、暂停、停止、不存在四种状态间转换。

每次转换时，都有相应的方法发送消息，通知Activity。

```
<img src="https://github.com/itshizhan/android-practice/blob/master/AndroidProgrammingGuide/notes/images/android-lifecycle.png?raw=true"/>
```

#### activity的状态

| 状态   | 有内存实例  |  用户可见     |    处于前台|
| -- |  --  | -- | --  |
|   不存在 |  否  |    不可见          |  否     |
|   停止  |  是   |    不可见         |   否    |
|   暂停   |   是   |    可见或部分可见 |   否    |
|   运行   |   是   |    可见         |    是   |


> 任何时候只能有一个 activity处于用户能交互的运行状态。

Activity的子类可以在activity的生命周期状态发生关键性转换时完 成某些工作。这些方法通常被称为生命周期回调方法。

我们熟知的onCreate(Bundle)方法，是在创建activity实例后，但在此实例出现在屏幕上之前，Android操作系统会调用该方法。

通过覆盖onCreate(Bundle)方法，activity可以预处理以下UI相关工作:
- 实例化组件，并将其放置在屏幕上（setContentView(int id)）。
- 引用已经实例化的组件。
- 为组件设置监听器，以处理y用户交互。
- 访问外部模型数据。

#### 切记：不能主动调用onCreate(Bundle)方法或任何其他activity生命周期方法，只需在Activity子类中覆写这些方法即可，安卓会适时进行调用。

# 输出日志信息
Android 的   android.util.Log 类可以向系统共享日志中心发送日志信息。Log类最常用的是debug方法：

```java
public static inti d(String tag,String msg)
```

- d: 即debug，标识日志信息的级别
- 第一个参数是日志的来源。 通常以类名为值。
- 第二个参数是日志的具体内容。

例如：

```java	
public class QuizActivity extends AppCompatActivity{
	privite static final String TAG = "QuizActivity";
	……
	@Overrid
	protected void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		Log.d(Tag,"onCreate called")
	}
}
```
> 在覆写实现方式里面，超类实现方式总是在第一行调用。

# Activity 生命周期解析

1. 当GeiQuiz 应用启动并创建 Activity实例后，onCreate()-> onStart()->onResume()这三个生命周期方法依次被调用了。
此时，Activity 实例处于运行状态（在内存里、用户可见、活动在前台）
2. 当单击设备的回退键时，此时onPause()->onStop->onDestory()方法依次被调用。此时，Activity 实例处于不存在状态（不在内存里、不用户可见、不活动在前台）。
> 单击后退键，相当于 Activity不需要了。可以销毁了。
3. 在此点击应用图标，会重复第一步的生命周期方法调用。Activity从不存在变为运行状态。
4. 现在单击主屏幕键，视图消失，依次调用了，onPause()->onStop()生命周期方法。此时只是不可见，并没有调用onDestroy()方法。即Activity处于在内存中，不可见、不在前台。
> 处于停止状态的Activity 可以随时被回收，进行销毁。
5. 现在点击最近应用键, 即最右边的方格键，从中点击GeoQuiz应用，视图再次出现，依次调用了 onStart()->onResume()方法。此时不会调用onCreate,因为一直在内存中。
6. 旋转屏幕，会依次调用onStop()->onDestroy()->然后重复第一步。即系统会销毁当前Activity实例，然后创建一个新的Activity实例。



旋转屏幕时的日志如下所示：
```java
05-04 08:32:08.269 5486-5486/com.itshizhan.geoquiz D/QuizActivity: onStop……
      onDestroy……
05-04 08:32:08.302 5486-5486/com.itshizhan.geoquiz D/QuizActivity: onCreate……
05-04 08:32:08.306 5486-5486/com.itshizhan.geoquiz D/QuizActivity: onStart……
05-04 08:32:08.309 5486-5486/com.itshizhan.geoquiz D/QuizActivity: onResume……
```



#### 设备旋转时保存数据
若需要在设备旋转时保存数据，需要覆盖Activity 的 onSaveInstanceState()方法。

```java
// 保存数据
protected void onSaveInstanceState(Bundle savedInstanceState){
	……
	savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
}
// 获取数据
protected void onCreate(Bundle savedinstanceState) { 	

	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_quiz);
	Log.d(TAG,"onCreate……");
	// 必须放在initView()方法之前。
	if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
	}

	initView();
	...
}

```
此方法通常在onStop()方法之前由系统调用，除非用户按下后退键。


#### 深入日志级别

- ERROR: Log.e(), 错误
- WARNING: Log.w(), 警告
- INFO: Log.i(),信息消息
- DEBUG: Log.d(),调试输出
- VERBOSE: Log.v(),仅用于开发