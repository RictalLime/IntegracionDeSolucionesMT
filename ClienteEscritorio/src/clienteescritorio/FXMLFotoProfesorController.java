/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritorio;

import clienteescritorio.utilidad.Utilidades;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Tron7
 */
public class FXMLFotoProfesorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView ivFotoProfesor;
    private File foto;
    private int idProfesor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarValores(int idProfesor){
        this.idProfesor = idProfesor;
    }
    
    @FXML
    public void clicSeleccionarFoto(ActionEvent event){
        mostrarDialogoSeleccion();
    }
    
    @FXML
    public void clicGuardar(ActionEvent event){
        
    }
    
    private void mostrarDialogoSeleccion(){
        FileChooser dialogo = new FileChooser();
        dialogo.setTitle("Selecciona una foto");
        FileChooser.ExtensionFilter filtroImg = new FileChooser.ExtensionFilter("Archivos de imagen (.jpg .png)", "*.jpg", "*.png");
        dialogo.getExtensionFilters().add(filtroImg);
        foto = dialogo.showOpenDialog(ivFotoProfesor.getScene().getWindow());
        if(foto != null){
            mostrarFoto(foto);
        }
    }
    
    private void mostrarFoto(File file){
        try {
            BufferedImage bufferImg = ImageIO.read(file);
            Image imagen = SwingFXUtils.toFXImage(bufferImg, null);
            ivFotoProfesor.setImage(imagen);
        }catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "La Foto no pudo ser cargada", Alert.AlertType.NONE);
        }
    }
}
