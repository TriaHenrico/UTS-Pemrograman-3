/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasigudang;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Tria Henrico
 */
public class MainMenu implements ActionListener{
    private JFrame f;
    private JButton Input, Output, Cancel, SignOff, Retailer, DaftarRetailer, DaftarBarang;
    private JPanel LayoutPanel, Panel2, Panel3;
    private JLabel Label1, Label2;
    
    public MainMenu(){
        f = new JFrame("POS Server v 0.7");
        Input = new JButton("Input");
        Output = new JButton("Output");
        Cancel = new JButton("Cancel");
        SignOff = new JButton("Sign Off");
        Retailer = new JButton("Retailer");
        DaftarRetailer = new JButton("Daftar retailer");
        DaftarBarang = new JButton("Daftar Barang");
        LayoutPanel = new JPanel();
        Label1 = new JLabel("Selamat Datang, Admin");
        Label2 = new JLabel("Silakan pilih aksi manipulasi");
        Panel2 = new JPanel();
        Panel3 = new JPanel();
        LayoutPanel.setLayout( new GridLayout(4, 1));
        Panel2.setLayout(new FlowLayout());
        Panel3.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    public void MenuUtama(){
        Panel2.add(Input);
        Panel2.add(Output);
        Panel2.add(Cancel);
        Panel2.add(Retailer);
        Panel2.add(DaftarBarang);
        Panel2.add(DaftarRetailer);
        Panel3.add(SignOff, BorderLayout.EAST);
        LayoutPanel.add(Label1, BorderLayout.CENTER);
        LayoutPanel.add(Label2, BorderLayout.CENTER);
        LayoutPanel.add(Panel2);
        LayoutPanel.add(Panel3);
        
        //tambahin event
        Input.addActionListener(this);
        Input.setActionCommand("input");
        Output.addActionListener(this);
        Output.setActionCommand("output");
        Cancel.addActionListener(this);
        Cancel.setActionCommand("cancel");
        Retailer.addActionListener(this);
        Retailer.setActionCommand("retailer");
        DaftarBarang.setActionCommand("daftarbarang");
        DaftarBarang.addActionListener(this);
        DaftarRetailer.setActionCommand("daftarretailer");
        DaftarRetailer.addActionListener(this);
        
        
        f.add(LayoutPanel);
        f.setSize(600,300);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //dapatkan tombol apa yang di klik dari ActionCommand nya
        if(e.getActionCommand() == "input"){
            //f.hide();
            InputBarang ib = new InputBarang();
            ib.mulai();
        }
        else if(e.getActionCommand() == "daftarbarang"){
            try {
                //System.out.println("haha");
                viewbarang vr = new viewbarang();
                vr.tampilkan();
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "daftarretailer"){
            try {
                //System.out.println("haha");
                viewretailer vr = new viewretailer();
                vr.tampilkan();
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "output"){
            try {
                //f.hide();
                OutputBarang ob = new OutputBarang();
                ob.mulai();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "cancel"){
            try {
                //f.hide();
                CancelBarang cb = new CancelBarang();
                cb.mulai();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand() == "retailer"){
            try {
                //f.hide();
                Retailer r = new Retailer();
                r.mulai();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
