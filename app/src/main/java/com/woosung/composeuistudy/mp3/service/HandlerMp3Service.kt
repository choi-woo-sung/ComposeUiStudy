package com.woosung.composeuistudy.mp3.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 마지막 클라이언트가 연결을 해제할때 파괴된다.
class HandlerMp3Service : Service() {

    private val handler = Handler(mainLooper, object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            return when (msg.what) {
                START -> {
                    Toast.makeText(this@HandlerMp3Service, "왔습니다 신호", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
    )


    override fun onCreate() {
        Toast.makeText(this, "시작", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    //Bind를 만들기 위해서는 3가지의 방법이 필요하다.
    //1. Binder 객체를 생성하는것.
    //2. hadler를 쓰는것
    //3. aidl를 쓰는것
    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this@HandlerMp3Service, "onBind", Toast.LENGTH_SHORT).show()
        return Messenger(handler).binder
    }


    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    //onDestroy()일떄 파괴 된다.
    override fun onDestroy() {
        Toast.makeText(this, "꺼짐", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}
