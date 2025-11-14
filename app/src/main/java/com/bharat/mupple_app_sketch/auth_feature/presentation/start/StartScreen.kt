package com.bharat.mupple_app_sketch.auth_feature.presentation.start

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bharat.mupple_app_sketch.core.components.AuthButton
import com.bharat.mupple_app_sketch.core.components.MyButton
import com.bharat.mupple_app_sketch.core.components.MyDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


@Composable
fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
    onStartedClick : ()-> Unit,
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    val googleSignInOptions = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("205278832472-tinhd5tj1a501hc5vjqkctco1pt046d4.apps.googleusercontent.com")
            .build()
    }

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, googleSignInOptions)
    }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    )
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken ?: throw Exception("Failed to get Id Token")
                viewModel.onLocalGoogleLoginSuccess(idToken)
            } catch (e: Exception) {
                viewModel.onLocalGoogleLoginError(e.message ?: "Something went wrong.")
            }
        } else {
            viewModel.setIsGoogleLoggingIn(false)
        }
    }


    LaunchedEffect(uiState.loginSuccess) {
        if(uiState.loginSuccess){
            Toast.makeText(context, "Login Successfull.", Toast.LENGTH_LONG).show()
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
                    onClick = onStartedClick,
                    text = "Let's Get Started"
                )
                AuthButton(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    text = "Login with Google",
                    onClick = {
                        viewModel.setIsGoogleLoggingIn(true)
                        googleSignInClient.signOut().addOnCompleteListener {
                            googleSignInLauncher.launch(googleSignInClient.signInIntent)
                        }
                    }
                )

            }


        }

        if(uiState.loginError != null){
            MyDialog(
                text = uiState.loginError!!,
                colorButtonText = "OK",
                onColorButtonClick = {viewModel.onGoogleLoginErrorDismiss()},
                onDismiss = {viewModel.onGoogleLoginErrorDismiss()}
            )
        }

        if(uiState.isLoggingIn){
            Box(
                modifier = Modifier.fillMaxSize().pointerInput(Unit){},
                contentAlignment = Alignment.Center,

            ){
                Box(modifier = Modifier.clip(CircleShape).background(Color.White).padding(6.dp)){
                    CircularProgressIndicator()
                }

            }
        }



    }

