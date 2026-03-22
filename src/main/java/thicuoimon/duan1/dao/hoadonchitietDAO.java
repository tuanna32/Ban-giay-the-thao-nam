/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import java.util.List;
import thicuoimon.duan1.entity.hoadonchitiet;

/**
 *
 * @author Asus
 */
public interface hoadonchitietDAO extends CrudDAO<hoadonchitiet, Integer> {

    List<hoadonchitiet> timhdct(int id);

    List<hoadonchitiet> timtenspcuahdct(int id, String ten);

    List<hoadonchitiet>findallgiohang(int id);
   hoadonchitiet findidhdctbyidhdvaidspct(int idhd,int idspct);
     List<hoadonchitiet>findbytimkiemallgiohang(int id);
}