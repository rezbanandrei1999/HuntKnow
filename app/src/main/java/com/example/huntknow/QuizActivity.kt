package com.example.huntknow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.os.CountDownTimer
import android.widget.TextView
import com.example.huntknow.com.example.huntknow.models.Question
import com.google.firebase.database.*
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import com.example.huntknow.ui.main.SectionsPagerAdapter
import java.util.concurrent.TimeUnit


class QuizActivity : AppCompatActivity() {

    var quiz: MutableList<Question> = mutableListOf()

    lateinit var ref : DatabaseReference
    lateinit var quiz_timer : TextView
    var time_spent = 60*1000
    val timerForQuizActivity = object: CountDownTimer(time_spent.toLong(), 1000){
        override fun onFinish() {
            // finish game
        }

        override fun onTick(millisUntilFinished: Long) {
            quiz_timer.setText(String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))
            time_spent -=1000
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val setQrResult : TextView = findViewById(R.id.qrResultTest)


        val qrCode: String?
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                qrCode = null
            } else {
                qrCode = extras.getString("qrResult")
            }
        } else {
            qrCode = savedInstanceState.getSerializable("qrResult") as String
        }

        ref = FirebaseDatabase.getInstance().getReference("questions")

        val context = this
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.mapNotNullTo(quiz) {
                    it.getValue<Question>(Question::class.java)
                }

                val iterator = quiz.listIterator()
                var aux : MutableList<Question> = mutableListOf()
                for(question in iterator)
                    if(question.qr_group == qrCode)
                        aux.add(question)
                quiz = aux
                quiz.shuffle()
                quiz_timer = findViewById(R.id.quizTimer)

                timerForQuizActivity.start()
                val sectionsPagerAdapter = SectionsPagerAdapter(context, supportFragmentManager, quiz)
                val viewPager: ViewPager = findViewById(R.id.view_pager)
                viewPager.adapter = sectionsPagerAdapter
                val tabs: TabLayout = findViewById(R.id.slidingTabs)
                tabs.setupWithViewPager(viewPager)


            }

            override fun onCancelled(p0: DatabaseError) {}
        })


    }
    private fun submitAnswer()
    {}
}