package com.example.disgamexia.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.disgamexia.R
import java.util.Arrays
import java.util.Collections

class Activity3 : AppCompatActivity() {
    private var sentenceTextView: TextView? = null
    private val optionButtons = arrayOfNulls<Button>(3)
    private var numOfErrors = 0
    private val sentences = arrayOf(
        "El gato casó un ratón en el jardín.",
        "Mañana iré a la feria para comprar verdura y almuada.",
        "El cielo estrellao brillaba con intensidad sobre la ciudad.",
        "Mi abuela cocinó un delisioso pastel de chocolate.",
        "El perro ladró juerte cuando escuchó un ruido extraño.",
        "Compré un camion de jugete para mi sobrinito.",
        "La biblioteca estaba llena de libros antigous y polvo.",
        "El arco iris apareció después de la llovíua.",
        "El doctor recetó una medicina para alibiar la tos de mi hija.",
        "Esta película es aburridísima, no debería exsistir.",
        "El gato jugó con una flor en el parque.",
        "Mañana iré a la festa con mi hermano.",
        "Mi ermana cocinó un delicioso pastel de fresa.",
        "El perro ladró fuerta cuando vio al gato.",
        "Compré un regalo para mi padre en la tiend.",
        "La casa estaba limpia y ordendo cuando llegamos.",
        "El niño construyó un castillo de arena en el bosque.",
        "La yuvia empezó de repente y mojó todos.",
        "El sol brilla en el cielo verde en invierno.",
        "La niña leyo un libro interesante en la escuela."

    )
    private val options = arrayOf(
        arrayOf("casó", "ratón", "jardín"),
        arrayOf("almuada", "verdura", "iré"),
        arrayOf("estrellao", "intensidad", "brillaba"),
        arrayOf("delisioso", "pastel", "chocolate"),
        arrayOf("juerte", "ladró", "extraño"),
        arrayOf("jugete", "camion", "Compré"),
        arrayOf("antigous", "polvo", "estaba"),
        arrayOf("llovíua", "iris", "apareció"),
        arrayOf("alibiar", "recetó", "tos"),
        arrayOf("exsistir", "debería", "película"),

        arrayOf("jugó", "gato", "flor"),
        arrayOf("festa", "mañana", "hermano"),
        arrayOf("ermana", "cocinó", "fresa"),
        arrayOf("fuerta", "perro", "gato"),
        arrayOf("regalo", "padre", "tiend"),
        arrayOf("ordendo", "casa", "limpia"),
        arrayOf("bosque", "niño", "castillo"),
        arrayOf("yuvia", "empezó", "todos"),
        arrayOf("verde", "cielo", "invierno"),
        arrayOf("leyo", "niña", "escuela")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        sentenceTextView = findViewById(R.id.sentenceTextView)
        optionButtons[0] = findViewById(R.id.optionButton1)
        optionButtons[1] = findViewById(R.id.optionButton2)
        optionButtons[2] = findViewById(R.id.optionButton3)
        setupGame()
    }

    private fun setupGame() {
        // Choose a random sentence
        val randomIndex = (Math.random() * sentences.size).toInt()
        val sentence = sentences[randomIndex]

        // Display the sentence with some words misspelled
        sentenceTextView!!.text = sentence

        // Choose a random option for the correct word and two incorrect options
        val optionsList: List<String?> = ArrayList(Arrays.asList(*options[randomIndex]))
        Collections.shuffle(optionsList)

        // Display the options on buttons
        for (i in optionButtons.indices) {
            optionButtons[i]!!.text = optionsList[i]
            optionButtons[i]!!.setOnClickListener { v ->
                val selectedButton = v as Button
                val selectedOption = selectedButton.text.toString()
                checkAnswer(selectedOption)
            }
        }
    }

    private fun checkAnswer(selectedOption: String) {
        for (optionGroup in options) {
            for (option in optionGroup) {
                if (option == selectedOption) {
                    if (option == optionGroup[0]) {
                        Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show()
                        setupGame()
                    } else {
                        numOfErrors++
                        Toast.makeText(this, "Incorrecto. Intenta de nuevo.", Toast.LENGTH_SHORT)
                            .show()
                        if (numOfErrors >= 3) {
                            Toast.makeText(
                                this,
                                "Demasiados intentos. La palabra correcta era: " + optionGroup[0],
                                Toast.LENGTH_LONG
                            ).show()
                            setupGame()
                            numOfErrors = 0
                        }
                    }
                    return
                }
            }
        }
    }
}
