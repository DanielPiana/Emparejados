package com.example.appemparejados

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
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
    var bonus = 0
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
        ib_sonido.setColorFilter(Color.BLACK)

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
        tv_j1.setTextColor(getResources().getColor(R.color.azulJugador1))
        tv_anuncio = findViewById(R.id.tv_anuncio)

    }

    private fun sonido(sonidoName: String, loop: Boolean = false) {
        val resID = resources.getIdentifier(sonidoName, "raw", packageName)

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
            ib_sonido.setColorFilter(Color.BLACK)
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

            val puntosAcumulados = 1 + bonus

            if (turno == 1) {
                puntosj1 += puntosAcumulados
                tv_j1.text = "J1: $puntosj1"
                bonus = (bonus + 1).coerceAtMost(3)
                animarCombo(this, findViewById(R.id.fl_contenedor_combo), "¡$bonus x COMBO!")
            } else if (turno == 2) {
                puntosj2 += puntosAcumulados
                tv_j2.text = "J2: $puntosj2"
                bonus = (bonus + 1).coerceAtMost(3)
                animarCombo(this, findViewById(R.id.fl_contenedor_combo), "¡$bonus x COMBO!")
            }
            imagen1.isEnabled = false
            imagen2.isEnabled = false
            imagen1.tag = ""
            imagen2.tag = ""
        } else {
            vibracionMovil(this)
            bonus = 0
            sonido("no")
            animarError(imagen1)
            animarError(imagen2)
            imagen1.setImageResource(R.drawable.oculta)
            imagen2.setImageResource(R.drawable.oculta)
            if (turno==1) {
                animarAnuncio(tv_anuncio,
                    getString(R.string.turno_del_jugador_2),getResources().getColor(R.color.azulJugador1))
                turno = 2
                tv_j1.setTextColor(Color.GRAY)
                tv_j2.setTextColor(getResources().getColor(R.color.azulJugador1))
            } else if (turno==2) {
                animarAnuncio(tv_anuncio,
                    getString(R.string.turno_del_jugador_1),getResources().getColor(R.color.rojoJugador2))
                turno = 1
                tv_j2.setTextColor(Color.GRAY)
                tv_j1.setTextColor(getResources().getColor(R.color.rojoJugador2))
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

            // CREAMOS UNA INSTANCIA DEL DIALOG
            val dialog = dialogFinJuego()


            dialog.arguments = Bundle().apply {
                putInt("puntosJugador1",puntosj1)
                putInt("puntosJugador2",puntosj2)
            }

            dialog.setAcciones(
                jugarDeNuevo = {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                salir = {
                    finish()
                }
            )

            dialog.show(supportFragmentManager, getString(R.string.dialogofinaljuego))
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

fun animarError(carta: ImageView) {
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
            //AUMENTAMOS EL TAMAÑO VERTICAL Y HORIZONTAL
            .scaleX(1.5f)
            .scaleY(1.5f)
            .setDuration(1000)
            .withEndAction { // VUELVE A LAS CARACTERISTICAS ORIGINALES
                imageView.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500)
                    .start()
            }
            .start()
    }
}

fun animarCombo(context: Context, parent: FrameLayout, combo: String) {
    // CREAR UN NUEVO TextView Y CONFIGURAR SUS ATRIBUTOS
    val textView = TextView(context).apply {
        text = combo // ESTABLECER EL TEXTO DEL TextView
        textSize = 30f // ESTABLECER EL TAMAÑO DE FUENTE
        setTypeface(null, Typeface.BOLD) // ESTABLECER EL ESTILO DE FUENTE COMO NEGRITA
        setTextColor(Color.YELLOW) // ESTABLECER EL COLOR DEL TEXTO (AMARILLO)
        setShadowLayer(10f, 0f, 0f, Color.RED) // AÑADIR UNA SOMBRA AL TEXTO (SOMBRAS ROJAS)
        gravity = Gravity.CENTER // CENTRAR EL TEXTO
    }

    // CONFIGURAR LOS PARÁMETROS DE LAYOUT PARA QUE EL TextView SE UBIQUE EN EL CENTRO
    val params = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.WRAP_CONTENT, // ANCHO AJUSTADO AL CONTENIDO
        FrameLayout.LayoutParams.WRAP_CONTENT  // ALTO AJUSTADO AL CONTENIDO
    ).apply {
        gravity = Gravity.CENTER // UBICAR EL TextView EN EL CENTRO DEL FrameLayout
    }

    textView.layoutParams = params // ASIGNAR LOS PARÁMETROS DE LAYOUT AL TextView
    parent.addView(textView) // AÑADIR EL TextView AL FrameLayout

    // INICIAR LA ANIMACIÓN: HACER QUE EL TEXTO SE HAGA GRANDE, BRILLE Y LUEGO SE HAGA PEQUEÑO Y DESAPAREZCA
    textView.animate()
        .scaleX(1.6f) // AMPLIAR EL TextView EN EL EJE X (ANCHO) AL DOBLE DE SU TAMAÑO ORIGINAL
        .scaleY(1.6f) // AMPLIAR EL TextView EN EL EJE Y (ALTO) AL DOBLE DE SU TAMAÑO ORIGINAL
        .alpha(1f) // AÑADIR UNA OPACIDAD COMPLETA AL TEXTO
        .setDuration(500) // DURACIÓN DE ESTA ANIMACIÓN (500 MILISEGUNDOS)
        .withEndAction {
            // SEGUNDA PARTE DE LA ANIMACIÓN: HACERLO PEQUEÑO Y DESAPARECER
            textView.animate()
                .scaleX(0.5f) // HACER EL TextView MÁS PEQUEÑO EN EL EJE X
                .scaleY(0.5f) // HACER EL TextView MÁS PEQUEÑO EN EL EJE Y
                .alpha(0f) // HACER QUE EL TextView DESAPAREZCA (OPACIDAD 0)
                .setDuration(2000) // DURACIÓN DE ESTA SEGUNDA ANIMACIÓN (2000 MILISEGUNDOS)
                .withEndAction {
                    parent.removeView(textView) // ELIMINAR EL TextView DEL FrameLayout AL TERMINAR LA ANIMACIÓN
                }
                .start() // INICIAR LA SEGUNDA PARTE DE LA ANIMACIÓN
        }
        .start() // INICIAR LA PRIMERA PARTE DE LA ANIMACIÓN
}

