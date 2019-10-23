package com.example.huntknow.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.huntknow.R
import com.example.huntknow.com.example.huntknow.models.Question

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setQuestion(arguments?.getString(ARG_QUESTION) ?: "")
            setVariantA(arguments?.getString(ARG_VARIANT_A) ?: "")
            setVariantB(arguments?.getString(ARG_VARIANT_B) ?: "")
            setVariantC(arguments?.getString(ARG_VARIANT_C) ?: "")
            setVariantD(arguments?.getString(ARG_VARIANT_D) ?: "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_quiz, container, false)
        val question: TextView = root.findViewById(R.id.question)
        val variantA: CheckBox = root.findViewById(R.id.variant_a)
        val variantB: CheckBox = root.findViewById(R.id.variant_b)
        val variantC: CheckBox = root.findViewById(R.id.variant_c)
        val variantD: CheckBox = root.findViewById(R.id.variant_d)
        pageViewModel.questionText.observe(this, Observer<String> {
            question.text = it
        })
        pageViewModel.variantAText.observe(this, Observer<String> {
            variantA.text = it
        })
        pageViewModel.variantBText.observe(this, Observer<String> {
            variantB.text = it
        })
        pageViewModel.variantCText.observe(this, Observer<String> {
            variantC.text = it
        })
        pageViewModel.variantDText.observe(this, Observer<String> {
            variantD.text = it
        })

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_QUESTION = "question"
        private const val ARG_VARIANT_A = "variant_a"
        private const val ARG_VARIANT_B = "variant_b"
        private const val ARG_VARIANT_C = "variant_c"
        private const val ARG_VARIANT_D = "variant_d"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, question : Question): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ARG_QUESTION,question.question)
                    putString(ARG_VARIANT_A,question.variant_a)
                    putString(ARG_VARIANT_B,question.variant_b)
                    putString(ARG_VARIANT_C,question.variant_c)
                    putString(ARG_VARIANT_D,question.variant_d)
                }
            }
        }
    }
}