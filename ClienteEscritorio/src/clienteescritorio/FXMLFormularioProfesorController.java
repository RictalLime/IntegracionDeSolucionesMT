/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private ComboBox cbRol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        
    }
    
    @FXML
    private void clicGuardar(ActionEvent event) {
        
    }
}
