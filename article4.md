
# Android动画四：Animator 使用教程详解
Android 动画主要分为3种：
1. 逐帧动画（Drawable Animation）
2. 视图动画/补间动画（View Animation）
3. 属性动画（Property Animation）

### 逐帧动画

逐帧动画是通过把一组图片依次播放，形成动画效果，类似gif的效果。实现方式非常简单，可以通过Java或者XML方式实现，首先要放入一组图片到资源文件中。

##### Java 方式

初始化一个 AnimationDrawable 对象，添加一组 Drawable 图片即可。

```kotlin
val animationDrawable = AnimationDrawable()
for (i in 0..9) {
    val id = resources.getIdentifier("frame$i", "drawable", packageName)
    val drawable = resources.getDrawable(id)
    animationDrawable.addFrame(drawable, 100)
}
// 是否只播放一次
animationDrawable.isOneShot = false
image_view.setImageDrawable(animationDrawable)
button1.setOnClickListener {
    // 播放
    animationDrawable.start()
}
button2.setOnClickListener {
    // 暂停
    animationDrawable.stop()
}
```

##### XML 方式

在 drawable 目录下创建一个 img_frame.xml 文件，`android:oneshot` 是控制是否只播放一次。

```xml
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android" android:oneshot="false">
    <item android:drawable="@drawable/frame0" android:duration="100"/>
    <item android:drawable="@drawable/frame1" android:duration="100"/>
    <item android:drawable="@drawable/frame2" android:duration="100"/>
    <item android:drawable="@drawable/frame3" android:duration="100"/>
    <item android:drawable="@drawable/frame4" android:duration="100"/>
    <item android:drawable="@drawable/frame5" android:duration="100"/>
    <item android:drawable="@drawable/frame6" android:duration="100"/>
    <item android:drawable="@drawable/frame7" android:duration="100"/>
    <item android:drawable="@drawable/frame8" android:duration="100"/>
    <item android:drawable="@drawable/frame9" android:duration="100"/>
</animation-list>
```

在布局文件中设置资源

```xml
<ImageView
    android:id="@+id/image_view2"
    android:layout_width="100dp"
    android:src="@drawable/img_frame"
    android:layout_height="100dp"
    android:layout_marginTop="50dp" />
```

java

```kotlin
val animationDrawable = image_view.drawable as AnimationDrawable
button1.setOnClickListener {
    // 播放
    animationDrawable.start()
}
button2.setOnClickListener {
    // 暂停
    animationDrawable.stop()
}
```

### 视图动画/补间动画

视图动画是指通过代码定义开始和结束的 `关键帧` 位置或效果，由SDK实现补充中间的效果，从而实现动画，可以通过xml或者纯Java代码实现，5.0之前 Activity 的转场动画就是通过补间动画实现，补间动画有4种方式：

1. 透明度（淡入淡出）：alpha
2. 位移：translate
3. 缩放：scale
4. 旋转：rotate

#### XML 实现

##### 透明度：alpha

`android:fromAlpha` 是起始透明度， `android:toAlpha`结束透明度。

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="300"
    android:fromAlpha="1.0"
    android:toAlpha="0.0" />
```

##### 位移：translate

```xml
<?xml version="1.0" encoding="utf-8"?>
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="300"
    android:fromXDelta="0"
    android:fromYDelta="100%"
    android:toXDelta="100%"
    android:toYDelta="0" />
```

##### 缩放：scale

`android:pivotX` 和 `android:pivotY` 是设置缩放的中心点，可以写百分比，也可以写绝对值。

```xml
<?xml version="1.0" encoding="utf-8"?>
<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="300"
    android:fromXScale="0.0"
    android:fromYScale="0.0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:toXScale="1.0"
    android:toYScale="1.0"/>
```

##### 旋转：rotate

`android:pivotX` 和 `android:pivotY` 是设置旋转的中心点。

`android:fromDegrees` 是开始角度，`android:toDegrees` 是结束角度。

```xml
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="300"
    android:fromDegrees="0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:toDegrees="359"
    android:drawable=""
    android:visible="false"/>
```

##### 组合动画

可以通过<set/> 组合多个动画，通过 android:duration 设置每一个动画的播放时长。

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <alpha
        android:duration="300"
        android:fromAlpha="1.0"
        android:toAlpha="0.0" />
    <translate
        android:duration="300"
        android:fromXDelta="0"
        android:fromYDelta="100%"
        android:toXDelta="100%"
        android:toYDelta="0" />
</set>
```

##### 使用方式

```kotlin
// 加载动画
val animation = AnimationUtils.loadAnimation(this, R.anim.example_alpha)
// 保持动画结束后的状态
animation?.fillAfter = true
// 开始动画
target_view.startAnimation(animation)
// 还原动画起始状态
target_view.clearAnimation()
```

#### Java 实现

```kotlin
// 透明度
val alphaAnimation = AlphaAnimation(fromAlpha, toAlpha)
// 位移
val translateAnimation = TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta)
// 缩放
val scaleAnimation = ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue)
// 旋转
val rotateAnimation = RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY)

// 设置间隔
translateAnimation.duration = 1000

// 动画组合
val animationSet = AnimationSet(true)
animationSet.addAnimation(alphaAnimation)
animationSet.addAnimation(translateAnimation)
animationSet.addAnimation(scaleAnimation)
animationSet.addAnimation(rotateAnimation)

// 开始动画
target_view.startAnimation(animationSet)
```

### 属性动画

属性动画很好理解，就是通过反射实现动态改变View的属性，比如透明度，宽高等，让其实现各种动画。

```kotlin
// 通过反射设置 setAlpha(float) 方法
val objectAnimator = ObjectAnimator.ofFloat(targetView, "alpha", 0F, 1F)
objectAnimator.duration = 1000
objectAnimator.start()
// 通过反射设置 setTranslationY(float) 方法
val objectAnimator = ObjectAnimator.ofFloat(targetView, "translationY", 200f, 0F)
```