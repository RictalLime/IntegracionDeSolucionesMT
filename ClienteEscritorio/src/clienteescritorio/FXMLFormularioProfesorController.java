/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritorio;

import clienteescritorio.dominio.CatalogoImp;
import clienteescritorio.dominio.ProfesorImp;
import clienteescritorio.dto.Respuesta;
import clienteescritorio.pojo.Profesor;
import clienteescritorio.pojo.Rol;
import clienteescritorio.utilidad.Constantes;
import clienteescritorio.utilidad.Utilidades;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tron7
 */
public class FXMLFormularioProfesorController implements Initializable {
    
    @FXML
    private TextField tfNumPersonal;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApPaterno;
    @FXML
    private TextField tfApMaterno;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private DatePicker dpFechaContratacion;
    @FXML
    private PasswordField pfContrasena;
    @FXML
    private ComboBox<Rol> cbRol;
    
    private ObservableList<Rol> roles;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRolesProfesor();
    }

    private void cargarRolesProfesor(){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerRolesSistema();
        if(!(boolean)respuesta.get(Constantes.KEY_ERROR)){
            List<Rol> rolesAPI = (List<Rol>) respuesta.get(Constantes.KEY_LISTA);
            roles = FXCollections.observableArrayList();
            roles.addAll(rolesAPI);
            cbRol.setItems(roles);
        }else{
            Utilidades.mostrarAlertaSimple("Error", respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            cerrarVentana();
        }
    }
    
    private void cerrarVentana(){
        ((Stage) tfNombre.getScene().getWindow()).close();
    }
    
    private boolean sonCamposValidos(){
        
        return true;
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    @FXML
    private void clicGuardar(ActionEvent event) {
        if(sonCamposValidos()){
            Profesor profesor = new Profesor();
            profesor.setNombre(tfNombre.getText());
            profesor.setApellidoPaterno(tfApPaterno.getText());
            profesor.setApellidoMaterno(tfApMaterno.getText());
            profesor.setNoPersonal(tfNumPersonal.getText());
            profesor.setPassword(pfContrasena.getText());
            profesor.setFechaNacimiento(dpFechaNacimiento.getValue().toString());
            profesor.setFechaContratacion(dpFechaContratacion.getValue().toString());
            Rol rolSeleccionado = cbRol.getSelectionModel().getSelectedItem();
            profesor.setIdRol(rolSeleccionado.getIdRol());
            registrarProfesor(profesor);
        }
    }
    
    private void registrarProfesor(Profesor profesor){
        Respuesta respuesta = ProfesorImp.registrar(profesor);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Profesor registrado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();

        }else {
            Utilidades.mostrarAlertaSimple("Error al registrar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
}
