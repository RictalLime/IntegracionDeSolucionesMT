/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Tron7
 */
public class Facultad {
    
    private int idFacultad;
    private String facultad;

    public Facultad() {
    }

    public Facultad(int idFacultad, String facultad) {
        this.idFacultad = idFacultad;
        this.facultad = facultad;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    
}
