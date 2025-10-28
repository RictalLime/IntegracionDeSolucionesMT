/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritorio;

import clienteescritorio.pojo.Profesor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tron7
 */
public class FXMLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbNumPersonal;
    @FXML
    private Profesor profesorSesion;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbRol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void cargarInformacion(Profesor profesor){
        profesorSesion = profesor;
        lbNombre.setText(profesor.getNombre()+" "+profesor.getApellidoPaterno()+" "+profesor.getApellidoMaterno());
        lbNumPersonal.setText("No. de personal "+profesor.getNoPersonal());
        lbRol.setText("Rol: "+profesor.getRol());
    }

    @FXML
    private void clicAdminProfesores(ActionEvent event) {
      
        try {
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLAdministracionDeProfesores.fxml"));
            Scene scAdminUsuarios = new Scene(vista);
            
            Stage stAdmin = new Stage();
            stAdmin.setScene(scAdminUsuarios);
            stAdmin.initModality(Modality.APPLICATION_MODAL);
            stAdmin.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void clickCerarsesion(ActionEvent event) {
        try {
        // Cargar la vista de inicio de sesión
            Parent vistaLogin = FXMLLoader.load(getClass().getResource("FXML_InicioSesion.fxml"));
            Scene escenaLogin = new Scene(vistaLogin);

        // Obtener el escenario actual y reemplazar la escena
            Stage stageActual = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stageActual.setScene(escenaLogin);
            stageActual.setTitle("Inicio de Sesión");
            stageActual.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al cerrar sesión");
            alerta.setHeaderText(null);
            alerta.setContentText("No se pudo regresar al inicio de sesión.");
            alerta.showAndWait();
        }
    }
    
    @FXML
    private void clicFormProfesores(ActionEvent event) {
        try {
            Parent vistaFormularioProfesores = FXMLLoader.load(getClass().getResource("FXMLFormularioProfesor.fxml"));
            Scene escenaFormularioProfesores = new Scene (vistaFormularioProfesores);
            
            Stage stageFormProfesores = new Stage();
            stageFormProfesores.setScene(escenaFormularioProfesores);
            stageFormProfesores.initModality(Modality.APPLICATION_MODAL);
            stageFormProfesores.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
