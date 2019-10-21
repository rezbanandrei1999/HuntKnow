package com.example.huntknow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class QuizActivity : AppCompatActivity() {

    lateinit var questionText : TextView
    lateinit var variantA : CheckBox
    lateinit var variantB : CheckBox
    lateinit var variantC : CheckBox
    lateinit var variantD : CheckBox
    lateinit var submit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.question)
        variantA = findViewById(R.id.variant_a)
        variantB = findViewById(R.id.variant_b)
        variantC = findViewById(R.id.variant_c)
        variantD = findViewById(R.id.variant_d)
        submit = findViewById(R.id.submit)

        submit.setOnClickListener{

            submitAnswer()
        }
    }
    private fun submitAnswer()
    {

    }
}
