package uv.tc.controlescolarmt.dto

import uv.tc.controlescolarmt.poko.Alumno

data class RSAutenticacionAlumno(
    val error: Boolean,
    val mensaje: String,
    var alumno: Alumno?
)
