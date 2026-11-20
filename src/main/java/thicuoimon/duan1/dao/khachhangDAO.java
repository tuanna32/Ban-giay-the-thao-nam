/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import thicuoimon.duan1.entity.GiaoDich;
import java.util.Date;
import java.util.List;
import thicuoimon.duan1.entity.khachhang;

/**
 *
 * @author Asus
 */
public interface khachhangDAO extends CrudDAO<khachhang, Integer>{

 List<khachhang> timTheoTen(String ten);
    List<khachhang> timTheoSDT(String sdt);
    List<khachhang> getPage(int page, int size);
    List<khachhang> locTheoNgayTao(Date tuNgay, Date denNgay);

    public void add(khachhang kh);
    void insert(khachhang kh);
    public void delete(khachhang kh);

    public List<khachhang> findByTenOrSDT(String keyword);
    khachhang getById(int id);

    public List<khachhang> findByTen(String keyword);
 
    public List<GiaoDich> findBygd (int id);

    public void delete(Integer id);

    public List<Object[]> findBygd(String tuKhoa);
 public int countKhachHangUsingDiaChi(Integer iddiachi);
    public void update1(khachhang entity);
    public khachhang create1(khachhang entity);
    khachhang creKHbanle(khachhang entity);
    khachhang finbymaBanhang(String ma);
   int getTotalCount();
   boolean isMaKhachHangExist(String ma);

    boolean isSDTExist(String sdt);

    boolean isMaKhachHangExistForUpdate(String ma, int id);

    boolean isSDTExistForUpdate(String sdt, int id);

    boolean isEmailExistForUpdate(String email, int id);
}
