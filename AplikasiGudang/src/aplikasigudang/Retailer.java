/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasigudang;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
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
public class Retailer implements ActionListener{
    private JPanel panel1, panel2;
    private JFrame frame;
    private JButton bTambah, bHapus, bKembali;    
    private JLabel lJudul, lNamaRetailer, lAlamat, lAtau, lTelepon, labelDummy, labelDummy2, labelDummy3;
    private JTextField tfNamaRetailer, tfAlamat, tfTelepon;
    private JComboBox cbPilihPeritel;
    
    //buat objek class databaseservice
    DatabaseService ds = new DatabaseService();
    private PopupMenu tTelepon;
    private final JButton bTelepon;
    
    public Retailer() throws ClassNotFoundException, SQLException{
        frame = new JFrame("Input Barang");
        frame.setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(13,1));
        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        
        //buttons
        bTambah = new JButton("Tambah Peritel");
        bHapus = new JButton("Hapus Peritel yang ada");
        bKembali = new JButton("Kembali");
        bTelepon = new JButton("Tambah telepon");
        
        //labels
        lJudul = new JLabel("Daftar, serta manipulasi retailer");
        lNamaRetailer = new JLabel("Nama Retailer");
        lAlamat = new JLabel("Alamat");
        lTelepon = new JLabel("Telepon");
        lAtau = new JLabel("Atau");
        labelDummy = new JLabel("");
        labelDummy2 = new JLabel("");
        labelDummy3 = new JLabel("");
        
        
        //text fields
        tfNamaRetailer = new JTextField(10);
        tfAlamat = new JTextField(10);
        tfTelepon = new JTextField(10);
        
        //combo box
        //String[] isiComboBox = ds.comboRetailer();
        //String[] isiComboBox = {"7 Eleven", "Lawson"};
        ArrayList<String> isiComboBox = ds.comboRetailer();
        //isiComboBox.add("alfa");
        //isiComboBox.add("midi");
        
        cbPilihPeritel = new JComboBox(isiComboBox.toArray());
    }
    public void mulai(){
        //semua komponen ditaruh di panel
        panel1.add(lJudul);
        panel1.add(labelDummy);
        panel1.add(lNamaRetailer);
        panel1.add(tfNamaRetailer);
        panel1.add(lAlamat);
        panel1.add(tfAlamat);
        panel1.add(lTelepon);
        panel1.add(tfTelepon);
        panel1.add(bTambah);
        panel1.add(labelDummy2);
        panel1.add(lAtau);
        panel1.add(cbPilihPeritel);
        panel1.add(bHapus);
        
        panel2.add(bKembali, BorderLayout.EAST);
        
        //panel nya tambahin ke frame
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(280,420);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        //tambah even
        bTambah.addActionListener(this);
        bTambah.setActionCommand("tambah");
        bHapus.addActionListener(this);
        bHapus.setActionCommand("hapus");
        bKembali.addActionListener(this);
        bKembali.setActionCommand("kembali");
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Retailer r = new Retailer();
        r.mulai();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "tambah"){
            try {
                //ambil isi textbox nya
                String nama = tfNamaRetailer.getText();
                String alamat = tfAlamat.getText();
                String telepon = tfTelepon.getText();
                
                String hasil = ds.tambahRetailer(nama, alamat, telepon);
                if(hasil.equals("berhasil")){
                    System.out.println("peritel baru berhasil dimasukkan");
                    
                    //update combo nya
                    //ArrayList<String> isiComboBox = ds.comboRetailer();
                    cbPilihPeritel.addItem(nama);
                }
                else{
                    System.out.println("peritel gagal dimasukkan");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Retailer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Retailer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "hapus"){
            String nama = (String) cbPilihPeritel.getSelectedItem();
            try {
                String hasil = ds.hapusRetailer(nama);
                if(hasil.equals("berhasil")){
                    System.out.println("peritel berhasil dihapus");
                }
                else{
                    System.out.println("peritel gagal dihapus, atau sudah tidak ada dalam database");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Retailer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Retailer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "kembali"){
            frame.hide();
        }
    }
}
