package com.taoweiji.activity.transition.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.ViewAnimationUtils



class TransitionTargetActivity : AppCompatActivity() {
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
