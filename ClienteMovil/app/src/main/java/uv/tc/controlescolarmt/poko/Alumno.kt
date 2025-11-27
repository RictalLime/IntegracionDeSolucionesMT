package uv.tc.controlescolarmt.poko

data class Alumno(
    val idAlumno: Int,
    val nombre: String,
    val apellidoPaterno: String,
    var apellidoMaterno: String?,
    val matricula: String,
    val correo: String,
    val fechaNacimiento: String,
    val idCarrera: Int,
    val carrera: String,
    val idFacultad: Int,
    val facultad: String,
    var fotoBase64: String?
)
