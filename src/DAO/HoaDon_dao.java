/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Const;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import POJO.HoaDon_pojo;
import POJO.XuatHoaDon_pojo;
import java.sql.CallableStatement;

/**
 *
 * @author ThinhDN
 */
public class HoaDon_dao {

    public static ArrayList<HoaDon_pojo> layDanhSachHoaDon() {
        ArrayList<HoaDon_pojo> dsHD = new ArrayList<>();
        try {
            String sql = "Select * from HOADON";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                HoaDon_pojo hd = new HoaDon_pojo();
                hd.setMaHD(rs.getString("maHD"));
                hd.setNgayLap(rs.getDate("ngayLap"));
                hd.setTongTien(rs.getFloat("tongTien"));
                hd.setMaKH(rs.getString("maKH"));
                hd.setMaNV(rs.getString("maNV"));
                hd.setTinhTrang(rs.getString("tinhTrang"));
                dsHD.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsHD;
    }

    public static boolean ktraMaHD(String maHD) {
        try {
            String sql = "Select * from HOADON where maHD = '" + maHD + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean themHD(String maHD, String maKH) {
        String maNV = Const.strUser;
        boolean kq = false;
        String sql = String.format("Insert into HOADON(maHD,maNV,maKH) values('%s','%s','%s')", maHD, maNV, maKH);
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean suaHD(String maHD, String maKH, String tinhTrang) {
        String maNV = Const.strUser;
        boolean kq = false;
        String sql = String.format("update HOADON set maKH = '" + maKH + "' , tinhTrang = N'" + tinhTrang + "' , maNV = '" + maNV + "' where maHD = '" + maHD + "'");
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean xoaHD(String maHD) {
        boolean kq = false;
        String sql = String.format("DELETE FROM HOADON where maHD = '" + maHD + "'");
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static ArrayList<XuatHoaDon_pojo> xuatHoaDon(String maHD) {
        ArrayList<XuatHoaDon_pojo> dsXHD = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            provider.open();
            CallableStatement cs = provider.connection.prepareCall("{call xuat_HD(?)}");
            cs.setString(1, maHD);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                XuatHoaDon_pojo hd = new XuatHoaDon_pojo();
                hd.setMaHD(rs.getString("maHD"));
                hd.setNgayLap(rs.getDate("ngayLap"));
                hd.setTongTien(rs.getFloat("tongTien"));
                hd.setTenSach(rs.getString("tenSach"));
                hd.setSoLuongMua(rs.getInt("soLuongMua"));
                hd.setDonGia(rs.getFloat("donGia"));
                dsXHD.add(hd);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsXHD;
    }
}
