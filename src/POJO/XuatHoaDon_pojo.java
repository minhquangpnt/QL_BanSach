/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.Date;

/**
 *
 * @author PC
 */
public class XuatHoaDon_pojo {

    private String maHD, tenSach;
    private int soLuongMua;
    private float donGia, tongTien;
    private Date ngayLap;

    public XuatHoaDon_pojo(String maHD, String tenSach, int soLuongMua, float donGia, float tongTien, Date ngayLap) {
        this.maHD = maHD;
        this.tenSach = tenSach;
        this.soLuongMua = soLuongMua;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
    }

    public XuatHoaDon_pojo() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    
}
