package com.bharat.mupple_app_sketch.auth_feature.presentation.registerAuth

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bharat.mupple_app_sketch.core.components.AuthButton
import com.bharat.mupple_app_sketch.core.components.MyButton
import com.bharat.mupple_app_sketch.core.components.MyDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


@Composable
fun RegisterAuthScreen(
    viewModel: RegisterAuthViewModel = hiltViewModel(),
    onExit : () -> Unit,
    onTermsOfServiesClick : () -> Unit,
    onPrivacyPolicyClick : () -> Unit,
    onAuthRegistrationSuccess : () -> Unit
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
    val googleSignInLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    )
    { result ->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try{
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken ?: throw Exception("Failed to get Id Token.")
                viewModel.onLocalGoogleRegistrationSuccess(idToken)

            }catch (e : Exception){
                viewModel.onLocalGoogleRegisterationError(e?.message ?: "Something went wrong.")
            }
        } else {
            viewModel.setGoogleRegistrationLoading(false)
        }
    }

    LaunchedEffect(uiState.registrationSuccess) {
        if(uiState.registrationSuccess){
            Toast.makeText(context, "Registraion Success", Toast.LENGTH_LONG).show()

        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {RegisterScreenTopAppBar(onBackPress = onExit)}
    ){ paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "New Registration",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Box(modifier = Modifier.size(160.dp).background(Color.Black))

            Text(
                "Even if you authenticate, your usage will not mbe made public on social media or the outside world.",
                fontSize = 12.sp
            )

            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp)
            ){
                AuthButton(
                    onClick = {
                        viewModel.setGoogleRegistrationLoading(true)
                        googleSignInClient.signOut().addOnCompleteListener {
                            googleSignInLaucher.launch(googleSignInClient.signInIntent)
                        }
                    },
                    text = "Register with Google",
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
                ) {

                MyButton(
                    onClick = onTermsOfServiesClick,
                    text = "Terms of Use",
                    modifier = Modifier,
                    textModifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )
                MyButton(
                    onClick = onPrivacyPolicyClick,
                    text = "Privacy Policy",
                    modifier = Modifier,
                    textModifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )

            }



        }

    }

    if(uiState.registrationError != null){
        MyDialog(
            text = uiState.registrationError!!,
            colorButtonText = "OK",
            onDismiss = {viewModel.onGoogleRegistrationErrorDismiss()},
            onColorButtonClick =  {viewModel.onGoogleRegistrationErrorDismiss()},
        )
    }

    if(uiState.isRegistering){
        Box(
            modifier = Modifier.fillMaxSize().pointerInput(Unit){},
            contentAlignment = Alignment.Center
        ){
            Box(modifier = Modifier.clip(CircleShape).background(Color.White).padding(6.dp)){
                CircularProgressIndicator()
            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenTopAppBar(onBackPress : ()-> Unit){
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(
                onClick = onBackPress
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}



