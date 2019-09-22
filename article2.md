
# Android动画二：5.0 实现复杂 Activity 过渡动画方式：分解动画、共享元素、滑动动画等
在 5.0 之前我们可以通过 `activity.overridePendingTransition()` 实现滑动动画、渐变动画、缩放动画等。在 5.0 版本之后，可以使用 `android.transition.*` 实现复杂的动画效果。
![](https://upload-images.jianshu.io/upload_images/2431302-271749ec9100b8a2.gif?imageMogr2/auto-orient/strip)
### 教程
#### 分解动画
```kotlin
val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
startActivity(Intent(activity, TargetActivity::class.java), bundle)
```
TransitionTargetActivity.kt
```kotlin
class TargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        val explode = Explode()
        window.enterTransition = explode
        window.exitTransition = explode
    }
}
```
#### 共享元素动画
共享元素动画的方式也很简单，只需要在跳转时候带上前面页面的View，并设置名称，在第二个Activity的xml设置`android:transitionName`，填写对应的名称即可。
```kotlin
 // 共享
button_pair1.setOnClickListener {
  val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,button_pair1,"button_pair1").toBundle()
  startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","pair"), bundle)
}
```

activity_target.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <Button
        android:id="@+id/button_pair1"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_marginTop="20dp"
        android:text="共享元素一"
        android:transitionName="button_pair1" />
</LinearLayout>
```
TransitionTargetActivity.kt
```kotlin
class TargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
    }
}
```

#### 滑动动画
```kotlin
val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
startActivity(Intent(activity, TargetActivity::class.java), bundle)
```
TransitionTargetActivity.kt
```kotlin
class TargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        val slide = Slide(Gravity.LEFT)
        window.enterTransition = slide
        window.exitTransition = slide
    }
}
```

#### 淡入淡出
```kotlin
val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
startActivity(Intent(activity, TargetActivity::class.java), bundle)
```
TransitionTargetActivity.kt
```kotlin
class TargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        val fade = Fade().setDuration(1000)
        window.enterTransition = fade
        window.exitTransition = fade
    }
}
```


### 完整示例代码
```kotlin
class MainTab1Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_tab1, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 分解
        button_explode1.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","explode"), bundle)
        }
        // 共享
        button_pair1.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,button_pair1,"button_pair1").toBundle()
            startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","pair"), bundle)
        }
        button_pair2.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,button_pair2,"button_pair2").toBundle()
            startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","pair"), bundle)
        }
        button_pair3.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,button_pair3,"button_pair3").toBundle()
            startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","pair"), bundle)
        }
        // 滑动
        button_slide1.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","slide1"), bundle)
        }
        button_slide2.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","slide2"), bundle)
        }
        // 淡入淡出
        button_fade1.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, TargetActivity::class.java).putExtra("animation","fade"), bundle)
        }
    }
}
```
TransitionTargetActivity.kt
```
class TargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val animationType = intent.getStringExtra("animation")
        when (animationType) {
            "explode" -> {
                val explode = Explode()
                window.enterTransition = explode
                window.exitTransition = explode
            }
            "pair" -> {
            }
            "slide1" -> {
                val explode = Slide()
                window.enterTransition = explode
                window.exitTransition = explode
            }
            "slide2" -> {
                val slide = Slide(Gravity.LEFT)
                window.enterTransition = slide
                window.exitTransition = slide
            }
            "fade" -> {
                val fade = Fade().setDuration(1000)
                window.enterTransition = fade
                window.exitTransition = fade
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
```
activity_target.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/constraintLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="20dp">
    <Button
        android:id="@+id/button_explode1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="分解" />
    <Button
        android:id="@+id/button_pair1"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_marginTop="20dp"
        android:text="共享元素一"
        android:transitionName="button_pair1" />
    <Button
        android:id="@+id/button_pair2"
        android:layout_width="match_parent"
        android:layout_height="150dp"
        android:text="共享元素二"
        android:transitionName="button_pair2" />
    <Button
        android:id="@+id/button_pair3"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:text="共享元素三"
        android:transitionName="button_pair3" />
    <Button
        android:id="@+id/button_slide1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="滑动一" />
    <Button
        android:id="@+id/button_slide2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="滑动二" />
    <Button
        android:id="@+id/button_fade1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="淡入淡出" />
</LinearLayout>
```
