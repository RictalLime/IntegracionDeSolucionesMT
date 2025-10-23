/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteescritorio.dto;

import clienteescritorio.pojo.Profesor;

/**
 *
 * @author Tron7
 */
public class RSAutenticacionAdmin {
    
    public boolean error;
    public String mensaje;
    public Profesor profesor;

    public RSAutenticacionAdmin() {
    }

    public RSAutenticacionAdmin(boolean error, String mensaje, Profesor profesor) {
        this.error = error;
        this.mensaje = mensaje;
        this.profesor = profesor;
    }

    public boolean isError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
}
