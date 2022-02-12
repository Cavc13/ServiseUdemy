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
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MyJobIntentService: JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        log("onHandleWork")
        val pageNumber = intent.getIntExtra(PAGE, 0)
        for  (i in 0 until 5) {
            Thread.sleep(1000)
            log("Second $i Number of page $pageNumber")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyJobIntentService: $message")
    }

    companion object {
        private const val PAGE = "page"
        private const val JOB_ID = 111


        fun enqueue(context: Context, pageNumber: Int) {
            enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context, pageNumber)
            )
        }

        private fun newIntent(context: Context, pageNumber: Int) =
            Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, pageNumber)
            }
    }
}