package clienteescritoriovp;

import clienteescritoriovp.dominio.InicioSesionImp;
import clienteescritoriovp.dto.RespuestaInicioSesion;
import clienteescritoriovp.pojo.Profesor;
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

        if (noPersonal != null && password != null && !noPersonal.isEmpty() && !password.isEmpty()) {
            RespuestaInicioSesion respuesta = InicioSesionImp.verificarCredenciales(noPersonal, password);
            if (!respuesta.isError() && respuesta.getProfesor() != null) {
                Alert alertaSimple = new Alert(Alert.AlertType.INFORMATION);
                alertaSimple.setTitle("Credenciales correctas");
                alertaSimple.setHeaderText(null);
                alertaSimple.setContentText("Bienvenido(a) profesor(a) " + respuesta.getProfesor().getNombre()
                        + " " + respuesta.getProfesor().getApellidoPaterno());
                alertaSimple.showAndWait();

                Profesor profesorLogueado = respuesta.getProfesor();
                irPantallaPrincipal(profesorLogueado);

            } else {
                Alert alertaSimple = new Alert(Alert.AlertType.WARNING);
                alertaSimple.setTitle("Error");
                alertaSimple.setHeaderText(null);
                alertaSimple.setContentText(respuesta.getMensaje() != null ? respuesta.getMensaje() : "Error desconocido al iniciar sesión."); // Manejar mensaje nulo
                alertaSimple.showAndWait();
            }
        } else {
            Alert alertaSimple = new Alert(Alert.AlertType.WARNING);
            alertaSimple.setTitle("Campos vacíos");
            alertaSimple.setHeaderText(null);
            alertaSimple.setContentText("Para ingresar debes escribir tu número de personal y contraseña"); // Mensaje corregido
            alertaSimple.showAndWait();
        }
    }

    private void irPantallaPrincipal(Profesor profesor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPrincipal.fxml"));
            Parent vista = loader.load(); 

            FXMLPrincipalController principalController = loader.getController();

            principalController.inicializarDatos(profesor);

            Scene scPrincipal = new Scene(vista);
            Stage primaryStage = (Stage) tfNumeroPersonal.getScene().getWindow();
            primaryStage.setScene(scPrincipal);
            primaryStage.setTitle("Principal");
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, "Error al cargar FXMLPrincipal", ex);
             Alert alertaError = new Alert(Alert.AlertType.ERROR);
             alertaError.setTitle("Error de Carga");
             alertaError.setHeaderText(null);
             alertaError.setContentText("No se pudo cargar la pantalla principal.");
             alertaError.showAndWait();
        } catch (Exception e) {
             Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, "Error inesperado en irPantallaPrincipal", e);
        }
    }
}