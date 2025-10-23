/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritorio;

import clienteescritorio.dominio.InicioSesionImp;
import clienteescritorio.dto.RSAutenticacionAdmin;
import clienteescritorio.pojo.Profesor;
import clienteescritorio.utilidad.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tron7
 */
public class FXML_InicioSesionController implements Initializable {
    
    @FXML
    private TextField tfCorreo;
    @FXML
    private PasswordField tpContrasena;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //tfCorreo.setText("Hola");
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        String noPersonal = tfCorreo.getText();
        String password = tpContrasena.getText();
        if(noPersonal.length() > 0 && password.length() > 0) { //.thisEmty();
            verificarCredenciales(noPersonal, password);
        }else{
            Utilidades.mostrarAlertaSimple("Campos requeridos", "El No. de personal y/o contraseña son obligatorias", Alert.AlertType.WARNING);
        }
    }
    
    private void verificarCredenciales(String noPersonal, String password){
        RSAutenticacionAdmin respuesta = InicioSesionImp.verificarCredenciales(noPersonal, password);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Credenicales verificar", "Bienvenido(a) profesor(a)"+respuesta.profesor.getNombre()+"Al sistema escolar.", Alert.AlertType.INFORMATION);
            irPantallaInicio(respuesta.getProfesor());
        }else{
            Utilidades.mostrarAlertaSimple("Eror", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void irPantallaInicio(Profesor profesor) {
        try {
            //crear la scena
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLPrincipal.fxml"));
            
            Parent vista = cargador.load();
            FXMLPrincipalController controlador = cargador.getController();
            controlador.cargarInformacion(profesor);
            Scene escenaPrincipal = new Scene(vista);
            //Obtener escenario actual
            Stage stPrincipal = (Stage) tfCorreo.getScene().getWindow();
            //Cambio de escena
            stPrincipal.setScene(escenaPrincipal);
            stPrincipal.setTitle("Principal");
            stPrincipal.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
