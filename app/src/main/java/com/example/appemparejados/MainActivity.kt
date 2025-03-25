package com.example.appemparejados

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Looper

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //<editor-fold desc="IMAGENES GUI">
    lateinit var iv_11:ImageView
    lateinit var iv_12:ImageView
    lateinit var iv_13:ImageView
    lateinit var iv_14:ImageView

    lateinit var iv_21:ImageView
    lateinit var iv_22:ImageView
    lateinit var iv_23:ImageView
    lateinit var iv_24:ImageView

    lateinit var iv_31:ImageView
    lateinit var iv_32:ImageView
    lateinit var iv_33:ImageView
    lateinit var iv_34:ImageView
    //</editor-fold>
    //<editor-fold desc="OTROS GUI">
    lateinit var tv_j1:TextView
    lateinit var tv_j2:TextView
    lateinit var tv_anuncio:TextView

    lateinit var ib_sonido:ImageButton

    lateinit var mp: MediaPlayer
    lateinit var mpFondo:MediaPlayer
    lateinit var imagen1:ImageView
    lateinit var imagen2:ImageView
    //</editor-fold>
    //<editor-fold desc="VARIABLES GUI">
    var imagenesArray = arrayOf(11,12,13,14,15,16,21,22,23,24,25,26)
    var homero = 0
    var bart = 0
    var lisa = 0
    var familia = 0
    var juntos = 0
    var comida = 0

    var turno = 1
    var puntosj1 = 0
    var puntosj2 = 0
    var numeroImagen = 1
    var escuchar = true
    //</editor-fold>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        enlazarGUI()
    }

    private fun enlazarGUI() { // METODO PARA ENLAZAR LA INTERFAZ GRAFICA CON VARIABLES
        iv_11 = findViewById(R.id.iv_11)
        iv_12 = findViewById(R.id.iv_12)
        iv_13 = findViewById(R.id.iv_13)
        iv_14 = findViewById(R.id.iv_14)
        iv_21 = findViewById(R.id.iv_21)
        iv_22 = findViewById(R.id.iv_22)
        iv_23 = findViewById(R.id.iv_23)
        iv_24 = findViewById(R.id.iv_24)
        iv_31 = findViewById(R.id.iv_31)
        iv_32 = findViewById(R.id.iv_32)
        iv_33 = findViewById(R.id.iv_33)
        iv_34 = findViewById(R.id.iv_34)

        ib_sonido = findViewById(R.id.ib_sonido)
        ib_sonido.setColorFilter(Color.GREEN)

        sonido("background",true)
        println("sonido")
        iv_11.tag = "0"
        iv_12.tag = "1"
        iv_13.tag = "2"
        iv_14.tag = "3"
        iv_21.tag = "4"
        iv_22.tag = "5"
        iv_23.tag = "6"
        iv_24.tag = "7"
        iv_31.tag = "8"
        iv_32.tag = "9"
        iv_33.tag = "10"
        iv_34.tag = "11"

        homero = R.drawable.homero
        bart = R.drawable.bart
        comida = R.drawable.comida
        familia = R.drawable.familia
        juntos = R.drawable.juntos
        lisa = R.drawable.lisa

        imagenesArray.shuffle()

        tv_j1 = findViewById(R.id.tv_j1)
        tv_j2 = findViewById(R.id.tv_j2)
        tv_j2.setTextColor(Color.GRAY)
        tv_j1.setTextColor(Color.WHITE)
        tv_anuncio = findViewById(R.id.tv_turno)
    }

    private fun sonido(sonidoName: String, loop: Boolean = false) {
        var resID = resources.getIdentifier(sonidoName, "raw", packageName)

        if (sonidoName == "background") {
            mpFondo = MediaPlayer.create(this,resID)
            mpFondo.isLooping = loop
            mpFondo.setVolume(0.04F,0.04F)
            if(!mpFondo.isPlaying) {
                mpFondo.start()
            }
        } else {
            mp = MediaPlayer.create(this,resID)
            mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            })
            if (!mp.isPlaying) {
                mp.start()
            }
        }
    }
    fun musicaFondo(v:View) {
        if (escuchar) {
            mpFondo.pause()
            ib_sonido.setImageResource(R.drawable.ic_volumen_off)
            ib_sonido.setColorFilter(Color.GRAY)
        } else {
            mpFondo.start()
            ib_sonido.setImageResource(R.drawable.ic_volumen_on)
            ib_sonido.setColorFilter(Color.GREEN)
        }
        escuchar = !escuchar
    }

    fun seleccionar(imagen:View) {
        sonido("touch")
        verificar(imagen)
    }

    private fun verificar(imagen: View) {
        var iv = (imagen) as ImageView
        var tag = imagen.tag.toString().toInt()
        if (imagenesArray[tag] == 11) {
            iv.setImageResource(homero)
        } else if (imagenesArray[tag] == 12) {
            iv.setImageResource(bart)
        } else if (imagenesArray[tag] == 13) {
            iv.setImageResource(lisa)
        } else if (imagenesArray[tag] == 14) {
            iv.setImageResource(familia)
        } else if (imagenesArray[tag] == 15) {
            iv.setImageResource(comida)
        } else if (imagenesArray[tag] == 16) {
            iv.setImageResource(juntos)
        } else if (imagenesArray[tag] == 21) {
            iv.setImageResource(homero)
        } else if (imagenesArray[tag] == 22) {
            iv.setImageResource(bart)
        } else if (imagenesArray[tag] == 23) {
            iv.setImageResource(lisa)
        } else if (imagenesArray[tag] == 24) {
            iv.setImageResource(familia)
        } else if (imagenesArray[tag] == 25) {
            iv.setImageResource(comida)
        } else if (imagenesArray[tag] == 26) {
            iv.setImageResource(juntos)
        }
        println(numeroImagen)
        if (numeroImagen == 1) {
            imagen1 = iv
            numeroImagen = 2
            iv.isEnabled = false
        } else if (numeroImagen == 2) {
            imagen2 = iv
            numeroImagen = 1
            iv.isEnabled = false

            deshabilitarImagenes()
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                sonImagenesIguales()
            }, 1000)
        }

    }

    private fun sonImagenesIguales() {
        if (imagen1.drawable.constantState == imagen2.drawable.constantState) {
            sonido("success")
            if (turno == 1) {
                puntosj1++
                tv_j1.text = "J1: $puntosj1"
            } else if (turno == 2) {
                puntosj2++
                tv_j2.text = "J2: $puntosj2"
            }
            imagen1.isEnabled = false
            imagen2.isEnabled = false
            imagen1.tag = ""
            imagen2.tag = ""
        } else {
            sonido("no")
            imagen1.setImageResource(R.drawable.oculta)
            imagen2.setImageResource(R.drawable.oculta)
            if (turno==1) {
                turno = 2
                tv_j1.setTextColor(Color.GRAY)
                tv_j2.setTextColor(Color.WHITE)
            } else if (turno==2) {
                turno = 1
                tv_j2.setTextColor(Color.GRAY)
                tv_j1.setTextColor(Color.WHITE)
            }
        }
        iv_11.isEnabled = iv_11.tag.toString().isNotEmpty()
        iv_12.isEnabled = iv_12.tag.toString().isNotEmpty()
        iv_13.isEnabled = iv_13.tag.toString().isNotEmpty()
        iv_14.isEnabled = iv_14.tag.toString().isNotEmpty()
        iv_21.isEnabled = iv_21.tag.toString().isNotEmpty()
        iv_22.isEnabled = iv_22.tag.toString().isNotEmpty()
        iv_23.isEnabled = iv_23.tag.toString().isNotEmpty()
        iv_24.isEnabled = iv_24.tag.toString().isNotEmpty()
        iv_31.isEnabled = iv_31.tag.toString().isNotEmpty()
        iv_32.isEnabled = iv_32.tag.toString().isNotEmpty()
        iv_33.isEnabled = iv_33.tag.toString().isNotEmpty()
        iv_34.isEnabled = iv_34.tag.toString().isNotEmpty()

        verificarFinjuego()
    }

    private fun verificarFinjuego() {
        if (
            iv_11.tag.toString().isEmpty() &&
            iv_12.tag.toString().isEmpty() &&
            iv_13.tag.toString().isEmpty() &&
            iv_14.tag.toString().isEmpty() &&
            iv_21.tag.toString().isEmpty() &&
            iv_22.tag.toString().isEmpty() &&
            iv_23.tag.toString().isEmpty() &&
            iv_24.tag.toString().isEmpty() &&
            iv_31.tag.toString().isEmpty() &&
            iv_32.tag.toString().isEmpty() &&
            iv_33.tag.toString().isEmpty() &&
            iv_34.tag.toString().isEmpty()
        ) {
            mp.stop()
            mp.release()
            sonido("win")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("FIN DEL JUEGO")
                .setMessage("PUNTAJE \nJ1: $puntosj1\nJ2: $puntosj2")
                .setCancelable(false)
                .setPositiveButton("Nuevo juego",
                    DialogInterface.OnClickListener{ dialogInterface, i ->
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    })
                .setNegativeButton("Salir",
                    DialogInterface.OnClickListener{ dialogInterface, i ->
                        finish()
                    })
            val ad = builder.create()
            ad.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mpFondo.stop()
        mpFondo.release()
    }

    private fun deshabilitarImagenes() {
        iv_11.isEnabled = false
        iv_12.isEnabled = false
        iv_13.isEnabled = false
        iv_14.isEnabled = false
        iv_21.isEnabled = false
        iv_22.isEnabled = false
        iv_23.isEnabled = false
        iv_24.isEnabled = false
        iv_31.isEnabled = false
        iv_32.isEnabled = false
        iv_33.isEnabled = false
        iv_34.isEnabled = false

    }
}