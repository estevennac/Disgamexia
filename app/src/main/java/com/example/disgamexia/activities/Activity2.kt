/*
package com.example.disgamexia.activities


import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.disgamexia.R

class Activity2 : AppCompatActivity() {
    // Declara las variables miembro necesarias
    private lateinit var textViewPelota: TextView
    private lateinit var textViewPesos: TextView
    private lateinit var textViewEpistemologia: TextView
    private lateinit var textViewErbivoro: TextView
    private lateinit var linearLayoutGood: LinearLayout
    private lateinit var linearLayoutBad: LinearLayout
    private lateinit var linearLayoutTrash: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        // Inicializa las vistas
        textViewPelota = findViewById(R.id.textViewPelota)
        textViewPesos = findViewById(R.id.textViewPesos)
        textViewEpistemologia = findViewById(R.id.textViewEpistemologia)
        textViewErbivoro = findViewById(R.id.textViewErbivoro)
        linearLayoutGood = findViewById(R.id.linearLayoutGood)
        linearLayoutBad = findViewById(R.id.linearLayoutBad)
        linearLayoutTrash = findViewById(R.id.linearLayoutTrash)

        // Asigna oyentes de eventos para el arrastre
        textViewPelota.setOnTouchListener(MyTouchListener())
        textViewPesos.setOnTouchListener(MyTouchListener())
        textViewEpistemologia.setOnTouchListener(MyTouchListener())
        textViewErbivoro.setOnTouchListener(MyTouchListener())
        linearLayoutGood.setOnDragListener(MyDragListener())
        linearLayoutBad.setOnDragListener(MyDragListener())
        linearLayoutTrash.setOnDragListener(MyDragListener())

        // Asigna oyente de eventos al botón de verificación
        val buttonVerificar: Button = findViewById(R.id.buttonVerificar)
        buttonVerificar.setOnClickListener {
            verificarRespuesta()
        }
    }

    // Clase interna para el oyente de eventos de arrastre
    private inner class MyTouchListener : View.OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDrag(null, shadowBuilder, view, 0)
                return true
            }
            return false
        }
    }

    // Clase interna para el oyente de eventos de arrastre
    private inner class MyDragListener : View.OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            val action = event.action
            if (action == DragEvent.ACTION_DROP) {
                // Obtiene la vista arrastrada
                val view = event.localState as View
                val owner = view.parent as ViewGroup

                // Determina en qué contenedor se soltó la vista y realiza la acción correspondiente
                if (v.id == R.id.linearLayoutGood || v.id == R.id.linearLayoutBad) {
                    if (view === textViewPelota || view === textViewPesos || view === textViewEpistemologia || view === textViewErbivoro) {
                        owner.removeView(view)
                        val container = v as LinearLayout
                        container.addView(view)
                        view.visibility = View.VISIBLE
                    }
                } else if (v.id == R.id.linearLayoutTrash) {
                    owner.removeView(view)
                }
            }
            return true
        }
    }

    private fun verificarRespuesta() {
        val pelotaEnCorrecto = linearLayoutGood.indexOfChild(textViewPelota) != -1
        val pesosEnCorrecto = linearLayoutGood.indexOfChild(textViewPesos) != -1
        val epistemologiaEnCorrecto = linearLayoutGood.indexOfChild(textViewEpistemologia) != -1
        val erbivoroEnCorrecto = linearLayoutGood.indexOfChild(textViewErbivoro) != -1

        val pelotaEnIncorrecto = linearLayoutBad.indexOfChild(textViewPelota) != -1
        val pesosEnIncorrecto = linearLayoutBad.indexOfChild(textViewPesos) != -1
        val epistemologiaEnIncorrecto = linearLayoutBad.indexOfChild(textViewEpistemologia) != -1
        val erbivoroEnIncorrecto = linearLayoutBad.indexOfChild(textViewErbivoro) != -1

        if (pelotaEnCorrecto && !pelotaEnIncorrecto &&
            !pesosEnCorrecto && pesosEnIncorrecto &&
            epistemologiaEnCorrecto && !epistemologiaEnIncorrecto &&
            !erbivoroEnCorrecto && erbivoroEnIncorrecto
        ) {
            Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show()
        } else {
            // La respuesta es incorrecta
            Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show()
        }
    }
}
*/

package com.example.disgamexia.activities

