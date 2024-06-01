package com.easyhz.cmc15th_hackathon.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.presentation.ui.MainFragmentDirections

import java.lang.ref.WeakReference

object NavControllerManager {
    private var navControllerRef: WeakReference<NavController>? = null
    val backStack: NavBackStackEntry?
        get() = navControllerRef?.get()?.previousBackStackEntry

    fun init(navController: NavController) {
        this.navControllerRef = WeakReference(navController)
        setStartDestination()
    }

    /* 로그인 여부에 따른 startDestination 설정 */
    private fun setStartDestination() {
        val navController = navControllerRef?.get()
        val navGraph = navController?.navInflater?.inflate(R.navigation.nav_main)
        val startDestination = R.id.mainFragment
        navGraph?.setStartDestination(startDestination)
        navController?.graph = navGraph ?: return
    }

    fun navigateToExample() {
        val action = MainFragmentDirections.actionMainFragmentToExampleFragment()
        navControllerRef?.get()?.navigate(action)
    }

    fun getNavController(): NavController {
        return checkNotNull(navControllerRef?.get()) { "NavController is not initialized" }
    }
}