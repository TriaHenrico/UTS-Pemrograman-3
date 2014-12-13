/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasigudang;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class CancelBarang implements ActionListener{
    private JPanel panel;
    private JFrame frame;
    private JButton bTerapkan, bMenuAwal;
    private JLabel lNamaBarang, labelDummy;
    private JComboBox cbNamaBarang;
    
    //buat objek class databaseservice
    DatabaseService ds = new DatabaseService();

    
    public CancelBarang() throws ClassNotFoundException, SQLException{
        frame = new JFrame("Cancel Barang");
        frame.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(5,1));
        
        //buttons
        bTerapkan = new JButton("Terapkan!");
        bMenuAwal = new JButton("Menu Awal");
        
        //labels
        lNamaBarang = new JLabel("Nama Barang");
        labelDummy = new JLabel("");
        
        //combo box
        ArrayList<String> isiComboBox = ds.comboBarang();
        cbNamaBarang = new JComboBox(isiComboBox.toArray());
    }
    public void mulai(){
        //semua komponen ditaruh di panel
        panel.add(lNamaBarang);
        panel.add(cbNamaBarang);
        panel.add(labelDummy);
        panel.add(bTerapkan);
        panel.add(bMenuAwal);
        
        //panel nya tambahin ke frame
        frame.add(panel, BorderLayout.WEST);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(220,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        //event button nya
        bTerapkan.addActionListener(this);
        bTerapkan.setActionCommand("terapkan");
        bMenuAwal.addActionListener(this);
        bMenuAwal.setActionCommand("menu_awal");
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        CancelBarang cb = new CancelBarang();
        cb.mulai();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "terapkan"){
            try {
                String nama = (String) cbNamaBarang.getSelectedItem();
                String hasil = ds.hapusBarang(nama);
                if(hasil.equals("berhasil")){
                    System.out.println("data berhasil dihapus");
                }
                else{
                    System.out.println("data gagal dihapus");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CancelBarang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CancelBarang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "menu_awal"){
            frame.hide();
        }
    }
}
