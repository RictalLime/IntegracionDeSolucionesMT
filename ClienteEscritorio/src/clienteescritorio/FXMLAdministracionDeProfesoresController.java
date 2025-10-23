/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritorio;

import clienteescritorio.dominio.ProfesorImp;
import clienteescritorio.pojo.Profesor;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tron7
 */
public class FXMLAdministracionDeProfesoresController implements Initializable {
    
    @FXML
    private TextField tfBusqueda;
    @FXML
    private TableView<Profesor> tvProfesores;
    @FXML
    private TableColumn colNoPersonal;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApMaterno;
    @FXML
    private TableColumn colApPaterno;
    @FXML
    private TableColumn colFechaContratacion;
    @FXML
    private TableColumn colRol;
    
    private ObservableList<Profesor> profesores;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInformacionProfesores();
    }    
    
    private void configurarTabla(){
        colNoPersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colFechaContratacion.setCellValueFactory(new PropertyValueFactory("fechaContratacion"));
        colRol.setCellValueFactory(new PropertyValueFactory("rol"));
    }
    
    private void cargarInformacionProfesores(){
        HashMap<String, Object> respuesta = ProfesorImp.obtenerTodos();
        boolean esError = (boolean) respuesta.get("error");
        if(!esError){
            List<Profesor> ProfesoresAPI = (List<Profesor>) respuesta.get("profesores");
            profesores = FXCollections.observableArrayList();
            profesores.addAll(ProfesoresAPI);
            tvProfesores.setItems(profesores);
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get("Mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void clicNuevo(ActionEvent event) {
        
    }

    @FXML
    private void clicEditar(ActionEvent event) {
        
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
        
    }
}
