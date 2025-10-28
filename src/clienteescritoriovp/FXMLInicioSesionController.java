package clienteescritoriovp;

import clienteescritoriovp.dominio.InicioSesionImp;
import clienteescritoriovp.dto.RespuestaInicioSesion;
import clienteescritoriovp.pojo.Profesor;
import clienteescritoriovp.utilidad.Utilidades;
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
    @FXML
    private Button btnIngresar;
    @FXML
    private Label lbErrorNoPersonal;
    @FXML
    private Label lbErrorContrasenia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        String noPersonal = tfNumeroPersonal.getText();
        String password = pfContrasenia.getText();
        if( sonCamposValidos(noPersonal, password) ){
            validarCredenciales(noPersonal, password);
        }
    }

    private boolean sonCamposValidos(String noPersonal, String password){
        boolean valido = true;
        lbErrorNoPersonal.setText("");
        lbErrorContrasenia.setText("");
        if(noPersonal.isEmpty()){
            valido = false;
            lbErrorNoPersonal.setText("No. de personal requerido.");
        }
        if(password.isEmpty()){
            valido = false;
            lbErrorContrasenia.setText("Contraseña requerida.");
        }
        return valido;
    }
    
    private void validarCredenciales(String noPersonal, String password){
        RespuestaInicioSesion respuesta = InicioSesionImp.verificarCredenciales(noPersonal, password);
        if (! respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Credenciales correctas", 
                "Bienvenido(a) profesor(a) "+respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            irPantallaPrincipal(respuesta.getProfesor());
        }else{
            Utilidades.mostrarAlertaSimple("Error en la validacion", respuesta.getMensaje(), Alert.AlertType.ERROR);
            
        }
    }
    
    
    private void irPantallaPrincipal(Profesor profesor) {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLPrincipal.fxml"));
            Parent vista = cargador.load(); 
            FXMLPrincipalController controlador = cargador.getController();
            controlador.inicializarDatos(profesor);
            Scene scPrincipal = new Scene(vista);
            
            Stage primaryStage = (Stage) tfNumeroPersonal.getScene().getWindow();
            primaryStage.setScene(scPrincipal);
            primaryStage.setTitle("Principal");
            primaryStage.show();

        /*} catch (IOException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, "Error al cargar FXMLPrincipal", ex);
             Alert alertaError = new Alert(Alert.AlertType.ERROR);
             alertaError.setTitle("Error de Carga");
             alertaError.setHeaderText(null);
             alertaError.setContentText("No se pudo cargar la pantalla principal.");
             alertaError.showAndWait(); */
        } catch (Exception e) {
             Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, "Error inesperado en irPantallaPrincipal", e);
        }
    }
}