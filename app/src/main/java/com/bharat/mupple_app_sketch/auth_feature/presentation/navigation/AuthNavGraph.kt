package com.bharat.mupple_app_sketch.auth_feature.presentation.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.mupple_app_sketch.app_root.AppRoutes
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerAuth.RegisterAuthScreen
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileScreen
import com.bharat.mupple_app_sketch.auth_feature.presentation.start.StartScreen
import com.bharat.mupple_app_sketch.legal_feature.presentation.legal.LegalType


fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(
        route = AppRoutes.AuthRoute,
        startDestination = AuthRoutes.RegisterProfile
    ){


        composable(
            route = AuthRoutes.Start,
        ) {
            StartScreen(
                onStartedClick = {navController.navigate(AuthRoutes.RegisterAuth)}
            )
        }

        composable(
            route= AuthRoutes.RegisterAuth,
            ) {
            RegisterAuthScreen(
                onExit = {navController.navigateUp()},
                onAuthRegistrationSuccess = {navController.navigate(AuthRoutes.RegisterProfile)},
                onTermsOfServiesClick = { navController.navigate("${AppRoutes.LegalRoute}/${LegalType.TERMS_OF_SERVICES.name}")},
                onPrivacyPolicyClick =  { navController.navigate("${AppRoutes.LegalRoute}/${LegalType.PRIVACY_POLICY.name}")}
            )
        }

        composable(
            route = AuthRoutes.RegisterProfile,
        ) {
            RegisterProfileScreen(
                onExit = {navController.navigateUp()}
            )
        }

    }
}