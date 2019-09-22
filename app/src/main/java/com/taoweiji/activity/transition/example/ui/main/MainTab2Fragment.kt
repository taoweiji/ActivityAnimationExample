package com.taoweiji.activity.transition.example.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taoweiji.activity.transition.example.R

/**
 * A simple [Fragment] subclass.
 */
class MainTab2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_tab2, container, false)
    }


}
