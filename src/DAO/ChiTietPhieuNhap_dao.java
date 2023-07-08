/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.ChiTietPhieuNhap_pojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThinhDN
 */
public class ChiTietPhieuNhap_dao {

    public static ArrayList<ChiTietPhieuNhap_pojo> layDanhSachCTPhieuNhap(String maPN) {
        ArrayList<ChiTietPhieuNhap_pojo> dsCT = new ArrayList<>();
        try {
            String sql = "Select * from CHITIETPHIEUNHAP where maPhieuNhap = '" + maPN + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                ChiTietPhieuNhap_pojo hd = new ChiTietPhieuNhap_pojo();
                hd.setMaPhieuNhap(rs.getString("maPhieuNhap"));
                hd.setMaSach(rs.getString("maSach"));
                hd.setSoLuong(rs.getInt("soLuongNhap"));
                dsCT.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhap_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsCT;
    }

    public static boolean ktraCTPN(String maPN, String maS) {
        try {
            String sql = "Select * from CHITIETPHIEUNHAP where maPhieuNhap = '" + maPN + "' and maSach = '" + maS + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhap_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean themCTPN(ChiTietPhieuNhap_pojo ct) {
        boolean kq = false;
        String sql = String.format("Insert into CHITIETPHIEUNHAP values('%s','%s',%d)", ct.getMaPhieuNhap(), ct.getMaSach(), ct.getSoLuong());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean xoaCTPN(String maPN, String maS) {
        boolean kq = false;
        String sql = String.format("DELETE FROM CHITIETPHIEUNHAP where maPhieuNhap = '" + maPN + "' and maSach ='" + maS + "'");
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean suaCTPN(ChiTietPhieuNhap_pojo ct) {
        boolean kq = false;
        String sql = String.format("update CHITIETPHIEUNHAP set soLuongNhap = " + ct.getSoLuong() + " where maPhieuNhap = '" + ct.getMaPhieuNhap()+ "' and maSach ='" + ct.getMaSach()+ "'");
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
}
