package com.woosung.composeuistudy.mp3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Mp3Screen(mainViewModel: MP3ViewModel = viewModel()) {
    Row {
        Column {
            TextButton(onClick = {
                mainViewModel.startService()
            }) {
                Text(text = "Start 서비스 시작")
            }
            TextButton(onClick = {
                mainViewModel.stopService()
            }) {
                Text(text = "Start 서비스 시작")
            }
        }
        Column {
            TextButton(onClick = {
                mainViewModel.startBindService()
            }) {
                Text(text = "Bound 서비스 시작")
            }
            TextButton(onClick = {
                mainViewModel.stopUnBindService()
            }) {
                Text(text = "Bound 서비스 종료")
            }
        }
    }

}

