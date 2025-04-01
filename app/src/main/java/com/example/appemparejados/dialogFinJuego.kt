package com.example.appemparejados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import android.text.Html


class dialogFinJuego : DialogFragment() {
    private lateinit var alJugarDeNuevo: () -> Unit
    private lateinit var alSalir: () -> Unit

    fun setAcciones(jugarDeNuevo: () -> Unit, salir: () -> Unit) {
        alJugarDeNuevo = jugarDeNuevo
        alSalir = salir
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_final_juego, container, false)
        val puntuacionJugador1 = view.findViewById<TextView>(R.id.tv_resultadoj1)
        val puntuacionJugador2 = view.findViewById<TextView>(R.id.tv_resultadoj2)
        val botonJugarDeNuevo = view.findViewById<Button>(R.id.b_jugarDeNuevo)
        val botonSalir = view.findViewById<Button>(R.id.b_salir)

        // Obtener los puntajes de los argumentos
        val puntosJugador1 = arguments?.getInt("puntosJugador1") ?: 0
        val puntosJugador2 = arguments?.getInt("puntosJugador2") ?: 0

        puntuacionJugador1.text = Html.fromHtml("<u>Jugador 1</u><br>$puntosJugador1 puntos")
        puntuacionJugador2.text = Html.fromHtml("<u>Jugador 2</u><br>$puntosJugador2 puntos")

        botonJugarDeNuevo.setOnClickListener {
            alJugarDeNuevo()
            dismiss()
        }

        botonSalir.setOnClickListener {
            alSalir()
            dismiss()
        }

        return view
    }

}