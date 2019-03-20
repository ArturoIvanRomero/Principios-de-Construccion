/**
 * Descripción: Clase Materia.
 *              Guarda los datos de las materias.
 * Modificación: 2019/03/17
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/17
 */
package alumnos;

import alumnos.dao.DaoSqlMaterias;
import alumnos.conexion.Conexion;
import static alumnos.Alumnos.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlMaterias implements DaoSqlMaterias {
  /**
   * Mpetodo para consultar información sobre las materias guardadas en la base de datos.
   * @return listaMaterias - Lista de los atributos de todas las materias.
   * @throws SQLException - Excpeción de SQL (base de datos).
   */
  @Override
  public List<String> consultarMaterias() throws SQLException{
    List<String> listaMaterias = new ArrayList<String>();
    Statement s;
    Connection conn = null;
    conn = new Conexion().getConnection();
    ResultSet rs = null;
    String sQuery = "select idmateria, nombre, nrc, horasPracticas, horasTeoricas, creditos from "
            + "materia order by idmateria";
    try {
      s = conn.createStatement();
      rs = s.executeQuery(sQuery);
      while (rs != null && rs.next()) {
        listaMaterias.add(String.valueOf(rs.getInt("idmateria")));
        listaMaterias.add(rs.getString("nombre"));
        listaMaterias.add(rs.getString("nrc"));
        listaMaterias.add(String.valueOf(rs.getInt("horasPracticas")));
        listaMaterias.add(String.valueOf(rs.getInt("horasTeoricas")));
        listaMaterias.add(String.valueOf(rs.getInt("creditos")));
      }
    } catch (SQLException e) {
      System.err.println("Error al consultar: " + e.getMessage() + "\n" + e.getErrorCode());
    } finally {
      conn.close();
    }
    return listaMaterias;
  }

  /**
   * Método para borrar a todas las materias de la base de datos.
   * @return s.executeUpdate(sQuery) - Número de filas afectadas por la consulta.
   * @throws SQLException - Excepción de SQL (base de datos).
   */
  @Override
  public int borrarMaterias() throws SQLException{
    String sQuery = "delete from materia;";
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
   * Método para agregar una materia a la base de datos.
   * @param idmateria - ID de la materia.
   * @param nombre - Nombre de la materia.
   * @param nrc - NRC de la materia.
   * @param horasPracticas - Horas prácticas de la materia.
   * @param horasTeoricas - Horas teóricas de la materia.
   * @param creditos - Créditos de la materia.
   * @return s.executeUpdate(sQuery) - Número de filas afectadas por la consulta.
   * @throws SQLException - Excepción de SQL (base de datos).
   */
  @Override
  public int agregarMaterias(int idmateria, String nombre, String nrc, int horasPracticas, int horasTeoricas, 
          int creditos) throws SQLException{
    String sQuery = "insert into materia values ("+idmateria+",'"+nombre+"','"+nrc+"',"+horasPracticas+","
            + ""+horasTeoricas+","+creditos+");"; 
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