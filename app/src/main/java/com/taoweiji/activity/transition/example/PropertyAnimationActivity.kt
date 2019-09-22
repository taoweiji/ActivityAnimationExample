package com.taoweiji.activity.transition.example

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_property_animation.*

class PropertyAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation)

//        val targetView = target_view
//        val objectAnimator = ObjectAnimator.ofFloat(targetView, "alpha", 0F, 1F)
//        objectAnimator.duration = 1000
//        objectAnimator.start()
//
//        val objectAnimator = ObjectAnimator.ofFloat(targetView, "translationY", 200f, 0F)
    }
}
