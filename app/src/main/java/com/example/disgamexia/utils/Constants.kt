package com.example.disgamexia.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.text.HtmlCompat
import com.example.disgamexia.R
import com.example.disgamexia.models.Category
import com.example.disgamexia.models.CategoryModel

object Constants {

    val difficultyList = listOf("Any","Easy","Medium","Hard")
    val typeList = listOf("Any","Multiple Choice","True/False")
    fun redDisponible(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network)?: return false

        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else->false

        }
    }

    fun getCategoryItemList():ArrayList<CategoryModel>{
        val list = ArrayList<CategoryModel>()
        list.add(
            CategoryModel("9", R.drawable.general_knowledge, "Science & Nature")
        )

        list.add(
            CategoryModel("10", R.drawable.books, "Libros")
        )


        return list
    }

    fun getRandomOptions(correctAnswer:String,incorrectAnswer:List<String>):Pair<String,List<String>>
    {
        var list = mutableListOf<String>()
        list.add(decodeHtmlString(correctAnswer))
        for(i in incorrectAnswer){
            list.add(decodeHtmlString(i))
        }

        list.shuffle()
        return Pair(correctAnswer,list)
    }

    fun decodeHtmlString(htmlEncoded:String):String{
        return HtmlCompat.fromHtml(htmlEncoded, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }

    fun getCategoryStringArray():List<String>{
        val list = getCategoryItemList()
        val result = mutableListOf<String>()
        result.add("Any")
        for(i in list)
            result.add(i.name)

        return result
    }
}