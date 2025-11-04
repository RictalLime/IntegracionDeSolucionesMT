/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteescritorio.dominio;

import clienteescritorio.conexion.ConexionAPI;
import clienteescritorio.dto.Respuesta;
import clienteescritorio.pojo.Profesor;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.utilidad.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Tron7
 */
public class ProfesorImp {
    
    public static HashMap<String, Object> obtenerTodos(){
        HashMap<String, Object> respuesta = new LinkedHashMap();
        String URL = Constantes.URL_WS + "profesor/obtener_todos";
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Profesor>>(){}.getType();
            List<Profesor> profesores = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put("error", false);
            respuesta.put("profesores", profesores);
        }else{
            respuesta.put("error", true);
            switch(respuestaAPI.getCodigo()) {
                case Constantes.ERROR_MALFORMED_URL:
                    respuesta.put("Mensaje", Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put("Mensaje", Constantes.MSJ_ERROR_PETICION);
                    break;
                default:
                    respuesta.put("Mensaje", "Lo sentimos hay problemas para"+"verificar sus credenciales en este momento, por favor intentelo mas tarde.");
            }
        }
        return respuesta;
    }
    
    public static Respuesta registrar(Profesor profesor){
        Respuesta respuesta = new Respuesta();
        String URL = Constantes.URL_WS + "profesor/registrar";
        Gson gson = new Gson();
        String parametrosJSON = gson.toJson(profesor);
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionBody(URL,Constantes.PETICION_POST, parametrosJSON, Constantes.APPLICATION_JSON);
        if(respuestaAPI.getCodigo() == HttpURLConnection.HTTP_OK){
            respuesta = gson.fromJson(respuestaAPI.getContenido(),Respuesta.class);
        }else{
            respuesta.setError(true);
            switch (respuestaAPI.getCodigo()) {
                case Constantes.ERROR_MALFORMED_URL:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.setMensaje("Campos en formato incorrecto, " + "+porfavor verifica toda la informacion enviada.");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hay problemas para " + "registrar la información en este momento, por favor intentelo mas tarde.");
            }
        }
        return respuesta;
    }
    
    public static Respuesta editar(Profesor profesor){
        Respuesta respuesta = new Respuesta();
        String URL = Constantes.URL_WS + "profesor/editar";
        Gson gson = new Gson();
        String parametrosJSON = gson.toJson(profesor);
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionBody(URL, "PUT", parametrosJSON, Constantes.APPLICATION_JSON);
        if(respuestaAPI.getCodigo() == HttpURLConnection.HTTP_OK){
            respuesta = gson.fromJson(respuestaAPI.getContenido(), Respuesta.class);
        }else{
            respuesta.setError(true);
            switch (respuestaAPI.getCodigo()) {
                case Constantes.ERROR_MALFORMED_URL:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.setMensaje("Campos en formato incorrecto, " + "+porfavor verifica toda la informacion enviada.");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hay problemas para " + "editar la información en este momento, por favor intentelo mas tarde.");
            }
        }
        return respuesta;
    }
}
