/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author ThinhDN
 */
public class Sach_pojo {
    private String maS;
    private String tenS;
    private String donGia;
    private String slCon;
    private String maTG;
    private String maTL;

    public Sach_pojo(String maS, String tenS, String donGia, String slCon, String maTG, String maTL) {
        this.maS = maS;
        this.tenS = tenS;
        this.donGia = donGia;
        this.slCon = slCon;
        this.maTG = maTG;
        this.maTL = maTL;
    }

    public Sach_pojo() {
    }

    public String getMaS() {
        return maS;
    }

    public void setMaS(String maS) {
        this.maS = maS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getSlCon() {
        return slCon;
    }

    public void setSlCon(String slCon) {
        this.slCon = slCon;
    }

    public String getMaTG() {
        return maTG;
    }

    public void setMaTG(String maTG) {
        this.maTG = maTG;
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }
    
}
