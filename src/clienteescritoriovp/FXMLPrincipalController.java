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
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stivm
 */
public class FXMLPrincipalController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void clicIrAdministrador(ActionEvent event) {
        try {
            //Cargar el FXML a memoria a un Parent
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLAdministracionUsuarios.fxml"));
            Scene scAdmin = new Scene(vista);
                    
                
            //Para manejar navegacion vamos a crear un nuevo Stage
            Stage stAdmin = new Stage();
            stAdmin.setScene(scAdmin);
            stAdmin.setTitle("Administración usuarios");
            stAdmin.initModality(Modality.APPLICATION_MODAL);
            stAdmin.showAndWait();
        
            } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    
}
