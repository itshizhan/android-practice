#  Android 开发基础

#### activity 和 layout

activity: 是android SDK 中Activity类的一个实例，负责管理用户与应用界面的交互。通常，应用的功能是通过编写Activity 的子类来实现的。

layout: 布局定义一系列用户界面对象，以及他们显示在屏幕上的位置。布局的定义保存在XML文件中。

> 布局的命名规则：颠倒Activity子类名的单词顺序并全部转为小写，然后再单词之间添加下划线。例如，Activity 名称为：`QuizActivity.java`， Layout 名称为：`activity_quiz.xml`