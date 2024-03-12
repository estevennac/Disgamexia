
package com.example.disgamexia.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import com.example.disgamexia.activities.QuizActivity
import com.example.disgamexia.models.Category
import com.example.disgamexia.models.QuestionStats
import com.example.disgamexia.models.QuizQuestion
import com.example.disgamexia.models.QuizResponse
import com.example.disgamexia.retrofit.QuestionStatsService
import com.example.disgamexia.retrofit.QuizService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.temporal.TemporalAmount
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Array
import java.nio.charset.Charset

class QuizClass (private val context: Context) {

    fun getQuizList (amount: Int,category:Int?,difficulty:String?, type:String?) {


        if (Constants.redDisponible(context)) {
            val pbDialog = Utils.showProgressBar(context)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val service: QuizService = retrofit.create(QuizService::class.java)
            val dataCall: Call<QuizResponse> = service.getQuiz(
                amount, category, difficulty, type
            )
            dataCall.enqueue(object : Callback<QuizResponse> {
                override fun onResponse(
                    call: Call<QuizResponse>,
                    response: Response<QuizResponse>
                ) {
                    pbDialog.cancel()
                    if (response.isSuccessful) {
                        val responseData: QuizResponse = response.body()!!
                        val questionList = ArrayList(responseData.results)
                        //callback.onQuestionListFetched(questionList)

                        //Log.e("Debug", questionList.toString())

                        if (questionList.isNotEmpty()){
                            val intent = Intent(context,QuizActivity::class.java)
                            intent.putExtra("questionList",questionList)
                            context.startActivity(intent)
                        }else{
                            Utils.showToast(context,"Pronto tendremos mas preguntas")
                        }
                    } else {
                        Utils.showToast(context, "Respuesta Incorrecta")
                    }
                }

                override fun onFailure(call: Call<QuizResponse>, t: Throwable) {
                    pbDialog.cancel()
                    Utils.showToast(context, "Error")

                }
            })

        } else {
            Utils.showToast(context, "Red no disponible")

        }
    }/*
    interface QuestionListCallback{
        fun onQuestionListFetched(list:List<QuizQuestion>)
    }*/


    fun getQuestionStatsList(callback: QuestionStatCallback){
        if (Constants.redDisponible(context)){
            val pbDialog = Utils.showProgressBar(context)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val service: QuestionStatsService = retrofit.create(QuestionStatsService::class.java)
            val dataCall:Call<QuestionStats> = service.getData()

            dataCall.enqueue(object:Callback<QuestionStats>{
                override fun onResponse(call: Call<QuestionStats>, response: Response<QuestionStats>) {
                    pbDialog.cancel()
                    if(response.isSuccessful){
                        val questionStats:QuestionStats = response.body()!!
                        val categoryMap = questionStats.categories
                        callback.onQuestionStatFetched(categoryMap)
                    }else{
                        Utils.showToast(context,"Error Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<QuestionStats>, t: Throwable) {
                    pbDialog.cancel()
                    Utils.showToast(context,"ERROR DE LLAMADA")
                }

            })
        }else{
            Utils.showToast(context,"Red no disponible")
        }
    }
    interface QuestionStatCallback{
        fun onQuestionStatFetched(map:Map<String, Category>)
    }
}