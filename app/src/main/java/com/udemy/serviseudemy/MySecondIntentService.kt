package com.udemy.serviseudemy

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MySecondIntentService: IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true)
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val pageNumber = intent?.getIntExtra(PAGE, 0) ?: 0
        for  (i in 0 until 13) {
            Thread.sleep(1000)
            log("Second $i Number of page $pageNumber")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyForegroundService: $message")
    }

    companion object {
        private const val NAME = "MyIntentService"
        private const val PAGE = "page"

        fun newIntent(context: Context, pageNumber: Int) =
            Intent(context, MySecondIntentService::class.java).apply {
                putExtra(PAGE, pageNumber)
            }
    }
}