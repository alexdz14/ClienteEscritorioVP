/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritoriovp;

import clienteescritoriovp.pojo.Profesor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author stivm
 */
public class FXMLAdminProfesorController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<Profesor> tvProfesores;
    @FXML
    private TableColumn colNoPersonal;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApPaterno;
    @FXML
    private TableColumn colApMaterno;
    @FXML
    private TableColumn colFechaContratacion;
    @FXML
    private TableColumn colRol;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
    }    

    @FXML
    private void clicIrModificar(ActionEvent event) {
    }

    @FXML
    private void clicIrRegistrar(ActionEvent event) {
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
    }
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colNoPersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colFechaContratacion.setCellValueFactory(new PropertyValueFactory("fechaContratacion"));
        colRol.setCellValueFactory(new PropertyValueFactory("idRol"));
    }
    
}