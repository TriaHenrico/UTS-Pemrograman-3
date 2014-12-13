/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplikasigudang;

/**
 *
 * @author Tria Henrico
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class DatabaseService {

    /*
    */

    /*
    */
    public String login(String username, String password) throws Exception {
        
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        String hasil = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        String sql2 = "select *from user where username='" + username +"'&& password='" + password +"'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql2);
        
        
        while(rs.next()) {
        
            hasil = rs.getString("username");
            
        }
        
        rs.close();
        conn.close();
        return hasil;
    }
    public String masukBarang(String nama, String jenis, int qty, int harga) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        String hasilMasuk = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        String query = "insert into barang (nama_barang, jenis, quantity, harga) values (?,?,?,?)";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, nama);
        ps.setString(2, jenis);
        ps.setInt(3, qty);
        ps.setInt(4, harga);
        
        //eksekusi
        int hasil = ps.executeUpdate();
        
        if(hasil > 0){
            hasilMasuk = "berhasil";
        }
        else{
            hasilMasuk = "gagal";
        }
        
        return hasilMasuk;
    }
    //KODINGAN FORM OUTPUT
    public ArrayList<String> comboBarang() throws ClassNotFoundException, SQLException{
        ArrayList<String> hasil = new ArrayList<>();
        
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        //String hasil = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        String sql2 = "select * from barang";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql2);
        
        int hitung = 0;
        while(rs.next()) {
            hasil.add(rs.getString("nama_barang"));
        }
        rs.close();
        conn.close();
        
        return hasil;
    }
    public void kurangStokBarang(String nama, int qty) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        //String hasil = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        String sql2 = "update barang set quantity = quantity-? where nama_barang = ?";
        PreparedStatement ps = conn.prepareStatement(sql2);
        ps.setInt(1, qty);
        ps.setString(2, nama);
        
        int hasil = ps.executeUpdate();
    }
    public int getIdRetail(String nama) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        int hasilID = 0;
        //String hasil = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        String sql2 = "select id_retailer as hasil from retailer where nama_retailer=?";
        PreparedStatement ps = conn.prepareStatement(sql2);
        ps.setString(1, nama);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            hasilID = rs.getInt("hasil");
        }
        
        return hasilID;
    }
    public int getIdBarang(String nama) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        int hasilID = 0;
        //String hasil = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        String sql2 = "select id_barang as hasil from barang where nama_barang=?";
        PreparedStatement ps = conn.prepareStatement(sql2);
        ps.setString(1, nama);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            hasilID = rs.getInt("hasil");
        }
        
        return hasilID;
    }
    public String masukTransaksi(int id_retailer, int id_barang, int qty) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        String hasilMasuk = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        String query = "insert into transaksi(id_barang, quantity, id_retailer) values (?,?,?)";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id_barang);
        ps.setInt(2, qty);
        ps.setInt(3, id_retailer);
        
        //eksekusi
        int hasil = ps.executeUpdate();
        
        if(hasil > 0){
            hasilMasuk = "berhasil";
        }
        else{
            hasilMasuk = "gagal";
        }
        
        return hasilMasuk;
    }
    
    
    //KODINGAN RETAILER
    public ArrayList<String> comboRetailer() throws ClassNotFoundException, SQLException{
        //String[] daftar = new String[];
        ArrayList<String> hasil = new ArrayList<>();
        
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        //String hasil = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        String sql2 = "select * from retailer";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql2);
        
        int hitung = 0;
        while(rs.next()) {
            hasil.add(rs.getString("nama_retailer"));
            //daftar[hitung] = rs.getString("nama_retailer");
            //hitung++;
            //hasil = rs.getString("hasil");
        }
        
        rs.close();
        conn.close();
        
        return hasil;
    }
    public String tambahRetailer(String nama, String alamat, String telepon) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        String hasilMasuk = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        String query = "insert into retailer(nama_retailer, alamat, telepon) values (?,?,?)";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, nama);
        ps.setString(2, alamat);
        ps.setString(3, telepon);
        
        //eksekusi
        int hasil = ps.executeUpdate();
        
        if(hasil > 0){
            hasilMasuk = "berhasil";
        }
        else{
            hasilMasuk = "gagal";
        }
        
        return hasilMasuk;
    }
    public String hapusRetailer(String nama) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        String hasilMasuk = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        String query = "delete from retailer where nama_retailer = ?";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, nama);
        
        //eksekusi
        int hasil = ps.executeUpdate();
        
        if(hasil > 0){
            hasilMasuk = "berhasil";
        }
        else{
            hasilMasuk = "gagal";
        }
        
        return hasilMasuk;
    }
    
    //KODINGAN FORM CANCEL
    public String hapusBarang(String nama) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        String hasilMasuk = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        String query = "delete from barang where nama_barang = ?";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, nama);
        
        //eksekusi
        int hasil = ps.executeUpdate();
        
        if(hasil > 0){
            hasilMasuk = "berhasil";
        }
        else{
            hasilMasuk = "gagal";
        }
        
        return hasilMasuk;
    }

    public String[][] getRetailer() throws SQLException{
        
        try {
            // 0. Variabel untuk koneksi
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://localhost/gudangjava";
            String dbUser = "root";
            String dbPass = "";
            
            //jumlah baris di tabel retailer
            int jumlahBaris = hitungBarisTabel("retailer");
            String[][] hasil = new String[jumlahBaris][4];
            
            // 1. Aktivasi driver database
            Class.forName(dbDriver);
            
            // 2. Connect ke database
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            
            String sql2 = "select * from retailer";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            
            int counter = 0;
            String[] sementara = new String[4];
            while(rs.next()) {
                //hasil = rs.getString("username");
                sementara[0] = rs.getString("id_retailer");
                sementara[1] = rs.getString("nama_retailer");
                sementara[2] = rs.getString("alamat");
                sementara[3] = rs.getString("telepon");
                
                //tambahin ke hasil
                hasil[counter][0] = sementara[0];
                hasil[counter][1] = sementara[1];
                hasil[counter][2] = sementara[2];
                hasil[counter][3] = sementara[3];    
                counter++;
            }
            
            rs.close();
            conn.close();
            return hasil;
            
            //return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String[][] getBarang() throws SQLException{
        
        try {
            // 0. Variabel untuk koneksi
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://localhost/gudangjava";
            String dbUser = "root";
            String dbPass = "";
            
            //jumlah baris di tabel retailer
            int jumlahBaris = hitungBarisTabel("barang");
            String[][] hasil = new String[jumlahBaris][5];
            
            // 1. Aktivasi driver database
            Class.forName(dbDriver);
            
            // 2. Connect ke database
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            
            String sql2 = "select * from barang";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            
            int counter = 0;
            String[] sementara = new String[5];
            while(rs.next()) {
                //hasil = rs.getString("username");
                sementara[0] = Integer.toString(rs.getInt("id_barang"));
                sementara[1] = rs.getString("nama_barang");
                sementara[2] = rs.getString("jenis");
                sementara[3] = Integer.toString(rs.getInt("quantity"));
                sementara[4] = Integer.toString(rs.getInt("harga"));
                
                //tambahin ke hasil
                hasil[counter][0] = sementara[0];
                hasil[counter][1] = sementara[1];
                hasil[counter][2] = sementara[2];
                hasil[counter][3] = sementara[3];
                hasil[counter][4] = sementara[4];
                counter++;
            }
            
            rs.close();
            conn.close();
            return hasil;
            
            //return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int hitungBarisTabel(String namatabel) throws SQLException{
        int hasilHitung = 0;
        try {
            // 0. Variabel untuk koneksi
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://localhost/gudangjava";
            String dbUser = "root";
            String dbPass = "";
            String hasil = "";
            
            // 1. Aktivasi driver database
            Class.forName(dbDriver);
            
            // 2. Connect ke database
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            
            String sql2 = "select COUNT(*) AS hasil from " + namatabel;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql2);
                    
                    while(rs.next()) {
                        //hasil = rs.getString("username");
                        hasilHitung = Integer.parseInt(rs.getString("hasil"));
                    }
                    
                    rs.close();
                    conn.close();
                    return hasilHitung;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    } 
}


// tambah telepon

 /*public String masukTelepon(String telepon) throws ClassNotFoundException, SQLException{
        // 0. Variabel untuk koneksi
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/gudangjava";
        String dbUser = "root";
        String dbPass = "";
        String hasilMasuk = "";

        // 1. Aktivasi driver database
        Class.forName(dbDriver);

        // 2. Connect ke database
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        String query = "insert into retailer telepon values (?)";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, telepon);
        //eksekusi
        int hasil = ps.executeUpdate();
        
        if(hasil > 0){
            hasilMasuk = "berhasil";
        }
        else{
            hasilMasuk = "gagal";
        }
        
        return hasilMasuk;
    } */

