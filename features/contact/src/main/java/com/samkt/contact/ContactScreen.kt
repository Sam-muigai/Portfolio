package com.samkt.contact

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(
    contactScreenViewModel: ContactScreenViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {

}