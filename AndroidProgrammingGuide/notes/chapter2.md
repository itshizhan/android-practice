# android 与mvc 设计模式

android 应用基于模型-视图-控制器（Model-View-Controller，mvc）的架构模式进行设计。

- 模型对象：存储应用的数据和业务逻辑。通常用来映射与应用相关的一些事物。如商店里面的商品，服务器上的图片。模型通常是不关心用户界面的，他为存储和管理应用的数据而生。

- 视图：视图对象知道如果在屏幕上绘制自己，以及如何响应用户的输入等。一个经验法则是：凡是能在屏幕上看见的对象，就是视图对象。例如Activity.xml 中定义的各种组件即构成了视图。

- 控制器：含有应用的逻辑单元，是视图对象与模型对象的联系纽带。控制器对象响应视图对象中触发的各类事件，此外还管理着模型对象与视图层间的数据流动。

### 如何在xml文件中引用资源？

例如：设置文字+icon的Button按钮,或设置图片按钮
```xml
<Button
	android:id="@+id/next_button" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="@string/next_button" android:drawableRight="@drawable/arrow_right" android:drawablePadding="4dp" />

<ImageButton
	android:id="@+id/next_button"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:drawablePadding="4dp"
	android:src="@drawable/arrow_right"
	android:contentDescription="@string/next_button" />
```

> 提示，为了为视力障碍用户提供方面，如果是ImageButton，必须设置`android:contentDescription` 属性，即可以阅读按钮的描述文字。