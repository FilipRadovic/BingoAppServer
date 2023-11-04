/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Igra;
import domen.RezultatIgre;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author frado
 */
public class Kontroler {
    
    private static Kontroler instance;
    private DBBroker dbb;
    private int[] dobitnaKombinacija = new int[4];
    int rbIgre = 0;
    private RezultatIgre rezIgre = new RezultatIgre(0, null, null, 0, "Izgubio", new ArrayList<>());
    

    private Kontroler() {
        dbb = new DBBroker();
    }
    
    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public int[] getDobitnaKombinacija() {
        return dobitnaKombinacija;
    }

    public void setDobitnaKombinacija(int[] dobitnaKombinacija) {
        this.dobitnaKombinacija = dobitnaKombinacija;
    }
    
    public Igra posaljiKomb(int[] niz){
        
        rezIgre.setBrojPokusaja(niz[4]);
        int brojPogodjenihNaMestu=0;
        int brojPogodjenihNisuNaMestu=0;
        boolean [] pogodjeniNaMestu=new boolean[4];
        boolean [] iskorisceniBrojevi=new boolean[4];
        
        for(int i=0;i<4;i++){
            if(dobitnaKombinacija[i]==niz[i]){
                brojPogodjenihNaMestu++;
                pogodjeniNaMestu[i]=true;
                iskorisceniBrojevi[i]=true;
            }
        }
        for(int i=0;i<4;i++){
            if(!pogodjeniNaMestu[i]){
                for(int j=0;j<4;j++){
                    if(!pogodjeniNaMestu[j] && !iskorisceniBrojevi[j] && dobitnaKombinacija[j]==niz[i]){
                        brojPogodjenihNisuNaMestu++;
                        iskorisceniBrojevi[j]=true;
                        break;
                    }
                }
            }
        }
        
        if (brojPogodjenihNaMestu == 4) {
            rezIgre.setRezultat("Pobedio");
        }
        
        Igra igra = new Igra(null, ++rbIgre, niz[0]+"-"+niz[1]+"-"+niz[2]+"-"+niz[3], brojPogodjenihNaMestu, brojPogodjenihNisuNaMestu);
        rezIgre.getListaIgara().add(igra);
        
        return igra;
    }

    public boolean sacuvajIgru() throws SQLException {
        if (rezIgre.getListaIgara().isEmpty()) {
            return false;
        }
        rezIgre.setZadataKombinacija(dobitnaKombinacija[0] + "-" + dobitnaKombinacija[1] + "-" +
                dobitnaKombinacija[2] + "-" + dobitnaKombinacija[3]);
        return dbb.sacuvajIgru(rezIgre);
        
    }
    
}
