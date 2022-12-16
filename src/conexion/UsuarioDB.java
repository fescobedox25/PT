/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fesco
 */
public class UsuarioDB extends Conexion {
    
    public List<Usuario> buscaUsuarios() throws SQLException{
        List<Usuario> lista = new ArrayList<>();
        Connection cn = Conexion.conectar();
        PreparedStatement pst = cn.prepareStatement("SELECT id_usuario, nombre_usuario, email, telefono, username, tipo_nivel, estatus FROM usuarios;");
        pst.executeQuery();
        ResultSet rs = pst.getResultSet();
        while (rs.next()) {
            Usuario i = new Usuario();
            i.setCorreo(rs.getString("email"));
            i.setEstado(rs.getString("estatus"));
            i.setId(rs.getInt("id_usuario"));
            i.setNivel(rs.getString("tipo_nivel"));
            i.setNombre(rs.getString("nombre_usuario"));
            i.setTelefono(rs.getString("telefono"));
            i.setUsuario(rs.getString("username"));
            lista.add(i);
        }
        return lista;
    }
    
    public void actaulizarUsuario(int id, String nombre, String correo, String telefono, String usuario, String nivel, String estado) throws SQLException{
        Connection cn = Conexion.conectar();
        PreparedStatement pst = cn.prepareStatement("UPDATE usuarios SET nombre_usuario = ?, email = ?, telefono = ?, username = ?, tipo_nivel = ?, estatus = ? WHERE id_usuario = ? ;");
        pst.setString(1, nombre);
        pst.setString(2, correo);
        pst.setString(3, telefono);
        pst.setString(4, usuario);
        pst.setString(5, nivel);
        pst.setString(6, estado);
        pst.setInt(7, id);
        pst.execute();
    }
    
}
