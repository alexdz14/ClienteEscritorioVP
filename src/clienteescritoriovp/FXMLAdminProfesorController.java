package clienteescritoriovp;

import clienteescritoriovp.dominio.ProfesorImp;
import clienteescritoriovp.dto.Respuesta;
import clienteescritoriovp.interfaz.INotificador;
import clienteescritoriovp.pojo.Profesor;
import clienteescritoriovp.utilidad.Utilidades;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stivm
 */
public class FXMLAdminProfesorController implements Initializable, INotificador {

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
    private ObservableList<Profesor> profesores;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionProfesores();
    }    

    @FXML
    private void clicIrModificar(ActionEvent event) {
        Profesor profesor = tvProfesores.getSelectionModel().getSelectedItem();
        if(profesor != null){
            irFormulario(profesor);
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un profesor", 
                    "Para editar la información de un profesor, debes seleccionarlo en la tabla", 
                    Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicIrRegistrar(ActionEvent event) {
        irFormulario(null);
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
        Profesor profesor = tvProfesores.getSelectionModel().getSelectedItem();
        if(profesor != null){
            boolean confirmacion = Utilidades.mostrarAlertaConfirmacion("Eliminar profesor",
                    "¿Está seguro de eliminar la informacion del profesor(a)"+profesor.getNombre()+"?"
            +"\nAl eliminar la informacion ya no puede ser recuperada.");
            if(confirmacion){
                eliminarProfesor(profesor);
            }
            
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un profesor", "Para eliminar la informacion de un profesor, debes deleccionarlo de la tabla", Alert.AlertType.WARNING);
        }
    }
    
    private void eliminarProfesor(Profesor profesor){
        Respuesta respuesta = ProfesorImp.eliminar(profesor.getIdProfesor());
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Informacion eliminada", "La informacion del profesor(a) "+profesor.getNombre()+" ha sido eliminado correctamente", Alert.AlertType.INFORMATION);
            cargarInformacionProfesores();
        }else{
            Utilidades.mostrarAlertaSimple("Error al eliminar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }   
    }
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colNoPersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colFechaContratacion.setCellValueFactory(new PropertyValueFactory("fechaContratacion"));
        colRol.setCellValueFactory(new PropertyValueFactory("rol"));
    }
    
    private void cargarInformacionProfesores() {
        HashMap<String, Object> respuesta = ProfesorImp.obtenerTodos();
        boolean esError = (boolean) respuesta.get("error");
        if (!esError) {
            List<Profesor> profesoresAPI = (List<Profesor>) respuesta.get("profesores");
            profesores = FXCollections.observableArrayList();
            profesores.addAll(profesoresAPI);
            tvProfesores.setItems(profesores);
        } else {
            Utilidades.mostrarAlertaSimple("Error al cargar",
                    "" + respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    private void irFormulario(Profesor profesor){
        try{
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioProfesor.fxml"));
            Parent vista = cargador.load();
            FXMLFormularioProfesorController controlador = cargador.getController();
            controlador.inicializarDatos(profesor, this);
            Scene escena = new Scene(vista);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Formulario profesor");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void notificarOperacionExitosa(String operacion, String nombre) {
        System.err.println("Operacion: "+operacion+", nombre: "+nombre);
        cargarInformacionProfesores();
    }

    @FXML
    private void clicIrSeleccionFoto(ActionEvent event) {
         Profesor profesor = tvProfesores.getSelectionModel().getSelectedItem();
        if (profesor != null) {
            irSeleccionarFoto(profesor);
        } else {
            Utilidades.mostrarAlertaSimple("Selecciona un profesor",
                    "Para seleccionar la foto de un profesor(a), debes seleccionarlo de la tabla",
                    Alert.AlertType.WARNING);
        }
    }
    
    private void irSeleccionarFoto(Profesor profesor){
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLSeleccionFotografia.fxml"));
            Parent vista = cargador.load();
            FXMLSeleccionFotografiaController controlador = cargador.getController();
            controlador.inicializarDatos(profesor.getIdProfesor());

            Scene escena = new Scene(vista);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Selección foto profesor");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}