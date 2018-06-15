# EditText

```java
public class EditText 
extends TextView 

java.lang.Object
   ↳	android.view.View
 	   ↳	android.widget.TextView
 	 	   ↳	android.widget.EditText
 	 	   
↳ Known direct subclasses
AutoCompleteTextView, ExtractEditText
↳ Known indirect subclasses
MultiAutoCompleteTextView

```



EditText 继承自TextView，甚至与TextView 共用了绝大部分XML属性和方法。EditText与TextView的最大区别在于：EditText可以接受用户输入。

其中比较重要的一个属性是inputType，用于为EditText设置输入类型，其属性值主要有以下一些。

- android:inputType=none：普通字符。
- android:inputType=text：普通字符。
- android:inputType=textCapCharacters：字母大写。
- android:inputType=textCapWords：首字母大写。
- android:inputType=textCapSentences：仅第一个字母大写。
- android:inputType=textAutoCorrect：自动完成。
- android:inputType=textAutoComplete：自动完成。
- android:inputType=textMultiLine：多行输入。
- android:inputType=textImeMultiLine：输入法多行（如果支持）。
- android:inputType=textNoSuggestions：不提示。
- android:inputType=textUri：网址。
- android:inputType=textEmailAddress：电子邮件地址。
- android:inputType=textEmailSubject：邮件主题。
- android:inputType=textShortMessage：短讯。
- android:inputType=textLongMessage：长信息。
- android:inputType=textPersonName：人名。
- android:inputType=textPostalAddress：地址。
- android:inputType=textPassword：密码。
- android:inputType=textVisiblePassword：可见密码。
- android:inputType=textWebEditText：作为网页表单的文本。
- android:inputType=textFilter：文本筛选过滤。
- android:inputType=textPhonetic：拼音输入。
- android:inputType=number：数字。
- android:inputType=numberSigned：带符号数字格式。
- android:inputType=numberDecimal：带小数点的浮点格式。
- android:inputType=phone：拨号键盘。
- android:inputType=datetime：时间日期。
- android:inputType=date：日期键盘。
- android:inputType=time：时间键盘。