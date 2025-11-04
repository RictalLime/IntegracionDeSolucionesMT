/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritorio;

import clienteescritorio.dominio.ProfesorImp;
import clienteescritorio.interfaz.INotificador;
import clienteescritorio.pojo.Profesor;
import clienteescritorio.utilidad.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tron7
 */
public class FXMLAdministracionDeProfesoresController implements Initializable, INotificador {
    
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
    private TableColumn colFechaNacimiento;
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
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
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
            //tvProfesores.refresh();
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get("Mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void clicNuevo(ActionEvent event) {
        irFormulario(null);
    }

    @FXML
    private void clicEditar(ActionEvent event) {
        Profesor profesor = tvProfesores.getSelectionModel().getSelectedItem();
        if(profesor != null){
            irFormulario(profesor);
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un Profesor", "Para editar la informacion de un profesor, debes seleccionarlo primero", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
        
    }
    
    private void irFormulario(Profesor profesor){
        
        try {
            FXMLLoader cargar = new FXMLLoader(getClass().getResource("FXMLFormularioProfesor.fxml"));
            Parent vista = cargar.load();
            FXMLFormularioProfesorController controlador = cargar.getController();
            controlador.inicializarDatos(profesor, this);
            Scene escena = new Scene(vista);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Formulario de Profesores");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAdministracionDeProfesoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void notificarOperacionExitosa(String operacion, String nombre) {
        System.out.print("Operacion: "+operacion+", nombre profesor: "+nombre);
        cargarInformacionProfesores();
    }
}
