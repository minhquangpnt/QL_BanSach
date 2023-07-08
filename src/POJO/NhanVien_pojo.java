/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author ThinhDN
 */
public class NhanVien_pojo {
    private String maNV;
    private String tenNV;
    private String cccd;
    private String sdtNV;
    private String thamNien;
    private String diaChi;
    private String email;
    private String chucVu;
    private String matKhau;

    public NhanVien_pojo(String maNV, String tenNV, String cccd, String sdtNV, String thamNien, String diaChi, String email, String chucVu, String matKhau) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.cccd = cccd;
        this.sdtNV = sdtNV;
        this.thamNien = thamNien;
        this.diaChi = diaChi;
        this.email = email;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
    }

    public NhanVien_pojo() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getThamNien() {
        return thamNien;
    }

    public void setThamNien(String thamNien) {
        this.thamNien = thamNien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


    
}
