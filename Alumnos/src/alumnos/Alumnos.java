/**
 * Descripción: Clase principal.
 *              Se aloja el método start() y el método mein().
 * Modificación: 2019/03/17
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/02/21
 */
package alumnos;

import alumnos.controlador.FXMLAlumnosController;
import alumnos.controlador.FXMLMateriasController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Alumnos extends Application {
  static Connection connection = null;
  static Statement statement = null;
  private Stage stagePrincipal;
  
  /**
   * Método para comenar con la ventana principal.
   * @param stage - Escenario para la ventana principal.
   * @throws Exception - Excepción al ejecutar el metodo alumnos().
   */
  @Override
  public void start(Stage stage) throws Exception {
    stagePrincipal = stage;
    alumnos();
  }
  
  /**
   * Método para abrir la ventana de alumnos.
   * @throws IOException - Excepción al intentar obtener la ruta del archivo fxml.
   */
  public void alumnos() throws IOException{
    FXMLLoader loader = new FXMLLoader(Alumnos.class.getResource("interfaz/FXMLAlumnos.fxml"));
    AnchorPane root = (AnchorPane) loader.load();
    Scene scene = new Scene(root);
    stagePrincipal.setTitle("Administrar Alumnos");
    stagePrincipal.setScene(scene);
    FXMLAlumnosController alumnosController = loader.getController();
    alumnosController.setAlumnos(this);
    stagePrincipal.show();
  }
  
  /**
   * Método para abrir la ventana de materias.
   * @throws IOException - Excepción al intentar obtener la ruta del archivo fxml.
   */
  public void materias() throws IOException{
    FXMLLoader loader = new FXMLLoader(Alumnos.class.getResource("interfaz/FXMLMaterias.fxml"));
    AnchorPane root = (AnchorPane) loader.load();
    Stage stage = new Stage();
    stage.setTitle("Administrar Materias");
    stage.initOwner(stagePrincipal);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    FXMLMateriasController materiasController = loader.getController();
    materiasController.setStagePrincipal(stage);
    stage.show();
  }

  /**
   * Método principal main().
   * @param args - Argumentos de inicio.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
