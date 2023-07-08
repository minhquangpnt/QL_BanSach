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
import POJO.TheLoai_pojo;

/**
 *
 * @author ThinhDN
 */
public class TheLoai_dao {

    public static ArrayList<TheLoai_pojo> layDanhSachTheLoai() {
        ArrayList<TheLoai_pojo> dsTL = new ArrayList<>();
        try {
            String sql = "Select * from THELOAI";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                TheLoai_pojo hd = new TheLoai_pojo();
                hd.setMaTL(rs.getString("maTL"));
                hd.setTenTL(rs.getString("tenTL"));
                dsTL.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoai_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTL;
    }
    public static boolean themTheLoai(TheLoai_pojo tl) {
        boolean kq = false;
        String sql = String.format("Insert into THELOAI(maTL,tenTL) Values ('%s',N'%s')", tl.getMaTL(),tl.getTenTL());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    public static boolean xoaTheLoai(String matTl) {
        boolean kq = false;
        String sql = String.format("Delete from THELOAI where maTL='%s'", matTl);
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static TheLoai_pojo layTheLoai(int matTl) {
        TheLoai_pojo tl = null;
        try {
            String sql = "Select * from THELOAI where maTL = " + matTl;
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                tl = new TheLoai_pojo();
                tl.setMaTL(rs.getString("maTL"));
                tl.setTenTL(rs.getString("tenTL"));
            }
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tl;
    }

    public static boolean capNhatTheLoai(TheLoai_pojo tl) {
        boolean kq = false;
        String sql = String.format("Update THELOAI set tenTL =N'%s' Where maTL = '%s'", tl.getTenTL(), tl.getMaTL());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean ktraMaTl(String maTL) {
        try {
            String sql = "Select * from THELOAI where maTL = '" + maTL + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoai_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
