/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author LENOVO
 */
public class DoanhThuNV_pojo {
    private String maNV, tenNV;
    private float doanhThu;

    public DoanhThuNV_pojo(String maNV, String tenNV, float doanhThu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.doanhThu = doanhThu;
    }

    public DoanhThuNV_pojo() {
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

    public float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
    }
}
