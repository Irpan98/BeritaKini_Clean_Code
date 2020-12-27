package id.itborneo.beritakini.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.itborneo.beritakini.R
import id.itborneo.core.ui.theme.DayNight
import id.itborneo.core.utils.uiUtils.BottomNavigationUtils

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DayNight.disableDayNight()

        BottomNavigationUtils.initBottomNav(this, R.id.MainNavHostFragment)

    }
}