package com.woosung.composeuistudy.mp3.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BoundMp3Service : Service() {
    var isShowingToast = true
    var musicInt = 0
    override fun onCreate() {
        Toast.makeText(this, "시작", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        isShowingToast = true
        CoroutineScope(Dispatchers.Main).launch {
            while (isShowingToast) {
                delay(3000L)
                musicInt = ++musicInt
                Toast.makeText(this@BoundMp3Service, "$musicInt", Toast.LENGTH_SHORT).show()
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this@BoundMp3Service, "onBind", Toast.LENGTH_SHORT).show()
        return null
    }


    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    //onDestroy()일떄 파괴 된다.
    override fun onDestroy() {
        Toast.makeText(this, "꺼짐", Toast.LENGTH_SHORT).show()
        isShowingToast = false
        super.onDestroy()
    }
}
