# Button

```java
public class Button 
extends TextView 

java.lang.Object
   ↳	android.view.View
 	   ↳	android.widget.TextView
 	 	   ↳	android.widget.Button
 	 	   
↳ Known direct subclasses
CompoundButton
↳ Known indirect subclasses
CheckBox, RadioButton, Switch, ToggleButton


// 构造函数
public Button (Context context, 
                AttributeSet attrs, 
                int defStyleAttr, 
                int defStyleRes)
```



Button可以通过指定android:background 属性为按钮增加背景颜色或背景图片，如果将背景图片设为不规则的背景图片，则可以开发出各种不规则形状的按钮。

如果只是使用普通的背景颜色或背景图片，那么这些背景是固定的，不会随着用户的动作而改变。如果需要让按钮的背景颜色、背景图片随用户动作动态改变，则可以考虑使用自定义Drawable对象来实现。

