package com.example.disgamexia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disgamexia.R
import com.example.disgamexia.adapter.QuizSummeryAdapter
import com.example.disgamexia.databinding.ActivityResultBinding
import com.example.disgamexia.models.ResultModel

class ResultActivity : AppCompatActivity() {
    private var binding:ActivityResultBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val resultList:ArrayList<ResultModel> = intent.getSerializableExtra("resultList")
                as ArrayList<ResultModel>
        binding?.rvSummery?.layoutManager = LinearLayoutManager(this)
        val adapter = QuizSummeryAdapter(resultList)
        binding?.rvSummery?.adapter = adapter

        binding?.tvTotalScore?.text = getFinalScore(resultList).toString()
        binding?.btnHome?.setOnClickListener{
            finish()
        }
    }

    private fun getFinalScore(list:ArrayList<ResultModel>):Double{
        var result = 0.0
        for(i in list)
            result+=i.score

        return String.format("%.2f",result).toDouble()
    }
}