

# Android动画五：ConstraintLayout 通过XML和Java两种方式实现动画

ConstraintLayout 动画的实现有两种方式实现，一种是 XML 方式实现，另外一种是Java，两者各有优点，下面就分别介绍两种实现方式，只要会 ConstraintLayout ，实现都非常简单。动画对字体、背景颜色、字体颜色、text、padding等都不生效，只对布局约束改变生效。

当阅读这篇文章，我已经假设读者已经知道了 ConstraintLayout 的基本用法，如果不知道的请先学习 [ConstraintLayout](https://www.jianshu.com/p/8d2658b279b0)。

![](https://upload-images.jianshu.io/upload_images/2431302-b73fb7cd03b8b73d.gif?imageMogr2/auto-orient/strip)


##### 添加依赖
```
dependencies {
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.transition:transition:1.1.0'
}
```


### XML 代码实现
这种方式非常的简单，写好一个正常的ConstraintLayout布局，然后编写一个变化后的布局，然后使用Java代码关联即可。
1. 变化前后的控件 ID 必须保持一致，否则不生效。
2. 只需要编写需要改变的控件，不需要改变的控件无需编写代码。

##### constraint_layout_normal.xml
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/constraint_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ImageView
        android:id="@+id/image_view"
        android:layout_width="0dp"
        app:layout_constraintWidth_percent="0.4"
        android:layout_height="0dp"
        android:background="@android:color/holo_red_dark"
        app:layout_constraintDimensionRatio="H,16:9"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
    <TextView
        android:id="@+id/content"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:ellipsize="end"
        android:padding="10dp"
        android:text="经过许多个冬天之后，我才渐渐明白自己再躲不过雪，无论我残缩在屋子里，还是远在冬天的另一个地方，纷纷扬扬的雪，都会落在我正经历的一段岁月里。当一个人的岁月像荒野一样敞开时，他便再无法照管好自己。就像现在，我紧围着火炉，努力想烤热自己。我的一根骨头．却露在屋外的寒风中，隐隐作疼。那是我多年前冻坏的一根骨头，我再不能像捡一根牛骨头一样，把它捡回到火炉旁烤熟。它永远地冻坏在那段天亮前的雪路上了。那个冬天我十四岁，赶着牛车去沙漠里拉柴禾。那时一村人都是靠长在沙漠里的一种叫梭梭的灌木取暖过冬。因为不断砍挖，有柴禾的地方越来越远。往往要用一天半夜时间才能拉回一车柴禾。每次拉柴禾，都是母亲半夜起来做好饭，装好水和馍馍，然后叫醒我。有时父亲也会起来帮我套好车。我对寒冷的认识是从那些夜晚开始的。 "
        app:layout_constrainedHeight="true"
        app:layout_constraintBottom_toBottomOf="@+id/image_view"
        app:layout_constraintLeft_toRightOf="@+id/image_view"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
    <Button
        android:id="@+id/start"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="开始"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent" />
    <Button
        android:id="@+id/recover"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="恢复"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintRight_toRightOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>

```
##### constraint_layout_animation.xml
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/constraint_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
            android:id="@+id/image_view"
            android:layout_width="0dp"
            app:layout_constraintWidth_percent="1"
            android:layout_height="0dp"
            android:background="@android:color/holo_red_dark"
            app:layout_constraintDimensionRatio="H,16:9"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    <TextView
            android:id="@+id/content"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:text="经过许多个冬天之后..."
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/image_view" />
</androidx.constraintlayout.widget.ConstraintLayout>
```
##### MainActivity.java
```
public class MainActivity extends AppCompatActivity {
    private ConstraintSet constraintSet1;
    private ConstraintSet constraintSet2;
    private ConstraintLayout constraint_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout_normal);
        constraintSet1 = new ConstraintSet();
        constraintSet2 = new ConstraintSet();
        constraint_layout = findViewById(R.id.constraint_layout);
        constraintSet1.clone(constraint_layout);
        constraintSet2.clone(this, R.layout.constraint_layout_animation);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoTransition transition = new AutoTransition();
                TransitionManager.beginDelayedTransition(constraint_layout, transition);
                constraintSet2.applyTo(constraint_layout);
            }
        });
        findViewById(R.id.recover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoTransition transition = new AutoTransition();
                transition.setDuration(300);
                TransitionManager.beginDelayedTransition(constraint_layout, transition);
                constraintSet1.applyTo(constraint_layout);
            }
        });
    }
}
```
![](https://upload-images.jianshu.io/upload_images/2431302-115ed3c4eba5702f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### Java 代码实现
也可以通过Java代码手动控制控件的位置，下面的代码等同上面的效果。
```
public class MainActivity extends AppCompatActivity {
    private ConstraintSet constraintSet1;
    private ConstraintSet constraintSet2;
    private ConstraintLayout constraint_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout_normal);
        constraintSet1 = new ConstraintSet();
        constraintSet2 = new ConstraintSet();
        constraint_layout = findViewById(R.id.constraint_layout);
        constraintSet1.clone(constraint_layout);
        constraintSet2.clone(constraint_layout);

        // image_view 的右边缘在父布局的右边缘
        constraintSet2.connect(R.id.image_view,ConstraintSet.RIGHT,R.id.constraint_layout,ConstraintSet.RIGHT);
        // image_view 占父布局宽度的百分百
        constraintSet2.constrainPercentWidth(R.id.image_view,1);

        // content 的顶部在 image_view 的下方
        constraintSet2.connect(R.id.content,ConstraintSet.TOP,R.id.image_view,ConstraintSet.BOTTOM);
        // content 的左边缘在父布局的左边缘
        constraintSet2.connect(R.id.content,ConstraintSet.LEFT,R.id.constraint_layout,ConstraintSet.LEFT);
        // content 的右边缘在父布局的右边缘
        constraintSet2.connect(R.id.content,ConstraintSet.RIGHT,R.id.constraint_layout,ConstraintSet.RIGHT);
        // 取消 content 的底部边缘设置
        constraintSet2.clear(R.id.content,ConstraintSet.BOTTOM);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoTransition transition = new AutoTransition();
                TransitionManager.beginDelayedTransition(constraint_layout, transition);
                constraintSet2.applyTo(constraint_layout);
            }
        });
        findViewById(R.id.recover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoTransition transition = new AutoTransition();
                transition.setDuration(300);
                TransitionManager.beginDelayedTransition(constraint_layout, transition);
                constraintSet1.applyTo(constraint_layout);
            }
        });
    }
}
```

