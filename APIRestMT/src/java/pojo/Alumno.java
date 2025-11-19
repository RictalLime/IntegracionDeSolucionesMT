/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Tron7
 */
public class Alumno {
    
    private int idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String matricula;
    private String correo;
    private String fechaNacimiento;
    private int idCarrera;
    private String carrera;
    private int idFacultad;
    private String facultad;
    private byte[] foto;
    private String fotoBase64;
    private String password;

    public Alumno() {
    }

    public Alumno(int idAlumno, String nombre, String apellidoPaterno, String apellidoMaterno, String matricula, String correo, String fechaNacimiento, int idCarrera, String carrera, int idFacultad, String facultad, byte[] foto, String fotoBase64) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.matricula = matricula;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.idFacultad = idFacultad;
        this.facultad = facultad;
        this.foto = foto;
        this.fotoBase64 = fotoBase64;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
