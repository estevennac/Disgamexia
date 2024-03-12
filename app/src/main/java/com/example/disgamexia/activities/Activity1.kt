package com.example.disgamexia.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.disgamexia.R
import java.util.Collections
import java.util.Locale
import java.util.Random

class Activity1 : AppCompatActivity() {
    // Lista de palabras predefinidas
    private val palabras: MutableList<String> = ArrayList()

    // Palabra seleccionada para el juego
    private var palabraSeleccionada: String? = null

    // Campo de texto para la respuesta del jugador
    private var campoRespuesta: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        // Inicializar la lista de palabras
        inicializarPalabras()

        // Referenciar los elementos de la interfaz
        campoRespuesta = findViewById<EditText>(R.id.editTextRespuesta)

        // Iniciar el juego por primera vez
        iniciarJuego()
    }

    // Método para inicializar la lista de palabras predefinidas
    private fun inicializarPalabras() {
        palabras.add("ABEJA")
        palabras.add("PERRO")
        palabras.add("JIRAFA")
        palabras.add("ISLAS")
        palabras.add("MANZANA")
        palabras.add("PELOTA")
        palabras.add("TELÉFONO")
        palabras.add("ARMADA")
        palabras.add("TORTUGA")
        palabras.add("LLUVIA")
        palabras.add("VENTANA")
        palabras.add("COCODRILO")
        palabras.add("CASA")
        palabras.add("ÁRBOL")
        palabras.add("GATO")
        palabras.add("CARRO")
        palabras.add("BICICLETA")
        palabras.add("ESTRELLA")
        palabras.add("FLOR")
        palabras.add("CIELO")
        palabras.add("SOL")
        palabras.add("LUNA")
        palabras.add("PAPEL")
        palabras.add("PLUMA")
        palabras.add("PUERTA")
        palabras.add("VENTANA")
        palabras.add("MESA")
        palabras.add("SILLA")
        palabras.add("CUADRO")
        palabras.add("RELOJ")
        palabras.add("CAMIÓN")
        palabras.add("AVIÓN")
        palabras.add("HELADO")
        palabras.add("PANTALLA")
        palabras.add("BOLÍGRAFO")
        palabras.add("LIBRO")
        palabras.add("CUADERNO")
        palabras.add("LÁPIZ")
        palabras.add("TELEVISIÓN")
        palabras.add("RATÓN")
        palabras.add("PIZZA")
        palabras.add("HAMBURGUESA")
        palabras.add("ZAPATOS")
        palabras.add("CAMISETA")
        palabras.add("PANTALÓN")
        palabras.add("SACO")
        palabras.add("CHAQUETA")
        palabras.add("VESTIDO")
        palabras.add("TRAJE")
        palabras.add("CORBATA")
        palabras.add("GAFAS")
        palabras.add("CINTURÓN")
        palabras.add("GORRA")
        palabras.add("BUFANDA")
        palabras.add("GUANTES")
        palabras.add("PANTUFLAS")
        palabras.add("JUGUETE")
        palabras.add("BALÓN")
        palabras.add("PEINE")
        palabras.add("CEPILLO")
        palabras.add("CHOCOLATE")
        palabras.add("DULCE")
        palabras.add("GALLETAS")
        palabras.add("PAYASO")
        palabras.add("MAGO")
        palabras.add("HÉROE")
        palabras.add("VILLANO")
        palabras.add("TESORO")
        palabras.add("DINOSAURIO")
        palabras.add("MONSTRUO")
        palabras.add("SIRENA")
        palabras.add("PRINCESA")
        palabras.add("PRÍNCIPE")
        palabras.add("CASTILLO")
        palabras.add("PIRATA")
        palabras.add("NAVE")
        palabras.add("ASTRONAUTA")
        palabras.add("PLANETA")
        palabras.add("GALAXIA")
        palabras.add("UNIVERSO")
        palabras.add("VIAJE")
        palabras.add("AVELLANA")
        palabras.add("CEREZA")
        palabras.add("CIRUELA")
        palabras.add("FRESA")
        palabras.add("FRAMBUESA")
        palabras.add("NARANJA")
        palabras.add("KIWI")
        palabras.add("PLÁTANO")
        palabras.add("PERA")
        palabras.add("UVA")
        palabras.add("LIMÓN")
    }


    // Método para iniciar el juego
    private fun iniciarJuego() {
        // Seleccionar una palabra aleatoria de la lista
        val random = Random()
        palabraSeleccionada = palabras[random.nextInt(palabras.size)]

        // Desordenar las letras de la palabra seleccionada
        val letrasDesordenadas: MutableList<Char> = ArrayList()
        for (c in palabraSeleccionada!!.toCharArray()) {
            letrasDesordenadas.add(c)
        }
        Collections.shuffle(letrasDesordenadas)

        // Construir la palabra desordenada
        val palabraDesordenada = StringBuilder()
        for (c in letrasDesordenadas) {
            palabraDesordenada.append(c)
        }

        // Mostrar la palabra desordenada en el TextView
        val textViewPalabraDesordenada = findViewById<TextView>(R.id.textViewPalabraDesordenada)
        textViewPalabraDesordenada.text = palabraDesordenada.toString()

        // Limpiar el campo de respuesta
        campoRespuesta!!.setText("")

        // Limpiar el mensaje
        val textViewMensaje = findViewById<TextView>(R.id.textViewMensaje)
        textViewMensaje.text = ""

        // Ocultar el botón de siguiente palabra
        val botonSiguientePalabra = findViewById<Button>(R.id.buttonSiguientePalabra)
        botonSiguientePalabra.visibility = View.GONE
    }

    // Método para comprobar la respuesta del jugador
    fun comprobarRespuesta(view: View?) {
        // Obtener la respuesta ingresada por el jugador
        val respuesta = campoRespuesta!!.text.toString().uppercase(Locale.getDefault())

        // Verificar si la respuesta es correcta
        if (respuesta == palabraSeleccionada) {
            mostrarMensaje("¡Felicidades! ¡Has acertado!")
            mostrarSiguientePalabra()
        } else {
            mostrarMensaje("Respuesta incorrecta. ¡Inténtalo de nuevo!")
        }
    }

    // Método para mostrar un mensaje en un Toast
    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    // Método para mostrar el botón de siguiente palabra
    private fun mostrarSiguientePalabra() {
        val botonSiguientePalabra = findViewById<Button>(R.id.buttonSiguientePalabra)
        botonSiguientePalabra.visibility = View.VISIBLE
    }

    // Método para pasar a la siguiente palabra
    fun siguientePalabra(view: View?) {
        iniciarJuego()
    }
}