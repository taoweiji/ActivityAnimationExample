package com.taoweiji.activity.transition.example

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_override_pending_transition_target.*

class OverridePendingTransitionTargetActivity : AppCompatActivity(),
    AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ThemeOverlay_AppCompat_Dark)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_override_pending_transition_target)
        title = javaClass.simpleName
        val data = arrayOf("从右离开", "从左离开", "顶部离开", "底部离开", "淡出")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        list_view.adapter = adapter
        list_view.onItemClickListener = this

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        finish()
        when (position) {
            0 -> {
                overridePendingTransition(
                    R.anim.activity_left_to_right_enter,
                    R.anim.activity_left_to_right_exit
                )
            }
            1 -> {
                overridePendingTransition(
                    R.anim.activity_right_to_left_enter,
                    R.anim.activity_right_to_left_exit
                )
            }
            2 -> {
                overridePendingTransition(0, R.anim.activity_bottom_to_top_exit)
            }
            3 -> {
                overridePendingTransition(0, R.anim.activity_top_to_bottom_exit)
            }
            4 -> {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }
}
