package com.woosung.composeuistudy.mp3

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woosung.composeuistudy.mp3.service.BindMp3Service
import com.woosung.composeuistudy.mp3.service.HandlerMp3Service
import com.woosung.composeuistudy.mp3.service.StartMp3Service
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MP3ViewModel @Inject constructor(
    @ApplicationContext private val application: Context
) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private var mService: BindMp3Service? = null
    private var mMessenger: Messenger? = null
    private var isBind = false


    private val serviceBinderConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BindMp3Service.LocalBinder
            mService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    private val serviceHandlerConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMessenger = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mMessenger = null
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
        val mp3Service = Intent(application, BindMp3Service::class.java)
        isBind = true
        application.bindService(mp3Service, serviceBinderConnection, Context.BIND_AUTO_CREATE)
        viewModelScope.launch {
            while (isBind) {
                delay(3000)
                Toast.makeText(application, mService?.musicInt.toString() ?: "0", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun stopUnBindService() {
        application.unbindService(serviceBinderConnection)
        isBind = false
    }

    fun startHandlerBindService() {
        val mp3Service = Intent(application, HandlerMp3Service::class.java)
        isBind = true
        application.bindService(mp3Service, serviceHandlerConnection, Context.BIND_AUTO_CREATE)
        viewModelScope.launch {
            while (isBind) {
                delay(3000)
                //Message는 재활용 불가
                val msg: Message = Message.obtain(null, 123)
                mMessenger?.send(msg)
            }
        }
    }

    fun stopHandlerUnBindService() {
        application.unbindService(serviceHandlerConnection)
        isBind = false
    }

}
