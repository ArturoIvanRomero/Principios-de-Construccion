/**
 * Descripción: Dao de la clase SqlHorariosMaterias.
 * Modificación: 2019/03/18
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/18
 */
package alumnos.dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoSqlHorariosMaterias {
  public List<String> consultarHorariosMaterias(int idmateria) throws SQLException;
  public int borrarHorariosMaterias(int idmateria) throws SQLException;
  public int agregarHorariosMaterias(int idhorarioMateria, int idmateria, String dia, String salon,
          String horaEntrada, String horaSalida) throws SQLException;
}
