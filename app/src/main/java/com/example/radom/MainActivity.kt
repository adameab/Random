package com.example.radom

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var numeroOculto = 0
    private var intentos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumero = findViewById<EditText>(R.id.etNumero)
        val btnComprobar = findViewById<Button>(R.id.btnComprobar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnReiniciar = findViewById<Button>(R.id.btnReiniciar)

        generarNuevoNumero()

        btnComprobar.setOnClickListener {
            val textoIntroducido = etNumero.text.toString()

            if (textoIntroducido.isEmpty()) {
                etNumero.error = "Introduce un número"
                return@setOnClickListener
            }

            val numeroUsuario = textoIntroducido.toInt()

            // Comprobar rango y mostrar aviso en el propio recuadro
            if (numeroUsuario !in 1..100) {
                etNumero.error = "por favor mete un numero entre 1-100"
                return@setOnClickListener
            }

            intentos++

            when {
                numeroUsuario < numeroOculto -> {
                    tvResultado.text = "El número oculto es MAYOR"
                    etNumero.text.clear()
                }
                numeroUsuario > numeroOculto -> {
                    tvResultado.text = "El número oculto es MENOR"
                    etNumero.text.clear()
                }
                else -> {
                    tvResultado.text = "¡Correcto! Te han hecho falta $intentos intentos."
                    btnComprobar.isEnabled = false
                    etNumero.isEnabled = false
                    btnReiniciar.visibility = View.VISIBLE
                }
            }
        }

        btnReiniciar.setOnClickListener {
            generarNuevoNumero()
            tvResultado.text = "Introduce un número del 1 al 100 para empezar."
            etNumero.text.clear()
            btnComprobar.isEnabled = true
            etNumero.isEnabled = true
            btnReiniciar.visibility = View.GONE
        }
    }

    private fun generarNuevoNumero() {
        numeroOculto = Random.nextInt(1, 101) // Genera de 1 a 100
        intentos = 0
    }
}