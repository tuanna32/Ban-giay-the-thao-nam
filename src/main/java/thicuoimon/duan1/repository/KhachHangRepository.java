/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimon.duan1.repository;

import java.sql.ResultSet;
import java.util.List;
import thicuoimon.duan1.entity.KhachHang1;
import thicuoimon.duan1.util.XJdbc;
import thicuoimon.duan1.util.XQuery;

/**
 *
 * @author honga
 */
public class KhachHangRepository {

    public List<KhachHang1> getAll() {
        String sql = "SELECT * FROM khach_hang";
        return XQuery.getBeanList(KhachHang1.class, sql);
    }

    // Lấy dữ liệu theo trang (Sử dụng OFFSET FETCH của SQL Server 2012+)
    public List<KhachHang1> getPage(int page, int size) {
        String sql = "SELECT * FROM khach_hang ORDER BY idkhachhang OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        return XQuery.getBeanList(KhachHang1.class, sql, (page - 1) * size, size);
    }

// Đếm tổng số bản ghi để tính toán số trang
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM khach_hang";
        try (java.sql.ResultSet rs = thicuoimon.duan1.util.XJdbc.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insert(KhachHang1 kh) {
        String sql = "INSERT INTO khach_hang (makhachhang, tenkhachhang, sodienthoai, email, gioitinh, ngaysinh, trangthai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        XJdbc.executeUpdate(sql, kh.getMakhachhang(), kh.getTenkhachhang(), kh.getSodienthoai(), kh.getEmail(), kh.getGioitinh(), kh.getNgaysinh(), kh.getTrangthai());
    }

    // Cập nhật
    public void update(KhachHang1 kh) {
        String sql = "UPDATE khach_hang SET makhachhang=?, tenkhachhang=?, sodienthoai=?, email=?, gioitinh=?, ngaysinh=?, trangthai=? WHERE idkhachhang=?";
        XJdbc.executeUpdate(sql, kh.getMakhachhang(), kh.getTenkhachhang(), kh.getSodienthoai(), kh.getEmail(), kh.getGioitinh(), kh.getNgaysinh(), kh.getTrangthai(), kh.getIdkhachhang());
    }

    // Xóa (Nên dùng xóa mềm - đổi trạng thái)
    public void delete(int id) {
        String sql = "DELETE FROM khach_hang WHERE idkhachhang=?";
        XJdbc.executeUpdate(sql, id);
    }

    // Tìm kiếm
    public List<KhachHang1> search(String keyword) {
        String sql = "SELECT * FROM khach_hang WHERE tenkhachhang LIKE ? OR sodienthoai LIKE ?";
        return XQuery.getBeanList(KhachHang1.class, sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public thicuoimon.duan1.entity.KhachHang1 getById(int id) {
        String sql = "SELECT * FROM khach_hang WHERE idkhachhang = ?";
        List<thicuoimon.duan1.entity.KhachHang1> list = XQuery.getBeanList(thicuoimon.duan1.entity.KhachHang1.class, sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

}
