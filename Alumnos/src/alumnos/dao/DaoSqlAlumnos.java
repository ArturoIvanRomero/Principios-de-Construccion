/**
 * Descripción: Dao de la clase SqlAlumnos.
 * Modificación: 2019/03/08
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/08
 */
package alumnos.dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoSqlAlumnos {
  public List<String> consultarAlumnos() throws SQLException;
  public int borrarAlumnos() throws SQLException;
  public int agregarAlumnos(String matricula, String nombre, String apellidoPaterno, 
          String apellidoMaterno) throws SQLException;
}
