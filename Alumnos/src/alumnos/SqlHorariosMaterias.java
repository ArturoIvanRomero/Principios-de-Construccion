/**
 * Descripción: Clase para sentencias SQL para obtener información de los horarios de las materias
 *              de la base de datos.
 * Modificación: 2019/03/18
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/03/18
 */
package alumnos;

import alumnos.dao.DaoSqlHorariosMaterias;
import alumnos.conexion.Conexion;
import static alumnos.Alumnos.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlHorariosMaterias implements DaoSqlHorariosMaterias {
  /**
   * Mpetodo para consultar información sobre los horarios de las maerias guardados en la base de datos.
   * @param idmateria - ID de la materia de la que se consultarán horarios.
   * @return listaMaterias - Lista de los atributos de todas los horarios de las materias.
   * @throws SQLException - Excpeción de SQL (base de datos).
   */
  @Override
  public List<String> consultarHorariosMaterias(int idmateria) throws SQLException{
    List<String> listaHorariosMaterias = new ArrayList<String>();
    Statement s;
    Connection conn = null;
    conn = new Conexion().getConnection();
    ResultSet rs = null;
    String sQuery = "select idhorarioMateria, idmateria, dia, salon, horaEntrada, horaSalida from "
            + "horarioMateria where idmateria="+idmateria+" order by dia";
    try {
      s = conn.createStatement();
      rs = s.executeQuery(sQuery);
      while (rs != null && rs.next()) {
        listaHorariosMaterias.add(String.valueOf(rs.getInt("idhorarioMateria")));
        listaHorariosMaterias.add(String.valueOf(rs.getInt("idmateria")));
        listaHorariosMaterias.add(rs.getString("dia"));
        listaHorariosMaterias.add(rs.getString("salon"));
        listaHorariosMaterias.add(rs.getString("horaEntrada"));
        listaHorariosMaterias.add(rs.getString("horaSalida"));
      }
    } catch (SQLException e) {
      System.err.println("Error al consultar: " + e.getMessage() + "\n" + e.getErrorCode());
    } finally {
      conn.close();
    }
    return listaHorariosMaterias;    
  }
  
  /**
   * Método para borrar a todos los horarios de las materias de la base de datos.
   * @param idmateria - ID de la materia de la que se borrarn los horarios.
   * @return s.executeUpdate(sQuery) - Número de filas afectadas por la consulta.
   * @throws SQLException - Excepción de SQL (base de datos).
   */
  @Override
  public int borrarHorariosMaterias(int idmateria) throws SQLException{
    String sQuery = "delete from horarioMateria where idmateria="+idmateria+";";
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
   * Método para agregar un horario a una materia a la base de datos.
   * @param idhorarioMateria - ID del horario de la materia.
   * @param idmateria - ID de la materia.
   * @param dia - Día del horario.
   * @param salon - Salón del horario.
   * @param horaEntrada - Hora de entrada del horario.
   * @param horaSalida - Hora de salida del horario.
   * @return s.executeUpdate(sQuery) - Número de filas afectadas por la consulta.
   * @throws SQLException - Excepción de SQL (base de datos).
   */
  @Override
  public int agregarHorariosMaterias(int idhorarioMateria, int idmateria, String dia, String salon,
          String horaEntrada, String horaSalida) throws SQLException{
    String sQuery = "insert into horarioMateria values ("+idhorarioMateria+","+idmateria+",'"+dia+"','"+salon+"',"
            + "'"+horaEntrada+"','"+horaSalida+"');"; 
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
