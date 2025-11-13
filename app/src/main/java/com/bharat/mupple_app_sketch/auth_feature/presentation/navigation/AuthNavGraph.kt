package com.bharat.mupple_app_sketch.auth_feature.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.mupple_app_sketch.app_root.AppRoutes

fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(
        route = AppRoutes.AuthRoute,
        startDestination = AuthRoutes.Start
    ){
        composable(AuthRoutes.Start) {

        }

        composable(AuthRoutes.RegisterAuth) {

        }

        composable(AuthRoutes.RegisterProfile) {

        }

    }
}