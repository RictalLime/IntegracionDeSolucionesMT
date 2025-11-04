/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import Utilidades.Constantes;
import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Profesor;

/**
 *
 * @author Tron7
 */
public class ProfesorImp {
    
    public static List<Profesor> obtenerProfesor(){
        List<Profesor> profesores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                profesores = conexionBD.selectList("profesor.obtener_todos");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return profesores;
    }
    
    public static Respuesta registrarProfesor(Profesor profesor){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.insert("profesor.registrar", profesor);
                conexionBD.commit();
                if(filasAfectadas == 1){
                    respuesta.setError(false);
                    respuesta.setMensaje("Registro del profesor"+profesor.getNombre()+"Agregado correctamente");
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos la informacion no pudo ser guardada.");
                    conexionBD.close();
                }
            }catch (Exception e){
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje("");
            }
            conexionBD.close();
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta editarProfesor(Profesor profesor){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.update("profesor.editar", profesor);
                conexionBD.commit();
                if (filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Informacion del profesor "+profesor.getNombre()+", actualizada correctamente");
                }else{
                    respuesta.setMensaje("Lo sentimos :( la informacion no pudo ser actualizada");
                    conexionBD.close();
                }
            }catch (Exception e) {
                conexionBD.rollback();
                respuesta.setMensaje(e.getMessage());
            }
            conexionBD.close();
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta eliminarProfesor(int idProfesor){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.delete("profesor.eliminar", idProfesor);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    respuesta.setError(true);
                    respuesta.setMensaje("Informacion del profesor "+idProfesor+", actualizada correctamente");
                }else{
                    respuesta.setMensaje("Lo sentimos :( la informacion no pudo ser actualizada");
                    conexionBD.close();
                }
            }catch (Exception e){
                conexionBD.rollback();
                respuesta.setMensaje(e.getMessage());
            }
            conexionBD.close();
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
}