import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.disgamexia.R
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class Activity2 : AppCompatActivity() {
    private lateinit var textViewPelota: TextView
    private lateinit var textViewPesos: TextView
    private lateinit var textViewEpistemologia: TextView
    private lateinit var textViewErbivoro: TextView
    private lateinit var linearLayoutGood: LinearLayout
    private lateinit var linearLayoutBad: LinearLayout
    private lateinit var linearLayoutTrash: LinearLayout
    private var intentos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        textViewPelota = findViewById(R.id.textViewPelota)
        textViewPesos = findViewById(R.id.textViewPesos)
        textViewEpistemologia = findViewById(R.id.textViewEpistemologia)
        textViewErbivoro = findViewById(R.id.textViewErbivoro)
        linearLayoutGood = findViewById(R.id.linearLayoutGood)
        linearLayoutBad = findViewById(R.id.linearLayoutBad)
        linearLayoutTrash = findViewById(R.id.linearLayoutTrash)

        obtenerPalabras()

        textViewPelota.setOnTouchListener(MyTouchListener())
        textViewPesos.setOnTouchListener(MyTouchListener())
        textViewEpistemologia.setOnTouchListener(MyTouchListener())
        textViewErbivoro.setOnTouchListener(MyTouchListener())
        linearLayoutGood.setOnDragListener(MyDragListener())
        linearLayoutBad.setOnDragListener(MyDragListener())
        linearLayoutTrash.setOnDragListener(MyDragListener())

        val buttonVerificar: Button = findViewById(R.id.buttonVerificar)
        buttonVerificar.setOnClickListener {
            verificarRespuesta()
            intentos++
            if (intentos >= 5) {
                intentos = 0
            }
            obtenerPalabras()
        }


        val buttonSiguiente: Button = findViewById(R.id.buttonSiguiente)
        buttonSiguiente.setOnClickListener {
            obtenerPalabras()
        }

    }



    private fun obtenerPalabras() {
        val url = "http://10.40.10.242:80/disgamexia/inicio.php"

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API_REQUEST", "Falló la solicitud: ${e.message}")
                runOnUiThread {
                    Toast.makeText(applicationContext, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body()?.let { responseBody ->
                    try {
                        val jsonString = responseBody.string()
                        val jsonArray = JSONArray(jsonString)
                        runOnUiThread {
                            if (jsonArray.length() >= 4) {
                                textViewPelota.text = jsonArray.getString(0)
                                textViewPesos.text = jsonArray.getString(1)
                                textViewEpistemologia.text = jsonArray.getString(2)
                                textViewErbivoro.text = jsonArray.getString(3)
                            } else {
                                Toast.makeText(applicationContext, "No se encontraron suficientes palabras", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: JSONException) {
                        Log.e("API_RESPONSE", "Error al procesar JSON: ${e.message}")
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: IOException) {
                        Log.e("API_RESPONSE", "Error al leer la respuesta del servidor: ${e.message}")
                        runOnUiThread {
                            Toast.makeText(applicationContext, "Error al leer la respuesta del servidor", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    private inner class MyTouchListener : View.OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDrag(null, shadowBuilder, view, 0)
                return true
            }
            return false
        }
    }

    private inner class MyDragListener : View.OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            val action = event.action
            if (action == DragEvent.ACTION_DROP) {
                val view = event.localState as View
                val owner = view.parent as ViewGroup

                if (v.id == R.id.linearLayoutGood || v.id == R.id.linearLayoutBad) {
                    if (view === textViewPelota || view === textViewPesos || view === textViewEpistemologia || view === textViewErbivoro) {
                        owner.removeView(view)
                        val container = v as LinearLayout
                        container.addView(view)
                        view.visibility = View.VISIBLE
                    }
                } else if (v.id == R.id.linearLayoutTrash) {
                    owner.removeView(view)
                }
            }
            return true
        }
    }

    private var puntaje = 0

    private fun verificarRespuesta() {
        val pelotaEnCorrecto = linearLayoutGood.indexOfChild(textViewPelota) != -1
        val pesosEnCorrecto = linearLayoutGood.indexOfChild(textViewPesos) != -1
        val epistemologiaEnCorrecto = linearLayoutGood.indexOfChild(textViewEpistemologia) != -1
        val erbivoroEnCorrecto = linearLayoutGood.indexOfChild(textViewErbivoro) != -1

        val pelotaEnIncorrecto = linearLayoutBad.indexOfChild(textViewPelota) != -1
        val pesosEnIncorrecto = linearLayoutBad.indexOfChild(textViewPesos) != -1
        val epistemologiaEnIncorrecto = linearLayoutBad.indexOfChild(textViewEpistemologia) != -1
        val erbivoroEnIncorrecto = linearLayoutBad.indexOfChild(textViewErbivoro) != -1

        if (pelotaEnCorrecto && !pelotaEnIncorrecto &&
            !pesosEnCorrecto && pesosEnIncorrecto &&
            epistemologiaEnCorrecto && !epistemologiaEnIncorrecto &&
            !erbivoroEnCorrecto && erbivoroEnIncorrecto
        ) {
            Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show()
            puntaje++
            if (puntaje >= 5) {
                Toast.makeText(this, "Tu puntaje es: $puntaje", Toast.LENGTH_SHORT).show()
                puntaje = 0
            } else {
                obtenerPalabras()
            }
        } else {
            Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show()
        }
    }


}
