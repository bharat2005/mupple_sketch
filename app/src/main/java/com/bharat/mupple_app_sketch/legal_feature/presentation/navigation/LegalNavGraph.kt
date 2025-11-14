package com.bharat.mupple_app_sketch.legal_feature.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bharat.mupple_app_sketch.app_root.AppRoutes
import com.bharat.mupple_app_sketch.auth_feature.presentation.navigation.timingTween
import com.bharat.mupple_app_sketch.legal_feature.presentation.legal.LegalScreen

fun NavGraphBuilder.legalNavGraph(navController: NavController){

        composable(
            route = "${AppRoutes.LegalRoute}/{documentType}",
            arguments = listOf(
                navArgument("documentType"){ type = NavType.StringType }
            ),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(timingTween - 130))+ fadeIn(tween(timingTween  - 130))
            },
            exitTransition = { ExitTransition.None},
            popEnterTransition = { EnterTransition.None },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(timingTween - 130)) + fadeOut(tween(timingTween - 130))
            },
        ) {
            LegalScreen(
                onExit = {navController.navigateUp()}
            )
        }

}