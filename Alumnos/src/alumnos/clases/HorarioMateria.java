/**
 * Descripción: Clase HorarioMateria.
 *              Guarda los datos de los horarios de una materia.
 * Modificación: 2019/03/18
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/18
 */
package alumnos.clases;

import javafx.beans.property.SimpleStringProperty;

public class HorarioMateria {
  public SimpleStringProperty idhorarioMateria = new SimpleStringProperty();
  public SimpleStringProperty idmateria = new SimpleStringProperty();
  public SimpleStringProperty dia = new SimpleStringProperty();
  public SimpleStringProperty salon = new SimpleStringProperty();
  public SimpleStringProperty horaEntrada = new SimpleStringProperty();
  public SimpleStringProperty horaSalida = new SimpleStringProperty();

  /**
   * Método para obtener el ID del horario de la materia.
   * @return idhorarioMateria - ID del horario de la materia.
   */
  public String getIdhorarioMateria() {
    return idhorarioMateria.get();
  }

  /**
   * Método para obtener el ID de la materia.
   * @return idmateria - ID de la materia.
   */
  public String getIdmateria() {
    return idmateria.get();
  }

  /**
   * Método para obtener el día del horario.
   * @return dia - Día del horario.
   */
  public String getDia() {
    return dia.get();
  }

  /**
   * Método para obtener el salón del horario.
   * @return salon - Salón del horario.
   */
  public String getSalon() {
    return salon.get();
  }
  
  /**
   * Método para obtener la hora del entrada del horario.
   * @return horaEntrada - Hora de entrada del horario.
   */
  public String getHoraEntrada() {
    return horaEntrada.get();
  }
  
  /**
   * Método para obtener la hora de salida del horario.
   * @return horaSalida - Hora de salida del horario.
   */
  public String getHoraSalida() {
    return horaSalida.get();
  }
}
