# Handler 是什么？
Handler 是android提供给我们用来更新UI的一条机制，也是一套消息处理的机制。可以通过handler 发送消息，也可以通过handler 来处理消息。



# 1. 尝试在非UI线程更新Ui

布局xml文件:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/hello_handler"
        android:gravity="center"
        android:textSize="40dp"/>
</LinearLayout>
```

activity 文件：
```java
public class HandlerTwo extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_two);
        mTextView = findViewById(R.id.text_view);

        //尝试在非UI线程更新UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mTextView.setText(R.string.update_view_text);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
```

启动应用，报错如下：
 `android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.`

因为所有的Ui控件都是非线程安全的，如果在非Ui线程更新Ui会造成Ui混乱，所以一般会在Handler中更新Ui。


#### 解决方案，通过Handler 更新即可
```java
public class HandlerTwo extends AppCompatActivity {
    private TextView mTextView;
    Handler mHandler = new Handler(); // 创建一个Handler

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_two);
        mTextView = findViewById(R.id.text_view);

        //尝试在非UI线程更新UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                  Thread.sleep(1000);
                  // mTextView.setText(R.string.update_view_text);
                  // 通过handler 的post更新UI
                  mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                      mTextView.setText(R.string.update_view_text);
                    }
                  });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
```


# 2.handler.postDelayed 实现轮播图

核心代码：
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ImageView
        android:id="@+id/image_view"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:layout_gravity="center"
        android:layout_marginTop="40dp"
        />

</LinearLayout>
```

```java
public class HandlerThree extends AppCompatActivity {

    private ImageView mImageView;
    private int[] images = {
      R.drawable.icon111,
      R.drawable.icon222,
      R.drawable.icon333
    };
    private Handler mHandler = new Handler();

    private int index;

    class MyRunnable implements Runnable{

      @Override
      public void run() {
        index++;
        index = index%3;
        mImageView.setImageResource(images[index]);
        //每个1s执行
        mHandler.postDelayed(new MyRunnable(),1000);
      }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_handler_three);
      mImageView = this.<ImageView>findViewById(R.id.image_view);
      //每个1s执行
      mHandler.postDelayed(new MyRunnable(),1000);

    }
}
```

#### 主要步骤：
- 首先创建一个Handler对象 Handler mHandler = new Handler();
- 然后创建一个Runnable对象。Runnable runnable = new Runnable{}
- 使用PostDelayed方法
- 如果需要关闭定时器，可以 mHandler.removeCallbacks(runnable);

实际上，Handler没有自己处理Delay，而是交给了MessageQueue处理

> 需要注意的是：当上一个消息存在耗时任务的时候，会占用延时任务执行的时机，此时handler.postDelayed函数的延时执行计时是不准确的。那么如何准确执行延时任务呢，可以开启一个HandlerThread为一个专门的唯一的延时消息服务。



# 3. 使用sendMessage 发送消息

xml文件：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/hello_handler"
        android:textSize="40sp"/>

</LinearLayout>
```



java文件：

```java
public class HandlerFour extends AppCompatActivity {
    private TextView mTextView;
    private Handler mHandler;
    /*
    //此方式会报：This Handler class should be static or leaks might occur 警告
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            mTextView.setText(""+msg.arg1+"--"+msg.obj);
        }
    };
    */
    static class MyHandler extends Handler{
        WeakReference<HandlerFour> mActivity;
        private MyHandler(HandlerFour activity) {
            mActivity = new WeakReference<HandlerFour>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerFour fourAcitvity = mActivity.get();
            fourAcitvity.mTextView.setText(""+msg.arg1+"="+msg.obj);

        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        mTextView = findViewById(R.id.text_view);
        mHandler = new MyHandler(this);
        // 开启线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    // Message msg = new Message();
                    Message msg = mHandler.obtainMessage();
                    msg.arg1 = 88;
                    msg.obj = "hello";

                    //mHandler.sendMessage(msg);
                    //或者 sendToTarget 等同于: target.sendMessage(this);
                    msg.sendToTarget();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

```



mHandler.obtainMessage() 实际上返回的是 `Message.obtain(this)`， 而`Message.obtain(this)` 返回的正是`new Message()`;





# 4. Handler 与Looper 与MessageQueue



1)  Handler 主要负责消息的发送，把消息发送给谁

2）Looper相对于消息封装的一个载体，其内部包含一个消息队列即MessageQueue，所有handler发送的消息都走向这个消息队列。

3) Looper.Looper 方法，就是一个死循环，不断的从消息队列MessageQueue中取消息，如果有消息就处理，如果没有消息，就阻塞。

4) MessageQueue 消息队列可以添加消息，并处理消息。

5) Handler 内部会跟Looper进行关联，找到了Looper, 即找到了MessageQueue。在handler中发送消息，其实就是向MessageQueue队列中发送消息。



> 总结： handler 负责发送消息，Looper负责接收handler发送的消息，并直接把消息回传给handler自己，而MessageQueue就是一个存储消息的容器。



