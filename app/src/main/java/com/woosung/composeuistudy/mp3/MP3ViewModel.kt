package com.woosung.composeuistudy.mp3

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.ViewModel
import com.woosung.composeuistudy.mp3.service.BoundMp3Service
import com.woosung.composeuistudy.mp3.service.StartMp3Service
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MP3ViewModel @Inject constructor(
    @ApplicationContext private val application: Context
) : ViewModel() {
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    fun startService() {
        val mp3Service = Intent(application, StartMp3Service::class.java)
        application.startService(mp3Service)
    }


    fun stopService() {
        val mp3Service = Intent(application, StartMp3Service::class.java)
        application.stopService(mp3Service)
    }


    fun startBindService() {
        val mp3Service = Intent(application, BoundMp3Service::class.java)

        application.bindService(mp3Service, serviceConnection, Context.BIND_AUTO_CREATE)

    }

    fun stopUnBindService() {
        application.unbindService(serviceConnection)
    }

}
