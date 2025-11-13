package com.bharat.mupple_app_sketch.app_root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppRoot(

) {
     val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ""
    ){
        composable(AppRoutes.SplashRoute) {
            SplashScreen()
        }




    }


}