fun vibracionMovil(context: Context) {
    // OBTENEMOS EL SERVICIO DE VIBRACIÓN DEL SISTEMA
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    // COMPROBAMOS SI EL DISPOSITIVO TIENE SOPORTE PARA VIBRACIÓN
    if (vibrator.hasVibrator()) {
        // CREAMOS UN PATRÓN DE VIBRACIÓN (TIEMPO EN MILISEGUNDOS)
        // EL PATRÓN ES: [TIEMPO DE VIBRACIÓN, TIEMPO DE ESPERA, TIEMPO DE VIBRACIÓN]
        val pattern = longArrayOf(0, 200, 100, 200) // VIBRA 200MS, ESPERA 100MS, VIBRA 200MS

        // VERIFICAMOS LA VERSIÓN DE ANDROID Y APLICAMOS EL MÉTODO CORRESPONDIENTE
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // PARA VERSIONES DE ANDROID OREO (API 26) O SUPERIOR, USAMOS VIBRATIONEFFECT
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1)) // EL -1 SIGNIFICA QUE NO SE REPITE
        } else {
            vibrator.vibrate(pattern, -1) // EL -1 SIGNIFICA QUE NO SE REPITE
        }
    }
}

fun animarAnuncio(textView: TextView, anuncio: String, colorJugador:Int) {
    textView.setTextColor(colorJugador)

    // ESCALA DEL TEXTVIEW (EFECTO DE CRECER Y LUEGO VOLVER A SU TAMAÑO ORIGINAL)
    val scaleX = android.animation.ObjectAnimator.ofFloat(textView, "scaleX", 1f, 1.5f, 1f) // Animación de escala horizontal
    val scaleY = android.animation.ObjectAnimator.ofFloat(textView, "scaleY", 1f, 1.5f, 1f) // Animación de escala vertical

    // DURACIÓN DE LA ANIMACIÓN (500 MILISEGUNDOS)
    scaleX.duration = 500
    scaleY.duration = 500

    // INICIAMOS LA ANIMACIÓN
    scaleX.start()
    scaleY.start()

    // CAMBIAMOS EL TEXTO A "ES EL TURNO DE [NOMBRE]" O LO QUE QUIERAS
    textView.text = anuncio
}