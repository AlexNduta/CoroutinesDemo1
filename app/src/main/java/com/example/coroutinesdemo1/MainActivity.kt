package com.example.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var messageText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messageText= findViewById(R.id.tvMessage)
        val textView = findViewById<TextView>(R.id.tvCount)
        val countButton = findViewById<Button>(R.id.btnCount)
        val downloadButton = findViewById<Button>(R.id.btnDownload)

        countButton.setOnClickListener {
            textView.text = count++.toString()
        }
        downloadButton.setOnClickListener {
            GlobalScope.launch{
                downloadUserData()
            }

        }

    }
    private suspend fun downloadUserData() {
        for (i in 1..2000000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")

            withContext(Dispatchers.Main){
                messageText.text= "Downloading user $i"
            }


            delay(100)
        }
    }
}

