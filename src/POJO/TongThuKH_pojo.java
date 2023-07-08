/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author PC
 */
public class TongThuKH_pojo {

    String maKH;
    String tenKH;
    float tongThu;

    public TongThuKH_pojo(String maKH, String tenKH, float tongThu) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.tongThu = tongThu;
    }

    public TongThuKH_pojo() {
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public float getTongThu() {
        return tongThu;
    }

    public void setTongThu(float tongThu) {
        this.tongThu = tongThu;
    }
}
