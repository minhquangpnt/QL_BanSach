/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import POJO.Sach_pojo;

/**
 *
 * @author ThinhDN
 */
public class Sach_dao {

    public static ArrayList<Sach_pojo> layDanhSachSach() {
        ArrayList<Sach_pojo> dsS = new ArrayList<>();
        try {
            String sql = "Select * from SACH";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Sach_pojo hd = new Sach_pojo();
                hd.setMaS(rs.getString("maSach"));
                hd.setTenS(rs.getString("tenSach"));
                hd.setDonGia(rs.getString("donGia"));
                hd.setSlCon(rs.getString("soLuongCon"));
                hd.setMaTG(rs.getString("maTG"));
                hd.setMaTL(rs.getString("maTL"));
                dsS.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsS;
    }

    public static boolean themSach(Sach_pojo s) {
        boolean kq = false;
        String sql = String.format("Insert into Sach (maSach,tenSach,donGia,soLuongCon,maTG,maTL"
                + ") Values ('%s','%s','%s','%s','%s','%s')", s.getMaS(), s.getTenS(), s.getDonGia(), s.getSlCon(), s.getMaTG(), s.getMaTL());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean xoaSach(String maS) {
        boolean kq = false;
        String sql = String.format("Delete from SACH where maSach='%s'", maS);
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static Sach_pojo laySach(int maS) {
        Sach_pojo s = null;
        try {
            String sql = "Select * from Sach where maSach = " + maS;
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                s = new Sach_pojo();
                s.setMaS(rs.getString("maSach"));
                s.setTenS(rs.getString("tenSach"));
                s.setMaTL(rs.getString("maTL"));
                s.setMaTG(rs.getString("maTG"));
                s.setDonGia(rs.getString("donGia"));
                s.setSlCon(rs.getString("soLuongCon"));
            }
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean capNhatSach(Sach_pojo s) {
        boolean kq = false;
        String sql = String.format("Update Sach set tenSach ='%s' , maTL = '%s',"
                + "maTG='%s',soLuongCon='%s',donGia ='%s'"
                + "Where maNV = '%S'", s.getTenS(), s.getMaTL(), s.getMaTG(), s.getSlCon(), s.getDonGia());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean ktraMaS(String maS) {
        try {
            String sql = "Select * from SACH where maSach = '" + maS + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ArrayList<Sach_pojo> layDanhSachSachTheoTL(String mtl) {
        ArrayList<Sach_pojo> dsS = new ArrayList<>();
        try {
            String sql = "Select * from SACH where maTL ='" + mtl + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Sach_pojo hd = new Sach_pojo();
                hd.setMaS(rs.getString("maSach"));
                hd.setTenS(rs.getString("tenSach"));
                hd.setDonGia(rs.getString("donGia"));
                hd.setSlCon(rs.getString("soLuongCon"));
                hd.setMaTG(rs.getString("maTG"));
                hd.setMaTL(rs.getString("maTL"));
                dsS.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sach_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsS;
    }

    public static ArrayList<Sach_pojo> layDanhSachSachTheoTG(String mtg) {
        ArrayList<Sach_pojo> dsS = new ArrayList<>();
        try {
            String sql = "Select * from SACH where maTG ='" + mtg + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Sach_pojo hd = new Sach_pojo();
                hd.setMaS(rs.getString("maSach"));
                hd.setTenS(rs.getString("tenSach"));
                hd.setDonGia(rs.getString("donGia"));
                hd.setSlCon(rs.getString("soLuongCon"));
                hd.setMaTG(rs.getString("maTG"));
                hd.setMaTL(rs.getString("maTL"));
                dsS.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sach_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsS;
    }

    public static ArrayList<Sach_pojo> layDanhSachSachTheoTen(String ten) {
        ArrayList<Sach_pojo> dsS = new ArrayList<>();
        try {
            String sql = "Select * from SACH where tenSach like '%" + ten + "%'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Sach_pojo hd = new Sach_pojo();
                hd.setMaS(rs.getString("maSach"));
                hd.setTenS(rs.getString("tenSach"));
                hd.setDonGia(rs.getString("donGia"));
                hd.setSlCon(rs.getString("soLuongCon"));
                hd.setMaTG(rs.getString("maTG"));
                hd.setMaTL(rs.getString("maTL"));
                dsS.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sach_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsS;
    }

    public static int laySLSach(String ma) {
        int sl = 0;
        try {
            String sql = "Select soLuongCon from SACH where maSach = '" + ma + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                sl = rs.getInt("soLuongCon");
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sach_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
}
