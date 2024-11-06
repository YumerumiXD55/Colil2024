package com.yumerdevs.props

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout


class HomePacienteActivity : AppCompatActivity() {

    private lateinit var progressCircle: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_paciente)



        // Inicializar vistas
        progressCircle = findViewById(R.id.progressCircle)
        progressText = findViewById(R.id.progressText)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)


        // Configurar click listener para los botones de actividad
        val buttonGlucosa = findViewById<RelativeLayout>(R.id.button_glucosa)
        buttonGlucosa.setOnClickListener {
            val intent = Intent(this, GlucosaActivity::class.java)
            startActivity(intent)
        }

        val buttonRutina = findViewById<RelativeLayout>(R.id.button_rutina)
        buttonRutina.setOnClickListener {
            val intent = Intent(this, RutinaActivity::class.java)
            startActivity(intent)
        }

        val buttonMinuta = findViewById<RelativeLayout>(R.id.button_minuta)
        buttonMinuta.setOnClickListener {
            val intent = Intent(this, minutaActivity::class.java)
            startActivity(intent)
        }

        val buttonTfg = findViewById<RelativeLayout>(R.id.button_tfg)
        buttonTfg.setOnClickListener {
            val intent = Intent(this, TfgActivity::class.java)
            startActivity(intent)
        }
        fun updateProgress() {
            // Contar los checkboxes seleccionados
            val totalChecked =
                listOf(checkBox1, checkBox2, checkBox3, checkBox4).count { it.isChecked }
            val progress = totalChecked * 25

            progressCircle.progress = progress
            progressText.text = "$progress%"
        }
    }}
