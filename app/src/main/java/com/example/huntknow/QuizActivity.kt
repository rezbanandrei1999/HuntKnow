package com.example.huntknow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.example.huntknow.com.example.huntknow.models.Question
import com.google.firebase.database.*


class QuizActivity : AppCompatActivity() {

    lateinit var quiz: MutableList<Question>
    lateinit var questionText : TextView
    lateinit var variantA : CheckBox
    lateinit var variantB : CheckBox
    lateinit var variantC : CheckBox
    lateinit var variantD : CheckBox
    lateinit var submit : Button
    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quiz = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("questions")
        questionText = findViewById(R.id.question)
        variantA = findViewById(R.id.variant_a)
        variantB = findViewById(R.id.variant_b)
        variantC = findViewById(R.id.variant_c)
        variantD = findViewById(R.id.variant_d)
        submit = findViewById(R.id.submit)

        submit.setOnClickListener{

            submitAnswer()
        }

        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.mapNotNullTo(quiz) {
                    it.getValue<Question>(Question::class.java)
                }



                questionText.text = quiz.first().question
                variantA.text = quiz.first().variant_a
                variantB.text = quiz.first().variant_b
                variantC.text = quiz.first().variant_c
                variantD.text = quiz.first().variant_d

            }

            override fun onCancelled(p0: DatabaseError) {


            }

        })


    }
    private fun submitAnswer()
    {

    }
}
