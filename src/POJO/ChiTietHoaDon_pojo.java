/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author ThinhDN
 */
public class ChiTietHoaDon_pojo {
    private String maHD;
    private String maS;
    private int soLuong;

    public ChiTietHoaDon_pojo(String maHD, String maS, int soLuong) {
        this.maHD = maHD;
        this.maS = maS;
        this.soLuong = soLuong;
    }

    public ChiTietHoaDon_pojo() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaS() {
        return maS;
    }

    public void setMaS(String maS) {
        this.maS = maS;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
