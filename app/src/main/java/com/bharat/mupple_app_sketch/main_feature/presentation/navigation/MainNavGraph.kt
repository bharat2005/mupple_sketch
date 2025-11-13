package com.bharat.mupple_app_sketch.main_feature.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.bharat.mupple_app_sketch.app_root.AppRoutes

fun NavGraphBuilder.MainNavGraphBuilder(navController: NavController){
    navigation(
        route = AppRoutes.MainRoute,
        startDestination = ""
    ){

    }
}