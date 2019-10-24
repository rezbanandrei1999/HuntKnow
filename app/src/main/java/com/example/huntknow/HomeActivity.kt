package com.example.huntknow

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import com.example.huntknow.GlobalVariables.Companion.right_answers
import java.util.concurrent.TimeUnit

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



        val goToQRScan: Button = findViewById(R.id.open_scan)
        val newLocation: TextView = findViewById(R.id.newLocation)
        newLocation.isVisible = false
        val timeBlocked: String?
        val nextLocation: String?
        val isFinished: String?
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                timeBlocked = null
                nextLocation = null
                isFinished = null
            } else {
                timeBlocked = extras.getString("timeBlocked")
                nextLocation = extras.getString("location")
                isFinished = extras.getString("done")
            }
        } else {
            timeBlocked = savedInstanceState.getSerializable("timeBlocked") as String
            nextLocation = savedInstanceState.getSerializable("location") as String
            isFinished = savedInstanceState.getSerializable("location") as String
        }

        if(timeBlocked != null) {
        var time : Int = timeBlocked.toInt()
        val timeForScanActivity = object: CountDownTimer(time.toLong(), 1000){
            override fun onFinish() {
                goToQRScan.text = "Scan QR"
                goToQRScan.isEnabled = true
                newLocation.isVisible = true
                    newLocation.text = String.format("Next Location: %s",nextLocation)
            }

            override fun onTick(millisUntilFinished: Long) {
                goToQRScan.text = (String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))
                time -=1000
            }
        }
        goToQRScan.isEnabled = false
        if(isFinished == "false")
            timeForScanActivity.start()
        else {
            newLocation.isVisible = true
            newLocation.text = String.format("You finished the hunt!")
        }
        }


        goToQRScan.setOnClickListener {
            right_answers = 0
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

        setupUI()

    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
