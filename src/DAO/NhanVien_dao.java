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
import POJO.NhanVien_pojo;

/**
 *
 * @author ThinhDN
 */
public class NhanVien_dao {

    public static ArrayList<NhanVien_pojo> layDanhSachNhanVien() {
        ArrayList<NhanVien_pojo> dsNV = new ArrayList<>();
        try {
            String sql = "Select * from NHANVIEN";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                NhanVien_pojo hd = new NhanVien_pojo();
                hd.setMaNV(rs.getString("maNV"));
                hd.setTenNV(rs.getString("tenNV"));
                hd.setCccd(rs.getString("CCCD"));
                hd.setSdtNV(rs.getString("SDT"));
                hd.setThamNien(rs.getString("thamNien"));
                hd.setDiaChi(rs.getString("diaChi"));
                hd.setEmail(rs.getString("email"));
                hd.setChucVu(rs.getString("chucVu"));
                hd.setMatKhau(rs.getString("matKhau"));
                dsNV.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNV;
    }

    public static boolean themNhanVien(NhanVien_pojo nv) {
        boolean kq = false;
        String sql = String.format("Insert into NHANVIEN (maNV,tenNV,CCCD,SDT,thamNien,diaChi,email,chucVu,"
                + "matKhau) Values ('%s',N'%s','%s','%s','%s','%s','%s','%s','%s')", nv.getMaNV(), nv.getTenNV(), nv.getCccd(), nv.getSdtNV(), nv.getThamNien(), nv.getDiaChi(), nv.getEmail(), nv.getChucVu(), nv.getMatKhau());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean xoaNhanVien(String maNV) {
        boolean kq = false;
        String sql = String.format("Delete from NHANVIEN where maNV='%s'", maNV);
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static NhanVien_pojo layNhanVien(int maNV) {
        NhanVien_pojo nv = null;
        try {
            String sql = "Select * from NHANVIEN where maNV = " + maNV;
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                nv = new NhanVien_pojo();
                nv.setMaNV(rs.getString("maNV"));
                nv.setTenNV(rs.getString("tenNV"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setSdtNV(rs.getString("SDT"));
                nv.setThamNien(rs.getString("thamNien"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setEmail(rs.getString("email"));
                nv.setChucVu(rs.getString("chucVu"));
                nv.setMatKhau(rs.getString("matKhau"));
            }
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public static boolean capNhatNhanVien(NhanVien_pojo nv) {
        boolean kq = false;
        String sql = String.format("Update NHANVIEN set tenNV ='%s' , CCCD = '%s',"
                + "SDT='%s',thamNien='%s',diaChi ='%s',email='%s',chucVu='%s',matKhau='%s'"
                + "Where maNV = '%S'", nv.getTenNV(), nv.getCccd(), nv.getSdtNV(), nv.getThamNien(), nv.getDiaChi(), nv.getEmail(), nv.getChucVu(), nv.getMatKhau(), nv.getMaNV());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean ktraMaNV(String maNV) {
        try {
            String sql = "Select * from NHANVIEN where maNV = '" + maNV + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
