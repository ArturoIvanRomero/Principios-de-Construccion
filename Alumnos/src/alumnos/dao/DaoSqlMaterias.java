/**
 * Descripción: Dao de la clase SqlMaterias.
 * Modificación: 2019/03/17
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/17
 */
package alumnos.dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoSqlMaterias {
  public List<String> consultarMaterias() throws SQLException;
  public int borrarMaterias() throws SQLException;
  public int agregarMaterias(int idmateria, String nombre, String nrc, int horasPracticas, int horasTeoricas, 
          int creditos) throws SQLException;
}
