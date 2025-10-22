/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritoriovp;

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
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stivm
 */
public class FXMLInicioSesionController implements Initializable {

    private TextField tfCorreo;
    private PasswordField pfContrasenia;
    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private PasswordField pfContrasena;
    @FXML
    private Button btnIngresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    private void clickIniciarSesion(ActionEvent event) {
        String correo = tfCorreo.getText();
        String password = pfContrasenia.getText();
        
        if(!correo.isEmpty() && !password.isEmpty()){
            irPantallaPrincipal();
        }else{
            Alert alertaSimple = new Alert(Alert.AlertType.WARNING);
            alertaSimple.setTitle("Campos vacíos");
            alertaSimple.setHeaderText(null);
            alertaSimple.setContentText("Para ingresar debes escribir un correo electronico y contraseña");
            alertaSimple.showAndWait();
        }
    }
    
    private void irPantallaPrincipal(){
        try {
            // Creamos la escena
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene scPrincipal = new Scene(vista);
            // Obtenemos el Stage actual
            Stage primaryStage = (Stage) tfCorreo.getScene().getWindow();
            primaryStage.setScene(scPrincipal);
            primaryStage.setTitle("Principal");
            primaryStage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clickIngresar(ActionEvent event) {
    }
}

