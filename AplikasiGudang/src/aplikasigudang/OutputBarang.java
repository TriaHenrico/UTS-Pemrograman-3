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
public class OutputBarang implements ActionListener{
    private JPanel panel;
    private JFrame frame;
    private JButton bTerapkan, bMenuAwal;
    private JLabel lQuantity, lRetailer, labelDummy, lNamaBarang;
    private JTextField tfNamaBarang, tfQuantity;
    private JComboBox cbNamaBarang, cbRetailer;
    
    //buat objek class databaseservice
    DatabaseService ds = new DatabaseService();

    
    public OutputBarang() throws ClassNotFoundException, SQLException{
        frame = new JFrame("Output Barang");
        frame.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(11,1));
        
        //buttons
        bTerapkan = new JButton("Terapkan!");
        bMenuAwal = new JButton("Menu Awal");
        
        //labels
        lNamaBarang = new JLabel("Nama Barang");
        lQuantity = new JLabel("Quantity");
        labelDummy = new JLabel("");
        lRetailer = new JLabel("Retailer");
        
        //text fields
        tfNamaBarang = new JTextField(10);
        tfQuantity = new JTextField(10);
        
        //combo box
        ArrayList<String> isiComboBarang = ds.comboBarang();
        cbNamaBarang = new JComboBox(isiComboBarang.toArray());
        
        //String[] isiRetailer = {"7 Eleven", "Lawson"};
        ArrayList<String> isiRetailer = ds.comboRetailer();
        cbRetailer = new JComboBox(isiRetailer.toArray());
    }
    public void mulai(){
        //semua komponen ditaruh di panel
        panel.add(lNamaBarang);
        panel.add(cbNamaBarang);
        panel.add(lQuantity);
        panel.add(tfQuantity);
        panel.add(lRetailer);
        panel.add(cbRetailer);
        panel.add(labelDummy);
        panel.add(bTerapkan);
        panel.add(bMenuAwal);
        
        //panel nya tambahin ke frame
        frame.add(panel, BorderLayout.WEST);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(220,350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        //event tombolnya
        bTerapkan.addActionListener(this);
        bTerapkan.setActionCommand("terapkan");
        bMenuAwal.addActionListener(this);
        bMenuAwal.setActionCommand("menu_awal");
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        OutputBarang ob = new OutputBarang();
        ob.mulai();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "terapkan"){
            try {
                String namaBarang = (String) cbNamaBarang.getSelectedItem();
                int qty = Integer.parseInt(tfQuantity.getText());
                String namaRetailer = (String) cbRetailer.getSelectedItem();
                
                //kurangin stok barangnya
                ds.kurangStokBarang(namaBarang, qty);
                
                //dapatin ID barang nya
                int idBarang = ds.getIdBarang(namaBarang);
                //dapatin ID retailer nya
                int idRetailer = ds.getIdRetail(namaRetailer);
                
                //System.out.println("id barang : " + idBarang);
                //System.out.println("id retail : " + idRetailer);
                
                //masukin ke tabel transaksi
                String transaksi = ds.masukTransaksi(idRetailer, idBarang, qty);
                if(transaksi.equals("berhasil")){
                    System.out.println("transaksi berhasil");
                }
                else{
                    System.out.println("transaksi gagal");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OutputBarang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(OutputBarang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "menu_awal"){
            frame.hide();
        }
    }
}
