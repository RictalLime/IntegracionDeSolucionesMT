/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Tron7
 */
public class OperacionPrueba {
    
    private String operacion;
    private Float resultado;
    private String operando;

    public OperacionPrueba(String operacion, Float resultado, String operando) {
        this.operacion = operacion;
        this.resultado = resultado;
        this.operando = operando;
    }

    public OperacionPrueba() {
        
    }

    public String getOperacion(String operacion1) {
        return operacion;
    }

    public Float getResultado() {
        return resultado;
    }

    public String getOperando() {
        return operando;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void setResultado(Float resultado) {
        this.resultado = resultado;
    }

    public void setOperando(String operando) {
        this.operando = operando;
    }
    
    
}
