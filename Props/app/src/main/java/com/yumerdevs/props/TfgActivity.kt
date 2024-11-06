package com.yumerdevs.props

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import java.text.DecimalFormat

class TfgActivity : AppCompatActivity() {

    private var generoSeleccionado = -1 // -1 para ninguno seleccionado, 0 para masculino, 1 para femenino

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tfg)

        val editTextEdad = findViewById<EditText>(R.id.edad_creatanina)
        val editTextCreatinina = findViewById<EditText>(R.id.creatinina)

        val buttonMasculino = findViewById<ImageButton>(R.id.masculino)
        val buttonFemenino = findViewById<ImageButton>(R.id.femenino)
        val botonCalcular = findViewById<Button>(R.id.calcular)

        // Manejar selección de género masculino
        buttonMasculino.setOnClickListener {
            generoSeleccionado = 0
            buttonMasculino.setBackgroundResource(R.drawable.tfg_genero_masculino_select) // Fondo seleccionado para masculino
            buttonFemenino.setBackgroundResource(R.drawable.tfg_genero2) // Fondo no seleccionado para femenino
        }

        buttonFemenino.setOnClickListener {
            generoSeleccionado = 1
            buttonFemenino.setBackgroundResource(R.drawable.tfg_genero_femenino_seleccionado) // Fondo seleccionado para femenino
            buttonMasculino.setBackgroundResource(R.drawable.tfg_genero) // Fondo no seleccionado para masculino
        }
        botonCalcular.setOnClickListener {
            Log.d("TfgActivity", "Botón calcular presionado") // Agregado para verificar

            val edad = editTextEdad.text.toString().toIntOrNull()
            val creatinina = editTextCreatinina.text.toString().toDoubleOrNull()

            if (edad != null && creatinina != null && generoSeleccionado != -1) {
                val tfg = calcularTfg(edad, creatinina, generoSeleccionado)
                mostrarDialogoResultado(tfg)
            } else {
                Log.e("TfgActivity", "Datos inválidos")
            }
        }
    }

    private fun calcularTfg(edad: Int, creatinina: Double, genero: Int): Double {
        val (A, B) = when {
            (genero == 0 && creatinina <= 0.9) -> Pair(0.9, -0.302) // Hombre, Scr ≤ 0.9
            (genero == 0 && creatinina > 0.9) -> Pair(0.9, -1.209) // Hombre, Scr > 0.9
            (genero == 1 && creatinina <= 0.7) -> Pair(0.7, -0.241) // Mujer, Scr ≤ 0.7
            (genero == 1 && creatinina > 0.7) -> Pair(0.7, -1.209) // Mujer, Scr > 0.7
            else -> throw IllegalArgumentException("Valores de creatinina no válidos")
        }

        // Cálculo de TFG y suma de 0.4 antes de redondear
        val tfgRaw = 141 * (creatinina / A).pow(B) * (0.9938).pow(edad) * if (genero == 1) 1.012 else 1.0
        return (Math.round((tfgRaw + .49) * 10.0) / 10.0) // Suma 0.4 y redondea a un decimal
    }

    private fun mostrarDialogoResultado(tfg: Double) {
        try {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_resultado)

            val tituloResultado = dialog.findViewById<TextView>(R.id.tituloResultado)
            val mensajeResultado = dialog.findViewById<TextView>(R.id.mensajeResultado)
            val botonCerrar = dialog.findViewById<Button>(R.id.botonCerrar)

            if (tituloResultado == null || mensajeResultado == null || botonCerrar == null) {
                Log.e("TfgActivity", "Algún elemento del diálogo no fue encontrado")
                return
            }

            val formatoDecimal = DecimalFormat("#") // Formato sin decimales
            val tfgRedondeado = formatoDecimal.format(tfg)

            mensajeResultado.text = "Tu TFG es: $tfgRedondeado mg/dl\n" + when {
                tfg < 60 -> "Indicativo de enfermedad renal."
                tfg in 60.0..89.9 -> "Función renal levemente disminuida."
                else -> "Función renal normal."
            }

            botonCerrar.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
            Log.d("TfgActivity", "Diálogo mostrado correctamente")
        } catch (e: Exception) {
            Log.e("TfgActivity", "Error al mostrar el diálogo: ${e.message}")
        }
    }
}
