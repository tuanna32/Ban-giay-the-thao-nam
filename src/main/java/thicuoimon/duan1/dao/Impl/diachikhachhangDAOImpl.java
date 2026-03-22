/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimon.duan1.dao.Impl;

import thicuoimon.duan1.dao.diachikhachhangDAO;
import java.util.List;
import thicuoimon.duan1.entity.diachikhachhang;
import thicuoimon.duan1.util.XJdbc;
import thicuoimon.duan1.util.XQuery;

/**
 *
 * @author Asus
 */
public class diachikhachhangDAOImpl implements diachikhachhangDAO{

    String cre = "INSERT INTO dia_chi_khach_hang(\n"
            + "    madiachikhachhang, diachicuthe, phuongxa, quanhuyen, tinhthanh, \n"
            + "    ghichu, nguoithem, nguoisua, nguoixoa, ngaythem, ngaysua, ngayxoa, trangthaian\n"
            + ")\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String findall = "select * from dia_chi_khach_hang";
    String findbyid = "select * from dia_chi_khach_hang\n"
            + "where iddiachi=?";
    String findByDCCT = "SELECT * \n"
            + "FROM dia_chi_khach_hang \n"
            + "WHERE diachicuthe = ? \n"
            + "  AND phuongxa = ? \n"
            + "  AND (quanhuyen = ? OR quanhuyen IS NULL)\n"
            + "  AND tinhthanh = ?";

    @Override
    public diachikhachhang create(diachikhachhang entity) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Object[] values = {
            entity.getMadiachikhachhang(),
            entity.getDiachicuthe(),
            entity.getPhuongxa(),
            entity.getQuanhuyen(),
            entity.getTinhthanh(),
            entity.getGhichu(),
            entity.getNguoithem(),
            entity.getNguoisua(),
            entity.getNguoixoa(),
            entity.getNgaythem(),
            entity.getNgaysua(),
            entity.getNgayxoa(),
            entity.isTrangthaian()
        };
        XJdbc.executeUpdate(cre, values);
        return entity;
    }

    @Override
    public void update(diachikhachhang entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<diachikhachhang> findAll() {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return XQuery.getBeanList(diachikhachhang.class, findall);
    }

    @Override
    public diachikhachhang findById(Integer id) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return XQuery.getSingleBean(diachikhachhang.class, findbyid, id);
    }

    @Override
    public void add(diachikhachhang dc) {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "INSERT INTO dia_chi_khach_hang (diachicuthe,phuongxa, quanhuyen, tinhthanh) VALUES (?, ?, ?, ?)";
        XJdbc.executeUpdate(sql, dc.getDiachicuthe(), dc.getPhuongxa(), dc.getQuanhuyen(), dc.getTinhthanh());
    }

    @Override
    public diachikhachhang findByDCCT(String diachi) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String[] parts = diachi.split(",", -1);
        String cuthe = parts[0].trim();
        String phuongxa = parts[1].trim();
        String quanhuyen = (parts.length == 4) ? parts[2].trim() : null;
        String tinhthanh = (parts.length == 4) ? parts[3].trim() : parts[2].trim();
        return XQuery.getSingleBean(diachikhachhang.class, findByDCCT, cuthe, phuongxa, quanhuyen, tinhthanh);
    }

}
