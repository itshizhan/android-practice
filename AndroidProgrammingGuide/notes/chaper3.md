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

