package thicuoimon.duan1.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ThongKe {

    private String tenSanPham;
    private String maHoaDon;
    private int soLuong;
    private BigDecimal tongTienChuaGiam;
    private Date ngayThanhToan;
    private BigDecimal giamGia;
    private BigDecimal giaBan;
    private BigDecimal tongTienSauGiam;
    private BigDecimal tienLai;
    private Integer thang;
    private Integer nam;

    public ThongKe() {
    }

    public ThongKe(String tenSanPham, String maHoaDon, int soLuong,
                   BigDecimal tongTienChuaGiam, Date ngayThanhToan,
                   BigDecimal giamGia, BigDecimal giaBan,
                   BigDecimal tongTienSauGiam, BigDecimal tienLai,
                   Integer thang, Integer nam) {
        this.tenSanPham = tenSanPham;
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
        this.tongTienChuaGiam = tongTienChuaGiam;
        this.ngayThanhToan = ngayThanhToan;
        this.giamGia = giamGia;
        this.giaBan = giaBan;
        this.tongTienSauGiam = tongTienSauGiam;
        this.tienLai = tienLai;
        this.thang = thang;
        this.nam = nam;
    }

    // Getter & Setter
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public BigDecimal getTongTienChuaGiam() { return tongTienChuaGiam; }
    public void setTongTienChuaGiam(BigDecimal tongTienChuaGiam) { this.tongTienChuaGiam = tongTienChuaGiam; }

    public Date getNgayThanhToan() { return ngayThanhToan; }
    public void setNgayThanhToan(Date ngayThanhToan) { this.ngayThanhToan = ngayThanhToan; }

    public BigDecimal getGiamGia() { return giamGia; }
    public void setGiamGia(BigDecimal giamGia) { this.giamGia = giamGia; }

    public BigDecimal getGiaBan() { return giaBan; }
    public void setGiaBan(BigDecimal giaBan) { this.giaBan = giaBan; }

    public BigDecimal getTongTienSauGiam() { return tongTienSauGiam; }
    public void setTongTienSauGiam(BigDecimal tongTienSauGiam) { this.tongTienSauGiam = tongTienSauGiam; }

    public BigDecimal getTienLai() { return tienLai; }
    public void setTienLai(BigDecimal tienLai) { this.tienLai = tienLai; }

    public Integer getThang() { return thang; }
    public void setThang(Integer thang) { this.thang = thang; }

    public Integer getNam() { return nam; }
    public void setNam(Integer nam) { this.nam = nam; }
}