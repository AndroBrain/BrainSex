package com.androbrain.brainsex.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.androbrain.brainsex.R
import com.androbrain.brainsex.navigation.Arguments
import com.androbrain.brainsex.navigation.Routes
import com.androbrain.brainsex.ui.choosegender.ChooseGenderFragment
import com.androbrain.brainsex.ui.test.TestFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        val navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = Routes.choose_gender
        ) {
            fragment<TestFragment>(
                "${Routes.test}/{${Arguments.gender}}"
            ) {
                argument(Arguments.gender) {
                    type = NavType.StringType
                }
            }

            fragment<ChooseGenderFragment>(Routes.choose_gender)
        }
    }

}
