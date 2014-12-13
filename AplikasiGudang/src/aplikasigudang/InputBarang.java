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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class InputBarang implements ActionListener{
    private JPanel panel1, panel2;
    private JFrame frame;
    private JButton bInput, bMenuAwal;
    private JLabel lNamaBarang, lJenisBarang, lQuantity, lHargaSatuan, labelDummy, labelDummy2;
    private JTextField tfNamaBarang, tfQuantity, tfHargaSatuan;
    private JComboBox cbJenisBarang;
    private JOptionPane opInputBarang;
    
    
    public InputBarang(){
        frame = new JFrame("Input Barang");
        frame.setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(10,1));
        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        
        //buttons
        bInput = new JButton("Input");
        bMenuAwal = new JButton("Menu Awal");
        
        //labels
        lNamaBarang = new JLabel("Nama Barang");
        lJenisBarang = new JLabel("Jenis Barang");
        lQuantity = new JLabel("Quantity");
        lHargaSatuan = new JLabel("Harga Satuan");
        labelDummy = new JLabel("");
        labelDummy2 = new JLabel("");
        
        //text fields
        tfNamaBarang = new JTextField(10);
        tfQuantity = new JTextField(10);
        tfHargaSatuan = new JTextField(10);
        
        //combo box
        String[] isiComboBox = {"Makanan", "Minuman"};
        cbJenisBarang = new JComboBox(isiComboBox);
    }
    public void mulai(){
        //semua komponen ditaruh di panel
        //panel1.add(labelDummy2);
        panel1.add(lNamaBarang);
        panel1.add(tfNamaBarang);
        panel1.add(lJenisBarang);
        panel1.add(cbJenisBarang);
        panel1.add(lQuantity);
        panel1.add(tfQuantity);
        panel1.add(lHargaSatuan);
        panel1.add(tfHargaSatuan);
        panel1.add(labelDummy);
        panel1.add(bInput);
        
        panel2.add(bMenuAwal, BorderLayout.EAST);
        
        //panel nya tambahin ke frame
        frame.add(panel2, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.WEST);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(300,350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        //event tombol
        bInput.addActionListener(this);
        bInput.setActionCommand("input");
        bMenuAwal.addActionListener(this);
        bMenuAwal.setActionCommand("menu_awal");
        // event clear after input
        
       
    }
    public static void main(String[] args){
        InputBarang ib = new InputBarang();
        ib.mulai();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "input"){
            try {
                //ambil nilai text box nya
                String nama = tfNamaBarang.getText();
                String jenis = (String) cbJenisBarang.getSelectedItem();
                int qty = Integer.parseInt(tfQuantity.getText());
                int harga = Integer.parseInt(tfHargaSatuan.getText());
                
                //bikin objek DatabaseService, dan masukkan datanya
                DatabaseService ds = new DatabaseService();
                String output = ds.masukBarang(nama, jenis, qty, harga);
                
                //cek apakah berhasil
                if(output.equals("berhasil")){
                    System.out.println("data berhasil dimasukkan");
                    tfNamaBarang.setText("");
                    tfQuantity.setText("");
                    tfHargaSatuan.setText("");
                    opInputBarang.showMessageDialog(null, "Data berhasil dimasukkan");
                }
                else if(output.equals("gagal")){
                    System.out.println("data gagal dimasukkan");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InputBarang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(InputBarang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "menu_awal"){
            frame.hide();
        }
    }
}
