package com.asterisks.onesec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.asterisks.onesec.databinding.ActivityMainBinding
import com.asterisks.onesec.map.CrimeMapFragment
import com.asterisks.onesec.notification.CrimeNotificationFragment
import com.asterisks.onesec.pastReports.PastReportsFragment
import com.asterisks.onesec.report.CrimeReportFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    var bottomNavListner =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.crime_map->{
                        val crimeMapFragment = CrimeMapFragment()
                        addFragment(crimeMapFragment)
                        mainBinding.mainToolbar.title = "Map"
                        return true

                    }
                    R.id.report->{
                        val crimeReportFragment = CrimeReportFragment()
                        addFragment(crimeReportFragment)
                        mainBinding.mainToolbar.title = "Report"
                        return true

                    }
                    R.id.crime_notification->{
                        val crimeNotificationFragment = CrimeNotificationFragment()
                        addFragment(crimeNotificationFragment)
                        mainBinding.mainToolbar.title = "Alerts"
                        return true

                    }
                    R.id.pastReports->{
                        val pastReportFragment = PastReportsFragment()
                        addFragment(pastReportFragment)
                        mainBinding.mainToolbar.title = "Past Reports"
                        return true

                    }
                }
                return false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        mainBinding.bottomNav.setOnNavigationItemSelectedListener(bottomNavListner)
        mainBinding.bottomNav.selectedItemId = R.id.report

    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment, fragment::class.java.simpleName)
            .commit()
    }
}
