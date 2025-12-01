package uv.tc.controlescolarmt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import uv.tc.controlescolarmt.databinding.ActivityLoginBinding
import uv.tc.controlescolarmt.dto.RSAutenticacionAlumno
import uv.tc.controlescolarmt.util.Constantes
import android.util.Log


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnIngresar.setOnClickListener {
            verificarCredenciales()
        }
    }

    fun verificarCredenciales(){
        if(sonCamposValidos()) {
            consumirAPI(matricula = binding.etMatricula.text.toString(), password = binding.etPassword.text.toString())
        }
    }

    fun sonCamposValidos(): Boolean{
        var valido = true
        if(binding.etMatricula.text.isEmpty()){
            binding.etMatricula.setError("Matricula abligatoria")
            valido = false

        }
        if(binding.etPassword.text.isNullOrBlank()){
            binding.etPassword.error = "Contraseña abligatoria"
            valido = false
        }
        return valido
        }
    fun consumirAPI(matricula: String, password: String){
        //Configuracion iniciar: solo primer vez
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)
        //Consumo WS
        Ion.with(this@LoginActivity).load("POST", "${Constantes().URL_API}autenticacion/alumno")
            .setHeader("Content-Type", "Content-Type: application/x-www-form-urlencoded")
            .setBodyParameter("matricula", matricula)
            .setBodyParameter("password", password)
            .asString().setCallback { e, result ->
                if (e == null) {
                    // 200 OK
                    serializarRespuesta(result)
                }else{
                    //ERROR
                    Toast.makeText(this@LoginActivity, "Error: ${e.message}",Toast.LENGTH_LONG).show()
                }
            }

    }
    fun serializarRespuesta(json : String){
        Log.e("WS", json)
        try{
            val gson = Gson()
            val respuestaLogin = gson.fromJson(json, RSAutenticacionAlumno::class.java)
            if(!respuestaLogin.error){
                Toast.makeText(this@LoginActivity, "Bienvenido(a) ${respuestaLogin.alumno!!.nombre} a tu app de alumnos",Toast.LENGTH_LONG).show()
                irPantallaPrincipal()
            }else{
                Toast.makeText(this@LoginActivity, respuestaLogin.mensaje,Toast.LENGTH_LONG).show()
            }
        }catch (e : Exception){
            Toast.makeText(this@LoginActivity, "Losentimos hubo un error en la solicitud.",Toast.LENGTH_LONG).show()
        }
    }
    fun irPantallaPrincipal(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    }
