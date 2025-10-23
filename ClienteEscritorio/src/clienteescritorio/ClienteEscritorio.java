/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package clienteescritorio;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Tron7
 */
public class ClienteEscritorio extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent vista = 
                    FXMLLoader.load(getClass().getResource("FXML_InicioSesion.fxml"));
            Scene escenaLogin = new Scene(vista);
            primaryStage.setScene(escenaLogin);
            primaryStage.setTitle("Authentificación");
            primaryStage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
