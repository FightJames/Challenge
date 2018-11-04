package com.techapp.james.bottomnavigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var selectFlag = true
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (selectFlag) {
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.addToBackStack("home")
                    transaction.replace(R.id.fragment, BlankFragment.newInstance("Home", true))
                    transaction.commit()
                }
            }
            R.id.navigation_dashboard -> {
                if (selectFlag) {
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.addToBackStack("dashboard")
                    transaction.replace(R.id.fragment, BlankFragment.newInstance("Dashboard", false))
                    transaction.commit()
                }
            }
            R.id.navigation_notifications -> {
                if (selectFlag) {
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.addToBackStack("notification")
                    transaction.replace(R.id.fragment, BlankFragment.newInstance("Notification", false))
                    transaction.commit()
                }
            }
        }
//        Log.d("Count ", "${supportFragmentManager.backStackEntryCount}")
        selectFlag = true
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home

    }

    override fun onBackPressed() {
        getCurrentFragment()
        super.onBackPressed()
        var tag = getCurrentFragment()
        selectFlag = false
        if (tag == null) {
            finish()
        } else {
            when (tag) {
                "home" -> {
                    navigation.selectedItemId = R.id.navigation_home
                }
                "dashboard" -> {
                    navigation.selectedItemId = R.id.navigation_dashboard

                }
                "notification" -> {
                    navigation.selectedItemId = R.id.navigation_notifications
                }
            }
        }
    }

    private fun getCurrentFragment(): String? {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            val fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
//        return fragmentManager.findFragmentByTag(fragmentTag)
            return fragmentTag!!
        }
        return null
    }
}
