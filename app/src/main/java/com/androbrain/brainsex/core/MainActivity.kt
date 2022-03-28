package com.androbrain.brainsex.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.androbrain.brainsex.R
import com.androbrain.brainsex.navigation.nav_arguments
import com.androbrain.brainsex.navigation.nav_routes
import com.androbrain.brainsex.ui.choosegender.ChooseGenderFragment
import com.androbrain.brainsex.ui.menu.MainMenuFragment
import com.androbrain.brainsex.ui.result.ResultFragment
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
            startDestination = nav_routes.main_menu
        ) {
            fragment<MainMenuFragment>(nav_routes.main_menu)

            fragment<ChooseGenderFragment>(nav_routes.choose_gender)

            fragment<TestFragment>(
                "${nav_routes.test}/{${nav_arguments.gender}}"
            ) {
                argument(nav_arguments.gender) {
                    type = NavType.StringType
                }
            }

            fragment<ResultFragment>(
                "${nav_routes.result}/{${nav_arguments.points}}"
            ) {
                argument(nav_arguments.points) {
                    type = NavType.StringType
                }
            }
        }
    }

}
