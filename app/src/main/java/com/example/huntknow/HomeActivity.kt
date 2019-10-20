package com.example.huntknow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val setQrResult : TextView = findViewById(R.id.qrResultTest)

        var getScanResult: String

        val intent = intent
        val bundle = intent.extras

        if (bundle != null) {
            getScanResult = bundle.getString("qrResult")!!
            setQrResult.text=getScanResult.toString()
        }

        val goToQRScan: Button = findViewById(R.id.open_scan)
        goToQRScan.setOnClickListener {
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
