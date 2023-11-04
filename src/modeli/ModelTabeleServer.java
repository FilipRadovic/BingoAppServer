/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author frado
 */
public class ModelTabeleServer extends AbstractTableModel{
    
    ArrayList<Object> lista;
    String[] kolone = {};

    public ModelTabeleServer() {
        lista = new ArrayList<>();
    }
    
    

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Object stavkaListe = lista.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return "";
                
            default: return "return!";
        }
        
        
    }
    
}
