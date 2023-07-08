/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.DoanhThuNV_pojo;
import POJO.HoaDon_pojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class DoanhThuNV_dao {

    public static ArrayList<DoanhThuNV_pojo> layDanhSachDoanhThuNV() {
        ArrayList<DoanhThuNV_pojo> dsDT = new ArrayList<>();
        try {
            String sql = "select * from dbo.tk_DoanhThuNV()";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                DoanhThuNV_pojo dt = new DoanhThuNV_pojo();
                dt.setMaNV(rs.getString("maNV"));
                dt.setTenNV(rs.getString("tenNV"));
                dt.setDoanhThu(rs.getFloat("Doanh thu"));
                dsDT.add(dt);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuNV_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsDT;
    }

    public static ArrayList<DoanhThuNV_pojo> layDanhSachDoanhThuNVTheoNgay(String fromDate, String toDate) {
        ArrayList<DoanhThuNV_pojo> dsDT = new ArrayList<>();
        try {
            String sql = "select * from dbo.tk_DoanhThuNVTheoNgay('" + fromDate + "','" + toDate + "')";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                DoanhThuNV_pojo dt = new DoanhThuNV_pojo();
                dt.setMaNV(rs.getString("maNV"));
                dt.setTenNV(rs.getString("tenNV"));
                dt.setDoanhThu(rs.getFloat("Doanh thu"));
                dsDT.add(dt);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuNV_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsDT;
    }
}
