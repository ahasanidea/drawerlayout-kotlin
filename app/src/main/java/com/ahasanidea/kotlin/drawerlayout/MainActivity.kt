package com.ahasanidea.kotlin.drawerlayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ahasanidea.kotlin.drawerlayout.fragment.FavoritesFragment
import com.ahasanidea.kotlin.drawerlayout.fragment.HomeFragment
import com.ahasanidea.kotlin.drawerlayout.fragment.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    internal lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fragmentManager = supportFragmentManager
        //main view setup
        setupView()
        if (savedInstanceState == null) showHome()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        //select navigation view item
        nav_view.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            true
        }
    }

    private fun showHome() {
        selectDrawerItem(nav_view.menu.getItem(0))
        drawer_layout.openDrawer(GravityCompat.START)
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        var specialToolbarBehaviour = false
        val fragmentClass: Class<*>

        when (menuItem.itemId) {
            R.id.drawer_home -> fragmentClass = HomeFragment::class.java
            R.id.drawer_favorites -> {
                fragmentClass = FavoritesFragment::class.java
                specialToolbarBehaviour = true
            }
            R.id.drawer_settings -> fragmentClass = SettingsFragment::class.java

            else -> fragmentClass = HomeFragment::class.java
        }
        try {
            val fragment = fragmentClass.newInstance() as Fragment
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setToolbarElevation(specialToolbarBehaviour)
        menuItem.isChecked = true
        title = menuItem.title
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun setToolbarElevation(specialToolbarBehaviour: Boolean) {
        if (specialToolbarBehaviour) {
            toolbar.elevation = 0.0f
            content_frame.elevation = resources.getDimension(R.dimen.elevation_toolbar)
        } else {
            toolbar.elevation = resources.getDimension(R.dimen.elevation_toolbar)
            content_frame.elevation = 0.0f
        }
    }

    private fun showSnackbarMessage(view: View) {}

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
