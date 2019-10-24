package com.example.huntknow

import android.app.Application
import com.example.huntknow.com.example.huntknow.models.QrCode

class GlobalVariables: Application() {

    companion object {
        var right_answers = 0
        var qrList : MutableList<QrCode> = mutableListOf()
    }
    override fun onCreate() {
        super.onCreate()
        right_answers = 0
    }
}
