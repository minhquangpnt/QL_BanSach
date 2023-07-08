/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import POJO.KhachHang_pojo;
import java.sql.Connection;

/**
 *
 * @author ThinhDN
 */
public class KhachHang_dao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public static ArrayList<KhachHang_pojo> layDanhSachKhachHang() {
        ArrayList<KhachHang_pojo> dsKH = new ArrayList<>();
        try {
            String sql = "Select * from KHACHHANG";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                KhachHang_pojo hd = new KhachHang_pojo();
                hd.setMaKH(rs.getString("maKH"));
                hd.setTenKH(rs.getString("tenKH"));
                hd.setSdtKH(rs.getString("SDT"));
                hd.setEmail(rs.getString("email"));
                hd.setSoDiem(rs.getString("soDiem"));
                dsKH.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsKH;
    }

    public static ArrayList<KhachHang_pojo> layDanhSachKhachHangTheoMa(String ma) {
        ArrayList<KhachHang_pojo> dsKH = new ArrayList<>();
        try {
            String sql = "Select * from KHACHHANG where maKH = '" + ma + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                KhachHang_pojo hd = new KhachHang_pojo();
                hd.setMaKH(rs.getString("maKH"));
                hd.setTenKH(rs.getString("tenKH"));
                hd.setSdtKH(rs.getString("SDT"));
                hd.setEmail(rs.getString("email"));
                hd.setSoDiem(rs.getString("soDiem"));
                dsKH.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsKH;
    }

    public static boolean themKhachHang(KhachHang_pojo kh) {
        boolean kq = false;
        String sql = String.format("Insert into KHACHHANG(maKH,tenKH,SDT,email,soDiem) Values ('%s',N'%s','%s','%s','%s')", kh.getMaKH(), kh.getTenKH(), kh.getSdtKH(), kh.getEmail(), kh.getSoDiem());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean xoaKhachHang(String makh) {
        boolean kq = false;
        String sql = String.format("Delete from KHACHHANG where maKH='%s'", makh);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static KhachHang_pojo layKhachHang(String maKH) {
        KhachHang_pojo kh = null;
        try {
            String sql = "Select * from KHACHHANG where maKH = '" + maKH + "'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                kh = new KhachHang_pojo();
                kh.setMaKH(rs.getString("maKH"));
                kh.setTenKH(rs.getString("tenKH"));
                kh.setSdtKH(rs.getString("SDT"));
                kh.setEmail(rs.getString("email"));
                kh.setSoDiem(rs.getString("soDIem"));
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

    public static boolean capNhatKhachHang(KhachHang_pojo kh) {
        boolean kq = false;
        String sql = String.format("Update KHACHHANG set tenKH ='%s' , SDT = '%s',"
                + "email='%s',soDiem='%s'Where maKH = '%S'", kh.getTenKH(), kh.getSdtKH(), kh.getEmail(), kh.getSoDiem(), kh.getMaKH());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean ktraMaKH(String maKH) {
        try {
            String sql = "Select * from KHACHHANG where maKH = '" + maKH + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
