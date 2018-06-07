
# RecyclerView

RecyclerView直接继承于ViewGroup父类.

```java
public class RecyclerView 
extends ViewGroup implements ScrollingView, NestedScrollingChild2

java.lang.Object
   ↳	android.view.View
 	   ↳	android.view.ViewGroup
 	 	   ↳	android.support.v7.widget.RecyclerView
```

### LayoutManager：
用来确定每一个item如何进行排列摆放，何时展示和隐藏。回收或重用一个View的时候, LayoutManager会向适配器请求新的数据来替换旧的数据
目前RecyclerView库提供了如下三种子Manager：
- LinearLayoutManager :展示了水平或者垂直的滚动列表，相当于之前学习的ListView,但是没有页头和页尾
- GridLayoutManager: 在网格中展示条目，相当于之前学习的GridView
- StaggeredGridLayoutManager: 在错落的网格中展示条目，比如常见的瀑布流

### Adapter
新型适配器
在使用recyclerview之前，需要自定义一个继承自RecyclerView.Adapter的适配器，将数据与每一个item进行绑定。编写Adapter面向的是ViewHolder而不在是View。并重写以下方法：
- onCreateViewHolder：用来展现视图和它的持有者。
- onBindViewHolder：主要用来把数据绑定到视图上。

除了上面两个主要元素，通常还会使用到如下三个类：
- ViewHolder ：维持了所有被数据充填的实体的引用。
- ItemDecoration：一个实体的周围的装饰。
- ItemAnimator：条目增加删除时重新排序产生的动画。


