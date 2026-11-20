/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import java.util.List;
import thicuoimon.duan1.entity.thuonghieu;


/**
 *
 * @author Admin
 */
public interface thuonghieuDAO1 extends CrudDAO<thuonghieu, Integer>{
    thuonghieu findByTen(String ten);
     thuonghieu findByTenRealtheoidhoadon(String ten,int id);
}
