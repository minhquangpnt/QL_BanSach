/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.TongThuKH_pojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class TongThuKH_dao {

    public static ArrayList<TongThuKH_pojo> layDanhSachTongThuKH() {
        ArrayList<TongThuKH_pojo> dsTT = new ArrayList<>();
        try {
            String sql = "select * from dbo.tk_TongThuKH()";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                TongThuKH_pojo tt = new TongThuKH_pojo();
                tt.setMaKH(rs.getString("maKH"));
                tt.setTenKH(rs.getString("tenKH"));
                tt.setTongThu(rs.getFloat("Tong thu"));
                dsTT.add(tt);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(TongThuKH_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTT;
    }

    public static ArrayList<TongThuKH_pojo> layDanhSachTongThuKHTheoNgay(String fromDate, String toDate) {
        ArrayList<TongThuKH_pojo> dsTT = new ArrayList<>();
        try {
            String sql = "select * from dbo.tk_TongThuKHTheoNgay('" + fromDate + "','" + toDate + "')";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                TongThuKH_pojo tt = new TongThuKH_pojo();
                tt.setMaKH(rs.getString("maKH"));
                tt.setTenKH(rs.getString("tenKH"));
                tt.setTongThu(rs.getFloat("Tong thu"));
                dsTT.add(tt);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(TongThuKH_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTT;
    }
}
