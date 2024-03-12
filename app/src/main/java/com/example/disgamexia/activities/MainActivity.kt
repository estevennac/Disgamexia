/*package com.example.disgamexia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.disgamexia.utils.QuizClass
import com.example.disgamexia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        val quizClass = QuizClass(this)
        val questionList = quizClass.getQuizList(5,null,null,null, binding?.QuestionsList!!,binding?.QuestionsSize!!)

    }
}*/
/*
package com.example.disgamexia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.disgamexia.utils.QuizClass
import com.example.disgamexia.databinding.ActivityMainBinding
import com.example.disgamexia.models.QuizQuestion

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        val quizClass = QuizClass(this)
        quizClass.getQuizList(5,null,null,null, object : QuizClass.QuestionListCallback{
            override fun onQuestionListFetched(list: List<QuizQuestion>){
                binding?.QuestionsList?.text = list.toString()
                binding?.QuestionsList?.text = list.size.toString()

            }
        })

    }
}*/

package com.example.disgamexia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.disgamexia.adapter.GridAdapter
import com.example.disgamexia.utils.QuizClass
import com.example.disgamexia.databinding.ActivityMainBinding
import com.example.disgamexia.models.Category
import com.example.disgamexia.models.QuizQuestion
import com.example.disgamexia.utils.Constants
import com.example.disgamexia.utils.Utils

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        val quizClass = QuizClass(this)
        val rvCategoryList = binding?.rvCategoryList
        rvCategoryList?.layoutManager = GridLayoutManager(this,2)
        quizClass.getQuestionStatsList(object : QuizClass.QuestionStatCallback{
            override fun onQuestionStatFetched(map: Map<String, Category>) {
                val adapter = GridAdapter(Constants.getCategoryItemList(),map)
                rvCategoryList?.adapter=adapter
                adapter.setOnTouchResponse(object : GridAdapter.OnTouchResponse{
                    override fun onClick(id: Int) {
                        //Utils.showToast(this@MainActivity,"Id is $id")
                        quizClass.getQuizList(10,id,null,null)
                    }
                })

            }
        })

        binding?.btnRandomQuiz?.setOnClickListener {
            quizClass.getQuizList(10,null,null,null)
        }
        binding?.btnCustomQuiz?.setOnClickListener {
            startActivity(Intent(this,CustomQuizActivity::class.java))
        }

        binding?.btnOne?.setOnClickListener {
            startActivity(Intent(this,Activity1::class.java))
        }
        binding?.btnTwo?.setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
        }

        binding?.btnThree?.setOnClickListener {
            startActivity(Intent(this,Activity3::class.java))
        }



    }
}