/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author fesco
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
 
public class MiRenderer extends DefaultTableCellRenderer {
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
                                                   boolean isSelected, 
                                                   boolean hasFocus, 
                                                   int row, 
                                                   int column) {
 
        String color = (String) table.getValueAt(row, 3);
 
        switch (color) {
            case "verde":
                setBackground(Color.GREEN);
                setForeground(Color.BLACK);
                break;
            case "amarillo":
                setBackground(Color.YELLOW);
                setForeground(Color.BLACK);
                break;
            default:
                setBackground(Color.RED);
                setForeground(Color.BLACK);
                break;
        }
 
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
 
}