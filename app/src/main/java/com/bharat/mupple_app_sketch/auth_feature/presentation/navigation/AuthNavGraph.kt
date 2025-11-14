package com.bharat.mupple_app_sketch.auth_feature.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bharat.mupple_app_sketch.app_root.AppRoutes
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerAuth.RegisterAuthScreen
import com.bharat.mupple_app_sketch.auth_feature.presentation.start.StartScreen
import com.bharat.mupple_app_sketch.legal_feature.presentation.legal.LegalType

val timingTween = 430
fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(
        route = AppRoutes.AuthRoute,
        startDestination = AuthRoutes.Start
    ){


        composable(
            route = AuthRoutes.Start,
            enterTransition = { EnterTransition.None },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(timingTween)) + fadeOut(tween(timingTween))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = {-it }, animationSpec = tween(timingTween)) + fadeIn(tween(timingTween))
            },
            popExitTransition = { ExitTransition.None }
        ) {
            StartScreen(
                onStartedClick = {navController.navigate(AuthRoutes.RegisterAuth)}
            )
        }

        composable(
            route= AuthRoutes.RegisterAuth,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(timingTween))+ fadeIn(tween(timingTween))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it/6 }, animationSpec = tween(timingTween)) + fadeOut(tween(timingTween))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = {-it/6 }, animationSpec = tween(timingTween)) + fadeIn(tween(timingTween))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(timingTween)) + fadeOut(tween(timingTween))
            }
            ) {
            RegisterAuthScreen(
                onExit = {navController.navigateUp()},
                onAuthRegistrationSuccess = {navController.navigate(AuthRoutes.RegisterProfile)},
                onTermsOfServiesClick = { navController.navigate("${AppRoutes.LegalRoute}/${LegalType.TERMS_OF_SERVICES.name}")},
                onPrivacyPolicyClick =  { navController.navigate("${AppRoutes.LegalRoute}/${LegalType.PRIVACY_POLICY.name}")}
            )

        }

        composable(AuthRoutes.RegisterProfile) {

        }

    }
}