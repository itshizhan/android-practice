# RecyclerView

RecyclerView 是Android 5.X新增的一个列表组件，被认为是ListView和GridView控件的替代者，在最新的support-V7版本中提供支持。

#### RecyclerView直接继承于ViewGroup父类。要使用RecyclerView，需要先了解清楚LayoutManager和Adapter元素

### LayoutManager

LayoutManager用来确定每一个item如何进行排摆放，如何展示和隐藏。回收或重利用view的时候，LayoutManager会向适配器请求新的数据来替换旧的数据，这种机制避免了创建过多的View和频繁的调用findViewById方法。

**目前RecyclerView提供了三种子Manager：**

- LinearLayoutManager：创建水平或垂直滚动列表，相关之前的listview，但是没有页眉页尾。
- GridLayoutManager：在网格中展示条目，相当之前的GridView。
- StaggeredGridLayoutManager： 在错落的网格中展示条目，比如常见的瀑布流。



### Adapter

一种新型的适配器，在使用RecyclerView之前，需要创建(自定义)一个继承自**RecyclerView.Adapter** 的适配器。

 将数据与每一个itme的界面进行绑定。

**编写Adapter面向的是ViewHolder而不在是View了，复用的逻辑被封装了起来，实现更加简单。使用时需要重写以下两个主要方法：**

-  onCreateViewHolder：用来展现视图和它的持有者。
- onBindViewHolder：主要用来把数据绑定到视图上。



 **除了上面两个主要元素，通常还会使用到如下三个类：**

- ViewHolder：维持了所有被数据填充的实体的视图的引用。
- ItemDecoration：一个实体的周围的装饰。
- ItemAnimator：条目增加删除时重新排序所产生动画。







