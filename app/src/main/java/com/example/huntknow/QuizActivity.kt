package com.example.huntknow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.util.Log
import android.widget.Toast
import com.example.huntknow.com.example.huntknow.models.Question
import com.google.firebase.database.*


class QuizActivity : AppCompatActivity() {

    lateinit var questionText : TextView
    lateinit var variantA : CheckBox
    lateinit var variantB : CheckBox
    lateinit var variantC : CheckBox
    lateinit var variantD : CheckBox
    lateinit var submit : Button

    private lateinit var questionReference: DatabaseReference

    private var questionListener: ValueEventListener? = null

    private val quiz: MutableList<Question> = mutableListOf()

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
