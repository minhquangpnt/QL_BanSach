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
import POJO.ChiTietHoaDon_pojo;
import POJO.HoaDon_pojo;

/**
 *
 * @author ThinhDN
 */
public class ChiTietHoaDon_dao {
    public static ArrayList<ChiTietHoaDon_pojo> layDanhSachChiTiet(String maHD) {
        ArrayList<ChiTietHoaDon_pojo> dsCT = new ArrayList<>();
        try {
            String sql = "Select * from CHITIETHOADON where maHD = '"+maHD+"'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                ChiTietHoaDon_pojo hd = new ChiTietHoaDon_pojo();
                hd.setMaHD(rs.getString("maHD"));
                hd.setMaS(rs.getString("maSach"));
                hd.setSoLuong(rs.getInt("soLuongMua"));
                dsCT.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsCT;
    }
    public static boolean ktraCTHDS(String maHD, String maS) {
        try {
            String sql = "Select * from CHITIETHOADON where maHD = '" + maHD + "' and maSach = '"+maS+"'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean themCTHD(ChiTietHoaDon_pojo ct){
        boolean kq = false;
        String sql = String.format("Insert into CHITIETHOADON values('%s','%s',%d)", ct.getMaHD(), ct.getMaS(),ct.getSoLuong());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    public static boolean xoaCTHD(String maHD, String maS){
        boolean kq = false;
        String sql = String.format("DELETE FROM CHITIETHOADON where maHD = '"+maHD+"' and maSach ='"+maS+"'");
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    public static boolean suaCTHD(ChiTietHoaDon_pojo ct){
        boolean kq = false;
        String sql = String.format("update CHITIETHOADON set soLuongMua = "+ct.getSoLuong()+" where maHD = '"+ct.getMaHD()+"' and maSach ='"+ct.getMaS()+"'");
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
