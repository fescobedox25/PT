
package conexion;
import java.sql.*;


public class Conexion {
    
    //conexion local
    
    public static Connection conectar(){
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:8000/bd_process2", "root", "root");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
            
        }
        return(null);
    }
}
