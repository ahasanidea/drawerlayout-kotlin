package com.ahasanidea.kotlin.drawerlayout.fragment


import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahasanidea.kotlin.drawerlayout.FabActivity

import com.ahasanidea.kotlin.drawerlayout.R



/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView=inflater.inflate(R.layout.fragment_settings, container, false)
        val fab=rootView.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(activity, FabActivity::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                activity?.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
            else
                startActivity(intent)
        }
        return rootView
    }


}
