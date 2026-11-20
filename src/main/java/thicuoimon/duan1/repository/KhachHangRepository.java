/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimon.duan1.repository;

import java.sql.ResultSet;
import java.util.List;
import thicuoimon.duan1.entity.khachhang;

import thicuoimon.duan1.util.XJdbc;
import thicuoimon.duan1.util.XQuery;


/**
 *
 * @author honga
 */
public class KhachHangRepository {

    public List<khachhang> getAll() {
        String sql = "SELECT * FROM khach_hang";
        return XQuery.getBeanList(khachhang.class, sql);
    }

    // Lấy dữ liệu theo trang (Sử dụng OFFSET FETCH của SQL Server 2012+)
    public List<khachhang> getPage(int page, int size) {
        String sql = "SELECT * FROM khach_hang ORDER BY idkhachhang OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        return XQuery.getBeanList(khachhang.class, sql, (page - 1) * size, size);
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

    public void insert(khachhang kh) {
        String sql = "INSERT INTO khach_hang (makhachhang, tenkhachhang, sodienthoai, email, ngaysinh, trangthai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        XJdbc.executeUpdate(sql, kh.getMakhachhang(), kh.getHovaten(), kh.getSodienthoai(), kh.getEmail(), kh.getNgaysinh(), kh.isTrangthaian());
    }

    // Cập nhật
    public void update(khachhang kh) {
        String sql = "UPDATE khach_hang SET makhachhang=?, tenkhachhang=?, sodienthoai=?, email=?,  ngaysinh=?, trangthai=? WHERE idkhachhang=?";
        XJdbc.executeUpdate(sql, kh.getMakhachhang(), kh.getHovaten(), kh.getSodienthoai(), kh.getEmail(),  kh.getNgaysinh(), kh.isTrangthaian(), kh.getIdkhachhang());
    }

    // Xóa (Nên dùng xóa mềm - đổi trạng thái)
    public void delete(int id) {
        String sql = "DELETE FROM khach_hang WHERE idkhachhang=?";
        XJdbc.executeUpdate(sql, id);
    }

    // Tìm kiếm
    public List<khachhang> search(String keyword) {
        String sql = "SELECT * FROM khach_hang WHERE tenkhachhang LIKE ? OR sodienthoai LIKE ?";
        return XQuery.getBeanList(khachhang.class, sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public thicuoimon.duan1.entity.khachhang getById(int id) {
        String sql = "SELECT * FROM khach_hang WHERE idkhachhang = ?";
        List<thicuoimon.duan1.entity.khachhang> list = XQuery.getBeanList(thicuoimon.duan1.entity.khachhang.class, sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

}
