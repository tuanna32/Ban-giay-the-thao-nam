    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package thicuoimon.duan1.dao.Impl;

    import thicuoimon.duan1.entity.GiaoDich;
    import thicuoimon.duan1.dao.khachhangDAO;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.List;
    import thicuoimon.duan1.entity.khachhang;
    import thicuoimon.duan1.util.XJdbc;
    import thicuoimon.duan1.util.XQuery;

    /**
     *
     * @author Asus
     */
    public class khachhangDAOImpl implements khachhangDAO{

        String creKHbanle = "insert into khach_hang(makhachhang,hovaten,trangthaian)\n"
                + "values(?,?,?)";
        String cre = "INSERT INTO khach_hang (makhachhang, hovaten, ngaysinh, sodienthoai, email,iddiachi, nguoithem, nguoisua, nguoixoa, ngaythem, ngaysua, ngayxoa,trangthaian) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        String update = "UPDATE khach_hang SET hovaten=?, ngaysinh=?, sodienthoai=?, email=?, iddiachi=?,trangthaian=?, nguoisua=?, nguoixoa=?, ngaysua=?, ngayxoa=? WHERE idkhachhang=?";
        String delete = "UPDATE khach_hang SET trangthaian = 0 WHERE idkhachhang = ?";
        String findAll = """
        SELECT 
            kh.idkhachhang,
            kh.iddiachi,
            kh.makhachhang,
            kh.hovaten,
            kh.ngaysinh,
            kh.sodienthoai,
            kh.email,
            kh.gioitinh,
            kh.nguoithem,
            kh.nguoisua,
            kh.nguoixoa,
            kh.ngaythem,
            kh.ngaysua,
            kh.ngayxoa,
            kh.trangthaian,
            dc.diachicuthe + ', ' + dc.phuongxa + ', ' + dc.quanhuyen + ', ' + dc.tinhthanh AS diachi
        FROM khach_hang kh
        LEFT JOIN dia_chi_khach_hang dc ON kh.iddiachi = dc.iddiachi
        WHERE kh.trangthaian = 1
        ORDER BY kh.idkhachhang DESC
    """;    
        String findbyid = """
        SELECT 
            idkhachhang,
            iddiachi,
            makhachhang,
            hovaten,
            ngaysinh,
            sodienthoai,
            email,
            gioitinh,
            nguoithem,
            nguoisua,
            nguoixoa,
            ngaythem,
            ngaysua,
            ngayxoa,
            trangthaian
        FROM khach_hang 
        WHERE idkhachhang=?
    """;    
        String findbyma = "SELECT \n" +
    "    idkhachhang,\n" +
    "    iddiachi,\n" +
    "    makhachhang,\n" +
    "    hovaten,\n" +
    "    ngaysinh,\n" +
    "    sodienthoai,\n" +
    "    email,\n" +
    "    gioitinh,\n" +
    "    nguoithem,\n" +
    "    nguoisua,\n" +
    "    nguoixoa,\n" +
    "    ngaythem,\n" +
    "    ngaysua,\n" +
    "    ngayxoa,\n" +
    "    trangthaian"
                + " FROM khach_hang WHERE makhachhang=?";
        String findByTen = """
        SELECT kh.*,
                   CONCAT_WS(', ', dc.diachicuthe, dc.phuongxa, dc.quanhuyen, dc.tinhthanh) AS diachi 
            FROM khach_hang kh 
            LEFT JOIN dia_chi_khach_hang dc ON kh.iddiachi = dc.iddiachi
            WHERE kh.hovaten LIKE ?
    """;
        String findBySDT = "SELECT \n" +
    "    idkhachhang,\n" +
    "    iddiachi,\n" +
    "    makhachhang,\n" +
    "    hovaten,\n" +
    "    ngaysinh,\n" +
    "    sodienthoai,\n" +
    "    email,\n" +
    "    gioitinh,\n" +
    "    nguoithem,\n" +
    "    nguoisua,\n" +
    "    nguoixoa,\n" +
    "    ngaythem,\n" +
    "    ngaysua,\n" +
    "    ngayxoa,\n" +
    "trangthaian "
    + "FROM khach_hang WHERE sodienthoai LIKE ?";

        String findBygd = "SELECT \n"
                + "        kh.idkhachhang AS idKhachHang,\n"
                + "        kh.makhachhang AS maKhachHang,\n"
                + "        hd.mahoadon AS maHoaDon,\n"
                + "        kh.hovaten AS hovaten,\n"
                + "        kh.sodienthoai AS soDienThoai,\n"
                + "        dc.diachicuthe AS diaChi,\n"
                + "        hd.ngayxuathoadon AS ngayGiaoDich,\n"
                + "        hd.tongtiensaugiam AS tongTien,\n"
                + "        hd.trangthaian AS trangthaian\n"
                + "    FROM \n"
                + "        khach_hang kh\n"
                + "    JOIN \n"
                + "        hoa_don hd ON kh.idkhachhang = hd.idkhachhang\n"
                + "    JOIN \n"
                + "        dia_chi_khach_hang dc ON kh.iddiachi = dc.iddiachi";
        String countidKH = "select count(idkhachhang) from khach_hang where iddiachi=?";
        String update1SQL = "update khach_hang set iddiachi=?, hovaten=?,sodienthoai=?,email=?,gioitinh=?,nguoisua=?,ngaysua=? where idkhachhang=?";
        String create1SQL = "insert into khach_hang(iddiachi,makhachhang,hovaten,sodienthoai,email,nguoithem,ngaythem,trangthaian)\n"
                + "values(?,?,?,?,?,?,?,1)";

        @Override
        public khachhang create(khachhang entity) {
    //        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Object[] values = {
                entity.getMakhachhang(),
                entity.getHovaten(),
                entity.getNgaysinh(),
                entity.getSodienthoai(),
                entity.getEmail(),

                entity.getIddiachi(),
                entity.getNguoithem(),
                entity.getNguoisua(),
                entity.getNguoixoa(),
                entity.getNgaythem(),
                entity.getNgaysua(),
                entity.getNgayxoa(),
                true
            };

            XJdbc.executeUpdate(cre, values);
            return null;
        }

        @Override
        public void update(khachhang entity) {
    //       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Object[] values = {
                entity.getHovaten(),
                entity.getNgaysinh(),
                entity.getSodienthoai(),
                entity.getEmail(),
                entity.getIddiachi(),
                entity.isTrangthaian(),
                entity.getNguoisua(),
                entity.getNguoixoa(),
                entity.getNgaysua(),
                entity.getNgayxoa(),
                entity.getIdkhachhang()
            };
            XJdbc.executeUpdate(update, values);
        }

        @Override
        public void deleteById(Integer id) {
    //        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            String delete = "DELETE FROM khach_hang WHERE idkhachhang = ?";
            XJdbc.executeUpdate(delete, id);
        }

        @Override
        public List<khachhang> findAll() {
            // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return XQuery.getBeanList(khachhang.class, findAll);
        }

        @Override
        public khachhang findById(Integer id) {
            //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return XQuery.getSingleBean(khachhang.class, findbyid, id);
        }

        @Override
        public List<khachhang> timTheoTen(String ten) {
    //        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return XQuery.getBeanList(khachhang.class, findByTen, "%" + ten + "%");
        }

        @Override
        public List<khachhang> timTheoSDT(String sdt) {
    //        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return XQuery.getBeanList(khachhang.class, findBySDT, "%" + sdt + "%");
        }


        @Override
        public void add(khachhang kh) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void delete(khachhang kh) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<khachhang> findByTenOrSDT(String keyword) {
             String sql = """
            SELECT 
                idkhachhang,
                iddiachi,
                makhachhang,
                hovaten,
                ngaysinh,
                sodienthoai,
                email,
                gioitinh,
                nguoithem,
                nguoisua,
                nguoixoa,
                ngaythem,
                ngaysua,
                ngayxoa,
                trangthaian
            FROM khach_hang
            WHERE hovaten LIKE ? OR sodienthoai LIKE ?
        """;

        return XQuery.getBeanList(
            khachhang.class,
            sql,
            "%" + keyword + "%",
            "%" + keyword + "%"
        );
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<khachhang> findByTen(String keyword) {
    //        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return XQuery.getBeanList(khachhang.class, findByTen, "%" + keyword + "%");
        }

        @Override
        public List<khachhang> locTheoNgayTao(Date tuNgay, Date denNgay) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<GiaoDich> findBygd(int id) {
    //        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return XQuery.getBeanList(GiaoDich.class, findBygd, id);
        }

        @Override
        public void delete(Integer id) {
    //        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            String delete = "UPDATE khach_hang SET trangthaian = 0 WHERE idkhachhang = ?";
            XJdbc.executeUpdate(delete, id);
        }

        @Override
        public List<Object[]> findBygd(String tuKhoa) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public int countKhachHangUsingDiaChi(Integer iddiachi) {
            //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            try {
                ResultSet rs = XJdbc.executeQuery(countidKH, iddiachi);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi truy vấn số khách hàng theo địa chỉ: " + e.getMessage());
            }
            return 0;
        }

        @Override
        public void update1(khachhang entity) {
            //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Object[] values = {
                entity.getIddiachi(),
                entity.getHovaten(),
                entity.getSodienthoai(),
                entity.getEmail(),

                entity.getNguoisua(),
                entity.getNgaysua(),
                entity.getIdkhachhang()
            };
            XJdbc.executeUpdate(update1SQL, values);

        }

        @Override
        public khachhang create1(khachhang entity) {
            //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Object[] values = {
                entity.getIddiachi(),
                entity.getMakhachhang(),
                entity.getHovaten(),
                entity.getSodienthoai(),
                entity.getEmail(),

                entity.getNguoithem(),
                entity.getNgaythem()
            };
            XJdbc.executeUpdate(create1SQL, values);
            return entity;
        }

        @Override
        public khachhang creKHbanle(khachhang entity) {
            //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Object[] values = {
                entity.getMakhachhang(),
                entity.getHovaten(),
                entity.isTrangthaian()
            };
            XJdbc.executeUpdate(creKHbanle, values);
            return entity;
        }

        @Override
        public khachhang finbymaBanhang(String ma) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            String findbymaBH = "SELECT * FROM khach_hang WHERE makhachhang=? and trangthaian=1";
            return XQuery.getSingleBean(khachhang.class, findbymaBH, ma);

        }

        @Override
        public List<khachhang> getPage(int page, int size) {
           String sql = """
        SELECT 
            idkhachhang,
            iddiachi,
            makhachhang,
            hovaten,
            ngaysinh,
            sodienthoai,
            email,
            gioitinh,
            nguoithem,
            nguoisua,
            nguoixoa,
            ngaythem,
            ngaysua,
            ngayxoa,
            trangthaian
        FROM khach_hang
        ORDER BY idkhachhang
        OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
    """;    

        return XQuery.getBeanList(
            khachhang.class,
            sql,
            (page - 1) * size,
            size
        );
        }

        @Override
        public khachhang getById(int id) {
            String sql = "SELECT idkhachhang, makhachhang, hovaten AS tenkhachhang, "
                    + "sodienthoai, email, ngaysinh, trangthaian AS trangthai "
                    + "FROM khach_hang WHERE idkhachhang = ?";
            List<khachhang> list = XQuery.getBeanList(khachhang.class, sql, id);
            return list.isEmpty() ? null : list.get(0);
    //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public int getTotalCount() {
            String sql = "SELECT COUNT(*) FROM khach_hang";
            try (ResultSet rs = XJdbc.executeQuery(sql)) {
                if (rs.next()) return rs.getInt(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
    //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isMaKhachHangExist(String ma) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isSDTExist(String sdt) {
            String sql = "SELECT COUNT(*) FROM khach_hang WHERE sodienthoai = ?";
            try (ResultSet rs = XJdbc.executeQuery(sql, sdt)) {
                return rs.next() && rs.getInt(1) > 0;
            } catch (Exception e) {
                return false;
            }

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isMaKhachHangExistForUpdate(String ma, int id) {
            String sql = "SELECT COUNT(*) FROM khach_hang WHERE makhachhang = ? AND idkhachhang <> ?";
            Object result = XJdbc.getValue(sql, ma, id);
            return result != null && Integer.parseInt(result.toString()) > 0;

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isSDTExistForUpdate(String sdt, int id) {
            String sql = "SELECT COUNT(*) FROM khach_hang WHERE sodienthoai = ? AND idkhachhang <> ?";
            Object result = XJdbc.getValue(sql, sdt, id);
            return result != null && Integer.parseInt(result.toString()) > 0;
        }

        @Override
        public boolean isEmailExistForUpdate(String email, int id) {
            String sql = "SELECT COUNT(*) FROM khach_hang WHERE email = ? AND idkhachhang <> ?";
            Object result = XJdbc.getValue(sql, email, id);
            return result != null && Integer.parseInt(result.toString()) > 0;

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void insert(khachhang kh) {
            String sql = "INSERT INTO khach_hang (makhachhang, hovaten, sodienthoai, email, ngaysinh, trangthaian) VALUES (?, ?, ?,  ?, ?, ?)";
            XJdbc.executeUpdate(sql,
                    kh.getMakhachhang(),
                    kh.getHovaten(),
                    kh.getSodienthoai(),
                    kh.getEmail(),

                    kh.getNgaysinh(),
                    kh.isTrangthaian()
            );
        }

    }
