/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import dto.RSAutenticacionAdmin;
import java.util.HashMap;
import java.util.LinkedHashMap;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Profesor;

/**
 *
 * @author Tron7
 */
public class AutenticacionImp {
    
    public static RSAutenticacionAdmin autenticarAdministrador(String noPersonal, String password){
        //Todo la llamada al mapper y el procesamiento de la informacion de retorno
        RSAutenticacionAdmin respuesta = new RSAutenticacionAdmin();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            //Estoy en flujo principal
            try{
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("password", password);
                Profesor profesor = conexionBD.selectOne("autenticacion.administrador", parametros);
                if (profesor != null){
                    //Flujo principal credenciales correctas
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del usuario: "+profesor.getNombre());
                    respuesta.setProfesor(profesor);
                }else{
                    // Flujo alterno 3 Credenciales incorrectas
                    respuesta.setError(true);
                    respuesta.setMensaje("Credenciales incorrectas, " + "por favor verifique la información");
                }
            }catch (Exception e){
                // Flujo alterno 2 Error en la ejecucion del Query
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            // Flujo alterno 1 Sin conecion a BD
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos por el momento no hay conexion a los datos...");
        }
        return respuesta;
    }
}
