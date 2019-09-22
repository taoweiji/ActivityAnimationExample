package com.taoweiji.activity.transition.example

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.drawable.Drawable
import kotlinx.android.synthetic.main.activity_animation_frame.*


class AnimationFrameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_frame)
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


        val animationDrawable2 = image_view2.drawable as AnimationDrawable
        button3.setOnClickListener {
            // 播放
            animationDrawable2.start()
        }
        button4.setOnClickListener {
            // 暂停
            animationDrawable2.stop()
        }
    }
}
