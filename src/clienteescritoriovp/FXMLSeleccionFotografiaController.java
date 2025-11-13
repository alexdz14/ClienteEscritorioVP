/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritoriovp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author stivm
 */
public class FXMLSeleccionFotografiaController implements Initializable {

    @FXML
    private ImageView ivFoto;
    private int idProfesor;
    private File fotoProfesor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inicializarDatos(int idProfesor){
        this.idProfesor = idProfesor;
    }
    
    @FXML
    private void clicSeleccionar(ActionEvent event) {
        FileChooser dialogoFoto = new FileChooser();
        dialogoFoto.setTitle("Selecciona una foto de perfil");
        FileChooser.ExtensionFilter filtroImg = 
                new FileChooser.ExtensionFilter("Archivos de imagen (.jpg,.png)", "*.jpg", "*.png");
        dialogoFoto.getExtensionFilters().add(filtroImg);
        fotoProfesor = dialogoFoto.showOpenDialog(ivFoto.getScene().getWindow());
        if(fotoProfesor != null){
            mostrarFoto(fotoProfesor);
        }
    }

    @FXML
    private void clicSubir(ActionEvent event) {
    }
    
    private void mostrarFoto(File file){
        try{
            BufferedImage bufferImg = ImageIO.read(file);
            Image imagen = SwingFXUtils.toFXImage(bufferImg, null);
            ivFoto.setImage(imagen);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
