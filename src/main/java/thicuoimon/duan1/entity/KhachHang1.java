/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimon.duan1.entity;
import java.sql.Date;
/**
 *
 * @author honga
 */
public class KhachHang1 {
    private int idkhachhang;
    private String makhachhang;
    private String tenkhachhang;
    private String sodienthoai;
    private String email;
    private String gioitinh;
    private Date ngaysinh;
    private int trangthai;

    public KhachHang1() {
    }

    public KhachHang1(int idkhachhang, String makhachhang, String tenkhachhang, String sodienthoai, String email, String gioitinh, Date ngaysinh, int trangthai) {
        this.idkhachhang = idkhachhang;
        this.makhachhang = makhachhang;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.trangthai = trangthai;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

}
