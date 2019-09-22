package com.taoweiji.activity.transition.example.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taoweiji.activity.transition.example.R
import com.taoweiji.activity.transition.example.TransitionActivity
import kotlinx.android.synthetic.main.fragment_main_tab3.*

class MainTab3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button1.setOnClickListener {
            startActivity(Intent(activity, TransitionActivity::class.java))
        }
    }
}
