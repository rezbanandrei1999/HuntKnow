package com.example.huntknow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openQuiz = findViewById<Button>(R.id.open_scan)
        openQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            val openScan = findViewById<Button>(R.id.open_scan)
            openScan.setOnClickListener {
                val intent = Intent(this, ScanActivity::class.java)
                startActivity(intent)
            }
            val openLeaderboard: Button = findViewById(R.id.open_leaderboard)
            openLeaderboard.setOnClickListener {
                val intent = Intent(this, LeaderboardActivity::class.java)
                startActivity(intent)
            }


            val openLocation: Button = findViewById(R.id.open_location)
            openLocation.setOnClickListener {
                val intent = Intent(this, LocationActivity::class.java)
                startActivity(intent)
            }


        }
    }
}

