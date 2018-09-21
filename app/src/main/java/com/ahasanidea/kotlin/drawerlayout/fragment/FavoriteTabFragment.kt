package com.ahasanidea.kotlin.drawerlayout.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.ahasanidea.kotlin.drawerlayout.R



/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class FavoriteTabFragment(position: Int) : Fragment() {
    private var mPosition: Int = 0

    init {
        mPosition = position
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView=inflater.inflate(R.layout.fragment_favorite_tab, container, false)
        rootView.findViewById<TextView>(R.id.fav_number).text="Favorite"+mPosition
        return rootView
    }


}
