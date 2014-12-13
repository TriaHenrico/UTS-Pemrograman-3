/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasigudang;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javax.swing.*;

/**
 *
 * @author Tria Henrico
 */
public class AplikasiGudang implements ActionListener{
    private JFrame f;
    private JButton Masuk;
    private JPanel LayoutPanel;
    private JLabel Labele;
    private JTextField Fielde;
    private JPasswordField Passworde;
    /**
     * @param args the command line arguments
     */
    public AplikasiGudang(){
        f = new JFrame("POS Server v 0.7");
        Masuk = new JButton("Masuk");
        LayoutPanel = new JPanel();
        Labele = new JLabel("Silakan Login");
        Fielde = new JTextField(10);
        Passworde = new JPasswordField(10);
        LayoutPanel.setLayout(new GridLayout(5,1));
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    public void AplikasiGudang(){
        LayoutPanel.add(Labele);
        LayoutPanel.add(Fielde);
        LayoutPanel.add(Passworde);
        LayoutPanel.add(Masuk);
        Masuk.addActionListener(this);
        Masuk.setActionCommand("Masuk");
        f.add(LayoutPanel, BorderLayout.WEST);
        f.setSize(600,300);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
       AplikasiGudang user = new AplikasiGudang();
       user.AplikasiGudang();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Masuk"){
            try {
                String username = Fielde.getText();
                String password = Passworde.getText();
                
                DatabaseService DS = new DatabaseService();
                String hasil = DS.login(username, password);
                
                if(!"".equals(hasil)){
                    MainMenu mm = new MainMenu();
                    mm.MenuUtama();
                    f.hide();
                }
                else{
                    System.out.println("TETOOOOTTT!!!");
                            
                }
            } catch (Exception ex) {
                Logger.getLogger(AplikasiGudang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
