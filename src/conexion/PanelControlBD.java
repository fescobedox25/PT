/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import Entidades.Tarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fesco
 */
public class PanelControlBD extends Conexion {

    public List<Tarea> buscaTareas(String responsable) throws SQLException {
        List<Tarea> lista = new ArrayList<>();
        Connection cn = Conexion.conectar();
        PreparedStatement pst = cn.prepareStatement("SELECT idTarea, categoria, titulo, descripcion, Fecha, fechaInicio, estado FROM tareas WHERE resposable = '" + responsable + "';");
        pst.executeQuery();
        ResultSet rs = pst.getResultSet();
        while (rs.next()) {
            Tarea t = new Tarea();
            t.setId(rs.getInt("idTarea"));
            t.setCategoria(rs.getString("categoria"));
            t.setDescripcion(rs.getString("descripcion"));
            t.setFecha(rs.getString("Fecha"));
            t.setTitulo(rs.getString("titulo"));
            t.setSemaforo(calculaSemaforo(rs.getString("Fecha")));
            t.setPorcentaje((calculaPorcentaje(rs.getString("fechaInicio"), rs.getString("Fecha"))));
            t.setEstado(rs.getString("estado"));
            lista.add(t);
        }
        return lista;
    }

    private String calculaSemaforo(String fechaTermino) {
        Date dd = new Date();
        SimpleDateFormat formatoNum = new SimpleDateFormat("yyyyMMdd");
        int fechaActual = Integer.valueOf(formatoNum.format(dd));
        int fechaTareaIn = Integer.valueOf(fechaTermino.replaceAll("-", ""));
        int dias = fechaTareaIn - fechaActual;
        if (dias <= 6 && dias >= 1) {
            return "amarillo";
        } else if (dias <= 0) {
            return "rojo";
        }
        return "verde";
    }

    private int calculaPorcentaje(String fechaCrea, String fechaTermino) {
        Date dd = new Date();
        SimpleDateFormat formatoNum = new SimpleDateFormat("yyyyMMdd");
        int fechaActual = Integer.valueOf(formatoNum.format(dd));
        int fechaCreaIn = Integer.valueOf(fechaCrea.replaceAll("-", ""));
        int fechaTerminoIn = Integer.valueOf(fechaTermino.replaceAll("-", ""));
        int diasTarea = fechaTerminoIn - fechaCreaIn;
        int diasTransc = (fechaActual - fechaCreaIn);
        if (diasTransc == 0) {
            diasTransc = 1;
        }
        return (100 * diasTransc) / diasTarea;
    }

    public void actualizaTarea(int id, String descError, String estado) throws SQLException {
        Connection cn = Conexion.conectar();
        PreparedStatement pst = cn.prepareStatement("update tareas set estado = ?, descproblema = ? where idTarea = ?;");
        pst.setInt(3, id);
        pst.setString(1, estado);
        pst.setString(2, descError);
        pst.execute();
    }

}
