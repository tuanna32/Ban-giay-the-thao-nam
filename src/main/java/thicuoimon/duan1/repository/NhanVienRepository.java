/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimon.duan1.repository;

import java.util.List;
import thicuoimon.duan1.entity.NhanVien1;
import thicuoimon.duan1.util.XQuery;

/**
 *
 * @author honga
 */
public class NhanVienRepository {

    public List<NhanVien1> getAll() {
        String sql = "SELECT * FROM nhan_vien";
        return XQuery.getBeanList(NhanVien1.class, sql);
    }

    public int add(NhanVien1 nv) {
        String sql = """
        INSERT INTO nhan_vien (manhanvien, hovaten, email, sodienthoai, matkhau, trangthai)
        VALUES (?, ?, ?, ?, ?, ?)
    """;
        return thicuoimon.duan1.util.XJdbc.executeUpdate(sql,
                nv.getManhanvien(),
                nv.getHovaten(),
                nv.getEmail(),
                nv.getSodienthoai(),
                nv.getMatkhau(),
                nv.getTrangthai()
        );
    }

// Sửa lại getById cho đúng tên cột ID của bạn (idnhanvien)
    public NhanVien1 getById(int id) {
        String sql = "SELECT * FROM nhan_vien WHERE idnhanvien = ?";
        return XQuery.getSingleBean(NhanVien1.class, sql, id);
    }

    public int update(NhanVien1 nv) {
        String sql = """
        UPDATE nhan_vien
        SET manhanvien = ?, hovaten = ?, email = ?, sodienthoai = ?, matkhau = ?, trangthai = ?
        WHERE idnhanvien = ?
    """;

        return thicuoimon.duan1.util.XJdbc.executeUpdate(sql,
                nv.getManhanvien(),
                nv.getHovaten(),
                nv.getEmail(),
                nv.getSodienthoai(),
                nv.getMatkhau(),
                nv.getTrangthai(),
                nv.getIdnhanvien()
        );
    }

    public int delete(int id) {
        String sql = "DELETE FROM nhan_vien WHERE idnhanvien = ?";
        return thicuoimon.duan1.util.XJdbc.executeUpdate(sql, id);
    }

    public List<NhanVien1> search(String keyword) {
        // Câu lệnh SQL: Dùng CAST để ép kiểu ID sang chữ mới dùng LIKE được
        String sql = "SELECT * FROM nhan_vien WHERE "
                + "CAST(idnhanvien AS VARCHAR) LIKE ? OR "
                + "manhanvien LIKE ? OR "
                + "hovaten LIKE ?";

        String pattern = "%" + keyword + "%";

        // Sử dụng XQuery để tự động đổ dữ liệu vào List
        return thicuoimon.duan1.util.XQuery.getBeanList(
                thicuoimon.duan1.entity.NhanVien1.class,
                sql,
                pattern, pattern, pattern
        );
    }

    public List<NhanVien1> getPagination(int pageIndex, int pageSize) {
        // Truy vấn phân trang: Bỏ qua (pageIndex * pageSize) dòng và lấy pageSize dòng tiếp theo
        String sql = "SELECT * FROM nhan_vien ORDER BY idnhanvien OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        return XQuery.getBeanList(NhanVien1.class, sql, pageIndex * pageSize, pageSize);
    }

    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM nhan_vien";
        try (java.sql.ResultSet rs = thicuoimon.duan1.util.XJdbc.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
