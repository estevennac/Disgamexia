package com.example.disgamexia.models

import java.io.Serializable

data class ResultModel(
    val time:Int,
    val type:String,
    val difficulty:String,
    val score:Double
):Serializable
