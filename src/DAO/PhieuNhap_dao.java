/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Const;
import POJO.HoaDon_pojo;
import POJO.PhieuNhap_pojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThinhDN
 */
public class PhieuNhap_dao {

    public static ArrayList<PhieuNhap_pojo> layDanhSachPhieuNhap() {
        ArrayList<PhieuNhap_pojo> dsPN = new ArrayList<>();
        try {
            String sql = "Select * from PHIEUNHAP";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                PhieuNhap_pojo hd = new PhieuNhap_pojo();
                hd.setMaPhieuNhap(rs.getString("maPhieuNhap"));
                hd.setNgayNhap(rs.getDate("ngayNhap"));
                hd.setTongTien(rs.getFloat("tongTienNhap"));
                hd.setMaNV(rs.getString("maNV"));
                dsPN.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhap_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsPN;
    }

    public static boolean ktraMaPN(String maPN) {
        try {
            String sql = "Select * from PHIEUNHAP where maPhieuNhap = '" + maPN + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhap_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean themPN(String ma, String ngay) {
        String maNV = Const.strUser;
        boolean kq = false;
        String sql = String.format("Insert into PHIEUNHAP(maPhieuNhap, ngayNhap, maNV) values('" + ma + "','" + ngay + "','" + maNV + "')");
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean suaPN(String ma, String ngay) {
        String maNV = Const.strUser;
        boolean kq = false;
        String sql = String.format("update PHIEUNHAP set  ngayNhap =  '" + ngay + "', maNV = '" + maNV + "' where maPhieuNhap = '" + ma + "'");
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean xoaPN(String maPN) {
        boolean kq = false;
        String sql = String.format("DELETE FROM PHIEUNHAP where maPhieuNhap = '" + maPN + "'");
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }
}
