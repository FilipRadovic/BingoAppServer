/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author frado
 */
public class BingoNit extends Thread{
    
    private JLabel lbl_PrviBroj;
    private JLabel lbl_DrugiBroj;
    private JLabel lbl_TreciBroj;
    private JLabel lbl_Cetvrti_Broj;
    public boolean radi;

    public BingoNit(JLabel lbl_PrviBroj, JLabel lbl_DrugiBroj, JLabel lbl_TreciBroj, JLabel lbl_Cetvrti_Broj, boolean radi) {
        this.lbl_PrviBroj = lbl_PrviBroj;
        this.lbl_DrugiBroj = lbl_DrugiBroj;
        this.lbl_TreciBroj = lbl_TreciBroj;
        this.lbl_Cetvrti_Broj = lbl_Cetvrti_Broj;
        this.radi = radi;
    }
    

    @Override
    public void run() {
        while (radi) {            
            
            try {
                long prviBroj = Math.round(Math.random() * 5);
                long drugiBroj = Math.round(Math.random() * 5);
                long treciBroj = Math.round(Math.random() * 5);
                long cetvrtiBroj = Math.round(Math.random() * 5);
                
                lbl_PrviBroj.setText(String.valueOf(prviBroj));
                lbl_DrugiBroj.setText(String.valueOf(drugiBroj));
                lbl_TreciBroj.setText(String.valueOf(treciBroj));
                lbl_Cetvrti_Broj.setText(String.valueOf(cetvrtiBroj));
                
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BingoNit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    
}
