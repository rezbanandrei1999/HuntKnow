package com.example.huntknow

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    private fun signOut() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        FirebaseAuth.getInstance().signOut();
    }
    private fun setupUI() {
        open_sign_out.setOnClickListener {
            signOut()
        }
    }
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
            val intent = Intent(this, QuizActivity::class.java)
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

        setupUI()

    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
