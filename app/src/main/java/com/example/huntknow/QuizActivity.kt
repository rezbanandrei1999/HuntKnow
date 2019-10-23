package com.example.huntknow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val setQrResult : TextView = findViewById(R.id.qrResultTest)

        var getScanResult: String

        val intent = intent
        val bundle = intent.extras

        if (bundle != null) {
            getScanResult = bundle.getString("qrResult")!!
            setQrResult.text=getScanResult
        }

    }
}
