/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fesco
 */
public class Tareas extends Conexion {

    public boolean crearTarea(String responsable, String categoria, String titulo, String fecha, String descripcion) throws SQLException {
        Connection cn = Conexion.conectar();
        PreparedStatement pst = cn.prepareStatement("INSERT INTO tareas values (0,?,?,?,?,?,?,?);");
        pst.setString(1, responsable);
        pst.setString(2, categoria);
        pst.setString(3, titulo);
        pst.setString(4, descripcion);
        pst.setString(5, fecha);
        Date dd = new Date();
        SimpleDateFormat formatoNum = new SimpleDateFormat("yyyy-MM-dd");
        pst.setString(6, formatoNum.format(dd));
        pst.setString(7, "En Proceso");
        return pst.execute();
    }
   
    
}
