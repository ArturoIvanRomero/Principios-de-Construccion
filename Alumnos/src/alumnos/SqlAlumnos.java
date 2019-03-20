/**
 * Descripción: Clase para sentencias SQL para obtener información de alumnos de la base de datos.
 * Modificación: 2019/02/21
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/02/21
 */
package alumnos;

import alumnos.dao.DaoSqlAlumnos;
import alumnos.conexion.Conexion;
import static alumnos.Alumnos.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlAlumnos implements DaoSqlAlumnos {
  /**
   * Mpetodo para consultar información sobre los alumnos guardados en la base de datos.
   * @return listaAlumnos - Lista de los atributos de todos los alumno.
   * @throws SQLException - Excpeción de SQL (base de datos).
   */
  @Override
  public List<String> consultarAlumnos() throws SQLException{
    List<String> listaAlumnos = new ArrayList<String>();
    Statement s;
    Connection conn = null;
    conn = new Conexion().getConnection();
    ResultSet rs = null;
    String sQuery = "select matricula, nombre, apellidoPaterno, apellidoMaterno from alumno order by matricula";
    try {
      s = conn.createStatement();
      rs = s.executeQuery(sQuery);
      while (rs != null && rs.next()) {
        listaAlumnos.add(rs.getString("matricula"));
        listaAlumnos.add(rs.getString("nombre"));
        listaAlumnos.add(rs.getString("apellidoPaterno"));
        listaAlumnos.add(rs.getString("apellidoMaterno"));
      }
    } catch (SQLException e) {
      System.err.println("Error al consultar: " + e.getMessage() + "\n" + e.getErrorCode());
    } finally {
      conn.close();
    }
    return listaAlumnos;
  }
  
  /**
   * Método para borrar a todos los alumnos de la base de datos.
   * @return s.executeUpdate(sQuery) - Número de filas afectadas por la consulta.
   * @throws SQLException - Excepción de SQL (base de datos).
   */
  @Override
  public int borrarAlumnos() throws SQLException{
    String sQuery = "delete from alumno;";
    try {
      connection = new Conexion().getConnection();
      Statement s = connection.createStatement();
      return s.executeUpdate(sQuery);
    } catch (SQLException e) {
      System.err.println("Eror al borrar: " + e.getMessage() + "\n" + e.getErrorCode());
    } finally {
      connection.close();
    }
    return -1;
  }
  
  /**
   * Método para agregar un alumno a la base de datos.
   * @param matricula - Matrícula del alumno.
   * @param nombre - Nombre del alumno.
   * @param apellidoPaterno - Apellido paterno del alumno.
   * @param apellidoMaterno - Apellido materno del alumno.
   * @return s.executeUpdate(sQuery) - Número de filas afectadas por la consulta.
   * @throws SQLException - Excepción de SQL (base de datos).
   */
  @Override
  public int agregarAlumnos(String matricula, String nombre, String apellidoPaterno, 
          String apellidoMaterno) throws SQLException{
    String sQuery = "insert into alumno values ('"+matricula+"','"+nombre+"','"+apellidoPaterno+"','"
            + ""+apellidoMaterno+"');"; 
    try {
      connection = new Conexion().getConnection();
      Statement s = connection.createStatement();
      return s.executeUpdate(sQuery);
    } catch (SQLException e) {
      System.err.println("Error al agregar: " + e.getMessage() + "\n" + e.getErrorCode());
    } finally {
      connection.close();
    }
    return -1;
  }
}
