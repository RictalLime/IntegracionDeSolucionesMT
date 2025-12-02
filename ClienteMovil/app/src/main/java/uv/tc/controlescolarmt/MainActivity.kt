package uv.tc.controlescolarmt

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import kotlinx.coroutines.CoroutineStart
import uv.tc.controlescolarmt.databinding.ActivityMainBinding
import uv.tc.controlescolarmt.dto.RSAutenticacionAlumno
import uv.tc.controlescolarmt.poko.Alumno
import uv.tc.controlescolarmt.util.Constantes
import kotlin.io.encoding.ExperimentalEncodingApi
import android.util.Base64

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var alumno : Alumno


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mostrarInformacion()
    }

    override fun onStart() {
        super.onStart()
        // Asegúrate de que 'alumno' ya esté inicializado antes de llamar esto
        // Si lo inicializas en mostrarInformacion(), llama a mostrarInformacion() ANTES de onStart si es necesario
        if (::alumno.isInitialized) {
            descargarFotoAlumno(alumno.idAlumno)
        }
    }

    fun mostrarInformacion() {
        try {
            val jsonAlumno: String? = intent.getStringExtra("alumno")
            if (jsonAlumno != null) {
                val gson = Gson()
                // ✅ Corrección: usar ::class.java
                val respuestaLogin: RSAutenticacionAlumno = gson.fromJson(jsonAlumno, RSAutenticacionAlumno::class.java)
                alumno = respuestaLogin.alumno!! // ⚠️ Asumimos que siempre viene; mejor usar let (ver nota abajo)

                // ✅ Corrección: usar .text para TextView
                binding.tvTituloMatricula.text = alumno.matricula
                binding.tvTituloNombre.text = "${alumno.nombre} ${alumno.apellidoPaterno} ${alumno.apellidoMaterno}"
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Error al cargar", Toast.LENGTH_LONG).show()
        }
    }

    // Variable para guardar la respuesta JSON de la foto (la necesitas en cargarFotoPerfilAPI)
    private var jsonFoto: String = ""

    fun descargarFotoAlumno(idAlumno: Int) {
        Ion.with(this@MainActivity)
            .load("GET", "${Constantes().URL_API}alumno/obtener-foto/$idAlumno")
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null) {
                    jsonFoto = result // ✅ Guardamos el resultado para usarlo después
                    cargarFotoPerfilAPI()
                } else {
                    // Opcional: mostrar error
                    Toast.makeText(this, "Error al descargar la foto", Toast.LENGTH_SHORT).show()
                }
            }
    }
    @OptIn(ExperimentalEncodingApi::class)
    fun cargarFotoPerfilAPI() {
        try {
            if (jsonFoto.isNotBlank()) {
                val gson = Gson()
                val alumnoFoto: Alumno = gson.fromJson(jsonFoto, Alumno::class.java)
                if (alumnoFoto.fotoBase64 != null) {
                    val imgBytes = Base64.decode(alumnoFoto.fotoBase64, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.size)
                    binding.ivFotoPerfil.setImageBitmap(bitmap)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al cargar la foto", Toast.LENGTH_SHORT).show()
        }
    }
}