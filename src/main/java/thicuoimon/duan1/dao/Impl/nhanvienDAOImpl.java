    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package thicuoimon.duan1.dao.Impl;

    import thicuoimon.duan1.dao.nhanvienDAO;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.util.List;
    import thicuoimon.duan1.entity.nhanvien;
    import thicuoimon.duan1.util.XJdbc;
    import thicuoimon.duan1.util.XQuery;

    /**
     *
     * @author Asus
     */
    public class nhanvienDAOImpl implements nhanvienDAO{

        String CREATE_SQL = "INSERT INTO nhan_vien (idvaitro, manhanvien, hovaten, ngaysinh, cccd,gioitinh, diachi, email, sodienthoai, nguoisua, nguoixoa, ngaysua, ngayxoa,matkhau) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String UPDATE_SQL = """
UPDATE nhan_vien 
SET manhanvien=?, hovaten=?, email=?, sodienthoai=?, matkhau=?, trangthaian=? ,idvaitro=?
WHERE idnhanvien=?
""";

        String DELETE_SQL = "DELETE FROM nhan_vien WHERE idnhanvien=?";
        String FIND_ALL_SQL = "SELECT * FROM nhan_vien ORDER BY idnhanvien DESC ";
        String FIND_BY_ID_SQL = "SELECT * FROM nhan_vien WHERE idnhanvien=?";
        String FIND_BY_NAME_SQL = "SELECT * FROM nhan_vien WHERE hovaten LIKE ?";
        private final String BASE_SQL = """
        SELECT 
                    nv.idnhanvien,
                    nv.manhanvien,
                    nv.hovaten,
                    nv.email,
                    nv.sodienthoai,
                    nv.matkhau,
                    nv.trangthaian,
                    nv.idvaitro,  
                    vt.tenvaitro AS tenvaitro
                FROM nhan_vien nv
                JOIN vai_tro vt ON nv.idvaitro = vt.idvaitro
            """;
        @Override
        public nhanvien create(nhanvien nv) {
            Object[] values = {
                nv.getIdvaitro(),
                nv.getManhanvien(),
                nv.getHovaten(),
                nv.getNgaySinh(),
                nv.getCccd(),
                nv.isGioitinh(),
                nv.getDiaChi(),
                nv.getEmail(),
                nv.getSodienthoai(),
                nv.getNguoisua(),
                nv.getNguoixoa(),
                nv.getNgaysua(),
                nv.getNgayxoa(),
                nv.getMatkhau()
            };
            XJdbc.executeUpdate(CREATE_SQL, values);
            return nv;
        }

        @Override
        public void update(nhanvien nv) {
            XJdbc.executeUpdate(UPDATE_SQL,
                nv.getManhanvien(),
                nv.getHovaten(),
                nv.getEmail(),
                nv.getSodienthoai(),
                nv.getMatkhau(),
                nv.isTrangthaian(),
                nv.getIdvaitro(),
                nv.getIdnhanvien()
        );
        }

        @Override
        public void deleteById(Integer id) {
            XJdbc.executeUpdate(DELETE_SQL, id);
        }

        @Override
        public List<nhanvien> findAll() {
            return XQuery.getBeanList(nhanvien.class, FIND_ALL_SQL);
        }

        @Override
        public nhanvien findById(Integer id) {
            return XQuery.getSingleBean(nhanvien.class, FIND_BY_ID_SQL, id);
        }

        @Override
        public List<nhanvien> timkiem(String ten) {
            //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return XQuery.getBeanList(nhanvien.class, FIND_BY_NAME_SQL, "%" + ten + '%');
        }

        public nhanvien findByMa(String maNV) {
            String sql = "SELECT * FROM nhan_vien WHERE manhanvien = ?";
            try (Connection con = XJdbc.openConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, maNV);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    nhanvien nv = new nhanvien();
                    nv.setIdnhanvien(rs.getInt("idnhanvien")); // nếu có
                    nv.setManhanvien(rs.getString("manhanvien"));
                    nv.setHovaten(rs.getString("hovaten"));
                    nv.setAnh(rs.getString("anh"));
                    nv.setNgaySinh(rs.getDate("ngaysinh"));
                    nv.setCccd(rs.getString("cccd"));
                    nv.setGioitinh(rs.getBoolean("gioitinh"));
                    nv.setDiaChi(rs.getString("diachi"));
                    nv.setEmail(rs.getString("email"));
                    nv.setSodienthoai(rs.getString("sodienthoai"));
                    nv.setIdvaitro(rs.getInt("idvaitro"));
                    return nv;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public nhanvien findByTaiKhoan(String taiKhoan) {
            String sql = "SELECT * FROM nhan_vien WHERE manhanvien = ?";
            try (Connection con = XJdbc.openConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, taiKhoan);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nhanvien nv = new nhanvien();
                    nv.setManhanvien(rs.getString("manhanvien"));
                    nv.setMatkhau(rs.getString("matkhau"));
                    // thêm các trường khác nếu cần
                    return nv;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public nhanvien findByMaAndPassword(String email, String matKhau) {
            String sql = "SELECT * FROM nhan_vien WHERE email = ? AND matkhau = ?";
        try (Connection con = XJdbc.openConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nhanvien nv = new nhanvien();
                nv.setIdnhanvien(rs.getInt("idnhanvien"));
                nv.setManhanvien(rs.getString("manhanvien"));
                nv.setMatkhau(rs.getString("matkhau"));
                nv.setHovaten(rs.getString("hovaten"));
                nv.setAnh(rs.getString("anh"));
                nv.setNgaySinh(rs.getDate("ngaysinh"));
                nv.setCccd(rs.getString("cccd"));
                nv.setGioitinh(rs.getBoolean("gioitinh"));
                nv.setDiaChi(rs.getString("diachi"));
                nv.setEmail(rs.getString("email"));
                nv.setSodienthoai(rs.getString("sodienthoai"));
                nv.setIdvaitro(rs.getInt("idvaitro"));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }

        public nhanvien findByMaa(String maNV) {
            String sql = "SELECT * FROM nhan_vien WHERE manhanvien = ?";
            try (Connection con = XJdbc.openConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, maNV);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nhanvien nv = new nhanvien();
                    nv.setManhanvien(rs.getString("manhanvien"));
                    // Gán các trường còn lại nếu cần
                    return nv;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        public nhanvien findByEmail(String email) {
        String sql = "SELECT * FROM nhan_vien WHERE email = ?";
        return XQuery.getSingleBean(nhanvien.class, sql, email);
    }

        @Override
        public boolean existsMaNV(String ma) {
            String sql = "SELECT COUNT(*) FROM nhan_vien WHERE manhanvien = ?";
            try (java.sql.ResultSet rs = XJdbc.executeQuery(sql, ma)) {
                return rs.next() && rs.getInt(1) > 0;
            } catch (Exception e) {
                return false;
            }

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean existsSDT(String sdt) {
            String sql = "SELECT COUNT(*) FROM nhan_vien WHERE sodienthoai = ?";
            try (java.sql.ResultSet rs = XJdbc.executeQuery(sql, sdt)) {
                return rs.next() && rs.getInt(1) > 0;
            } catch (Exception e) {
                return false;
            }

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean existsEmail(String email) {
            String sql = "SELECT COUNT(*) FROM nhan_vien WHERE email = ?";
            try (java.sql.ResultSet rs = XJdbc.executeQuery(sql, email)) {
                return rs.next() && rs.getInt(1) > 0;
            } catch (Exception e) {
                return false;
            }

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean existsMaNVExceptId(String ma, int id) {
             String sql = "SELECT COUNT(*) FROM nhan_vien WHERE manhanvien = ? AND idnhanvien <> ?";
            try (java.sql.ResultSet rs = XJdbc.executeQuery(sql, ma, id)) {
                return rs.next() && rs.getInt(1) > 0;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public boolean existsSDTExceptId(String sdt, int id) {
             String sql = "SELECT COUNT(*) FROM nhan_vien WHERE sodienthoai = ? AND idnhanvien <> ?";
            try (java.sql.ResultSet rs = XJdbc.executeQuery(sql, sdt, id)) {
                return rs.next() && rs.getInt(1) > 0;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public boolean existsEmailExceptId(String email, int id) {
             String sql = "SELECT COUNT(*) FROM nhan_vien WHERE email = ? AND idnhanvien <> ?";
            try (java.sql.ResultSet rs = XJdbc.executeQuery(sql, email, id)) {
                return rs.next() && rs.getInt(1) > 0;
            } catch (Exception e) {
                return false;
            }

    }

        @Override
        public nhanvien getById(int id) {
            String sql = BASE_SQL + " WHERE idnhanvien = ?";
            return XQuery.getSingleBean(nhanvien.class, sql, id);

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public int add(nhanvien nv) {
            String sql = """
            INSERT INTO nhan_vien (manhanvien, hovaten, email, sodienthoai, matkhau, trangthaian, idvaitro)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

            return XJdbc.executeUpdate(sql,
                    nv.getManhanvien(),
                    nv.getHovaten(),
                    nv.getEmail(),
                    nv.getSodienthoai(),
                    nv.getMatkhau(),
                    nv.isTrangthaian(),
                    nv.getIdvaitro()
            );
        }

        @Override
        public int getTotalCount() {
            String sql = "SELECT COUNT(*) FROM nhan_vien";
            try (java.sql.ResultSet rs = XJdbc.executeQuery(sql)) {
                if (rs.next()) return rs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public int delete(int id) {
            String sql = "DELETE FROM nhan_vien WHERE idnhanvien = ?";
            return XJdbc.executeUpdate(sql, id);
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<nhanvien> search(String keyword) {
            String sql = BASE_SQL + """
            WHERE CAST(idnhanvien AS VARCHAR) LIKE ?
            OR manhanvien LIKE ?
            OR hovaten LIKE ?
            OR sodienthoai LIKE ?
            OR vt.tenvaitro LIKE ?
            OR email LIKE ?
            """;

            String pattern = "%" + keyword + "%";

            return XQuery.getBeanList(
                    nhanvien.class,
                    sql,
                    pattern, pattern, pattern, pattern, pattern, pattern
            );
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<nhanvien> getPagination(int pageIndex, int pageSize) {

            String sql = BASE_SQL + """
            ORDER BY idnhanvien
            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
            """;

            return XQuery.getBeanList(nhanvien.class, sql, pageIndex * pageSize, pageSize);
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }



    }
