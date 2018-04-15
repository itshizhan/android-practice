# 常用adb命令

adb即：android debug bridge，一个Debug工具，可以看成是一套调试android系统的指令集合，方便开发与测试。

### 基础命令
- adb devices：查看当前连接的设备。
- adb remount: 重新挂载设备,需要root权限。
- adb shell： 进入shell。即可以操作系统的文件。可以使用基本的linux 文件操作指令，如：cd,ls,cp,rm,touch,cat等
- adb push 本地文件的路径 android系统里的路径: 把文件推入到android系统里面.
- adb pull android系统里的目标文件路径 本地路径: 把文件从android系统里面拉出了。
- adb install apk的路径： 安装应用
- adb uninstall 包名：根据包名卸载应用。（可以通过 adb logcat | grep START 抓包查看应用的包名）


### adb logcat

打印log信息
- adb logcat： 打印log	/
- adb logcat -c： 清除手机的log buffer	有些手机权限控制, 不支持.
- adb logcat -b <buffer>： 打印指定buffer的log信息	buffer有: main(主log区,默认), events(事件相关的log), radio(射频, telephony相关的log)
- adb logcat -v <format>： 格式化输出log	常用的用adb logcat -v time显示时间
- adb logcat -f <filename>： 输出log到指定文件

### adb start/kill-server: 启动杀死server进程

### 其他指令
- adb shell screencap： 截屏

例如：截屏后拉去到本地
```
adb shell screencap /sdcard/screen.png
adb pull /sdcard/screen.png ~/temp
```
- adb shell screenrecord：屏幕录制，可以做成演示demo


### 更多指令
请`adb help` 查询