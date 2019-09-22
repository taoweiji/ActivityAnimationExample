
# Android动画三：使用 Transition 最简单方式实现动画
在 `android.view.animation.*`包下有多种实现动画的方式，有逐帧动画、补间动画、属性动画，可以实现复杂的动画。其实在 `android.transition.*` 或 `androidx.transition.*` 下面有非常简单的实现动画方式，同样可以实现复杂的动画，使用方式非常简单。

![](https://upload-images.jianshu.io/upload_images/2431302-60e1197fb72b572b.gif?imageMogr2/auto-orient/strip)


##### 教程
实现动画的方式非常简单，只需要在改变显示方式之前调用 `TransitionManager.beginDelayedTransition` 即可，支持改变显示、隐藏、大小修改、位置修改。setDuration() 设置动画的时长。
```kotlin
// 设置动画方式 - 默认
TransitionManager.beginDelayedTransition(rootView)
button.visibility = View.VISIBLE

// 设置动画方式 - 淡入淡出
val fade = Fade().setDuration(1000)
TransitionManager.beginDelayedTransition(rootView, fade)
button.visibility = View.VISIBLE

// 设置动画方式 - 右边滑动
val slide = Slide(Gravity.RIGHT).setDuration(1000)
TransitionManager.beginDelayedTransition(rootView, slide)
button.visibility = View.VISIBLE

// 分离 Explode()
```

##### 完整代码
TransitionActivity.kt
```kotlin
class TransitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = LinearLayout(this)
        rootView.orientation = LinearLayout.VERTICAL
        setContentView(rootView)
        val viewGroup1 = getTemplate("无动画", Runnable {

        })
        val viewGroup2 = getTemplate("默认动画", Runnable {
            TransitionManager.beginDelayedTransition(rootView)
        })
        val viewGroup3 = getTemplate("淡入淡出", Runnable {
            TransitionManager.beginDelayedTransition(rootView, Fade().setDuration(1000))
        })
        val viewGroup4 = getTemplate("滑动动画", Runnable {
            TransitionManager.beginDelayedTransition(rootView, Slide().setDuration(1000))
        })
        val viewGroup5 = getTemplate("滑动动画 - 右边", Runnable {
            TransitionManager.beginDelayedTransition(rootView, Slide(Gravity.RIGHT).setDuration(1000))
        })
        rootView.addView(viewGroup1)
        rootView.addView(viewGroup2)
        rootView.addView(viewGroup3)
        rootView.addView(viewGroup4)
        rootView.addView(viewGroup5)
    }

    private fun getTemplate(text: String, runnable: Runnable): ViewGroup {
        val viewGroup = LayoutInflater.from(this).inflate(R.layout.view_transition, null) as ViewGroup
        viewGroup.target.text = text
        viewGroup.button1.setOnClickListener {
            runnable.run()
            viewGroup.target.visibility = View.INVISIBLE
        }
        viewGroup.button2.setOnClickListener {
            runnable.run()
            viewGroup.target.visibility = View.VISIBLE
        }
        viewGroup.button3.setOnClickListener {
            runnable.run()
            val layoutParams = viewGroup.target.layoutParams as LinearLayout.LayoutParams
            layoutParams.width = 300
            layoutParams.height = 200
            layoutParams.topMargin = 100
            viewGroup.target.layoutParams = layoutParams
        }
        viewGroup.button4.setOnClickListener {
            runnable.run()
            val layoutParams = viewGroup.target.layoutParams as LinearLayout.LayoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            layoutParams.topMargin = 0
            viewGroup.target.layoutParams = layoutParams
        }
        return viewGroup
    }
}

```
view_transition.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="10dp">
    <Button
        android:id="@+id/target"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="默认动画" />
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <Button
            android:id="@+id/button1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="隐藏" />
        <Button
            android:id="@+id/button2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="显示" />
        <Button
            android:id="@+id/button3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="放大" />
        <Button
            android:id="@+id/button4"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="缩小" />
    </LinearLayout>
</LinearLayout>
```
