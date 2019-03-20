/**
 * Descripción: Clase Alumno.
 *              Guarda los datos de los alumnos.
 * Modificación: 2019/03/19
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/02/21
 */
package alumnos.controlador;

import alumnos.Alumnos;
import alumnos.clases.Alumno;
import alumnos.SqlAlumnos;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FXMLAlumnosController implements Initializable{
  @FXML
  private Button buttonAgregar;
  @FXML
  private Button buttonEditar;
  @FXML
  private Button buttonEliminar;
  @FXML
  private Button buttonGuardar;
  @FXML
  private Button buttonLimpiar;
  @FXML
  private Button buttonMaterias;
  @FXML
  private Button buttonSalir;
  @FXML
  private Label labelAlumno;
  @FXML
  private Label labelApellidoMaterno;
  @FXML
  private Label labelApellidoPaterno;
  @FXML
  private Label labelMatricula;
  @FXML
  private Label labelNombre;
  @FXML
  private Label labelNota;
  @FXML
  private TableView<Alumno> tableAlumnos;
  @FXML
  private TableColumn tableColumnApellidoMaterno;
  @FXML
  private TableColumn tableColumnApellidoPaterno;
  @FXML
  private TableColumn tableColumnMatricula;
  @FXML
  private TableColumn tableColumnNombre;
  @FXML
  private TextField textFieldApellidoMaterno;
  @FXML
  private TextField textFieldApellidoPaterno;
  @FXML
  private TextField textFieldMatricula;
  @FXML
  private TextField textFieldNombre;
  ObservableList<Alumno> alumnos;
  private int posicionAlumno;
  SqlAlumnos sqlAlumnos = new SqlAlumnos();
  private Alumnos alumno;
  @FXML
  private Color x1;
  @FXML
  private Font x3;
  @FXML
  private Color x2;
  
  /**
   * Método para inicializar la tabla alumnos para que reciba parámetros String.
   */
  private void inicializarTableAlumnos() {
    tableColumnMatricula.setCellValueFactory(new PropertyValueFactory<Alumno, String>("matricula"));
    tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
    tableColumnApellidoPaterno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellidoPaterno"));
    tableColumnApellidoMaterno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellidoMaterno"));
    alumnos = FXCollections.observableArrayList();
    tableAlumnos.setItems(alumnos);
  }
 
  /**
   * Método para cargar los alumnos de la base de datos en la tabla.
   */
  private void cargarAlumnos() {
    alumnos.clear();
    List<String> listaAlumnos = null;
    try {
      listaAlumnos = sqlAlumnos.consultarAlumnos();
    } catch (SQLException ex) {
      Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
    }
    Iterator<String> it = listaAlumnos.iterator();
    //Guarda cada alumno en un objeto que se agrega a la tabla.
    while (it.hasNext()) { 
      Alumno alumno = new Alumno();
      alumno.matricula.set(it.next());
      alumno.nombre.set(it.next());
      alumno.apellidoPaterno.set(it.next());
      alumno.apellidoMaterno.set(it.next());
      alumnos.add(alumno);
    }
  }
  
  /**
   * Método para agregar alumno a la tabla.
   * @param event - Evento de dar clic en el botón Agregar.
   */
  @FXML
  private void agregarAlumno(ActionEvent event){
    //Valida que se ingresen todos los datos del alumno.
    if (!textFieldMatricula.getText().equals("") && !textFieldNombre.getText().equals("") && 
            !textFieldApellidoPaterno.getText().equals("") && !textFieldApellidoMaterno.getText().equals("")) {
      Alumno alumno = new Alumno();
      alumno.matricula.set(textFieldMatricula.getText());
      alumno.nombre.set(textFieldNombre.getText());
      alumno.apellidoPaterno.set(textFieldApellidoPaterno.getText());
      alumno.apellidoMaterno.set(textFieldApellidoMaterno.getText());
      alumnos.add(alumno);
      limpiarTextFields(new ActionEvent());
    }
  }
  
  /**
   * Método para obtener un alumno de la tabla.
   * @return alumnoSeleccionado - Alumno seleccionado en la tabla.
   */
  public Alumno obtenerAlumnoTable() {
    if (tableAlumnos != null) {
      List<Alumno> tablaAlumno = tableAlumnos.getSelectionModel().getSelectedItems();
      if (tablaAlumno.size() == 1) {
        final Alumno alumnoSeleccionado = tablaAlumno.get(0);
        return alumnoSeleccionado;
      }
    }
    return null;
  }
  
  /**
   * Método para vaciar los datos del alumno seleccionado en los text fields.
   */
  private void vaciarAlumnoTable() {
    final Alumno alumno = obtenerAlumnoTable();
    posicionAlumno = alumnos.indexOf(alumno);
    if (alumno != null) {
      textFieldMatricula.setText(alumno.getMatricula());
      textFieldNombre.setText(alumno.getNombre());
      textFieldApellidoPaterno.setText(alumno.getApellidoPaterno());
      textFieldApellidoMaterno.setText(alumno.getApellidoMaterno());
      buttonEditar.setDisable(false);
      buttonEliminar.setDisable(false);
      buttonAgregar.setDisable(true);
    }
  }
  
  /**
   * Método que escucha lo que se selecciona en la tabla de alumnos.
   */
  private final ListChangeListener<Alumno> seleccionarTableAlumno = new ListChangeListener<Alumno>() {
    @Override
    public void onChanged(ListChangeListener.Change<? extends Alumno> c) {
      vaciarAlumnoTable();
    }
  };

  /**
   * Método para editar un alumno de la tabla.
   * @param event - Evento de dar clic en el botón Editar.
   */
  @FXML
  private void editarAlumno(ActionEvent event) {
    //Valida que se ingresen todos los datos del alumno. 
    if (!textFieldMatricula.getText().equals("") && !textFieldNombre.getText().equals("") && 
            !textFieldApellidoPaterno.getText().equals("") && !textFieldApellidoMaterno.getText().equals("")){
      Alumno alumno = new Alumno();
      alumno.matricula.set(textFieldMatricula.getText());
      alumno.nombre.set(textFieldNombre.getText());
      alumno.apellidoPaterno.set(textFieldApellidoPaterno.getText());
      alumno.apellidoMaterno.set(textFieldApellidoMaterno.getText());
      alumnos.set(posicionAlumno, alumno);
      limpiarTextFields(new ActionEvent());
    }
  }

  /**
   * Método para eliminar un alumno de la tabla.
   * @param event - Evento de dar clic en el botón Eliminar.
   */
  @FXML
  private void eliminarAlumno(ActionEvent event) {
    alumnos.remove(posicionAlumno);
    limpiarTextFields(new ActionEvent());
  }
  
  /**
   * Método para limpiar los text fields.
   * @param event - Evento de dar clic en el botón Limpiar.
   */
  @FXML
  private void limpiarTextFields(ActionEvent event) {
    textFieldMatricula.setText("");
    textFieldNombre.setText("");
    textFieldApellidoPaterno.setText("");
    textFieldApellidoMaterno.setText("");
    buttonEditar.setDisable(true);
    buttonEliminar.setDisable(true);
    buttonAgregar.setDisable(false);
  }
  
  /**
   * Método para guardar los alumnos de la tabla en la base de datos.
   * @param event - Evento de dar clic en el botón Guardar.
   */
  @FXML
  private void guardarAlumnos(ActionEvent event) {
    try {
      //Primero borramos todos los alumnos guardados en la base de datos.
      sqlAlumnos.borrarAlumnos();
    } catch (SQLException ex) {
      Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //Obtiene cada alumno de la tabla y lo guarda en la base de datos.
    for (int i = 0; i < tableAlumnos.getItems().size(); i++) {
      String matricula = (String) tableAlumnos.getItems().get(i).getMatricula();
      String nombre = (String) tableAlumnos.getItems().get(i).getNombre();
      String apellidoPaterno = (String) tableAlumnos.getItems().get(i).getApellidoPaterno();
      String apellidoMaterno = (String) tableAlumnos.getItems().get(i).getApellidoMaterno();
      try {
        sqlAlumnos.agregarAlumnos(matricula, nombre, apellidoPaterno, apellidoMaterno);
      } catch (SQLException ex) {
        Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    limpiarTextFields(new ActionEvent());
  }

  /**
   * Método para abrir la ventana de materias.
   * @param event - Evento de dar clic en el botón Ir a Materias.
   * @throws IOException - Excepción al no encontrar el archivo de materias.
   */
  @FXML
  private void materias(ActionEvent event) throws IOException {
    alumno.materias();
  }
  
  /**
   * Método para cambiar la clase principal del controlador
   * @param alumno - Clase principal.
   */
  public void setAlumnos(Alumnos alumno) {
        this.alumno = alumno;
    }
  
  /**
   * Método para salir del sistema.
   * @param event - Evento de dar clic en el botón Salir.
   */
  @FXML
  private void salir(ActionEvent event) {
    System.exit(0);
  }
  
  /**
   * Método para inicializar la ventana.
   * @param url
   * @param rb 
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    inicializarTableAlumnos();
    cargarAlumnos();
    buttonEditar.setDisable(true);
    buttonEliminar.setDisable(true);
    final ObservableList<Alumno> alumnoSeleccionado = tableAlumnos.getSelectionModel().getSelectedItems();
    alumnoSeleccionado.addListener(seleccionarTableAlumno);
  }
}


