package com.bharat.mupple_app_sketch.legal_feature.presentation.legal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bharat.mupple_app_sketch.core.components.MyDialog

@Composable
fun LegalScreen(
    onExit : () -> Unit,
    viewModel: LegalViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
    containerColor = Color.Transparent,
        topBar = {LegalScreenTopBar(onExit, uiState.title)}
    ) {  paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 24.dp)
        ) {
            item {
                if(uiState.legalDocument != null){
                    Text(uiState.legalDocument?.documentString ?: "")
                }

            }

        }


    }

    if(uiState.legalError != null){
        MyDialog(
            text = uiState.legalError ?: "",
            colorButtonText = "OK",
            onColorButtonClick = {viewModel.onLegalErrorDismiss()},
            onDismiss = {viewModel.onLegalErrorDismiss()}
        )
    }

    if(uiState.isLoading){
        Box(modifier = Modifier.fillMaxSize().pointerInput(Unit){}, contentAlignment = Alignment.Center){
            Box(modifier = Modifier.clip(CircleShape).background(Color.White).padding(12.dp)){
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegalScreenTopBar(onExit : () -> Unit, title : String?) {

    Box {
        TopAppBar(
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
            title = { Text(title ?: "") },
            navigationIcon = {
                IconButton(onClick = onExit) {
                    Icon(Icons.Default.Close, contentDescription = null)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        Box(modifier = Modifier.align(Alignment.BottomCenter).height(0.8.dp).fillMaxWidth().background(Color(
            0xFFD7D7D7
        )
        ))

    }
}