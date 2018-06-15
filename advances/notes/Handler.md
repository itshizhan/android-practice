# Handler 是什么？
Handler 是android提供给我们用来更新UI的一条机制，也是一套消息处理的机制。可以通过handler 发送消息，也可以通过handler 来处理消息。因为谷歌采用了只允许在主线程更新UI，所以作为线程通信桥梁的Handler也就应运而生了。



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



### Looper

每个线程只有一个Looper。每个线程在初始化Looper之后，Looper会维护好该线程的消息队列（MessageQueue），用来存放Handler发送的Message，并处理消息队列出队的Message。Looper是和线程绑定的，处理消息也是在Looper所在的线程去处理，所以当我们在主线程创建Handler时，它就会跟主线程唯一的Looper绑定，从而我们使用Handler在子线程发消息时，最终也是在主线程处理。

> 提示：在主线程中，ActivityThread默认会把Looper初始化好，prepare以后，当前线程就会变成一个Looper线程。



## MessageQueue

MessageQueue是一个消息队列，用来存放Handler发送的消息。每个线程最多只有一个MessageQueue。MessageQueue通常都是由Looper来管理，而主线程创建时，会创建一个默认的Looper对象，而Looper对象的创建，将自动创建一个MessageQueue。其他非主线程，不会自动创建Looper。



## Message

消息对象，就是MessageQueue里面存放的对象，一个MessageQueu可以包括多个Message。





# 5.Handler 的主要用途 

> 官方文档：
>
> There are two main uses for a Handler: (1) to schedule messages and runnables to be executed as some point in the future; and (2) to enqueue(入队) an action to be performed on a different thread than your own.



- 定时执行Messages或者Runnables
- （在子线程）把需要在另一个线程执行的操作加入到消息队列中去。



## 5.1 定时执行

**相关方法：**

 `post(Runnable)`, 

`postAtTime(Runnable, long)`,

 `postDelayed(Runnable, Object, long)`, 

`sendEmptyMessage(int)`,

`sendMessage(Message)`, 

`sendMessageAtTime(Message, long)`, 

`sendMessageDelayed(Message, long)`



- 通过Handler + Message的方式实现倒计时

  xml代码

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/textview"
        android:textSize="40sp"/>
    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button" />   
</LinearLayout>
```

java代码

```java
public class HandlerFive extends AppCompatActivity {

    private Handler mHandler;
    private TextView mTextView;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button)findViewById(R.id.button);
        
        //  此处需要优化，存在内存泄漏
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                mTextView.setText("" + msg.what);
            }
        };

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= 10; i++) {
                    Message message = Message.obtain(mHandler);
                    message.what = 10 - i;
                    //通过延迟发送消息，每隔一秒发送一条消息
                    mHandler.sendMessageDelayed(message, 1000 * i);
                }
            }
        });
    }
    
}
```



- 通过Handler + Runnable的方式实现倒计时

```java
public class HandlerSix extends AppCompatActivity {

    //private Handler mHandler;
    private TextView mTextView;
    private Button mButton;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= 10; i++) {
                    final int fadedSecond = i;
                    //每延迟一秒，发送一个Runnable对象
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(""+ (10-fadedSecond));
                        }
                    },1000*i);
                }

            }
        });
    }
}

```



> **其实，无论Handler将Runnable还是Message加入MessageQueue，最终都只是将Message加入到MessageQueue**。



## 5.2 把需要在另一个线程执行的操作加入到消息队列中去 

主要代码：

```java
// 开启一个线程
new Thread(new Runnable() {
    @Override
    public void run() {
        //在Runnable中进行网络读取操作，返回bitmap
        final Bitmap bitmap = loadPicFromInternet();
        //在子线程中实例化Handler同样是可以的，只要在构造函数的参数中传入主线程的Looper即可
        Handler handler = new Handler(Looper.getMainLooper());
        //通过Handler的post Runnable到UI线程的MessageQueue中去即可
        handler.post(new Runnable() {
            @Override
            public void run() {
                //在MessageQueue出队该Runnable时进行的操作
                mImageView.setImageBitmap(bitmap);
            }
        });
    }
}).start();
```





# 6 主要API

#### 6.1 Message类可是有很多参数的，所以你可以理解为它是一个非常丰富的JavaBean，可以看看它的成员变量：

- public int what;
- public int arg1;
- public int arg2;
- public Object obj;



#### 6.2 handler 构造函数   

- Handler()
- Handler(Handler.Callback callback)：传入一个实现的Handler.Callback接口，接口只需要实现handleMessage方法。
- Handler(Looper looper)：将Handler关联到任意一个线程的Looper，在实现子线程之间通信可以用到。
- Handler(Looper looper, Handler.Callback callback)



#### 6.3 其他

- **Looper getLooper ()**

  拿到Handler相关联的Looper

- **Message obtainMessage (int what, Object obj)**

  从消息池中拿到一个消息并赋值what和obj，其他重载函数同理。

- **boolean post (Runnable r)**

  将Runnable对象加入MessageQueue。

- **boolean postAtTime (Runnable r, Object token, long uptimeMillis)**

  在某个时间点执行Runnable r。

- **boolean postDelayed (Runnable r, long delayMillis)**

  当前时间延迟delayMillis个毫秒后执行Runnable r。

- **void removeCallbacks (Runnable r, Object token)**

  移除MessageQueue中的所有Runnable对象。

- **void removeCallbacksAndMessages (Object token)**

  移除MessageQueue中的所有Runnable和Message对象。

- **void removeMessages (int what)**

  移除所有what值得Message对象。

- **boolean sendEmptyMessage (int what)**

   直接拿到一个空的消息，并赋值what，然后发送到MessageQueue。

- **boolean sendMessageDelayed (Message msg, long delayMillis)**

  在延迟delayMillis毫秒之后发送一个Message到MessageQueue。





# 7. HandlerThread



假如我们需要同时下载A和B，下载A需要6s，下载B需要5s，在它们下载完成后Toast信息出来即可，此时HandlerThread便是一种解决方式之一。那么HandlerThread到底是什么？

- HandlerThread就是一种线程。
- HandlerThread和普通的Thread之间的区别就是HandlerThread在创建的时候会提供自己该线程的Looper对象。



我们在Actvity创建时系统会自动帮我们初始化好主线程的Looper，然后这个Looper就会管理主线程的消息队列。但是在我们创建子线程时，系统并不会帮我们创建子线程的Looper，需要我们自己手动创建，如下：

```java
  new Thread(){
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            Handler mHandler = new Handler(Looper.myLooper());
            Looper.loop();
        }
    }.start();
