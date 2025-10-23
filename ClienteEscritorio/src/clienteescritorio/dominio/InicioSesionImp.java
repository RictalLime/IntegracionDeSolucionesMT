/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteescritorio.dominio;

import clienteescritorio.conexion.ConexionAPI;
import clienteescritorio.dto.RSAutenticacionAdmin;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.utilidad.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;

/**
 *
 * @author Tron7
 */
public class InicioSesionImp {
    
    public static RSAutenticacionAdmin verificarCredenciales(String noPersonal, String password){
        RSAutenticacionAdmin respuesta = new RSAutenticacionAdmin();
        String parametros = "noPersonal="+noPersonal+"&password="+password;
        String URL = Constantes.URL_WS + "autenticacion/administracion";
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionBody(URL, "POST", parametros, "application/x-www-form-urlencoded");
        
        if(respuestaAPI.getCodigo() == HttpURLConnection.HTTP_OK){
            try {
                Gson gson = new Gson();
                respuesta = gson.fromJson(respuestaAPI.getContenido(), RSAutenticacionAdmin.class);
                
            }catch (Exception e){
                respuesta.setError(true); // original true
                respuesta.setMensaje("Lo sentimos hubo un error al obtener"+"la informacionintentelo mas tarde.");
            }
        }else{
            respuesta.setError(true);
            switch(respuestaAPI.getCodigo()) {
                case Constantes.ERROR_MALFORMED_URL:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.setMensaje("Datos requeridos para poder realizar la operacion solicitada.");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hay problemas para"+"verificar sus credenciales en este momento, por favor intentelo mas tarde.");
            }
        }
        return respuesta;
    }
}
