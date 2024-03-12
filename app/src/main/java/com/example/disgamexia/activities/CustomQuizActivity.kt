package com.example.disgamexia.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SpinnerAdapter
import com.example.disgamexia.R
import com.example.disgamexia.databinding.ActivityCustomQuizBinding
import com.example.disgamexia.utils.Constants
import com.example.disgamexia.utils.QuizClass

class CustomQuizActivity : AppCompatActivity() {
    private var binding:ActivityCustomQuizBinding?=null
    private var amount = 10
    private var category:Int? = null
    private var difficulty:String? = null
    private var type:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomQuizBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        handleSeekbar()
        val categoryList = Constants.getCategoryStringArray()
        binding?.categorySpinner?.adapter = getSpinnerAdapter(categoryList)
        binding?.difficultySpinner?.adapter = getSpinnerAdapter(Constants.difficultyList)
        binding?.typeSpinner?.adapter = getSpinnerAdapter(Constants.typeList)
        handleCategorySpinner()
        handleDifficultySpinner()
        handleTypeSpinner()

        binding?.startCustomQuiz?.setOnClickListener {
            QuizClass(this).getQuizList(amount,category,difficulty,type)
        }
    }

    private fun handleSeekbar(){
        binding?.seekBarAmount?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                 amount = progress
                val text = "Amount: $amount"
                binding?.tvAmount?.text = text

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }

    private fun handleCategorySpinner(){
        binding?.categorySpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    category = if(position==0){
                        null
                    }else{
                        position + 8
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //nothing
                }



            }
    }

    private fun handleDifficultySpinner(){
        binding?.difficultySpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    difficulty = when(position) {
                        0 -> null
                        1->"easy"
                        2->"medium"
                        else->"hard"
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //nothing

                }



            }
    }

    private fun handleTypeSpinner(){
        binding?.typeSpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    type = when(position) {
                        0 -> null
                        1->"multiple"
                        else->"boolean"
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //nothing

                }



            }
    }

    private fun getSpinnerAdapter(list: List<String>):SpinnerAdapter{
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        return adapter

    }
}