package com.example.huntknow.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is score Fragment"
    }
    val text: LiveData<String> = _text
}