```



HandlerThread就在内部帮我们封装了Looper的创建过程，从源码可以看到，HandlerThread集成于Thread，然后覆写run方法，进行Looper的创建，从而通过getLooper方法暴露出该线程的Looper对象

##### 通过HandlerThread，我们可以轻松创建一个包含了Looper的子线程：

```java
final HandlerThread mHandlerThread = new HandlerThread("HandlerThread");

mHandlerThread.start();

Handler mHandler = new Handler(mHandlerThread.getLooper());
```



示例如下：模拟同时下载同载A和B文件

xml 文件：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_gravity="center"
    android:gravity="center">
    <TextView
        android:id="@+id/tv_a"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAlignment="center"
        android:textSize="30sp"
        tools:text="A任务" />
    <TextView
        android:id="@+id/tv_b"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAlignment="center"
        android:textSize="30sp"
        android:layout_marginTop="5dp"
        tools:text="B任务" />
</LinearLayout>
```

java文件：

```JAVA
public class HandlerThreadActivity extends AppCompatActivity {

    private TextView tv_A, tv_B;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthread);

        tv_A = (TextView) findViewById(R.id.tv_a);
        tv_B = (TextView) findViewById(R.id.tv_b);

        final Handler mainHandler = new Handler();

        final HandlerThread downloadAThread = new HandlerThread("downloadAThread");
        downloadAThread.start();
        Handler downloadAHandler = new Handler(downloadAThread.getLooper());

        // 通过postDelayed模拟耗时操作
        downloadAHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "下载A完成", Toast.LENGTH_SHORT).show();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_A.setText("A任务已经下载完成");
                    }
                });
            }
        }, 1000 * 15);

        final HandlerThread downloadBThread = new HandlerThread("downloadBThread");
        downloadBThread.start();
        Handler downloadBHandler = new Handler(downloadBThread.getLooper());

        // 通过postDelayed模拟耗时操作
        downloadBHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "下载B完成", Toast.LENGTH_SHORT).show();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_B.setText("B任务已经下载完成");
                    }
                });
            }
        }, 1000 * 10);

    }

}
```



# 8. ThreadLocal的内部工作机制



ThreadLocal 是一个线程内部的数据存储类。通过使用ThreadLocal，能够让同一个数据对象在不同的线程中存在多个副本，而这些副本互不影响。Looper的实现中便使用到了ThreadLocal。通过使用ThreadLocal，每个线程都有自己的Looper，它们是同一个数据对象的不同副本，并且不会相互影响。

```java
private ThreadLocal<Integer> mIntegerThreadLocal = new ThreadLocal<Integer>();
mIntegerThreadLocal.set(1);
mIntegerThreadLocal.get(); //1

```



# 9. 实例



app 启动时进行倒计时，倒计时完毕跳转到新页面：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:gravity="center"
        android:textSize="30sp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="NO DATA"
        android:id="@+id/tv_countdown"/>

</LinearLayout>

```

java代码：

```java
public class HandlerCountDownActivity extends AppCompatActivity {
    public static final int UPDATE = 0x1;
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        mTextView = (TextView) findViewById(R.id.tv_countdown);
        begin();//开启倒计时并跳转页面的方法
    }

    //Handler静态内部类
    private static class MyHandler extends Handler {
        //弱引用
        WeakReference<HandlerCountDownActivity> weakReference;
        public MyHandler(HandlerCountDownActivity activity) {
            weakReference = new WeakReference<HandlerCountDownActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            HandlerCountDownActivity activity = weakReference.get();
            if (activity != null) {
                activity.mTextView.setText(String.valueOf(msg.arg1));
            }
        }
    }
    private MyHandler handler = new MyHandler(this);

    private void begin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 5; i > 0; i--) {
                    Message msg = new Message();
                    msg.what = UPDATE;
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);//休眠1秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("tag", HandlerCountDownActivity.this + "-" + i);
                }
                //计时结束后跳转到其他界面
                startActivity(new Intent(HandlerCountDownActivity.this, 
                                         HandlerFive.class));
                //添加finish方法在任务栈中销毁倒计时界面，
                //使新开界面在回退时直接退出而不是再次返回该界面
                finish();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        Log.i("tag", "destory");
    }
}
```

