package com.taoweiji.activity.transition.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_view_animation1.*

class ViewAnimation1Activity : AppCompatActivity() {
    var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation1)

        radio_group.setOnCheckedChangeListener { group, checkedId ->
            val animationId = when (checkedId) {
                R.id.radio1 -> R.anim.example_alpha
                R.id.radio2 -> R.anim.example_translate
                R.id.radio3 -> R.anim.example_scale
                R.id.radio4 -> R.anim.example_rotate
                else -> R.anim.example_set
            }
            // 加载动画
            animation = AnimationUtils.loadAnimation(this, animationId)
            // 保持动画结束后的状态
            animation?.fillAfter = true
        }
        radio_group.check(R.id.radio1)
        button_start.setOnClickListener {
            target_view.startAnimation(animation)
        }
        button_reset.setOnClickListener {
            target_view.clearAnimation()
        }
    }
}
