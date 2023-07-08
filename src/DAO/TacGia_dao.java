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
import POJO.TacGia_pojo;
import POJO.TheLoai_pojo;

/**
 *
 * @author ThinhDN
 */
public class TacGia_dao {
    public static ArrayList<TacGia_pojo> layDanhSachTacGia() {
        ArrayList<TacGia_pojo> dsTG = new ArrayList<>();
        try {
            String sql = "Select * from TACGIA";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                TacGia_pojo hd = new TacGia_pojo();
                hd.setMaTG(rs.getString("maTG"));
                hd.setTenTG(rs.getString("tenTG"));
                dsTG.add(hd);
            }
            ds.close();
        } catch (SQLException ex) {
            Logger.getLogger(TacGia_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTG;
    }
    public static boolean themTacGia(TacGia_pojo tg) {
        boolean kq = false;
        String sql = String.format("Insert into TACGIA(maTG,tenTG) Values ('%s',N'%s')", tg.getMaTG(),tg.getTenTG());
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    public static boolean xoaTacGia(String matg) {
        boolean kq = false;
        String sql = String.format("Delete from TACGIA where maTG='%s'", matg);
        SQLServerDataProvider ds = new SQLServerDataProvider();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static TacGia_pojo layTacGia(int matg) {
        TacGia_pojo tg = null;
        try {
            String sql = "Select * from TACGIA where maTG = " + matg;
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                tg = new TacGia_pojo();
                tg.setMaTG(rs.getString("maTG"));
                tg.setTenTG(rs.getString("tenTG"));
            }
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tg;
    }

    public static boolean capNhatTacGia(TacGia_pojo tg) {
        boolean kq = false;
        String sql = String.format("Update TACGIA set tenTG ='%s' Where maTG = '%S'", tg.getTenTG(), tg.getMaTG());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean ktraMaTG(String maTG) {
        try {
            String sql = "Select * from TACGIA where maTG = '" + maTG + "'";
            SQLServerDataProvider ds = new SQLServerDataProvider();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGia_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
