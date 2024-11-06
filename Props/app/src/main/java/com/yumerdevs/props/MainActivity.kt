package com.yumerdevs.props

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Definición de la data class Persona
data class Persona(
    val username: String,
    val password: String,
    val nombre: String,
    val apellido: String,
    val edad: String,
    val fecha_Nacimiento: String
)

// Lista de usuarios
val listaUsuario = mutableListOf(
    Persona(
        username = "1",
        password = "1",
        nombre = "Tomas",
        apellido = "Mella",
        edad = "22",
        fecha_Nacimiento = "26/08/2002"
    ),
    Persona(
        username = "mati123",
        password = "mati123#",
        nombre = "Matias",
        apellido = "Fernandez",
        edad = "20",
        fecha_Nacimiento = "12/12/2004"
    )
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datos() // Llama a datos()
    }

    private fun datos() {
        val editTextUsername = findViewById<EditText>(R.id.etUsername)
        val editTextPassword = findViewById<EditText>(R.id.etPassword)
        val buttonLogin = findViewById<Button>(R.id.submit_button)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (validarUsuario(username, password)) {
                // Iniciar HomePacienteActivity
                val intent = Intent(this, HomePacienteActivity::class.java)
                startActivity(intent)
                finish() // Opcional: cerrar la actividad actual
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarUsuario(username: String, password: String): Boolean {
        for (usuario in listaUsuario) {
            if (usuario.username == username && usuario.password == password) {
                return true
            }
        }
        return false
    }
}
