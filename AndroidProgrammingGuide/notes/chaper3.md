# Activity 的生命周期

#### 当应用旋转时，界面状态会进行重置？？

每个Activity实例都有其生命周期，在其生命周期内，Activity总是在运行、暂停、停止、不存在四种状态间转换。

每次转换时，都有相应的方法发送消息，通知Activity。

<img src="/usr/local/var/www/gitclone/android-practice/AndroidProgrammingGuide/notes/images/android-lifecycle.png"/>