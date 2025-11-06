/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package clienteescritoriovp;

import clienteescritoriovp.dominio.CatalogoImp;
import clienteescritoriovp.dominio.ProfesorImp;
import clienteescritoriovp.dto.Respuesta;
import clienteescritoriovp.interfaz.INotificador;
import clienteescritoriovp.pojo.Profesor;
import clienteescritoriovp.pojo.Rol;
import clienteescritoriovp.utilidad.Constantes;
import clienteescritoriovp.utilidad.Utilidades;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stivm
 */
public class FXMLFormularioProfesorController implements Initializable {

    @FXML
    private TextField tfNoPersonal;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApPaterno;
    @FXML
    private TextField tfApMaterno;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private DatePicker dpFechaContratacion;
    @FXML
    private TextField tfPassword;
    @FXML
    private ComboBox<Rol> cbRol;
    private ObservableList<Rol> roles;
    private Profesor profesorEdicion;
    private INotificador observador;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRoles();
    }    

    public void inicializarDatos(Profesor profesorEdicion, INotificador observador){
        this.profesorEdicion = profesorEdicion;
        this.observador = observador;
        if(profesorEdicion != null){
            tfNoPersonal.setText(profesorEdicion.getNoPersonal());
            tfNombre.setText(profesorEdicion.getNombre());
            tfApPaterno.setText(profesorEdicion.getApellidoPaterno());
            tfApMaterno.setText(profesorEdicion.getApellidoMaterno());
            tfPassword.setVisible(false);
            tfNoPersonal.setDisable(true);
            dpFechaNacimiento.setValue(LocalDate.parse(profesorEdicion.getFechaNacimiento()));
            dpFechaContratacion.setValue(LocalDate.parse(profesorEdicion.getFechaContratacion()));
            int pos = obtenerPosicionRol(profesorEdicion.getIdRol());
            cbRol.getSelectionModel().select(pos);
        }
    }
    
    @FXML
    private void clicBtnGuardar(ActionEvent event) {
        if(sonCamposValidos()){
            Profesor profesor = new Profesor();
            profesor.setNoPersonal(tfNoPersonal.getText());
            profesor.setNombre(tfNombre.getText());
            profesor.setApellidoPaterno(tfApPaterno.getText());
            profesor.setApellidoMaterno(tfApMaterno.getText());
            profesor.setPassword(tfPassword.getText());
            profesor.setFechaNacimiento(dpFechaNacimiento.getValue().toString());
            profesor.setFechaContratacion(dpFechaContratacion.getValue().toString());
            Rol rolseleccion = cbRol.getSelectionModel().getSelectedItem();
            profesor.setIdRol(rolseleccion.getIdRol());
            registrarProfesor(profesor);
        }
    }

    @FXML
    private void clicBtnCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void cargarRoles(){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerRolesSistema();
        if( !(boolean)respuesta.get(Constantes.KEY_ERROR)){
            List<Rol> listaRoles = (List<Rol>) respuesta.get(Constantes.KEY_LISTA);
            roles = FXCollections.observableArrayList();
            roles.addAll(listaRoles);
            cbRol.setItems(roles);
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar",  respuesta.get(Constantes.KEY_MENSAJE).toString(),
                    Alert.AlertType.ERROR);
            cerrarVentana();
        }
    }
    
    private void cerrarVentana(){
        //Stage escenario = (Stage) tfNombre.getScene().getWindow();
        //escenario.close();
        ((Stage) tfNombre.getScene().getWindow()).close();
    }
    
    //TAREA ES TEMPORAL
    private boolean sonCamposValidos(){
        //TODO VALIDAR TODOS LOS CAMPOS
        return true;
    }
    
    private void registrarProfesor(Profesor profesor){
        Respuesta respuesta = ProfesorImp.registrar(profesor);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Profesor(a) registrado(a)", 
                    "La información del profesor "+profesor.getNombre()+" ha sido guardada correctamente", 
                    Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Registro", profesor.getNombre());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", respuesta.getMensaje(), Alert.AlertType.NONE);
        }
    }
    
    private int obtenerPosicionRol(int idRol){
        for (int i = 0; i < roles.size(); i++){
        if(roles.get(i).getIdRol() == idRol)
            return i;
    }
        return -1;
    }
}
