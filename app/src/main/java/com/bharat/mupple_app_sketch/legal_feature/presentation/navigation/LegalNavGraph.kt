package com.bharat.mupple_app_sketch.legal_feature.presentation.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bharat.mupple_app_sketch.app_root.AppRoutes
import com.bharat.mupple_app_sketch.legal_feature.presentation.legal.LegalScreen

fun NavGraphBuilder.legalNavGraph(navController: NavController){

        composable(
            route = "${AppRoutes.LegalRoute}/{documentType}",
            arguments = listOf(
                navArgument("documentType"){ type = NavType.StringType }
            ),
        ) {
            LegalScreen(
                onExit = {navController.navigateUp()}
            )
        }

}