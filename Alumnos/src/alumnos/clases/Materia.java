/**
 * Descripción: Clase Materia.
 *              Guarda los datos de las materias.
 * Modificación: 2019/03/17
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/17
 */
package alumnos.clases;

import javafx.beans.property.SimpleStringProperty;

public class Materia {
  public SimpleStringProperty idmateria = new SimpleStringProperty();
  public SimpleStringProperty nombre = new SimpleStringProperty();
  public SimpleStringProperty nrc = new SimpleStringProperty();
  public SimpleStringProperty horasPracticas = new SimpleStringProperty();
  public SimpleStringProperty horasTeoricas = new SimpleStringProperty();
  public SimpleStringProperty creditos = new SimpleStringProperty();

  /**
   * Método para obtener el ID de la materia.
   * @return idmateria - ID de la materia.
   */
  public String getIdmateria() {
    return idmateria.get();
  }

  /**
   * Método para obtener el nombre de la materia.
   * @return nombre - Nombre de la materia.
   */
  public String getNombre() {
    return nombre.get();
  }
  
  /**
   * Método para obtener el NRC de la materia.
   * @return nrc - NRC de la materia.
   */
  public String getNrc() {
    return nrc.get();
  }

  /**
   * Método para obtener las horas prácticas de la materia.
   * @return horasPracticas - Horas prácticas de la materia.
   */
  public String getHorasPracticas() {
    return horasPracticas.get();
  }

  /**
   * Método para obtener las horas teóricas de la materia.
   * @return horasTeoricas - Horas teóricas de la materia.
   */
  public String getHorasTeoricas() {
    return horasTeoricas.get();
  }
  
  /**
   * Método para obtener los créditos de la materia.
   * @return creditos - Créditos de la materia.
   */
  public String getCreditos() {
    return creditos.get();
  }
}
