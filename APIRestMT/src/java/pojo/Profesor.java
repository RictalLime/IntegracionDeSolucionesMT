/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Tron7
 */
public class Profesor {
    
    private Integer idProfesor;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String noPersonal;
    private String password;
    private String fechaNacimiento;
    private String fechaContratacion;
    private int  idRol;
    private String rol;
    private byte[] foto;
    private String fotoBase64;

    public Profesor(Integer idProfesor, String nombre, String apellidoPaterno, String apellidoMaterno, String noPersonal, String password, String fechaNacimiento, String fechaContratacion, int idRol, String rol) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.noPersonal = noPersonal;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContratacion = fechaContratacion;
        this.idRol = idRol;
        this.rol = rol;
    }

    public Profesor() {
    }

    public Integer getIdProfesor() {
        return idProfesor;
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

    public String getNoPersonal() {
        return noPersonal;
    }

    public String getPassword() {
        return password;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public int getIdRol() {
        return idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
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

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void setFechaContratacion(String fechaContratacion){
        this.fechaContratacion = fechaContratacion;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }
    
    
}
