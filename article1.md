# Android动画一：Activity过渡动画详细实现原理
虽然 Android 5.0 之后推出了新的过渡动画方式，但通常只是用于特定的场合使用，`activity.overridePendingTransition()` 通用方式的过渡动画还是很常用。
![](https://upload-images.jianshu.io/upload_images/2431302-34c710186932a1e9.gif?imageMogr2/auto-orient/strip)

#### 原理分析

```kotlin
startActivity(Intent(this,SecondActivity::class.java))
overridePendingTransition(enterAnim, exitAnim)
```
overridePendingTransition有两个参数，第一个参数（enterAnim）是作用于SecondActivity 的`进入屏幕可见区域`效果，第二个参数（exitAnim）是作用于当前 Activity `离开屏幕可见区域`效果。
#### 示意图
![](https://upload-images.jianshu.io/upload_images/2431302-46754928497aeea3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### RightIn：从右边滑入屏幕（iOS默认效果）
iOS 默认的效果，新的Activity从右边（R）进入显示区域，当前Activity从左边离开显示区域到（L）。

enterAnim（activity_right_to_left_enter.xml）：X轴从 100% 到 0
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromXDelta="100%"
        android:toXDelta="0" />
</set>
```
exitAnim（activity_right_to_left_exit.xml）：X轴从 0 到 -100%
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromXDelta="0"
        android:toXDelta="-100%" />
</set>
```
使用
```kotlin
startActivity(Intent(this,SecondActivity::class.java))
overridePendingTransition(R.anim.activity_right_to_left_enter, R.anim.activity_right_to_left_exit)
```

##### BottomIn：从底部弹出Activity（常用效果）
一般从底部弹出新Activity，就是从B区域到屏幕可见区域，当前的Activity是保持不变的。
enterAnim（activity_bottom_to_top_enter.xml）：Y轴从 100% 到 0
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromYDelta="100%"
        android:toYDelta="0" />
</set>
```
exitAnim（no_anim.xml）：Y轴保持不变
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromYDelta="0"
        android:toYDelta="0"/>
</set>
```
使用
```kotlin
startActivity(Intent(this,SecondActivity::class.java))
overridePendingTransition(R.anim.activity_bottom_to_top_enter, R.anim.no_anim)
```

#### RightOut（和RightIn对应，iOS 默认效果）
前面讲了startActivity的转场动画，下面讲finish()的转场动画。overridePendingTransition有两个参数，第一个参数（enterAnim）是作用于上一个Activity的进入屏幕可见区域效果，第二个参数（exitAnim）是作用于当前 Activity 离开屏幕可见区域效果。

iOS默认的finish动画，是当前的Activity从屏幕可见区域到R区域，上一个Activity从L区域到屏幕可见区域。

enterAnim（activity_left_to_right_enter.xml）：X轴从 -100% 到 0
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromXDelta="-100%"
        android:toXDelta="0" />
</set>
```
exitAnim（activity_left_to_right_exit.xml）：X轴从 0 到 100%
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromXDelta="0"
        android:toXDelta="100%" />
</set>
```
使用
```kotlin
finish()
overridePendingTransition(R.anim.activity_left_to_right_enter, R.anim.activity_left_to_right_exit)
```

##### BottomOut（和BottomIn对应，常用效果）
从屏幕底部滑出效果是，当前Activity从底部滑出屏幕可见区域，上一个Activity保持不变，和BottomIn不同的是，enterAnim是不需要使用动画，因为上一个Activity已经在屏幕的后面了，只需要改变当前Activity消失的效果。
exitAnim（activity_top_to_bottom_exit.xml）：Y轴从 0 到 100%
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromYDelta="0%"
        android:toYDelta="100%" />
</set>
```
使用
```kotlin
finish()
overridePendingTransition(0, R.anim.activity_top_to_bottom_exit)
```
#### 完整示例代码

[https://github.com/taoweiji/ActivityTransitionExample](https://github.com/taoweiji/ActivityTransitionExample)
