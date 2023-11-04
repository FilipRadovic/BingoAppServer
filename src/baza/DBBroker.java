/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Igra;
import domen.RezultatIgre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frado
 */
public class DBBroker {
    
    public ArrayList<Object> vrati(){
        
        ArrayList<Object> lista = new ArrayList<>();
        String upit = "";
        
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            
            while (rs.next()) {                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    
    public boolean cuvajIzmeniBrisi(){
        
        String upit = "";
        
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.executeQuery();
            
            
            Konekcija.getInstance().getConnection().commit();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean sacuvajIgru(RezultatIgre rezIgre) throws SQLException {
        String upit = "INSERT INTO REZULTATIGRE() VALUES (?,?,?,?,?)";
         int ID=maxID();
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            rezIgre.setRezultatIgreId(ID);
            ps.setInt(1, rezIgre.getRezultatIgreId());
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setString(3, rezIgre.getZadataKombinacija());
            ps.setInt(4, rezIgre.getBrojPokusaja());
            ps.setString(5, rezIgre.getRezultat());
            ps.executeUpdate();
            if(sacuvaneIgre(rezIgre)){
            Konekcija.getInstance().getConnection().commit();

            return true;
            }else{
                 Konekcija.getInstance().getConnection().rollback();

            return false;
            }

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int maxID() {
        String upit = "SELECT MAX(REZULTATIGREID) FROM REZULTATIGRE";
  int maxID=0;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                maxID=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ++maxID;
    }

    private boolean sacuvaneIgre(RezultatIgre rezIgre) throws SQLException {
        String upit = "INSERT INTO IGRA VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            for (Igra igra : rezIgre.getListaIgara()) {
                ps.setInt(1, rezIgre.getRezultatIgreId());
                ps.setInt(2, igra.getRb());
                ps.setString(3, igra.getKombinacija());
                ps.setInt(4, igra.getPogodjeniNaMestu());
                ps.setInt(5, igra.getPogodjeniNisuNaMestu());
                ps.addBatch();
            }
            ps.executeBatch();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    
    
    
    
}
