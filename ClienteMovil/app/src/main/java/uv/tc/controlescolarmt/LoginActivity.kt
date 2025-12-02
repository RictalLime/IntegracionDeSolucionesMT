package uv.tc.controlescolarmt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.koushikdutta.ion.Ion
import uv.tc.controlescolarmt.databinding.ActivityLoginBinding
import uv.tc.controlescolarmt.dto.RSAutenticacionAlumno
import uv.tc.controlescolarmt.util.Constantes

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            verificarCredenciales()
        }
    }

    private fun verificarCredenciales() {
        if (sonCamposValidos()) {
            val matricula = binding.etMatricula.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            consumirAPI(matricula, password)
        }
    }

    private fun sonCamposValidos(): Boolean {
        var valido = true

        if (binding.etMatricula.text.isNullOrBlank()) {
            binding.etMatricula.error = "Matrícula obligatoria"
            valido = false
        } else {
            binding.etMatricula.error = null // Limpiar error si es válido
        }

        if (binding.etPassword.text.isNullOrBlank()) {
            binding.etPassword.error = "Contraseña obligatoria"
            valido = false
        } else {
            binding.etPassword.error = null
        }

        return valido
    }

    private fun consumirAPI(matricula: String, password: String) {
        // Evita múltiples llamadas al hacer clic rápido
        binding.btnIngresar.isEnabled = false

        Ion.getDefault(this).conscryptMiddleware.enable(false)

        Ion.with(this)
            .load("POST", "${Constantes().URL_API}autenticacion/alumno")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("matricula", matricula)
            .setBodyParameter("password", password)
            .asString()
            .setCallback { e: Exception?, result: String? ->
                // Volver a habilitar el botón
                binding.btnIngresar.isEnabled = true

                if (e == null && result != null) {
                    serializarRespuesta(result)
                } else {
                    val errorMsg = e?.message ?: "Error desconocido en la conexión"
                    Toast.makeText(
                        this,
                        "Error: $errorMsg",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun serializarRespuesta(json: String) {
        try {
            val gson = Gson()
            val respuestaLogin = gson.fromJson(json, RSAutenticacionAlumno::class.java)

            if (!respuestaLogin.error) {
                val nombre = respuestaLogin.alumno?.nombre ?: "Usuario"
                Toast.makeText(
                    this,
                    "Bienvenido $nombre a tu app",
                    Toast.LENGTH_LONG
                ).show()

                irPantallaPrincipal(json)
            } else {
                val mensaje = respuestaLogin.mensaje ?: "Error en la autenticación"
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            }
        } catch (ex: JsonSyntaxException) {
            // Más específico: error de formato JSON
            Toast.makeText(
                this,
                "Respuesta inválida del servidor",
                Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            Toast.makeText(
                this,
                "Error al procesar la respuesta",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun irPantallaPrincipal(json : String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("alumno", json)
        startActivity(intent)
        finish()
    }
}