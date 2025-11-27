/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws;

import dominio.AlumnoImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Alumno;

/**
 *
 * @author Tron7
 */
@Path("alumno")
public class AlumnoWS {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> obtenerAlumnos(){
        return AlumnoImp.obtenerAlumnos();
    }
    
    @Path("obtener-por-carrera/{idCarrera}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> obtenerAlumnoCarrera(@PathParam("idCarrera") Integer idCarrera){
        if(idCarrera != null && idCarrera > 0){
            return AlumnoImp.obtenerAlumnosPorCarrera(idCarrera);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-por-id/{idAlumno}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> obtenerIdAlumno(@PathParam("idAlumno") Integer idAlumno){
        if(idAlumno != null && idAlumno > 0){
            return AlumnoImp.obtenerAlumnosPorId(idAlumno);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-por-facultad/{idFacultad}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> obtenerAlumnoFacultad(@PathParam("idFacultad") Integer idFacultad){
        if(idFacultad != null && idFacultad > 0){
            return AlumnoImp.obtenerAlumnosPorFacultad(idFacultad);
        }
        throw new BadRequestException();
    }
    
    @Path("subir-foto/{idAlumno}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta subirFoto(@PathParam("idAlumno") Integer idAlumno, byte[] foto){
        if(idAlumno != null && idAlumno > 0 && foto.length > 0){
            return AlumnoImp.guardarFoto(idAlumno, foto);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-foto/{idAlumno}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Alumno obtenerFoto(@PathParam("idAlumno")Integer idAlumno){
        if(idAlumno != null && idAlumno > 0){
            return AlumnoImp.obtenerFoto(idAlumno);
        }
        throw new BadRequestException();
    }
}
