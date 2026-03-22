/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimon.duan1.entity;

/**
 *
 * @author honga
 */
public class NhanVien1 {
    private int idnhanvien;
    private String manhanvien;
    private String hovaten;
    private String email;
    private String sodienthoai;
    private String matkhau;
    private int trangthai;

    public NhanVien1() {}

    public NhanVien1(int idnhanvien, String manhanvien, String hovaten,
                    String email, String sodienthoai, String matkhau, int trangthai) {
        this.idnhanvien = idnhanvien;
        this.manhanvien = manhanvien;
        this.hovaten = hovaten;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.matkhau = matkhau;
        this.trangthai = trangthai;
    }

    public int getIdnhanvien() { return idnhanvien; }
    public void setIdnhanvien(int idnhanvien) { this.idnhanvien = idnhanvien; }

    public String getManhanvien() { return manhanvien; }
    public void setManhanvien(String manhanvien) { this.manhanvien = manhanvien; }

    public String getHovaten() { return hovaten; }
    public void setHovaten(String hovaten) { this.hovaten = hovaten; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSodienthoai() { return sodienthoai; }
    public void setSodienthoai(String sodienthoai) { this.sodienthoai = sodienthoai; }

    public String getMatkhau() { return matkhau; }
    public void setMatkhau(String matkhau) { this.matkhau = matkhau; }

    public int getTrangthai() { return trangthai; }
    public void setTrangthai(int trangthai) { this.trangthai = trangthai; }
}
