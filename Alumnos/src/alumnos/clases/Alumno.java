/**
 * Descripción: Clase Alumno.
 *              Guarda los datos de los alumnos.
 * Modificación: 2019/02/21
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/02/21
 */
package alumnos.clases;

import javafx.beans.property.SimpleStringProperty;

public class Alumno {
  public SimpleStringProperty matricula = new SimpleStringProperty();
  public SimpleStringProperty nombre = new SimpleStringProperty();
  public SimpleStringProperty apellidoPaterno = new SimpleStringProperty();
  public SimpleStringProperty apellidoMaterno = new SimpleStringProperty();

  /**
   * Método para obtener la matrícula del alumno.
   * @return matricula - Matricula del alumno.
   */
  public String getMatricula() {
    return matricula.get();
  }

  /**
   * Método para obtener el nombre del alumno.
   * @return nombre - Nombre del alumno.
   */
  public String getNombre() {
    return nombre.get();
  }

  /**
   * Método para obtener el apellido paterno del alumno.
   * @return apellidoPaterno - Apellido paterno del alumno.
   */
  public String getApellidoPaterno() {
    return apellidoPaterno.get();
  }

  /**
   * Método para obtener el apellido materno del alumno.
   * @return apellidoMaterno - Apellido materno del alumno.
   */
  public String getApellidoMaterno() {
    return apellidoMaterno.get();
  }
}
