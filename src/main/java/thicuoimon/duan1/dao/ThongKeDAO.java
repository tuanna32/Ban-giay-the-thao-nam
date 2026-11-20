package thicuoimon.duan1.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import thicuoimon.duan1.entity.ThongKe;
import thicuoimon.duan1.entity.hoadon;

public interface ThongKeDAO {
    List<ThongKe> getThongKeTheoNamVaThang(Date from, Date to);
    List<ThongKe> getDoanhThuTheoNgay(Date from, Date to);
    List<ThongKe> findAllThongKe();

    BigDecimal getTongDoanhThu();
    int getTongSoHoaDon();
    int getTongSoKhachHang();
    int getTongDonHuy();

    BigDecimal getTongDoanhThu(Date from, Date to);
    int getTongSoHoaDon(Date from, Date to);
    int getTongSoKhachHang(Date from, Date to);
    int getTongDonHuy(Date from, Date to);

    List<hoadon> bieuDo();
}