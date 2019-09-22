package com.taoweiji.activity.transition.example.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.taoweiji.activity.transition.example.OverridePendingTransitionTargetActivity
import com.taoweiji.activity.transition.example.R
import kotlinx.android.synthetic.main.fragment_main_tab2.*

/**
 * A simple [Fragment] subclass.
 */
class MainTab2Fragment : Fragment(), AdapterView.OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_tab2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arrayOf("从右进入", "从左进入", "顶部进入", "底部进入", "淡入")
        val adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, data)
        list_view.adapter = adapter
        list_view.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        startActivity(Intent(activity, OverridePendingTransitionTargetActivity::class.java))

        when (position) {
            0 -> {
                activity?.overridePendingTransition(
                    R.anim.activity_right_to_left_enter,
                    R.anim.activity_right_to_left_exit
                )
            }
            1 -> {
                activity?.overridePendingTransition(
                    R.anim.activity_left_to_right_enter,
                    R.anim.activity_left_to_right_exit
                )
            }
            2 -> {
                activity?.overridePendingTransition(
                    R.anim.activity_top_to_bottom_enter,
                    R.anim.no_anim
                )
            }
            3 -> {
                activity?.overridePendingTransition(
                    R.anim.activity_bottom_to_top_enter,
                    R.anim.no_anim
                )
            }
            4 -> {
                activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }
}
