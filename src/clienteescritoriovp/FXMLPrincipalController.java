package clienteescritoriovp;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLPrincipalController implements Initializable {

    @FXML
    private Label lbNombreCompleto;
    @FXML
    private Label lbNoPersonal;
    @FXML
    private Label lbRol;

    private Profesor profesorLogueado;
    @FXML
    private Button btnCerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    public void inicializarDatos(Profesor profesor) {
            this.profesorLogueado = profesor;
            if (profesor != null) {
                lbNombreCompleto.setText(profesor.getNombre() + " " + profesor.getApellidoPaterno() + " " + profesor.getApellidoMaterno());
                lbNoPersonal.setText(profesor.getNoPersonal());
                lbRol.setText(profesor.getRol());
            } else {
                lbNombreCompleto.setText("Error al cargar datos");
                lbNoPersonal.setText("-");
                lbRol.setText("-");
            }

        }
    /*public void inicializarDatos(Profesor profesor){
        this.profesorLogueado = profesor;
        lbRol.setText("Rol: "+profesor.getRol());
        lbNombreCompleto.setText(profesor.getNombre()+" "+profesor.getApellidoPaterno()+" "+profesor.getApellidoMaterno());
        lbNoPersonal.setText("No. de personal "+profesor.getNoPersonal());
    }*/

    @FXML 
        private void clicCerrarSesion(ActionEvent event) {
        try {
            Parent vistaLogin = FXMLLoader.load(getClass().getResource("FXMLInicioSesion.fxml"));
            Scene escenaInicioSesion = new Scene(vistaLogin);
            
            Stage stPrimaryStage = (Stage) ( (Button) event.getSource() ).getScene().getWindow();
            
            stPrimaryStage.setScene(escenaInicioSesion);
            stPrimaryStage.show();
                        
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    @FXML
    private void clicIrAdminProfesores(ActionEvent event) {
    }
}