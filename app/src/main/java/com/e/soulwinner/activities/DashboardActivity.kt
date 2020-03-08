package com.e.soulwinner.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.e.soulwinner.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.dashboard_main.*


class DashboardActivity : AppCompatActivity() {
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)
        setupDrawerToggle()
    }

    fun setupDrawerToggle() {
        getSupportActionBar()?.setTitle("")
        drawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.setToolbarNavigationClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        drawerToggle.setDrawerIndicatorEnabled(false)
        drawerToggle.syncState()
        drawerToggle.setHomeAsUpIndicator(R.drawable.menu_icon)
    }


}
