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

    lateinit var iv_41:ImageView
    lateinit var iv_42:ImageView
    lateinit var iv_43:ImageView
    lateinit var iv_44:ImageView

    lateinit var imageViews: List<ImageView>


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
    var imagenesArray = arrayOf(11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28)
    var homero = 0
    var bart = 0
    var lisa = 0
    var familia = 0
    var juntos = 0
    var comida = 0
    var apu = 0
    var barney = 0

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
        iv_41 = findViewById(R.id.iv_41)
        iv_42 = findViewById(R.id.iv_42)
        iv_43 = findViewById(R.id.iv_43)
        iv_44 = findViewById(R.id.iv_44)


        // LISTA PARA COMPROBAR TODAS LAS TAGS
        imageViews = listOf(iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24,
            iv_31, iv_32, iv_33, iv_34, iv_41, iv_42, iv_43, iv_44)



        ib_sonido = findViewById(R.id.ib_sonido)
        ib_sonido.setColorFilter(Color.GREEN)

        sonido("background",true)
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
        iv_41.tag = "12"
        iv_42.tag = "13"
        iv_43.tag = "14"
        iv_44.tag = "15"

        homero = R.drawable.homero
        bart = R.drawable.bart
        comida = R.drawable.comida
        familia = R.drawable.familia
        juntos = R.drawable.juntos
        lisa = R.drawable.lisa
        apu = R.drawable.apu
        barney = R.drawable.barney

        imagenesArray.shuffle()

        tv_j1 = findViewById(R.id.tv_j1)
        tv_j2 = findViewById(R.id.tv_j2)
        tv_j2.setTextColor(Color.GRAY)
        tv_j1.setTextColor(Color.WHITE)
        tv_anuncio = findViewById(R.id.tv_anuncio)
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

    private fun mostrarCartaConAnimacion(view: ImageView, tag: Int) {
        view.animate()
            .rotationY(90f)
            .setDuration(150)
            .withEndAction {
                val nuevaImagen = when (imagenesArray[tag]) {
                    11 -> R.drawable.homero
                    12 -> R.drawable.bart
                    13 -> R.drawable.lisa
                    14 -> R.drawable.familia
                    15 -> R.drawable.comida
                    16 -> R.drawable.juntos
                    17 -> R.drawable.apu
                    18 -> R.drawable.barney
                    21 -> R.drawable.homero
                    22 -> R.drawable.bart
                    23 -> R.drawable.lisa
                    24 -> R.drawable.familia
                    25 -> R.drawable.comida
                    26 -> R.drawable.juntos
                    27 -> R.drawable.apu
                    28 -> R.drawable.barney
                    else -> R.drawable.oculta
                }
                view.setImageResource(nuevaImagen)


                view.rotationY = -90f
                view.animate()
                    .rotationY(0f)
                    .setDuration(150)
                    .start()
            }
            .start()
    }


    private fun verificar(imagen: View) {
        var iv = imagen as ImageView
        var tag = imagen.tag.toString().toInt()

        mostrarCartaConAnimacion(iv, tag)

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
            animarAcierto(imagen1)
            animarAcierto(imagen2)
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
            vibrarCarta(imagen1)
            vibrarCarta(imagen2)
            imagen1.setImageResource(R.drawable.oculta)
            imagen2.setImageResource(R.drawable.oculta)
            if (turno==1) {
                tv_anuncio.text = "Turno del jugador 2"
                turno = 2
                tv_j1.setTextColor(Color.GRAY)
                tv_j2.setTextColor(Color.WHITE)
            } else if (turno==2) {
                tv_anuncio.text = "Turno del jugador 1"
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
        iv_41.isEnabled = iv_41.tag.toString().isNotEmpty()
        iv_42.isEnabled = iv_42.tag.toString().isNotEmpty()
        iv_43.isEnabled = iv_43.tag.toString().isNotEmpty()
        iv_44.isEnabled = iv_44.tag.toString().isNotEmpty()

        verificarFinjuego()
    }

    private fun verificarFinjuego() {
        if (imageViews.all { it.tag.toString().isEmpty() }) {
            mp.stop()
            mp.release()
            animarFinalJuego(imageViews)
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
        if (mpFondo.isPlaying) {
            mpFondo.stop()
            mpFondo.release()
        }
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
        iv_41.isEnabled = false
        iv_42.isEnabled = false
        iv_43.isEnabled = false
        iv_44.isEnabled = false

    }
}

fun vibrarCarta(carta: ImageView) {
    carta.animate()
        .translationXBy(20f) // MOVEMOS LA CARTA A LA DERECHA
        .setDuration(50)
        .withEndAction {
            carta.animate()
                .translationXBy(-40f) // MOVEMOS LA CARTA A LA IZQUIERDA DE SU POSICION ORIGINAL
                .setDuration(50)
                .withEndAction {
                    carta.animate()
                        .translationXBy(20f) // LA VOLVEMOS A PONER EN EL CENTRO
                        .setDuration(50)
                }
        }
}
fun animarAcierto(carta: ImageView) {
    carta.animate()
        //AUMENTAMOS EL TAMAÑO VERTICAL Y HORIZONTAL
        .scaleX(1.2f)
        .scaleY(1.2f)
        .alpha(0.7f) // HACEMOS LA CARTA UN POCO TRANSPARENTE
        .setDuration(200)
        .withEndAction { // CUANDO ACABA LA ANIMACION ANTERIOR, LO VOLVEMOS A PONER COMO ESTABA
            carta.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(200)
        }
}
fun animarFinalJuego(imageViews: List<ImageView>) {
    imageViews.forEach { imageView ->
        imageView.animate()
            .scaleX(1.5f) // Aumentar el tamaño en el eje X
            .scaleY(1.5f) // Aumentar el tamaño en el eje Y
            .setDuration(1000) // Duración de la animación
            .withEndAction {
                // Al terminar, puedes devolver las imágenes a su tamaño original si lo deseas
                imageView.animate()
                    .scaleX(1f) // Vuelve al tamaño original en el eje X
                    .scaleY(1f) // Vuelve al tamaño original en el eje Y
                    .setDuration(500) // Tiempo para el regreso
                    .start()
            }
            .start() // Inicia la animación
    }
}





