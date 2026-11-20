/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import java.util.List;
import thicuoimon.duan1.entity.nhanvien;

/**
 *
 * @author Asus
 */
    public interface nhanvienDAO extends CrudDAO<nhanvien, Integer>{
      List<nhanvien> timkiem(String ten);
      boolean existsMaNV(String ma);

    boolean existsSDT(String sdt);

    boolean existsEmail(String email);

    boolean existsMaNVExceptId(String ma, int id);

    boolean existsSDTExceptId(String sdt, int id);

    boolean existsEmailExceptId(String email, int id);
    nhanvien getById(int id);
    int add(nhanvien nv);
    int getTotalCount();
    int delete(int id);
     List<nhanvien> search(String keyword);
      List<nhanvien> getPagination(int pageIndex, int pageSize);
}
