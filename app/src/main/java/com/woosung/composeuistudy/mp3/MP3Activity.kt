package com.woosung.composeuistudy.mp3

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Mp3Screen(mainViewModel: MP3ViewModel = viewModel()) {

    val ahandler = AHandler()
    val bhandler = BHandler()

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
                Text(text = "Start 서비스 종료")
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
        Column {
            TextButton(onClick = {
                mainViewModel.startHandlerBindService()
            }) {
                Text(text = "Handler Bound 서비스 시작")
            }
            TextButton(onClick = {
                mainViewModel.stopUnBindService()
            }) {
                Text(text = "Handler Bound 서비스 종료")
            }
            TextButton(onClick = {
                ahandler.obtainMessage(0)
            }) {
                Text(text = "은혜순대국밥")
            }
        }
    }
}


class AHandler : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        println("삐약 삐약 병아리")
        super.handleMessage(msg)
    }
}

class BHandler : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        println("음메 음메 송아지")
        val aHandler = AHandler()
        aHandler.obtainMessage("뽀동뽀동")
        super.handleMessage(msg)
    }
}

