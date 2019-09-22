package com.taoweiji.activity.transition.example.ui.main


import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.taoweiji.activity.transition.example.*
import kotlinx.android.synthetic.main.fragment_main_tab1.*


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
            startActivity(Intent(activity, TransitionTargetActivity::class.java).putExtra("animation","explode"), bundle)
        }
        // 共享
        button_pair1.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,button_pair1,"button_pair1").toBundle()
            startActivity(Intent(activity, TransitionTargetActivity::class.java).putExtra("animation","pair"), bundle)
        }
        button_pair2.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,button_pair2,"button_pair2").toBundle()
            startActivity(Intent(activity, TransitionTargetActivity::class.java).putExtra("animation","pair"), bundle)
        }
        button_pair3.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,button_pair3,"button_pair3").toBundle()
            startActivity(Intent(activity, TransitionTargetActivity::class.java).putExtra("animation","pair"), bundle)
        }
        // 滑动
        button_slide1.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, TransitionTargetActivity::class.java).putExtra("animation","slide1"), bundle)
        }
        button_slide2.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, TransitionTargetActivity::class.java).putExtra("animation","slide2"), bundle)
        }
        // 淡入淡出
        button_fade1.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, TransitionTargetActivity::class.java).putExtra("animation","fade"), bundle)
        }
    }
}
