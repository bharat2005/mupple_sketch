package com.bharat.mupple_app_sketch.app_root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bharat.mupple_app_sketch.auth_feature.presentation.navigation.authNavGraph
import com.bharat.mupple_app_sketch.legal_feature.presentation.navigation.legalNavGraph
import com.bharat.mupple_app_sketch.main_feature.presentation.navigation.mainNavGraph

@Composable
fun AppRoot(
    viewModel: AppRootViewModel = hiltViewModel()
) {
     val navController = rememberNavController()
    val authState by viewModel.authState.collectAsState()

    val startDestination = when(authState){
        AuthState.UNKNOWN -> AppRoutes.SplashRoute
        AuthState.UNAUTHENTICATED -> AppRoutes.AuthRoute
        AuthState.AUTHENTICATED -> AppRoutes.MainRoute
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(AppRoutes.SplashRoute) {
                SplashScreen()
            }

            authNavGraph(navController)

            mainNavGraph(navController)

            legalNavGraph(navController)


        }
    }


}