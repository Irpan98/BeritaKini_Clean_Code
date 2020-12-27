package id.itborneo.core.utils.uiUtils

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.itborneo.core.R


object BottomNavigationUtils {

    fun initBottomNav(
        activity: AppCompatActivity,
        navHostFragment: Int,
    ) {
        val bottomNavigationView =
            activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        val navController = Navigation.findNavController(activity, navHostFragment)

        val navHostFragmentFind =
            activity.supportFragmentManager.findFragmentById(navHostFragment) as NavHostFragment
        val navController = navHostFragmentFind.navController


        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }


    fun visible(activity: Activity?) {
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility =
            View.VISIBLE
    }

    fun invisible(activity: Activity?) {
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility =
            View.GONE
    }
}
