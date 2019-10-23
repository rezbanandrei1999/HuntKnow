package com.example.huntknow.com.example.huntknow.models

class User (

    var uid : String = "",
    var qr_list : MutableList<String> = mutableListOf(),
    var final_qr: String="" ,
    var visited_place:Int=0

)