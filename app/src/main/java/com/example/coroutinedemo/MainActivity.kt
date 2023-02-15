package com.example.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var messageTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messageTextView = findViewById(R.id.txtMessage)
        val textView = findViewById<TextView>(R.id.txtCount)
        val btnDownload = findViewById<Button>(R.id.btnDownload)
        val btnCount = findViewById<Button>(R.id.btnCount)

        btnCount.setOnClickListener {
            textView.text = count++.toString()
        }
        btnDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                userDownload()
            }
        }
    }
    private suspend fun userDownload(){
        for(i in 1..200000){
            Log.i("MyTag", "Download user $i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main){
                messageTextView.text = "Download user $i in ${Thread.currentThread().name}"
            }
            delay(100)
        }
    }
}