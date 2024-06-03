package com.easyhz.cmc15th_hackathon.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.presentation.ui.MainFragmentDirections
import com.easyhz.cmc15th_hackathon.presentation.ui.filter.FilterFragmentDirections
import com.easyhz.cmc15th_hackathon.presentation.ui.random.RandomFragmentDirections
import com.easyhz.cmc15th_hackathon.presentation.ui.random_result.RandomResultFragmentDirections
import com.easyhz.cmc15th_hackathon.presentation.ui.result.ResultFragmentDirections

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

    fun navigateToResult() {
        val action = MainFragmentDirections.actionMainFragmentToResultFragment()
        navControllerRef?.get()?.navigate(action)
    }

    fun navigateFilterToResult() {
        val action = FilterFragmentDirections.actionFilterFragmentToResultFragment()
        navControllerRef?.get()?.navigate(action)
    }

    fun navigateToRandom() {
        val action = ResultFragmentDirections.actionResultFragmentToRandomFragment("P")
        navControllerRef?.get()?.navigate(action)
    }

    fun navigateMainToRandom() {
        val action = MainFragmentDirections.actionMainFragmentToRandomFragment("F")
        navControllerRef?.get()?.navigate(action)
    }

    fun navigateToRandomResult(cate: String) {
        val action = RandomFragmentDirections.actionRandomFragmentToRandomResultFragment(cate)
        navControllerRef?.get()?.navigate(action)
    }

    fun navigateToHome() {
        val action = RandomResultFragmentDirections.actionRandomResultFragmentToMainFragment()
        navControllerRef?.get()?.navigate(action)
    }

    fun navigateToFilter() {
        val action = MainFragmentDirections.actionMainFragmentToFilterFragment()
        navControllerRef?.get()?.navigate(action)
    }

    fun getNavController(): NavController {
        return checkNotNull(navControllerRef?.get()) { "NavController is not initialized" }
    }
}