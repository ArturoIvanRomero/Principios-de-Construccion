/**
 * Descripción: Clase de conexión a la base de datos.
 *              Guarda los datos y realiza la conexión a la base de datos.
 * Modificación: 2019/02/21
 *
 * @author Romero Peña Arturo Iván
 * @version 1.0
 * @since 2019/02/21
 */
package alumnos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

  private Connection connection;
  private String host;
  private String baseDatos;
  private String usuario;
  private String contrasenia;
  private static Conexion conexion;

  /**
   * Constructor vacio de la conexión para la base de datos.
   */
  public Conexion() {
    host = "localhost";
    baseDatos = "alumnos";
    usuario = "usuarioAlumno";
    contrasenia = "123456";
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://" + host + "/" + baseDatos;
      connection = DriverManager.getConnection(url, usuario, contrasenia);
      System.out.println("Conexion a BD establecida");
    } catch (SQLException ex) {
      System.out.println("Error de mysql");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Se produjo un error inesperado: " + e.getMessage());
    }
    conexion = this;
  }

  /**
   * Constructor de la conexión de base de datos, donde se mandan los parámetros
   * @param host - Host de la base de datos.
   * @param baseDatos - Nombre de la base de datos.
   * @param usuario - Usuario de la base de datos.
   * @param contrasenia - Contraseña del usuario de la base de datos.
   * @throws ClassNotFoundException - Excepción cuando no se encuantra la clase.
   * @throws SQLException - Excepción de SQL (base de datos).
   */
  public Conexion(String host, String baseDatos, String usuario, String contrasenia) 
          throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    this.host = host;
    this.baseDatos = baseDatos;
    this.usuario = usuario;
    this.contrasenia = contrasenia;
    connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + baseDatos, usuario, contrasenia);
    conexion = this;
  }

  /**
   * Método para obtener el host de la conexión.
   * @return host - Host de la base de datos.
   */
  public String getHost() {
    return host;
  }

  /**
   * Método para cambiar el host de la conexión.
   * @param host - Host de la base de datos.
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * Método para obtener el nombre de la base de datos.
   * @return baseDatos - Nombre de la base de datos.
   */
  public String getBaseDatos() {
    return baseDatos;
  }

  /**
   * Método para cambiar el nombre de la base de datos.
   * @param baseDatos - Nombre de la base de datos.
   */
  public void setBaseDatos(String baseDatos) {
    this.baseDatos = baseDatos;
  }

  /**
   * Método para obtener el nombre de usuario.
   * @return usuario - Usuario de la base de datos.
   */
  public String getUsuario() {
    return usuario;
  }

  /**
   * Método para cambiar el nombre de usuario.
   * @param usuario - Usuario de la base de datos.
   */
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  /**
   * Método para obtener la contraseña del usuario.
   * @return contrasenia - Contraseña del usuario.
   */
  public String getContrasenia() {
    return contrasenia;
  }

  /**
   * Método para cambiar la contraseña del usuario.
   * @param contrasenia - Contraseña del usuario.
   */
  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

  /**
   * Método para obtener la conexión a la base de datos.
   * @return connection - Conexión a la base da datos.
   */
  public Connection getConnection() {
    return connection;
  }

  /**
   * Método para cambiar la conexión a la base de datos.
   * @param connection - Conexión a la base da datos.
   */
  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  /**
   * Método para obtener la conexión a la base de datos.
   * @return conexion - Conexión a la base da datos.
   */
  public static Conexion getConexion() {
    return conexion;
  }

  /**
   * Método para cambiar la conexión a la base de datos.
   * @param conexion - Conexión a la base da datos.
   */
  public static void setConexion(Conexion conexion) {
    Conexion.conexion = conexion;
  }
 
  /**
   * Método para cerrar la conexión a la base de datos.
   */
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
    }
  }
}
