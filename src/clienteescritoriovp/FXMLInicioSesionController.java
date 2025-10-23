package clienteescritoriovp;

import clienteescritoriovp.dominio.InicioSesionImp;
import clienteescritoriovp.dto.RespuestaInicioSesion;
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
   
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private PasswordField pfContrasenia;  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        String noPersonal = tfNumeroPersonal.getText();
        String password = pfContrasenia.getText();
        
        if(!noPersonal.isEmpty() && !password.isEmpty()){
            //irPantallaPrincipal();
            RespuestaInicioSesion respuesta = InicioSesionImp.verificarCredenciales(noPersonal, password);
            if(!respuesta.isError()){
                Alert alertaSimple = new Alert(Alert.AlertType.WARNING);
                alertaSimple.setTitle("Credenciales correctas");
                alertaSimple.setHeaderText(null);
                alertaSimple.setContentText("Bienvenido(a) profesor(a) "+respuesta.getProfesor().getNombre()
                        +" "+respuesta.getProfesor().getApellidoPaterno());
                alertaSimple.showAndWait();
            }else {
                Alert alertaSimple = new Alert(Alert.AlertType.WARNING);
                alertaSimple.setTitle("Error");
                alertaSimple.setHeaderText(null);
                alertaSimple.setContentText(respuesta.getMensaje());
                alertaSimple.showAndWait();
            }
        }else{
            Alert alertaSimple = new Alert(Alert.AlertType.WARNING);
            alertaSimple.setTitle("Campos vacíos");
            alertaSimple.setHeaderText(null);
            alertaSimple.setContentText("Para ingresar debes escribir tu correo electrónico y contraseña");
            alertaSimple.showAndWait();
        }
    }
    
    private void irPantallaPrincipal(){
        try {
            // Creamos el Scene
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene scPrincipal = new Scene(vista);
            // Casteamos porque necesita un Stage
            Stage primaryStage = (Stage) tfNumeroPersonal.getScene().getWindow();
            primaryStage.setScene(scPrincipal);
            primaryStage.setTitle("Principal");
            primaryStage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}