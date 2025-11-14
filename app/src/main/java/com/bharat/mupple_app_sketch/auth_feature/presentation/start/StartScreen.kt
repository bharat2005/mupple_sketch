package com.bharat.mupple_app_sketch.auth_feature.presentation.start

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bharat.mupple_app_sketch.core.components.AuthButton
import com.bharat.mupple_app_sketch.core.components.MyButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


@Composable
fun StartScreen(
    viewModel: StartViewModel = hiltViewModel()
) {
    val context = LocalContext.current


    val googleSignInOptions = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("")
            .build()
    }

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, googleSignInOptions)
    }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken ?: throw Exception("Failed to get Id Token")
                //Success
            } catch (e: Exception) {
                viewModel.onGoogleLoginError(e.message ?: "Something went wrong.")
            }
        } else {
            viewModel.onGoogleLoginError("Google Loign Failed or Cancled.")

        }
    }


        Scaffold(
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(
                    12.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                MyButton(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    onClick = {},
                    text = "Let's Get Started"
                )


                AuthButton(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    text = "Login with Google",
                    onClick = {}
                )


            }


        }









    }

