/**
 * Descripción: Clase Materia.
 *              Guarda los datos de las materias.
 * Modificación: 2019/03/19
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/17
 */
package alumnos.controlador;

import alumnos.Alumnos;
import alumnos.clases.HorarioMateria;
import alumnos.clases.Materia;
import alumnos.SqlHorariosMaterias;
import alumnos.SqlMaterias;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXMLMateriasController implements Initializable {
  @FXML
  private Button buttonAgregar;
  @FXML
  private Button buttonAgregar2;
  @FXML
  private Button buttonAlumnos;
  @FXML
  private Button buttonEditar;
  @FXML
  private Button buttonEditar2;
  @FXML
  private Button buttonEliminar;
  @FXML
  private Button buttonEliminar2;
  @FXML
  private Button buttonGuardar;
  @FXML
  private Button buttonGuardar2;
  @FXML
  private Button buttonLimpiar;
  @FXML
  private Button buttonLimpiar2;
  @FXML
  private Button buttonSalir;
  @FXML
  private Label labelCreditos;
  @FXML
  private Label labelDia;
  @FXML
  private Label labelHoraEntrada;
  @FXML
  private Label labelHoraSalida;
  @FXML
  private Label labelHorasPracticas;
  @FXML
  private Label labelHorasTeoricas;
  @FXML
  private Label labelMateria;
  @FXML
  private Label labelNRC;
  @FXML
  private Label labelNombre;
  @FXML
  private Label labelNota;
  @FXML
  private Label labelNota2;
  @FXML
  private Label labelNota3;
  @FXML
  private Label labelSalon;
  @FXML
  private TableColumn tableColumnCreditos;
  @FXML
  private TableColumn tableColumnDia;
  @FXML
  private TableColumn tableColumnHoraEntrada;
  @FXML
  private TableColumn tableColumnHoraSalida;
  @FXML
  private TableColumn tableColumnHorasPracticas;
  @FXML
  private TableColumn tableColumnHorasTeoricas;
  @FXML
  private TableColumn tableColumnNRC;
  @FXML
  private TableColumn tableColumnNombre;
  @FXML
  private TableColumn tableColumnSalon;
  @FXML
  private TableView<HorarioMateria> tableHorariosMaterias;
  @FXML
  private TableView<Materia> tableMaterias;
  @FXML
  private TextField textFieldCreditos;
  @FXML
  private TextField textFieldDia;
  @FXML
  private TextField textFieldHoraEntrada;
  @FXML
  private TextField textFieldHoraSalida;
  @FXML
  private TextField textFieldHorasPracticas;
  @FXML
  private TextField textFieldHorasTeoricas;
  @FXML
  private TextField textFieldNRC;
  @FXML
  private TextField textFieldNombre;
  @FXML
  private TextField textFieldSalon;
  ObservableList<Materia> materias;
  ObservableList<HorarioMateria> horariosMaterias;
  private int posicionMateria;
  private int posicionHorarioMateria;
  private int contador = 1;
  private int contador2 = 1;
  SqlMaterias sqlMaterias = new SqlMaterias();
  SqlHorariosMaterias sqlHorariosMaterias = new SqlHorariosMaterias();
  private Alumnos alumno;
  private Stage stagePrincipal;
  @FXML
  private Color x1;
  @FXML
  private Color x2;
  @FXML
  private Font x3;

  /**
   * Método para inicializar la tabla materias para que reciba parámetros String.
   */
  private void inicializarTableMaterias() {
    tableColumnNombre.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombre"));
    tableColumnNRC.setCellValueFactory(new PropertyValueFactory<Materia, String>("nrc"));
    tableColumnHorasPracticas.setCellValueFactory(new PropertyValueFactory<Materia, String>("horasPracticas"));
    tableColumnHorasTeoricas.setCellValueFactory(new PropertyValueFactory<Materia, String>("horasTeoricas"));
    tableColumnCreditos.setCellValueFactory(new PropertyValueFactory<Materia, String>("creditos"));
    materias = FXCollections.observableArrayList();
    tableMaterias.setItems(materias);
  }

  /**
   * Método para cargar las materias de la base de datos en la tabla.
   */
  private void cargarMaterias() {
    materias.clear();
    List<String> listaMaterias = null;
    try {
      listaMaterias = sqlMaterias.consultarMaterias();
    } catch (SQLException ex) {
      Logger.getLogger(FXMLMateriasController.class.getName()).log(Level.SEVERE, null, ex);
    }
    Iterator<String> it = listaMaterias.iterator();
    //Guarda cada materia en un objeto que se agrega a la tabla.
    while (it.hasNext()) {
      Materia materia = new Materia();
      contador = Integer.parseInt(it.next());
      materia.idmateria.set(Integer.toString(contador));
      materia.nombre.set(it.next());
      materia.nrc.set(it.next());
      materia.horasPracticas.set(it.next());
      materia.horasTeoricas.set(it.next());
      materia.creditos.set(it.next());
      materias.add(materia);
    }
  }

  /**
   * Método para agregar materia a la tabla.
   * @param event - Evento de dar clic en el botón Agregar.
   */
  @FXML
  void agregarMateria(ActionEvent event) {
    //Valida que se ingresen todos los datos de la materia.
    if (!textFieldNombre.getText().equals("") && !textFieldNRC.getText().equals("") && 
            !textFieldHorasPracticas.getText().equals("") && 
            !textFieldHorasTeoricas.getText().equals("") && !textFieldCreditos.getText().equals("")) {
      contador++;
      Materia materia = new Materia();
      materia.idmateria.set(Integer.toString(contador));
      materia.nombre.set(textFieldNombre.getText());
      materia.nrc.set(textFieldNRC.getText());
      materia.horasPracticas.set(textFieldHorasPracticas.getText());
      materia.horasTeoricas.set(textFieldHorasTeoricas.getText());
      materia.creditos.set(textFieldCreditos.getText());
      materias.add(materia);
      limpiarTextFields(new ActionEvent());
    }
  }

  /**
   * Método para obtener una materia de la tabla.
   * @return materiaSeleccionada - Materia seleccionada en la tabla.
   */
  public Materia obtenerMateriaTable() {
    if (tableMaterias != null) {
      List<Materia> tablaMateria = tableMaterias.getSelectionModel().getSelectedItems();
      if (tablaMateria.size() == 1) {
        final Materia materiaSeleccionada = tablaMateria.get(0);
        return materiaSeleccionada;
      }
    }
    return null;
  }
  
  /**
   * Método para vaciar los datos de la materia seleccionada en los text fields.
   */
  private void vaciarMateriaTable() {
    final Materia materia = obtenerMateriaTable();
    posicionMateria = materias.indexOf(materia);
    if (materia != null) {
      textFieldNombre.setText(materia.getNombre());
      textFieldNRC.setText(materia.getNrc());
      textFieldHorasPracticas.setText(materia.getHorasPracticas());
      textFieldHorasTeoricas.setText(materia.getHorasTeoricas());
      textFieldCreditos.setText(materia.getCreditos());
      buttonEditar.setDisable(false);
      buttonEliminar.setDisable(false);
      buttonAgregar.setDisable(true);
      buttonAgregar2.setDisable(false);
      buttonGuardar2.setDisable(false);
      cargarHorariosMaterias(Integer.parseInt(materia.getIdmateria()));
    }
  }
  
  /**
   * Método que escucha lo que se selecciona en la tabla de materias.
   */
  private final ListChangeListener<Materia> seleccionarTableMateria = new ListChangeListener<Materia>() {
    @Override
    public void onChanged(ListChangeListener.Change<? extends Materia> c) {
      vaciarMateriaTable();
    }
  };
  
  /**
   * Método para editar una materia de la tabla.
   * @param event - Evento de dar clic en el botón Editar.
   */
  @FXML
  void editarMateria(ActionEvent event) {
    //Valida que se ingresen todos los datos de la materia.
    if (!textFieldNombre.getText().equals("") && !textFieldNRC.getText().equals("") && 
            !textFieldHorasPracticas.getText().equals("") && 
            !textFieldHorasTeoricas.getText().equals("") && !textFieldCreditos.getText().equals("")) {
      Materia materia = new Materia();
      materia.nombre.set(textFieldNombre.getText());
      materia.nrc.set(textFieldNRC.getText());
      materia.horasPracticas.set(textFieldHorasPracticas.getText());
      materia.horasTeoricas.set(textFieldHorasTeoricas.getText());
      materia.creditos.set(textFieldCreditos.getText());
      materias.set(posicionMateria, materia);
      limpiarTextFields(new ActionEvent());
    }
  }

  /**
   * Método para eliminar una materia de la tabla.
   * @param event - Evento de dar clic en el botón Eliminar.
   */
  @FXML
  private void eliminarMateria(ActionEvent event) {
    materias.remove(posicionMateria);
    limpiarTextFields(new ActionEvent());
  }

  /**
   * Método para limpiar los text fields de materias.
   * @param event - Evento de dar clic en el botón Limpiar.
   */
  @FXML
  private void limpiarTextFields(ActionEvent event) {
    textFieldNombre.setText("");
    textFieldNRC.setText("");
    textFieldHorasPracticas.setText("");
    textFieldHorasTeoricas.setText("");
    textFieldCreditos.setText("");
    buttonEditar.setDisable(true);
    buttonEliminar.setDisable(true);
    buttonAgregar.setDisable(false);
    horariosMaterias.clear();
    buttonAgregar2.setDisable(true);
    buttonEditar2.setDisable(true);
    buttonEliminar2.setDisable(true);
    buttonGuardar2.setDisable(true);
  }
  
  /**
   * Método para guardar las materias de la tabla en la base de datos.
   * @param event - Evento de dar clic en el botón Guardar.
   */
  @FXML
  private void guardarMaterias(ActionEvent event) {
    try {
      //Primero borramos todas las materias guardadas en la base de datos.
      sqlMaterias.borrarMaterias();
    } catch (SQLException ex) {
      Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //Obtiene cada materia de la tabla y lo guarda en la base de datos.
    for (int i = 0; i < tableMaterias.getItems().size(); i++) {
      int idmateria = Integer.parseInt(tableMaterias.getItems().get(i).getIdmateria());
      String nombre = (String) tableMaterias.getItems().get(i).getNombre();
      String nrc = (String) tableMaterias.getItems().get(i).getNrc();
      int horasPracticas = Integer.parseInt(tableMaterias.getItems().get(i).getHorasPracticas());
      int horasTeoricas = Integer.parseInt(tableMaterias.getItems().get(i).getHorasTeoricas());
      int creditos = Integer.parseInt(tableMaterias.getItems().get(i).getCreditos());
      try {
        sqlMaterias.agregarMaterias(idmateria, nombre, nrc, horasPracticas, horasTeoricas, creditos);
      } catch (SQLException ex) {
        Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    limpiarTextFields(new ActionEvent());
  }
  
  /**
   * Método para inicializar la tabla horarios de materias para que reciba parámetros String.
   */
  private void inicializarTableHorariosMaterias() {
    tableColumnDia.setCellValueFactory(new PropertyValueFactory<HorarioMateria, String>("dia"));
    tableColumnSalon.setCellValueFactory(new PropertyValueFactory<HorarioMateria, String>("salon"));
    tableColumnHoraEntrada.setCellValueFactory(new PropertyValueFactory<HorarioMateria, String>("horaEntrada"));
    tableColumnHoraSalida.setCellValueFactory(new PropertyValueFactory<HorarioMateria, String>("horaSalida"));
    horariosMaterias = FXCollections.observableArrayList();
    tableHorariosMaterias.setItems(horariosMaterias);
  }
    
  /**
   * Método para cargar los horarios de las materias de la base de datos en la tabla.
   */
  private void cargarHorariosMaterias(int idmateria) {
    horariosMaterias.clear();
    List<String> listaHorariosMaterias = null;
    try {
      listaHorariosMaterias = sqlHorariosMaterias.consultarHorariosMaterias(idmateria);
      
    } catch (SQLException ex) {
      Logger.getLogger(FXMLMateriasController.class.getName()).log(Level.SEVERE, null, ex);
    }
    Iterator<String> it = listaHorariosMaterias.iterator();
    //Guarda cada horario de la materia en un objeto que se agrega a la tabla.
    while (it.hasNext()) {
      HorarioMateria horarioMateria = new HorarioMateria();
      contador2 = Integer.parseInt(it.next());
      horarioMateria.idhorarioMateria.set(Integer.toString(contador2));
      horarioMateria.idmateria.set(it.next());
      horarioMateria.dia.set(it.next());
      horarioMateria.salon.set(it.next());
      horarioMateria.horaEntrada.set(it.next());
      horarioMateria.horaSalida.set(it.next());
      horariosMaterias.add(horarioMateria);
    }
  }
  
  /**
   * Método para agregar horario a la tabla.
   * @param event - Evento de dar clic en el botón Agregar.
   */
  @FXML
  private void agregarHorarioMateria(ActionEvent event) {
    //Valida que se ingresen todos los datos del horario.
    final Materia materia = obtenerMateriaTable();
    if (!textFieldDia.getText().equals("") && !textFieldSalon.getText().equals("") && 
            !textFieldHoraEntrada.getText().equals("") && !textFieldHoraSalida.getText().equals("")){
      contador2++;
      HorarioMateria horarioMateria = new HorarioMateria();
      horarioMateria.idhorarioMateria.set(Integer.toString(contador2));
      horarioMateria.idmateria.set(materia.getIdmateria());
      horarioMateria.dia.set(textFieldDia.getText());
      horarioMateria.salon.set(textFieldSalon.getText());
      horarioMateria.horaEntrada.set(textFieldHoraEntrada.getText());
      horarioMateria.horaSalida.set(textFieldHoraSalida.getText());
      horariosMaterias.add(horarioMateria);
      limpiarTextFields2(new ActionEvent());
    }
  }
  
  /**
   * Método para obtener una horario de la tabla.
   * @return horarioMateriaSeleccionado - Horario de la materia seleccionado en la tabla.
   */
  public HorarioMateria obtenerHorarioMateriaTable() {
    if (tableHorariosMaterias != null) {
      List<HorarioMateria> tablaHorarioMateria = tableHorariosMaterias.getSelectionModel().getSelectedItems();
      if (tablaHorarioMateria.size() == 1) {
        final HorarioMateria horarioMateriaSeleccionado = tablaHorarioMateria.get(0);
        return horarioMateriaSeleccionado;
      }
    }
    return null;
  }
  
  /**
   * Método para vaciar los datos del horario de la materia seleccionado en los text fields.
   */
  private void vaciarHorarioMateriaTable() {
    final HorarioMateria horarioMateria = obtenerHorarioMateriaTable();
    posicionHorarioMateria = horariosMaterias.indexOf(horarioMateria);
    if (horarioMateria != null) {
      textFieldDia.setText(horarioMateria.getDia());
      textFieldSalon.setText(horarioMateria.getSalon());
      textFieldHoraEntrada.setText(horarioMateria.getHoraEntrada());
      textFieldHoraSalida.setText(horarioMateria.getHoraSalida());
      buttonEditar2.setDisable(false);
      buttonEliminar2.setDisable(false);
      buttonAgregar2.setDisable(true);
    }
  }
    
  /**
   * Método que escucha lo que se selecciona en la tabla de horarios de materias.
   */
  private final ListChangeListener<HorarioMateria> seleccionarTableHorarioMateria = 
          new ListChangeListener<HorarioMateria>() {
    @Override
    public void onChanged(ListChangeListener.Change<? extends HorarioMateria> c) {
      vaciarHorarioMateriaTable();
    }
  };
  
  /**
   * Método para editar un horario de una materia de la tabla.
   * @param event - Evento de dar clic en el botón Editar.
   */
  @FXML
  private void editarHorarioMateria(ActionEvent event) {
    //Valida que se ingresen todos los datos del horario de la materia.
    if (!textFieldDia.getText().equals("") && !textFieldSalon.getText().equals("") && 
            !textFieldHoraEntrada.getText().equals("") && !textFieldHoraSalida.getText().equals("")){
      HorarioMateria horarioMateria = new HorarioMateria();
      horarioMateria.dia.set(textFieldDia.getText());
      horarioMateria.salon.set(textFieldSalon.getText());
      horarioMateria.horaEntrada.set(textFieldHoraEntrada.getText());
      horarioMateria.horaSalida.set(textFieldHoraSalida.getText());
      horariosMaterias.set(posicionHorarioMateria, horarioMateria);
      limpiarTextFields2(new ActionEvent());
    }
  }
  
  /**
   * Método para eliminar un horario de una materia de la tabla.
   * @param event - Evento de dar clic en el botón Eliminar.
   */
  @FXML
  private void eliminarHorarioMateria(ActionEvent event) {
    horariosMaterias.remove(posicionHorarioMateria);
    limpiarTextFields2(new ActionEvent());
  }
  
  /**
   * Método para limpiar los text fields de horarios.
   * @param event - Evento de dar clic en el botón Limpiar.
   */
  @FXML
  private void limpiarTextFields2(ActionEvent event) {
    textFieldDia.setText("");
    textFieldSalon.setText("");
    textFieldHoraEntrada.setText("");
    textFieldHoraSalida.setText("");
    buttonEditar2.setDisable(true);
    buttonEliminar2.setDisable(true);
    if (!buttonEditar.isDisable()) {
      buttonAgregar2.setDisable(false);
    }
  }
  
  /**
   * Método para guardar los horarios de materias de la tabla en la base de datos.
   * @param event - Evento de dar clic en el botón Guardar.
   */
  @FXML
  private void guardarHorariosMaterias(ActionEvent event) {
    final Materia materia = obtenerMateriaTable();
    int idmateria = Integer.parseInt(materia.getIdmateria());
    try {
      //Primero borramos todos los horarios de la materia guardados en la base de datos.
      sqlHorariosMaterias.borrarHorariosMaterias(idmateria);
    } catch (SQLException ex) {
      Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //Obtiene cada horario de la materia de la tabla y lo guarda en la base de datos.
    for (int i = 0; i < tableHorariosMaterias.getItems().size(); i++) {
      int idhorarioMateria = Integer.parseInt(tableHorariosMaterias.getItems().get(i).getIdhorarioMateria());
      String dia = (String) tableHorariosMaterias.getItems().get(i).getDia();
      String salon = (String) tableHorariosMaterias.getItems().get(i).getSalon();
      String horaEntrada = (String) tableHorariosMaterias.getItems().get(i).getHoraEntrada();
      String horaSalida = (String) tableHorariosMaterias.getItems().get(i).getHoraSalida();
      try {
        sqlHorariosMaterias.agregarHorariosMaterias(idhorarioMateria, idmateria, dia, salon, horaEntrada, horaSalida);
      } catch (SQLException ex) {
        Logger.getLogger(FXMLAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    limpiarTextFields2(new ActionEvent());
  }
  
  /**
   * Método para regresar a la ventana de alumnos.
   * @param event - Evento de dar clic en el botón Regresar a Alumnos.
   * @throws IOException - Excepción al no encontrar el archivo de alumnos.
   */
  @FXML
  private void alumnos(ActionEvent event) throws IOException {
    ((Node) (event.getSource())).getScene().getWindow().hide();
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
   * Método para cambiar el stage principal por el stage de materias.
   * @param stagePrincipal - Stage de materias.
   */
  public void setStagePrincipal(Stage stagePrincipal) {
    this.stagePrincipal = stagePrincipal;
  }

  /**
   * Método para inicializar la ventana.
   * @param url
   * @param rb 
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    inicializarTableMaterias();
    inicializarTableHorariosMaterias();
    cargarMaterias();
    buttonEditar.setDisable(true);
    buttonEliminar.setDisable(true);
    buttonAgregar2.setDisable(true);
    buttonEditar2.setDisable(true);
    buttonEliminar2.setDisable(true);
    buttonGuardar2.setDisable(true);
    final ObservableList<Materia> materiaSeleccionada = tableMaterias.getSelectionModel().getSelectedItems();
    materiaSeleccionada.addListener(seleccionarTableMateria);
    final ObservableList<HorarioMateria> horarioMateriaSeleccionado = tableHorariosMaterias.getSelectionModel().getSelectedItems();
    horarioMateriaSeleccionado.addListener(seleccionarTableHorarioMateria);
  }
}
