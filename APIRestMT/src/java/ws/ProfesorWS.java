/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws;

import com.google.gson.Gson;
import dominio.profesorImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Profesor;

/**
 *
 * @author Tron7
 */
@Path("profesor")
public class ProfesorWS {
    @Path("obtener_todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profesor> obtenerProfesor(){
        return profesorImp.obtenerProfesor();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json){
        Gson gson = new Gson();
        try{
            Profesor profesor = gson.fromJson(json, Profesor.class);
            return profesorImp.registrarProfesor(profesor);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar(String json){
        Gson gson = new Gson();
        try {
            Profesor profesor = gson.fromJson(json, Profesor.class);
            return profesorImp.editarProfesor(profesor);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@PathParam("id") int idProfesor){
        try {
            return profesorImp.eliminarProfesor(idProfesor);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
