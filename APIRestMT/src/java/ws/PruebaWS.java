/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojo.OperacionPrueba;
import pojo.OperacionSolicitud;

/**
 * REST Web Service
 *
 * @author Tron7
 */
@Path("prueba")
public class PruebaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of pruebaWS
     */
    public PruebaWS() {
    }

    /**
     * Retrieves representation of an instance of ws.PruebaWS
     * @return an instance of java.lang.String
     */
    @Path("saludar/{nombre},{apellidos}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("nombre") String nombre, @PathParam ("apellidos") String apellidos ) {
        return "Hola "+nombre+" "+apellidos+" un gusto saludarte!!!";
    }
    
    @Path("sumar/{num1},{num2}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OperacionPrueba sumar(@PathParam("num1") Integer num1, @PathParam ("num2") Integer num2){
        OperacionPrueba resultado = new OperacionPrueba();
        resultado.setOperacion("suma");
        resultado.setOperando(num1+","+num2);
        resultado.setResultado((float) (num1+num2));
        return resultado;
    }
    
    @Path("resta")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public OperacionPrueba restar(@FormParam("num1") Integer num1, @FormParam("num2") Integer num2){
        OperacionPrueba resultado = new OperacionPrueba();
        resultado.setOperacion("resta");
        resultado.setOperando(num1+","+num2);
        resultado.setResultado((float) (num1-num2));
        return resultado;
    }
    
    @Path("multiplicar/{num1}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public OperacionPrueba multiplicar(@FormParam("num1") Integer num1, @FormParam("num2") Integer num2){
        OperacionPrueba resultado = new OperacionPrueba();
        resultado.setOperacion("multiplicar");
        resultado.setOperando(num1+","+num2);
        resultado.setResultado((float) (num1*num2));
        return resultado;
    }
    
    @Path("dividir")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OperacionPrueba dividir(String json){
        Gson gson = new Gson();
        OperacionSolicitud datos = gson.fromJson(json, OperacionSolicitud.class);
        OperacionPrueba resultado = new OperacionPrueba();
        resultado.getOperacion(datos.getOperacion());
        resultado.setOperando(datos.getNum1()+","+datos.getNum2());
        resultado.setResultado((float) datos.getNum1()/datos.getNum2());
        
        return resultado;
        
    }
    
}
